function ajaxFileUpload(bpath,url,id,saveid,imgid){
	
    //开始上传文件时显示一个图片,文件上传完成将图片隐藏
	//执行上传文件操作的函数
    $.ajaxFileUpload({
        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
        url:bpath+url,
        secureuri:false,                       //是否启用安全提交,默认为false
        fileElementId:id,           			//文件选择框的id属性
        dataType:'json',                       //服务器返回的格式,可以是json或xml等
        success:function(data, status){        //服务器响应成功时的处理函数
        	if(data.state == 1){
        		var imgurl = data.data;
        		$("#"+saveid).val(imgurl);
            	$("#"+imgid).attr("src","/"+ imgurl);
        	}else{
        		alert(data.result);
        	}
        },
        error:function(data, status, e){ //服务器响应失败时的处理函数
        	 alert("error");
        }
    });
}


function ajaxFileExcel(url,id){
    //开始上传文件时显示一个图片,文件上传完成将图片隐藏
    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
    //执行上传文件操作的函数
    $.ajaxFileUpload({
        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
        url:url,
        secureuri:false,                       //是否启用安全提交,默认为false
        fileElementId:id,           			//文件选择框的id属性
        dataType:'text',                       //服务器返回的格式,可以是json或xml等
        success:function(data, status){        //服务器响应成功时的处理函数
        	data = data.replace("<PRE>", '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
            data = data.replace("</PRE>", '');
            data = data.replace("<pre>", '');
            data = data.replace("</pre>", ''); //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
            if(data.substring(0, 1) == 0){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
            	alert(data.substring(2));
            }else{
            	alert("失败");
            }
        },
        error:function(data, status, e){ //服务器响应失败时的处理函数
        	 alert("error");
        }
    });
}