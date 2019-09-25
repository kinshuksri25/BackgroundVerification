package main.java.com.BGV.IO.Impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import main.java.com.BGV.IO.IOCommonService;
import main.java.com.BGV.IO.PropertyCommonService;
import main.java.com.BGV.Model.DataConstants;
import main.java.com.BGV.Model.ErrorMsgs;
import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewObject;
import main.java.com.BGV.Service.CommonService;

public class IOService extends CommonService implements IOCommonService 
{
	PropertyCommonService propertyService = new PropertyService();
	
	public static IOCommonService getInstance()
	{
		return new IOService();
	}

	@Override
	public ViewObject copyFile(Request request) 
	{
		
		ViewObject view = new ViewObject();
		try
		{
			List<CommonsMultipartFile> supportingDocuments = request.getSupportingDocuments();
			Map<String, String> props = propertyService.getProps(DataConstants.CONFIGPATH,DataConstants.DELIMITER);
			if(props!=null)
			{
				String filePath = props.get(DataConstants.FILEPATH);
				String fileName = request.getEmployeeDetails().getEmployeeID();
				String finalPath = writeOperation(supportingDocuments,filePath,fileName);
				view.setReturnMsg(finalPath);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(null);;
		}
		return view;
	}
	@Override
	public String writeOperation(List<CommonsMultipartFile> documents, String commonSavePath,String fileName) 
	{	
		String returnMsg = null;
		String filePath = commonSavePath+ "\\"+fileName;
		ErrorMsgs errorMsgs = new ErrorMsgs();
		
		try 
		{
				Iterator<CommonsMultipartFile> it= documents.iterator();
				
				File fileDirectory = new File(filePath);
				
				if(!fileDirectory.exists())
				{
					fileDirectory.mkdir();
				}
				else
				{
					File[] files = fileDirectory.listFiles();
					int size = 0 ;
					while(size <files.length)
					{
						files[size].delete();
						size++;
					}
				}
				while(it.hasNext())
				{
					CommonsMultipartFile tempFile = it.next();	
					File file = new File(fileDirectory+"\\"+tempFile.getOriginalFilename());
				    if (file.exists()) {
				        File newFile = file;
				        String name = newFile.getName();
				        int period = name.indexOf('.');
				        newFile = new File(newFile.getParent(), name.substring(0, period) + "_new"+ name.substring(period));
				        file.renameTo(newFile);
				      }
					byte[] bytes= tempFile.getBytes();
					FileOutputStream fileOut = new FileOutputStream(file);
					BufferedOutputStream bufferedStream = new BufferedOutputStream(fileOut);
				    InputStream inputStream = new ByteArrayInputStream(bytes);
				    int b = -1;
				    
				    while(( b = inputStream.read())!= -1)
				    {
				    	bufferedStream.write(b);
				    }
				    bufferedStream.flush();
				    bufferedStream.close();
				    inputStream.close();
			}
			
				returnMsg = filePath;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			errorMsgs = getErrorMsg(DataConstants.CXDSKSV010);
			returnMsg = errorMsgs.getDescription();
		}
		return returnMsg;
	}
	
	@Override
	public ViewObject fileList(String fileLocation)
	{
		ViewObject view = new ViewObject();
		try
		{
			File directory =  new File(fileLocation);
			
			if(directory.exists())
			{
				String fileList="";
				File[] files = directory.listFiles();
				
				for(int i =0 ; i<files.length;i++)
				{
					if(i==files.length-1)
					{
						fileList+=files[i].getName();	
					}
					else
					{
						fileList+=files[i].getName()+",";	
					}	
				}
				view.setReturnMsg(fileList);
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			view.setReturnMsg(null);
		}
		return view;
	}
}
