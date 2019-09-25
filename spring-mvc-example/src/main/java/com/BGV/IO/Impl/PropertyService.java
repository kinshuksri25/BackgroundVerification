package main.java.com.BGV.IO.Impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import main.java.com.BGV.IO.PropertyCommonService;
import main.java.com.BGV.Model.DataConstants;

public class PropertyService implements PropertyCommonService
{
	public static PropertyCommonService getInstance()
	{
		return new PropertyService();
	}

	@Override
	public Map<String, String> getProps(String fileName, String delimiter)
	{
		Map<String, String> map = new HashMap();
		try
		{	if(new File(fileName).exists())
			{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
		    	String line;
			    while ((line = reader.readLine()) != null)
			    {
			      if (line.trim().length()==0) continue;
			      if (line.charAt(0)=='#') continue;
			      int delimPosition = line.indexOf(delimiter);
			      String key = line.substring(0, delimPosition-1).trim();
			      String value = line.substring(delimPosition+1).trim();
			      map.put(key, value);
			    }
			}
			else
			{
				throw new Exception();
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			map =null;
		}
		
		return map;
	}
}
