package com.project_one.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project_one.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Employeeapi_GET_All_Employees extends TestBase  {
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("-------------- Started TC001 GET all Emmployees data------------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employee");
		Thread.sleep(3);
	
	}
	@Test
	void responseBody()
	{
		logger.info("---------------checking response body--------------");
		String responseBody=response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	@Test
	void checkStatuscode()
	{
		logger.info("----------------Checking status code---------------");
		int statusCode=response.getStatusCode(); //Getting status code
		logger.info("Status code==>"+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	@Test
	void checkresponseTime()
	{
		logger.info("----------------checking response time---------------");
		long responseTime=response.getTime();
		if(responseTime>2000)
		logger.warn("response time is greater than 2000");
		Assert.assertTrue(responseTime<2000);
		
	}
		
	@Test
	void checkstatusline()
	{
		logger.info("------------------Checking statusLine---------------");
	String statusLine=response.getStatusLine();
	logger.info("Status Line is==>"+statusLine);
	Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	@Test
     void checkcontentType()
       {
		logger.info("------------------Checking Content-Type---------------");
		String contentType=response.header("Content-Type");
		logger.info("Content type is==>"+contentType);
		Assert.assertEquals(contentType, "text/html;charset=UTF-8");
           }
	@Test
	void checkserverType()
	{
		logger.info("------------------server type--------------");
		String serverType=response.header("Server");
		logger.info("Server Type is==>"+serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");
	}
	@Test
	void checkContentEncoding()
	{
	String contentencoding=response.header("Content-Encoding");
	logger.info("Content Encoding"+contentencoding);
	Assert.assertEquals(contentencoding, "gzip");
	}
	@Test
	void checkcontentlength()
	{
		String contentlength=response.header("Content-Length");
		logger.info("content length is==>"+contentlength);
		
		if(Integer.parseInt(contentlength)<100)
			logger.warn("content length is less than 100");
		Assert.assertTrue(Integer.parseInt(contentlength)>100);
	}
	@Test
	void checkCookies()
	{
		logger.info("---------------Checking Cookies-----------");

	String cookie=response.getCookie("PHPSESSID");
		
	}
	
	@AfterClass
	void testDrown()
	{
		logger.info("-----------Finish TC001_GET_ALL_Employe--------------");
		
	}
	
	
	
	
}
