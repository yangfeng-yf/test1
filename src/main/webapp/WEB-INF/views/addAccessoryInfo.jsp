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
	<title>添加配件信息</title>
	<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="js/jquery.cookie.js" type="text/javascript"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="js/HUpages.js" type="text/javascript"></script>
	<script src="js/utils.js" type="text/javascript"></script>
</head>

<body id="pagestyle" class="padding15">
<div class="Bombbox-info Tab-Module">
	<ul class="tab_memu box">
		<li class="boxbox-flex2">
			<a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox selected" data-id="0">添加配件信息</a>
		</li>

	</ul>
	<div class="tab-box tabcontent">
		<div class="tab-conent prompt_style active">
			<form action="/addAccessoryInfo" method="post" id="addAccessoryInfo">
			<ul class="clearfix add_style">
				<li class="cell-item">
					<label class="cell-left label_name">配件名称</label>
					<div class="cell-right"><input name="aName" type="text" class=" col-xs-6 col-lg-12" placeholder="必填"   ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name">供货商</label>
					<div class="cell-right">
						<select class="form-control "style="width: 160px; display:inline" name="aSId" id="sel">

						</select>
					</div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name">配件描述</label>
					<div class="cell-right"><input name="aDescribe" type="text" placeholder="必填" class="col-xs-6 col-lg-12" ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name">配件销售价格</label>
					<div class="cell-right"><input name="aSalePrice" type="text" class="col-xs-6 col-lg-12" placeholder="必填" ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name">进货价格</label>
					<div class="cell-right"><input name="aIncomingPrice" type="text" placeholder="必填" class="col-xs-6 col-lg-12" ></div>
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
    addAccessoryInfo.onsubmit = function() {
        //form提交之前先判空
        var aName = $("input[name='aName']").val().trim();
        var adType = $("#sel").find("option:selected").val();
        var aDescribe = $("input[name='aDescribe']").val().trim();
        var aSalePrice = $("input[name='aSalePrice']").val().trim();
        var aIncomingPrice = $("input[name='aIncomingPrice']").val().trim();
        //浮点数的正则
		var re = /^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;


		if (aName == null || aName == ""
			|| adType == null || adType == ""
            || aDescribe == null || aDescribe == ""
            || aSalePrice == null || aSalePrice == ""
            || aIncomingPrice == null || aIncomingPrice == ""){
		    alert("信息填写不完整");
			return false;
		}

		console.log(re.test(aSalePrice));
        if (!re.test(aSalePrice) || !re.test(aIncomingPrice) ){
            alert("请输入正确的参数");
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
        postAjax("listSupplierInfo",null,{},
			function (data) {
            	console.log("---------------");
            	console.log(data);
				for(var cur in data){
                    $("#sel").append("<option value='"+data[cur].sId+"'>"+data[cur].sCampanyname+"</option>");
				}
        },function(err){
				console.log(err);
        })
    })

</script>