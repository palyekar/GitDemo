package GoogleMapAPI;

import org.junit.Assert;

import io.restassured.path.json.JsonPath;



public class complexJson {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		JsonPath js= new JsonPath(payload.complexJsonBody());
	   int size= js.getInt("courses.size()");
	   System.out.println(size);
	   int purchaseamount= js.get("dashboard.purchaseAmount");
	   System.out.println(purchaseamount);
	   System.out.println("=============");
	   for(int i=0;i<size;i++) {
		   String titlename=js.getString("courses["+i+"].title");
		   System.out.println(titlename);
		   System.out.println(js.getInt("courses["+i+"].price"));
		   System.out.println("=============");
	   }
	 
	   for(int i=0;i<size;i++) {
		   String titlename=js.getString("courses["+i+"].title");
		   
		  if(titlename.equalsIgnoreCase("RPA")) {
			  int copies= js.get("courses["+i+"].copies");
			  System.out.println(copies);
		  }
		 
		  }
	   System.out.println("=============");
	   int totalamount;
	   int sum=0;
	   //purchase amount is same as course amount
	   for(int i=0;i<size;i++) {
		   int price=js.get("courses["+i+"].price");
		   int copies=js.get("courses["+i+"].copies");
		   totalamount= price*copies;
		   System.out.println(totalamount);
		   sum=sum+totalamount;
		   
	}
	  System.out.println("totalsum:"+sum);
	  Assert.assertEquals(sum, purchaseamount);
	}
}
