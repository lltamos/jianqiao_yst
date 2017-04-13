<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li>医生列表 <span class="divider">/</span>
		</li>
		<li class="active">查看医生详细信息：</li>
	</ul>
	<form class="form-horizontal" id="productInfoForm" method="post"  enctype="multipart/form-data">
		<div class="control-group">
			<label class="control-label" for="name">医生名称：</label>
			<div class="controls">
				${doctors.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">所属医院：</label>
			<div class="controls">
				${doctors.merchantName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">医院类型：</label>
			<div class="controls">
				${doctors.hospitalypeName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">科室：</label>
			<div class="controls">
				${doctors.officeName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">医生专长：</label>
			<div class="controls">
				${doctors.specName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">职称：</label>
			<div class="controls">
				${doctors.titleName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">医生介绍：</label>
			<div class="controls">
				${doctors.des }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">头像图片：</label>
			<div class="controls">
				<s:if test="image_header!=null">
					<img alt="image_header" src="${img_service }/${doctors.image_header }" width="200" height="200">
				</s:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">工作证：</label>
			<div class="controls">
				<s:if test="image_work_1!=null">
					<img alt="image_work_1" src="${img_service }/${doctors.imageWork1 }" width="200" height="200">
				</s:if>
				<s:if test="image_work_2!=null">
					<img alt="image_work_2" src="${img_service }/${doctors.imageWork2 }" width="200" height="200">
				</s:if>
			</div>
		</div>
		
		<div class="control-group">
			<div class="controls">
				<button class="btn" onclick="pageGo('${ctx }/doctor/to-doctor-list')" type="button">返回
		</button>
			</div>
		</div>
	
	</form>
		<%-- <table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>医生名称：</th>
				<td>${doctors.name }</td>
			</tr>
			<tr>
				<th>所属医院：</th>
				<td>${doctors.merchantName }</td>
			</tr>
			<tr>
				<th>科室：</th>
				<td>${doctors.officeName }</td>
			</tr>
			<tr>
			<tr>
				<th>医生专长：</th>
				<td>${doctors.specName }</td>
			</tr>
			<tr>
				<th>医生类别：</th>
				<td>
					<s:if test='type=="1"'>
					家庭医生
					</s:if>
					<s:if test='{#staffinfo.grade == "高"}'></s:if><s:else>中级</s:else>
					<s:if test='type=="2"'>
					疑难杂症专家
					</s:if>
				</td>
			</tr>
			<tr>
				<th>职称：</th>
				<td>${doctors.titleName }</td>
			</tr>
			<tr>
				<th>医生介绍</th>
				<td>${doctors.des }</td>
			</tr>
			<tr>
				<th>头像图片：</th>
				<td>
					<s:if test="image_header!=null">
						<img alt="image_header" src="${img_service }/${doctors.image_header }" width="200" height="200">
					</s:if>
				</td>
			</tr>
			<tr>
				<th>工作证：</th>
				<td colspan="2">
					<s:if test="image_work_1!=null">
						<img alt="image_work_1" src="${img_service }/${doctors.imageWork1 }" width="200" height="200">
					</s:if>
					<s:if test="image_work_2!=null">
						<img alt="image_work_2" src="${img_service }/${doctors.imageWork2 }" width="200" height="200">
					</s:if>
				</td>
			</tr>
			<tr>
				<th>医院类型：</th>
				<td>${doctors.hospitalypeName }</td>
			</tr>
			<tr>
				<th>医院名称：</th>
				<td>${doctors.hospital }</td>
			</tr>
			<tr>
				<th>所在地址：</th>
				<td>${doctors.address }</td>
			</tr>
			<tr>
				<th>纬度：</th>
				<td>${doctors.latitude }</td>
			</tr>
			<tr>
				<th>经度：</th>
				<td>${doctors.longitude }</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn" onclick="pageGo('${ctx }/doctor/to-doctor-list')" type="button">返回
		</button></td>
			</tr>
		</table> --%>

</body>
