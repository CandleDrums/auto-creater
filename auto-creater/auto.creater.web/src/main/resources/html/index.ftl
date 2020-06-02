<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" href="${rc.contextPath}/layui/css/layui.css"
	media="all" />
<script type="text/javascript">
	function check() {
		var myselect=document.getElementById("tableSelect");
		var index=myselect.selectedIndex ; 
		var value=myselect.options[index].value;
		if(value==""){
			return false;
		}
		
		var values=value.split(",");
		document.getElementById("dbName").value=values[0];
		document.getElementById("tableName").value=values[1];
		return true;
	}
</script>
</head>
<body class="layui-layout-body">
	<form class="layui-form" action="${rc.contextPath}/project/index.htm"
		method="post" onsubmit="return check()">
		<input type="hidden" name="tableName" id="tableName" /> <input
			type="hidden" name="dbName" id="dbName" />
		<!-- 内容主体区域 -->
		<div class="layui-container">
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 20px;">
				<legend>项目自动创建</legend>
			</fieldset>

			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">选择表*</label>
					<div class="layui-input-inline">
						<select id="tableSelect">
							<option selected="selected">请选择</option>
							<#list allTablesMap?keys as key>
							<optgroup label="${key}">
								<#assign list=allTablesMap[key]> <#list list as detail>
								<option value="${detail.dbName},${detail.tableName}">${detail.tableName}(${detail.remark})</option>
								</#list>
							</optgroup>
							</#list>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">项目名</label>
				<div class="layui-input-block">
					<input type="text" name="projectName" lay-verify="title"
						autocomplete="off" placeholder="默认不填写" class="layui-input"
						value="${projectName}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">作者</label>
				<div class="layui-input-block">
					<input type="text" name="author" lay-verify="title"
						autocomplete="off" placeholder="必须填写" class="layui-input"
						value="${author}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">生成路径</label>
				<div class="layui-input-block">
					<input type="text" name="outputPath" lay-verify="title"
						autocomplete="off" placeholder="自定义生成路径" class="layui-input"
						value="${outputPath}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">端口号</label>
				<div class="layui-input-block">
					<input type="text" name="port" lay-verify="title"
						autocomplete="off" placeholder="必须填写" class="layui-input"
						value="${port}">
				</div>
			</div>
			<div class="layui-form-item" pane="">
				<label class="layui-form-label">创建pom文件</label>
				<div class="layui-input-block">
					<input type="checkbox" checked="" name="pomCreate"
						lay-skin="switch" lay-filter="switchTest" title="选择是否创建pom.xml">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit=""
						lay-filter="demo1">创建</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>

		</div>
		<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
	</form>
</body>
</html>
