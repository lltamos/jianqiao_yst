<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="${cxt }/css/base.css" />
<link rel="stylesheet" href="${cxt }/css/personal_center_3.css" />
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${cxt }/js/jianqiaoindex.js"></script>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
</head>
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
						<li><span class="font_2">2</span>
							<p>修改登录密码</p></li>
						<li><b></b></li>
						<li><span class="font_2">3</span>
							<p>成功</p></li>
					</ul>
					<div class="phone_1">
						<ul>
							<li><span id="phonenum">${sessionScope.customer.phone}
							</span>
								<button id="send">发送验证码</button></li>
							<li><input type="text" id="code" name="code"
								placeholder="请输入短信验证码" /></li>
							<li><a class="yanzheng" href="javascript:checkSubmit();">验证</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	var countk = 60;//按钮禁用60秒
	var iCount;//定时器 

	$("#send").click(function() {

		sendMsg();
	});

	function sendMsg() {

		$("#send").attr("disabled", true);
		iCount = setInterval(otherFun, 1000);

		$.ajax({
			type : "POST",
			url : "${cxt}/pc/after/customer/editpwd-sendmsg",
			success : function(result) {

				var result = JSON.parse(result);
				var message = result.msg;
				alert(message);
			},
			error : function(data) {
				alert("短信发送败");
			}
		});
	}

	function otherFun() {
		countk = countk - 1;
		$("#send").text("重新获取(" + countk + ")");
		if (countk <= 0) {
			$("#send").attr("disabled", false);
			$("#send").text("获取验证码");
			clearInterval(iCount);
			countk = 60;
		}
	}

	function checkSubmit() {

		var code = {
			code : $("#code").val(),
			type:"pwd"

		};
		$.ajax({
					type : "POST",
					url : " ${cxt}/pc/after/customer/check-sendmsg",
					data : code,
					success : function(result) {
						var data = eval('(' + result + ')')

						if (data.code == 1) {
							alert(data.msg)
							window.location = "${cxt}/pc/after/customer/to-editpasswd-page"
						}
						if (data.code == 0) {
							alert(data.msg)
						}
					}
				});

	}
</script>
</html>
