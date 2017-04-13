<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/common.css">
<link rel="stylesheet" href="${cxt }/css/diaryshareindex.css">
<link rel="stylesheet" href="${cxt }/css/personal_center_4.css" />
<link rel="stylesheet" href="${cxt }/css/personal_center_1.css" />
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<!DOCTYPE html>
<html>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<body>
	<div class="bg_gray">
		<div class="content">
			<%@include file="/common/mycenterleft.jsp"%>
			<div class="con_right">
				<div class="password_1">
					<div class="modify">修改密码</div>
					<ul>
						<li><span class="font_1">1</span>
							<p>验证身份</p></li>
						<li><b></b></li>
						<li><span class="font_1">2</span>
							<p>修改登录密码</p></li>
						<li><b></b></li>
						<li><span class="font_2">3</span>
							<p>成功</p></li>
					</ul>
					<div class="phone_1">
						<form id="changepasswd">
							<ul>
								<li><span>当前登录密码 :</span><input id="oldPassWd"
									name="oldPassWd" class="pad_1" type="password" /></li>
								<li><span>新的登录密码 :</span><input id="password1"
									name="password1" class="pad_1" type="password" /></li>

								<li><span>确认新的登录密码 :</span> <input name="password2"
									id="password2" class="pad_2" type="password" /></li>
								<li><a class="yanzheng" href="javascript:checkPut();">验证</a></li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function open_1() {
			var s = document.getElementById("test");
			s.style.display = "block";
			var d = document.getElementById("phone_num");
			d.style.border = '1px dashed #9C9AA5';
			var a = document.getElementById("shezhi");
			a.style.visibility = "hidden";
		}

		function checkPut() {

			var checkPassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";

			if ($("#oldPassWd").val() == "") {
				alert("旧密码不能为空！");

				return false;
			}

			if ($("#password1").val() == "") {
				alert("新密码不能为空！");

				return false;
			}
			if ($("#password2").val() == "") {
				alert("新密码不能为空！");

				return false;
			}

			if (!$("#password1").val().match(checkPassword)) {
				alert("密码格式不正确！");

				return false;
			}

			$.ajax({
						type : "POST",
						url : " ${cxt}/pc/after/customer/change-passwd",
						data : $("#changepasswd").serialize(),
						success : function(result) {

							var data = eval('(' + result + ')')

							if (data.code == 1) {
								window.location = "${cxt}/pc/after/customer/to-editpasswdsucess-page"
							}
							if (data.code == '0') {
								alert(data.msg)
							}
						}
					})
		}
	</script>
</body>
</html>