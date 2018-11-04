package org.restapi.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.restapi.base.TestBase;
import org.restapi.data.Users;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import prg.restapi.client.RestClient;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostApiTest extends TestBase

{
	String URI;
	RestClient postMethod;
	CloseableHttpResponse responseUnderPostRequest;

	public PostApiTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		String URL =prop.getProperty("url");
		String serviceURL=prop.getProperty("serviceUrl");
		URI=URL+serviceURL;
		System.out.println("URI is " +URI);
	}

	
	@Test
	public void postCallMethod() throws JsonGenerationException, JsonMappingException, IOException
	{
		
		//Input for POST API
		//First create Object of RestClient so we can make use of Post method in it
		postMethod=new RestClient();
		
		//Header Value to Pass with GET Method (URI is already ready in before Method)
		HashMap<String, String> header=new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		
		//Create Java Object (POJO) in which data is stored
		Users user=new Users("Rakesh", "SSE");
		
		//Convert POJO into JSON Object
		ObjectMapper mapper=new ObjectMapper();
		mapper.writeValue(new File("/Users/rakeshrane/workspace/Workspace_API/restApiTest/src/main/java/user.json"), user);
		//java Object to JSON in string  (Marshelling)
		String usersJsonString=mapper.writeValueAsString(user);

		
		//POST method is called using URI,JSON body and headers and response is saved in variable
		responseUnderPostRequest= postMethod.post(URI, usersJsonString, header);
	
		
		
		//Output of POST call
		//Validation part (For POST method output is 1.StatusCode,2.Response JSON,3.Header)
		//1. Retrieve Response Status Code and Assert it
		int StatusCode = responseUnderPostRequest.getStatusLine().getStatusCode();
		Assert.assertEquals(StatusCode, RESPONSE_STATUS_CODE_201);
		
		//2.Retrieve Response JSON Object as a String
		String jsonString =EntityUtils.toString(responseUnderPostRequest.getEntity(),"UTF-8");
		
		/*JSONObject jsonObject=new JSONObject(jsonString);
		System.out.println(jsonObject);*/
		
		//Convert JSON Object to java object POJO (Unmarshelling) and Assert with Previous JAVA Object POJO 
		Users responseUser =mapper.readValue(jsonString, Users.class);		
		Assert.assertTrue(responseUser.getName().equals(user.getName()));
	}
	
	
	
	
	
	
	
	
}
