package main.java.com.BGV.SessionManager.Impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import javax.servlet.http.Cookie;

import main.java.com.BGV.DAO.EmployeeCommonDAO;
import main.java.com.BGV.DAO.Impl.EmployeeDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;
import main.java.com.BGV.SessionManager.CommonSession;

public class SessionManager extends CommonService implements CommonSession
{
	EmployeeCommonDAO employeeDAO = EmployeeDAO.getinstance();
	
	public static CommonSession getInstance()
	{
		return new SessionManager();
	}

	@Override
	public boolean checkSession(HttpServletRequest request) throws NullPointerException
	{
		boolean isValidSession =false;
		boolean isCookieAvailable =false;
		Cookie[] cookies = request.getCookies();
		try
		{
			if(cookies!=null)
			{
				for(Cookie cookie : cookies)
				{
					if(cookie.getName().equals("EmployeeID"))
					{
						isCookieAvailable =true;
						break;
					}
				}
				if(isCookieAvailable)
				{
					isValidSession = true;
				}
				else
				{
					isValidSession = false;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			isValidSession = false;
		}
		return isValidSession;
	}

	@Override
	public ViewObject createSession(HttpServletResponse response, EmployeeLogin employee) 
	{
		ViewObject view = new ViewObject();
		Employee emp = new Employee();
		try
		{
			//setting cookie
			Cookie cookie = new Cookie("EmployeeID", employee.getEmp_ID());
			cookie.setPath("/");
			cookie.setMaxAge(600);
			response.addCookie(cookie);
			
			//Getting employee details
			emp.setEmpID(employee.getEmp_ID());
			view = employeeDAO.getEmployeeDetails(emp);
			List returnList = view.getReturnObject();
			if(returnList.size() !=0)
			emp = (Employee) returnList.get(0);
			
			//setting cookie
			String empName = emp.getEmpName().indexOf(" ") > -1 ? emp.getEmpName().substring(0, emp.getEmpName().indexOf(" ")) : emp.getEmpName();
			cookie = new Cookie("EMPNAME",empName);
			cookie.setPath("/");
			cookie.setMaxAge(600);
			response.addCookie(cookie);
			
			view.setResponse(response);
			return view;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;	
		}
		
	}

	@Override
	public ViewObject destroySession(HttpServletResponse response,HttpServletRequest request) 
	{
		
		Cookie[] cookies = request.getCookies();
		ViewObject view = new ViewObject();
		String userName ="";
		for(Cookie cookie : cookies)
		{
			if(cookie.getName().equals("EMPNAME"))
			userName = cookie.getValue().toUpperCase();
				
			cookie.setValue(null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		view.setReturnMsg(userName+getErrorMsg(DataConstants.CXLGOUT007).getDescription());
		view.setResponse(response);
		return view;
	}
	
}
