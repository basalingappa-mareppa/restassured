import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Getcource;
import pojo.Webautomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

public class Demooauth {

	public static void main(String[] args) {
		
		String[] courcetitle= {"Selenium Webdriver Java","Cypress","Protractor"};
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given()
		.formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
         System.out.println(response);
         JsonPath js=new JsonPath(response);
         String acces=js.get("access_token");
         System.out.println(acces);
         
         Getcource gc = given().queryParam("access_token",acces)
         .when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
         .as(Getcource.class);
         
         System.out.println(gc.getLinkedIn());
         System.out.println(gc.getInstructor());
         
         //GET THE COURCES FROM WEAUTOMATION
         
         ArrayList<String> a=new ArrayList<String>();
         List<Webautomation>w=gc.getCourses().getWebAutomation();
         for(int j=0;j<w.size();j++)
         {
        	 a.add(w.get(j).getCourseTitle());
         }
        List<String>Expectedlist=Arrays.asList(courcetitle);
        Assert.assertTrue(a.equals(Expectedlist));
         
        
          
	}

}
