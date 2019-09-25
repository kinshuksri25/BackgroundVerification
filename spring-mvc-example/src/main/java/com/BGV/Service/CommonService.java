package main.java.com.BGV.Service;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.BGV.DAO.ErrorMsgsCommonDAO;
import main.java.com.BGV.DAO.ViewCommonDAO;
import main.java.com.BGV.DAO.Impl.ErrorMsgsDAO;
import main.java.com.BGV.DAO.Impl.ViewDAO;
import main.java.com.BGV.Model.ErrorMsgs;
import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewName;
import main.java.com.BGV.Model.ViewObject;

public class CommonService
{
	ViewCommonDAO viewDAO = ViewDAO.getinstance();
	ErrorMsgsCommonDAO errorDao = ErrorMsgsDAO.getinstance();
	
	public ErrorMsgs getErrorMsg(String errorCode)
	{
		ViewObject view = errorDao.getErrorMsgs(errorCode);
		List returnList = view.getReturnObject();
		ErrorMsgs msgs = (ErrorMsgs) returnList.get(0);
		return msgs;
	}
	
	public ViewName getView(String urlMapping)
	{
		ViewObject view = viewDAO.getview(urlMapping);
		List returnList = view.getReturnObject();
		ViewName viewName = (ViewName) returnList.get(0);
		return viewName;
	}
	
	public String jsonConverter(List<Object> returnList)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		int count=1;
		String json="";
		try {
			for(Object col : returnList)
			{ 
				if(json !="")
				 json = json + ",\"JsonTab "+count+"\":"+ objectMapper.writeValueAsString(col);
				else
				 json = "{\"JsonTab "+count+"\":"+objectMapper.writeValueAsString(col);
				count++;
			}
				json = json+"}";
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return json;
	}
}
