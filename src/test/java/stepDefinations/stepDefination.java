package stepDefinations;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;



import static org.junit.Assert.assertEquals;

public class stepDefination {

		RequestSpecification rs;
		ResponseSpecification responcespec;
		Response responce;
		RequestSpecification reqs;
		
		@Given(": Add Place Payload")
		public void add_place_payload() {
			
			PojoClasses.Pojo_AddPlace a= new PojoClasses.Pojo_AddPlace();
			a.setAccuracy(50);
			a.setAddress("29, side layout, cohen 09");
			a.setLanguage("French-IN");
			a.setName("Frontline");
			a.setPhone_number("43354543");
			a.setWebsite("http://google.com");
			
			List<String> type= new ArrayList<String>();
			type.add("shoe park");
			type.add("shop");
			a.setTypes(type);
			
			PojoClasses.location l = new PojoClasses.location();
			l.setLat(-38.45);
			l.setLng(34.67);
			
			a.setLocation(l);
			
			rs=
			new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
			
			responcespec=
			new ResponseSpecBuilder().expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();
			
			 reqs=given().spec(rs)
			.body(a);
	   
	}
		
		
	@When(": user calls {string} with POST request")
	public void user_calls_with_post_request(String string) {
		responce= reqs.when() 
		.post("/maps/api/place/add/json")
		.then().spec(responcespec)
		.extract().response();
	}
	
	
	@Then(": the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(responce.getStatusCode(),200);
				  
	}
	
	
	@Then(": {string} in responce body is {string}")
	public void in_responce_body_is(String keyvalue, String ExpectedValue) {
		  String respo= responce.asString();
		    JsonPath js= new JsonPath(respo);
		    assertEquals(js.get(keyvalue).toString(),ExpectedValue);
	   
	}




}
