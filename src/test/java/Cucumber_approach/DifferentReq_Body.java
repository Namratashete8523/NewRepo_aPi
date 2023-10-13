package Cucumber_approach;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class DifferentReq_Body {
	
//1)create the request body using hash map
@ Test	
void createreqdata_usiingHashMap()
{
	
	HashMap data =new HashMap();
	data.put("name","namratashete");
	data.put("phone","66666");
	String coursearr[]= {"selenium","java"};
	data.put("courses", coursearr);
	
	given()
	.contentType("application/json")
	.body(data)
	.when()
	.post("http://localhost:3000/student")
	.then()
.statusCode(201)
.log().all();
			

}
	
	
	
	

}
