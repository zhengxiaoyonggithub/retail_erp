<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/layui/css/layui.css">
<style type="text/css">
	.layui-tab-item{
		width:100%; 
		height:100%;
		margin:0;
		padding:0;
	}
</style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">layui 后台布局</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <c:forEach var="item" items="${mainMenus}">
			<li class="layui-nav-item"><a href="${item.url}">${item.name}</a></li>
	  </c:forEach>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退了</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test"> 
        <c:forEach var="menu" items="${menus}" varStatus="i">
        		<c:if test="${i.count == 1}">
        			<li class="layui-nav-item layui-nav-itemed">
        		</c:if>
        		<c:if test="${i.count != 1}">
        			<li class="layui-nav-item">
        		</c:if>
          	<a href="javascript:;">${menu.name}</a>
          	<dl class="layui-nav-child">
        		<c:forEach var="item" items="${menu.menus}">
        			<dd><a href="javascript:;" class="menuItem" url="${item.url}">${item.name}</a></dd>
        		</c:forEach>
        		</dl>
        		</li>
        </c:forEach>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
  	<div class="layui-tab" lay-filter="iframeNav" lay-allowclose="true" style="margin:0;height:100%;">
	  <ul class="layui-tab-title">
	    
	  </ul>
	  <div class="layui-tab-content" style="width:100%; height:95%;margin:0;padding:0;">
	
	  </div>
	</div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js""></script>
<script src="${pageContext.request.contextPath }/plugins/layui/layui.all.js"></script>
<script type="text/javascript">
var element = layui.element;

//切换标签
$(".menuItem").click(function(){
	var clickUrl = $(this).attr("url");
	var text = $(this).text();
	var content = '<div class="layui-tab-item layui-show">' +
	'<iframe src="'+clickUrl+'" width="100%" height="100%" frameborder="0"></iframe>'+
	'</div>';
	var totalLen = $(".layui-tab-title").children("li").length;
	var len = $(".layui-tab-title").children("li[lay-id='"+clickUrl+"']").length;
	if(len > 0){
		element.tabChange('iframeNav', clickUrl); 
		return;
	}
	if(totalLen > 5){
		layer.msg("最多开启5个标签页面，请关闭一些在重新打开");
		return;
	}
	element.tabAdd('iframeNav', { title: text ,content: content  ,id: clickUrl });
	element.tabChange('iframeNav', clickUrl); 
});


function showDialog(url,t,closeFuc){
	var iii = 111;
	var index = layer.open({
		  type: 2,
		  content: url,
		  area: ['320px', '195px'],
		  maxmin: true,
		  end:function(){
			  closeFuc(iii);
		  }
	});
}


function erp_alert(msg,t){
	layer.alert(msg);
}

$(function(){
	$(".menuItem").eq(0).parent().addClass("layui-this");
	$(".menuItem").eq(0).click();
})
</script>
</body>
</html>