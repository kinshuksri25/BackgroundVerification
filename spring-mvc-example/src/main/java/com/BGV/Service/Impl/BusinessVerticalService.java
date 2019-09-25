package main.java.com.BGV.Service.Impl;

import main.java.com.BGV.DAO.BusinessVerticalCommonDAO;
import main.java.com.BGV.DAO.Impl.BusinessVerticalDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.ErrorMsgs;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.BusinessVerticalCommonService;
import main.java.com.BGV.Service.CommonService;

public class BusinessVerticalService extends CommonService implements BusinessVerticalCommonService 
{
	BusinessVerticalCommonDAO businessVerticalDAO = BusinessVerticalDAO.getInstance();
	
	public static BusinessVerticalCommonService getInstance()
	{
		return new BusinessVerticalService();
	}

	@Override
	public ViewObject getBusinessVertical()
	{
		ViewObject view = new ViewObject();
		try
		{
			view = businessVerticalDAO.fetchBusinessVertical();
			if(view.getReturnObject() !=null)
			{
				view.setReturnMsg(jsonConverter(view.getReturnObject()));
			}
			else
			{
				ErrorMsgs error = (getErrorMsg(DataConstants.CXDBFTH011));
				view.setReturnMsg(error.getDescription());
			}
			return view;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
