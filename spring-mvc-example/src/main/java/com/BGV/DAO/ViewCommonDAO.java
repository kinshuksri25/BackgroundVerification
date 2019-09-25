package main.java.com.BGV.DAO;

import org.hibernate.SessionFactory;

import main.java.com.BGV.Model.ViewObject;

public interface ViewCommonDAO extends CommonDAO
{
	public static SessionFactory sessionFactory = CommonDAO.sessionFactory;
	public ViewObject getview(String urlMapping);
}
