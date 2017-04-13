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
	// 到家服务审核
	function approDoctorHomeServiceOrders() {
		if (check()) {
			var form = $("#doctorHomeServiceOrdersInfoForm");
			var options = {
				url : 'doctorHomeServiceOrders!updateAjax', //提交给哪个执行
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
						pageGo('doctorHomeServiceOrders.action');
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
		<li>到家服务列表 <span class="divider">/</span>
		</li>
		<li class="active">到家服务审批：</li>
	</ul>
<form method="post" id="doctorHomeServiceOrdersInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>用户号码：</th>
				<td>
					<input type="hidden" name="order_id" value="<s:property value="order_id" />"/>
					<s:property value="str_customer_phone" />
				</td>
			</tr>
			<tr>
				<th>医生姓名：</th>
				<td><s:property value="str_doctor_name" /></td>
			</tr>
			<tr>
			<tr>
				<th>服务类型：</th>
				<td>
					<s:if test="dicServiceType.service_type_id==0">在线咨询/小时</s:if>
					<s:if test="dicServiceType.service_type_id==1">在线咨询/按年</s:if>
					<s:if test="dicServiceType.service_type_id==2">到家服务/按年</s:if>
					<s:if test="dicServiceType.service_type_id==3">到家服务/小时</s:if>
					<s:if test="dicServiceType.service_type_id==4">会议服务</s:if>
				</td>
			</tr>
			<tr>
				<th>价格：</th>
				<td>
					<input type="text" name="price" value="<s:property value="price" />"/>
				</td>
			</tr>
			<tr>
				<td colspan="2"><button class="btn"
						type="button" onclick="approDoctorHomeServiceOrders()" id="save">审核</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('doctorHomeServiceOrders.action')">返回</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
