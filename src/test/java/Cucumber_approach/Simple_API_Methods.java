package Cucumber_approach;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class Simple_API_Methods {
	
	int id;	
	@Test(priority=1)
	void getrequest()
	{	
		given()
	.when()
	.get("https://reqres.in/api/users?page=2")
	.then()
	.statusCode(200)
	.body("page",equalTo(2))
	.log().all();
	}
	
	@Test()
	void postrequest()
	{
		HashMap data =new HashMap();
		
		data.put("name","Namrata");
		data.put("job", "tester");
			
	id=	given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
	}
	
	@Test(priority=3,dependsOnMethods= {"postrequest"})
	void putdata()
	{
		HashMap data=new HashMap();
		data.put("name", "shete");
		data.put("job","developer");
		given()
		
		.contentType("application/json")
		.body(data)
		.when()
		.put("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test
	void deletereq()
	{
		given()
		.when()
		.delete("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(204)
		.log().all();
	}
}
