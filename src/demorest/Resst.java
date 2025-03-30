package demorest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payloads;
import files.Reusablecalss;


public class Resst {

	public static void main(String[] args) {
		//given.when.then
		//given->all input details
		//when->submit the API
		//then->validate the response
		
		//Add place API
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		
		.body(Payloads.addplace()).when().post("maps/api/place/add/json")
		        .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		        .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		        
		     System.out.println(response);
		     JsonPath js = new JsonPath(response);
		     String placeId=js.getString("place_id");
		     System.out.println(placeId);
		     
	  //update place API     
		     String newaddress = "70 winter walk, USA";
		     given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		     .body("{\n"
		     		+ "\"place_id\":\""+placeId+"\",\n"
		     		+ "\"address\":\""+newaddress+"\",\n"
		     		+ "\"key\":\"qaclick123\"\n"
		     		+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		     .body("msg",equalTo("Address successfully updated"));
		     
		     
		     //get place
		     String resp=given().log().all().queryParam("key", "qaclick123")
		     .queryParam("place_id", placeId).when().get("maps/api/place/get/json")
		     .then().assertThat().statusCode(200).extract().response().asString();
		     
		     JsonPath js1=Reusablecalss.rowtojson(resp);
		     String newa=js1.getString("address");
		     System.out.println(newa);
		     System.out.println(resp);
		     
		     Assert.assertEquals(newa, newaddress);
		     
		     //delete the placeid
		     
		     
	
	}

}
