<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var $phone = $("input[name='login_name']");
		if ($phone.val() == "") {
			$phone.tips({
				side : 2,
				msg : '用户名不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$phone.focus();
			return false;
		} else {
			$phone.val(jQuery.trim($phone.val()));
		}
		var $password = $("input[name='password']");
		if ($password.val() == "") {
			$password.tips({
				side : 2,
				msg : '密码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$password.focus();
			return false;
		} else {
			$password.val(jQuery.trim($password.val()));
		}

		var $password2 = $("input[name='password2']");
		if ($password2.val() == "") {
			$password2.tips({
				side : 2,
				msg : '确认密码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$password2.focus();
			return false;
		} else {
			$password2.val(jQuery.trim($password2.val()));
		}

		if ($password2.val() != $password.val()) {
			$password2.tips({
				side : 2,
				msg : '两次密码不一致',
				bg : '#AE81FF',
				time : 3
			});
			$password2.focus();
			return false;
		}
		return true;
	}
	function saveUser() {
		if (check()) {
			var form = $("#userInfoForm");
			var options = {
				url : 'user!addAjax', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var resultInfo = eval("(" + result + ")").resultInfo;
					var message = resultInfo.message;
					$("#save").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 10
					});
					var type = resultInfo.type;
					if (type == 1) {
						pageGo("user.action");
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
</script>
<body>
	<ul class="breadcrumb">
		<li class="active">添加用户信息：</li>
	</ul>
	<br>
	<form id="userInfoForm" method="post">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>用户名：</th>
				<td><input type="text" name="login_name" id="login_name"/></td>
			</tr>
			<tr>
				<th>密码：</th>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<th>确认密码：</th>
				<td><input name="password2" type="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveUser()" id="save">保存</button>
						<button class="btn" type="button"
						onclick="pageGo('user.action')">返回</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
