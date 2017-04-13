<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>

<link rel="stylesheet" href="${cxt }/css/base.css">


<link rel="stylesheet" href="${cxt }/css/personal_center_6.css" />
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
					<div class="modify">修改手机号</div>
					<ul>
						<li><span class="font_1">1</span>
							<p>验证身份</p></li>
						<li><b></b></li>
						<li><span class="font_2">2</span>
							<p>更换手机号</p></li>
						<li><b></b></li>
						<li><span class="font_2">3</span>
							<p>成功</p></li>
					</ul>
					<div class="phone_1">
						<ul>
							<li><span>${sessionScope.customer.phone}</span>
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
	<script>
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
				url : "${cxt}/pc/after/customer/editoldphone-sendmsg",
				success : function(result) {

					var result = JSON.parse(data);
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
				type:"phone"
			};
			$
					.ajax({
						type : "POST",
						url : " ${cxt}/pc/after/customer/check-sendmsg",
						data : code,
						success : function(result) {
							
							
							var data = eval('(' + result + ')')
					
							if (data.code == 1) {
								alert(data.msg)
								window.location = "${cxt}/pc/after/customer/to-editphone-page"
							}
							if (data.code == '0') {
								alert(data.msg)
							}
						}
					});

		}
	</script>
</body>
</html>