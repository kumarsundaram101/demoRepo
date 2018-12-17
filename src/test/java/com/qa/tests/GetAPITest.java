package com.qa.tests;

import java.io.IOException;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.util.TestUtil;

import restapi.TestBase;

public class GetAPITest extends TestBase{
	
	TestBase  testBase;
	String url;
	String apiUrl;
	String actualurl;
	RestClient  restClient;
	CloseableHttpResponse closeablehttpResponse;
	
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException, JSONException {
		testBase = new TestBase();
		// PROP OBJECT WILL BE INITIALIZED 
		 url = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		 actualurl = url +apiUrl;
		
		
	}
@Test(priority =1)
public void getAPITest() throws ClientProtocolException, IOException, JSONException {
	restClient = new RestClient();
	closeablehttpResponse =	restClient.get(actualurl);
	//1 status code 
	int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();
	System.out.println(statusCode);
	Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status is not 200");
	
	
	//b json string 
	String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(),"UTF-8");
	System.out.println("the value of the json string " +responseString);
	
	
	JSONObject responsejson = new JSONObject(responseString);
	System.out.println("Response JSON from the API " + responsejson);
	String perPageValue = TestUtil.getValueByJpath(responsejson,"/per_page");
	System.out.println(" the value of per page "+ perPageValue);
	Assert.assertEquals(Integer.parseInt(perPageValue),3);
	
	
	String perPagvalTotal = TestUtil.getValueByJpath(responsejson,"/total");
	System.out.println(" the value of total  "+ perPagvalTotal);
	Assert.assertEquals(Integer.parseInt(perPagvalTotal),12);
	// to get the value from the JSON array 
String last_name = 	TestUtil.getValueByJpath(responsejson, "/data[0]/last_name");
String id = 	TestUtil.getValueByJpath(responsejson, "/data[0]/id");	
String avatar = 	TestUtil.getValueByJpath(responsejson, "/data[0]/avatar");	
String first_name = 	TestUtil.getValueByJpath(responsejson, "/data[0]/first_name");	


System.out.println(last_name);

System.out.println(id);

System.out.println(avatar);
System.out.println(first_name);
	//c All headers 
	Header [] headerArray = closeablehttpResponse.getAllHeaders();
	HashMap<String , String > allHeader = new HashMap<String , String >();
	for(Header header :headerArray) {
		allHeader.put(header.getName()	,header.getValue());
	}
	System.out.println("Header Array " + allHeader);
	}
	
	
@Test(priority = 2)
public void getAPITestWithHeaders() throws ClientProtocolException, IOException, JSONException {
	restClient = new RestClient();
	HashMap<String , String> headerMap = new HashMap<String , String>();
	headerMap.put("Content-Type", "application/json");
	closeablehttpResponse =	restClient.get(actualurl);
	//1 status code 
	int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();
	System.out.println(statusCode);
	Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status is not 200");
	
	
	//b json string 
	String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(),"UTF-8");
	System.out.println("the value of the json string " +responseString);
	
	
	JSONObject responsejson = new JSONObject(responseString);
	System.out.println("Response JSON from the API " + responsejson);
	String perPageValue = TestUtil.getValueByJpath(responsejson,"/per_page");
	System.out.println(" the value of per page "+ perPageValue);
	Assert.assertEquals(Integer.parseInt(perPageValue),3);
	
	
	String perPagvalTotal = TestUtil.getValueByJpath(responsejson,"/total");
	System.out.println(" the value of total  "+ perPagvalTotal);
	Assert.assertEquals(Integer.parseInt(perPagvalTotal),12);
	// to get the value from the JSON array 
String last_name = 	TestUtil.getValueByJpath(responsejson, "/data[0]/last_name");
String id = 	TestUtil.getValueByJpath(responsejson, "/data[0]/id");	
String avatar = 	TestUtil.getValueByJpath(responsejson, "/data[0]/avatar");	
String first_name = 	TestUtil.getValueByJpath(responsejson, "/data[0]/first_name");	


System.out.println(last_name);

System.out.println(id);

System.out.println(avatar);
System.out.println(first_name);
	//c All headers 
	Header [] headerArray = closeablehttpResponse.getAllHeaders();
	HashMap<String , String > allHeader = new HashMap<String , String >();
	for(Header header :headerArray) {
		allHeader.put(header.getName()	,header.getValue());
	}
	System.out.println("Header Array " + allHeader);
	}
	



	
}
	

	

		
	

