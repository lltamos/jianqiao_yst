<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>商户列表 <span class="divider">/</span>
		</li>
		<li class="active">查看商户详细信息：</li>
	</ul>

		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>商铺名称：</th>
				<td><s:property value="name" /></td>
			</tr>
			<tr>
				<th>所属用户：</th>
				<td><s:property value="customer.phone" /></td>
			</tr>
			<tr>
			<tr>
				<th>所属用户名称：</th>
				<td><s:property value="customer.name" /></td>
			</tr>
			<tr>
				<th>商家证件：</th>
				<td><img alt="商家证件_1" src="<s:property value="image_cred_1"/>" width="200" height="200"><img
					alt="商家证件_2" src="<s:property value="image_cred_2"/>"  width="200" height="200"></td>
			</tr>
			<!-- <tr>
				<th>审核情况：</th>
				<td ><s:if test="verify==0">待审核</s:if><s:if test="verify==1">已认证</s:if><s:if test="verify==2">已拒绝</s:if></td>
			</tr> -->
			<tr>
				<th>商铺简介：</th>
				<td><s:property value="des" /></td>
			</tr>
			<!--  <tr>
				<th>不通过原因：</th>
				<td><s:property value="reject_reason" /></td>
			</tr>-->
			<tr>
				<td colspan="2" align="center"><button class="btn" onclick="pageGo('merchant.action')" type="button">返回
		</button></td>
			</tr>
		</table>

</body>
</html>
