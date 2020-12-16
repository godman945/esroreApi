import axios from 'axios';
import MessageService from '@/assets/services/message.service.js';
import ValidateUtil from '@/assets/services/validateUtil.js';
import Log from '@/assets/services/log.js';

/*
ajax/form service
  request pattern
    token : JWT 權限認證，系統自帶，沒有導回 login page
    source : 發送系統，系統自帶，e.g. MobileCounter
    data : 商務邏輯資料，這邊就交給 PG 送
  response pattern
    status code : 後端 AP 回傳的狀態碼
      分類 : success, notification, form
      詳細狀態碼可再設計
    message : 需處理的訊息，e.g. vaildate message
      {form field : vaildate msg} or {error msg : 非4G門號無法使用}
    data :  商務邏輯資料，這邊就交給 PG 送
  response pattern (exception 
    http status code : 4xx, 500
    errorhandler : 發生錯誤統一處理，return page or notification，並記錄下 error log
header service
  跨網站傳送訊息，e.g. token
*/

/*-------------------------- 攔截器 Start -------------------------- */
// 新增 axios request 攔截器
axios.interceptors.request.use(function (config) {
  let vue = window.vm; /* 取得Vue物件 */
  vue.$debug.info('axios request');
  vue.$debug.info(config);

  /*
    由於 SIT 環境與 UAT 環境在同一個機器上，判斷如果是 SIT 就替換 URL 位置
    這段邏輯到了 UST Branch 被廢除時也該一併廢除 Edit By Yang
  */
  if(location.href != null && location.href.indexOf("NSPMobile_SIT")  != -1){
    config.url = config.url.replace("NSPMobile_API","NSPMobile_API_SIT");
  }

  return config;
}, function (error) {
  // Do something with request error
  return Promise.reject(error);
});

// 新增 axios response 攔截器
axios.interceptors.response.use(function (response) {
  let vue = window.vm; /* 取得Vue物件 */
  vue.$debug.info('axios response');
  vue.$debug.info(response);
  let statusCode = vue.$const.statusCode; /* 取得網路狀態代碼 */

  //TODO 如果有要做什麼共同驗證可以在這處理

  return response;
}, function (error) {
  // Do something with response error
  return Promise.reject(error);
});

/*-------------------------- 攔截器 End -------------------------- */

function downloadFile(response) {
  let filename = moment().format('YYYY-MM-DD') + '.xlsx';
  // 取後端傳回的檔案名稱
  const contentDisposition = response.headers['content-disposition'];
  if (contentDisposition) {
    if (contentDisposition.indexOf('filename=') !== -1) {
      let filenameStr = contentDisposition.substring(contentDisposition.indexOf('filename='));
      let filenameArr = filenameStr.split('=');
      filename = decodeURI(filenameArr[1]);
    }
  }

  // 下載檔案
  const url = window.URL.createObjectURL(new Blob([response.data]));
  const link = document.createElement('a');
  link.href = url;
  link.setAttribute('download', filename); //or any other extension
  document.body.appendChild(link);
  link.click();
  link.remove();
  window.URL.revokeObjectURL(url);
}


