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
	// 医生审核
	function approDoctor() {
		if (check()) {
			var form = $("#doctorInfoForm");
			/* var doctor_id = $("input[name='doctor_id']");
			var verify = $("input[name='verify']");
			var reject_reason = $("input[name='reject_reason']"); */
			var options = {
				url : 'doctor!approvalDoctor', //提交给哪个执行
				type : 'POST',
				/* data: "{'doctor_id':doctor_id,'verify':verify,'reject_reason':reject_reason}", */
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
		<li class="active">医生审批：</li>
	</ul>
<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>类型：</th>
				<td>
					<input name="doctor_id" value="<s:property value="doctor_id"/>" type="hidden"/>
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
				<th>姓名：</th>
				<td><s:property value="name" /></td>
			</tr>
			<tr>
			<tr>
				<th>工作证件：</th>
				<td>
					<s:if test="image_work_1!=null">
						<img alt="image_work_1" src="<s:property value="image_work_1"/>" width="200" height="200">
					</s:if>
					<s:if test="image_work_2!=null">
						<img alt="image_work_2" src="<s:property value="image_work_2"/>" width="200" height="200">
					</s:if>
				</td>
			</tr>
			<tr>
				<th>工作单位：</th>
				<td><s:property value="hospital" /></td>
			</tr>
			<tr>
				<th>工作单位类型：</th>
				<td><s:property value="str_dicHospitalType"/></td>
			</tr>
			<tr>
				<th>医生专长</th>
				<td><s:property value="str_spec" /></td>
			</tr>
			<tr>
				<th>科室：</th>
				<td><s:property value="str_office" /></td>
			</tr>
			<tr>
				<th>职称：</th>
				<td><s:property value="str_title" /></td>
			</tr>
			<tr>
				<th>个人简介：</th>
				<td><s:property value="des" /></td>
			</tr>
			<tr>
				<th>审核：</th>
				<td>
					<input <c:if test="${verify == 1 }">checked</c:if> name="verify" value="1" type="radio"/>&nbsp;通过&nbsp;&nbsp;&nbsp;&nbsp;
					<input <c:if test="${verify == 2 }">checked</c:if> name="verify" value="2" type="radio"/>&nbsp;不通过
				</td>
			</tr>
			<tr>
				<th>不通过原因：</th>
				<td><input value="<s:property value="reject_reason" />" name="reject_reason" type="text"/></td>
			</tr>
			<tr>
				<td colspan="2"><button class="btn"
						type="button" onclick="approDoctor()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('doctor.action')">返回</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
