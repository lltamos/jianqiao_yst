<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		//将menuContainer变成一棵树
		$("#jstree_main").jstree({
			"core" : {
				"themes" : {
					"name" : "default-dark"
				},
			},
			"strings" : {
				"Loading ..." : "Please wait ..."
			},
			"plugins" : [ "wholerow", "themes", "html_data", "dnd" ]
		}).bind("select_node.jstree", function(event, data) {//节点单击事件  
			var url = data.node.a_attr.href;
			if (url == null || url == "" || url == "#") {
				return;
			} else {
				//resourceMain的div加载页面
				pageGo(url);
			}
		});
		//给所有连接target设置成rightFrame
	});
	function pageGo(href) {
		$("#index_div").load(href, function() {
			try {
				/* for (var i = 0; i < 1000; i++) {
					clearInterval(i);
				} */
			} catch (err) {

			}
		});
	}
</script>
<div class="sidebar-nav">
	<div id="jstree_main" style="height: 100%">
		<%-- OA树开始--%>
		<ul id="navigation">
			<%-- 管理员后台  --%>
			<c:if test="${sessionScope.dbUser !=null}">
				<%-- <li><a href="${ctx }/user/user-list">系统用户管理</a></li> --%>
				<li><a href="#">商户管理</a>
					<ul>
						<li><a href="${ctx }/merchant/merchant-list">总院</a></li>
						<li><a href="${ctx }/store/store-list">分院</a></li>
						<li><a href="${ctx }/product/product-list">疑难杂症导医</a></li>
					</ul>
				</li>
				<li><a href="${ctx }/customer/to-customer-list">会员管理</a>
				</li> 
				<li><a href="#">订单管理</a>
					<ul>
						<li><a href="${ctx }/productorder/productorder-list">疑难杂症导医订单</a></li>
						<li><a href="${ctx }/doctorserviceorder/to-doctorserviceorder-list">咨询订单</a></li>
					</ul>
				</li>
				<li><a href="#">医生管理</a>
					<ul>
						<li><a href="${ctx }/doctor/to-doctor-list">医生列表</a></li>
						<li><a href="${ctx }/doctor/to-doctor-apply-list">医生审核</a></li>
					</ul>
				</li>
				<%-- <li><a href="${ctx }/patient/to-patient-list">患者管理</a></li>
				<li><a href="${ctx }/relativemedicinerecord/to-relativemedicinerecord-list">用药记录管理</a></li>
				<li><a href="${ctx }/customerillnessrecord/to-customerillnessrecord-list">病例记录管理</a></li> --%>
				<%-- <li><a href="${ctx }/balance/to-balance-list">提现记录</a></li> --%>
			<%-- 	<li><a href="${ctx }/doctorserviceorder/to-doctorserviceorder-list">医生订单记录</a></li> --%>
				<li>
						<a href="#">字典管理</a>
						<ul>
							<li><a href="${ctx }/dic/service/to-service-list">服务类型</a></li>
							<li><a href="${ctx }/dic/relation/to-relation-list">关系类型</a></li>
							<li><a href="${ctx }/dic/title/to-title-list">医生抬头</a></li>
							<li><a href="${ctx }/dic/hospital/to-hospital-list">医院类型</a></li>
							<li><a href="${ctx }/dic/spec/to-spec-list">医生专长</a></li>
							<li><a href="${ctx }/dic/office/to-office-list">科室类型</a></li>
						</ul>
				</li>
				<li>
						<a href="#">pc端设置</a>
						<ul>
							<li><a href="${ctx }/banner/to-bannerlist-page">首页宣传图</a></li>
							<li><a href="${ctx }/message/to-message">健桥资讯</a></li>
						</ul>
				</li>
			</c:if>
			<%-- 医生后台  --%>
			<c:if test="${sessionScope.doctors !=null && sessionScope.customertype ==1}">
				<c:if test="${sessionScope.nameAuthentication == 1 }">
					<li><a href="${ctx}/sharemoney/doctor-list">疑难杂症订单分润</a></li> 
					<li><a href="${ctx}/doctorserviceorder/to-doctorservice-list">咨询订单分润</a></li> 
					<li><a href="${ctx}/balance/to-doctorbalance-list">提现记录</a></li> 
					<li><a href="${ctx}/authentication/to-savebrank-doctor">绑定/修改银行卡</a></li> 
				</c:if>
				<c:if test="${sessionScope.nameAuthentication == 0 || sessionScope.nameAuthentication == null }">
					<li><a href="${ctx}/customer/to-nameauthentication-doctor">实名认证</a></li>
				</c:if>
			</c:if>
			<%-- 总院后台  --%>
			<c:if test="${sessionScope.merchant !=null && sessionScope.customertype ==2}">
				<c:if test="${sessionScope.nameAuthentication == 1 }">
					<li><a href="${ctx}/sharemoney/merchant-list">订单分润</a></li> 
					<li><a href="${ctx}/balance/to-balance-list">提现记录</a> </li> 
					<li><a href="${ctx}/authentication/to-savebrank-merchant">绑定/修改银行卡</a></li> 
				</c:if>
				<c:if test="${sessionScope.nameAuthentication == 0 || sessionScope.nameAuthentication == null }">
					<li><a href="${ctx}/customer/to-nameauthentication-merchant">实名认证</a></li>
				</c:if>
			</c:if>
			<%-- 推荐人后台  --%>
			<c:if test="${sessionScope.recommender !=null && sessionScope.customertype ==3}">
				<c:if test="${sessionScope.nameAuthentication == 1 }">
					<li><a href="${ctx}/sharemoney/recommender-list">订单分润</a></li> 
					<li><a href="${ctx}/balance/to-balancetjr-list">提现记录</a> </li> 
					<li><a href="${ctx}/authentication/to-savebrank-tuijianren">绑定/修改银行卡</a></li> 
				</c:if>
				<c:if test="${sessionScope.nameAuthentication == 0 || sessionScope.nameAuthentication == null }">
					<li><a href="${ctx}/customer/to-nameauthentication-tuijianren">实名认证</a></li>
				</c:if>
			</c:if>
		</ul>
		<%-- 树结束--%>
	</div>
</div>