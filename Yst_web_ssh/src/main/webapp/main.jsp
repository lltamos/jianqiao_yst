<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<head>
<%@ include file="/common/commonHead.jsp"%>
<link rel="stylesheet" href="${baseurl}/style/theme.css" />
<style type="text/css">
	#line-chart {
		height: 300px;
		width: 800px;
		margin: 0px auto;
		margin-top: 1em;
	}
	
	.brand {
		font-family: georgia, serif;
	}
	
	.brand .first {
		color: #ccc;
		font-style: italic;
	}
	
	.brand .second {
		color: #fff;
		font-weight: bold;
	}
</style>
</head>
<body>
	<%@ include file="/common/top.jsp"%>
	<%@ include file="/common/left.jsp"%>
	
	<div class="content" id="index_div">
		<ul class="breadcrumb">
			<li class="active">主页</li> 
		</ul>
		<div class="row-fluid">
			<div class="http-error">
				<h1>欢迎进入!</h1>
				<h1>健桥后台管理系统</h1>
			</div>
		</div>
	</div>
</body>
</html>