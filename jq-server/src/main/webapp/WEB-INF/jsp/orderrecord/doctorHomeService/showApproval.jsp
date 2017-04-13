<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>到家服务列表 <span class="divider">/</span>
		</li>
		<li class="active">查看到家服务详细信息：</li>
	</ul>

		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>用户号码：</th>
				<td><s:property value="str_customer_phone" /></td>
			</tr>
			<tr>
				<th>医生姓名：</th>
				<td><s:property value="str_doctor_name" /></td>
			</tr>
			<tr>
			<tr>
				<th>类型：</th>
				<td>
					<%-- <s:property value="str_service_type_name" /> --%>
					<s:if test="dicServiceType.service_type_id==0">在线咨询/小时</s:if>
					<s:if test="dicServiceType.service_type_id==1">在线咨询/按年</s:if>
					<s:if test="dicServiceType.service_type_id==2">到家服务/按年</s:if>
					<s:if test="dicServiceType.service_type_id==3">到家服务/小时</s:if>
					<s:if test="dicServiceType.service_type_id==4">会议服务</s:if>
				</td>
			</tr>
			<tr>
				<th>是否审核：</th>
				<td>
					<s:if test="pay_status==3&&(dicServiceType.service_type_id==2||dicServiceType.service_type_id==3||dicServiceType.service_type_id==4)">未审核</s:if>
					<s:if test="pay_status==0&&(dicServiceType.service_type_id==2||dicServiceType.service_type_id==3||dicServiceType.service_type_id==4)">已审核</s:if>
					<s:if test="pay_status==1&&(dicServiceType.service_type_id==2||dicServiceType.service_type_id==3||dicServiceType.service_type_id==4)">已支付</s:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn" onclick="pageGo('doctorHomeServiceOrders.action')" type="button">返回
		</button></td>
			</tr>
		</table>

</body>
</html>
