<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>病例列表 <span class="divider">/</span>
		</li>
		<li class="active">查看病例详细信息：</li>
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
				<td>症状：</td>
				<td><s:property value="symptom" /></td>
			</tr>
			<tr>
				<td>医生诊断：</td>
				<td><s:property value="diagnose" /></td>
			</tr>
			<tr>
				<td>发病过程：</td>
				<td><s:property value="proce" /></td>
			</tr>
			<tr>
				<td>相关图片：</td>
				<td>
					<s:if test="image1!=null">
						<img alt="image1" src="<s:property value="image1"/>" width="200" height="200">
					</s:if>
					<br/>
					<img alt="image2" src="<s:property value="image2"/>" width="200" height="200">
					<br/>
					<img alt="image3" src="<s:property value="image3"/>" width="200" height="200">
					<br/>
					<img alt="image4" src="<s:property value="image4"/>" width="200" height="200">
					<br/>
					<img alt="image5" src="<s:property value="image5"/>" width="200" height="200">
					<br/>
					<img alt="image6" src="<s:property value="image6"/>" width="200" height="200">
					<br/>
					<img alt="image7" src="<s:property value="image7"/>" width="200" height="200">
					<br/>
					<img alt="image8" src="<s:property value="image8"/>" width="200" height="200">
					<br/>
					<img alt="image9" src="<s:property value="image9"/>" width="200" height="200">
					<br/>
					<img alt="image10" src="<s:property value="image10"/>" width="200" height="200">
					<br/>
					<img alt="image11" src="<s:property value="image11"/>" width="200" height="200">
					<br/>
					<img alt="image12" src="<s:property value="image12"/>" width="200" height="200">
					<br/>
					<img alt="image13" src="<s:property value="image13"/>" width="200" height="200">
					<br/>
					<img alt="image14" src="<s:property value="image14"/>" width="200" height="200">
					<br/>
					<img alt="image15" src="<s:property value="image15"/>" width="200" height="200">
					<br/>
					<img alt="image16" src="<s:property value="image16"/>" width="200" height="200">
					<br/>
					<img alt="image17" src="<s:property value="image17"/>" width="200" height="200">
					<br/>
					<img alt="image18" src="<s:property value="image18"/>" width="200" height="200">
					<br/>
					<img alt="image19" src="<s:property value="image19"/>" width="200" height="200">
					<br/>
					<img alt="image20" src="<s:property value="image20"/>" width="200" height="200">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn" onclick="pageGo('customerIllnessRecord.action')" type="button">返回
					</button>
				</td>
			</tr>
		</table>

</body>
</html>
