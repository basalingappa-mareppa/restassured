import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Poastapiauto {

	public static void main(String[] args) {
		RestAssured.baseURI="https://dev-room-feed-service.chingari.io";
		String token = "eyJhbGciOiJIUzI1NiJ9.YTE4MWJkMzgyMDgzNjg1NWZkMzY3MmYzZjFkYzdiZjU6MTQyMjM0MTlkMGI2ZGRiNDgzMTkyYzAwYTA2NWNlNWFlNjQ2NTJkM2VhYmQ2NGM3NDk4N2Q2MjVkZWMxZGExNTMyYjdlZGZiZWJmNTIxMDg2ZjQwZjY5MjAxMzIxOWJiNTUwMWRhMDY1ZjQ1NjgzZTVlZDBlYTdhN2NhNWRkMTljODI2Njk1ODM0MDkwODJmZmFjZWZhYTI0ZWYzZjk0ODU4MWY0ZTNlZjQzZjgyYzIxMDkyM2YxZWY0NTU5MjFiN2UzNDY5NDFjMjAwMWQzMjMxMzcxODk0NjQ4YWI1NGQyZDg5MzdiM2RlYjVjYTA4MWI5NWZkODlmNThjNzJlYjBhZTM0N2VkYTMzYzFlOTYwMzUwYjhhYmYyMWVlNWNiZDkxNWRjNDU3YjBiZDEyNTRhNTBkZjU1NTc1NzUzNGVlM2Q5MGEzNTRiYzhhOTc2ZTQyNWQzMWQyZTM1OWIzMmJjMTc2Yzc3NTg4NDQ3NTA3NTgwZDUxMjE0MjAwNWM1ODI2YzZkMGIzYzgzZWE0YmFlM2M3NGNjZjFiYjhjNTQwODJkNjg1OWMxZWMzZDAzNzRjMTgwYTU3YWU3NDJjYWE0NjlmYjliYzc4ZjA2YWZjNzFiYjI4Yzc1YzAwNWQ3MzQ2MTQyMGQ5MWUzYjU0MTgzNzFjODI5ZGE1YjdhMTUyOTE3ZWU4ZmE4ZjUzNmI5NWNmNjMwNmUyZjY2NWY0MGFiM2MyZTNjMmYzYzI1NTk0MDUyNzg0NmJhYWQ4NWNmYThmY2RkMjQ5NGJkM2YwNjU2NDA2ZjE2YWQ3YzFmMjE5NjcwMjI0NDgxYzhiMzA5YjE5YzI4YTEwN2ZlZDhlMDk5NjE3ZjkwOGM2MzVmZjA0ZDNmMDE4OTUwYzFkYjM2YWUwMDBmYzc1NDk0MjQ1YjU3NDkxMTgxOTM2N2VmZGE0OWJkZmMwZGRmYmU2Y2VlYWU1Y2ZiYWM5NDZhMWY1YQ.rDPU3sOJes3G_XmY7lPixoXEYgKVlmDF3XyLfyzubgQ";
		RequestBody m=new RequestBody();
		m.setCategory("Dating");
		m.setLimit(20);
		m.setSkip(0);
		m.setSubCategory("ForYou");
		m.setType("feedV3");
		m.setServerToken("");
		
		String responses=given()
		.header("Content-Type","application/json")
		.header("Authorization","Bearer" + token)
		.header("device-id","0fcb24223da64192")
		.header("Version","4.2.8_alpha4")
		.header("Version-Code","828")
		.header("platform","android")
		.header("App-Id","io.chingari.app")
		.header("X-COUNTRY-CODE","in")
		.header("X-LANGUAGE","en")
		.body(m)
		.log().all().when().post("room_feed/privateCall/feedV3").then().assertThat().statusCode(401).extract().response().asString();
          System.out.println(responses);
          JsonPath js3=new JsonPath(responses);
          String msg=js3.get("message");
          System.out.println(msg);
	}

}