const AjaxService = {
  post(url, data, callBack, errorCallBack, urlParam) {

    let vue = window.vm; /* 取得Vue物件 */
    window.vm.blockCount++; // 加上 ajax 計數器，平行呼叫時才擋得住畫面

    if(window.vm.blockCount == 1){
      // 目前使用上 vue-loading-overlay 在畫面上不能同時存在兩個，所以註冊到 Vue 中統一控管
      window.vm.loader = vue.$loading.show();/* 開啟loading小圈圈 */
    }

    axios.post(`${url}`, data, {
      params : urlParam
    }).then((response) => {
        
        // 1.2 回傳正確資訊
        callBack(response.data);

      }).catch((error) => {

        // 1 呼叫後端失敗
        if (errorCallBack) {
          // 1.1 有給自定義要執行的 error call back 的 function
          errorCallBack(error);
        } else {
          // 1.2 預設網路傳輸錯誤處理
          MessageService.showError('Network Error', '請稍後再試');
        }
        vue.$debug.error(error);
      }).then((final) => {
        window.vm.blockCount--;
        if(window.vm.blockCount <= 0){
        // 3 關掉loading 小圈圈
          window.vm.blockCount = 0;
          if(window.vm.loader != null){
            window.vm.loader.hide();
          }
        }
      });
  },

  get(url, data, callBack, errorCallBack, urlParam) {

    let vue = window.vm; /* 取得Vue物件 */
    window.vm.blockCount++; // 加上 ajax 計數器，平行呼叫時才擋得住畫面

    if(window.vm.blockCount == 1){
      // 目前使用上 vue-loading-overlay 在畫面上不能同時存在兩個，所以註冊到 Vue 中統一控管
      window.vm.loader = vue.$loading.show();/* 開啟loading小圈圈 */
    }

    axios.get(`${url}`, data, {
      params : urlParam
    }).then((response) => {
        
        // 1.2 回傳正確資訊
        callBack(response.data);

      }).catch((error) => {

        // 1 呼叫後端失敗
        if (errorCallBack) {
          // 1.1 有給自定義要執行的 error call back 的 function
          errorCallBack(error);
        } else {
          // 1.2 預設網路傳輸錯誤處理
          MessageService.showError('Network Error', '請稍後再試');
        }
        vue.$debug.error(error);
      }).then((final) => {
        window.vm.blockCount--;
        if(window.vm.blockCount <= 0){
        // 3 關掉loading 小圈圈
          window.vm.blockCount = 0;
          if(window.vm.loader != null){
            window.vm.loader.hide();
          }
        }
      });
  },

  /**
   * @description 多個 Action 的進度條，配合 MultiLoadingService 使用
   * @param {*} url 
   * @param {*} data 
   * @param {*} callBack 
   * @param {*} errorCallBack 
   * @param {*} key             配合 MultiLoadingService 的 multiLoadingData input Key
   */
  multiLoadingPost(url, data, callBack, errorCallBack, key) {

    let vue = window.vm; /* 取得Vue物件 */
    
    axios.post(`${url}`, data)
      .then((response) => {

        if(key != null){
          window.vm.$multiLoadingData.data[key]["isSuccess"] = true;
          window.vm.$multiLoadingData.data[key]["isPass"] = response.data.success;
        }

        callBack(response.data);
      }).catch((error) => {
        //JWT驗證失敗回傳401，目前僅顯示錯誤訊息

        if(key != null){
          window.vm.$multiLoadingData.data[key]["isSuccess"] = true;
          window.vm.$multiLoadingData.data[key]["isPass"] = false;
        }

        // 2 呼叫後端失敗
        if (errorCallBack) {
          // 2.1 有給自定義要執行的 error call back 的 function
          errorCallBack(error);
        } else {
          // 2.2 預設網路傳輸錯誤處理
          MessageService.showError('Network Error', '請稍後再試');
        }

        vue.$debug.error(error);
      }).then((final) => {

      });
  },
  /**
   * @description 提供 Promise 的 post function 可以使用 await 操作
   * @param {*} url 
   * @param {*} params 
   */
  promisePost(url, params) {
    return new Promise((resolve, reject) => {
      axios.post(`${url}`, params)
        .then((response) => {
          // 處理回傳，只將需要的Data傳回
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    })
  },
  /**
   * @description 提供 Promise 的 get function 可以使用 await 操作
   * @param {*} url 
   * @param {*} params 
   */
  promiseGet(url, params) {
    return new Promise((resolve, reject) => {
      axios.get(`${url}`, params)
        .then((response) => {
          // 處理回傳，只將需要的Data傳回
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    })
  },
  nonStatuspost(url, data, callBack, urlParam) {// 服務異動裡面有呼叫某些 Action 不需要管 Status 是否為 200

    let vue = window.vm; /* 取得Vue物件 */
    window.vm.blockCount++; // 加上 ajax 計數器，平行呼叫時才擋得住畫面

    if(window.vm.blockCount == 1){
      // 目前使用上 vue-loading-overlay 在畫面上不能同時存在兩個，所以註冊到 Vue 中統一控管
      window.vm.loader = vue.$loading.show();/* 開啟loading小圈圈 */
    }

    axios.post(`${url}`, data, {
      validateStatus: function (status) {
        return status >= 1 // 忽略所有 status 讓他都進入成功的區塊
      },
      params : data
    }).then((response) => {
        
        // 1.2 回傳正確資訊
        callBack(response.data);

      }).then((final) => {
        window.vm.blockCount--;
        if(window.vm.blockCount <= 0){
        // 3 關掉loading 小圈圈
          window.vm.blockCount = 0;
          if(window.vm.loader != null){
            window.vm.loader.hide();
          }
        }
      });
  },
  async postExcelFile(url, data, callBack, errorCallBack) {
    /*
      1. handle success
      2. handle error
    */
    axios.post(`${url}`, data, {
        responseType: 'arraybuffer'
      })
      .then((response) => {
        // 1. 處理正常回傳
        if (response && response.headers && response.data) {
          // 認 Header 的 content-type，判斷要跳訊息還是要下載檔案
          const contentType = response.headers['content-type'];
          if (contentType) {
            // 回傳這個表示要跳訊息
            if (contentType.indexOf('json') !== -1) {
              const text = Buffer.from(response.data).toString('utf8');
              let jsonObj = JSON.parse(text);
              callBack(jsonObj);
            } else {
              downloadFile(response);
              callBack({
                success: true
              });
            }
          }
        }
      }).catch((error) => {
        // 2 呼叫後端失敗
        if (errorCallBack) {
          // 2.1 有給自定義要執行的 error call back 的 function
          errorCallBack(error);
        } else {
          // 2.2 預設網路傳輸錯誤處理
          MessageService.showError('Network Error', '請稍後再試');
        }
      });
  },

  /**
   * @description 取得模組權限
   * @param {*} linkId 
   * @param {*} moduleCode 
   * @param {*} oriPayMode 
   * @param {*} parentModuleCode 
   */
  getComponentAuth(linkId,moduleCode,oriPayMode,parentModuleCode) {
    return new Promise((resolve, reject) => {
      Log.info("Action: 取得模組權限 START");
      AjaxService.post(
          '/NSP2/pageAuth/getComponentAuth.action',
          {
              linkId: linkId,
              moduleCode: moduleCode,
              oriPayMode: oriPayMode,
              parentModuleCode: parentModuleCode
          },
          (response) => {
              let data = response.result.datas;
              // validation
              if(ValidateUtil.validateEmpty(data)){
                  Log.info("查無使用者權限設定, 將使用預設權限設定");
                  resolve({});
                  return;
              }
              if(data.success != null && !data.success){
                  // MessageService.showInfo('查使用者權限設定失敗：'+ data.message);
                  // 用戶折扣查詢開啟此頁面不會傳linkId會有錯誤，先不show錯誤訊息
                  Log.info('查使用者權限設定失敗，將使用預設權限設定，失敗訊息：'+ data.message);
                  resolve({});
                  return;
              }
  
              resolve(data);
          },
          (error) => {
              MessageService.showSystemError();
          }
      );
      Log.info("Action: 取得模組權限 END");
    });
  },
  /**
   * @param {*} params url帶的參數($route.query物件)
   */
  getIdViewInfo(params) {
    return new Promise((resolve, reject) => {
        if(params == null) {
          return;
        }
        const msisdn = params.msisdn;
        const subscrId = params.subscrId;
        const accountId = params.accountId;
        
        // 參數沒傳 msisdn 也沒傳 subscrId，但有傳 accountId
        if(ValidateUtil.validateEmpty(msisdn) && ValidateUtil.validateEmpty(subscrId) 
            && !ValidateUtil.validateEmpty(accountId)){
            let idViewInfo = {};
            idViewInfo.acctId = accountId;
            idViewInfo.subscrId = null;
            idViewInfo.paymentCategory = 'PS';
            resolve(idViewInfo);
            return;
        }
        // 3個參數都沒傳
        if(ValidateUtil.validateEmpty(msisdn) && ValidateUtil.validateEmpty(subscrId) 
            && ValidateUtil.validateEmpty(accountId)){
            resolve();
            return;
        }

        Log.info("Action: 取得 idViewInfo START");
        AjaxService.post(
            '/NSP2/ncpCustDataQuery/getIdViewByAppPartDef.action', 
            {
                appPartDefCustQuery: {
                    msisdn: msisdn,
                    subscrId: subscrId
                }
            },
            (response) => {
                // validation
                if (ValidateUtil.validateEmpty(response) || ValidateUtil.validateEmpty(response.result) 
                    || ValidateUtil.validateEmpty(response.result.datas)) {
                    MessageService.showError("查詢客資時發生未知錯誤");
                    reject();
                    return;
                }

                const datas = response.result.datas;
                if (!datas.success) {
                    if (!ValidateUtil.validateEmpty(datas.message)) {
                        MessageService.showError(datas.message);
                    } else {
                        MessageService.showError("查詢客資時發生未知錯誤");
                    }
                    reject();
                    return;
                }

                resolve(datas.idViewInfo);
            },
            (error) => {
                MessageService.showSystemError();
            }
        );
        Log.info("Action: 取得 idViewInfo END");
    });
  },
  /**
   * @description 取得郵遞區號
   * @param {*} address 地址 
   */
  searchZipCode(address) {
    return new Promise((resolve, reject) => {
      Log.info("Action: 查詢郵遞區號 START");
      AjaxService.post(
          '/NSP2/common/searchZipCode.action',
          {
            address : address
          },
          (response) => {
            // validation
            if(ValidateUtil.validateEmpty(response) || ValidateUtil.validateEmpty(response.result) 
            || ValidateUtil.validateEmpty(response.result.datas)) {
              MessageService.showError("查詢郵遞區號失敗");
              reject();
              return;
            }

            let data = response.result.datas;
            if(ValidateUtil.validateEmpty(data.zipCode)){
              if(!ValidateUtil.validateEmpty(data.retMessage)) {
                MessageService.showError(data.retMessage);
              } else {
                MessageService.showError("請輸入正確地址");
              }
              reject();
              return;
            }

            resolve(data);
          },
          (error) => {
              MessageService.showSystemError();
          }
      );
      Log.info("Action: 查詢郵遞區號 END");
    });
  },
};

export default AjaxService;