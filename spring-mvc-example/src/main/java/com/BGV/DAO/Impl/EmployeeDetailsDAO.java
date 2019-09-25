package main.java.com.BGV.DAO.Impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.com.BGV.DAO.EmployeeDetailsCommonDAO;
import main.java.com.BGV.Model.EmployeeDetails;
import main.java.com.BGV.Model.ViewObject;

public class EmployeeDetailsDAO implements EmployeeDetailsCommonDAO
{
	SessionFactory sessionFactory = EmployeeDetailsCommonDAO.sessionFactory;
	
	public static EmployeeDetailsCommonDAO getInstance()
	{
		return new EmployeeDetailsDAO();
	}
	
	@Override
	public ViewObject getEmployeeDetails(EmployeeDetails employeeDetails) {
		
		ViewObject view = new ViewObject();
		Session session = sessionFactory.openSession();
		try
		{
			String hql = "from EmployeeDetails e where e.employeeID= :employeeid";
			Query query = session.createQuery(hql);
			query.setParameter("employeeid", employeeDetails.getEmployeeID());
			List results = query.getResultList();
			view.setReturnObject(results);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view= null;
		}
		return view;
	}

}
