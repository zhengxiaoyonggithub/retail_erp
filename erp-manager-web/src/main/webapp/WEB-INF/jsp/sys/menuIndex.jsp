<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/layui/css/layui.css" media="all">
<style type="text/css">
	.layui-form-checkbox{ top :0; }
</style>
</head>
<body>

	<div class="layui-form-item" style="margin:10px 0px 10px 0px;">
		<div class="layui-inline">
			<label class="layui-form-label">菜单名称</label>
			<div class="layui-input-block" style="width: 200px;">
				<input type="text" name="name" required lay-verify="required"
					placeholder="菜单名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<button class="layui-btn layui-btn-normal" id="menuSerach">查询</button>
		</div>
	</div>
	<table class="layui-hide" id="menuTable" lay-filter="menuTable"></table>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js""></script>
	<script src="${pageContext.request.contextPath }/plugins/layui/layui.all.js" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath }/js/util/dateFormat.js" charset="utf-8"></script>
	<script type="text/javascript">
		//window.parent.showDialog("${pageContext.request.contextPath}/dialog.jsp",this,function(callback){ alert(callback) });

		var table = layui.table;
		var tableIns = table.render({
			elem : '#menuTable',
			url : '/sys/menus/getDatas',
			cols : [ [ 
				{ type : 'checkbox',fixed:'left', checkbox:true ,style : ''},
				{ title : '操作' ,templet : '#operation' , width : 120 }, 
				{ field : 'id', width : 80, title : 'ID', sort : true }, 
				{ field : 'name', width : 120, title : '菜单名称' }, 
				{ field : 'url', width : 200, title : '链接' }, 
				{ field : 'createTime', width : 200, title : '创建时间', templet : '#createTime' }, 
				{ field : 'updateTime', title : '修改时间', width : 200, templet : '#updateTime'}, 
				{ field : 'status', width : 80, title : '状态', templet : '#status' } 
				] ],
			page : true,
			done:function(){
				//解决checkbox 样式问题
				$(".layui-form-checkbox").css("top",0);
			}
		});
		
		//查询
		$("#menuSerach").click(function(){
			reloadDataGrid();
		});
		
		
		//监听行工具事件
		  table.on('tool(menuTable)', function(obj){
		    var data = obj.data;
		    //console.log(obj)
		    if(obj.event === 'del'){
		      layer.confirm('真的删除行么', function(index){
		    	  	$.get("/sys/menus/del",{ id : data.id },function(data){
		    	  		if(data.status == 200){
		    	  			reloadDataGrid();
		    	  		}else{
		    	  			layer.msg(data.msg);
		    	  		}
		    	  	});
		    	  	layer.close(index);
		      });
		    } else if(obj.event === 'edit'){
		    		var option = {
		    				  type: 2,
		    				  title: "编辑菜单",
		    				  content: "${pageContext.request.contextPath}/sys/menu/showEdit?id="+data.id,
		    				  area: ['500px', '500px'],
		    				  maxmin: true,
		    				  end:function(){
		    					  reloadDataGrid();
		    				  }
		    			};
		    		window.parent.showDialog(option,this,function(callback){ alert(callback) });
		    }
		  });
		
		//刷新表格
		function reloadDataGrid(){
			tableIns.reload({ where: {  name: $("input[name='name']").val() } });
		}
	</script>

<script type="text/javascript">
	function del(id){
		layer.msg(id);
	}
	
	function edit(id){
		layer.msg(id);
	}
</script>

<script type="text/html" id="operation">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="createTime">
	{{dateFormat(d.createTime)}}
</script>
	<script type="text/html" id="updateTime">
	{{dateFormat(d.updateTime)}}
</script>
	<script type="text/html" id="status">
	{{#  if(d.status === 0){ }}
    		启用
  	{{#  } else { }}
 		失效
	{{#  } }}  
</script>
</body>
</html>