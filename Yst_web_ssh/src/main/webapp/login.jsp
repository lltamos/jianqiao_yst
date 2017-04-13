<%@ page pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<head>
<%@ include file="/common/commonHead.jsp"%>
</head>
<style type="text/css">
html {
	overflow-y: scroll;
	vertical-align: baseline;
}

body {
	font-family: Microsoft YaHei, Segoe UI, Tahoma, Arial, Verdana,
		sans-serif;
	font-size: 12px;
	color: #fff;
	height: 80%;
	line-height: 1;
	background: #999
}

* {
	margin: 0;
	padding: 0
}

ul,li {
	list-style: none
}

h1 {
	font-size: 30px;
	font-weight: 700;
	text-shadow: 0 1px 4px rgba(0, 0, 0, .2)
}

.login-box {
	width: 410px;
	margin: 120px auto 0 auto;
	text-align: center;
	padding: 30px;
	background: url(${pageContext.request.contextPath}/images/mask.png);
	border-radius: 10px;
}

.login-box .name,.login-box .password,.login-box .code,.login-box {
	font-size: 16px;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .1)
}

.login-box label {
	display: inline-block;
	width: 100px;
	text-align: right;
	vertical-align: middle
}

.login-box #code {
	width: 120px
}

.login-box .codeImg {
	float: right;
	margin-top: 26px;
}

.login-box img {
	width: 148px;
	height: 42px;
	border: none
}

input[type=text],input[type=password],select {
	width: 270px;
	height: 42px;
	margin-top: 25px;
	padding: 0px 15px;
	border: 1px solid rgba(255, 255, 255, .15);
	border-radius: 6px;
	color: #fff;
	letter-spacing: 2px;
	font-size: 16px;
	background: transparent;
}

button {
	margin: 20px auto 0 auto;
	cursor: pointer;
	width: 100%;
	height: 44px;
	padding: 0;
	background: #ef4300;
	border: 1px solid #ff730e;
	border-radius: 6px;
	font-weight: 700;
	color: #fff;
	font-size: 24px;
	letter-spacing: 15px;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .1)
}

input:focus {
	outline: none;
	box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset, 0 2px 7px 0
		rgba(0, 0, 0, .2)
}

button:hover {
	box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset, 0 2px 7px 0
		rgba(0, 0, 0, .2)
}

.screenbg {
	position: fixed;
	bottom: 0;
	left: 0;
	z-index: -999;
	overflow: hidden;
	width: 100%;
	height: 100%;
	min-height: 100%;
}

.screenbg ul li {
	display: block;
	list-style: none;
	position: fixed;
	overflow: hidden;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: -1000;
	float: right;
}

.screenbg ul a {
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	display: inline-block;
	margin: 0;
	padding: 0;
	cursor: default;
}

.screenbg a img {
	vertical-align: middle;
	display: inline;
	border: none;
	display: block;
	list-style: none;
	position: fixed;
	overflow: hidden;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: -1000;
	float: right;
}

.bottom {
	margin: 8px auto 0 auto;
	width: 100%;
	position: fixed;
	text-align: center;
	bottom: 0;
	left: 0;
	overflow: hidden;
	padding-bottom: 8px;
	color: #ccc;
	word-spacing: 3px;
	zoom: 1;
}

.bottom a {
	color: #FFF;
	text-decoration: none;
}

