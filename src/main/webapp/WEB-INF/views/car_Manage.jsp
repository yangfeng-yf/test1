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
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<title>商品管理</title>
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		<script src="js/HUpages.js" type="text/javascript"></script>
		<script src="js/common.js" type="text/javascript"></script>
		<script src="js/utils.js" type="text/javascript"></script>
	</head>

<body id="situation">
<div class="pages-style" >

	<!--右侧内容-->
	<div class="bk-con-message message-section" id="iframe_box">

           <!--编辑内容-->
           <div class="operation mb15">
			   <select class="form-control "style="width: 80px; display:inline" id="sel">
				   <option value="c_id">车牌</option>
				   <option value="c_u_id">所有人编号</option>
				   <option value="c_engine_number">发动机编号</option>
				   <option value="c_tardemark">车辆类型</option>
				   <option value="count_id">入库编号</option>
			   </select>
			    <input type="text" class="icon-icon-test"  id="txtContent">
           		<button class="btn button_btn btn-danger" type="button" onclick="findUserInfo()"><i class="iconfont"></i>&nbsp;查询</button>
           </div>
           <!--列表内容-->
           <div class="page_content clearfix mb15 table-module ">
           	<table class="gallery table table_list table_striped table-bordered " id="">
           		<thead>
		        <tr>
					<th >入库编号</th>
					<th >自行车类型</th>
					<th >品牌</th>
					<th >所属人</th>
					<th >添加时间</th>
					<th >操作</th>
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
		    $(settings.pagecontent).css({height:$(window).height()-$(settings.operation).outerHeight()-pc-(settings.padding*2)})
		    settings.scrollbar($(settings.slide) && $(settings.pagecontent));		    
		}//自定义方法
	});

	$.ajax({
		url:"/listCarInfo",
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
function findUserInfo(){
	var sel = $("#sel").val();
	var txtContent = $("#txtContent").val().trim();
	postAjax("selectCarInfoByParam",null,
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
        var date = data[i].cCreatetime;
		date = new Date(date).Format("yyyy-MM-dd hh:mm:ss");
        var $tr = $("<tr class=\"list-users\">"+
            "<td>"+data[i].countId+"</td>"+
            "<td>"+data[i].cType+"</td>"+
            "<td>"+data[i].cBrand+"</td>"+
            "<td>"+data[i].cPerson+"</td>"+
            "<td>"+date+"</td>"+
            "<td><button type='button' class='btn button_btn bg-deep-gray' onclick='clickDeleteUserInfo(\""+ data[i].countId + "\")' ><i class=\"iconfont\">删除</i></button>"+
            "<button type='button'  class='btn button_btn bg-deep-gray'   onclick='clickUpdateUserInfo(\""+ data[i].countId + "\")'> <i class=\"iconfont\">修改</i></button>"+
            "</tr>");
        var $table = $('table tbody');
        $table.append($tr);
	}
}
function clearChild(){
    $("table tbody").empty();
}
/**
 * 更新车辆信息
 * @param data
 */
function clickUpdateUserInfo(data){
    console.log(data);
	window.location.href="/jumpEditCarInfo?countId="+data;

}

/**
 * 删除车辆信息
 */
function clickDeleteUserInfo(data){
    console.log(data);
    postAjax("/deleteCarInfoById",null,{
        countId:data
	},function(data){
        alert("删除成功！");
		forEachData(data);
	},function(err){
		console.log(err);
	});
}

</script>
