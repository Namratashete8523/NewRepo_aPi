package API_Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_Get_Request_Autherization {
	
	
	@Test
	void getAuthentication()
	{
		// Specify the base url
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/wether/city";
		// Request object specification (Request we are sending) 
		RequestSpecification httprequest=RestAssured.given();
		
		// Response object
		Response response=httprequest.request(Method.GET,"/");
		
		//status code validation 
		int statuscode=response.getStatusCode();
		System.out.println("ststus code is :" +statuscode);
		Assert.assertEquals(statuscode, 200);
		
		
				

}}
