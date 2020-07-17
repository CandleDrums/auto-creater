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
</head>
<body>
	<div class="layui-card">
		<div class="layui-card-header">任务列表</div>
		<div class="layui-card-body">
			<table class="layui-table">
				<tbody>
					<tr>
						<td width="36px;" style="text-align: center"><img
							src="${rc.contextPath}/img/checkmark.png" width="36px"></td>
						<td>数据库链接信息单独配置与展示</td>
						<td width="48px;" style="text-align: center"><del
								style="color: #5FB878;">已完成</del></td>
					</tr>
					<tr>
						<td width="36px;" style="text-align: center"><img
							src="${rc.contextPath}/img/rate.png" width="36px"></td>
						<td>Maven垃圾文件清理</td>
						<td width="48px;" style="text-align: center"><span style="color: #FFB800;">进行中</span></td>
					</tr>
					<tr>
						<td width="36px;" style="text-align: center"><img
							src="${rc.contextPath}/img/alarm.png" width="36px"></td>
						<td>数据库链接信息单独配置与展示</td>
						<td width="48px;" style="text-align: center"><span>未开始</span></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>