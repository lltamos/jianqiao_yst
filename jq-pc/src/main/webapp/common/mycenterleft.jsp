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


		<ul id="test">

			<c:if test="${lefttype ==0}">
				<li style="background-color: blue;"><a href="${cxt}/pc/after/order/findOrdersList.do">个人中心</a></li>
				<li><a href="${cxt}/pc/after/customer/to-editnickname-page">修改头像、昵称</a></li>
				<li><a href="${cxt}/pc/after/customer/to-editpasswdcheck-page">修改密码</a></li>
				<li><a href="${cxt}/pc/after/customer/to-editphonecheck-page">修改手机号</a></li>
				<%-- 		<li><a href="${cxt}/order/userbank/toSaveBank">绑定银行卡</a></li>
						<li><a href="${cxt}/order/userbank/toUpdateBank">修改银行卡</a></li> --%>
			</c:if>

			<c:if test="${lefttype ==1}">
				<li><a href="${cxt}/pc/after/order/findOrdersList.do">个人中心</a></li>
				<li style="background-color: blue;"><a href="${cxt}/pc/after/customer/to-editnickname-page">修改头像、昵称</a></li>
				<li><a href="${cxt}/pc/after/customer/to-editpasswdcheck-page">修改密码</a></li>
				<li><a href="${cxt}/pc/after/customer/to-editphonecheck-page">修改手机号</a></li>
				<%-- 		<li><a href="${cxt}/order/userbank/toSaveBank">绑定银行卡</a></li>
						<li><a href="${cxt}/order/userbank/toUpdateBank">修改银行卡</a></li> --%>
			</c:if>

			<c:if test="${lefttype ==2}">
				<li><a href="${cxt}/pc/after/order/findOrdersList.do">个人中心</a></li>
				<li><a href="${cxt}/pc/after/customer/to-editnickname-page">修改头像、昵称</a></li>
				<li style="background-color: blue;"><a href="${cxt}/pc/after/customer/to-editpasswdcheck-page">修改密码</a></li>
				<li><a href="${cxt}/pc/after/customer/to-editphonecheck-page">修改手机号</a></li>
				<%-- 		<li><a href="${cxt}/order/userbank/toSaveBank">绑定银行卡</a></li>
						<li><a href="${cxt}/order/userbank/toUpdateBank">修改银行卡</a></li> --%>
			</c:if>

			<c:if test="${lefttype ==3}">
				<li><a href="${cxt}/pc/after/order/findOrdersList.do">个人中心</a></li>
				<li><a href="${cxt}/pc/after/customer/to-editnickname-page">修改头像、昵称</a></li>
				<li><a href="${cxt}/pc/after/customer/to-editpasswdcheck-page">修改密码</a></li>
				<li style="background-color: blue;"><a href="${cxt}/pc/after/customer/to-editphonecheck-page">修改手机号</a></li>
				<%-- 		<li><a href="${cxt}/order/userbank/toSaveBank">绑定银行卡</a></li>
						<li><a href="${cxt}/order/userbank/toUpdateBank">修改银行卡</a></li> --%>
			</c:if>

		</ul>




	</div>
</body>
