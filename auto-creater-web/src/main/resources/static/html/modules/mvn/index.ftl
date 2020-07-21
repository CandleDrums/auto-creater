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
					<legend>填写参数</legend>
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
					<div id="custom-div">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">自定义类型</label>
								<div class="layui-input-inline">
									<input type="text" name="customList" id="customList"
									 autocomplete="off" placeholder="自定义文件类型"
										class="layui-input">
								</div>
								<div class="layui-input-inline">
									<button type="button" class="layui-btn layui-btn-normal" id="add"
										href="javascript:;">
										<i class="layui-icon layui-icon-add-1" style="font-size: 20px;"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button type="submit" class="layui-btn" lay-submit="">
								<i class="layui-icon" style="font-size: 20px;">&#xe615;</i> 扫描
							</button>
							<#if fileList>
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
		  var junkList ="";//定义一个数组    
	            $('input[name="junkList"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
	        	junkList=junkList+$(this).val()+",";
	            });
			$.ajax({
				url:"${rc.contextPath}/mvn/clean.htm",
				type:'post',
				data:{
				    junkList:junkList,
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
			window.location.reload();
		});
	});
	</script>
	<script type="text/javascript">
	    var html = "";
		html += '<div class="layui-form-item">';
		html += '<div class="layui-inline">';
		html += '<label class="layui-form-label">自定义类型</label>';
		html += '<div class="layui-input-inline">';
		html += '<input type="text" name="customList" id="customList" autocomplete="off" placeholder="自定义文件类型" class="layui-input">';
		html += '</div>';
		html += '<div class="layui-input-inline">';
		html += '<button type="button" class="layui-btn layui-btn-danger remove" href="javascript:;">';
		html += '<i class="layui-icon layui-icon-delete" style="font-size: 20px;"></i>';
		html += '</button>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		$('#add').on('click', function() {
		    $("#custom-div").append(html);
		});
		$("body").on('click','.remove',function () {
		    //获取当前点击的元素的父级的父级进行删除
		    $(this).parent().parent().parent().remove();
		})
	</script>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>

</body>
</html>