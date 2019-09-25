package main.java.com.BGV.Service;

import main.java.com.BGV.Model.ViewObject;

public interface RequestCommonService 
{
	public ViewObject searchRequest(String searchRequest,String searchCriteria);
	public ViewObject updateRequestStatus(String requestID,String status,String comments);
}
