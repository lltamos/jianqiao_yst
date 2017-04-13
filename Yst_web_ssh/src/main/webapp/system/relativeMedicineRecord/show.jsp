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
				<td>所属相关人员：</td>
				<td>
					<s:property value="str_relative" />
				</td>
			</tr>
			<tr>
				<td>症状及诊断：</td>
				<td><s:property value="des" /></td>
			</tr>
			<tr>
				<td>医生名称：</td>
				<td><s:property value="doctor_name" /></td>
			</tr>
			<tr>
				<td>科室：</td>
				<td><s:property value="office_name" /></td>
			</tr>
			<tr>
				<td>用药时长：</td>
				<td><s:property value="during" /></td>
			</tr>
			<tr>
				<td>服用时间：</td>
				<td><s:property value="start_date" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn" onclick="pageGo('relativeMedicineRecord.action')" type="button">返回
					</button>
				</td>
			</tr>
		</table>

</body>
</html>
