<link rel="stylesheet" href="${rc.contextPath}/layui/css/layui.css"
	media="all" />
<link rel="stylesheet" href="${rc.contextPath}/layui/css/admin.css"
	media="all" />

<div class="layui-fluid">
	<div class="layui-row layui-col-space15">

		<div class="layui-col-md4">
			<#include "html/component/quick.ftl">
			<#include "html/component/list.ftl">
		</div>
		<div class="layui-col-md4">
			<#include "html/component/history.ftl">
		</div>
		<div class="layui-col-md4">
			<#include "html/component/copyright.ftl">
			<#include "html/component/info.ftl">
		</div>

	</div>
</div>