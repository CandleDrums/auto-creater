<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" href="${rc.contextPath}/layui/css/layui.css"
	media="all" />
<link rel="stylesheet" href="${rc.contextPath}/layui/css/admin.css"
	media="all" />
<script src="${rc.contextPath}/js/jquery-3.5.1.min.js"></script>
</head>
<body>
	<div class="layui-container" style="width: 800px;">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>填写参数</legend>
		</fieldset>
		<form class="layui-form" action="${rc.contextPath}/app/index.htm"
			method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">项目名</label>
				<div class="layui-input-inline">
					<input type="text" name="projectName" id="projectName"
						lay-verify="title" autocomplete="off" placeholder="填写项目名称"
						class="layui-input" value="${projectName}">
				</div>
				<div class="layui-form-mid layui-word-aux">*必填</div>
			</div>
		<div class="layui-form-item">
			<label class="layui-form-label">基础包名</label>
			<div class="layui-input-inline">
				<input type="text" name="packageName" id="packageName"
					lay-verify="title" autocomplete="off" placeholder="必须填写"
					class="layui-input" value="${packageName}">
			</div>
			<div class="layui-form-mid layui-word-aux">*必填，默认为:com.cds</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">作者</label>
			<div class="layui-input-inline">
				<input type="text" name="author" id="author" lay-verify="title"
					autocomplete="off" placeholder="必须填写" class="layui-input"
					value="${author}">
			</div>
			<div class="layui-form-mid layui-word-aux">*必填</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">生成路径</label>
			<div class="layui-input-inline" style="width: 400px">
				<input type="text" name="outputPath" id="outputPath"
					lay-verify="title" autocomplete="off" placeholder="自定义生成路径"
					class="layui-input" value="${outputPath}">
			</div>
			<div class="layui-form-mid layui-word-aux">*必填</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">端口号</label>
			<div class="layui-input-inline">
				<input type="text" name="port" id="port" lay-verify="number"
					autocomplete="off" placeholder="必须填写" class="layui-input"
					value="80">
			</div>
			<div class="layui-form-mid layui-word-aux">*必填</div>
		</div>
			<div class="layui-form-item">
				<label class="layui-form-label">类型</label>
				<div class="layui-input-block">
					<input type="radio" name="type" value="REST" title="REST">
					<input type="radio" name="type" value="WEB" title="WEB" checked>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" id="projectCreate" class="layui-btn"
						lay-submit="">
						<i class="layui-icon" style="font-size: 20px;">&#xe609;</i> 创建
					</button>
				</div>
			</div>
		</form>
	</div>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
</body>
</html>