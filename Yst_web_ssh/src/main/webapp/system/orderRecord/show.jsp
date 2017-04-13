<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>订单记录列表 <span class="divider">/</span>
		</li>
		<li class="active">查看订单记录详细信息：</li>
	</ul>

		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>所属用户：</td>
				<td><s:property value="str_customer_name" /> </td>
			</tr>
			<tr>
				<td>所属医生：</td>
				<td><s:property value="str_doctor_name" /></td>
			</tr>
			<tr>
				<td>订单时间：</td>
				<td><s:property value="order_date" /></td>
			</tr>
			<tr>
				<td>服务类型：</td>
				<td><s:property value="str_service_name" /></td>
			</tr>
			<tr>
				<td>价格：</td>
				<td><s:property value="price" /></td>
			</tr>
			<tr>
				<td>执行状态：</td>
				<td>
					<s:if test="executed == 0">未执行</s:if>
					<s:if test="executed == 1">正在执行中</s:if>
					<s:if test="executed == 2">执行完毕</s:if>
					<s:if test="executed == 3">评价完毕</s:if>
				</td>
			</tr>
			<tr>
				<td>支付状态：</td>
				<td>
					<s:if test="pay_status == 0">未支付</s:if>
					<s:if test="pay_status == 1">已支付</s:if>
				</td>
			</tr>
			<tr>
				<td>支付类型：</td>
				<td>
					<s:if test="pay_type==0">系统内支付</s:if>
					<s:if test="pay_type==1">支付宝</s:if>
					<s:if test="pay_type==2">银联</s:if>
				</td>
			</tr>
			<tr>
				<td>支付时间：</td>
				<td><s:property value="pay_time" /></td>
			</tr>
			<tr>
				<td>支付宝订单号：</td>
				<td><s:property value="pay_relative_id" /></td>
			</tr>
			<tr>
				<td>是否同意：</td>
				<td>
					<s:if test="approved==0">开始</s:if>
					<s:if test="approved==1">同意</s:if>
					<s:if test="approved==2">拒绝</s:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn" onclick="pageGo('doctorServiceOrder.action')" type="button">返回
					</button>
				</td>
			</tr>
		</table>

</body>
</html>
