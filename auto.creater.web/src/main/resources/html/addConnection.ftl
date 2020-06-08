<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加配置</title>
<link rel="stylesheet" href="${rc.contextPath}/layui/css/layui.css"
	media="all" />
</head>
<body class="layui-layout-body">

	<!-- 内容主体区域 -->
	<div class="layui-container">
		<form class="layui-form" action="${rc.contextPath}/db/add.htm"
			method="post">
			<div class="layui-form-item" style="margin-top: 10px;">
				<label class="layui-form-label">名称*</label>
				<div class="layui-input-inline">
					<input type="text" name="name" id="name" placeholder="名称"
						class="layui-input" required lay-verify="required"
						placeholder="请输入名称" value="${connection.name}" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">IP与端口*</label>
				<div class="layui-input-inline">
					<input type="text" name="host" id="host" placeholder="IP地址"
						class="layui-input" required lay-verify="required"
						placeholder="请输入IP地址" value="${connection.host}"
						autocomplete="off">
				</div>
				<div class="layui-input-inline" style="width: 128px;">
					<input type="text" name="port" id="port" placeholder="端口号"
						value="${connection.port}" class="layui-input" required
						lay-verify="required" placeholder="请输入端口号" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户名*</label>
				<div class="layui-input-inline">
					<input type="text" name="userName" id="userName" placeholder="用户名"
						value="${connection.userName}" class="layui-input" required
						lay-verify="required" placeholder="请输入用户名" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码*</label>
				<div class="layui-input-inline">
					<input type="text" name="passwd" id="passwd" placeholder="密码"
						value="${connection.passwd}" class="layui-input" required
						lay-verify="required" placeholder="请输入密码" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit="">确定</button>
					<button type="button" class="layui-btn layui-btn-primary" id="test">测试</button>
					&nbsp;&nbsp;<font color="#FF5722">${error}</font><font
						color="#5FB878">${info}</font>
				</div>
			</div>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
	<script type="text/javascript">
		$('#test').on('click', function() {
		var	host=$("#host").val();
			var  port=$("#port").val();
			 var userName=$("#userName").val();
			 var passwd=$("#passwd").val();
			if(host==""||port==""||userName==""||passwd==""){
				layer.alert("请填写配置信息",{icon: 2, title:'测试失败'});
			}else{
				$.ajax({
					url:"${rc.contextPath}/db/test.htm",
					type:'post',
					data:{host:host,
						  port:port,
						  userName:userName,
						  passwd:passwd
						 },
					success:function(data){
						if(data.result=='SUCCESS'){
							layer.alert(data.message,{icon: 1, title:'测试成功'});
						}else{
							layer.alert(data.message,{icon: 2, title:'测试失败'});
						}
						
					}
				})
			}
		});
	</script>
</body>
</html>
