package GoogleMapAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestHttpMethods {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String responce= given().log().all()
		.queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payload.addPlacePayload())
		.when()
		.post("/maps/api/place/add/json")
		.then().log().all()
		.assertThat().statusCode(200).body("scope",equalTo("APP"))
		.extract().response().asString();
		JsonPath js2=ReusableCode.reuse_code(responce);
		
		//JsonPath js= new JsonPath(responce);
		String place_id= js2.get("place_id");
		System.out.println(place_id);
		
	
		//update address
		String address= "mapusa";
		given().queryParam("key","qaclick123")
		.header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when()
		.put("/maps/api/place/update/json")
		.then().log().all()
		.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//get address
		given().queryParam("key","qaclick123").queryParam("place_id", place_id)
		.header("Content-Type","application/json")
		.when()
		.get("/maps/api/place/get/json")
		.then().log().all()
		.assertThat().statusCode(200).body("address", equalTo(address));
	}

}
