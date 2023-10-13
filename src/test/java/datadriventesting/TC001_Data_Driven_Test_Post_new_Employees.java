package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Data_Driven_Test_Post_new_Employees {

	//Post the record(Create the record) in database. 
	
	@Test(dataProvider="empDataProvider2")
	void Post_newEmployee(String ename,String eage, String esal)
	{
		// Specify the base url
				RestAssured.baseURI="http://restapi.demoqa.com/utilities/wether/city";
				
				// Request object specification (Request we are sending) 
				RequestSpecification httprequest=RestAssured.given();
								
				// Request Payload sending along with post request
				JSONObject requestparams= new JSONObject();
				
				requestparams.put("EmpName",ename);
				requestparams.put("age", eage);
				requestparams.put("Salary", esal);
				
					
				//Add a header stating the request body is a JSON
				httprequest.header("Content-Type","application/json");
				
				//attach above data to request
				httprequest.body(requestparams.toJSONString());
				
				
				// Response object
				Response response=httprequest.request(Method.POST,"/create");
				
				//Capturing the response body to perform validation
				//print response in console window
				String responseBody=response.getBody().asString();
				System.out.println("Response Body is :"+responseBody);
				
				//verification
				Assert.assertEquals(responseBody.contains(ename), true);
				Assert.assertEquals(responseBody.contains(eage), true);
				Assert.assertEquals(responseBody.contains(esal), true);
				
				//status code validation 
				int statuscode=response.getStatusCode();
				System.out.println("ststus code is :" +statuscode);
				Assert.assertEquals(statuscode, 200);				
				
	}

	@DataProvider(name="empDataProvider")
 String [][] getEMPdata()
{
String empdata[][]= { {"abc123","10000", "20"},{"def123", "20000", "21"},{"ghi123 ","30000", "22"}};

return(empdata);


}

	@DataProvider(name="empDataProvider2")
 String [][] getEMPdata2() throws IOException
{
	//Read Data from excel	
	String path=System.getProperty("user.dir")+"/src/test/java/datadriventesting/empdata.xlsx";
	int rownum=	XLUtiles.getRowCount(path,"sheet1");
	int colcount=XLUtiles.getCellCount(path, "sheet1", 1);
	String empdata[][]=new String[rownum][colcount];
	//row
	for(int i=1;i<=rownum;i++)
	{
		//column
		for(int j=0;j<colcount;j++)
		{	
		empdata[i-1][j]=XLUtiles.getCellData(path, "Sheet1", i, j);
		
	}}
		return(empdata);


}
	
}
