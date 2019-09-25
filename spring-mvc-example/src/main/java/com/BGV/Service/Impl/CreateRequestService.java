package main.java.com.BGV.Service.Impl;

import main.java.com.BGV.DAO.CreateCommonDAO;
import main.java.com.BGV.DAO.RequestCommonDAO;
import main.java.com.BGV.DAO.Impl.CreateDAO;
import main.java.com.BGV.DAO.Impl.RequestDAO;
import main.java.com.BGV.IO.IOCommonService;
import main.java.com.BGV.IO.Impl.IOService;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.ErrorMsgs;
import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewName;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;
import main.java.com.BGV.Service.CreateRequestCommonService;

public class CreateRequestService extends CommonService implements CreateRequestCommonService 
{
	RequestCommonDAO requestDAO = RequestDAO.getInstance();
	IOCommonService ioService = IOService.getInstance();
	public static CreateRequestCommonService getInstance()
	{
		return new CreateRequestService();
	}

	@Override
	public ViewObject createBGVRequest(Request request) 
	{
		ViewObject view = new ViewObject();
		ErrorMsgs msgs = new ErrorMsgs();
		ViewName viewName = new ViewName();
		try
		{
			if(request.getStatus()==null)
			{
				view = requestDAO.searchRequestDAO(request);
				msgs = getErrorMsg(DataConstants.CXDSKSV010);
				if(view.getReturnObject().size()==0)
				{
					view = ioService.copyFile(request);
					if(view.getReturnMsg()!=null && !view.getReturnMsg().equals(msgs.getDescription()))
					{
						request.setFileLocation(view.getReturnMsg());
						request.setStatus(DataConstants.PENDING_APPROVAL);
						view = requestDAO.createRequestDAO(request);
						
						msgs = getErrorMsg(DataConstants.CXREQCR015);
						if(view.getReturnMsg().equals(msgs.getDescription()))
						{
							//change required
							viewName = getView(DataConstants.CREATE_REQUEST);
							view.setViewName(viewName.getViewname());
						}
						else
						{   
							//change required
							viewName = getView(DataConstants.INBOX);
							view.setViewName(viewName.getViewname());
						}
					}
				}
				else
				{
					//change required
					msgs = getErrorMsg(DataConstants.CXREQCR013);
					view.setReturnMsg(msgs.getDescription());
					viewName = getView(DataConstants.CREATE_REQUEST);
					view.setViewName(viewName.getViewname());
				}

			}
			else if(request.getStatus().equals(DataConstants.PENDING_CORRECTION))
			{
				request.setStatus(DataConstants.PENDING_APPROVAL);
				view = requestDAO.createRequestDAO(request);
				msgs = getErrorMsg(DataConstants.CXREQCR015);
				if(view.getReturnMsg().equals(msgs.getDescription()))
				{
						//change required
					viewName = getView(DataConstants.CREATE_REQUEST);
					view.setViewName(viewName.getViewname());
				}
				else
				{   
						//change required
					viewName = getView(DataConstants.INBOX);
					view.setViewName(viewName.getViewname());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			msgs = getErrorMsg(DataConstants.CXGEN004);
			view.setReturnMsg(msgs.getErrorMessages());
			viewName= getView(DataConstants.ERROR);
			view.setViewName(viewName.getViewname());
		}
		
		return view;
	}
	
	
}
