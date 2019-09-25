package main.java.com.BGV.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ViewObject;

public interface CommonSession
{
	public boolean checkSession(HttpServletRequest request);
	public ViewObject createSession(HttpServletResponse response, EmployeeLogin employee);
	public ViewObject destroySession(HttpServletResponse response,HttpServletRequest request);
	
}
