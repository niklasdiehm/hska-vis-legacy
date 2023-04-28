package com.hka.vslab.admin.model.database.dataAccessObjects;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hka.vslab.admin.model.database.GenericHibernateDAO;
import com.hka.vslab.admin.model.database.dataobjects.User;
import com.hka.vslab.admin.model.sessionFactory.util.HibernateUtil;

public class UserDAO extends GenericHibernateDAO<User, Integer> {

	public User getUserByUsername(String name) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try
		{
			User user = null;
			session.beginTransaction();
            Criteria crit = session.createCriteria(User.class);
            crit.add(Restrictions.eq("username",name));
            List<User> resultList = crit.list();
            if (resultList.size() > 0) {
            	user = (User) crit.list().get(0);
            }
            session.getTransaction().commit();
            return user;
		}
		catch (HibernateException e)
		{
			System.out.println("Hibernate Exception" + e.getMessage());
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}



}
