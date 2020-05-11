<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
		<title>系统框架首页</title>
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/jquery.cookie.js" type="text/javascript"></script>
		<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
		<script src="js/HUpages.js" type="text/javascript"></script>
		<script src="js/dist/echarts.common.min.js"></script>
		<script SRC="js/utils.js"></script>
			<!--[if lt IE 9]>
          <script src="js/html5shiv.js" type="text/javascript"></script>
          <script src="js/css3-mediaqueries.js"  type="text/javascript"></script>

        <![endif]-->
	</head>

	<body>
	<div  style="width: 100%; height: 100%">
		<div >
			<div id="box1" style="width: 50%;height:50%; float: left;"></div>
			<div id="box2" style="width: 50%;height:50%; float: left;" ></div>
		</div>
		<div>
			<div id="box3" style="width: 50%;height:50%; float: left;"></div>
			<div id="box4" style="width: 50%;height:50%; float: left;" ></div>
		</div>
	</div>
	</body>
</html>
<script src="js/dist/echarts.js" type="text/javascript"></script>
<script>
	//内页框架
	$(function() {
		$("#situation").Hupage({
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
			expand: function(thisBox, settings) {
				settings.scrollbar(thisBox);//设置当前页滚动样式
			}
		});

		postAjax("findMRLogCount",null,{},function(data){
		    console.log(data);
			var piedata = [];
            setPieData(data);
		},function(err){
			console.log(err);
		});

        postAjax("listAccessoryInfo",null,{},function(data){
            console.log(data);
            var piedata = [];
            setZhu(data);
        },function(err){
            console.log(err);
        });

        postAjax("findAllTardemarkInfo",null,{},function(data){
            console.log(data);
            var piedata = [];
            setZhu2(data);
        },function(err){
            console.log(err);
        });

        postAjax("findAllUserAddressInfo",null,{},function(data){
            console.log(data);
            var piedata = [];
            setPie2(data);
        },function(err){
            console.log(err);
        });
	})

    function setPieData(data){
        // 基于准备好的dom，初始化echarts实例
        var box1 = echarts.init(document.getElementById('box1'));
        var nameData = []
        for(var i = 0;i <data.length ;  i++){
            if (data[i].name == null ){
                data[i].name = "刚接待";
            }
            if (data[i].name == 1){
                data[i].name = "进厂";
			}
            if (data[i].name == 2){
                data[i].name = "出厂";
            }
			nameData[i] = data.name;
		}
        var option = {

            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data:nameData
            },
            series: [
                {
                    name:'进出场',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:data
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        box1.setOption(option);
    }

    function setZhu(data){
        // 基于准备好的dom，初始化echarts实例
        var box1 = echarts.init(document.getElementById('box2'));
        // 指定图表的配置项和数据
		var dataName = [];
		var dataNum = [];
        for(var i = 0; i < data.length ; i++){
			dataName[i] = data[i].aName;
            dataNum[i] = data[i].aNum;
        }

        option = {
            title: {
                text: '配件剩余数量'
            },
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : dataName,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'剩余数量',
                    type:'bar',
                    barWidth: '60%',
                    data:dataNum
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        box1.setOption(option);
    }

    function setZhu2(data){
        // 基于准备好的dom，初始化echarts实例
        var box3 = echarts.init(document.getElementById('box3'));
        // 指定图表的配置项和数据
        var dataName = [];
        var dataNum = [];
        for(var i = 0; i < data.length ; i++){
            dataName[i] = data[i].tardemark;
            dataNum[i] = data[i].num;
        }

        option = {
            title: {
                text: '车辆维修品牌统计'
            },
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : dataName,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'剩余',
                    type:'bar',
                    barWidth: '60%',
                    data:dataNum
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        box3.setOption(option);
    }


    function setPie2(data){
        var box4 = echarts.init(document.getElementById('box4'));
        option = {
            backgroundColor: '#2c343c',
            title: {
                text: '车主各省份分布',
                left: 'center',
                top: 20,
                textStyle: {
                    color: '#ccc'
                }
            },

            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },

            visualMap: {
                show: false,
                min: 80,
                max: 600,
                inRange: {
                    colorLightness: [0, 1]
                }
            },
            series : [
                {
                    name:'省份',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:
                       data
                    .sort(function (a, b) { return a.value - b.value; }),
                    roseType: 'radius',
                    label: {
                        normal: {
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            },
                            smooth: 0.2,
                            length: 10,
                            length2: 20
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#c23531',
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    },

                    animationType: 'scale',
                    animationEasing: 'elasticOut',
                    animationDelay: function (idx) {
                        return Math.random() * 200;
                    }
                }
            ]
        };
        box4.setOption(option);
    }
</script>
<script>



</script>