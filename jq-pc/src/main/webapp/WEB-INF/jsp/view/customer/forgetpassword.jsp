<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>忘记密码</title>
    <link rel="stylesheet" href="${cxt }/css/base.css">
    <link rel="stylesheet" href="${cxt }/css/wangjimima.css">
    <script src="${cxt }/js/jquery-1.7.2.js"></script>
    <style>
        label {
            color: #ccc;
            position: absolute;
            top: 11px;
            left: 48px;
        }
    </style>
</head>
<script type="text/javascript">


function forgetPassword(){
	if(checkMobil()){
		$.ajax({
			type:"POST",
			 url:"${ctx}/pc/view/customer/forgetpassword",
			 data: $("#forgetForm").serialize(),
			 success:function(result){
				var data = eval('(' + result + ')')
				if(data.code == 1){
					alert(data.msg)
					window.location = "${cxt }/pc/view/customer/to-login"
				}
				if(data.code =='0'){
					alert(data.msg)
				}
			}
		})
	}
}


function sendmsg(){
	var checkMobile="^[1]+[3,4,5,7,8]+\\d{9}$"
	var mobile = $("input[name=phone]").val();
	if(mobile==''||mobile==null){
		$(".mobil_code").html("手机号不能为空!");
           $(".mobil").focus();
		return false;
	}
	if (!mobile.match(checkMobile)) {
           $(".mobil_code").html("手机号码格式不正确！");
           $(".mobil").focus();
           return false;
	}else{
        $(".mobil_code").html("");
    }
	$.ajax({
		type:"POST",
		 url:"${cxt }/pc/view/customer/get-customer",
		 data: {"phone":mobile},
		 success:function(result){
			var data = eval('(' + result + ')')
			if( data.code == 1 ){
				var time = 60;
			    var s = time+1;
			    var timer = null;
			    function countDown(){
					s--;
					$('.yanzhengma').val('('+s+')正在发送');
					$('.yanzhengma').attr("disabled",true);
					$('.yanzhengma').css('background','#ccc');
					if(s==0){
						clearInterval(timer);
						$('.yanzhengma').val('重新发送');
						$('.yanzhengma').attr("disabled",false);
						$('.yanzhengma').css('background','#6ACF33');
						s=time+1;
					}
				  }
				countDown();
				timer = setInterval(countDown,1000);
				
				
				$.ajax({
					type:"POST",
					 url:"${cxt }/pc/view/customer/forget-send",
					 data: {"phone":mobile},
					 success:function(result){
						var data = eval('(' + result + ')')
						if(data.code ==0){
							alert(data.msg)
						}
					}
				})
			}
			if( data.code ==0 ){
				$(".mobil_code").html("账号不存在！");
			}
		}
	})
}
function toIndex() {
	window.location.href="${cxt }/pc/view/customer/toIndex";
}

</script>
<body>
<form id="forgetForm">
	<div class="beijing">
	    <div class="denglu">
	        <img src="${cxt }/img/jian_10.jpg" class="logo" onclick="toIndex()">
	        <div class="denglu_1">忘记密码</div>
	        <a class="fanhui" href="${cxt }/pc/view/customer/to-login">返回登录界面</a>
	        <div class="denglu_3">
	            <label>手机号</label>
	            <input name="phone" type="text" class="deng_1 mobil">
	            <p class="deng_2 mobil_code"></p>
	        </div>
	        <div class="denglu_4">
	            <label>输入验证码</label>
	            <input name="checkmsg" type="text" class="deng_12 nickname">
	            <input type="button" class="yanzhengma" value="获取验证码" onclick="sendmsg()"/>
	            <p class="deng_2  nickname_code"></p>
	        </div>
	        <div class="denglu_4">
	            <label>密码</label>
	            <input name="password" type="password" id="password" class="deng_1 submit">
	            <p class="deng_2  submit_code"></p>
	        </div>
	        <div class="denglu_4">
	            <label>重复密码</label>
	            <input name="repassword" type="password" id="passwords" class="deng_1 submit_again">
	            <p class="deng_2  submit_again_code"></p>
	        </div>
	        <a href="javascript:;" onclick="forgetPassword()" class="dinglu_6">下一步</a>
	    </div>
	</div>
</form>
<script>
    $(function(){
        $('label').click(function(){
            $(this).css('display','none');
            $(this).siblings('input').focus();
        });
        $('input').focus(function(){
            $(this).siblings('label').css('display','none');
        });
        $('input').blur(function(){
            if($(this).val()==''){
                $(this).siblings('label').css('display','block');
            }
        });
    });


    function checkMobil() {
    	
    	var checkMobile="^[1]+[3,4,5,7,8]+\\d{9}$";
	    if ($(".mobil").val() == "") {
	        $(".mobil_code").html("手机号码不能为空");
	        $(".mobil").focus();
	        return false;
	    }
	     if(!$(".mobil").val().match(checkMobile)){
	        $(".mobil_code").html("手机号码格式不正确！");
	        $(".mobil").focus();
	        return false;
	    }else{
	        $(".mobil_code").html("");
	    }
        
        if($(".nickname").val() == ""){
	        $(".nickname_code").html("验证码不能为空！");
	        $(".nickname").focus();
	        return false;
	    }else if($(".nickname").val().match('^[0-9]{6}$')){
	    	$(".nickname_code").html("");
	    }else{
	        $(".nickname_code").html("验证码错误");
	    }
        var checkPassword="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";
		var password = $("#password").val();
		if (password == "") {
            $(".submit_code").html("密码不能为空");
            $("#password").focus();
            return false;
        }
		if(!$("#password").val().match(checkPassword)){
            $(".submit_code").html("密码格式不正确！");
            $("#password").focus();
            return false;
        }else{
            $(".submit_code").html("");
        }
        
       var passwords=$("#passwords").val();
        if( passwords == ""){
            $(".submit_again_code").html("密码不能为空！");
            $("#passwords").focus();
            return false;
        }
        if(password!=passwords){
            $(".submit_again_code").html("密码不一致！");
            $("#passwords").focus();
            return false;
        }else{
            $(".submit_again_code").html("");
        }
        return true;
        
    }
	    
        
</script>
</body>