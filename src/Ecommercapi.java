import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.Loginresponse;
import pojo.Orderdetails;
import pojo.Orderplacereq;
import pojo.Requestbodylogin;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ecommercapi {

	public static void main(String[] args) {
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		Requestbodylogin requestbodylogin=new Requestbodylogin();
		requestbodylogin.setUserEmail("postman@gmail.com");
		requestbodylogin.setUserPassword("Hello123@");
		
		RequestSpecification reqlogin=given().spec(req).body(requestbodylogin);
		
		Loginresponse loginresponse=reqlogin.when().post("api/ecom/auth/login")
		.then().extract().response().as(Loginresponse.class);
		String token=loginresponse.getToken();
		String userid=loginresponse.getUserId();
		System.out.println(token);
		System.out.println(userid);
		
		//add product API automate
		
		RequestSpecification addproduct=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization",token).build();
		
		RequestSpecification addreqproduct=given().spec(addproduct).param("productName", "qwerty")
		.param("productAddedBy",userid ).param("productCategory", "fashion")
		.param("productSubCategory", "dell").param("productPrice", 8000)
		.param("productDescription", "originaldell").param("productFor", "men")
		.multiPart("productImage",new File("/Users/basu/Downloads/Star (1).png"));
		String responseproduct=addreqproduct.when().post("api/ecom/product/add-product").then().log().all().extract().response().asString();
		JsonPath js3=new JsonPath(responseproduct);
		String prod=js3.get("productId");
		String msg=js3.get("message");
		System.out.println(prod);
		System.out.println(msg);
		
		
		//create order 
		
		RequestSpecification recreateord=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		
		Orderdetails gh=new Orderdetails();
		gh.setCountry("India");
		gh.setProductOrderedId(prod);
		List<Orderdetails> orderdetaillist=new ArrayList<Orderdetails>();
		orderdetaillist.add(gh);
		Orderplacereq bs=new Orderplacereq();
		bs.setOrders(orderdetaillist);
		RequestSpecification create=given().spec(recreateord).body(bs);
		String re=create.when().post("api/ecom/order/create-order").then().extract().response().asString();
		System.out.println(re);
		
		
		//delete the order
		RequestSpecification deleteorder=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		RequestSpecification del=given().spec(deleteorder).pathParam("prod",prod );
		String delres=del.when().delete("api/ecom/product/delete-product/{prod}").then().extract().response().asString();
		System.out.println(delres);
	}

}
