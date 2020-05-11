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
					<th >订单编号</th>
					<th >车辆编号</th>
					<th >用户</th>
					<th >接待员</th>
					<th >维修员</th>
					<th >维修状态</th>
					<th >登记时间</th>
					<th >开始时间</th>
					<th >结束时间</th>
					<th >用户备注</th>
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

        var  mRegiterTime = new Date(data[i].mRegiterTime).Format("yyyy-MM-dd hh:mm:ss");
        /*时间判断*/
        var  mStartTime=""
		if(data[i].mStartTime!=null){
            mStartTime = new Date(data[i].mStartTime).Format("yyyy-MM-dd hh:mm:ss");
		}
        var  mEndTime=""
        if(data[i].mEndTime!=null){
            mEndTime = new Date(data[i].mEndTime).Format("yyyy-MM-dd hh:mm:ss");
        }
		/*维修员*/
        var mService=""
		if(data[i].mService!=null){
            mService=data[i].mService
		}
		/*状态*/
        var type = ""
        if(data[i].mStuta == 0){
			type = "已接待"
		}
        if(data[i].mType== 1){
            type = "正在维修"
        }
        if(data[i].mType== 2){
            type = "维修完成"
        }

		var $tr = $("<tr class=\"list-users\">"+
            "<td>"+data[i].mId+"</td>"+
            "<td>"+data[i].mCCountId+"</td>"+
            "<td>"+data[i].mUser+"</td>"+
            "<td>"+data[i].mSalesman+"</td>"+
            "<td>"+mService+"</td>"+
            "<td>"+type+"</td>"+
            "<td>"+mRegiterTime+"</td>"+
            "<td>"+mStartTime+"</td>"+
            "<td>"+mEndTime+"</td>"+
            "<td>"+data[i].mRemark+"</td>"+
           //"<td>" +
			//"<button type='button' class='btn button_btn bg-deep-gray' onclick='clickDeleteUserInfo(\""+ data[i].countId + "\")' ><i class=\"iconfont\">删除</i></button>"+
            //"<button type='button'  class='btn button_btn bg-deep-gray'   onclick='clickUpdateUserInfo(\""+ data[i].countId + "\")'> <i class=\"iconfont\">修改</i></button>"+
            "</td></tr>");
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
