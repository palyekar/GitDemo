package GoogleMapAPI;

import io.restassured.path.json.JsonPath;

public class ReusableCode {
	public static JsonPath reuse_code(String responce) {
		JsonPath js= new JsonPath(responce);
		return js;
	}

}
