<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<body>
            <div class="con_right">
                <div class="change">
                    <span class="change_1"><a  href="${cxt }/pc/after/doctorimage/doctorimage-list" <c:if test="${style=='doctorimage'}">class='color_font'</c:if>>图文介绍</a></span>
                    <span class="change_2"><a <c:if test="${style=='doctorservice'}">class='color_font'</c:if> href="${cxt }/pc/after/doctorservice/gosettings">服务设置</a></span>
                    <span class="change_3"><a <c:if test="${style=='order'}">class='color_font'</c:if> href="${cxt }/pc/after/order/findAllDoctorOrderList">订单记录</a></span>
                    <span class="change_4"><a <c:if test="${style=='imlog'}">class='color_font'</c:if> href="${cxt }/pc/after/doctorimlog/imlog-list">聊天记录</a></span>
                </div>
</body>
