package main.java.com.BGV.DAO.Impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.com.BGV.DAO.EmployeeCommonDAO;
import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ViewObject;

public class EmployeeDAO implements EmployeeCommonDAO
{
	SessionFactory sessionFactory = EmployeeCommonDAO.sessionFactory;
	public static EmployeeCommonDAO getinstance()
	{
		return new EmployeeDAO();
	}
	@Override
	public ViewObject getEmployeeDetails(Employee employee) {
		Session session = sessionFactory.openSession();
		try 
		{
			//Find the employee password based on employee id 
			String hql = "from Employee e where e.empID= :employee_id";
			Query query = session.createQuery(hql);
			query.setParameter("employee_id", employee.getEmpID());
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
	
	@Override
	public ViewObject getEmployeeDetailsAll() {
		Session session = sessionFactory.openSession();
		try 
		{
			//Find the employee password based on employee id 
			String hql = "from Employee";
			Query query = session.createQuery(hql);
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
