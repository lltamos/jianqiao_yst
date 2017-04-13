<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li>订单记录列表 <span class="divider">/</span>
		</li>
		<li class="active">查看订单记录详细信息：</li>
	</ul>
	<form class="form-horizontal" id="messageInfoForm" method="post" enctype="multipart/form-data">
		<div class="control-group">
			<label class="control-label" for="str_customer_name">所属用户：</label>
			<div class="controls">
				${dsorder.str_customer_name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="str_doctor_name">所属医生：</label>
			<div class="controls">
				${dsorder.str_doctor_name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="order_date">订单时间：</label>
			<div class="controls">
				${dsorder.order_date }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="serviceType">服务类型：</label>
			<div class="controls">
				<c:if test="${dsorder.serviceType ==1 }">在线咨询</c:if>
				<c:if test="${dsorder.serviceType ==2 }">在线预约</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="price">价格：</label>
			<div class="controls">
				${dsorder.price }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="executed">执行状态：</label>
			<div class="controls">
				<c:if test="${dsorder.executed ==0 }">未执行</c:if>
					<c:if test="${dsorder.executed ==1 }">正在执行中</c:if>
					<c:if test="${dsorder.executed ==2 }">执行完毕</c:if>
					<c:if test="${dsorder.executed ==3 }">评价完毕</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="payStatus">支付状态：</label>
			<div class="controls">
				<c:if test="${dsorder.payStatus ==0 }">未支付</c:if>
				<c:if test="${dsorder.payStatus ==1 }">已支付</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="pay_type">支付类型：</label>
			<div class="controls">
				<c:if test="${dsorder.pay_type ==0 }">系统内支付</c:if>
				<c:if test="${dsorder.pay_type ==1 }">支付宝</c:if>
				<c:if test="${dsorder.pay_type ==2 }">银联</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="pay_time">支付时间：</label>
			<div class="controls">
				<fmt:formatDate value="${dsorder.pay_time}" pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="pay_relative_id">支付宝订单号：</label>
			<div class="controls">
				${dsorder.pay_relative_id}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="pay_relative_id">是否同意：</label>
			<div class="controls">
				<c:if test="${dsorder.approved ==0 }">开始</c:if>
				<c:if test="${dsorder.approved ==1 }">同意</c:if>
				<c:if test="${dsorder.approved ==2 }">拒绝</c:if>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
					<button class="btn" type="button"
						onclick="pageGo('${ctx}/doctorserviceorder/to-doctorserviceorder-list')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
