package com.fet.db.oracle.service.Acc2giftLog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fet.db.oracle.pojo.Acc2giftLog;
import com.fet.db.oracle.service.IBaseService;

public interface IAcc2giftLogService extends IBaseService<Acc2giftLog, String>, JpaRepository<Acc2giftLog, String> {

	public void alex();

}
