<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<%String doctor_id=request.getParameter("doctor_id");%>
<html>
<script type="text/javascript">
	function check() {
		var express_name = $("input[name='express_name']");
		if (express_name.val() == "") {
			express_name.tips({
				side : 2,
				msg : '应用名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			express_name.focus();
			return false;
		} else {
			express_name.val(jQuery.trim(express_name.val()));
		}
		var express_no = $("input[name='express_no']");
		if (express_no.val() == "") {
			express_no.tips({
				side : 2,
				msg : '应用名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			express_no.focus();
			return false;
		} else {
			express_no.val(jQuery.trim(express_no.val()));
		}
		return true;
	}
	function saveExpress() {
		if (check()) {
			var form = $("#expressInfoForm");
			var options = {
				url : 'express!sendAjax', //提交给哪个执行
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
						pageGo('productOrder.action');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>应用列表 <span class="divider">/</span>
		</li>
		<li class="active">上传新应用：<a href="1111"></a></li>
	</ul>
	<form method="post" id="expressInfoForm">
		<input name="order_id" type="hidden" value="<s:property value="pay_relative_id" />" />
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			
			<tr>
				<td>客户名称：</td>
				<td><s:property value="customer.name" /></td>
			</tr>
			<tr>
				<td>客户手机号：</td>
				<td><s:property value="customer.phone" /></td>
			</tr>
			<tr>
				<td>客户地址：</td>
				<td><s:property value="customerAddress.address" /></td>
			</tr>
			<tr>
				<td>服务包信息：</td>
				<td><s:property value="product.name" /></td>
			</tr>
			<tr>
				<td>订单编号：</td>
				<td><s:property value="pay_relative_id" /></td>
			</tr>
			<tr>
				<td>物流名称：</td>
				<td><input type="text" name="express_name" /></td>
			</tr>
			<tr>
				<td>物流运单编号：</td>
				<td><input type="text" name="express_no" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveExpress()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('productOrder.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
