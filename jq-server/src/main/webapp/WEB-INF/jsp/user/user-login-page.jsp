<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
	<!--[if IE]>

<style type="text/css">
.login_title { display:block; padding:25px 30px 0 38px;}

</style>

<![endif]-->
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

ul, li {
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
	background: url(/themes/images/mask.png);
	border-radius: 10px;
}

.login-box .name, .login-box .password, .login-box .code, .login-box {
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

input[type=text], input[type=password], select {
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
				"${ctx}/user/user-code?r=" + Math.random());
	}

	// 检查输入
	function check() {
		var $usernameField = $("#login_name");
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

		var $imageCodeField = $("#code");
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
	function login(url, href) {
		if (!check()) {
			return;
		}
		var $form = $("form");
		var param = $form.serialize();
		$.ajax({
			type : 'POST',
			data : param,
			url : url,
			dateType : "JSON",
			success : function(result) {
				//从返回的json数据中获取结果信息
				//结果提示信息
				var result = JSON.parse(result);
				var message = result.msg;
				var code = result.code;
				$("#loginBox").tips({
					side : 2,
					msg : message,
					bg : '#68B500',
					time : 5
				});
				if (code==1) {
					window.location = href;
				} else {
					loadimage();
				}
			}
		});
	}
	function checkType() {
		var type = $("#type");
		var login_name = $("#login_name");
		var name_span = $("#name_span");
		var btn = $("#btn");
		if (type.val() == 0) {
			name_span.text("用户名：  ");
			login_name.attr("name", "userName");
			$("#password").attr("name", "userPassword");
			btn.attr("onclick", "login('${ctx}/user/user-login','${ctx}/user/user-index')");
		} else{
			name_span.text("手机号：  ");
			login_name.attr("name", "phone");
			$("#password").attr("name", "password");
			btn.attr("onclick", "login('${ctx}/customer/cusotmer-login','${ctx}/user/user-index')");
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
	function intoForgetPassword() {
		window.location.href = "${ctx}/ydmvc/supplier/toForgetPasswordPage.do";
	}
	// 用户注册 
	//popWin.showWin("800", "600", "注册用户",pageUrl);
</script>
</head>
<body>

	<div class="login-box" id="loginBox">
		<h1>健桥系统登陆</h1>
		<form>
			<div class="type">
				&nbsp;&nbsp;&nbsp;&nbsp;<select name="type" id="type" tabindex="1"
					onchange="checkType()" style="background: #999999;">
					<option value="0">管理员版</option>
					<option value="1">我是医生</option>
					<option value="2">总院后台登录</option>
					<option value="3">推荐人后台登录</option>
					<%-- <option value="3">易店商户版</option> --%>
					<%-- <option value="4">通权管理员版</option>  --%>
					<%--<option value="5">客服管理员版</option> --%>
					<%--<option value="6">财务管理员版</option>--%>
				</select>
			</div>
			<div class="name">
				<span id="name_span">用户名： </span><input type="text"
					name="userName" id="login_name" tabindex="1" autocomplete="off" />
			</div>
			<div class="password">
				密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="userPassword"
					maxlength="16" id="password" tabindex="2" />
			</div>
			<div class="code">
				验证码：<input type="text" name="imageCode" maxlength="4" id="code"
					tabindex="3" />
				<div class="codeImg">
					<img onclick="loadimage();" title="换一张试试" id="randImage" src="" />
				</div>
			</div>
			<div class="login">
				<button id="btn" type="button" style="background: #666;"
					onclick="login('${ctx}/user/user-login','${ctx}/user/user-index')" tabindex="4">登录</button>
			</div>
			<!-- <div id="forget_id" class="login">
				<button onclick="intoForgetPassword()" style="background: #D04848;"
					type="button" name="forget_password" id="forget_password" >
				忘记密码
				</button>
			</div> -->
		</form>
	</div>

	<div class="screenbg">
		<ul>
			<li><a href="javascript:;"><img src="/themes/images/background.jpg" /></a></li>
		</ul>
	</div>

</body>
</html>