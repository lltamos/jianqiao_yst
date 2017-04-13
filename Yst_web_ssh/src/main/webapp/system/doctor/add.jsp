<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var $phone = $("input[name='customer.phone']");
		if ($phone.val() == "") {
			$phone.tips({
				side : 2,
				msg : '名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$phone.focus();
			return false;
		} else {
			$phone.val(jQuery.trim($phone.val()));
		}
		return true;
	}
	// 添加医生信息
	function saveDoctor() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : 'doctor!addAjax', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					if (success != "SUCCESS" ) {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
					} else {
						pageGo('doctor.action');
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
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>医生列表 <span class="divider">/</span>
		</li>
		<li class="active">添加医生信息：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>医生姓名：</td>
				<td><input type="text" name="name" value="" /></td>
			</tr>
			<tr>
				<td>所属用户：</td>
				<td>
					<select name="customer_id">
						<s:iterator value="#customerList">
								<option value="<s:property value="customer_id"/>">
									<s:property value="name" /></option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td>所属商家：</td>
				<td>
					<select name="merchant_id">
							<option>请选择</option>
						<s:iterator value="#merchantList">
							<option value="<s:property value="merchant_id"/>" <s:if test="merchant_id == merchant.merchant_id">selected</s:if>>
							<s:property value="name"/>
							</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td>科室：</td>
				<td>
					<select name="office_id">
						<s:iterator value="#dicOfficeList">
							<option value="<s:property value="office_id"/>">
							<s:property value="name"/>
							</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td>医生专长：</td>
				<td>
					<select name="spec_id">
						<s:iterator value="#dicSpecList">
							<option value="<s:property value="spec_id"/>">
								<s:property value="name"/>
							</option>
						</s:iterator>
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
				<select name="title_id">
					<s:iterator value="#dicTitleList">
						<option value="<s:property value="title_id" />">
							<s:property value="name"/>
						</option>
					</s:iterator>
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
				<td><input type="file" name="image_header" /></td>
			</tr>
			<tr>
				<td>工作证：</td>
				<td><input type="file" name="image_work_1" /><input type="file" name="image_work_2" /></td>
			</tr>
			<tr>
				<td>医院类型：</td>
				<td>
				<select name="hospital_type_id">
					<s:iterator value="#dicHospitalTypeList">
						<option value='<s:property value="hospital_type_id" />'>
						<s:property value="name"/> 
						</option>
					</s:iterator>
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
						type="button" onclick="saveDoctor()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('doctor.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
