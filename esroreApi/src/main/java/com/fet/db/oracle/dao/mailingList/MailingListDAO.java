package com.fet.db.oracle.dao.mailingList;

import org.springframework.stereotype.Repository;

import com.fet.db.oracle.dao.base.BaseDAO;
import com.fet.db.oracle.pojo.MailingList;

@Repository
public class MailingListDAO extends BaseDAO<MailingList, String> implements IMailingListDAO {

}
