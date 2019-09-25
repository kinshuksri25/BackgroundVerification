package main.java.com.BGV.Service.Impl;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.java.com.BGV.DAO.CreateCommonDAO;
import main.java.com.BGV.DAO.EmployeeCommonDAO;
import main.java.com.BGV.DAO.LoginCommonDAO;
import main.java.com.BGV.DAO.Impl.CreateDAO;
import main.java.com.BGV.DAO.Impl.EmployeeDAO;
import main.java.com.BGV.DAO.Impl.LoginDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.EmployeeLogin;
import main.java.com.BGV.Model.ErrorMsgs;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;
import main.java.com.BGV.Service.UserCommonService;

public class UserService extends CommonService implements UserCommonService
{
	LoginCommonDAO loginDao = LoginDAO.getinstance();
	CreateCommonDAO createDao = CreateDAO.getInstance();
	EmployeeCommonDAO employeeDAO = EmployeeDAO.getinstance();
	
	public static UserCommonService getInstance()
	{
		return new UserService();
	}

	@Override
	public ViewObject createUser(String employeeJson) {
		
		Employee employee = generateEmployeeData(employeeJson);
		EmployeeLogin loginDetails = generateLoginData(employeeJson);
		ViewObject view = new ViewObject();
		
		if(employee != null || loginDetails !=null)
		{

			ViewObject viewEmployee = createDao.CreateUserDAO(employee);
			ViewObject viewLoginDetails = createDao.CreateLoginDetailsDAO(loginDetails);
			
			boolean saveSuccess = viewEmployee.getReturnMsg().equals(getErrorMsg(DataConstants.CXUSR005).getDescription()) && viewLoginDetails.getReturnMsg().equals(getErrorMsg(DataConstants.CXLGCR018).getDescription()) ? true : false;
			if(saveSuccess)
			{
				view.setReturnMsg(getErrorMsg(DataConstants.CXUSR005).getDescription());
			}
			else
			{
				view.setReturnMsg(getErrorMsg(DataConstants.CXUSR006).getDescription());
			}
		}
		else
		{
			view.setReturnMsg(getErrorMsg(DataConstants.CXLGUSR020).getDescription());
		}
		
		return view;
	}
	
	private EmployeeLogin generateLoginData(String employeeJson) {
		
		JSONParser parser = new JSONParser();
		EmployeeLogin loginDetails = new EmployeeLogin();
		try {
			JSONObject json = (JSONObject) parser.parse(employeeJson);
			loginDetails.setEmp_ID((String)json.get("employeeID"));
			loginDetails.setPassword((String)json.get("password"));
			
			return loginDetails;
		}
		catch (ParseException e) {			
			e.printStackTrace();
			return null;
		}
		
	}

	public Employee generateEmployeeData(String employeeJson)
	{
		JSONParser parser = new JSONParser();
		ViewObject view =  new ViewObject();
		Employee employee = new Employee();
		EmployeeLogin loginDetails = new EmployeeLogin();
		try {
			JSONObject json = (JSONObject) parser.parse(employeeJson);
			employee.setEmpName((String)json.get("employeeName"));
			employee.setEmpID((String)json.get("employeeID"));
			
			String adminRights = (String)json.get("isAdmin");
			int isAdmin = adminRights.equals("true") ? 1 : 0;
			
			employee.setIsAdmin(isAdmin);
			employee.setCreatedBy((String)json.get("createName"));
			employee.setCreatedDate(new Date());
			
			loginDetails.setEmp_ID((String)json.get("employeeID"));
			
			view = loginDao.loginDAO(loginDetails);
			if(view.getReturnObject().size() ==0 || view.getReturnObject() ==null)
				return employee;
			else
				
				return null;
			
			
		} catch (ParseException e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ViewObject getEmployeeDataAll() {
		ViewObject view = new ViewObject();
		ErrorMsgs err = new ErrorMsgs();
		try
		{
			view = employeeDAO.getEmployeeDetailsAll();
			if(view.getReturnObject()!=null)
			{
				view.setReturnMsg(jsonConverter(view.getReturnObject()));
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			err = getErrorMsg(DataConstants.CXDBFTH011);
			view.setReturnMsg(err.getDescription());
		}
		return view;
	}

	@Override
	public ViewObject getUserData(String employeeID) {
		ViewObject view = new ViewObject();
		ErrorMsgs err = new ErrorMsgs();
		Employee employee = new Employee();
		try
		{
			if(employeeID !=null || employeeID !="")
			{
				employee.setEmpID(employeeID);
				view = employeeDAO.getEmployeeDetails(employee);
				view.setReturnMsg(jsonConverter(view.getReturnObject()));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			err = getErrorMsg(DataConstants.CXDBFTH011);
			view.setReturnMsg(err.getDescription());
		}
		return view;
	}
	
	

}
