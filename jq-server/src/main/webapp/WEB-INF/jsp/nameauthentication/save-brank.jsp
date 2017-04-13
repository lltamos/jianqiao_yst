<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
clearInterval(timer);
	var senurl = "";
	var url = "";
	var gourl = '';
	var type = ${type}
	if(type == 1){
		senurl = "${ctx}/authentication/doctor-brank-sendCode";
		url = "${ctx}/authentication/realNameByDoctor";
		gourl = '${ctx}/authentication/to-savebrank-doctor';
	}else if(type == 2){
		senurl = "${ctx}/authentication/merchant-brank-sendCode";
		url = "${ctx}/authentication/realNameByMerchant";
		gourl = '${ctx}/authentication/to-savebrank-merchant';
	}else if(type == 3){
		senurl = "${ctx}/authentication/tjcustomer-brank-sendCode";
		url = "${ctx}/authentication/realNameByTjcustomer";
		gourl = '${ctx}/authentication/to-savebrank-tuijianren';
	}
	
/* 	 function sendMs() {
		var phone = $("input[id=phone]").val();
		var time = 60;
	    var s = time+1;
	    var timer = null;
		if (check()) {
			$("#sendMessage").text("发送中。。。。");
			$.ajax({
				type : 'POST',
				data : {"phone":phone},
				url : senurl,
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var resultInfo = eval("(" + result + ")");
					var result = JSON.parse(result);
					var message = result.msg;
					var code = result.code;
					if(code == 1){
						alert(message);
						$("#sendMessage").text("发送成功");
					}
				}
			});
		}
	}  */
    var timer = null;
	function sendmsg(){
		var mobile = $("input[id=phone]").val();	    
		var time = 60;
	    var s = time+1;
	    function countDown(){
			s--;
			$('#sendMessage').val('('+s+')正在发送');
			$('#sendMessage').attr("disabled",true);
			$('#sendMessage').css('background','#ccc');
			if(s==0){
				clearInterval(timer);
				$('#sendMessage').val('重新发送');
				$('#sendMessage').attr("disabled",false);
				$('#sendMessage').css('background','#ccc');
				s=time+1;
			}
		  }
		countDown();
		timer = setInterval(countDown,1000);
		
		
		$.ajax({
			type:"POST",
			 url:senurl,
			 data: {"phone":mobile},
			 success:function(result){
				var data = eval('(' + result + ')')
				if(data.code ==0){
					alert(data.msg)
				}
			}
		})
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
	function bangding(){
		var update = $("#update").val();
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
		var branknum = $("input[name='branknum']");
		var brankMobile= /^(\d{16}|\d{17}|\d{18}|\d{19})$/;
		if(branknum.val() == "") {
			branknum.tips({
				side : 2,
				msg : '银行卡号不得为空',
				bg : '#AE81FF',
				time : 3
			});
			
			branknum.focus();
			return false;
		} else {
			branknum.val(jQuery.trim(branknum.val()));
		}
		if(!brankMobile.test(branknum.val())){
			branknum.tips({
				side : 2,
				msg : '银行卡号格式不正确',
				bg : '#AE81FF',
				time : 3
			});
			
			branknum.focus();
			return false;
		} else {
			branknum.val(jQuery.trim(branknum.val()));
		}
		
		$.ajax({
			url : url,
			type : "POST",
			data : {"bankNo":branknum.val(), "code":idCode.val(), "update":update},
			success : function(result) {
	   		 	var appResult = eval("(" + result + ")"); 
	   			var message = appResult.msg;
	   			var code = appResult.code;
	   			alert(message);
	   			if(code==0){
	   				pageGo(gourl);
	   			}
	   		 } 
		});
		
	}

</script>
<body>

	<form style="padding-left: 20%; padding-top: 50px;">
		<input type="hidden" id="type" value="${type}">
		<input type="hidden" id="update" value="${update}">
		<table id="shenghe">
			
			<tr>
				<td>用户手机号：</td>
				<td><input type="text" name="phone" id="phone" value="${phone }" readonly="readonly"/>
					<input id="sendMessage" class="btn" onclick="sendmsg()"
						type="button" value="发送短信验证码"></td>
			</tr>
			<tr>
				<td>短信验证码：</td>
				<td><input type="text" name="code" id="code" value="" /></td>
			</tr>
			<tr>
				<td>银行卡号：</td>
				<td><input type="text" name="branknum" id="branknum" value="" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center" style="margin: 10px  0;"><button class="btn"
						type="button" onclick="bangding()" id="save">绑定</button>
			</tr>
		</table>

	</form>
	<style>
	#shenghe tr td{
		padding: 10px 0;
	}
	
	</style>
</body>
</html>