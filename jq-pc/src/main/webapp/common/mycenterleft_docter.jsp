<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<body>
	<div class="con_left">
		<img src="${img_service }/${sessionScope.customer.image}" alt="" style="height: 100px;width: 100px;display: block;"/>


		<c:choose>
			<c:when test="${sessionScope.customer.nickName != null}">
				<p id="phone_num">${sessionScope.customer.nickName}</p>
			</c:when>
			<c:when test="${sessionScope.customer.name != null}">
				<p id="phone_num">${sessionScope.customer.name}</p>
			</c:when>
			<c:otherwise>
				<p id="phone_num">${sessionScope.customer.phone}</p>
			</c:otherwise>
		</c:choose>


		
	</div>
</body>
