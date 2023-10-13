package API_Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request {
	@Test
	void get_google_Map()
	{
		// Specify the base url
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/wether/city";
		// Request object specification (Request we are sending) 
		RequestSpecification httprequest=RestAssured.given();
		
		// Response object
		Response response=httprequest.request(Method.GET,"/Hyderabad....");
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is :"+responseBody);
		
		//validating headers
		String contentType=response.header("Content-Type");//Capture details of content-Type header
		System.out.println("Content Type is: "+contentType);
		Assert.assertEquals(contentType, "application/xml; charset=UFT-8");
	
		//validating headers
		String contentEncoding=response.header("Content-Encoding");//Capture details of content-Encoding header
		System.out.println("Content Encoding is: "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
	}

}
