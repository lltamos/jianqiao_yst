<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<html>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li>供货商销售提现 <span class="divider">/</span>
		</li>
		<li class="active">填写提现信息：</li>
	</ul>
	<form action="" id="cashForm">
	<div class="form-horizontal">
		<div class="control-group">
				<label class="control-label" for="name">金额：</label>
				<div class="controls" id="">
					<input type="text" style="width: 300px;height: 30px;" id="money1" name="money1" value="" onblur="checkMoney();" maxlength="6" onkeyup='this.value=this.value.replace(/\D/gi,"")'>元
					 <!-- <input type="hidden" id="money" name="money"/> -->
					<span id="tishiMoney" style="color: red; display:none; float:right; padding-right: 290px;"></span>
					
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="login_code">银行卡号：</label>
				<div class="controls">
					 <input type="text" style="width: 300px;height: 30px;" id="cardNo" name="cardNo" value="" onblur="checkBankNo();" maxlength="19" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
					 <span id="tishiCard" style="color: red;display: none; float:right; padding-right: 360px;"> 请输入正确的银行卡号 </span>
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="prov">开户人姓名：</label>
				<div class="controls">
					  <input type="text" style="width: 300px;height: 30px;" id="personName" name="personName" value="" >
					  <span id="tishiName" style="color: red;display: none; float:right; padding-right: 360px;"> 请输入开户姓名 </span>
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="prov">开户银行：</label>
				<div class="controls">
					  <input type="text" style="width: 300px;height: 30px;" id="bankName" name="bankName" value="">
					  <span id="tishiBankName" style="color: red;display: none; float:right; padding-right: 360px;"> 请输入开户银行 </span>
				</div>
		</div>
	<div class="control-group">
				<label class="control-label" for="city">手机号码：</label>
				<div class="controls">
					  <input type="text" style="width: 300px;height: 30px;" id="phone" name="phone" value="" maxlength="11" onkeyup='this.value=this.value.replace(/\D/gi,"")' >
					  <span id="tishiPhone" style="color: red;display: none; float:right; padding-right: 360px;"> 请输入手机号码 </span>
					  <button id="sendMessage" class="btn" onclick="sendMs()"
						type="button">发送短信验证码</button>
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="city">验证码：</label>
				<div class="controls">
					  <input type="text" style="width: 300px;height: 30px;" id="code" name="code" value="" >
					  <span id="tishiBankSub" style="color: red;display: none; float:right; padding-right: 360px;"> 请输入手机验证码 </span>
				</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn" id="tixian" type="button" onclick="javascript:{this.disabled=true;cash();}">提现</button>
			</div>
		</div>
	</div>
	</form>
