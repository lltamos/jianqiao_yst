<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>套餐支付</title>
    <link rel="stylesheet" href="${cxt }/css/base.css">
    <link rel="stylesheet" href="${cxt }/css/defray_page.css">
    <script src="${cxt }/js/jquery-1.7.2.js"></script>
    <script src="${cxt}/js/jianqiaoindex.js"></script>
</head>
<script>

/* function pay() {
	var type =2;
	var tjr = $("#tjr").val();
	if(tjr == null || tjr =='undefined'){
		tjr = "";
	}
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
			 if(sc.code==9){
				 alert(sc.msg);
				 return false;
			 }else{
				 $(".hz_32").attr('disabled','disabled');
					$(".hz_32").css('background','#ccc');
				    $(".erweima").show();
				    setTimeout("aaa()",120000);
				    setTimeout("pay2()",120000); 
			 }
			 $(".tupian").attr('src',sc.msg); 
			 //showImages();
			 }
			
			
	 }); 
		
}
function pay2(){
	$(".hz_32").removeAttr('disabled','disabled');
	$(".hz_32").css('background','#F47900');

}
function aaa(){
       $(".erweima").hide();
   } */
   function pay() {
		var type =2;
		var tjr = $("#tjr").val();
		if(tjr == null || tjr =='undefined'){
			tjr = "";
		}
		var doctorid = $("input[id=doctorid]").val();
		window.location.href="${cxt }/erweima/goto-pay?doctorid="+doctorid+"&type="+type+"&tjr="+tjr;
	} 
</script>
<body>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<div class="hint">
<p><input name="doctorid" id="doctorid" value="${doctorid }" type="hidden"/></p>
<%-- <p><input name="productid" id="productid" value="${productid }" type="hidden"/></p>
<p><input name="hospitalid" id="hospitalid" value="${hospitalid }" type="hidden"/></p>
<p><input name="productname" id="productname" value="${productname }" type="hidden"/></p>
<p><input name="doctorname" id="doctorname" value="${doctorname }" type="hidden"/></p>
<p><input name="depositeprice" id="depositeprice" value="${price }" type="hidden"/></p> --%>
    <p>温馨提示：支付预约定金后，请到指定医院机构进行初诊后指定康复方案并签合约</p>
    <div class="set_meal">
        <p class="time">${doctorname }-${productname }</p><p>预约定金</p><b><span>${price/100 }</span>元</b>
    </div>
    <div class="referee">
        <span>推荐人：</span><input type="text" id="tjr" name="tjr" >
    </div>
    <div class="qrzf clearfix">
        <!-- <p class="hz_32"> -->
            <li>
          	  	
            	<input type="button" onclick ="pay()" class="hz_32" value="立即购买">
            	<a href="${cxt }/pc/view/doctor/todoctor" class="hz_33">返回</a>
            </li>
        <!-- </p> -->
    </div>
     <!-- <div class="erweima" >
     
        <img class="tupian" id= "imagePath" src="" alt="" />
    </div> -->
    <!-- <div id ="codeurl">
    	<p value="" id="imagePath" name="imagePath" ></p>
    	<img alt="" id="images" width="200" height="200">
    </div> -->
</div>

 <%@include file="/common/bottom.jsp"%>
</body>
</html>