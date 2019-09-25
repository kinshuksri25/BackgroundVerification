package main.java.com.BGV.Controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.com.BGV.DAO.ErrorMsgsCommonDAO;
import main.java.com.BGV.DAO.ViewCommonDAO;
import main.java.com.BGV.DAO.Impl.ErrorMsgsDAO;
import main.java.com.BGV.DAO.Impl.ViewDAO;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.ErrorMsgs;
import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewName;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CreateRequestCommonService;
import main.java.com.BGV.Service.Impl.CreateRequestService;

@Controller
public class CreateRequestController 
{
	CreateRequestCommonService createRequestService = CreateRequestService.getInstance();

	@RequestMapping(value = "createBGVRequest", method=RequestMethod.POST)
	public ModelAndView CreateRequest(Request request)
	{
		System.out.println(request.getAccountName());
		ModelAndView modelAndView = new ModelAndView();
		ViewObject view = new ViewObject();
		try
		{

			view = createRequestService.createBGVRequest(request);
			modelAndView.setViewName(view.getViewName());
			modelAndView.addObject("message",view.getReturnMsg());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("error.jsp");
			modelAndView.addObject("message",view.getReturnMsg());
		}
		return modelAndView;
	}
}
