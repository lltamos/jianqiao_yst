<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
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
					${customerIllnessRecord.relativeName }
				</td>
			</tr>
			<tr>
				<td>症状：</td>
				<td>${customerIllnessRecord.symptom }</td>
			</tr>
			<tr>
				<td>医生诊断：</td>
				<td>${customerIllnessRecord.diagnose}</td>
			</tr>
			<tr>
				<td>发病过程：</td>
				<td>${customerIllnessRecord.proce}</td>
			</tr>
			<tr>
				<td>相关图片：</td>
				<td>
					<c:if test="${customerIllnessRecord.image1 !=null }">
						<img alt="image1" src="${customerIllnessRecord.image1 }" width="200" height="200">
					</c:if>
					<br/>
					<img alt="image2" src="${customerIllnessRecord.image2 }" width="200" height="200">
					<br/>
					<img alt="image3" src="${customerIllnessRecord.image3 }" width="200" height="200">
					<br/>
					<img alt="image4" src="${customerIllnessRecord.image4 }" width="200" height="200">
					<br/>
					<img alt="image5" src="${customerIllnessRecord.image5 }" width="200" height="200">
					<br/>
					<img alt="image6" src="${customerIllnessRecord.image6 }" width="200" height="200">
					<br/>
					<img alt="image7" src="${customerIllnessRecord.image7 }" width="200" height="200">
					<br/>
					<img alt="image8" src="${customerIllnessRecord.image8 }" width="200" height="200">
					<br/>
					<img alt="image9" src="${customerIllnessRecord.image9 }" width="200" height="200">
					<br/>
					<img alt="image10" src="${customerIllnessRecord.image10 }" width="200" height="200">
					<br/>
					<img alt="image11" src="${customerIllnessRecord.image11 }" width="200" height="200">
					<br/>
					<img alt="image12" src="${customerIllnessRecord.image12 }" width="200" height="200">
					<br/>
					<img alt="image13" src="${customerIllnessRecord.image13 }" width="200" height="200">
					<br/>
					<img alt="image14" src="${customerIllnessRecord.image14 }" width="200" height="200">
					<br/>
					<img alt="image15" src="${customerIllnessRecord.image15 }" width="200" height="200">
					<br/>
					<img alt="image16" src="${customerIllnessRecord.image16 }" width="200" height="200">
					<br/>
					<img alt="image17" src="${customerIllnessRecord.image17 }" width="200" height="200">
					<br/>
					<img alt="image18" src="${customerIllnessRecord.image18 }" width="200" height="200">
					<br/>
					<img alt="image19" src="${customerIllnessRecord.image19 }" width="200" height="200">
					<br/>
					<img alt="image20" src="${customerIllnessRecord.image20 }" width="200" height="200">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn" onclick="pageGo('${ctx }/customerillnessrecord/to-customerillnessrecord-list')" type="button">返回
					</button>
				</td>
			</tr>
		</table>

</body>
