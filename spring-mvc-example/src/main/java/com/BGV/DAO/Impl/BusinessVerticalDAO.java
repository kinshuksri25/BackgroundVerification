package main.java.com.BGV.DAO.Impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.com.BGV.DAO.BusinessVerticalCommonDAO;
import main.java.com.BGV.Model.ViewObject;

public class BusinessVerticalDAO implements BusinessVerticalCommonDAO
{
	
	SessionFactory sessionFactory = BusinessVerticalCommonDAO.sessionFactory;

	public static BusinessVerticalCommonDAO getInstance()
	{
		return new BusinessVerticalDAO();
	}
	
	@Override
	public ViewObject fetchBusinessVertical() 
	{
		Session session = sessionFactory.openSession();
		try
		{	
			String hql = "from BusinessVertical";
			Query query = session.createQuery(hql);
			List<Object> resultList = query.getResultList();
			return new ViewObject("",resultList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
