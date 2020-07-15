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
	<ul class="layui-nav layui-bg-green">
		<li class="layui-nav-item"><a href="${rc.contextPath}/index.htm"
			target="content_frame"><img src="${rc.contextPath}/img/home.png"
				width="36px">首页</a></li>
		<li class="layui-nav-item"><a
			href="${rc.contextPath}/db/index.htm" target="content_frame"><img
				src="${rc.contextPath}/img/connect_to_database.png" width="36px">数据库信息</a></li>
		<li class="layui-nav-item"><a
			href="${rc.contextPath}/server/index.htm" target="content_frame"><img
				src="${rc.contextPath}/img/server.png" width="36px">server应用创建</a></li>
		<li class="layui-nav-item"><a
			href="${rc.contextPath}/app/index.htm" target="content_frame"><img
				src="${rc.contextPath}/img/mobile_device.png" width="36px">web应用创建</a></li>
	</ul>
	<ul class="layui-nav layui-layout-right layui-bg-green"
		lay-filter="layadmin-layout-right">
		<li class="layui-nav-item layui-hide-xs" lay-unselect=""><a
			href="javascript:void(0);" id="contact"><img
				src="${rc.contextPath}/img/mailtwitter.png" width="36px">联系我们</a></li>
		<li class="layui-nav-item layui-hide-xs" lay-unselect=""><a
			href="https://github.com/CandleDrums/auto-creater" target="_blank"><img
				src="${rc.contextPath}/img/star.png" width="36px">前去标星</a></li>
	</ul>

	<script type="text/javascript">
	$('#contact').on('click', function() {
	    layer.open({
		type : 2,
		title : '联系我们',
		anim : 0,
		closeBtn : 1,
		shade : 0,
		shadeClose : true,
		area : [ '480px', '312px' ],
		shadeClose : true, //点击遮罩关闭
		content : '${rc.contextPath}/to.htm?path=html/component/contact',
		cancel : function(index, layero) {
		    return false;
		}
	    });
	});
    </script>
	<script src="${rc.contextPath}/layui/layui.all.js" charset="utf-8"></script>
</body>
</html>