package prg.restapi.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient 

{
	CloseableHttpClient httpClientConnection;
	

    // 1.GET Method without Header	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{		
		//Created HttpCLient for connection
		httpClientConnection=HttpClients.createDefault();

		//HTTP GET request (Only URI is passed as a input to GET call)
		HttpGet getRequest =new HttpGet(url); 
		
		//Execute/Send GET call and in return will get response
		CloseableHttpResponse closableHttpResponse= httpClientConnection.execute(getRequest);
		return closableHttpResponse;
	}


	
	//2.GET Method with Header
	public CloseableHttpResponse get(String url, HashMap<String, String> header) throws ClientProtocolException, IOException
	{
		//Created HttpCLient for connection
		httpClientConnection=HttpClients.createDefault();
		
		//HTTP GET request (URL and Header both passed as a input to GET call)
		//URL passed
		HttpGet getRequest =new HttpGet(url);
		//Header is passed
		for(Map.Entry<String, String> entry: header.entrySet())
		{
			getRequest.addHeader(entry.getKey(), entry.getValue());	
		}
		
		//Execute/Send GET call and in return will get response
		CloseableHttpResponse closableHttpResponse=httpClientConnection.execute(getRequest);
		return closableHttpResponse;	
		
	}
	
	//3.Post Method
	public CloseableHttpResponse post(String url, String body, HashMap<String, String> header) throws ClientProtocolException, IOException
	{
		//Created HttpCLient for connection
		httpClientConnection=HttpClients.createDefault();
		
		//HTTP POST request (URL, Body and Header both passed as a input to POST call)
		//URL passed
		HttpPost postRequest=new HttpPost(url);                  	
		//For Body (Json Payload)
		postRequest.setEntity(new StringEntity(body)); 
		
		//For Header
		for(Map.Entry<String, String> entry: header.entrySet())  
		{
			postRequest.addHeader(entry.getKey(), entry.getValue());
		}
		
		//Execute/Send GET call and in return will get response
		CloseableHttpResponse cLosableHttpResponse = httpClientConnection.execute(postRequest);
		return cLosableHttpResponse;
	}
	
	
	//4.Delete Method without Header
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException
	{
		httpClientConnection=HttpClients.createDefault();
		HttpDelete deleteRequest=new HttpDelete(url);
		CloseableHttpResponse closableHttpResponse= httpClientConnection.execute(deleteRequest);
		return closableHttpResponse;
	
	}
	
	//5.Delete Method with Header
	public CloseableHttpResponse delete(String url, HashMap<String, String> header) throws ClientProtocolException, IOException
	{
		httpClientConnection=HttpClients.createDefault();
		HttpDelete deleteRequest=new HttpDelete(url);
		for(Map.Entry<String, String> entry:header.entrySet())	
		{
			deleteRequest.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closableHttpResponse=httpClientConnection.execute(deleteRequest);
		return closableHttpResponse;
		
	}
	
	//6. Put Method with Header
	public CloseableHttpResponse put(String url, String body, HashMap<String, String> header) throws ClientProtocolException, IOException
	{
		httpClientConnection=HttpClients.createDefault();
		HttpPut putRequest=new HttpPut(url);
		
		putRequest.setEntity(new StringEntity(body));
		
		for(Map.Entry<String, String> entry: header.entrySet())
		{
			putRequest.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closableHttpResponse=httpClientConnection.execute(putRequest);
		return closableHttpResponse;	
		
	}
	

}
