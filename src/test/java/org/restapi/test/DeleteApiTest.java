package org.restapi.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.restapi.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import prg.restapi.client.RestClient;

public class DeleteApiTest extends TestBase 
{

	String uri;
	
	public DeleteApiTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setup()
	{
		String url=prop.getProperty("url");
		String serviceUrl=prop.getProperty("putcall");
		uri=url+serviceUrl;
	}
	
	@Test
	public void deleteCall() throws ClientProtocolException, IOException
	{
		
		RestClient restclient=new RestClient();
		CloseableHttpResponse reposnseUnderDeleteCall= restclient.delete(uri);
			
		int responseStatusCode =reposnseUnderDeleteCall.getStatusLine().getStatusCode();
		Assert.assertEquals(responseStatusCode, RESPONSE_STATUS_CODE_204);
		
	}
	
	
	@Test
	public void deleteCallWithHeader() throws ClientProtocolException, IOException
	
	{
		RestClient restclient=new RestClient();
		
		HashMap<String, String> header=new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		
		CloseableHttpResponse responseUnderDeleteCall= restclient.delete(uri, header);
		int rsponseStatusCode =responseUnderDeleteCall.getStatusLine().getStatusCode();
		Assert.assertEquals(rsponseStatusCode, RESPONSE_STATUS_CODE_204);
		
		
	}
	
	
	
	
}
