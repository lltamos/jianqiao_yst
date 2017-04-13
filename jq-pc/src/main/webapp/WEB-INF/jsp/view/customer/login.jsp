<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" href="${cxt }/css/base.css">
	<link rel="stylesheet" href="${cxt }/css/home.css">
	<script src="${cxt }/js/jquery-1.7.2.js"></script>
	<style>
    label {
            color: #ccc;
            position: absolute;
            top: 11px;
            left: 48px;
        }
    .denglu_55{
	    font-size: 14px;
	    text-align: center;
	    color: #f28300;
	    margin-top: 13px;
	    text-decoration: underline;
	    margin-left: 233px;
     }
    </style>
</head>
<script type="text/javascript">
	var loginCount = 5;

	//-- 重新加载验证码表单
	function loadimage() {
		$("#randImage").attr("src",
				"${cxt}/pc/view/customer/user-code?r=" + Math.random());
	}
	// 进入页面后，请求一个验证码 
	$(function() {
		loadimage();
		$(".screenbg ul li").each(function() {
			$(this).css("opacity", "0");
		});
		$(".screenbg ul li:first").css("opacity", "1");
		var index = 0;
		var t;
		var li = $(".screenbg ul li");
		var number = li.size();
		function change(index) {
			li.css("visibility", "visible");
			li.eq(index).siblings().animate({
				opacity : 0
			}, 3000);
			li.eq(index).animate({
				opacity : 1
			}, 3000);
		}
		function show() {
			index = index + 1;
			if (index <= number - 1) {
				change(index);
			} else {
				index = 0;
				change(index);
			}
		}
		t = setInterval(show, 8000);
		//根据窗口宽度生成图片宽度
		var width = $(window).width();
		$(".screenbg ul img").css("width", width + "px");
	});
	
	function login(){
		if(loginCount == 0){
			alert("已连续登陆5次");
			window.location.href="${cxt }/pc/view/customer/to-forgetpassword";
		}
		var checkMobile="^1(3[0-9]|4[57]|5[0-35-9]|7[013678]|8[0-9])\\d{8}$";
		var mobile = $("input[name=phone]").val(); 	
			if ($("#phone").val() == "") {
	            $(".deng_2").html("手机号码不能为空");
	            $("#phone").focus();
	            return false;
	        }
	        if(!mobile.match(checkMobile)) {
	            $(".deng_2").html("手机号码格式不正确！");
	            $("#phone").focus();
	            return false;
	        }else{
	            $(".deng_2").html("");
	        }
			var imageCode = $("input[name=imageCode]").val();
			if (mobile==''||mobile==null){
				loginCount = loginCount-1;
				alert("验证码不能为空");
				return false;
			}
		$.ajax({
			type:"POST",
			 url:"${cxt}/pc/view/customer/login",
			 data: $("#dengluForm").serialize(),
			 success:function(result){
				var data = eval('(' + result + ')')
				if(data.code == 1){
				/* 	alert(data.msg) */
					window.location = "${cxt}/index.jsp"
				}
				if(data.code =='0'){
					loginCount = loginCount-1;
					alert(data.msg)
					location.reload();
				}
			}
		});
	}
</script>
<body>
  <div class="beijing">
  		<form id="dengluForm">
  			<div class="denglu">
  			<a href="${cxt }/pc/view/customer/toIndex">
  				<img src="${cxt }/img/jian_10.jpg" class="logo">
	        </a>
	        <div class="denglu_1">会员登录</div>
	        <div class="denglu_3">
	           <label>请输入手机号</label>
	            <input name="phone" type="text" class="deng_1" id="phone">
	            <p class="deng_2"></p>
	        </div>
	        <div class="denglu_4">
	            <label>请输入密码</label>
	            <input name="password" maxlength="32" type="password" class="deng_1" id="password">
	        </div>

	        <div class="code YanZhengMa">
	            <label>请输入验证码</label>
				<input class="YanZheng"type="text" name="imageCode" maxlength="4" id="image_code"
					tabindex="3" />
				<div class="codeImg">
					<img onclick="loadimage();" title="换一张试试" id="randImage" src="" />
				</div>
			</div>
			<style>
				.YanZhengMa{
					position: relative;
				}
				.YanZheng{
				    width: 308px;
				    height: 44px;
				    display: block;
				    border: #9e9e9e solid 1px;
				    border-radius: 5px;    
				    margin: 0 auto;
    				outline: none;
				}
				.codeImg{
					position: absolute;
					top:1px;
					right:41px;
				}
				.codeImg img{
					height:44px;
					width:100px;
					border-bottom-right-radius:5px;
					border-top-right-radius:5px;
				}
			</style>
			<div style="padding-top: 30px;">
		        <a class="denglu_55" href="${cxt }/pc/view/customer/to-register">立即注册</a>
		        <a href="${cxt }/pc/view/customer/to-forgetpassword" class="dinglu_5">忘记密码</a>
	        </div>
	        <a href="javascript:;" onclick="login()" class="dinglu_6">登录</a>
	    </div>
  		</form>
    </div>
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
    </script>
</body>
