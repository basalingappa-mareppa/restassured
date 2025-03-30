package files;

import io.restassured.path.json.JsonPath;

public class Reusablecalss {
	
	public static JsonPath rowtojson(String resp) 
	{
		JsonPath js1=new JsonPath(resp);
		return js1;
	}

	public static JsonPath rowtojson1(String resp) 
	{
		JsonPath js1=new JsonPath(resp);
		return js1;
	}

}
