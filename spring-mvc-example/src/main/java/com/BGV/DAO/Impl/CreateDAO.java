package main.java.com.BGV.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import main.java.com.BGV.DAO.CreateCommonDAO;
import main.java.com.BGV.DAO.LoginCommonDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;

public class CreateDAO extends CommonService implements CreateCommonDAO
{
	SessionFactory sessionFactory = LoginCommonDAO.sessionFactory;
	
	public static CreateCommonDAO getInstance()
	{
		return new CreateDAO();
	}

	@Override
	public ViewObject CreateUserDAO(Employee employee) {
		
		ViewObject view = new ViewObject();
		Session session = sessionFactory.openSession();
		Transaction tnx = session.beginTransaction();
		try {
			session.saveOrUpdate(employee);
			view.setReturnMsg(getErrorMsg(DataConstants.CXUSR005).getDescription());
			tnx.commit();
			return  view;
		}
		catch(Exception e)
		{
			tnx.rollback();
			e.printStackTrace();
			view.setReturnMsg(getErrorMsg(DataConstants.CXUSR006).getDescription());
			return view;	
		}
		finally
		{  
	           session.close();
		}
	}

	@Override
	public ViewObject CreateLoginDetailsDAO(EmployeeLogin loginDetails) {
		
		ViewObject view = new ViewObject();
		Session session =  sessionFactory.openSession();
		Transaction tnx = session.getTransaction();
		try {
			tnx.begin();
			session.saveOrUpdate(loginDetails);
			view.setReturnMsg(getErrorMsg(DataConstants.CXLGCR018).getDescription());
			tnx.commit();
			return  view;
		}
		catch(Exception e)
		{
			tnx.rollback();
			e.printStackTrace();
			view.setReturnMsg(getErrorMsg(DataConstants.CXLGCR019).getDescription());
			return view;	
		}
		finally
		{
	           session.close();
		}
	}
	
}
