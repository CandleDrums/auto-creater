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
	<div class="layui-container layui-fluid" style="width: 800px;">
		<div class="layui-card">
			<div class="layui-card-body">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 20px;">
					<legend>选择Maven参数</legend>
				</fieldset>
				<form class="layui-form" id="createForm" method="post"
					action="${rc.contextPath}/mvn/index.htm">
					<input type="hidden" value="${repPath}" id="repPathValue">
					<div class="layui-form-item">
						<label class="layui-form-label">目录位置</label>
						<div class="layui-input-inline" style="width: 400px">
							<input type="text" name="repPath" id="repPath" lay-verify="title"
								autocomplete="off" placeholder="要清理的文件夹位置" class="layui-input"
								value="${repPath}" lay-verify="title">
						</div>
						<div class="layui-form-mid layui-word-aux">*必填</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">Maven垃圾</label>
						<div class="layui-input-block">
							<input type="checkbox" name="junkList" title="*.jar.lastUpdated"
								lay-skin="primary" checked value="jar.lastUpdated"> <input
								type="checkbox" name="junkList" title="*lastUpdated.properties"
								lay-skin="primary" checked value="lastUpdated.properties">
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-input-block">
							<button type="submit" class="layui-btn" lay-submit="">
								<i class="layui-icon" style="font-size: 20px;">&#xe615;</i> 扫描
							</button>
							<#if repPath>
							<button type="button" class="layui-btn layui-btn-danger"
								id="clear" lay-submit="">
								<i class="layui-icon" style="font-size: 20px;">&#xe640;</i> 清理
							</button>
							</#if>
						</div>
					</div>
				</form>
				<#if fileList>
				<pre class="layui-code layui-box layui-code-view" lay-height="200px"><ol class="layui-code-ol" style="max-height: 200px;"><#list fileList as detail><li>${detail}</li></#list></ol></pre>
				</#if>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$('#clear').on('click', function() {
	    layer.confirm('是否确认清除？', {icon: 2, title:'删除'}, function(index){
			$.ajax({
				url:"${rc.contextPath}/mvn/clean.htm",
				type:'post',
				data:{
				    junkList:$("#junkList").val(),
				    repPath:$("#repPathValue").val()
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
	});
	</script>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>

</body>
</html>