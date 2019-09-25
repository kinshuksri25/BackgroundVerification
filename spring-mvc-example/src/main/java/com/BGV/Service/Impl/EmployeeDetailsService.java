package main.java.com.BGV.Service.Impl;

import main.java.com.BGV.DAO.EmployeeDetailsCommonDAO;
import main.java.com.BGV.DAO.Impl.EmployeeDetailsDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.EmployeeDetails;
import main.java.com.BGV.Model.ErrorMsgs;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;
import main.java.com.BGV.Service.EmployeeDetailsCommonService;

public class EmployeeDetailsService extends CommonService implements EmployeeDetailsCommonService
{
	
	EmployeeDetailsCommonDAO employeeDetailsDAO = EmployeeDetailsDAO.getInstance();
	
	public static EmployeeDetailsCommonService getInstance()
	{
		return new EmployeeDetailsService();
	}
	
	
	@Override
	public ViewObject getEmployeeDetails(EmployeeDetails employeeDetails) {
		
		ViewObject view = new ViewObject();
		ErrorMsgs err = new ErrorMsgs();
		try
		{
			if(employeeDetails!=null)
			{
				view = employeeDetailsDAO.getEmployeeDetails(employeeDetails);
				if(view.getReturnObject().size()!=0)
				{
					view.setReturnMsg(jsonConverter(view.getReturnObject()));
				}
				else
				{
					throw new Exception();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			err = getErrorMsg(DataConstants.CXDBFTH011);
			//need to check in controller 
			view.setReturnMsg(err.getDescription());
		}
		return view;
	}

}
