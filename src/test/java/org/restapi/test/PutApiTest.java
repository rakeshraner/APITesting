package org.restapi.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.restapi.base.TestBase;
import org.restapi.data.Put;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import prg.restapi.client.RestClient;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PutApiTest extends TestBase

{

	String uri;
	public PutApiTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setup()
	{
		String url=prop.getProperty("url");
		String serviceUrl=prop.getProperty("putcall");
		uri=url+serviceUrl;
		System.out.println(uri);	
	}
	
	
	@Test
	public void putCallTest() throws JsonGenerationException, JsonMappingException, IOException
	{
		RestClient restclient=new RestClient();
		
		HashMap<String, String> header=new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		
		//Add Updated Value In POJO so we can Pass through JSON
		Put put=new Put("Rakesh", "Vice President");
		
		//Convert POJO into JSON String (Marshelling) so that we can pass it in JSON format while executing Put Call
		ObjectMapper mapper=new ObjectMapper();
		//mapper.writeValue(new File("/Users/rakeshrane/workspace/Workspace_API/restApiTest/src/main/java/putdata.json"), put);
		String requestJsonStringValue=mapper.writeValueAsString(put);
		CloseableHttpResponse responseUnderPutCall=  restclient.put(uri, requestJsonStringValue, header);
		
		
		//Validation of Response StatusCode
		int ResponseStatusCode=responseUnderPutCall.getStatusLine().getStatusCode();
		System.out.println(ResponseStatusCode);
		Assert.assertEquals(ResponseStatusCode, RESPONSE_STATUS_CODE_200);
		
		//Convert JSON Object to java object POJO (Unmarshelling) so that we can Assert it with Previous JAVA Object POJO 
		String responseJsonStringValue =EntityUtils.toString(responseUnderPutCall.getEntity(), "UTF-8");		
		Put validateJson=mapper.readValue(responseJsonStringValue, Put.class);
		Assert.assertTrue(validateJson.getJob().equals(put.getJob()));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
