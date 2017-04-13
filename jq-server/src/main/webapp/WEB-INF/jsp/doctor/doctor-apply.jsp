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
	// 添加医生信息
	function doctorApply() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : '${ctx}/doctor/doctor-apply', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var result = eval("(" + result + ")");
					var codea = result.code;
					var msgs = result.msg;
					if (codea == 0) {
						$("#save").tips({
							side : 2,
							msg : msgs,
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
		<li class="active">添加医生信息：</li>
	</ul>
	<form method="post" id="doctorInfoForm" enctype="multipart/form-data">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>医生姓名：</td>
				<td><input type="text" name="name" value="" /></td>
			</tr>
			<tr>
				<td>所属用户：</td>
				<td>
					<select id="customerId" name="customerId">
						<option value="-1">请选择</option>
						<c:forEach items="${customerlist }" var="customer">
							<option value="${customer.id }">${customer.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>所属分院：</td>
				<td>
					<select name="merchantName">
							<option>请选择</option>
							<c:forEach items="${merchantlist }" var="merchant">
								<option value="${merchant.id }_${merchant.name }">${merchant.name }</option>
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
							<option value="${dicoffice.id}_${dicoffice.name }">${dicoffice.name }</option>
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
							<option value="${dicspec.id }_${dicspec.name }">${dicspec.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>医生类别：</td>
				<td>
				<select name="type">
					<option value="1">家庭医生</option>
					<option value="2">疑难杂症专家</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>职称：</td>
				<td>
				<select name="titleName">
					<option>请选择</option>
					<c:forEach items="${dictitaillist }" var="dictitail">
						<option value="${dictitail.id }_${dictitail.name }">${dictitail.name }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>医生介绍：</td>
				<td>
				<textarea rows="" name="des" rows="10" cols="20"></textarea>
				</td>
			</tr>
			<tr>
				<td>头像图片：</td>
				<td><input type="file" name="imageHeader" /></td>
			</tr>
			<tr>
				<td>工作证：</td>
				<td><input type="file" name="imageHeader" /><input type="file" name="imageHeader" /></td>
			</tr>
			<tr>
				<td>医院类型：</td>
				<td>
				<select name="hospitalypeName">
					<option>请选择</option>
					<c:forEach items="${dichospitaltypelist }" var="dichospitaltype">
						<option value="${dichospitaltype.id }_${dichospitaltype.name }">${dichospitaltype.name }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>医院名称：</td>
				<td><input type="text" name="hospital" value="" /></td>
			</tr>
			<tr>
				<td>所在地址：</td>
				<td><input type="text" name="address" value="" /></td>
			</tr>
			<tr>
				<td>纬度：</td>
				<td><input type="text" name="latitude" value="" /></td>
			</tr>
			<tr>
				<td>经度：</td>
				<td><input type="text" name="longitude" value="" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="doctorApply()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/doctor/to-doctor-list')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
