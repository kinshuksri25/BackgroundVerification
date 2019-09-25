package main.java.com.BGV.Service.Impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import main.java.com.BGV.DAO.CommonDAO;
import main.java.com.BGV.DAO.CreateCommonDAO;
import main.java.com.BGV.DAO.ErrorMsgsCommonDAO;
import main.java.com.BGV.DAO.LoginCommonDAO;
import main.java.com.BGV.DAO.ViewCommonDAO;
import main.java.com.BGV.DAO.Impl.CreateDAO;
import main.java.com.BGV.DAO.Impl.ErrorMsgsDAO;
import main.java.com.BGV.DAO.Impl.LoginDAO;
import main.java.com.BGV.DAO.Impl.ViewDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ErrorMsgs;
import main.java.com.BGV.Model.ViewName;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;
import main.java.com.BGV.Service.LoginCommonService;
import main.java.com.BGV.SessionManager.CommonSession;
import main.java.com.BGV.SessionManager.Impl.SessionManager;

public class LoginService extends CommonService implements LoginCommonService 
{
	CommonSession sessionManager = SessionManager.getInstance();
	LoginCommonDAO loginDao = LoginDAO.getinstance();
	CreateCommonDAO createDAO = CreateDAO.getInstance();
	public static LoginCommonService getInstance()
	{
		return new LoginService();
	}
		
	public ViewObject accessLogin(HttpServletRequest request, HttpServletResponse response)
	{
		ViewObject view = new ViewObject();
		ViewName viewName =  new ViewName();
		String url = request.getRequestURI();
		String mapping = url.substring(url.lastIndexOf("/")+1);
		mapping = mapping.toLowerCase();
		Boolean isSessionValid = sessionManager.checkSession(request);
		if(isSessionValid)
		{
			try
			{
				if(mapping.equalsIgnoreCase("logout"))
				{
					view = sessionManager.destroySession(response, request);
				}
				viewName = mapping.equalsIgnoreCase("login")? getView(DataConstants.MENU) : getView(mapping);
				view.setViewName(viewName.getViewname());
			}
			catch(Exception e)
			{
				System.out.println("Invalid Mapping/Incorrect url request!");
				
				viewName = getView(DataConstants.MENU);
				view.setViewName(viewName.getViewname());
			}	
		}
		else
		{
			viewName = getView(DataConstants.LOGIN);
			view.setViewName(viewName.getViewname());
		}
		return view;
	}
	
	public ViewObject userLogin(HttpServletResponse response, EmployeeLogin emp)
	{
		ViewObject view = new ViewObject();
		ViewName viewName =  new ViewName();
		ErrorMsgs msgs = new ErrorMsgs();
		try 
		{
			if(emp.getEmp_ID() != null || emp.getEmp_ID() != "" || emp.getPassword() != null || emp.getPassword() != "")
			{
				view = loginDao.loginDAO(emp);
				if(view !=null)
				{
					List returnList = view.getReturnObject();
					EmployeeLogin employee = new EmployeeLogin();
					if(returnList!=null && returnList.size() !=0)
					{
						employee = (EmployeeLogin) returnList.get(0);
						boolean isValidPassword = employee.getPassword().equals(emp.getPassword()) ? true : false;
						if(isValidPassword)
						{
							//creating session
							view = sessionManager.createSession(response, emp);
							viewName = getView(DataConstants.MENU);
							view.setViewName(viewName.getViewname());
						}
						else
						{
							msgs = getErrorMsg(DataConstants.CXLGPS002);
							view.setReturnMsg(msgs.getDescription());
							viewName = getView(DataConstants.LOGIN);
							view.setViewName(viewName.getViewname());
						}
				
					}
					else
					{
						msgs = getErrorMsg(DataConstants.CXLGID001);
						view.setReturnMsg(msgs.getDescription());
						viewName = getView(DataConstants.LOGIN);
						view.setViewName(viewName.getViewname());
					}
				}

			}
			else
			{
				msgs = getErrorMsg(DataConstants.CXLGINC003);
				view.setReturnMsg(msgs.getDescription());
				viewName = getView(DataConstants.LOGIN);
				view.setViewName(viewName.getViewname());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			msgs = getErrorMsg(DataConstants.CXGEN004);
			view.setReturnMsg(msgs.getDescription());
			viewName = getView(DataConstants.ERROR);
			view.setViewName(viewName.getViewname());
		}
		return view;
	}

	@Override
	public ViewObject changePassword(EmployeeLogin loginDetails) {
		ViewObject view = new ViewObject();
		ViewName viewName =  new ViewName();
		ErrorMsgs msgs = new ErrorMsgs();
		try
		{
			if(loginDetails != null)
			{
				view = createDAO.CreateLoginDetailsDAO(loginDetails);
				viewName = getView(DataConstants.USER_PROFILE);
				view.setViewName(viewName.getViewname());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			msgs = getErrorMsg(DataConstants.CXGEN004);
			view.setReturnMsg(msgs.errorMessages);
			viewName = getView(DataConstants.ERROR);
			view.setViewName(viewName.getViewname());
		}
		return view;
	}
	
	
	
}
