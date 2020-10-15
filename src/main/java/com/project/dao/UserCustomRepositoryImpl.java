package com.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

//@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Object[]> findUsersList(String code, String status, String name) {
		List<Object[]> ls = null;
		try {
			String sql = "select u.USER_ID,u.USER_NAME,u.USER_CODE,u.STATUS,to_char(u.CREATED_DATE,'dd-mm-yyyy'),to_char(u.LAST_LOGIN_TIME,'dd-mm-yyyy HH24:mi:ss') from users u where 1=1";
			if (code != null && code.trim().length() > 0)
				sql += " and (lower(u.user_code) like lower(:code)||'%' or lower(u.user_name) like lower(:code)||'%')";
			if (status != null && status.trim().length() > 0)
				sql += " and u.status='" + status.trim() + "'";
			Query qry = entityManager.createNativeQuery(sql);
			if (code != null && code.trim().length() > 0)
				qry.setParameter("code", code.trim());
			ls = qry.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

}
