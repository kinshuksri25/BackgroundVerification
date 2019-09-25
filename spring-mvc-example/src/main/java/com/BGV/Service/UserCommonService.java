package main.java.com.BGV.Service;

import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.ViewObject;

public interface UserCommonService 
{
	public ViewObject createUser(String employeeJson);
	public ViewObject getEmployeeDataAll();
	public ViewObject getUserData(String employeeID);
}
