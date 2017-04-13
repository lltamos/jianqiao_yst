<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>医生列表 <span class="divider">/</span>
		</li>
		<li class="active">查看医生详细信息：</li>
	</ul>

		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>医生名称：</th>
				<td><s:property value="name" /></td>
			</tr>
			<tr>
				<th>所属商家：</th>
				<td>
					<s:iterator value="#merchantList">
						<s:if test="merchant_id == merchant.merchant_id">
							<s:property value="name" />
						</s:if>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<th>科室：</th>
				<td><s:property value="str_office" /></td>
			</tr>
			<tr>
			<tr>
				<th>医生专长：</th>
				<td><s:property value="str_spec" /></td>
			</tr>
			<tr>
				<th>医生类别：</th>
				<td>
					<s:if test='type=="1"'>
					家庭医生
					</s:if>
					<%-- <s:if test='{#staffinfo.grade == "高"}'></s:if><s:else>中级</s:else> --%>
					<s:if test='type=="2"'>
					疑难杂症专家
					</s:if>
				</td>
			</tr>
			<tr>
				<th>职称：</th>
				<td><s:property value="str_title"/></td>
			</tr>
			<tr>
				<th>医生介绍</th>
				<td><s:property value="des" /></td>
			</tr>
			<tr>
				<th>头像图片：</th>
				<td>
					<s:if test="image_header!=null">
						<img alt="image_header" src="<s:property value="image_header"/>" width="200" height="200">
					</s:if>
				</td>
			</tr>
			<tr>
				<th>工作证：</th>
				<td colspan="2">
					<s:if test="image_work_1!=null">
						<img alt="image_work_1" src="<s:property value="image_work_1"/>" width="200" height="200">
					</s:if>
					<s:if test="image_work_2!=null">
						<img alt="image_work_2" src="<s:property value="image_work_2"/>" width="200" height="200">
					</s:if>
				</td>
			</tr>
			<tr>
				<th>医院类型：</th>
				<td><s:property value="str_dicHospitalType" /></td>
			</tr>
			<tr>
				<th>医院名称：</th>
				<td><s:property value="hospital" /></td>
			</tr>
			<tr>
				<th>所在地址：</th>
				<td><s:property value="address" /></td>
			</tr>
			<tr>
				<th>纬度：</th>
				<td><s:property value="latitude" /></td>
			</tr>
			<tr>
				<th>经度：</th>
				<td><s:property value="longitude" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn" onclick="pageGo('doctor.action')" type="button">返回
		</button></td>
			</tr>
		</table>

</body>
</html>
