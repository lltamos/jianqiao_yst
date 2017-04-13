<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function pageGo(href) {
	$("#index_div").load(href);
}
function logout(){
	window.location.href=("${ctx }/user/user-logout");
}
</script>
<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav  pull-right">
				<li id="fat-menu" class="dropdown">
				<a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> 
					<i class="icon-user"></i>
					<c:if test="${sessionScope.admin !=null}">
						${sessionScope.admin.loginName}
					</c:if>
					<c:if test="${sessionScope.doctors !=null}">
						${sessionScope.doctors.name}
					</c:if>
					<i class="icon-caret-down"></i>
				</a>

				<ul class="dropdown-menu">
					<li><a tabindex="-1" href="#" onclick="logout()">注销</a></li>
				</ul></li>
			</ul>
			<a class="brand" href=""><span class="second">健桥后台管理系统</span>
			</a>
		</div>
</div>
