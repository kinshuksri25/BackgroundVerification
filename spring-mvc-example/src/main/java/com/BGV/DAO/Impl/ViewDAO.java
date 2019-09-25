package main.java.com.BGV.DAO.Impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.com.BGV.DAO.CommonDAO;
import main.java.com.BGV.DAO.ViewCommonDAO;
import main.java.com.BGV.Model.ViewObject;

public class ViewDAO implements ViewCommonDAO
{
	public static SessionFactory sessionFactory = ViewCommonDAO.sessionFactory;
	
	public static ViewCommonDAO getinstance()
	{
		return new ViewDAO();
	}
	
	@Override
	public ViewObject getview(String urlMapping) {
		
		Session session = sessionFactory.openSession();
		try 
		{
			String hql = "from ViewName V where V.urlMapping= :urlMapping";
			Query query = session.createQuery(hql);
			query.setParameter("urlMapping", urlMapping);
			List resultList = query.getResultList();
			
			return new ViewObject("",resultList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String hql = "from ViewName V where V.urlMapping= :error";
			Query query = session.createQuery(hql);
			List resultList = query.getResultList();
			
			return new ViewObject("",resultList);
		}
	}
	
	
}
