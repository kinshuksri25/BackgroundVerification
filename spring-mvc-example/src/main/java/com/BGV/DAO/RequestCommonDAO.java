package main.java.com.BGV.DAO;

import org.hibernate.SessionFactory;

import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewObject;

public interface RequestCommonDAO extends CommonDAO
{
	public static SessionFactory sessionFactory = CommonDAO.sessionFactory; 
	
	public ViewObject createRequestDAO(Request request);
	public ViewObject searchRequestDAO(Request request);
	public ViewObject searchRequestReviewerDAO(Request request);
	public ViewObject searchRequestIDDAO(Request request);
	public ViewObject updateRequestStatus(Request request);
	public ViewObject searchRequestCreatorDAO(Request request);
}
