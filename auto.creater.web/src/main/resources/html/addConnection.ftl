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
		<form class="layui-form" action="${rc.contextPath}/db/connection/add.htm"
			method="post">
			<div class="layui-form-item" style="margin-top: 10px;">
				<label class="layui-form-label">名称*</label>
				<div class="layui-input-inline">
					<input type="text" name="name" placeholder="名称" class="layui-input"
						required lay-verify="required" placeholder="请输入名称"
						autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">IP与端口*</label>
				<div class="layui-input-inline">
					<input type="text" name="host" placeholder="IP地址" class="layui-input"
						required lay-verify="required" placeholder="请输入IP地址"
						autocomplete="off">
				</div>
				<div class="layui-input-inline" style="width: 128px;">
					<input type="text" name="port" placeholder="端口号"
						class="layui-input" required lay-verify="required"
						placeholder="请输入端口号" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户名*</label>
				<div class="layui-input-inline">
					<input type="text" name="userName" placeholder="用户名"
						class="layui-input" required lay-verify="required"
						placeholder="请输入用户名" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码*</label>
				<div class="layui-input-inline">
					<input type="text" name="passwd" placeholder="密码"
						class="layui-input" required lay-verify="required"
						placeholder="请输入密码" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit=""
						lay-filter="demo1">确定</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
</body>
</html>
