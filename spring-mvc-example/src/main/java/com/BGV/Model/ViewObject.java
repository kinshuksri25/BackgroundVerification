package main.java.com.BGV.Model;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class ViewObject 
{
	private String viewName;
	
	private String returnMsg;
	
	private List<Object> returnObject;
	
	private HttpServletResponse response;
	
	public ViewObject()
	{
		
	}
	
	public ViewObject(String viewName, List<Object> returnObject) 
	{
		this.viewName = viewName;
		this.returnObject = returnObject;
	}
	
	public ViewObject(String viewName)
	{
		this.viewName = viewName;
	}

	ViewObject(String viewName, List<Object> returnObject, HttpServletResponse response )
	{
		this.viewName = viewName;
		this.returnObject = returnObject;
		this.response = response;
	}
	
	public String getViewName()
	{
		return viewName;
	}
	
	public void setViewName(String viewName)
	{
		this.viewName = viewName;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public List<Object> getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(List<Object> returnObject) {
		this.returnObject = returnObject;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
}
