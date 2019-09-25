package main.java.com.BGV.DAO.Impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.com.BGV.DAO.CommonDAO;
import main.java.com.BGV.DAO.LoginCommonDAO;
import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ViewObject;

public class LoginDAO implements LoginCommonDAO
{
	SessionFactory sessionFactory = LoginCommonDAO.sessionFactory;
	public static LoginCommonDAO getinstance()
	{
		return new LoginDAO();
	}
	
	@Override
	public ViewObject loginDAO(EmployeeLogin employee) 
	{
		Session session = sessionFactory.openSession();
		try 
		{
			//Find the employee password based on employee id 
			String hql = "from EmployeeLogin e where e.emp_ID= :employee_id";
			Query query = session.createQuery(hql);
			query.setParameter("employee_id", employee.getEmp_ID());
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
