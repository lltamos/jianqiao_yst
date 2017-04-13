<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var verify = $("input[name='verify']:checked");
		if (verify.val()==null) {
			var ok = $("#ok");
			ok.tips({
				side : 2,
				msg : '请选择是否通过',
				bg : '#AE81FF',
				time : 3
			});
			return false;
		} else if (verify.val() == 2) {
			var reason = $("input[name='reject_reason']");
			if (reason.val() == "") {
				reason.tips({
					side : 2,
					msg : '请填写原因',
					bg : '#AE81FF',
					time : 3
				});

				reason.focus();
				return false;
			} else {
				reason.val(jQuery.trim(reason.val()));
			}
		}
		return true;
	}
	// 保存用户信息
	function saveMerchant() {
		if (check()) {
			var form = $("#merchantInfoForm");
			var options = {
				url : 'merchant!updateInfo', //提交给哪个执行
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
						pageGo('merchant.action');
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
		<li>商户列表 <span class="divider">/</span>
		</li>
		<li class="active">审核商户：</li>
	</ul>

	<form method="post" id="merchantInfoForm">
		<input name="merchant_id" value="<s:property value="merchant_id" />" type="hidden"/>
		<table border="3" bordercolor="blue" width="40%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>商铺名称：</th>
				<td><s:property value="name" /></td>
			</tr>
			<tr>
				<th>所属用户：</th>
				<td><s:property value="customer.phone" /></td>
			</tr>
			<tr>
			<tr>
				<th>所属用户名称：</th>
				<td><s:property value="customer.name" /></td>
			</tr>
			<tr>
				<th>商家证件：</th>
				<td><img alt="商家证件_1" src="<s:property value="image_cred_1" />"
					width="200" height="200" /><img alt="商家证件_2"
					src="<s:property value="image_cred_2"/>" width="200" height="200" /></td>
			</tr>
			<tr>
				<th>审核：</th>
				<td  id="ok"><input name="verify" value="1" type="radio" />通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					name="verify" type="radio" value="2" />不通过</td>
			</tr>
			<tr>
				<th>商家描述</th>
				<td><s:property value="des" /></td>
			</tr>
			<tr>
				<th>不通过原因：</th>
				<td><input type="text" name="reject_reason" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveMerchant()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('merchant.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
