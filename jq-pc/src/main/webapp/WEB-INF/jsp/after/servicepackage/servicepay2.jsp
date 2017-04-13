<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>套餐支付</title>
    <link rel="stylesheet" href="${cxt }/css/base.css">
    <link rel="stylesheet" href="${cxt }/css/common.css">
    <link rel="stylesheet" href="${cxt }/css/defray_page.css">
    <script src="${cxt }/js/jquery-1.7.2.js"></script>
</head>
<script>

/* function pay() {
	var type =1;
	var tjr = "";
	var doctorid = $("input[id=doctorid]").val();
	 $.ajax({
		 type:"POST",
		 url:"${cxt}/service/goto-pay",
		data:{"doctorid":doctorid,"type":type,"tjr":tjr},
		success:function(result){
			 var sc=eval('(' + result + ')');
			 if(sc.code==2){
				alert("请先登录!");
				 window.location.href="${cxt }/pc/view/customer/to-login";
			 }
			 $(".tupian").attr('src',sc.msg); 
			 //showImages();
			 }
			/* if(sc.code=5){
				alert("支付成功！");
			} */
		/*}); 
		$(".hz_32").attr('disabled','disabled');
		$(".hz_32").css('background','#ccc');
	    $(".erweima").show();
	    setTimeout("aaa()",120000);
	    setTimeout("pay2()",120000);
}
function pay2(){
	$(".hz_32").removeAttr('disabled','disabled');
	$(".hz_32").css('background','#F47900');

}
function aaa(){
       $(".erweima").hide();
   } */
   
   function pay() {
		var type =1;
		var tjr = "";
		var doctorid = $("input[id=doctorid]").val();
		$.ajax({
			type:"POST",
			url:"${cxt}/erweima/topay",
			data:{"doctorid":doctorid,"type":type,"tjr":tjr},
			success:function(result){
				 var sc=eval('(' + result + ')');
				 if(sc.code==2){
					alert("请先登录!");
					window.location.href="${cxt }/pc/view/customer/to-login";
				 }
				    if(sc.code==3){
				    	alert("该医生的在线预约没有开启,请稍后再试!");
				    	return false;
				    }
				    if(sc.code==4){
				    	window.location.href="${cxt }/erweima/goto-pay?doctorid="+doctorid+"&type="+type+"&tjr="+tjr;
				    }
				    
				 }
		});
		
   }
</script>
<body>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<div class="hint">
<p><input name="doctorid" id="doctorid" value="${doctorid }" type="hidden"/></p>

        <p>温馨提示：支付预约定金后，请到指定医院机构进行初诊后指定康复方案并签合约</p>
        <div class="set_meal">
            <p class="time">${doctorname }-- 电话咨询</p><p><span>1</span>次</p><b><span>${price/100 }</span>元</b>
        </div>
        <div class="qrzf clearfix">
        <!-- <p class="hz_32"> -->
            <li>
            	
            	<input type="button" onclick ="pay()" class="hz_32" value="立即购买">
            	<a href="${cxt }/pc/view/doctor/todoctor" class="hz_33">返回</a>
            </li>
        <!-- </p> -->
    </div>
    <!--  <div class="erweima" >
     
        <img class="tupian" id= "imagePath" src="" alt="" />
    </div> -->
    </div>
    <%@include file="/common/bottom.jsp"%>
</body>
</html>