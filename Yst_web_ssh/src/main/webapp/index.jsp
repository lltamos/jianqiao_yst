<%@ page pageEncoding="UTF-8"%>
<%
	String serverName =request.getServerName();
	String src = "";
	if(serverName.equals("wap.jianqiao001.com")){//手机端
		src="product!wapPage?is_real=1";
	}else if(serverName.equals("back.jianqiao001.com")){//后台
		src="user!loginPage";
	}else{
		src="product!indexPage";
	}
	request.setAttribute("src", src);
%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<head>
	<%@ include file="/common/commonHead.jsp"%>
	<script type="text/javascript">
	$(function(){
		$("#main").load("${src}");
	});
    function pageGo(href) {
		$("#main").load(href);
	}
	</script>
</head>
<div id="main"></div>
</html>