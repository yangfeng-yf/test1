<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
	<meta name="format-detection" content="telephone=no, email=no, date=no, address=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="format-detection" content="telephone=no" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<link href="css/bksystem.css" rel="stylesheet" type="text/css" />
	<link href="font/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="css/module.css" rel="stylesheet" type="text/css" />
	<link href="css/pages.css" rel="stylesheet" type="text/css" />
	<title>添加</title>
	<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="js/jquery.cookie.js" type="text/javascript"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="js/HUpages.js" type="text/javascript"></script>
	<script src="js/utils.js" type="text/javascript"></script>
</head>
<% String uId = (String)request.getAttribute("uId");%>

<body id="pagestyle" class="padding15">
<div class="Bombbox-info Tab-Module" style="width: 600px; margin: auto">
	<ul class="tab_memu box">
		<li class="boxbox-flex2">
			<a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox selected" data-id="0">修改信息</a>
		</li>

	</ul>
	<div class="tab-box tabcontent">
		<div class="tab-conent prompt_style active">
			<form action="/editUserInfoIm" method="post" id="saveUserInfo">
			<ul class="clearfix add_style">
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">编号</label>
					<div class="cell-right"><input name="uId" type="text" readonly="readonly"  class=" col-xs-6 col-lg-12" placeholder="必填" ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">姓名</label>
					<div class="cell-right"><input name="uName" type="text" class=" col-xs-6 col-lg-12" placeholder="必填"   ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">联系方式</label>
					<div class="cell-right"><input name="uPhone" type="text" class="col-xs-6 col-lg-12" placeholder="必填"  ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">地址</label>
					<div class="cell-right"><input name="uAddress" type="text" class="col-xs-6 col-lg-12" placeholder="必填" ></div>
				</li>

			</ul>
			<div class="buttonstyle ">
				<button  type="submit" title="保存添加" class="btn padding10 bg-deep-blue ">保存添加</button>
			</div>
			</form>
		</div>
	</div>
</div>
</body>

</html>
<script>
    saveUserInfo.onsubmit = function() {
        //form提交之前先判空
        var uName = $("input[name='uName']").val().trim();
        var uPhone = $("input[name='uPhone']").val().trim();
        var uAddress = $("input[name='uAddress']").val().trim();

		if (uName == null || uName == ""
			|| uPhone == null || uPhone == ""
            || uAddress == null || uAddress == ""){
		    alert("信息填写不完整");
			return false;
		}
		return true;
    }
    //内页框架
    $(function() {
        $("#pagestyle").Hupage({
            pagecontent:'.Bombbox-info',//自定义属性
            tabcontent:'.tabcontent',
            tabmemu:'.tab_memu',
            scrollbar: function(e) {
                e.niceScroll({
                    cursorcolor: "#dddddd",
                    cursoropacitymax: 1,
                    touchbehavior: false,
                    cursorwidth: "3px",
                    cursorborder: "0",
                    cursorborderradius: "3px",
                });
            },
            expand: function(thisBox, settings) {
                $(settings.pagecontent).css({height:$(window).height()-30});
                settings.scrollbar($(settings.tabcontent).css({height:$(window).height()-$(settings.tabmemu).outerHeight()-30}));
            }
        })

        /**
		 * 通过id查询用户信息
          */
        //var uId = getQueryString("uId");
		console.log("uId:"+${uId});
        postAjax("/selectUserInfoById",null,{
			"uId":${uId}
		},function(data){
            console.log("data:"+data);
            $("input[name='uId']").val(data.uId);
            $("input[name='uName']").val(data.uName);
            $("input[name='uPhone']").val(data.uPhone);
            $("input[name='uAddress']").val(data.uAddress);
		},function(error){
			console.log(error);
		})


    })

</script>