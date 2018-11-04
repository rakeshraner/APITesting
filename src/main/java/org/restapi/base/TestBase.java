package org.restapi.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase 
{

	protected static Properties prop;
	
	public int RESPONSE_STATUS_CODE_200=200; 
	public int RESPONSE_STATUS_CODE_201=201; 
	public int RESPONSE_STATUS_CODE_400=400; 
	public int RESPONSE_STATUS_CODE_204=204; 
	public int RESPONSE_STATUS_CODE_202=202; 

	
	
	public TestBase()
	{
		try 
		{
			prop=new Properties();
			FileInputStream fis=new FileInputStream("/Users/rakeshrane/workspace/Workspace_API/restApiTest/src/main/java/org/restapi/config/config.properties");
			prop.load(fis);
		} 
			catch (FileNotFoundException e) 
		{
			System.out.println("FIle Not found" +e.getMessage());		
		} 
			catch (IOException e)
		{
			System.out.println("IO Exeption " +e.getMessage());		
		}
	
	
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
