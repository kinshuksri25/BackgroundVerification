package main.java.com.BGV.Service.Impl;

import java.util.List;

import main.java.com.BGV.DAO.RequestCommonDAO;
import main.java.com.BGV.DAO.Impl.RequestDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.EmployeeDetails;
import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;
import main.java.com.BGV.Service.RequestCommonService;

public class RequestService extends CommonService implements RequestCommonService
{
	public RequestCommonDAO requestDAO = RequestDAO.getInstance();
	
	public static RequestCommonService getInstance()
	{
		return new RequestService();
	}

	
	@Override
	public ViewObject searchRequest(String searchRequest, String searchCriteria) {
		Request request = new Request();
		ViewObject view = new ViewObject();
		try
		{
			request = createRequestObject(searchRequest,searchCriteria);
			
			if(request !=null)
			{
				switch(searchCriteria)
				{
					case "EmployeeID":
									view = requestDAO.searchRequestDAO(request);
									break;
					case "ReviewerID":
									view = requestDAO.searchRequestReviewerDAO(request);
									break;
					case "RequestID":
									view = requestDAO.searchRequestIDDAO(request);
									break;
					case "CreatorID": 
									view = requestDAO.searchRequestCreatorDAO(request);
									break;
				}
				if(view !=null && view.getReturnObject().size() !=0)
				{
					view.setReturnMsg(jsonConverter(view.getReturnObject()));
				}
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg("");
		}
		return view;
		
	}
	
	public Request createRequestObject(String searchRequest,String searchCriteria)
	{
		Request request = new Request();
		EmployeeDetails employee = new EmployeeDetails();
		try
		{
			if(searchCriteria != "" && searchRequest != "")
			{
				switch(searchCriteria)
				{
					case "EmployeeID":
						employee.setEmployeeID(searchRequest);
						request.setEmployeeDetails(employee);
						break;
					case "ReviewerID":
						request.setAssignedReviewerID(searchRequest);
						break;
					case "RequestID":
						request.setRequestID(Integer.parseInt(searchRequest));
						break;
					case "CreatorID": 
						request.setRequestCreatorID(searchRequest);
						break;
				}
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request=null;
		}
		return request;
	}


	@Override
	public ViewObject updateRequestStatus(String requestID, String status, String comments) {
		
		ViewObject view = new ViewObject();
		Request request =  new Request();
		try
		{
			if(requestID != null || status !=null)
			{
				request.setRequestID(Integer.parseInt(requestID));
				String Status="";
				switch(status)
				{
					case "approve":	Status = DataConstants.APPROVED;
									break;
					case "reject":	Status =  DataConstants.REJECTED;
									break;
					case "correct":	Status = DataConstants.PENDING_CORRECTION;
									break;
				}
				request.setStatus(Status);
				request.setComments(comments);				
				view= requestDAO.updateRequestStatus(request);
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg("");
		}
		return view;
	}
}
