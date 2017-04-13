<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var login_name = $("input[name='login_name']");
		if (login_name.val() == "") {
			login_name.tips({
				side : 2,
				msg : '用户名不得为空',
				bg : '#AE81FF',
				time : 3
			});

			login_name.focus();
			return false;
		} else {
			login_name.val(jQuery.trim(login_name.val()));
		}
		var password = $("input[name='password']");
		if (password.val() == "") {
			password.tips({
				side : 2,
				msg : '新密码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			password.focus();
			return false;
		} else {
			password.val(jQuery.trim(password.val()));
		}
		var password2 = $("input[name='password2']");
		if (password2.val() == "") {
			password2.tips({
				side : 2,
				msg : '重复密码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			password2.focus();
			return false;
		} else {
			password2.val(jQuery.trim(password2.val()));
		}
		if(password2.val()!=password.val()){
			password2.tips({
				side : 2,
				msg : '两次输入密码不相同',
				bg : '#AE81FF',
				time : 3
			});
			password.focus();
			return false;
		}
		return true;
	}
	// 保存用户信息
	function saveUser() {
		if (check()) {
			var form = $("#userInfoForm");
			var options = {
				url : 'user!updateAjax', //提交给哪个执行
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
						pageGo('user.action');
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
		<li>管理员列表 <span class="divider">/</span>
		</li>
		<li class="active">修改人员信息：</li>
	</ul>
	<form class="form-horizontal" id="userInfoForm" method="POST">
		<input name="user_id" value="<s:property value="user_id" />"
			type="hidden" /> 
		<table border="3" bordercolor="blue" width="40%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>用户名：</th>
				<td><s:property value="login_name" /></td>
			</tr>
			<tr>
				<th>新的密码：</th>
				<td><input name="password" type="password" /></td>
			</tr>
			<tr>
				<th>重复密码：</th>
				<td><input name="password2" type="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn" type="button"
						onclick="saveUser()" id="save">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('user.action')">返回</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
