<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/personal_center_7.css" />
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
					<div class="modify">修改手机号</div>
					<ul>
						<li><span class="font_1">1</span>
							<p>验证身份</p></li>
						<li><b></b></li>
						<li><span class="font_1">2</span>
							<p>更换手机号</p></li>
						<li><b></b></li>
						<li><span class="font_2">3</span>
							<p>成功</p></li>
					</ul>
					<form id="changephone">
						<div class="phone_1">
							<ul>
								<li><span>请输入新的手机号 :</span><input name="phone"
									id="phonenum" class="pad_1" type="text" /></li>
								<li><button type="button" id="send">获取短信验证码</button></li>
								<li><span>请输入短信验证码 :</span><input name="code" class="pad_1"
									type="text" /></li>
								<li><a class="yanzheng" href="javascript:checkPut();">确定</a></li>
							</ul>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var countk = 60;//按钮禁用60秒
		var iCount;//定时器 

		$("#send").click(function() {

			sendMsg();
		});

		function sendMsg() {
			if (!(/^1[34578]\d{9}$/.test($("#phonenum").val()))) {
				alert("手机号输入错误！");
				return false;
			}

			$("#send").attr("disabled", true);
			iCount = setInterval(otherFun, 1000);

			var code = {
				phone : $("#phonenum").val()
			};

			$.ajax({
				type : "POST",
				url : "${cxt}/pc/after/customer/editnewphone-sendmsg",
				data : code,
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

		function checkPut() {

			$.ajax({
						type : "POST",
						url : "${cxt}/pc/after/customer/change-phone",
						data : $("#changephone").serialize(),
						success : function(result) {

							var data = eval('(' + result + ')')

							if (data.code == 1) {
								alert(data.msg);
								window.location = "${cxt}/pc/after/customer/to-editphonesucess-page"
							}
							if (data.code == '0') {
								alert(data.msg);
							}
						}
					});
		}
	</script>
</body>
</html>