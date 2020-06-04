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
		var myselect = document.getElementById("tableSelect");
		var index = myselect.selectedIndex;
		var value = myselect.options[index].value;
		if (value == "") {
			return false;
		}

		var values = value.split(",");
		document.getElementById("dbName").value = values[0];
		document.getElementById("tableName").value = values[1];
		return true;
	}
</script>
</head>
<body class="layui-layout-body">

	<!-- 内容主体区域 -->
	<div class="layui-container">
		
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 20px;">
				<legend>第一步，选择数据库</legend>
			</fieldset>
			<form class="layui-form" action="${rc.contextPath}/project/index.htm" method="post">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">数据库列表*</label>
					<div class="layui-input-inline">
						<select name="connectionConfigId" lay-verify="required">
							<option selected="selected" value="">请选择</option>
							<#list connectionList as detail>
							<optgroup label="${detail.name}">
								<option value="${detail.id}">${detail.host}:${detail.port}</option>
							</optgroup>
							</#list>
						</select>
					</div>
					<div class="layui-input-inline">
						<button type="button" class="layui-btn layui-btn-danger">
							<i class="layui-icon layui-icon-delete"></i>
						</button>
						<button type="button" class="layui-btn"  href="javascript:;" id="addConnection">
							<i class="layui-icon layui-icon-add-1"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit=""
						lay-filter="demo1">确认</button>
				</div>
			</div>
			</form>
			<form class="layui-form" action="${rc.contextPath}/project/index.htm" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">名称*</label>
				<div class="layui-input-inline">
					<input type="text" name="name" placeholder="名称" class="layui-input" required lay-verify="required" placeholder="请输入名称" autocomplete="off" >
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">IP与端口*</label>
				<div class="layui-input-inline">
					<input type="text" name="ip" placeholder="IP地址" class="layui-input" required lay-verify="required" placeholder="请输入IP地址" autocomplete="off" >
				</div>
				<div class="layui-input-inline" style="width: 128px;">
					<input type="text" name="port" placeholder="端口地址" class="layui-input" required lay-verify="required" placeholder="请输入端口地址" autocomplete="off" >
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户名*</label>
				<div class="layui-input-inline">
					<input type="text" name="userName" placeholder="用户名" class="layui-input" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" >
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码*</label>
				<div class="layui-input-inline">
					<input type="text" name="passwd" placeholder="密码" class="layui-input" required lay-verify="required" placeholder="请输入密码" autocomplete="off" >
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit=""
						lay-filter="demo1">创建</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
		<#if allTablesMap>
		<form class="layui-form" action="${rc.contextPath}/project/index.htm"
			method="post" onsubmit="return check()">
			<input type="hidden" name="tableName" id="tableName" /> <input
				type="hidden" name="dbName" id="dbName" />
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 10px;">
				<legend>项目信息</legend>
			</fieldset>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">选择表*</label>
					<div class="layui-input-inline">
						<select id="tableSelect"  lay-verify="required">
							<option selected="selected" value="">请选择</option>
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
		</form>
		</#if>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
	<script type="text/javascript">
	  $('#addConnection').on('click', function(){
	    layer.open({
	      type: 1,
	      area: ['600px', '360px'],
	      shadeClose: true, //点击遮罩关闭
	      content: '\<\div style="padding:20px;">自定义内容\<\/div>'
	    });
	  });
	</script>
</body>
</html>
