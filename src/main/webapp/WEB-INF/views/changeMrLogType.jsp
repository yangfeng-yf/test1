<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
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
		<title>维修状态</title>
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
		<script src="js/HUpages.js" type="text/javascript"></script>
		<script src="js/common.js" type="text/javascript"></script>
		<script src="js/utils.js" type="text/javascript"></script>
	</head>
	<% String msg = (String)request.getAttribute("msg");%>
<body id="situation">
<div class="pages-style" >

	<!--右侧内容-->
	<div class="bk-con-message message-section" id="iframe_box">

           <!--编辑内容-->
           <div class="operation mb15">
			   <select class="form-control "style="width: 80px; display:inline" id="sel">
				   <option value="m_c_count_id">车辆编号</option>
				   <option value="m_u_id">用户编号</option>
				<shiro:authenticated><option value="m_salesman_id">接待人员编号</option></shiro:authenticated>
				   <option value="m_maintenanceman_id">维修人员编号</option>
			   </select>
			    <input type="text" class="icon-icon-test"  id="txtContent">
           		<button class="btn button_btn btn-danger" type="button" onclick="findUserInfo()"><i class="iconfont"></i>&nbsp;查询</button>
           </div>
           <!--列表内容-->
           <div class="page_content clearfix mb15 table-module ">
           	<table class="gallery table table_list table_striped table-bordered " id="">
           		<thead>
		        <tr>
					<th >流水信息</th>
					<th >车辆编号</th>
					<th >用户编号</th>
					<th >销售员id(接待员)</th>
					<th >维修登记时间</th>
					<th >维修人员编号</th>
					<th >进入工厂时间</th>
					<th >离开工厂时间</th>
					<th >维修状态</th>
					<th >用户备注</th>
					<th>操作</th>
		       </tr>
		       </thead>
		       <tbody>

		       </tbody>
           	</table>
           </div>
    </div>
  </div>
 </body>
</html>
<script>
$(function(){
	//内页框架结构编辑
	$("#situation").Hupage({
		slide: '#breadcrumb',
		padding:15,
		menuModule:'#bk-con-menu', //菜单模块
		pagecontent:'.page_content',//自定义属性
		operation:'.operation',//自定义属性
		scrollbar:function(e){
			e.niceScroll({
				cursorcolor: "#dddddd",
				cursoropacitymax: 1,
				touchbehavior: false,
				cursorwidth: "3px",
				cursorborder: "0",
				cursorborderradius: "3px",
			});						
		},
		expand:function(thisBox,settings){	
			var pc="";
			$(settings.pagecontent).css("margin-bottom")!=null? pc=parseInt($(settings.pagecontent).css("margin-bottom").replace("px","")):'';
		    $(settings.pagecontent).css({height:$(window).height()-$(settings.operation).outerHeight()-pc-(settings.padding)})
		    settings.scrollbar($(settings.slide) && $(settings.pagecontent));		    
		}//自定义方法
	});
	var msg = "${msg}";
	if (msg!=null && msg != undefined && msg!=""){
	    alert(msg);
	}

	$.ajax({
		url:"/listMRLogInfo",
		type: "post",
		dataType:"json",
		data:{},
		error:function(error){
			console.log(error);
		},
		success:function(data){
			if (data != null){
                forEachData(data);
            }else{
			    alert("请求发生错误");
			}
		}
	})
 });
/**
 * 按条件查询
 * */
function findUserInfo(){
	var sel = $("#sel").val();
	var txtContent = $("#txtContent").val().trim();
	postAjax("selectMrlogInfoByParam",null,
		{
		    "param":sel,
			"value":txtContent
		},
		function(data){
			if (data==null){
				console.log("数据源为空");
			}
	    	forEachData(data);
		},function (err) {
			console.log(err);
        });
}

function forEachData(data){
    clearChild();
    for (var i=0; i< data.length ; i++){
		var str = "";

		console.log("data[i].countId"+data[i].countId);
        var  mRegistrationTime = new Date(data[i].mRegistrationTime).Format("yyyy-MM-dd hh:mm:ss");
        var  mEnterfactoryTime = new Date(data[i].mEnterfactoryTime).Format("yyyy-MM-dd hh:mm:ss");
        var  mLeavefactoryTime = new Date(data[i].mLeavefactoryTime).Format("yyyy-MM-dd hh:mm:ss");
		var $tr = $("<tr class=\"list-users\">"+
            "<td>"+data[i].mId+"</td>"+
            "<td>"+data[i].mCCountId+"</td>"+
            "<td>"+data[i].mUId+"</td>"+
            "<td>"+data[i].mSalesmanId+"</td>"+
            "<td>"+mRegistrationTime+"</td>"+
            "<td>"+data[i].mMaintenancemanId+"</td>"+
            "<td>"+mEnterfactoryTime+"</td>"+
            "<td>"+mLeavefactoryTime+"</td>"+
            "<td>"+data[i].mType+"</td>"+
            "<td>"+data[i].mRemark+"</td>"+
           "<td>" + "<button type='button' class='btn button_btn bg-deep-gray' onclick='inChangeMrLogType(\""+ data[i].mId + "\")' ><i class=\"iconfont\">入厂</i></button>"+
            "<button type='button'  class='btn button_btn bg-deep-gray'   onclick='outChangeMrLogType(\""+ data[i].mId + "\")'> <i class=\"iconfont\">出厂</i></button>"+
		  "</td></tr>");
        var $table = $('table tbody');
        $table.append($tr);
	}
}
function clearChild(){
    $("table tbody").empty();
}
/**
 * 出厂
 * @param data
 */
function outChangeMrLogType(data){
    console.log(data);
	window.location.href="/editMRLogTypeStatus?countId="+data+"&status="+2;

}

/**
 * 入厂
 */
function inChangeMrLogType(data){
	window.location.href="/editMRLogTypeStatus?countId="+data+"&status="+1;
}

</script>
