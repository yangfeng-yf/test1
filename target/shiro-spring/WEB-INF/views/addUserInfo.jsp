<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
	<title>个人信息</title>
	<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="js/jquery.cookie.js" type="text/javascript"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="js/HUpages.js" type="text/javascript"></script>
</head>

<body id="pagestyle" class="padding15"<%-- style="background-color: #d5e3ef"--%>>
<div class="Bombbox-info Tab-Module" style="width: 800px;margin: auto;margin-top: 30px">
	<ul class="tab_memu box"  >
		<li class="boxbox-flex2">
			<a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox selected" data-id="0" style="background-color: #33CCCC">信息登记</a>
		</li>

	</ul>
	<div class="tab-box tabcontent">
		<div class="tab-conent prompt_style active" style="margin-left: 120px">
			<form action="/saveUserInfo" method="post" id="saveUserInfo">
			<ul class="clearfix add_style">
				<li class="cell-item" style="padding-top: 20px">
					<label class="cell-left label_name" style="color: #2A2A2A;font-size: 14px">姓名</label>
					<div class="cell-right" ><input name="uName" type="text" class=" col-xs-6 col-lg-12" placeholder="必填" style="width: 400px" ></div>
				</li>
				<li class="cell-item" style="padding-top: 15px">
					<label class="cell-left label_name" style="color: #2A2A2A;font-size: 14px">联系方式</label>
					<div class="cell-right"><input name="uPhone" type="text" class="col-xs-6 col-lg-12" placeholder="必填" style="width: 400px" ></div>
				</li>
				<li class="cell-item" style="padding-top: 15px">
					<label class="cell-left label_name" style="color: #2A2A2A;font-size: 14px">地址</label>
					<div class="cell-right"><input name="uAddress" type="text" class="col-xs-6 col-lg-12" placeholder="必填" style="width: 400px"></div>
				</li>
				<li class="cell-item" style="padding-top: 15px">
					<label class="cell-left label_name" style="color: #2A2A2A;font-size: 14px">自行车类型</label>
					<div class="cell-right"><input name="cType" type="text" placeholder="自行车类型" class="col-xs-6 col-lg-12" style="width: 400px"></div>
				</li>
				<li class="cell-item" style="padding-top: 15px">
					<label class="cell-left label_name" style="color: #2A2A2A;font-size: 14px">自行车品牌</label>
					<div class="cell-right"><input name="cBrand" type="text" placeholder="必填" class="col-xs-6 col-lg-12" style="width: 400px"></div>
				</li>
				<li class="cell-item" style="padding-top: 15px">
					<label class="cell-left label_name" style="color: #2A2A2A;font-size: 14px">问题描述</label>
					<div class="cell-right"><input name="mRemark" type="text" class="col-xs-6 col-lg-12" style="width: 400px"></div>
				</li>
			</ul>
			<div class="buttonstyle "  >
				<button  type="submit" title="保存添加" class="btn padding10 bg-deep-blue "style="margin-left: -100px">保存添加</button>
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
//        var uPassword = $("input[name='uPassword']").val().trim();
//        var uRePassword = $("input[name='uRePassword']").val().trim();
        var cId = $("input[name='cId']").val().trim();
        var cTardemark = $("input[name='cTardemark']").val().trim();
        var cEngineNumber = $("input[name='cEngineNumber']").val().trim();
        var mRemark = $("input[name='mRemark']").val().trim();

		if (uName == null || uName == ""
			|| uPhone == null || uPhone == ""
            || uAddress == null || uAddress == ""
//            || uPassword == null || uPassword == ""
//            || uRePassword == null || uRePassword == ""
            || cId == null || cId == ""
            || cTardemark == null || cTardemark == ""
            || cEngineNumber == null || cEngineNumber == ""
            || mRemark == null || mRemark == ""){
		    alert("信息填写不完整");
			return false;
		}
		var re = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;

		if(!re.test(uPhone)){
			alert("请输入正确的电话号码");
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
    })

</script>