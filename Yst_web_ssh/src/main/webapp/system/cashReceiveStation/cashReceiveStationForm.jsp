<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language=javascript src="${baseurl }/js/jquery.cityselect.js"></script>
<link rel="stylesheet" type="text/css" href="${baseurl }/js/city.min.js" />

<script>
$(function(){
				$("#city_1").citySelect({
					nodata:"none",
					required:false
				}); 
		});	
</script>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="storeInfo!index">主页</a> <span class="divider">/</span></li>
		<li>供货商销售提现 <span class="divider">/</span>
		</li>
		<li class="active">填写提现信息：</li>
	</ul>
	<form action="" id="cashForm">
	<div class="form-horizontal">
		<div class="control-group">
				<label class="control-label" for="name">金额：</label>
				<div class="controls" id="">
					<input type="text" style="width: 300px;height: 30px;" id="money1"  value="" onblur="checkMoney();">元
					 <input type="hidden" id="money" name="money"/>
					<span id="tishiMoney" style="color: red; display:none; float:right; padding-right: 290px;"></span>
					
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="login_code">银行卡号：</label>
				<div class="controls">
					 <input type="text" style="width: 300px;height: 30px;" id="cardNo" name="cardNo" value="" onblur="checkBankNo();" >
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
				<label class="control-label" for="prov">所在省市：</label>
				<div class="controls">
		 		   <div id="city_1">
			        <select class="prov" id="bankPrv"  name="bankPrv" style="width: 150px;"></select> 
			        <select class="city" disabled="disabled" id="bankCity" name="bankCity" style="width: 150px;"></select>
			        <span id="tishiBankPro" style="color: red;display: none; float:right; padding-right: 360px;"> 请选择所在省市 </span>
                   </div>
					 
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="city">开户支行：</label>
				<div class="controls">
					  <input type="text" style="width: 300px;height: 30px;" id="bankSub" name="bankSub" value="" onblur="checkBankSub();">
					  <span id="tishiBankSub" style="color: red;display: none; float:right; padding-right: 360px;"> 请输入开户支行 </span>
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
		var ketixian="${ketiixan}";
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
		/* if(!re.test(n))
		{
		    $("#tishiMoney").html("请输入正确的提现金额！");
		    var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 360px;';
			$("#tishiMoney").show();
			return ;
		} */
		if($("#money1").val()>ketixian/100)
		{
		    $("#tishiMoney").html("提现金额不足，请重新输入");
		    var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 345px;';
			$("#tishiMoney").show();
			return ;
		}
		else
		{
		    $("#money1").val(formatMoney($("#money1").val(),2));
		    $("#tishiMoney").html("");
			$("#tishiMoney").hide();
			buttonObject=document.getElementById('tixian');
 			buttonObject.disabled=false;
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
		
		} if($("#bankPrv").val()=='')
		{
		//alert("所在省份不能为空！");
		$("#tishiBankPro").html("请选择所在省市！");
			$("#tishiBankPro").show();
		return ;
		}else
		{
		$("#tishiBankPro").html("");
			$("#tishiBankPro").show();
			buttonObject=document.getElementById('tixian');
			buttonObject.disabled=false;
		}
		
		
		 if($("#bankCity").val()=='')
		{
		//alert("所在城市不能为空！");
		$("#tishiBankPro").html("请选择所在省市！");
			$("#tishiBankPro").show();
		return ;
		}else
		{
		    $("#tishiBankPro").html("");
			$("#tishiBankPro").show();
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
            $("#money").val(100 * (g* 1000) / 1000);//乘以100 一分为单位
            $("#money1").val(n);
		    var param=$("#cashForm").serialize();
		    /* alert(param); */
		  /*  return; */ 
		$.ajax({
				  type: 'POST',
				  /* dataType: 'text', */
				  url: "${baseurl}/ydmvc/cashReceiveStation/CashResult.do",
				  data:param,
				  success : function (data){
					  if(data=='ok'){
						  alert("提现成功！");
						  pageGo("ydmvc/cashReceiveStation/merchantsCashReceiveStationPage.do");	
					  }else if(data=='more'){
						  alert("提现金额多于账户余额！");
					  }else if(data=='0104'){
						  alert("提现失败，请核实信息。");	
					  }else if(data=='0100'){
						  alert("商户提交的字段长度、格式错误！");
					  }else if(data=='0101'){
						  alert("商户验签错误！");	
					  }else if(data=='0102'){
						  alert("手续费计算出错！");	
					  }else if(data=='0103'){
						  alert("商户备付金帐户金额不足！");
					  }else if(data=='0105'){
						  alert("重复交易！");	
					  }else if(data=='BankLengthError'){
						  alert("请求失败！");	
					  }else if(data=='waiting'){
						  alert("申请成功，请稍后。。");
					  }else if(data=='error'){
						  alert("提现金额10元-10万");
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
		var ketixian="${ketiixan}";
		var re= /^\d+(\.\d+)?$/;
		var m=$("#money1").val();
		m=m.replace(/,/g,'');
		if( m==0 ||m<1 ||m=='')
		{
			$("#tishiMoney").html("提现金额不能小于1元 ，请重新输入");
			var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 293px;';
			$("#tishiMoney").show();
			return ;
		}
		else if(!re.test(m))
		{
		    $("#tishiMoney").html("请输入正确的提现金额！");
		    var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 360px;';
			$("#tishiMoney").show();
			return ;
		}
		else if($("#money1").val()>ketixian/100 )
		{
		    $("#tishiMoney").html("提现金额不足，请重新输入");
		    var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 345px;';
			$("#tishiMoney").show();
			return ;
		}else if($("#money1").val()>100000)
		{
			$("#tishiMoney").html("提现金额1-10万");
		    var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 345px;';
			$("#tishiMoney").show();
			return ;
		}
		else
		{
		     
		    $("#tishiMoney").html("");
			$("#tishiMoney").hide();
			buttonObject=document.getElementById('tixian');
			buttonObject.disabled=false;
		}
		$("#money1").val(formatMoney(m,2));
		
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
       
       //开户支行
       function checkBankSub()
       {
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
	    }
       }
       /* 金额保留小数点后两位 */
		function formatMoney(s,n){
		if(s>0)
		{
			n = n>0 && n<=20 ? n : 2;
			s = parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
			var l = s.split(".")[0].split("").reverse(),
			r = s.split(".")[1];
			t = "";
			for(i = 0;i<l.length;i++){
			t+=l[i]+((i+1)%3==0 && (i+1) != l.length ? "," : "");
			}
			return t.split("").reverse().join("")+"."+r;
		}else
			{
			$("#tishiMoney").html("请输入提现金额！");
		    var s= document.getElementById('tishiMoney');
		    s.style.cssText = 'color: red;display: none; float:right; padding-right: 360px;';
			$("#tishiMoney").show();
			}
		
		} 

</script>
