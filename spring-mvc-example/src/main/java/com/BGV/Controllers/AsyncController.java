package main.java.com.BGV.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import main.java.com.BGV.IO.IOCommonService;
import main.java.com.BGV.IO.Impl.IOService;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.EmployeeDetails;
import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.BusinessVerticalCommonService;
import main.java.com.BGV.Service.EmployeeDetailsCommonService;
import main.java.com.BGV.Service.LoginCommonService;
import main.java.com.BGV.Service.MenuCommonService;
import main.java.com.BGV.Service.RequestCommonService;
import main.java.com.BGV.Service.UserCommonService;
import main.java.com.BGV.Service.Impl.BusinessVerticalService;
import main.java.com.BGV.Service.Impl.EmployeeDetailsService;
import main.java.com.BGV.Service.Impl.LoginService;
import main.java.com.BGV.Service.Impl.MenuService;
import main.java.com.BGV.Service.Impl.RequestService;
import main.java.com.BGV.Service.Impl.UserService;
import main.java.com.BGV.SessionManager.CommonSession;
import main.java.com.BGV.SessionManager.Impl.SessionManager;

@Controller
public class AsyncController 
{
	MenuCommonService menuService =  MenuService.getInstance();
	UserCommonService userService = UserService.getInstance();
	BusinessVerticalCommonService businessVertical = BusinessVerticalService.getInstance();
	EmployeeDetailsCommonService employeeDetailsService = EmployeeDetailsService.getInstance();
	RequestCommonService requestService = RequestService.getInstance();
	IOCommonService ioService = IOService.getInstance();
	CommonSession sessionManager =  SessionManager.getInstance();
	
	
	@RequestMapping(value = "/async_menu_details" , method = RequestMethod.GET)
	public @ResponseBody String getAsyncMenuTabs(@RequestParam("employee_ID") String employee_id, HttpServletRequest request)
	{
		ViewObject view = new ViewObject();
		Employee employee = new Employee();
		employee.setEmpID(employee_id);
		try 
		{
			if(hasSession(request))
				view = menuService.getAsyncMenuTab(employee);
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(DataConstants.CXDBFTH011);
		}
		return view.getReturnMsg();
	}
	
	@RequestMapping(value = "/asyncCreateUser" , method = RequestMethod.POST)
	public @ResponseBody String asyncCreateUser(@RequestParam("RequestJson")String requestJson, HttpServletRequest request)
	{
		ViewObject view = new ViewObject();
		try
		{
			if(hasSession(request))
				view = userService.createUser(requestJson);
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(DataConstants.CXUSR006);
		}
		return  view.getReturnMsg();
	}
	
	@RequestMapping(value="/asyncBusinessVerticalDetails", method = RequestMethod.POST)
	public @ResponseBody String asyncBusinessVerticalDetails(HttpServletRequest request)
	{
		ViewObject view = new ViewObject();
		try
		{
			if(hasSession(request))
				view = businessVertical.getBusinessVertical();
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(DataConstants.CXDBFTH011);
		}
		return view.getReturnMsg();
	}
	
	@RequestMapping(value="/asyncEmployeeDetails", method= RequestMethod.GET)
	public @ResponseBody String asyncEmployeeDetails(@RequestParam("requestJson")String requestJson, HttpServletRequest request)
	{
		ViewObject view = new ViewObject();
		EmployeeDetails employeeDetails = new EmployeeDetails();
		try
		{
			if(hasSession(request))
				if(!requestJson.isEmpty())
				{
					employeeDetails.setEmployeeID(requestJson);
					view = employeeDetailsService.getEmployeeDetails(employeeDetails);
				}
			else
				throw new Exception();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(DataConstants.CXDBFTH011);
		}
		return view.getReturnMsg();
	}
	
	@RequestMapping(value="/asyncEmployeeData", method=RequestMethod.GET)
	public @ResponseBody String asyncEmployeeData(HttpServletRequest request)
	{
		ViewObject view = new ViewObject();
		try
		{
			if(hasSession(request))
			{
				view = userService.getEmployeeDataAll();
				if(view.getReturnObject()==null)
				{
					throw new Exception();
				}
			}
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(DataConstants.CXDBFTH011);
		}
		return view.getReturnMsg();
	}
	
	@RequestMapping(value="/asyncSearchRequest", method=RequestMethod.GET)
	public @ResponseBody String asyncSearchRequest(@RequestParam("searchFeildValue")String searchFeildValue ,@RequestParam("criteria")String criteria, HttpServletRequest request)
	{
		ViewObject view = new ViewObject();
		try
		{
			if(hasSession(request))
				view = requestService.searchRequest(searchFeildValue, criteria);
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(DataConstants.CXDBFTH011);
		}
		return view.getReturnMsg();
	}
	
	@RequestMapping(value="/asyncGetDownloadLocation", method=RequestMethod.GET)
	public @ResponseBody String asyncGetDownloadLocation(@RequestParam("fileLocation")String fileLocation, HttpServletRequest request)
	{
		ViewObject view = new ViewObject();
		try
		{
			if(hasSession(request))
				view = ioService.fileList(fileLocation);
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(DataConstants.CXDBFTH011);
		}
		return view.getReturnMsg();
	}
	
	@RequestMapping(value="asyncVerifyRequest", method=RequestMethod.GET)
	public @ResponseBody String asyncVerifyRequest(@RequestParam("status")String status,@RequestParam("requestID")String requestID,@RequestParam("comments")String comments, HttpServletRequest request)
	{
		ViewObject view =  new ViewObject();
		try
		{
			if(hasSession(request))
				view = requestService.updateRequestStatus(requestID, status, comments);
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(DataConstants.CXDBFTH011);
		}
		return view.getReturnMsg();
	}
	
	@RequestMapping(value="asyncUserData", method=RequestMethod.GET)
	public @ResponseBody String asyncUserData(@RequestParam("employeeID")String employeeID, HttpServletRequest request)
	{
		ViewObject view =  new ViewObject();
		try
		{
			if(hasSession(request))
				view = userService.getUserData(employeeID);
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(DataConstants.CXDBFTH011);
		}
		return view.getReturnMsg();
	}
	
	
	public boolean hasSession(HttpServletRequest request)
	{
		return sessionManager.checkSession(request);
	}

}
