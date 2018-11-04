package org.restapi.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.restapi.base.TestBase;
import org.restapi.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import prg.restapi.client.RestClient;

public class GetApiTest extends TestBase

{
	String URI;
	RestClient getMethod;
	CloseableHttpResponse responseUnderGetRequest;
	
	public GetApiTest()
	{
		super();
	}
	
	

	@BeforeMethod
	public void setup()
	{
		String URL =prop.getProperty("url");
		String serviceURL=prop.getProperty("serviceUrl");
		
		//Concatenate end Point URL and service URL to MAke URI
		URI=URL+serviceURL;
		System.out.println("URI is " +URI);
	
	}
	
	
	
	@Test
	public void getCallTestWithoutHeader() throws ClientProtocolException, IOException
	{
		//Input Part of GET MEthod
		//Created Object of RestClient Class so that we can call methods in it
		getMethod=new RestClient();
		
		//GET method is called using only URI and response is saved in variable
		responseUnderGetRequest = getMethod.get(URI);
			
		
		
		
		//Output Part of GET MEthod
		//Validation part (For GET method output is 1.StatusCode,2.Response JSON,3.Header)
		//1. Retrieve Response Status Code and Assert it
		int responseStatusCode =responseUnderGetRequest.getStatusLine().getStatusCode();
		Assert.assertEquals(responseStatusCode, RESPONSE_STATUS_CODE_200);	
		
		//2.Retrieve Response JSON Object as a String and Assert it
		String responseString =EntityUtils.toString(responseUnderGetRequest.getEntity(), "UTF-8");
		JSONObject responseJson= new JSONObject(responseString);
		
		//get value from fields (JASON Attribute) used Common utility for retrieving data from JSON
		String per_page =TestUtil.getValueByJpath(responseJson,"/per_page");
		Assert.assertEquals(per_page, prop.getProperty("ExpectedPerPage"));
		String total =TestUtil.getValueByJpath(responseJson,"/total");
		Assert.assertEquals(total, prop.getProperty("ExpectedTotal"));	
		
		//get the value from JSON Array  used Common utility for retrieving data from JSON
		String lastName = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		Assert.assertEquals(lastName, prop.getProperty("ExpectedLastName"));
		String id = TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		Assert.assertEquals(id, prop.getProperty("ExpectedId"));
		String firstName = TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		Assert.assertEquals(firstName, prop.getProperty("ExpectedFirstName"));
	
		//3.Response Headers
		Header[] responseHeaders = responseUnderGetRequest.getAllHeaders();
		HashMap<String, String> allHeaders =new HashMap<String, String>();
		for(Header header : responseHeaders)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers are " +allHeaders);	

	}
	
	
	@Test
	public void getCallTestWithHeader() throws ClientProtocolException, IOException
	{
		//Input Part of GET Method
		//Created Object of RestClient Class so that we can call methods in it
		getMethod=new RestClient();
		
		//Header Value to Pass with GET Method (URI is already ready in before Method)
		HashMap<String, String> headers=new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		
		//GET method is called using URI and headers and response is saved in variable
		responseUnderGetRequest = getMethod.get(URI, headers);
	
		
		
		
		//Output Part of GET MEthod
		//Validation part (For GET method output is 1.StatusCode,2.Response JSON,3.Header)
		//1. Retrieve Response Status Code and Assert it
		int responseStatusCode =responseUnderGetRequest.getStatusLine().getStatusCode();
		Assert.assertEquals(responseStatusCode, RESPONSE_STATUS_CODE_200);
			
		//2.Retrieve Response JSON Object as a String and Assert it
		String responseString =EntityUtils.toString(responseUnderGetRequest.getEntity(), "UTF-8");
		JSONObject responseJson= new JSONObject(responseString);
		
		//get value from fields (JASON Attribute) used Common utility for retrieving data from JSON
		String per_page =TestUtil.getValueByJpath(responseJson,"/per_page");
		Assert.assertEquals(per_page, "3");
		String total =TestUtil.getValueByJpath(responseJson,"/total");
		Assert.assertEquals(total, "12");
		
		//get the value from JSON Array  used Common utility for retrieving data from JSON
		String lastName = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		Assert.assertEquals(lastName, prop.getProperty("ExpectedLastName"));
		String id = TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		Assert.assertEquals(id, prop.getProperty("ExpectedId"));
		String firstName = TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		Assert.assertEquals(firstName, prop.getProperty("ExpectedFirstName"));
		
		//3.Response Headers
		Header[] responseHeaders = responseUnderGetRequest.getAllHeaders();
		HashMap<String, String> allHeaders =new HashMap<String, String>();
		for(Header header : responseHeaders)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers are " +allHeaders);	


	}

	
	
	
	
	
	
	
}