.bottom a:hover {
	text-decoration: underline;
}
</style>
<script type="text/javascript">
	//-- 重新加载验证码表单
	function loadimage() {
		$("#randImage").attr("src",
				"user!getVerifyCode.action?r=" + Math.random());
	}

	// 检查输入
	function check() {
		var type = $("#type").val();
		var $usernameField = (type == 0) ? $("input[name='login_name']")
				: (type == 1) ? $("input[name='phone']") : (type == 2) ? $("input[name='phone']") 
				: (type == 3) ? $("input[name='phone']") : (type == 4) ? $("input[name='phone']") : "";
		if ($usernameField.val() == "") {
			$usernameField.tips({
				side : 2,
				msg : '登录账号不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$usernameField.focus();
			return false;
		} else {
			$usernameField.val(jQuery.trim($usernameField.val()));
		}

		var $passwordField = $("#password");
		if ($passwordField.val() == "") {
			$passwordField.tips({
				side : 2,
				msg : '密码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$passwordField.focus();
			return false;
		}

		var $imageCodeField = $(".code > input");
		if ($imageCodeField.val() == "") {
			$imageCodeField.tips({
				side : 1,
				msg : '验证码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$imageCodeField.focus();
			return false;
		}

		$("#loginBox").tips({
			side : 1,
			msg : '正在登录 , 请稍后 ...',
			bg : '#68B500'
		});

		return true;
	}
	// 登录
	function login() {
		if (!check()) {
			return;
		}
		var $form = $("form");
		var param = $form.serialize();
		$.ajax({
			type : 'POST',
			data : param,
			url : "user!login.action",
			dateType : "JSON",
			success : function(result) {

				//从返回的json数据中获取结果信息
				//结果提示信息
				var resultInfo = eval("(" + result + ")").resultInfo;
				var url = eval("(" + result + ")").url;
				var message = resultInfo.message;
				$("#loginBox").tips({
					side : 2,
					msg : message,
					bg : '#68B500',
					time : 10
				});
				if (url != null) {
					window.location = url;
				}
				var type = resultInfo.type;
				if (type == 1) {
					setTimeout("toMainPage()", 1000);
				}
			}
		});
	}
	function webLogin(num) {
		if (check()) {
			var form = $("form");
			var param = form.serialize();
			$.ajax({
				type : 'POST',
				data : param,
				url : "customer!webLogin.action?loginRole="+num,
				dateType : "JSON",
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					$("#loginBox").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 10
					});
					if (success == "SUCCESS") {
						window.location = "user!index";
					}
				} //显示操作提示
			});
		}
	}
	function toMainPage() {
		window.location.href = '${pageContext.request.contextPath}';
	}
	function checkType() {
		var type = $("#type");
		var login_name = $("#login_name");
		var name_span = $("#name_span");
		var btn = $("#btn");
		if (type.val() == 0) {
			name_span.text("用户名：  ");
			login_name.attr("name", "login_name");
			btn.attr("onclick", "login()");
		} else if (type.val() == 1) {
			name_span.text("手机号：  ");
			login_name.attr("name", "phone");
			btn.attr("onclick", "webLogin(5)");
		}else if (type.val() == 2) {
			name_span.text("手机号：  ");
			login_name.attr("name", "phone");
			btn.attr("onclick", "webLogin(6)");
		}else if (type.val() == 3) {
			name_span.text("手机号：  ");
			login_name.attr("name", "phone");
			btn.attr("onclick", "webLogin(7)");
		}else if (type.val() == 4) {
			name_span.text("手机号：  ");
			login_name.attr("name", "phone");
			btn.attr("onclick", "webLogin(8)");
		}
	}
	// 进入页面后，请求一个验证码 
	$(function() {
		loadimage();
		$(".screenbg ul li").each(function() {
			$(this).css("opacity", "0");
		});
		$(".screenbg ul li:first").css("opacity", "1");
		var index = 0;
		var t;
		var li = $(".screenbg ul li");
		var number = li.size();
		function change(index) {
			li.css("visibility", "visible");
			li.eq(index).siblings().animate({
				opacity : 0
			}, 3000);
			li.eq(index).animate({
				opacity : 1
			}, 3000);
		}
		function show() {
			index = index + 1;
			if (index <= number - 1) {
				change(index);
			} else {
				index = 0;
				change(index);
			}
		}
		t = setInterval(show, 8000);
		//根据窗口宽度生成图片宽度
		var width = $(window).width();
		$(".screenbg ul img").css("width", width + "px");
	});
	$(document).keydown(function(e) {
		var theEvent = window.event || e;
		var code = theEvent.keyCode || theEvent.which;
		if (code == 13) {
			$("#btn").click();
		}
	});
	// 用户注册 
	//popWin.showWin("800", "600", "注册用户",pageUrl);
</script>
<body>

	<div class="login-box" id="loginBox">
		<h1>健桥系统登陆</h1>
		<form>
			<div class="type">
				类&nbsp;&nbsp;&nbsp;&nbsp;型：<select name="type" id="type"
					tabindex="1" onchange="checkType()">
					<option value="0">管理员</option>
					<option value="1">我是商户</option>
					<option value="2">普通用户</option>
					<option value="3">我是医生</option>
					<option value="4">我是推广人</option>
				</select>
			</div>
			<div class="name">
				<span id="name_span">用户名： </span><input type="text"
					name="login_name" id="login_name" tabindex="2" autocomplete="off" />
			</div>
			<div class="password">
				密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"
					maxlength="16" id="password" tabindex="3" />
			</div>
			<div class="code">
				验证码：<input type="text" name="imageCode" maxlength="4" id="code"
					tabindex="4" />
				<div class="codeImg">
					<img onclick="loadimage();" title="换一张试试" id="randImage" src="">
				</div>
			</div>
			<div class="login">
				<button id="btn" type="button" style="background:#666;"
					onclick='login()' tabindex="5">登录</button>
			</div>
		</form>
	</div>

	<div class="screenbg">
		<ul>
			<li><a href="javascript:;"><img src="images/background.jpg"></a></li>
		</ul>
	</div>
</body>
</html>
