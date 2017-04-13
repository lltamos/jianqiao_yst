<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function check() {
		
		var name = $("input[name='name']");
		if (name.val() == "") {
			name.tips({
				side : 2,
				msg : '医生名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			name.focus();
			return false;
		} else {
			name.val(jQuery.trim(name.val()));
		}
		var customerId = $("#customerId");
		if (customerId.val() == "-1") {
			customerId.tips({
				side : 2,
				msg : '所属用户不得为空',
				bg : '#AE81FF',
				time : 3
			});

			customerId.focus();
			return false;
		} else {
			customerId.val(jQuery.trim(customerId.val()));
		}
		var customerId = $("#customerId");
		if (customerId.val() == "-1") {
			customerId.tips({
				side : 2,
				msg : '所属用户不得为空',
				bg : '#AE81FF',
				time : 3
			});

			customerId.focus();
			return false;
		} else {
			customerId.val(jQuery.trim(customerId.val()));
		}
		
		return true;
	}
	// 修改医生信息
	function updateDoctor() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : '${ctx}/doctor/doctor-modfiy', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var code = appResult.code;
					var msg = appResult.msg;
					if (code == 0) {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
					} else {
						pageGo('${ctx }/doctor/to-doctor-list');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function pageGo(href) {
		$("#index_div").load(href);
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li>医生列表 <span class="divider">/</span>
		</li>
		<li class="active">修改医生信息：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>医生姓名：</td>
				<td>
					<input name="id" value="${doctor.id }" type="hidden"/>
					<input name="verify" value="${doctor.verify }" type="hidden"/>
					<input type="text" name="name" value="${doctor.name }"/>
				</td>
			</tr>
			<tr>
				<td>所属商家：</td>
				<td>
					<select name="merchantName">
							<option>请选择</option>
							<c:forEach items="${merchantlist }" var="merchant">
								<option value="${merchant.id }_${merchant.name }" <c:if test="${merchant.id == doctor.merchantId}">selected</c:if>>${merchant.name }</option>
							</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>科室：</td>
				<td>
					<select name="officeName">
						<option>请选择</option>
						<c:forEach items="${dicofficelist }" var="dicoffice">
							<option value="${dicoffice.id}_${dicoffice.name }" <c:if test="${dicoffice.id == doctor.officeId}">selected</c:if>>${dicoffice.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>医生专长：</td>
				<td>
					<select name="specName">
						<option>请选择</option>
						<c:forEach items="${dicspeclist }" var="dicspec">
							<option value="${dicspec.id }_${dicspec.name }" <c:if test="${dicspec.id == doctor.specId}">selected</c:if>>${dicspec.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>医生类别：</td>
				<td>
				<select name="type">
					<option value="1" <c:if test="${type == 1}">selected</c:if>>家庭医生</option>
					<option value="2" <c:if test="${type == 2}">selected</c:if>>疑难杂症专家</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>职称：</td>
				<td>
				<select name="titleName">
					<option>请选择</option>
					<c:forEach items="${dictitaillist }" var="dictitail">
						<option value="${dictitail.id }_${dictitail.name }" <c:if test="${dictitail.id == doctor.titleId}">selected</c:if>>${dictitail.name }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>医生介绍：</td>
				<td>
				<textarea rows="" name="des" rows="10" cols="20">${doctor.des } </textarea>
				</td>
			</tr>
			<tr>
				<td>头像图片：</td>
				<td><input type="file" name="image_header" /></td>
			</tr>
			<tr>
				<td>工作证：</td>
				<td><input type="file" name="imageWork1" /><input type="file" name="imageWork2" /></td>
			</tr>
			<tr>
				<td>医院类型：</td>
				<td>
				<select name="hospitalypeName">
					<option>请选择</option>
					<c:forEach items="${dichospitaltypelist }" var="dichospitaltype">
						<option value="${dichospitaltype.id }_${dichospitaltype.name }" <c:if test="${dichospitaltype.id == doctor.hospitalypeId}">selected</c:if>>${dichospitaltype.name }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>医院名称：</td>
				<td><input type="text" name="hospital" value="${doctor.hospital }" /></td>
			</tr>
			<tr>
				<td>所在地址：</td>
				<td><input type="text" name="address" value="${doctor.address}" /></td>
			</tr>
			<tr>
				<td>纬度：</td>
				<td><input type="text" name="latitude" value="${doctor.latitude}" /></td>
			</tr>
			<tr>
				<td>经度：</td>
				<td><input type="text" name="longitude" value="${doctor.longitude}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="updateDoctor()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/doctor/to-doctor-list')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
