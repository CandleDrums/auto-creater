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
	<#include "html/common/nav.ftl"> 
	<iframe
		src="${rc.contextPath}/welcome.htm" name="content_frame"
		id="content_frame" frameborder="0" class="layadmin-iframe"
		style="top: 60px;" scrolling="auto"> </iframe>
</body>
</html>
