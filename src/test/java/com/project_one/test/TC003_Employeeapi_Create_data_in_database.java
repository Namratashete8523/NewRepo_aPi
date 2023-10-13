package com.project_one.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project_one.base.TestBase;
import com.project_one.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Employeeapi_Create_data_in_database extends TestBase{
	RequestSpecification httpRequest;
    Response response;
    String empName=RestUtils.empName();
    String empsal=RestUtils.empsal();
    String empage=RestUtils.empAge();
    
    @BeforeClass
    void insertEmployeedata() throws InterruptedException
    {
    	logger.info("------------------TC003 Create data in database--------------");
    	RestAssured.baseURI=("http://dummy.restapiexample.com/api/v1");
    	httpRequest=RestAssured.given();
    	
    	JSONObject requestparams=new JSONObject();
    	requestparams.put("Name",empName);
    	requestparams.put("Salary", empsal);
    	requestparams.put("Age",empage);
    	
    	//Add the JSON to the body of the request
    	httpRequest.body(requestparams.toJSONString());
    		
    	
    	response=httpRequest.request(Method.POST,"/create");
    	Thread.sleep(4000);
    }
    @Test
    void checkResponseBody()
    {
    	String responseBody=response.getBody().asString();
    	Assert.assertEquals(responseBody.contains(empName),true);
    	Assert.assertEquals(responseBody.contains(empsal), true);
    	Assert.assertEquals(responseBody.contains(empage), true);
    	
    }
    @Test
    void checkstatuscode()
    {
    	int statuscode=response.getStatusCode();
    	Assert.assertEquals(statuscode, 200);
    }
    
    @Test
    void checkResponseTime()
    {
    	Long responsetime=response.getTime();
    	Assert.assertTrue(responsetime<2000);
    	
    }
}
