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
				<form class="layui-form" id="cleanForm">
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
						<label class="layui-form-label">日志垃圾</label>
						<div class="layui-input-block">
							<input type="checkbox" name="junkList" title="*.log"
								lay-skin="primary" value=".log">
							<input type="checkbox" name="junkList" title="*.log-2019*"
								lay-skin="primary" value=".log-2019">
							<input type="checkbox" name="junkList" title="*.log-2020*"
								lay-skin="primary" value=".log-2020">
							<input type="checkbox" name="junkList" title="*.log.gz"
								lay-skin="primary" value=".log.gz">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">tmp垃圾</label>
						<div class="layui-input-block">
							<input type="checkbox" name="junkList" title="*.tmp"
								lay-skin="primary" value=".tmp">
						</div>
					</div>
					<div id="custom-div">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">自定义类型</label>
								<div class="layui-input-inline">
									<input type="text" name="customList"
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
							<button type="button" class="layui-btn" id="scan" lay-submit="">
								<i class="layui-icon" style="font-size: 20px;">&#xe615;</i> 扫描
							</button>
							<button type="button" class="layui-btn layui-btn-danger" disabled="disabled"
								id="clear-btn" lay-submit="">
								<i class="layui-icon" style="font-size: 20px;">&#xe640;</i> 清理
							</button>
						</div>
					</div>
				</form>
				<div id="file-list-div"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function sleep (time) {
		  return new Promise((resolve) => setTimeout(resolve, time));
		}
	</script>
	<script type="text/javascript">
	$('#scan').on('click', function() {
	    var value = $("#repPath").val();
		if (value == '') {
			layer.alert('请输入要清理的目录位置！',{icon: 5, title:'扫描'});
		} else {
			var data = new FormData($("#cleanForm")[0]);
			$.ajax({
			     type: 'POST',
			     dataType: 'json',
			     processData: false, // 告诉jquery不要处理数据
			     contentType: false, // 告诉jquery不要设置contentType
			     data: data,
			     url: '${rc.contextPath}/clean/scan.htm',
			     success : function(data) {
					if(data.result=='SUCCESS'){
					    $("#file-list-div").empty();
					    var html = "";
						html += '<pre class="layui-code layui-box layui-code-view" lay-height="200px"><ol class="layui-code-ol" style="max-height: 200px;">';
						html += data.data;
						html += '</ol></pre>';
						 $("#file-list-div").append(html);
						 $("#clear-btn").removeAttr("disabled");//将按钮可用
					}else{
					    $("#clear-btn").attr({"disabled":"disabled"});
						layer.alert(data.message,{icon: 2, title:'搜索结果'});
					    $("#file-list-div").empty();
					}
			}
			})
		}
	});
	</script>

	<script type="text/javascript">
	$('#clear-btn').on('click', function() {
	    layer.confirm('是否确认清除？', {icon: 2, title:'删除'}, function(index){
		
		 var data = new FormData($("#cleanForm")[0]);
		      $.ajax({
		        type: 'POST',
		        dataType: 'json',
		        processData: false, // 告诉jquery不要处理数据
		        contentType: false, // 告诉jquery不要设置contentType
		        data: data,
		        url: '${rc.contextPath}/clean/clean.htm',
		        success : function(data) {
					if(data.result=='SUCCESS'){
						layer.close(index);
						layer.alert(data.message,{icon: 1, title:'删除成功'});
						sleep(2000).then(() => {
							window.location.reload();
						})
						
					}else{
						layer.alert(data.message,{icon: 2, title:'删除失败'});
					}
				}
		      })
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