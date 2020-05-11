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
	<title>修改</title>
	<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="js/jquery.cookie.js" type="text/javascript"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="js/HUpages.js" type="text/javascript"></script>
	<script src="js/utils.js" type="text/javascript"></script>
</head>
<% String aId = (String)request.getAttribute("aId");%>

<body id="pagestyle" class="padding15">
<div class="Bombbox-info Tab-Module" style="width: 600px; margin: auto">
	<ul class="tab_memu box">
		<li class="boxbox-flex2">
			<a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox selected" data-id="0">修改配件信息</a>
		</li>

	</ul>
	<div class="tab-box tabcontent">
		<div class="tab-conent prompt_style active">
			<form action="/editAccessoryInfo" method="post" id="saveUserInfo">
			<ul class="clearfix add_style">
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">配件编号</label>
					<div class="cell-right"><input name="aId" type="text" readonly="readonly"  class=" col-xs-6 col-lg-12" placeholder="必填" ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">配件名称</label>
					<div class="cell-right"><input name="aName" type="text" class=" col-xs-6 col-lg-12" placeholder="必填"   ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">配件数量</label>
					<div class="cell-right"><input name="aNum" type="text" readonly="readonly" class="col-xs-6 col-lg-12" placeholder="必填"  ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">供货商</label>
					<div class="cell-right">
						<select class="form-control "style="width: 160px; display:inline" name="aSId" id="sel">

						</select>
					</div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">配件描述</label>
					<div class="cell-right"><input name="aDescribe" type="text" class="col-xs-6 col-lg-12" placeholder="必填" ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">最后供货时间</label>
					<div class="cell-right"><input name="aLastSupplyTime" type="text" readonly="readonly" class="col-xs-6 col-lg-12" placeholder="必填" ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">配件销售价格</label>
					<div class="cell-right"><input name="aSalePrice" type="text" class="col-xs-6 col-lg-12" placeholder="必填" ></div>
				</li>
				<li class="cell-item">
					<label class="cell-left label_name" style="color: #2A2A2A; font-size: 14px">进货价格</label>
					<div class="cell-right"><input name="aIncomingPrice" type="text" class="col-xs-6 col-lg-12" placeholder="必填" ></div>
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
        //form提交之前先判空 aDescribe aLastSupplyTime
        var aId = $("input[name='aId']").val().trim();
        var aName = $("input[name='aName']").val().trim();
        var aNum = $("input[name='aNum']").val().trim();
        var aSId = $("#sel").find("option:selected").val();
        var aDescribe = $("input[name='aDescribe']").val().trim();
        var aLastSupplyTime = $("input[name='aLastSupplyTime']").val().trim();
        var aSalePrice = $("input[name='aSalePrice']").val().trim();
        var aIncomingPrice = $("input[name='aIncomingPrice']").val().trim();
		if (aName == null || aName == ""
			|| aId == null || aId == ""
            || aNum == null || aNum == ""
            || aSId == null || aSId == ""
            || aDescribe == null || aDescribe == ""
            || aLastSupplyTime == null || aLastSupplyTime == ""
            || aSalePrice == null || aSalePrice == ""
            || aIncomingPrice == null || aIncomingPrice == ""
        ){
		    alert("信息填写不完整");
			return false;
		}
         var re = /^[0-9]*$/;
        if (!re.test(aSalePrice) || !re.test(aIncomingPrice)){
			alert("请填写正确的数据格式！");
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
		 * 通过id查询配件信息
          */
        postAjax("/selectAccessoryInfoById",null,{
			"aId":${aId}
		},function(data){

			$("input[name='aId']").val(data.aId);
            $("input[name='aName']").val(data.aName);
            $("input[name='aNum']").val(data.aNum);
            $("input[name='aDescribe']").val(data.aDescribe);
            $("input[name='aLastSupplyTime']").val(date = new Date(data.aLastSupplyTime).Format("yyyy-MM-dd hh:mm:ss"));
            $("input[name='aSalePrice']").val(data.aSalePrice);
            $("input[name='aIncomingPrice']").val(data.aIncomingPrice);
//            console.log("aSID:"+data.aSId);
//            $("#aSId").val(data.aSId);
			var  nowASId = data.aSId;
            //这里再次调用使得
            postAjax("listSupplierInfo",null,{},
                function (data) {
                    console.log("---------------");
                    //console.log(data);
                    for(var cur in data){
                        console.log(nowASId + "=======" + data[cur].sId);
                        if (nowASId == data[cur].sId ){
                            $("#sel").append("<option value='"+data[cur].sId+"' selected=selected>"+data[cur].sCampanyname+"</option>");
						}else{
                            $("#sel").append("<option value='"+data[cur].sId+"'>"+data[cur].sCampanyname+"</option>");
						}

                    }
                },function(err){
                    console.log(err);
                })


		},function(error){
			console.log(error);
		})



    })

</script>