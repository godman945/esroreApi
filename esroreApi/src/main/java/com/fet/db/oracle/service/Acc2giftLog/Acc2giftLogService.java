package com.fet.db.oracle.service.Acc2giftLog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.db.oracle.dao.Acc2giftLog.Acc2giftLogDAO;
import com.fet.db.oracle.pojo.Acc2giftLog;
import com.fet.db.oracle.service.BaseService;

@Service
public class Acc2giftLogService extends BaseService <Acc2giftLog,String> {

	@Autowired
	Acc2giftLogDAO acc2giftLogDAO;
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void alex() {
		
//		 List resultList = entityManager.createNativeQuery("select * from ACC2GIFT_LOG").getResultList();
//		 System.out.println("長度:"+resultList.size());
//		
//		 System.out.println(resultList.get(0));
//		 
//		 for (int i=0; i<resultList.size(); i++) {
//			 Object[] objArray = (Object[]) resultList.get(i);
//			 System.out.println(objArray[0]);
//		 }
		 
		 
//		 8ac4c2e61faf0a8a011fcf89f6850de6
		 
//		Acc2giftLog a = entityManager.find(Acc2giftLog.class, "8ac4c2e61faf0a8a011fcf89f6850de6");
//		 
//		 System.out.println(a == null);
		 
		 
		 
		 
//		TypedQuery<Acc2giftLog> query = entityManager.createNamedQuery("ACC2GIFT_LOG", Acc2giftLog.class);
//		System.out.println(query.getResultList().size());
//		System.out.println("ssss");
//		System.out.println(acc2giftLogDAO.findAll().size());
	}

	
	
	
}
