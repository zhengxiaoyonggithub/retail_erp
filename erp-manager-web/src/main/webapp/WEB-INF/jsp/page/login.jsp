<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理-登陆</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
</head>
<body>
<div id="container">
    <div></div>
    <div class="admin-login-background">
            <div>
                <i class="layui-icon layui-icon-username admin-icon admin-icon-username"></i>
                <input type="text" name="username" placeholder="请输入用户名"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-username" value="zhengxiaoyong" />
            </div>
            <div>
                <i class="layui-icon layui-icon-password admin-icon admin-icon-password"></i>
                <input type="password" name="password"
                       placeholder="请输入密码"
                       autocomplete="off"
                       class="layui-input admin-input" value="123456" />
            </div>
            <div>
                <input type="text" name="verify"
                       placeholder="请输入验证码"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-verify">
                <img class="admin-captcha" src="${pageContext.request.contextPath }/page/captcha"
                     onclick="updateVerify()">
            </div>
            <button class="layui-btn admin-button" lay-submit lay-filter="formDemo">登陆</button>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js""></script>

<script src="${pageContext.request.contextPath }/plugins/layui/layui.all.js"></script>

<script type="text/javascript">
	$(".admin-button").click(function(){
		 var username = $("input[name='username']").val();
		 var password = $("input[name='password']").val();
		 var verify = $("input[name='verify']").val();
		 
		 var url = "${redirect}";
		 $.post("/page/login",{username:username,password:password,verify:verify },function(data){
			 if(data.status == 200){
				 if(url != ""){
					 window.location.href=url;
				 }else{
					 window.location.href="/";
				 } 
			 }else{
				 layer.msg(data.message);
			 }
				 
		 });
	});
</script>

</body>
</html>