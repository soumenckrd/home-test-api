package stepDef;

import java.util.Random;
import org.json.JSONObject;
import org.testng.Assert;
import com.automation.api.actions.HttpOperation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static com.automation.api.constants.EnvironmentConstants.baseURI;

public class InventoryStepDef {

	String res;
	int nextId;
	InventoryOperation response = new InventoryOperation();
	public static String name="Hawaiian";
	public static String image="hawaiian.png";
	public static String price="$14";
	

	@Given("User wants to fetch the Inventory details using GET api call")
	public void user_wants_to_fetch_the_inventory_details_using_get_api_call() {
		res = response.getAllInventoryDetails(baseURI + "/inventory", HttpOperation.GET);
	}

	@Then("User validate the api response")
	public void user_validate_the_api_response() {
		int statuscode = response.statusCode();
		System.out.println("Status Code: " + statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Then("User validates the Inventory response data")
	public void user_validates_the_Inventory_response_data() {
		System.out.println("Inventory Deatils: " + res);
		JSONObject responseParams = new JSONObject(res);
		int inventorySize = responseParams.getJSONArray("data").length();
		if (inventorySize >= 9) {
			System.out.println("Response contains at least 9 items");
		} else {
			Assert.fail("Response contains less than 9 items");
		}

		for (int i = 0; i < inventorySize; i++) {
			String id = responseParams.getJSONArray("data").getJSONObject(i).getString("id");
			String name = responseParams.getJSONArray("data").getJSONObject(i).getString("name");
			String image = responseParams.getJSONArray("data").getJSONObject(i).getString("image");
			String price = responseParams.getJSONArray("data").getJSONObject(i).getString("price");
			System.out.println("ID : " + id + " NAME :" + name + " IMAGE :" + image + " PRICE :" + price);
		}

	}

	@Given("User wants to fetch the Inventory details filtering by {string} using GET api call")
	public void user_wants_to_fetch_the_inventory_details_filtering_by_using_get_api_call(String id) {

		res = response.getInventoryDetailsById(baseURI + "/inventory/filter", HttpOperation.GET, id);
	}

	@Then("User validate the response contains correct Id {string} Name {string} Price {string} and Image {string}")
	public void user_validate_the_response_contains_correct_id_name_price_and_image(String id, String name,
			String price, String image) {
		
		System.out.println("Inventory Deatils: " + res);
		JSONObject responseParams = new JSONObject(res);
		String responseId=responseParams.getString("id");
		Assert.assertEquals(responseId,id);
		String responseName=responseParams.getString("name");
		Assert.assertEquals(responseName,name);
		String responsePrice=responseParams.getString("price");
		Assert.assertEquals(responsePrice,price);
		String responseImage=responseParams.getString("image");
		Assert.assertEquals(responseImage,image);

	}
	
	
	@Given("User wants to add an new item details using POST api call for {string}")
	public void user_wants_to_add_an_new_item_details_using_post_api_call_for(String testCase) {
		
		if(testCase.equalsIgnoreCase("POSITIVE")) {
			Random random = new Random();
			nextId=random.nextInt(10000);
			res = response.addAnItemToInventory(baseURI + "/inventory/add", HttpOperation.POST, String.valueOf(nextId));
			
		}
		else if(testCase.equalsIgnoreCase("NEGATIVE")) {
			res = response.addAnItemToInventory(baseURI + "/inventory/add", HttpOperation.POST, "10");
		}
		
		else if(testCase.equalsIgnoreCase("NEGATIVE MISSINGINFO")) {
			res = response.addAnItemToInventoryWithMissingInfo(baseURI + "/inventory/add", HttpOperation.POST);
		}
	   
	}

	
	@Then("User validate the api response {int} for {string}")
	public void user_validate_the_api_response_for(int code, String testCase) {

		if (testCase.equalsIgnoreCase("POSITIVE")) {
			int statusCode = response.statusCode();
			System.out.println("Status Code for " + testCase + " Add Inventory Item Scenario : " + statusCode);
			Assert.assertEquals(statusCode, code);
			System.out.println("Response Message: "+res);
			Assert.assertEquals(res,"OK");
		}
		else if(testCase.equalsIgnoreCase("NEGATIVE")) {
			int statusCode = response.statusCode();
			System.out.println("Status Code for " + testCase + " Add Inventory Item Scenario : " + statusCode);
			Assert.assertEquals(statusCode, code);
			System.out.println("Response Message: "+res);
			Assert.assertEquals(res,"Bad Request");
		}
		else if(testCase.equalsIgnoreCase("NEGATIVE MISSINGINFO")) {
			int statusCode = response.statusCode();
			System.out.println("Status Code for " + testCase + " Add Inventory Item Scenario : " + statusCode);
			Assert.assertEquals(statusCode, code);
			System.out.println("Response Message: "+res);
			Assert.assertEquals(res,"Not all requirements are met");
		}
	}
	
	@And("Validate the added item details")
	public void validate_the_added_item_details() {
		res = response.getInventoryDetailsById(baseURI + "/inventory/filter", HttpOperation.GET, String.valueOf(nextId));
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println("Newly Inventory Deatils: " + res);
		JSONObject responseParams = new JSONObject(res);
		String responseId=responseParams.getString("id");
		Assert.assertEquals(responseId,String.valueOf(nextId));
		String responseName=responseParams.getString("name");
		Assert.assertEquals(responseName,name);
		String responsePrice=responseParams.getString("price");
		Assert.assertEquals(responsePrice,price);
		String responseImage=responseParams.getString("image");
		Assert.assertEquals(responseImage,image);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
