package com.project_one.test;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project_one.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Employeeapi_GET_One_Employee_Detail extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("------------Started TC002_Get_Single_Employee_record------------------");
		RestAssured.baseURI=("http://dummy.restapiexample.com/api/v1");
		httpRequest=RestAssured.given();
		httpRequest.request(Method.GET,"/employee/"+empID);
		Thread.sleep(3000);
		}
	@Test
	void checkResponseBody()
	{
		String responsebody=response.getBody().asString();
		assertEquals(responsebody.contains(empID),true);
	}
	@Test
	void checkstatuscode()
	{
		int statuscode=response.getStatusCode();
        Assert.assertEquals(statuscode,200);
        
     }
	@Test
	void checkResponseTime()
	{
		Long responseTime=response.getTime();
		Assert.assertTrue(responseTime<2000);
		
	}
	
	@Test
	void checkstatusLine()
	{
	String statusLine=response.statusLine();
	Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	
	
	
}
