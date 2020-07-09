<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" href="${rc.contextPath}/layui/css/layui.css"
	media="all" />
</head>
<body>
	<ul class="layui-nav layui-bg-green">
		<li class="layui-nav-item"><a href="${rc.contextPath}/index.htm"><img
				src="${rc.contextPath}/img/macro.png" width="36px">服务端创建</a></li>
		<li class="layui-nav-item"><a href="${rc.contextPath}/index.htm"><img
				src="${rc.contextPath}/img/responsive.png" width="36px">应用端创建</a></li>
	</ul>
	<!-- 内容主体区域 -->
	<div class="layui-container" style="width: 800px;">
		<#include "html/server.ftl">
	</div>
<script src="${rc.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
</body>
</html>
