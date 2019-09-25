package main.java.com.BGV.DAO.Impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.com.BGV.DAO.CommonDAO;
import main.java.com.BGV.DAO.ErrorMsgsCommonDAO;
import main.java.com.BGV.Model.ViewObject;

public class ErrorMsgsDAO implements ErrorMsgsCommonDAO
{
	SessionFactory sessionFactory = ErrorMsgsCommonDAO.sessionFactory;
	
	public static ErrorMsgsCommonDAO getinstance()
	{
		return new ErrorMsgsDAO();
	}
	
	public ViewObject getErrorMsgs(String errorCode)
	{
		Session session = sessionFactory.openSession();
		try 
		{
			String hql = "from ErrorMsgs error where error.errorCode= :errorCode";
			Query query = session.createQuery(hql);
			query.setParameter("errorCode", errorCode);
			List resultList = query.getResultList();
			
			return new ViewObject("",resultList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			String hql = "from ErrorMsgs error where error.errorCode= :CXGEN004";
			Query query = session.createQuery(hql);
			List resultList = query.getResultList();
			
			return new ViewObject("",resultList);
		}
	}
}
