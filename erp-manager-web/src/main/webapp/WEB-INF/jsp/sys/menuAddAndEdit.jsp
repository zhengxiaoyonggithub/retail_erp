<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/layui/css/layui.css">

<style type="text/css">
	.layui-form-label{
		width:100px;
	}
</style>
</head>
<body>
<form class="layui-form" action="" style="margin:10px 0px 0px 0px;">
  <div class="layui-form-item">
    <label class="layui-form-label">菜单名称</label>
    <div class="layui-input-inline">
      <input type="text" name="name" lay-verify="title" autocomplete="off" placeholder="菜单名称" value="${menu.name}" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">URL</label>
    <div class="layui-input-inline">
      <input type="text" name="url" lay-verify="title" autocomplete="off" placeholder="URL" value="${menu.url}" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">选择上级菜单</label>
      <div class="layui-input-inline">
        <select id="parentMenu" name="parentId" lay-verify="required" lay-search="">
        		<option value=""></option>
        </select>
      </div>
    </div>
  </div>
  <div class="layui-form-item">
	  <div class="layui-inline">
	      <label class="layui-form-label">创建时间</label>
	      <div class="layui-input-inline">
	        <input type="text" class="layui-input" name="createTime" id="createTime" value="<fmt:formatDate value="${menu.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd HH:mm:ss">
	      </div>
	    </div>
   </div>
   <div class="layui-form-item">
    <label class="layui-form-label">菜单状态</label>
    <div class="layui-input-block">
    			<c:choose>
			   <c:when test="${menu.status == 0}">  
			        <input type="radio" name="status" value="0" title="启用" checked="">
			        <input type="radio" name="status" value="-1" title="禁用">
			   </c:when>
			   <c:otherwise> 
			    		 <input type="radio" name="status" value="0" title="启用">
			        <input type="radio" name="status" value="-1" title="禁用" checked="">
			   </c:otherwise>
			</c:choose>
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button type="button" class="layui-btn" onclick="submitForm()">立即提交</button>
      <button type="button" onclick="closeWindow()" class="layui-btn">取消</button>
    </div>
  </div>
</form>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js""></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugins/layui/layui.all.js" charset="utf-8"></script>
<script type="text/javascript">
	function renderForm(){
	  layui.use('form', function(){
	   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
	   form.render();
	  });
	 }
	var laydate = layui.laydate;
	 //日期时间选择器
	  laydate.render({
	    elem: '#createTime'
	    ,type: 'datetime'
	  });
	$(function(){
		$.get("/sys/menu/getMenuSelect",{},function(data){
			for(var i = 0; i < data.length; i++){
				if(data[i].id == ${menu.id}){
					$("#parentMenu").append('<option value="'+data[i].id+'" selected="selected">'+data[i].name+'</option>');
				}else{
					$("#parentMenu").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
				}
			}
			renderForm();
		});
	});


	//注意：parent 是 JS 自带的全局对象，可用于操作父页面
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function closeWindow() {
		parent.layer.close(index);
	}
	 
	function submitForm(){
		var paras = {};
		paras.id = ${menu.id};
		paras.name = $("input[name='name']").val();
		paras.url = $("input[name='url']").val();
		paras.parentId = $("#parentMenu").val();
		if(paras.parentId == ""){
			paras.parentId = 0;
		}
		paras.createTime = $("#createTime").val();
		paras.status = $("input[name='status']:checked").val();
		
		console.log(paras);
		$.post("/sys/menu/edit",paras,function(data){
			layer.msg(data.message);
			closeWindow();
		});
	}
</script>