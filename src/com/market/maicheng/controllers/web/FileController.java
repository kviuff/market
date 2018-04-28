package com.market.maicheng.controllers.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.FileUtils;
import com.market.maicheng.common.utils.PropertyUtils;


@Controller
@RequestMapping(value = "/file")
public class FileController {
	@RequestMapping(value="/fileUpload",method = {RequestMethod.POST})
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		//创建一个通用的多部分解析器
		PrintWriter out = response.getWriter();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){ 
			//转换成多部分request
	    	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
	    	//取得request中的所有文件名
	    	Iterator<String> iter = multiRequest.getFileNames();
	    	while(iter.hasNext()){  
	    		MultipartFile file = multiRequest.getFile(iter.next());
	    		if(file != null){
	    			//取得当前上传文件的文件名称  
	                String myFileName = file.getOriginalFilename();
	                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
	                if(myFileName.trim() !=""){
	                    SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
	                    String fileName = file.getOriginalFilename();
	                    String filetype = fileName.substring(fileName.indexOf(".")).toLowerCase();
	                    if(file.getSize() > 10000000){
	                    	jsonObject.put("state", "0");
							jsonObject.put("result", "文件超出限制，最大10M！");
	                    }else if(filetype.indexOf("jpg") > -1 || filetype.indexOf("gif") > -1 || filetype.indexOf("png") > -1){
	                    	//定义上传路径 
		                    String path = "images/upload/" + formater.format(new Date()) + "/" + UUID.randomUUID().toString().replaceAll("-", "") + filetype;
		                    String filePath = request.getRealPath("/")+ "/resources/" + path;
		                    File localFile = new File(filePath);
	                    
		                    if (!localFile.getParentFile().exists()) {
		                    	localFile.getParentFile().mkdirs();
		            		}
		                    try {
								file.transferTo(localFile);
								jsonObject.put("state", "1");
								jsonObject.put("result", "ok");
								jsonObject.put("data", path);
							} catch (Exception e) {
								jsonObject.put("state", "0");
								jsonObject.put("result", "文件上传失败，请重试！");
								e.printStackTrace();
							}
	                    	
	                    }else{
							jsonObject.put("state", "0");
							jsonObject.put("result", "文件上传失败，只支持jpg/gif/png格式！");
	                    }
	                } 
	    		}else{
	    			jsonObject.put("state", "0");
					jsonObject.put("result", "请选择文件后上传");
	    		}
	    	}
		}
		out.print(jsonObject.toString());
		out.flush();
        return null;
    }
	
	@RequestMapping(value = "/delfile",method = {RequestMethod.POST})  
	public ModelAndView delfile(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		String path = request.getParameter("path"); //StrUtils.getString(request, "path");
		int flag = FileUtils.deleteFile(PropertyUtils.getRoot()+ "/resources/" + path);
		PrintWriter out = response.getWriter();
		out.print(flag);
		out.flush();
		return null;
	}
}
