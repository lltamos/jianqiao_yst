<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jianqiao.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ridge.css">
</head>
<script type="text/javascript">
	function pageGo(href) {
		$("#index_div").load(href);
	}
	$(function(){
		pageGo("product!productListPage");
	});
</script>
<script src='http://kefu.easemob.com/webim/easemob.js?tenantId=7555&hide=false' async='async'></script>
<body>
	
<%@ include file="/common/head.jsp"%>
  <div class="content" id="index_div">
  </div>  
<%@ include file="/common/foot.jsp"%>
</body>
</html>