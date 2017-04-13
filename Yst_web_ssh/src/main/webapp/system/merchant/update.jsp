<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var name = $("input[name='name']");
		if (name.val() == "") {
			name.tips({
				side : 2,
				msg : '名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			name.focus();
			return false;
		} else {
			name.val(jQuery.trim(name.val()));
		}
		var phone = $("input[name='customer.phone']");
		if (phone.val() == "") {
			phone.tips({
				side : 2,
				msg : '手机号不得为空',
				bg : '#AE81FF',
				time : 3
			});

			phone.focus();
			return false;
		} else {
			phone.val(jQuery.trim(phone.val()));
		}
		var des = $("#des");
		if (des.val() == "") {
			des.tips({
				side : 2,
				msg : '请输入商铺简介',
				bg : '#AE81FF',
				time : 3
			});

			des.focus();
			return false;
		} else {
			des.val(jQuery.trim(des.val()));
		}
		return true;
	}
	// 保存用户信息
	function saveMerchant() {
		if (check()) {
			var form = $("#merchantInfoForm");
			var options = {
				url : 'merchant!updateAjax', //提交给哪个执行
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
		<li class="active">编辑商户：</li>
	</ul>

	<form method="post" id="merchantInfoForm">
		<input name="merchant_id" value="<s:property value="merchant_id" />" type="hidden"/>
		<table border="3" bordercolor="blue" width="40%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>商铺名称：</th>
				<td><input type="text" name="name" value="<s:property value="name" />"/></td>
			</tr>
			<tr>
				<th>原始营业执照：</th>
				<td><img alt="营业执照1" src="<s:property value="image_cred_1" />" width="200" height="200" /><img alt="营业执照2" src="<s:property value="image_cred_2" />" width="200" height="200"/></td>
			</tr>
			<tr>
				<th>重新上传营业执照：</th>
				<td><input type="file" name="image_cred_1" /><input type="file" name="image_cred_2" /></td>
			</tr>
			<tr>
				<th>所属用户手机号：</th>
				<td><input name="customer.phone" type="text" value="<s:property value="customer.phone" />" /></td>
			</tr>
			<tr>
				<th>店铺简介：</th>
				<td><textarea rows="5" cols="100" name="des" id="des"><s:property value="des" /></textarea></td>
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
