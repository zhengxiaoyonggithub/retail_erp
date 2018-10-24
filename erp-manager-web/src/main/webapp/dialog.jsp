<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/plugins/layui/css/layui.css">
<script src="${pageContext.request.contextPath }/plugins/layui/layui.all.js" charset="utf-8"></script>
</head>
<body>
	<table>
		<tr>
			<td>用户：</td>
			<td><input type="input" name="username" id="username"></td>
		</tr>
		<tr>
			<td><button class="layui-btn layui-btn-normal" id="closeBtn"
					onclick="closeWindow()">百搭按钮</button></td>
			<td></td>
		</tr>
	</table>
</body>
</html>

<script type="text/javascript">
	//注意：parent 是 JS 自带的全局对象，可用于操作父页面
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function closeWindow() {
		//给父页面传值
		alert(parent);
		parent.layer.close(index);
	}
</script>