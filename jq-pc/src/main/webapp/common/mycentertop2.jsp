<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<body>
	<c:if test="${modeltype == 0}">
		<div class="change">
			<span class="change_1 color_bor"><a class="color_font"
				href="${cxt}/pc/after/order/findOrdersList.do">订单中心</a></span> <span
				class="change_2"><a
				href="${cxt}/pc/after/chat/to-chatrecord-page">聊天记录</a></span> <span
				class="change_3"><a 
				 href="${cxt }/pc/view/diarybook/toDiaryListPage.do" >日记本</a></span>
		</div>
	</c:if>

	<c:if test="${modeltype ==1}">
		<div class="change">
			<span class="change_1"><a 
				href="${cxt}/pc/after/order/findOrdersList.do">订单中心</a></span> <span
				class="change_2"><a class="color_font"
				href="${cxt}/pc/after/chat/to-chatrecord-page">聊天记录</a></span> <span
				class="change_3"><a 
				 href="${cxt }/pc/view/diarybook/toDiaryListPage.do" >日记本</a></span>
		</div>
	</c:if>
	
	<c:if test="${modeltype ==2}">
		<div class="change">
			<span class="change_1"><a 
				href="${cxt}/pc/after/order/findOrdersList.do">订单中心</a></span> <span
				class="change_2"><a 
				href="${cxt}/pc/after/chat/to-chatrecord-page">聊天记录</a></span> <span
				class="change_3"><a class="color_font"
				 href="${cxt }/pc/view/diarybook/toDiaryListPage.do" >日记本</a></span>
		</div>
	</c:if>
</body>
