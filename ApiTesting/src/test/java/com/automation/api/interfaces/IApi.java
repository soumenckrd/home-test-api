package com.automation.api.interfaces;

import java.util.List;

import com.automation.api.actions.HttpOperation;
import com.automation.api.actions.ValidatorOperation;

public interface IApi {
      public void init(String url, HttpOperation method);
      public void setHeader(String[][] head);
      public void setHeader(String head, String val);
      public void setBody(String body);
      public void setFormParam(String key,String val);
      public void setQueryParam(String key,String val);
      public String callIt();
      public Object assertIt(int code);
      public Object assertIt(String key,String val,ValidatorOperation operation);
      public void assertIt(List<List<Object>> data);
      public void failTest(String expected,String present);
      public void failTest(String message);
      public void fileUpload(String key,String path,String mime);
      public String extractString(String path);
      public int extractInt(String path);
      public List extractList(String path);
      public Object extractIt(String path);
      public String extractHeader(String headerName);
      public String getResponseString();
      public void printResponse();
      
      
      
      
}
