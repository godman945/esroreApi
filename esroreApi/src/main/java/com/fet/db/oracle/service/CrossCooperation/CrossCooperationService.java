package com.fet.db.oracle.service.CrossCooperation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.db.oracle.dao.CrossCooperation.CrossCooperationDAO;
import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.service.BaseService;

@Service
public class CrossCooperationService extends BaseService <CrossCooperation,String> implements ICrossCooperationService {

	@Autowired
	CrossCooperationDAO crossCooperationDAO;
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void alex() {
		
		 List resultList = entityManager.createNativeQuery("select * from CROSS_COOPERATION").getResultList();
		 System.out.println("長度:"+resultList.size());
		 
		 
		 Query query = entityManager.createQuery("  from CrossCooperation ",CrossCooperation.class);
		
		 List<CrossCooperation> crossCooperationList = query.getResultList();
		 
		 for (CrossCooperation crossCooperation : crossCooperationList) {
			 System.out.println(crossCooperation.getOrderNo());
			 System.out.println(crossCooperation.getCreateTime());
		}
		 
		 
//		 entityManager.getDelegate()
		 
		 
//		 HibernateEntityManagerFactory entityManagerFactoryImpl = (HibernateEntityManagerFactory) entityManager.getDelegate();
//		 SessionFactory sessionFactory = entityManagerFactoryImpl.getSessionFactory();
		 
		 
		 
		 
//		 Session session = entityManager.unwrap(Session.class);
		 
//		 System.out.println(session);
//		 
//		 SessionFactory sessionFactory = session.getSessionFactory();
//		 
//		 
//		System.out.println( sessionFactory.getCurrentSession());
//		 
//		 System.out.println( sessionFactory.getCurrentSession().createNativeQuery("select * from CROSS_COOPERATION").getResultList());
		 
		 
//		 EntityManagerImpl(entityManager.getDelegate())
//		 org.hibernate.Session session = ((org.hibernate.ejb.EntityManagerImpl) em.getDelegate()).getSession(); 
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
