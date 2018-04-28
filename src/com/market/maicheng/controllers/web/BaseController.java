package com.market.maicheng.controllers.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

public class BaseController {
    @ExceptionHandler  
    @ResponseBody
   public JSONObject exp(HttpServletRequest request, Exception ex) {  
         
//       request.setAttribute("ex", ex);  
    	ex.printStackTrace();
    	JSONObject result = new JSONObject();
    	result.put("state", 0);
    	result.put("result", "程序出错,请联系管理员");
    	return result;
         
   } 
}
