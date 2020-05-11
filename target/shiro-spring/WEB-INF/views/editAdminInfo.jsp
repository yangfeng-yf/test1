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
	<title>修改管理员信息</title>
	<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="js/jquery.cookie.js" type="text/javascript"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="js/HUpages.js" type="text/javascript"></script>
	<script src="js/utils.js" type="text/javascript"></script>
</head>
<% String adId = (String)request.getAttribute("adId");
   String msg = (String)request.getAttribute("msg");%>

<body id="pagestyle" class="padding15">
<div class="Bombbox-info Tab-Module">
	<ul class="tab_memu box">
		<li class="boxbox-flex2">
			<a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox selected" data-id="0">修改信息</a>
		</li>

	</ul>
	<div class="tab-box tabcontent">
		<div class="tab-conent prompt_style active">
			<form action="/editAdminInfo" method="post" id="saveUserInfo">
			<ul class="clearfix add_style">
				<li class="cell-item">
					<label class="cell-left label_name">管理员ID</label>
					<div class="cell-right"><input name="adId" type="text" class=" col-xs-6 col-lg-12" placeholder="必填" readonly="readonly"  ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name">联系方式</label>
					<div class="cell-right"><input name="adPhone" type="text" class="col-xs-6 col-lg-12" placeholder="必填"  ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name">密码</label>
					<div class="cell-right"><input name="adPassword" type="text" placeholder="必填" class="col-xs-6 col-lg-12" ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name">管理员种类编号</label>
					<div class="cell-right"><input name="adType" type="text" placeholder="必填" readonly="readonly" class="col-xs-6 col-lg-12" ></div>
				</li>
				<li class="cell-item" style="display:none">
					<label class="cell-left label_name">最后修改时间</label>
					<div class="cell-right"><input name="adLastlogintime" type="text" placeholder="必填"    readonly="readonly" class="col-xs-6 col-lg-12" ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name">管理员姓名</label>
					<div class="cell-right"><input name="adName" type="text" placeholder="必填" class="col-xs-6 col-lg-12" ></div>
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
        var adId = $("input[name='adId']").val().trim();
        var adPhone = $("input[name='adPhone']").val().trim();
        var adPassword = $("input[name='adPassword']").val().trim();
        var adName = $("input[name='adName']").val().trim();

        if (adId == null || adId == ""
            || adPhone == null || adPhone == ""
            || adName == null || adName == ""
            || adPassword == null || adPassword == ""){
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
		 * 通过id查询管理员信息
          */

        postAjax("/selectAdminInfoById",null,{
			"adId":${adId}
		},function(data){

            $("input[name='adId']").val(data.adId);
            $("input[name='adPhone']").val(data.adPhone);
            $("input[name='adAddress']").val(data.adAddress);
            $("input[name='adPassword']").val(data.adPassword);
            $("input[name='adName']").val(data.adName);
            $("input[name='adType']").val(data.adType);
		},function(error){
			console.log(error);
		})


    })

</script>