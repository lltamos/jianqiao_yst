<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <title>健桥首页</title>
    <link rel="stylesheet" href="${cxt }/css/base.css">
    <link rel="stylesheet" href="${cxt }/css/topindex_2.css">
    <%-- <link rel="stylesheet" href="${cxt }/css/common.css"> --%>
</head>
<body>
    	<div class="navbox">
		<div class="nav clearfix">
			<h1 class="logo">
				<a href="${cxt }/pc/view/customer/toIndex">
					<img src="${cxt }/img/logo.png" alt="">
				</a>
			</h1>
			<ul class="navlinks clearfix">
				<li >
					<a href="${cxt }/index.jsp"  <c:if test="${check=='index'}">class='bluedl'</c:if>>首页</a>
				</li>
				<li >
					<a href="${cxt }/pc/view/product/todisease" <c:if test="${check=='product'}">class='bluedl'</c:if>>疑难杂症导医</a>
				</li>
				<li >
					<a href="${cxt }/pc/view/doctor/todoctor" <c:if test="${check=='doctor'}">class='bluedl'</c:if>>名医高手</a>
				</li>
				<li >
					<a href="${cxt }/pc/view/diary/toDiaryListPage.do" <c:if test="${check=='diary'}">class='bluedl'</c:if>>健康分享</a>
				</li>
				<li >
					<a href="${cxt }/pc/view/doctor/finddoctor" <c:if test="${check=='paitent'}">class='bluedl'</c:if>>患者求医</a>
				</li>
				<li >
					<a href="javascript:;" <c:if test="${check=='aboutas'}">class='bluedl'</c:if>>关于我们</a>
				</li>
			</ul>
		</div>
	</div>
</body>