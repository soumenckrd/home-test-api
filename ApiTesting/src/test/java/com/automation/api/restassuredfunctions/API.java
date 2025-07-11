package com.automation.api.restassuredfunctions;

import java.io.File;
import java.util.List;

import com.automation.api.actions.HttpOperation;
import com.automation.api.actions.ValidatorOperation;
import com.automation.api.interfaces.IApi;
import com.automation.api.utilities.Helper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class API  implements IApi{
	
	RequestSpecification reqSpec;
	HttpOperation method;
	String url;
	Response resp;

	public void init(String url, HttpOperation method) {
		this.url=url;
		this.method=method;
		reqSpec= given();
		
	}
	
	public void initBase(String baseContent) {
		Helper getHelp=new Helper();
		getHelp.setPath("resources/config.properties");
		try {
			RestAssured.baseURI=getHelp.loadProperties(baseContent);
		}catch(Exception e) {
			e.printStackTrace();
		}
		reqSpec=RestAssured.given();
		
	}

	public void setHeader(String[][] head) {
		 for(int row=0;row<head.length ; row++) {
			 for(int col=0;col<head[row].length;col++) {
				 reqSpec.header(head[row][col],head[row][col+1]);
			 }
		 }
		
	}

	public void setHeader(String head, String val) {reqSpec.header(head, val);}
	public void setBody(String body) {reqSpec.body(body);}
	public void setFormParam(String key, String val) {reqSpec.formParam(key,val);}
	public void setQueryParam(String key, String val) {reqSpec.queryParam(key,val);}
	public String callIt() {
		if(method.toString().equalsIgnoreCase("get")) {
			resp=reqSpec.get(url);
			return resp.asString();
		}else if(method.toString().equalsIgnoreCase("post")) {
			resp=reqSpec.post(url);
			return resp.asString();
		}else if(method.toString().equalsIgnoreCase("put")) {
			resp=reqSpec.put(url);
			return resp.asString();
		}else if(method.toString().equalsIgnoreCase("delete")) {
			resp=reqSpec.delete(url);
			return resp.asString();
		}else if(method.toString().equalsIgnoreCase("patch")) {
			resp=reqSpec.patch(url);
			return resp.asString();
		}
		 return "invalid method set for API";
	}

	public API assertIt(int code) {
		resp.then().statusCode(code);
		return this;
	}

	public API assertIt(String key, String val, ValidatorOperation operation) {
		switch(operation.toString()) {
		case "EQUALS":
			  resp.then().body(key,equalTo(val));
			  break;
		case "KEY_PRESENTS":
			  resp.then().body(key,hasKey(val));
			  break;
		case "HAS_ALL":	  
			  break;
		case "NOT_EQUALS":
			  resp.then().body(key,not(equalTo(val)));
			  break;
		case "NOT_NULL":
			 resp.then().body(key,notNullValue());
			  break;
		case "HAS_STRING":
			  resp.then().body(key,contains((String)val));
			  break;
		case "EMPTY":
			 resp.then().body(key,empty());
			 break;
		case "NOT_EMPTY":	  
			 resp.then().body(key,not(empty()));
			 break;
		case "SIZE":
			 resp.then().body(key,hasSize((Integer.valueOf(val))));
			 break;
			  
		}
		return this;
	}

	public void assertIt(List<List<Object>> data) {
		for(List<Object> li: data) {
			switch(((ValidatorOperation)li.get(2)).toString()){
			case "EQUALS":
				  resp.then().body(((String)li.get(0)),equalTo((String) li.get(1)));
				  break;
			case "KEY_PRESENTS":
				  resp.then().body(((String)li.get(0)),hasKey((String) li.get(1)));
				  break;
			}
		}
		
	}

	public void failTest(String expected, String present) {
		// TODO Auto-generated method stub
		
	}

	public void failTest(String message) {
		// TODO Auto-generated method stub
		
	}

	public void fileUpload(String key, String path, String mime) {
		reqSpec.multiPart(key,new File(path),mime);	
	}
	public void multiPartString(String key,String input) {reqSpec.multiPart(key,input);}

	public String extractString(String path) {return resp.jsonPath().getString(path);}

	public int extractInt(String path) {return resp.jsonPath().getInt(path);}

	public List extractList(String path) {return resp.jsonPath().getList(path);}

	public Object extractIt(String path) {return resp.jsonPath().get(path);}

	public String extractHeader(String headerName) {return resp.header(headerName);}

	public String getResponseString() {return resp.asString();}

	public void printResponse() {resp.print();}
	
	public String getCookieValue(String cookieName) {return resp.getCookie(cookieName);}
	
	public Headers getAllHeaders() {return resp.getHeaders();}
	
	public int getStatusCode() {return resp.getStatusCode();}
	
	public void setRedirection(boolean followRedirects) {
		reqSpec.when().redirects().follow(followRedirects);
	}
	

}
