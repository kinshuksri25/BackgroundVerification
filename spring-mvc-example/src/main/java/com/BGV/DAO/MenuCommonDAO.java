package main.java.com.BGV.DAO;

import org.hibernate.SessionFactory;

import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.ViewObject;

public interface MenuCommonDAO extends CommonDAO
{
	public static SessionFactory sessionFactory = CommonDAO.sessionFactory;
	public ViewObject getAsyncMenuTab(Employee employee);
}
