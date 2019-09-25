package main.java.com.BGV.IO;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import main.java.com.BGV.Model.Request;
import main.java.com.BGV.Model.ViewObject;

public interface IOCommonService 
{
	public ViewObject copyFile(Request request);
	
	public String writeOperation(List<CommonsMultipartFile>documents, String commonSavePath,String fileName);
	
	public ViewObject fileList(String fileLocation);
}
