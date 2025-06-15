package com.automation.api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.Assert;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;

public class Helper {
   
	String path;
	
	public String loadProperties(String property) {
		Properties prop=new Properties();
		InputStream input;
		
		try {
			input=new FileInputStream(path);
			prop.load(input);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(property);
	}
	
	public Helper setPath(String path) {
		this.path=path;
		return this;
	}
	public static void assertInt(int input,int output) {
		try {
			Assert.assertEquals(input,output);
		}catch(Exception e) {
			e.printStackTrace();;
		}
	}
	public static void assertString(String input,String output) {
		try {
			Assert.assertEquals(input,output);
		}catch(Exception e) {
			e.printStackTrace();;
		}
	}

	public static String getValue(String input,String array,int pos,String key) {
		JSONObject inputJson=new JSONObject(input);
		JSONArray jsonArray=inputJson.getJSONArray(array);
		JSONObject jsonObject=jsonArray.getJSONObject(pos);
		String val=jsonObject.getString(key);
		return val;
	}
	
	public static String generateStringFromResource(String path) throws IOException {
       return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	public static String buildJsonBody(String jsonReqFile, String paramValues) throws Exception {
		jsonReqFile = jsonReqFile.replace("\\", "//");
		String fullFilePath = System.getProperty("user.dir") + jsonReqFile;
		String jsonInStr = new String(Files.readAllBytes(Paths.get(fullFilePath)));
		if (jsonInStr.contains("PARAM")) {
			StringTokenizer st = new StringTokenizer(paramValues, "|");
			LinkedList paramList = new LinkedList();
			while (st.hasMoreTokens()) {
				paramList.add(st.nextToken());
			}

			int totParams = paramList.size();
			int requestArgConst = StringUtils.countMatches(jsonInStr, "PARAM");
			if (requestArgConst != totParams) {
				throw new Exception("Matching Error");
			}

			if (requestArgConst > 0) {
				for (int j = 0; j < requestArgConst; ++j) {
					if (((String) paramList.get(j)).contains("$")) {
						String handelPwd$ = (String) paramList.get(j);
						String[] separate$ = handelPwd$.split("\\$");
						String tt = separate$[0] + "\\$";
						jsonInStr = jsonInStr.replaceAll("PARAM" + String.format("%02d", j + 1), tt);
					} else {
						jsonInStr = jsonInStr.replaceAll("PARAM" + String.format("%02d", j + 1),
								(String) paramList.get(j));
					}
				}
			}

		}

		return jsonInStr;

	}
	
	public static String  randomNumberSixDigit() {
		int min=100;
		int max=900;
		int min1=200;
		int max1=800;
		int num1=(int)(Math.random()*(max-min+1)+min);
		int num2=(int)(Math.random()*(max1-min1+1)+min1);
		String a=Integer.toString(num1);
		String b=Integer.toString(num2);
		return a+b;
		
	}
	
	public static String getNextDate(String currDate) throws ParseException {

		Calendar today = Calendar.getInstance();
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = format.parse(currDate);
		today.setTime(date);
		today.add(Calendar.DAY_OF_YEAR, 1);
		String nextDate = format.format(today.getTime());

		return nextDate;
	}
	
	public static String getCurrentDate() {
		String currDate=new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
		return currDate;
	}
	
	
}
