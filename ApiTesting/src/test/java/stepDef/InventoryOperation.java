package stepDef;

import org.json.JSONObject;

import com.automation.api.actions.HttpOperation;
import com.automation.api.restassuredfunctions.API;

public class InventoryOperation extends API	{

	public String getAllInventoryDetails(String url, HttpOperation method) {
		init(url,method);
		String response=callIt();
		return response;
	}
	
	public String getInventoryDetailsById(String url, HttpOperation method,String id) {
		init(url,method);
		setQueryParam("id",id);
		String response=callIt();
		return response;
	}
	
	public String addAnItemToInventory(String url, HttpOperation method, String nextId) {
		init(url,method);
		System.out.println("Endpoint: "+url);
		JSONObject requestParams=new JSONObject();
		requestParams.put("id",nextId);
		requestParams.put("name",InventoryStepDef.name);
		requestParams.put("image",InventoryStepDef.image);
		requestParams.put("price",InventoryStepDef.price);
		System.out.println("Request Body :"+requestParams.toString());
		setHeader("Content-Type","application/json");
		setBody(requestParams.toString());
		String response=callIt();
		return response;
	}
	
	public String addAnItemToInventoryWithMissingInfo(String url, HttpOperation method) {

		init(url, method);
		JSONObject requestParams = new JSONObject();
		requestParams.put("name",InventoryStepDef.name);
		requestParams.put("image",InventoryStepDef.image);
		requestParams.put("price",InventoryStepDef.price);
		System.out.println("Request Body :" + requestParams.toString());
		setHeader("Content-Type", "application/json");
		setBody(requestParams.toString());
		String response = callIt();
		return response;
	}
	
	
	
	
	
	

	
	public int statusCode() {
		return getStatusCode();
	}
	
	public void verifyCode(int code) {
		assertIt(code);
	}

	

	

}
