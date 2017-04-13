<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>项目列表 <span class="divider">/</span>
		</li>
		<li class="active">项目详细信息：</li>
	</ul>

	<table border="3" bordercolor="blue" width="60%" cellspacing="0"
		cellpadding="0">
		<tr>
			<th>项目ID：</th>
			<td><s:property value="product_id" /></td>
		</tr>
		<tr>
			<th>所属总院：</th>
			<td><s:property value="merchant_name" /></td>
		</tr>
		<tr>
			<th>主治医生：</th>
			<td><s:property value="doctor.name" /></td>
		</tr>
		<tr>
			<th>客服账号：</th>
			<td><s:property value="customer_service_id" /></td>
		</tr>
		<tr>
			<th>项目所属：</th>
			<td><s:if test="is_real==0">健康项目</s:if> <s:if test="is_real==1">疑难杂症项目</s:if>
			</td>
		</tr>
		<tr>
			<th>项目是否配送：</th>
			<td><s:if test="product_for==0">到店类</s:if> <s:if
					test="product_for==1">配送类</s:if></td>
		</tr>
		<tr>
			<th>项目类别：</th>
			<td><s:property value="productType.name" /></td>
		</tr>
		<tr>
		<tr>
			<th>项目名称：</th>
			<td><s:property value="name" /></td>
		</tr>
		<tr>
			<th>项目推荐人：</th>
			<td><s:property value="recomm_phone" /></td>
		</tr>
		<tr>
			<th>项目主图：</th>
			<td><s:if test="image1!=null">
					<img alt="image1" src="<s:property value="image1"/>" width="100"
						height="100">
				</s:if> <s:if test="image2!=null">
					<img alt="image2" src="<s:property value="image2"/>" width="100"
						height="100">
				</s:if> <s:if test="image3!=null">
					<img alt="image3" src="<s:property value="image3"/>" width="100"
						height="100">
				</s:if> <s:if test="image4!=null">
					<img alt="image4" src="<s:property value="image4"/>" width="100"
						height="100">
				</s:if></td>
		</tr>
		<tr>
			<th>内容介绍：</th>
			<td><s:property value="des1" /></td>
		</tr>
		<tr>
			<th>总价：</th>
			<td><fmt:formatNumber value="${price/100}" pattern="0.00" />元</td>
		</tr>
		<tr>
			<th>定金：</th>
			<td><fmt:formatNumber value="${deposite_price/100}" pattern="0.00" />元</td>
		</tr>
		<tr>
			<th>分期：</th>
			<td>期数：<s:property value="divide" /> <s:if test="fee1!=null && fee1!=0">，第一期：<fmt:formatNumber
						value="${fee1/100}" pattern="0.00" />元</s:if> <s:if test="fee2!=null && fee2!=0">，第二期：<fmt:formatNumber
						value="${fee2/100}" pattern="0.00" />元</s:if> <s:if test="fee3!=null && fee3!=0">，第三期：<fmt:formatNumber
						value="${fee3/100}" pattern="0.00" />元</s:if> <s:if test="fee4!=null && fee4!=0">，第四期：<fmt:formatNumber
						value="${fee4/100}" pattern="0.00" />元</s:if> <s:if test="fee5!=null && fee5!=0">，第五期：<fmt:formatNumber
						value="${fee5/100}" pattern="0.00" />元</s:if></td>
		</tr>
		<tr>
			<th>运费：</th>
			<td><fmt:formatNumber value="${freight/100}" pattern="0.00" />元</td>
		</tr>
		<tr>
			<th>项目详情：</th>
			<td><s:property
						value="detail_html" escape="false"/></td>
		</tr>
		<tr>
			<th>服务流程：</th>
			<td><s:property value="service_process" /></td>
		</tr>
		<tr>
			<th>如何预约：</th>
			<td><s:property value="des2" /></td>
		</tr>
		<tr>
			<th>退款流程：</th>
			<td><s:property value="refund_process" /></td>
		</tr>
		<tr>
			<th>特别提示：</th>
			<td><s:property value="special" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><button class="btn"
					onclick="pageGo('product.action')" type="button">返回</button></td>
		</tr>
	</table>

</body>
</html>
