<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="JS/echarts.common.min.js"></script>
<script type="text/javascript" src="JS/jQuery3.2.1.js"></script>

  </head>
  
  <body>
    <a id="zhuaqu" href="zhuaqu.action">页面抓取</a>
    <input type="text" id="name">
    <input id="tupian" type="button" value="显示图形">
    <div id="main" style="width: 2000px; height: 500px;"></div>
    <script type="text/javascript">
    $("#tupian").click(function (){
    var myChart = echarts.init(document.getElementById('main'));
     
     var names=[];
     var nums=[];
     var nums1=[];
     var num2=[];
     var queryvo={"name":$('#name').val()};
    $.ajax({
     data:JSON.stringify(queryvo),
     url:"${pageContext.request.contextPath }/ajaxhuoqu.action",
     contentType:"application/json;charset=UTF-8",
     type:"post",
     dataType:"json",
     success:function(data){
     var vdata=eval(data);
     
     for(var i=0;i<vdata[0].length;i++){
     names.push(vdata[0][i].public_date);
     nums.push(vdata[0][i].price_hight);
     
     }
    for(var i=0;i<vdata[1].length;i++){
     names.push(vdata[1][i].public_date);
     nums1.push(vdata[1][i].price_hight);
    }
     myChart.setOption({
     title:{
     text:'统计图'
      },
    
     toolbox:{
     show:true,
     feature:{
     dataView:{show:true},
     saveAsImage:{
     show:true
     },
     }
     
     },
     legend:{
       data:['2016平均价','2017平均价']
    },
    xAxis:{
    data:names,
    },
    yAxis:{
    
    },
    series:[{
    name:'2016平均价',
    type:'line',
    data:nums,
    },{
    name:'2017平均价',
    type:'line',
    data:nums1,
    }]
    });
     }
    }
    
   
    )});
    </script>
  </body>
</html>
