package main.java.com.BGV.DAO;

import org.hibernate.SessionFactory;

import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewObject;

public interface CreateCommonDAO extends CommonDAO
{
	public static SessionFactory sessionFactory = CommonDAO.sessionFactory;
	
	public ViewObject CreateUserDAO(Employee employee);
	public ViewObject CreateLoginDetailsDAO(EmployeeLogin loginDetails);
}
