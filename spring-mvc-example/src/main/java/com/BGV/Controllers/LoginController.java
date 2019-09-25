package main.java.com.BGV.Controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.LoginCommonService;
import main.java.com.BGV.Service.Impl.LoginService;

@Controller
public class LoginController 
{
	ViewObject view = new ViewObject();
	LoginCommonService loginService = LoginService.getInstance();
				
	@RequestMapping(value= {"*"} , method=RequestMethod.GET)
	public ModelAndView viewgenerator(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{	
			view = loginService.accessLogin(request,response);
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("message", view.getReturnMsg());
			modelView.setViewName(view.getViewName());
			return modelView;
		}
		catch(Exception e)
		{
			//change the code here to get error page from service
			e.printStackTrace();
			return new ModelAndView("error.jsp");
		}
	}
	
	@RequestMapping(value = "/login_user" , method = RequestMethod.POST)
	public ModelAndView loginUser(EmployeeLogin employee, HttpServletResponse response , HttpServletRequest request)
	{
		try
		{
			ViewObject view = new ViewObject();
			ModelAndView modelView = new ModelAndView();
			view = loginService.userLogin(response, employee);
			response = view.getResponse();
			modelView.setViewName(view.getViewName());
			modelView.addObject("message", view.getReturnMsg());
			return modelView;
		}
		catch(Exception e)
		{
			//change the code here to get error page from service
			e.printStackTrace();
			return new ModelAndView("error.jsp");
		}
	}
	@RequestMapping(value = "/changePassword" , method = RequestMethod.POST)
	public ModelAndView changePassword(EmployeeLogin employee)
	{
		ViewObject view = new ViewObject();
		ModelAndView modelView = new ModelAndView();
		try
		{
			view = loginService.changePassword(employee);
			modelView.setViewName(view.getViewName());
			modelView.addObject("message", view.getReturnMsg());
		}
		catch(Exception e)
		{
			//change the code here to get error page from service
			e.printStackTrace();
			modelView.setViewName("error.jsp");
			modelView.addObject("message",view.getReturnMsg());
		}
		return modelView;
	}
}
