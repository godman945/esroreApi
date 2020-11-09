package com.fet.db.oracle.service.CrossCooperation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.service.IBaseService;
//, JpaRepository<CrossCooperation, String>
@Service
public interface ICrossCooperationService extends IBaseService<CrossCooperation, String> {

	public void alex() throws Exception;

}
