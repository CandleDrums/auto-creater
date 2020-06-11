<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" href="${rc.contextPath}/layui/css/layui.css"
	media="all" />
</head>
<body class="layui-layout-body">
	<ul class="layui-nav layui-bg-green" lay-filter="">
		<li class="layui-nav-item"><a href="${rc.contextPath}/index.htm"><img
				src="${rc.contextPath}/img/file.png" width="36px">项目创建</a></li>
	</ul>
	<!-- 内容主体区域 -->
	<div class="layui-container" style="width: 800px;">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>第一步 选择数据库</legend>
		</fieldset>
		<form class="layui-form" action="${rc.contextPath}/index.htm"
			method="post">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">数据库列表</label>
					<div class="layui-input-inline">
						<select name="connectionConfigId" id="connectionConfigId"
							lay-verify="required">
							<option selected="selected" value="">请选择</option>
							<#list connectionList as detail>
							<optgroup label="${detail.name}">
								<option value="${detail.id}"<#if
										connectionConfigId?? && connectionConfigId== detail.id>selected="selected"</#if>>${detail.host}:${detail.port}
								</option>
							</optgroup>
							</#list>
						</select>
					</div>
					<div class="layui-input-inline">
						<button type="button" class="layui-btn layui-btn-danger"
							href="javascript:;" id="deleteConnection">
							<i class="layui-icon layui-icon-delete" style="font-size: 20px;"></i>
						</button>
						<button type="button" class="layui-btn layui-btn-normal"
							href="javascript:;" id="addConnection">
							<i class="layui-icon layui-icon-add-1" style="font-size: 20px;"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit="">
						<i class="layui-icon" style="font-size: 20px;">&#xe615;</i> 查询
					</button>
					&nbsp;&nbsp;<font color="#FF5722">${error}</font>
				</div>
			</div>
		</form>
		<br />
		<#if allTablesMap> <input type="hidden" name="tableName"
			id="tableName" /> <input type="hidden" name="dbName" id="dbName" />
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 10px;">
			<legend>第二步 填写项目信息</legend>
		</fieldset>
		<form class="layui-form">
			<input type="hidden" name="connectionConfigId"
				id="connectionConfigId" value="${connectionConfigId}">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">选择表</label>
					<div class="layui-input-inline">
						<select id="tableSelect" lay-verify="required">
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
					<div class="layui-form-mid layui-word-aux">*必填</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">项目名</label>
				<div class="layui-input-inline">
					<input type="text" name="projectName" id="projectName"
						lay-verify="title" autocomplete="off" placeholder="默认与库名相同"
						class="layui-input" value="${projectName}">
				</div>
				<div class="layui-form-mid layui-word-aux">当库名特殊时，可单独指定</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">基础包名</label>
				<div class="layui-input-inline">
					<input type="text" name="packageName" id="packageName" lay-verify="title"
						autocomplete="off" placeholder="必须填写" class="layui-input"
						value="${packageName}">
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
					<input type="text" name="port" id="port" lay-verify="title"
						autocomplete="off" placeholder="必须填写" class="layui-input"
						value="${port}">
				</div>
				<div class="layui-form-mid layui-word-aux">*必填</div>
			</div>
			<div class="layui-form-item" pane="">
				<label class="layui-form-label">创建pom</label>
				<div class="layui-input-block">
					<input type="checkbox" checked="" name="pomCreate" id="pomCreate"
						lay-skin="switch" lay-filter="switchTest" title="选择是否创建pom.xml">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="button" id="projectCreate" class="layui-btn"
						lay-submit="">
						<i class="layui-icon" style="font-size: 20px;">&#xe609;</i> 创建
					</button>
				</div>
			</div>
		</#if>
	</div>
	<script src="${rc.contextPath}/js/jquery-3.5.1.min.js"></script>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
	<script type="text/javascript">
		function sleep (time) {
		  return new Promise((resolve) => setTimeout(resolve, time));
		}
	</script>
	<script type="text/javascript">
		$('#addConnection').on('click', function() {
			$('#addConnection').attr("disabled",true); 
			layer.open({
				type : 2,
				title : '添加数据库连接',
				anim : 0,
				closeBtn : 1,
				shade : 0,
				shadeClose : true,
				area : [ '480px', '312px' ],
				shadeClose : true, //点击遮罩关闭
				content : '${rc.contextPath}/db/toadd.htm',
				cancel : function(index, layero) {
					window.location.reload();
					return false;
				}
			});
		});
	</script>
	<script type="text/javascript">
		$('#deleteConnection').on('click', function() {
			var value = $("#connectionConfigId").val();
			if (value == '') {
				layer.alert('请选择一个连接！',{icon: 5, title:'删除'});
			} else {
				
				layer.confirm('是否确认删除该连接？', {icon: 2, title:'删除'}, function(index){
					  //do something
					  $.ajax({
						url : "${rc.contextPath}/db/delete.htm",
						type : 'post',
						data : {
							id : value
						},
						success : function(data) {
							if(data.result=='SUCCESS'){
								layer.alert(data.message,{icon: 1, title:'删除成功'});
								sleep(1000).then(() => {
									window.location.reload();
								})
								
							}else{
								layer.alert(data.message,{icon: 2, title:'删除失败'});
							}
						}
					});
					layer.close(index);
				});
			}
		});
	</script>
	<script type="text/javascript">
		$('#projectCreate').on('click', function() {
			layui.use('element', function(){
			  var element = layui.element;
				var selectValues=$("#tableSelect").val().split(",");
				$.ajax({
					url:"${rc.contextPath}/create.htm",
					type:'post',
					data:{connectionConfigId:$("#connectionConfigId").val(),
						projectName:$("#projectName").val(),
						dbName:selectValues[0],
						tableName:selectValues[1],
						pomCreate:$("#pomCreate").val(),
						outputPath:$("#outputPath").val(),
						port:$("#port").val(),
						author:$("#author").val(),
						packageName:$("#packageName").val()
					}
				})
				
				var progressLayer = layer.open({
		    	    type: 0,
					title : '开始创建相应项目文件',
					anim : 0,
					area : [ '480px' ],
		    	    closeBtn: 0,
		    	    btn: false,
		    	    content: '<div class="layui-progress layui-progress-big" lay-filter="progress" lay-showPercent="true"><div class="layui-progress-bar layui-bg-blue"></div></div>'
		    	});
				//定义扫描时间
				var scanTime = 500;
				//进度条方法查看进度
				var timer = setInterval(function (){
			    		$.ajax({
			    			url: "${rc.contextPath}/module/progress/detail.htm",
			    			success: function (data) {
			    					if(!jQuery.isEmptyObject(data)){
					    				console.info(data);
				    					var p=data.percent;
				    					//动态设置百分比
				    					element.progress('progress', p +'%')
				    					if(p  == 100){
				    						//进度到100%，注意关闭定时器
				    						clearInterval(timer);
				    						//关闭弹出层
				                            layer.close(progressLayer); 
											layer.alert("创建完成！",{icon: 1, title:'创建成功'});

				    					}}
			    			},
			    			error: function (e) {
								layer.alert(e,{icon: 2, title:'创建失败'});
			    			}
			    		});
			    	}, scanTime);
			});
		});
	</script>
</body>
</html>
