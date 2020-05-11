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
	<title>配件申请</title>
	<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="js/jquery.cookie.js" type="text/javascript"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="js/HUpages.js" type="text/javascript"></script>
	<script src="js/utils.js" type="text/javascript"></script>
</head>

<%String msg = (String) request.getAttribute("msg"); %>
<body id="pagestyle" class="padding15">
<div class="Bombbox-info Tab-Module" style="width: 600px; margin: auto">
	<ul class="tab_memu box">
		<li class="boxbox-flex2">
			<a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox selected" data-id="0">配件申请</a>
		</li>

	</ul>
	<div class="tab-box tabcontent">
		<div class="tab-conent prompt_style active">
			<form action="/addUseAccessoryiesLogInfo" method="post" id="saveUserInfo">
			<ul class="clearfix add_style">

				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">订单编号</label>
					<div class="cell-right">
						<select class="form-control "style="width: 80px; display:inline" name="cCountid" id="carsel">

						</select>
					</div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">配件名称</label>
					<div class="cell-right">
						<select class="form-control "style="width: 80px; display:inline" name="aId" id="sel">

						</select>
					</div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">数量</label>
					<div class="cell-right"><input name="num" type="text" placeholder="必填" class="col-xs-6 col-lg-12" ></div>
				</li>
			</ul>
			<div class="buttonstyle ">
				<button  type="submit" title="发起申请" class="btn padding10 bg-deep-blue ">发起申请</button>
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
        var aId = $("input[name='aId']").val().trim();
        var num = $("input[name='num']").val().trim();

		if (aId == null || aId == ""
			|| num == null || num == "" ){
		    alert("信息填写不完整");
			return false;
		}
		var re = /^[0-9]*$/
		if (re.test(num)){
			alert("请输入正确的格式");
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
        });
        var msg  = "${msg}";
        if(msg != null && msg != undefined && msg !=""){
			alert(msg);
		}


        postAjax("listMRLogInfo",null,{},
            function (data) {
                console.log("---------------");
                console.log(data);
                for(var cur in data){
                    console.log(cur);
                    $("#carsel").append("<option value='"+data[cur].mId+"'>"+data[cur].mId+"</option>");
                }
            },function(err){
                console.log(err);
            })


        postAjax("listAccessoryInfo",null,{},
			function (data) {
            	console.log("---------------");
				console.log(data);
				for(var cur in data){
				    console.log(cur);
                    $("#sel").append("<option value='"+data[cur].aId+"'>"+data[cur].aName+"</option>");
				}
        },function(err){
				console.log(err);
        })



    })

</script>