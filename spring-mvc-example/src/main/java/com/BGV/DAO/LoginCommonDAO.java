package main.java.com.BGV.DAO;

import org.hibernate.SessionFactory;

import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ViewObject;

public interface LoginCommonDAO extends CommonDAO
{
	public static SessionFactory sessionFactory = CommonDAO.sessionFactory;
	public ViewObject loginDAO(EmployeeLogin employee);
}
