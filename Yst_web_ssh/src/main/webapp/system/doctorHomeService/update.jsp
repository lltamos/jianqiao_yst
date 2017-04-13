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
	// 修改到家服务
	function updateDoctor() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : 'doctorHomeServiceInfo!updateAjax', //提交给哪个执行
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
						pageGo('doctorHomeServiceInfo.action');
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
		<li class="active">修改到家服务：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>联系人：</td>
				<td>
					<input type="hidden" name="order_id" value="<s:property value="order_id"/>"/>
					<input type="text" name="contact_name" value="<s:property value="contact_name"/>"/>
				</td>
			</tr>
			<tr>
				<td>服务地址：</td>
				<td>
					<input type="text" name="address" value="<s:property value="address"/>"/>
				</td>
			</tr>
			<tr>
				<td>联系电话：</td>
				<td><input type="text" name="phone" value="<s:property value="phone"/>"/></td>
			</tr>
			<tr>
				<td>家庭成员数量：</td>
				<td><input type="text" name="member_count" value="<s:property value="member_count"/>"/></td>
			</tr>
			<tr>
				<td>服务类型：</td>
				<td>
					<select name="home_doctor_type">
						<option value="0" <s:if test="home_doctor_type == 0">selected</s:if>>小时</option>
						<option value="1" <s:if test="home_doctor_type == 1">selected</s:if>>按年</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="updateDoctor()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('doctorHomeServiceInfo.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
