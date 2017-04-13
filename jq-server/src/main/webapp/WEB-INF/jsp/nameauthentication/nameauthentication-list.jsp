<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
function renzheng(){
		var checkMobile= /^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\d{8}$/;
		var $phone = $("input[name=phone]");
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
	    if(!checkMobile.test($phone.val())) {
	    	$phone.tips({
				side : 2,
				msg : '手机号格式错误',
				bg : '#AE81FF',
				time : 3
			});

			$phone.focus();
			return false;
		} else {
			$phone.val(jQuery.trim($phone.val()));
		}
	    var checkName=/^[\u4E00-\u9FA5]+$/;
	    var name = $("input[name=names]");
		    if (name.val() == "") {
		    	name.tips({
					side : 2,
					msg : '姓名不得为空',
					bg : '#AE81FF',
					time : 3
				});

		    	name.focus();
				return false;
			} else {
				name.val(jQuery.trim(name.val()));
			}
	        if(!checkName.test(name.val())) {
	        	name.tips({
					side : 2,
					msg : '姓名必须为中文',
					bg : '#AE81FF',
					time : 3
				});

		    	name.focus();
				return false;
			} else {
				name.val(jQuery.trim(name.val()));
			}
	    var checkIdcard=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
     	var idcard = $("input[name=idcard]");
	     	if(idcard.val() == ""){
	     		idcard.tips({
					side : 2,
					msg : '身份证号码不得为空',
					bg : '#AE81FF',
					time : 3
				});

	     		idcard.focus();
				return false;
			} else {
				idcard.val(jQuery.trim(idcard.val()));
			}
	        if(!checkIdcard.test(idcard.val())){
	        	idcard.tips({
					side : 2,
					msg : '身份证号码格式不正确',
					bg : '#AE81FF',
					time : 3
				});

	     		idcard.focus();
				return false;
			} else {
				idcard.val(jQuery.trim(idcard.val()));
			}
	        
	        var type = $("#type").val();
	        var url = "";
	        if(type == 1){
	        	url = "${ctx }/authentication/realNameByDoctor.do";
	        }else if(type == 2){
	        	url = "${ctx }/authentication/realNameByMerchant.do";
	        }else if(type == 3){
	        	url = "${ctx }/authentication/realNameByTjcustomer.do";
	        }
	        $.ajax({
	    	   url : url,
	    	   data : {"phone":$phone.val(),"name":name.val(),"idcard":idcard.val()},
	    	   type : "POST",
	    	   success : function(result) {
	   		 	var appResult = eval("(" + result + ")"); 
	   			var message = appResult.msg;
	   			var code = appResult.code;
	   			alert(message);
	   			if(code==0){
	   				logout();
	   			}
	   		 }
	       }); 
}

</script>
<body>
<h1 style="color: red; text-align: center; padding-top: 20px;">为保障您的财产安全，请您先进行身份的实名认证，实名认证通过即可进行其他相关操作</h1>

	<form style="padding-left: 20%; padding-top: 50px;">
		<input type="hidden" id="type" value="${type}">
		<table id="shenghe">
			
			<tr>
				<td><input type="hidden" name="phone" id="phone" value="${phone }"/>
			</tr>
			<tr>
				<td>真实姓名：</td>
				<td><input type="text" name="names" id="names" value="" style="height:30px;line-height:30px;" /></td>
			</tr>
			<tr>
				<td>身份证号码：</td>
				<td><input type="text" name="idcard" id="idcard"   value="" style="height:30px;line-height:30px;" /></td>
			</tr>  
			<tr>
				<td colspan="2" align="center" style="margin: 10px  0;"><button class="btn"
						type="button" onclick="renzheng()" id="save">审核</button>
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