package main.java.com.BGV.Service.Impl;

import java.util.List;

import main.java.com.BGV.DAO.EmployeeCommonDAO;
import main.java.com.BGV.DAO.ErrorMsgsCommonDAO;
import main.java.com.BGV.DAO.MenuCommonDAO;
import main.java.com.BGV.DAO.ViewCommonDAO;
import main.java.com.BGV.DAO.Impl.EmployeeDAO;
import main.java.com.BGV.DAO.Impl.ErrorMsgsDAO;
import main.java.com.BGV.DAO.Impl.MenuDAO;
import main.java.com.BGV.DAO.Impl.ViewDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ErrorMsgs;
import main.java.com.BGV.Model.ViewName;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;
import main.java.com.BGV.Service.MenuCommonService;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MenuService extends CommonService implements MenuCommonService 
{
	
	EmployeeCommonDAO employeeDAO = EmployeeDAO.getinstance();
	MenuCommonDAO menuDAO = MenuDAO.getinstance();
	
	public static MenuCommonService getInstance()
	{
		return new MenuService();
	}
	
	@Override
	public ViewObject getAsyncMenuTab(Employee employee) 
	{
		ViewObject view = new ViewObject();
		ViewName viewName =  new ViewName();
		ErrorMsgs msgs = new ErrorMsgs();
		Employee tempEmployee = new Employee();
		try
		{
			view = employeeDAO.getEmployeeDetails(employee);
			List returnList = view.getReturnObject();
			if(returnList!=null && returnList.size() !=0)
			{
				tempEmployee = (Employee) returnList.get(0);
				boolean isAdmin = tempEmployee.getIsAdmin() == DataConstants.ADMIN? true : false;
				
				tempEmployee.setIsadmin(isAdmin);
				
				view = menuDAO.getAsyncMenuTab(tempEmployee);
				view.setReturnMsg(jsonConverter(view.getReturnObject()));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			msgs = getErrorMsg(DataConstants.CXGEN004);
			view.setReturnMsg(msgs.errorMessages);
			viewName = getView(DataConstants.ERROR);
			view.setViewName(viewName.viewname);
		}
		return view;
	}
	


}
