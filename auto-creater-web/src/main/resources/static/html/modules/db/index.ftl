<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>数据库配置信息</title>
<link rel="stylesheet" href="${rc.contextPath}/layui/css/layui.css"
	media="all" />
<script src="${rc.contextPath}/js/jquery-3.5.1.min.js"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-container" style="width: 800px;">
		<div class="layui-table-tool" style="width:738px;min-height: 20px;">
			<button type="button" class="layui-btn layui-btn-normal"
				href="javascript:;" id="addConnection">
				<i class="layui-icon layui-icon-add-1" style="font-size: 16px;"></i>
			</button>
		</div>
		<div class="layui-table-box">
			<div class="layui-table-header">
				<table cellspacing="0" cellpadding="0" border="0"
					class="layui-table" lay-even="">
					<thead>
						<tr>
							<th data-field="id" data-key="45-0-0" class=" layui-unselect"><div
									class="layui-table-cell laytable-cell-45-0-0">
									<span>序号</span>
								</div></th>
							<th data-field="username" data-key="45-0-1" class=""><div
									class="layui-table-cell laytable-cell-45-0-1">
									<span>名称</span>
								</div></th>
							<th data-field="email" data-key="45-0-2" data-minwidth="150"
								class=""><div class="layui-table-cell laytable-cell-45-0-2">
									<span>IP地址</span>
								</div></th>
							<th data-field="sign" data-key="45-0-3" data-minwidth="160"
								class=""><div class="layui-table-cell laytable-cell-45-0-3">
									<span>端口</span>
								</div></th>
							<th data-field="sex" data-key="45-0-4" class=""><div
									class="layui-table-cell laytable-cell-45-0-4">
									<span>用户名</span>
								</div></th>
							<th data-field="city" data-key="45-0-5" class=""><div
									class="layui-table-cell laytable-cell-45-0-5">
									<span>密码</span>
								</div></th>
							<th data-field="city" data-key="45-0-5" class=""><div
									class="layui-table-cell laytable-cell-45-0-5">
									<span>操作</span>
								</div></th>
						</tr>
					</thead>
					<tbody>
						<#list connectionList as detail>
						<tr data-index="${detail_index}" class="">
							<td data-field="id" data-key="45-0-0" class=""><div
									class="layui-table-cell laytable-cell-45-0-0">${detail_index+1}</div></td>
							<td data-field="username" data-key="45-0-1" class=""><div
									class="layui-table-cell laytable-cell-45-0-1">${detail.name}</div></td>
							<td data-field="email" data-key="45-0-2" data-minwidth="150"
								class=""><div class="layui-table-cell laytable-cell-45-0-2">${detail.host}</div></td>
							<td data-field="sign" data-key="45-0-3" data-minwidth="160"
								class=""><div class="layui-table-cell laytable-cell-45-0-3">${detail.port}</div></td>
							<td data-field="sex" data-key="45-0-4" class=""><div
									class="layui-table-cell laytable-cell-45-0-4">${detail.userName}</div></td>
							<td data-field="city" data-key="45-0-5" class=""><div
									class="layui-table-cell laytable-cell-45-0-5">${detail.passwd}</div></td>
							<td data-field="experience" data-key="45-0-6" class=""><div
									class="layui-table-cell laytable-cell-45-0-6">
									<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" href="javascript:void(0);" onclick="deleteConnect(${detail.id})">删除</a>
								</div></td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
	</div>
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
	function deleteConnect(connectionConfigId){
		var value = connectionConfigId;
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
	}
</script>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
</body>
</html>