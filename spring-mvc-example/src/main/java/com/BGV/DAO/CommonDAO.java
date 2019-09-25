package main.java.com.BGV.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public interface CommonDAO 
{
	public static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

}
