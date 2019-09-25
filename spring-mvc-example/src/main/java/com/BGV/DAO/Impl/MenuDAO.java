package main.java.com.BGV.DAO.Impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.com.BGV.DAO.MenuCommonDAO;
import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.ViewObject;

public class MenuDAO implements MenuCommonDAO
{
	SessionFactory sessionFactory = MenuCommonDAO.sessionFactory;
	
	public static MenuCommonDAO getinstance()
	{
		return new MenuDAO();
	}

	@Override
	public ViewObject getAsyncMenuTab(Employee employee) {
		Session session = sessionFactory.openSession();
		try 
		{
			 
			String hql = employee.isIsadmin()? "from MenuTab m where m.adminTab>= :isAdmin" : "from MenuTab m where m.adminTab= :isAdmin";
			
			Query query = session.createQuery(hql);
			query.setParameter("isAdmin", 0);
			List results = query.getResultList();
			//set the view object and return  
			return new ViewObject("",results);	
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;	
		}
		finally
		{
	           session.close();
		}
	}

}
