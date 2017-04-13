<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/common.css">
<link rel="stylesheet" href="${cxt }/css/diaryshareindex.css">
<link rel="stylesheet" href="${cxt }/css/personal_center_4.css" />
<link rel="stylesheet" href="${cxt }/css/personal_center_1.css" />
<link rel="stylesheet" href="${cxt }/css/personal_center_5.css" />
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>

</head>
<body>
	<div class="bg_gray">
		<div class="content">
			<%@include file="/common/mycenterleft.jsp"%>
			<div class="con_right">
				<div class="password_1">
					<div class="modify">修改密码</div>
					<ul>
						<li><span class="font_1">1</span>
							<p>验证身份</p></li>
						<li><b></b></li>
						<li><span class="font_1">2</span>
							<p>修改登录密码</p></li>
						<li><b></b></li>
						<li><span class="font_1">3</span>
							<p>成功</p></li>
					</ul>
					<div class="phone_1">
						<span></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>