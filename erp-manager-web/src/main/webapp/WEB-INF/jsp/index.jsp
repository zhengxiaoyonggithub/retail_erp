<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">layui 后台布局</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <!-- <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li> -->
       
      <c:foreach var="item" items="mainMenus">
			<li class="layui-nav-item"><a href="">${item.name}</a></li>
	  </c:foreach>
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
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">所有商品</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="javascript:;">列表三</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">解决方案</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;">云市场</a></li>
        <li class="layui-nav-item"><a href="javascript:;">发布商品</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
  	<div class="layui-tab" lay-filter="demo" lay-allowclose="true" style="margin:0;height:100%;">
	  <ul class="layui-tab-title">
	    <li class="layui-this" lay-id="11">网站设置</li>
	    <li lay-id="22">用户管理</li>
	    <li lay-id="33">权限分配</li>
	    <li lay-id="44">商品管理</li>
	    <li lay-id="55">订单管理</li>
	  </ul>
	  <div class="layui-tab-content" style="width:100%; height:95%;margin:0;padding:0;">
	    <div class="layui-tab-item layui-show" style="width:100%; height:100%;margin:0;padding:0;">
			<iframe src="${pageContext.request.contextPath }/productList.jsp" width="100%" height="100%" frameborder="0"></iframe>
		</div>
	    <div class="layui-tab-item">内容2</div>
	    <div class="layui-tab-item">内容3</div>
	    <div class="layui-tab-item">内容4</div>
	    <div class="layui-tab-item">内容5</div>
	  </div>
	</div>
  </div>

</div>
<script src="${pageContext.request.contextPath }/plugins/layui/layui.all.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
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
</script>
</body>
</html>