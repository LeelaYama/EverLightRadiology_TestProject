package com.computerproject.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig()
	{
		File src=new File("./Configurations/config.properties");

		try
		{
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);

		}
		catch (Exception e)
		{
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	public String getApplicationURL()
	{
		String url=pro.getProperty("baseURL");
		return url;
		
	}
	public String getJavascriptApplicationURL()
	{
		String url=pro.getProperty("baseURL2");
		return url;
		
	}	
	public String getChromePath()
	{
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
		
	}
}
