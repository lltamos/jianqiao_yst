<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/personal_center_7.css" />
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<html>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<body>
	<div class="bg_gray">
		<div class="content">
			<%@include file="/common/mycenterleft.jsp"%>
			<div class="con_right">
				<div class="password_1">
					<div class="modify">绑定银行卡</div>

					<ul>
						<li><span class="font_1">1</span>
							<p>银行卡号:</p></li>
						<li><b></b></li>
						<li><span class="font_1">2</span>
							<p>身份证号：</p></li>
						<li><b></b></li>
						<li><span class="font_2">3</span>
							<p>成功</p></li>
					</ul>

					<div class="phone_1">
						<form id="formbank">
							<ul>
								<li><span>银行卡号:</span><input name="cardNum" class="pad_1"
									type="text" /></li>
								<li><span>身份证号：</span><input name="idCard" class="pad_1"
									type="text" /></li>
								<li><span>开户人姓名：</span><input name="ownName" class="pad_2"
									type="text" /></li>
								<li><span>开户银行：</span><input name="bankName" class="pad_2"
									type="text" /></li>
								<li><span>银行绑定手机号：</span><input name="telPhone"
									class="pad_2" type="text" /></li>
								<li><span>密保手机号：</span><input class="pad_2"
									type="text" value="${customer.phone }" />
									<button class="btn" id="send" type="button"
										onClick="sendMsg(this)">发送验证码</button></li>
								<li><a id="btn_submits" class="yanzheng"
									href="javascript:saveBank();">确定</a></li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>












	<script type="text/javascript">
		function saveBank() {
			if (check()) {
				$("#btn_submits").attr("disabled", false);
				var jdata = $("#formbank").serialize();
				var options = {
					url : '${ctx}/order/userbank/saveBank.do', //提交给哪个执行
					type : 'POST',
					data : jdata,
					success : function(data) {
						//从返回的json数据中获取结果信息
						//结果提示信息
						var result = JSON.parse(data);
						var message = result.msg;
						var code = result.code;
						alert(message);
						if (code == 1) {
							$("#btn_dialog_submit").attr("disabled", true);
							bindbankinfo();
						}
						;
					}
				};//显示操作提示
				form.ajaxSubmit(options);
			}
			;
		}

		function check() {
			var bankre = /[0-9]/;
			var teshu = /[~'!@#$%^&*()-+_=:]/;
			var cardNum = $("#cardNum").val();
			var idCard = $("#idCard");
			var len = cardNum.length;
			var bankName = $("#select2-bankName-container").html();
			var ownName = $("#ownName").val();
			var bankleng = bankName.length;
			var ownleng = ownName.length;
			if (cardNum == "") {
				alert('请填写银行卡号');

				$("#S").focus();
				return false;
			} else if (len > 30 || len < 2) {
				alert('请输入正确的银行卡号');

				$("#cardNum").focus();
				return false;
			} else if (!bankre.test(cardNum)) {
				alert('银行卡格式有误');

				$("#cardNum").focus();
				return false;
			} else {
				$("#cardNum").val(jQuery.trim(cardNum));
			}
			if (idCard.val() == "") {
				alert('请输入身份证号');

				idCard.focus();
				return false;
			}
			if (!isIdCard(idCard.val(), idCard)) {
				alert('请输入的正确的身份证号');
				idCard.focus();
				return false;
			}
			if (bankName == "") {
				alert('开户银行不能为空');

				$("#bankName").focus();
				return false;
			} else if (bankre.test(bankName)) {
				alert('开户银行格式不正确');

				$("#bankName").focus();
				return false;
			} else if (bankleng<3 || bankleng>20) {
				alert('开户银行长度不符合要求');

				$("#bankName").focus();
				return false;
			} else if (teshu.test(bankName)) {
				alert('开户银行存在非法字符');

				$("#bankName").focus();
				return false;
			} else {
				$("#bankName_hidden").val(jQuery.trim(bankName));
			}
			if (ownName == "") {
				alert('请填写开户人名称');

				$("#ownName").focus();
				return false;
			} else if (bankre.test(ownName)) {
				alert('开户人名称不能是数字');

				$("#ownName").focus();
				return false;
			} else if (ownleng<2 || ownleng>30) {
				alert('开户人名称长度不符合要求');

				$("#ownName").focus();
				return false;
			} else if (teshu.test(ownName)) {
				alert('开户人名称存在非法字符');

				$("#ownName").focus();
				return false;
			} else {
				$("#ownName").val(jQuery.trim(ownName));
			}
			var phone = $("#bankPhone");
			if (phone.val() == "") {
				alert('请输入银行预留手机号');
				phone.focus();
				return false;
			} else if (isNaN(phone.val())) {
				alert('请输入正确的手机号！');

				phone.focus();
				return false;
			} else if (phone.val().length != 11) {
				alert('请输入正确的手机号！');

				phone.focus();
				return false;
			} else {
				phone.val(jQuery.trim(phone.val()));
			}
			var code = $("#code");
			if (code.val() == "") {
				alert('请输入手机验证码');
				code.focus();
				return false;
			} else if (isNaN(code.val())) {
				alert('请输入正确的手机验证码！');
				code.focus();
				return false;
			} else if (code.val().length != 6) {
				alert('请输入正确的手机验证码！');

				code.focus();
				return false;
			} else {
				code.val(jQuery.trim(code.val()));
			}
			$("#bank_id_hidden").val(getIdFromListByText(bankName));
			return true;
		}

		var countk = 180;//按钮禁用60秒
		var iCount;//定时器 
		function sendMsg(e) {
			$(e).attr("disabled", true);
			iCount = setInterval(otherFun, 1000);
			//验证更修改信息并保存
			var AjaxURL = "${ctx}/order/userbank/sendMsg";
			$.ajax({
				type : "POST",
				url : AjaxURL,
				success : function(data) {
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
	</script>
	<script type="text/javascript">
		$(function() {
			createBankInfo($("#bankName"));
		});
	</script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
