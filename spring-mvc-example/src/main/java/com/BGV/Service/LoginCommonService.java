package main.java.com.BGV.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ViewObject;

public interface LoginCommonService 
{
	public ViewObject  accessLogin(HttpServletRequest request , HttpServletResponse response);
	
	public ViewObject userLogin(HttpServletResponse response , EmployeeLogin employee);
	
	public ViewObject changePassword(EmployeeLogin loginDetails);

}
