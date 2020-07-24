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
				<form class="layui-form" id="createForm">
					<div class="layui-form-item">
						<label class="layui-form-label">项目名</label>
						<div class="layui-input-inline">
							<input type="text" name="projectName" id="projectName"
								required  lay-verify="required" autocomplete="off" placeholder="填写项目名称"
								class="layui-input" value="${projectName}">
						</div>
						<div class="layui-form-mid layui-word-aux">*必填</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">基础包名</label>
						<div class="layui-input-inline">
							<input type="text" name="packageName" id="packageName"
								required  lay-verify="required" autocomplete="off" placeholder="必须填写"
								class="layui-input" value="${packageName}">
						</div>
						<div class="layui-form-mid layui-word-aux">*必填，默认为:com.cds</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">作者</label>
						<div class="layui-input-inline">
							<input type="text" name="author" id="author" required  lay-verify="required"
								autocomplete="off" placeholder="必须填写" class="layui-input"
								value="${author}">
						</div>
						<div class="layui-form-mid layui-word-aux">*必填</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">生成路径</label>
						<div class="layui-input-inline" style="width: 400px">
							<input type="text" name="outputPath" id="outputPath"
								required  lay-verify="required" autocomplete="off" placeholder="自定义生成路径"
								class="layui-input" value="${outputPath}">
						</div>
						<div class="layui-form-mid layui-word-aux">*必填</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">端口号</label>
						<div class="layui-input-inline">
							<input type="text" name="port" id="port" lay-verify="number" required
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
							<button type="button" id="projectCreate" class="layui-btn"
								lay-submit="">
								<i class="layui-icon" style="font-size: 20px;">&#xe609;</i> 创建
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function sleep (time) {
	  return new Promise((resolve) => setTimeout(resolve, time));
	}
</script>
	<script type="text/javascript">
	$('#projectCreate').on('click', function() {
		var value = $("#projectName").val();

		if (value == '') {
			layer.alert('请输入项目名！',{icon: 5, title:'删除'});
			return false;
		}
		layui.use('element', function(){
		  var element = layui.element;
			$.ajax({
				url:"${rc.contextPath}/app/create.htm",
				type:'post',
				data:{
					projectName:$("#projectName").val(),
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
	    	    content: '<div class="layui-progress layui-progress-big" lay-filter="progress" lay-showPercent="true"><div class="layui-progress-bar layui-bg-blue"><span id="progress_message"></span></div></div>'
	    	});
			//定义扫描时间
			var scanTime = 500;
			//进度条方法查看进度
			var timer = setInterval(function (){
		    		$.ajax({
		    			url: "${rc.contextPath}/module/progress/detail.htm",
		    			data:{
		    				name:"create_project_progress"
		    			},
		    			success: function (data) {
	    					if(!jQuery.isEmptyObject(data)){
		    					var p=data.percent;
		    					//动态设置百分比
		    					element.progress('progress', p +'%')
		    						console.info(data);
		    					if(data.message!=""){
		    					$("#progress_message").html(data.message)
		    					}
		    					if(p  == 100){
		    						//进度到100%，注意关闭定时器
		    						clearInterval(timer);
		    						//关闭弹出层
		                            layer.close(progressLayer); 
									layer.alert("创建完成！",{icon: 1, title:'创建成功'});
		    					}}
		    			},
		    			error: function (e) {
		    				clearInterval(timer);
		 					layer.close(progressLayer); 
							layer.alert(e,{icon: 2, title:'创建失败'});
		    			}
		    		});
		    	}, scanTime);
		});
	});
</script>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
</body>
</html>