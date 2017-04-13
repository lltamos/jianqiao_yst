<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/commonPublic.jsp"%>
<script type="text/javascript">
	function goToDoctor() {
		
		
	 	$.ajax({
				post : "POST",
				url : "${cxt}/pc/view/doctor/verification-doctor",
				success : function(result) {
					var appResult = eval("(" + result + ")");
					var message = appResult.msg;
					var code = appResult.code;
					if (code == 0) {
						window.location.href = "${cxt }/pc/view/doctor/to-certification";
					} else if (code == 2) {
						alert(message);
						
					} else {
						alert(message);
					}
				}
			}); 
	}
	function isDoctor(){
	
		 $.ajax({
			post : "POST",
			url : "${cxt }/pc/after/doctorimage/decide",
			success : function(result) {
				var appResult = eval("(" + result + ")");
				var message = appResult.msg;
				var code = appResult.code;
				if (code == 1) {
					window.location.href = "${cxt }/pc/after/doctorimage/doctorimage-list";
				}else {
					alert(message);
				}
			}
		}); 
	}
</script>

<div class="Tophead">
	<div class="header clearfix">
		<div class="cityicon">
			<div class="icon"></div>
		</div>
		<span class="cityw">城市</span>
		<div class="headerright">



			<c:choose>
				<c:when test="${sessionScope.customer.nickName != '' && sessionScope.customer.nickName != null }">
					<a href="${cxt }/pc/after/order/findOrdersList.do;"
						class="pleaselogin">${sessionScope.customer.nickName }</a>
				</c:when>
				<%-- <c:when test="${sessionScope.customer.phone != '' }">
					<a href="${cxt }/pc/after/order/findOrdersList.do;"
						class="pleaselogin">${sessionScope.customer.phone }</a>
				</c:when> --%>
				<c:otherwise>
					<a href="${cxt }/pc/after/order/findOrdersList.do;"
						class="pleaselogin">${sessionScope.customer.phone }</a>
				</c:otherwise>
			</c:choose>

			<c:if test="${sessionScope.customer == null}">
				<a href="${cxt }/pc/view/customer/to-login" class="pleaselogin">请登录
				</a>
			</c:if>
			<c:if test="${sessionScope.customer != null}">
				<a href="${cxt }/pc/view/customer/login-out" class="register">退出
				</a>
			</c:if>
			<c:if test="${sessionScope.customer == null}">
				<a href="${cxt }/pc/view/customer/to-register" class="register">免费注册
				</a>
			</c:if>



			<c:if test="${sessionScope.customer.type== 1 }">
				<a href="javascript:void(0);" onclick="isDoctor()"
					class="welcome">我是医生</a>
			</c:if>
			<c:if test="${sessionScope.customer.type != 1}">
				<a href="javascript:void(0);" onclick="goToDoctor()" class="welcome">认证为医生</a>
			</c:if>
			<div class="appiconbox">
				<span class="appicon"></span>
			</div>
			<a href="javascript:;" class="txt">健桥APP</a>
			<div class="weixiniconbox">
				<span class="weixinicon"></span>
			</div>
			<a href="javascript:;" class="txt">微信公众号</a>
			<div class="shareiconbox">
				<span class="shareicon"></span>
			</div>
			<a href="http://123.206.47.26:5889/user/user-login-page" class="txt">提现入口</a>
		</div>

		<div class="selectcity" style="display: none">
			<div class="cityline">
				<span>北京</span> <span>上海</span> <span>天津</span> <span>重庆</span>
			</div>
			<div class="cityline">
				<span>黑龙江</span> <span>吉林</span> <span>辽宁</span> <span>江苏</span> <span>安徽</span>
			</div>
			<div class="cityline">
				<span>河北</span> <span>河南</span> <span>湖北</span> <span>湖南</span> <span>江西</span>
			</div>
			<div class="cityline">
				<span>山西</span> <span>四川</span> <span>青海</span> <span>海南</span> <span>广东</span>
			</div>
			<div class="cityline">
				<span>贵州</span> <span>浙江</span> <span>福建</span> <span>台湾</span> <span>甘肃</span>
			</div>
			<div class="cityline">
				<span>云南</span> <span>内蒙古</span> <span>宁夏</span> <span>新疆</span> <span>西藏</span>
			</div>
			<div class="cityline">
				<span>广西</span> <span>香港</span> <span>澳门</span>
			</div>
		</div>
	</div>
</div>
