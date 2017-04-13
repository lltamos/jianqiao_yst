<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link  rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/js/select2/css/select2.min.css" />
<script src="${ctx }/js/select2/js/select2.min.js"></script>
<script src="${ctx }/js/bankAddr.js"></script>
<style>
.divsl {
	height: 30px;
}

.tixian_1 {
	width: 1210px;
	margin: 0 auto;
	overflow: hidden;
}

.controls>span {
	float: left;
	margin-top: 5px;
}

.TiXian6 {
	height: 600px;
	width: 800px;
	margin-top: 50px;
}

/*左侧导航蓝  */
.navid {
	width: 169px;
	float: left;
	height: 600px;
	margin-bottom: -2000px;
	padding-bottom: 2000px;
	background: #515151;
	margin-right: 10px;
}

.navid .navid_2 {
	display: block;
	text-align: center;
	color: #fff;
	height: 49px;
	line-height: 49px;
	border-bottom: 1px dashed #939393;
}

.navid a:hover {
	background: #b2191b;
	color: #fff;
}

.navid .redh {
	background: #b2191b;
	color: #fff;
}

.purchaseRecord {
	display: block;
	background: #eeedf7;
	padding-bottom: 50px;
	width: 1031px;
	float: left;
}
</style>
<html>
<body>
		<div class="navgeish" style="padding:12px 0 10px 0;">
			<a href="javascript:window.location.href='/customer!index'">首页</a> >
			<a href="javascript:;"
				onclick="stockMoneyDetailaa('${ctx}/ydmvc/customerCash/customerBindBankInfoPage.do')">银行卡信息修改</a>
		</div>
		<form action="" id="cashForm">
			<input type="hidden" id="id" name="id" value="${customer.id }">
			<input type="hidden" id="bankPhone" name="tel_phone" value="${customer.phone }" >
			<div class="form-horizontal TiXian6">
				<c:if test="${customer.bankStatus==1}">
				<div class="control-group">
					<span style="color: red;text-align: center;margin-left: 180px">提示:您已绑定过银行卡，每月只允许修改2次银行卡信息，本月已修改${count }次</span>
					</div>
				</c:if>
				<div class="control-group">
					<label class="control-label" for="prov">开户人姓名：</label>
					<div class="controls">
						<span></span><span>${customer.name }</span><input type="hidden" style="width: 300px;"
							id="ownName" name="own_name" value="${customer.name }"
							onkeyup="this.value=this.value.replace(/[, ]/g,'')">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="login_code">身份证号：</label>
					<div class="controls">
						<span></span><span>${customer.IDNumber }</span><input type="hidden" style="width: 300px;" id="idCard"
							name="id_card" value="${customer.IDNumber }"
							onKeyUp="this.value=this.value.replace(/[, ]/g,'')">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="login_code">银行卡号：</label>
					<div class="controls">
						<span></span> <input type="text" style="width: 300px;"
							id="cardNum" name="card_num" value="${customer.bankCardNum }"
							onKeyUp="this.value=this.value.replace(/[, ]/g,'')">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="prov">开户银行：</label>
					<div class="controls">
						<select class="form-control js-example-data-array"
							style="  width: 280px" id="bankName">
						</select>
						<!-- value="${customerBank.bankName }" -->
						<input type="hidden" name="bank_name" id="bankName_hidden" />
						<input type="hidden" name="bank_id" id="bank_id_hidden" />
						<span id="tishiName" style="color: red; display:none; float:right; height:34px; width: 300px"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="prov">密保手机号：</label>
					<div class="controls">
						<input type="text" style="width: 300px;" id="feePhone" value="${customer.phone }" readonly="readonly">
						<button class="btn" id="send" type="button" onClick="sendMsg(this)">获取验证码</button>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="prov">手机验证码：</label>
					<div class="controls">
						<input type="text" style="width: 300px;" id="code" name="code"
							value="" placeholder="请输入手机验证码">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button class="btn" id="btn_dialog_submit" type="button"
							onClick="saveBank()">确定</button>
					</div>
				</div>
			</div>
		</form>

		<script type="text/javascript">
			function saveBank() {
				if (check()) {
					$("#btn_dialog_submit").attr("disabled", false);
					var form = $("#cashForm");
					var options = {
						url : '${ctx}/ydmvc/customerCash/updateBankMessage.do', //提交给哪个执行
						type : 'POST',
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
							};
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
	
					$("#cardNum").focus();
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
			$(function() {
				createBankInfo($("#bankName"));
				var name ="${bankInfo.bank_name}";
				$("#bankName").val(getIdFromListByText(name)).trigger("change");
			});

			var countk = 180;//按钮禁用60秒
			var iCount;//定时器 
			function sendMsg(e) {
				$(e).attr("disabled",true);
				iCount = setInterval(otherFun, 1000);
				//验证更修改信息并保存
				var AjaxURL = "${ctx}/ydmvc/customerCash/getSmsResult.do?codeType=2002";
				$
						.ajax({
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
					$("#send").attr("disabled",false);
					$("#send").text("获取验证码");
					clearInterval(iCount);
					countk = 180;
				}
			}
		</script>
</body>
</html>
