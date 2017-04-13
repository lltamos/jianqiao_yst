<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function check() {
		var $phone = $("input[name='phone']");
		if ($phone.val() == "") {
			$phone.tips({
				side : 2,
				msg : '手机号不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$phone.focus();
			return false;
		} else {
			$phone.val(jQuery.trim($phone.val()));
		}
		var idCode = $("input[name='code']");
		if (idCode.val() == "") {
			idCode.tips({
				side : 2,
				msg : '验证码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			idCode.focus();
			return false;
		} else {
			idCode.val(jQuery.trim(idCode.val()));
		}
		var password = $("input[name='password']");
		if (password.val() == "") {
			password.tips({
				side : 2,
				msg : '密码不得为空',
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
				msg : '确认密码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			password2.focus();
			return false;
		} else {
			password2.val(jQuery.trim(password2.val()));
		}

		if (password.val() != password2.val()) {
			password2.tips({
				side : 2,
				msg : '两次密码不一致',
				bg : '#AE81FF',
				time : 3
			});
			return false;
		}
		return true;
	}
	function check() {
		var $phone = $("input[name='phone']");
		if ($phone.val() == "") {
			$phone.tips({
				side : 2,
				msg : '手机号不得为空',
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
	function sendMs() {
		//var $form = $("form");
		var phone = $("input[id=phone]").val();
		
		//var param = $form.serialize();
		if (check()) {
			$("#sendMessage").text("发送中。。。。");
			$.ajax({
				type : 'POST',
				data : {"customerPhone":phone},
				url : "${ctx}/customer/customer-add-sendCode",
				success : function(data) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var resultInfo = eval("(" + result + ")");
					
				}
			});
		}
	}
	function toMainPage() {
		window.location.href = '${pageContext.request.contextPath}';
	}
	// 保存用户信息
	function saveCustomer() {
		if (check()) {
			var form = $("#customerInfoForm");
			var options = {
				url : '${ctx}/customer/customer-save', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					var result = JSON.parse(result);
					var message = result.msg;
					var code = result.code;
					if (success != "SUCCESS") {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
						if (code==1) {
							pageGo('${ctx }/customer/to-customer-list');
						}
					} else {
						pageGo('${ctx}/customer/to-customer-list');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a> <span class="divider">/</span></li>
		<li>客户列表 <span class="divider">/</span>
		</li>
		<li class="active">添加客户信息：</li>
	</ul>

	<form method="post" id="customerInfoForm" method="post" enctype="multipart/form-data">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			
			<tr>
				<td>用户手机号：</td>
				<td><input type="text" name="phone" id="phone" value="${phone }"/>
					<button id="sendMessage" class="btn" onclick="sendMs()"
						type="button">发送短信验证码</button></td>
			</tr>
			<tr>
				<td>短信验证码：</td>
				<td><input type="text" name="code" id="code" value="" /></td>
			</tr>
		<tr>
				<td>头像：</td>
				<td><input name="image" type="file" src="" /></td>
			</tr>  
			<tr>
				<td>性别：</td>
				<td><input type="radio" name="sex" value="1" />男 <input
					type="radio" name="sex" value="0" /> 女</td>
			</tr>
			<tr>
				<td>修改密码：</td>
				<td><input name="password" id="password" type="password" value="${password }"/></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input name="password2" id ="password2" type="password" value="${password }"/></td>
			</tr>
			<tr>
				<td>出生日期：</td>
				<td><input name="birth_day" type="text" value="${birthday }"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="checkbox" value="1" name="isLock" />未锁定
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="checkbox" value="0" name="isLock" />已锁定</td>
			</tr>
			<tr>
				<td colspan="2"><input type="checkbox" value="1" name="deleted" />未删除&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" value="0" name="deleted" />已删除</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveCustomer()" id="save">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('${ctx}/customer/to-customer-list')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>