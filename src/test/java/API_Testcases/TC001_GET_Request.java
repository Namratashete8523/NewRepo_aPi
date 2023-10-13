package API_Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {

	@Test
	void getwetherdetails()
	{
		// Specify the base url
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/wether/city";
		// Request object specification (Request we are sending) 
		RequestSpecification httprequest=RestAssured.given();
		
		// Response object
		Response response=httprequest.request(Method.GET,"/Hyderabad");
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is :"+responseBody);
		
		//status code validation 
		int statuscode=response.getStatusCode();
		System.out.println("ststus code is :" +statuscode);
		Assert.assertEquals(statuscode, 200);
		
		//Status Line verification
		String statusLine=response.statusLine();
		System.out.println("Status Line is :"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");		
		
		
	}
}
