$(function(){ 
var win_w=$(window).width();
var win_h=$(window).height();

//左侧拖动插件
  $("#left").resizable({
        minWidth: 60,
        handles: "e",
        resize: function(e, t) {
            var n = $(".search-form .search-pane input[type=text]"),
            r = $("#main");
            n.css({
                width: t.size.width - 55
            });
            if (Math.abs(200 - t.size.width) <= 20) {
                $("#left").css("width", 200);
                n.css("width", 145);
                r.css("margin-left", 200)
            } else r.css("margin-left", $("#left").width())
        },
        stop: function() {
            $("#left .ui-resizable-handle").css("background", "none");
        },
        start: function() {
            $("#left .ui-resizable-handle").css("background", "#aaa")
        }
    });
	//end
	
	//底部公告
	$(".bot_box_b_close").click(function(){
		$(".bottom_box").hide();	
	})
	//end

	//左侧菜单显隐
	var left_bo=true
	$("#navigation .toggle-nav").click(function(){
		if(left_bo)
		{
			$("#left").stop().animate({left:-($("#left").width())},400,function(){
				$("#left").hide()
			})
			$("#main").stop().animate({marginLeft:0},400)
			left_bo=false;
		}
		else
		{
			$("#left").show().stop().animate({left:0},400)
			$("#main").stop().animate({marginLeft:($("#left").width())},400)
			left_bo=true;
		}
		
	})
	//
	
	
	$(".user li,.dropdown").hover(function(){
		$(this).find(".dropdown-menu").show();
		}, function(){
		$(this).find(".dropdown-menu").hide();	
		})	
	$("#left").mousedown(function(){
		$("#mask").show();	
		
	})
	$(document).mouseup(function(){
		$("#mask").hide();	
	})

	
	function w_resize()
	{
		win_w=$(window).height();
		win_h=$(window).height();
		$("#main,#main_cont").height(win_h-38);
		$(".left_center").height(win_h-38-40).find("ul:last").css({paddingBottom:140});
		$("#right_box1").css({height:win_h-38})
	}
	w_resize()
	
	$(window).resize(function(){
		w_resize()	
	})
	
	
	
	$("#top_bon").click(function(){
		$("#c_headerBar").show().stop().animate({top:0})	
		$(".mask_box").show();
	})
	$("#c_headerBar a").click(function(){
		$("#c_headerBar").show().stop().animate({top:-206},500)
		$(".mask_box").hide();
		})
	$(".mask_box").click(function(){
		$("#c_headerBar").show().stop().animate({top:-206},500)	
		$(".mask_box").hide();
		})

	
	//end
	
})
	//左侧导航显隐	
	function leftnav(num,id){
		$(".left_center ul").hide()
		$(id).show();
		$(id).find("li").removeClass("curr").eq(num).addClass("curr")
		w_resize()		
	}
 
/* 切换主题颜色 */

$(function(){
	$("#navigation .user .icon-nav > li .theme-colors li span").click(function(){
		var color = "theme-"+$(this).attr("class");
		$("body").attr("class",color)


		// $("body",document.frames('main').document).attr("class",color)


		$(window.frames["main"].document).find("body").attr("class",color)
	})
})


/* 左侧导航定位 */

$(function(){
	$("div.left_center ul li").each(function(){
		$(this).click(function(){
			$(this).siblings().removeClass("curr");
			$(this).addClass("curr")
		})
	})
})

// 站内消息操作

$(function(){

	$("#ullist_edit_selectall").change(function(){
	    // console.log($("this").attr("checked"))
	    if($(this).is(':checked')){
	        $("#main ul.ullist li input:checkbox").attr("checked",true);
	    }else{
	        $("#main ul.ullist li input:checkbox").attr("checked",false);
	    }
	})

	$("#ullist_edit_delate").click(function(){
	    $("#main ul.ullist li input:checkbox").each(function(){
	        if($(this).is(':checked')){
	            $(this).parents("li").remove();
	        }
	    })
	})
})


