package main.java.com.BGV.DAO.Impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import main.java.com.BGV.DAO.LoginCommonDAO;
import main.java.com.BGV.DAO.RequestCommonDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;

public class RequestDAO extends CommonService implements RequestCommonDAO
{
	SessionFactory sessionFactory = LoginCommonDAO.sessionFactory;
	
	public static RequestCommonDAO getInstance()
	{
		return new RequestDAO();
	}

	@Override
	public ViewObject createRequestDAO(Request request) 
	{
		Session session =  sessionFactory.openSession();
		Transaction tnx = session.getTransaction();
		ViewObject view = new ViewObject();
		
		try
		{
			tnx.begin();
			session.saveOrUpdate(request);
			view.setReturnMsg(getErrorMsg(DataConstants.CXREQCR014).getDescription());
			tnx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(getErrorMsg(DataConstants.CXREQCR015).getDescription());
			tnx.rollback();
		}
		finally
		{
			session.close();
		}
		return view;
	}

	@Override
	public ViewObject searchRequestDAO(Request request) {
		
		ViewObject view = new ViewObject();
		Session session = sessionFactory.openSession();
		try
		{
			String hql = "from Request e where e.employeeDetails= :employeeDetails";
			Query query = session.createQuery(hql);
			query.setParameter("employeeDetails", request.getEmployeeDetails());
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
	
	@Override
	public ViewObject searchRequestReviewerDAO(Request request)
	{
		ViewObject view = new ViewObject();
		Session session = sessionFactory.openSession();
		try
		{
			String hql = "from Request e where e.assignedReviewerID= :assignedReviewerID";
			Query query = session.createQuery(hql);
			query.setParameter("assignedReviewerID", request.getAssignedReviewerID());
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
	
	@Override
	public ViewObject searchRequestCreatorDAO(Request request)
	{
		ViewObject view = new ViewObject();
		Session session = sessionFactory.openSession();
		try
		{
			String hql = "from Request e where e.requestCreatorID= :requestCreatorID";
			Query query = session.createQuery(hql);
			query.setParameter("requestCreatorID", request.getRequestCreatorID());
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
	
	@Override
	public ViewObject searchRequestIDDAO(Request request)
	{
		ViewObject view = new ViewObject();
		Session session = sessionFactory.openSession();
		try
		{
			String hql = "from Request e where e.requestID= :requestID";
			Query query = session.createQuery(hql);
			query.setParameter("requestID", request.getRequestID());
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

	@Override
	public ViewObject updateRequestStatus(Request request) {
		
		Session session =  sessionFactory.openSession();
		Transaction tnx = session.getTransaction();
		ViewObject view = new ViewObject();
		try
		{
			tnx.begin();
			view = searchRequestIDDAO(request);
			Request tempRequest = (Request) view.getReturnObject().get(0);
			tempRequest.setStatus(request.getStatus());
			tempRequest.setComments(request.getComments());
			
			session.saveOrUpdate(tempRequest);
			view.setReturnMsg(getErrorMsg(DataConstants.CXDBUD016).getDescription());
			tnx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(getErrorMsg(DataConstants.CXDBUD017).getDescription());
			tnx.rollback();
		}
		finally
		{
			session.close();
		}
		return view;
	}
	
	
}
