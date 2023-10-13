package API_Testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Post_Request {
	
	@Test
	void postRegistrationCust()
	{
		// Specify the base url
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/wether/city";
	
		// Request object specification (Request we are sending) 
		RequestSpecification httprequest=RestAssured.given();
		
		// Request Payload sending along with post request
	JSONObject requestparams= new JSONObject();
	
	requestparams.put("FirstName", "Johnxyz123");
	requestparams.put("LastName", "Shetexyz123");
	requestparams.put("UserName", "xyz");
	requestparams.put("Password", "Jonpass");
		
	httprequest.header("Content-Type","application/json");
	
	//attach above data to request
	httprequest.body(requestparams.toJSONString());
	
	// Response object
			Response response=httprequest.request(Method.POST,"/register");
			
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is :"+responseBody);
		
		//status code validation 
		int statuscode=response.getStatusCode();
		System.out.println("ststus code is :" +statuscode);
		Assert.assertEquals(statuscode, 201);
		
		//success response 
		String sucesscode=response.jsonPath().get("SucessCode");
		Assert.assertEquals(sucesscode, "OPERATION_SUCESS");
        		
	}

}