</body>
</html>
<script type="text/javascript">
//提现
 function cash()
 {
        var regex = /^[0-9]+.?[0-9]*$/;
		var re= /^\d+(\.\d+)?$/;
		var n=$("#money1").val();//去除逗号
		var g=n.replace(/,/g,'');
		    //alert("g:"+g);
		//表单验证
		if( n==0 ||n<1 ||n=='')
		{
			$("#tishiMoney").html("提现金额不能小于1元 ，请重新输入");
			var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 293px;';
			$("#tishiMoney").show();
			return ;
		}
		if(g>100000)
		{
			$("#tishiMoney").html("提现金额1-10万");
		    var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 345px;';
			$("#tishiMoney").show();
			return ;
		}
		if($("#cardNo").val()=='' || !regex.test($("#cardNo").val()) || $("#cardNo").val().length<16 || $("#cardNo").val().length>19)
		{
		    $("#tishiCard").html("请输入正确的银行卡号！");
			$("#tishiCard").show();
		return ;
		}else
		{
		    $("#tishiCard").html("");
			$("#tishiCard").show();
			buttonObject=document.getElementById('tixian');
			buttonObject.disabled=false;
		}
		
		if($("#personName").val()=='')
		{
		//alert("开户人姓名不能为空！");
		    $("#tishiName").html("请输入开户人姓名！");
			$("#tishiName").show();
		return ;
		}else
		{
		 $("#tishiName").html("");
			$("#tishiName").show();
			buttonObject=document.getElementById('tixian');
			buttonObject.disabled=false;
		} 
		
		if($("#bankName").val()=='')
		{
		//alert("开户银行不能为空！");
		$("#tishiBankName").html("请输入开户银行!");
			$("#tishiBankName").show();
		return ;
		}else
		{
		$("#tishiBankName").html("");
			$("#tishiBankName").show();
			buttonObject=document.getElementById('tixian');
			buttonObject.disabled=false;
		
		}
		 if($("#bankSub").val()=='')
		{
		$("#tishiBankSub").html("请输入开户支行！");
			$("#tishiBankSub").show();
		return ;
		}
		else
		{
		    $("#tishiBankSub").html("");
			$("#tishiBankSub").show();
			buttonObject=document.getElementById('tixian');
			buttonObject.disabled=false;
           // $("#money").val(100 * (g* 1000) / 1000);/* //乘以100 一分为单位 */
            $("#money1").val(n);
		    var param=$("#cashForm").serialize();
		$.ajax({
				  type: 'POST',
				  url: "${cxt}/balance/hospitalcashreceive",
				  data:param,
				  success : function (result){
					  var sc=eval('(' + result + ')');
					  alert(sc.msg);
					  if(sc.msg == "SUCCESS"){
						  window.location.href ="${ctx}/balance/to-balance-list";
					  }
					 buttonObject=document.getElementById('tixian');
					buttonObject.disabled=false;
				  }
			  });
			  buttonObject.disabled=true;
		}
 }
		
		
	function checkMoney()
	{
		var m=$("#money1").val();
		if( m==0 ||m<1 ||m=='')
		{
			$("#tishiMoney").html("提现金额不能小于1元 ，请重新输入");
			var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 293px;';
			$("#tishiMoney").show();
			return ;
		}else($("#money1").val()>100000)
		{
			$("#tishiMoney").html("提现金额1-10万");
		    var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 345px;';
			$("#tishiMoney").show();
			return ;
		}
      }
       //校验银行卡
	  function checkBankNo()
		{
		var regex = /^[0-9]+.?[0-9]*$/;
		var no=$("#cardNo").val();
		if(no=='' || !regex.test(no) || no.length<16 || no.length>19)
		{
		    $("#tishiCard").html("请输入正确的银行卡号！");
			$("#tishiCard").show();
			return ;
		}else
		{
		   $("#tishiCard").html("");
			$("#tishiCard").show();
			buttonObject=document.getElementById('tixian');
			buttonObject.disabled=false;
		}

        }

		function sendMs(){
			
			var checkMobile="^[1]+[3,4,5,7,8]+\\d{9}$"
				var phone = $("input[name=phone]").val();
			
				if(phone==''||phone==null){
					/* $(".tishiPhone").html("手机号不能为空!");
		            $(".phone").focus(); */
		            alert("手机号不能为空!");
					return false;
				}
				if (!phone.match(checkMobile)) {
		          /*   $(".tishiPhone").html("手机号码格式不正确！");
		            $(".phone").focus(); */
		            alert("手机号码格式不正确！");
		            return false;
				} 
				/* var time = 60;
		        var s = time+1;
		        var timer = null;
		        function countDown(){
					s--;
					$('.btn').val('('+s+')正在发送');
					$('.btn').attr("disabled",true);
					$('.btn').css('background','#ccc');
					if(s==0){
						clearInterval(timer);
						$('.btn').val('重新发送');
						$('.btn').attr("disabled",false);
						$('.btn').css('background','#6ACF33');
						s=time+1;
					}
				  }
				countDown();
				timer = setInterval(countDown,1000); */
				
				
				$.ajax({
					type:"POST",
					 url:"${ctx}/balance/balance-sendHospitalCode",
					 data: {"phone":phone},
					 success:function(result){
						var data = eval('(' + result + ')')
						if(data.code ==0){
							alert(data.msg)
						}
					}
				})
		}
</script>
