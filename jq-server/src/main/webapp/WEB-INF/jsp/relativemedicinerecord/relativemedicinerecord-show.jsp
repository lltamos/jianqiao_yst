<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
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
					${relativemedicinerecord.relativeName }
				</td>
			</tr>
			<tr>
				<td>症状及诊断：</td>
				<td>${relativemedicinerecord.des }</td>
			</tr>
			<tr>
				<td>医生名称：</td>
				<td>${relativemedicinerecord.doctorName }</td>
			</tr>
			<tr>
				<td>科室：</td>
				<td>${relativemedicinerecord.officeName }</td>
			</tr>
			<tr>
				<td>用药时长：</td>
				<td>${relativemedicinerecord.during }</td>
			</tr>
			<tr>
				<td>服用时间：</td>
				<td>${relativemedicinerecord.startDate }</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn" onclick="pageGo('${ctx }/relativemedicinerecord/to-relativemedicinerecord-list')" type="button">返回
					</button>
				</td>
			</tr>
		</table>

</body>
