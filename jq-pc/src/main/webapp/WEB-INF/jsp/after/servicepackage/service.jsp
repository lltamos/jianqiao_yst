<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>疑难杂症导医服务包</title>
    <link rel="stylesheet" href="${cxt }/css/base.css">
    <link rel="stylesheet" href="${cxt }/css/symptom.css">
    <script src="${cxt}/js/jianqiaoindex.js"></script>
</head>
<script>
function pay(doctorid){
	//var doctorid = $("input[id=doctorid]").val();
	/* var id = $("input[id=id]").val(); */
	window.location.href="${cxt}/service/to-pay?doctorid="+doctorid
}
 function saypay(doctorid){
	//var doctorid = $("input[id=doctorid]").val();
	
	window.location.href="${cxt}/service/to-serverpay?doctorid="+doctorid;
} 

function toIndex(){
	window.location.href="${cxt }/pc/view/customer/toIndex";
}
/* <!-- 跳转到疑难杂症页面--> */
function todisease() {
	window.location.href="${cxt }/pc/view/product/todisease";
}
/* <!-- 跳转到疑难杂症页面--> */
function todiseasebyservicename(serviceName){
	window.location.href="${cxt }/pc/view/product/search?serviceName="+serviceName;
}

  function paySay(doctorid){
	  
	$.ajax({
		 type:"POST",
		 url:"${cxt}/goIm/verifyCustomerPay?doctorId="+doctorid,
		 success:function(result){
			 var sc=eval('(' + result + ')');
			 if(sc.code==3){
				 window.location.href="${cxt }/pc/view/customer/to-login";
			 } 
			if(sc.code==2){
				window.open('${cxt}/goIm/sayDoctor?doctorId='+doctorid,'jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px');
		   	 }
			if(sc.code==1){
				saypay(doctorid);
			}
			}
		
		});
}  
</script>
<body>

<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
    <div class="subjectjq clearfix">
        <div class="leftjq">
            <div class="hzqiu">
                <p class="hzqiu_1">
                    <a href="javascript:;" onclick="toIndex()">首页</a>>><a href="javascript:;"  onclick="todisease()">疑难杂症导医</a>>><a href="javascript:;" onclick="todiseasebyservicename('${serviceName.productTypeName }')">${serviceName.productTypeName }</a>>><i>${product.name }</i>
                </p>
            </div>
            <div class="theway">
             <p><input name="productid" id="productid" value="${product.id }" type="hidden"/></p>
             <p><input name="depositeprice" id="depositeprice" value="${product.deposite_price }" type="hidden"/></p>
             <input name="productid"  id="productname" name="productname" value="${serviceName.productTypeName }" type="hidden"/>
                <p class="hewayjq_1" ">【${serviceName.productTypeName }】${product.name }</p>
                <div class="hewayjq_2 clearfix">
                          <img src="${img_service }/${serviceName.address }">
                          <div class="wayjq_21">
                          <p><input name="productid" id="productid" value="${product.id }" type="hidden"/></p>
                            <p class="tejia_1">
                                		健桥特价
                            </p>
                             <p class="tejia_2">
                                  <span>健桥价：</span><i>${product.total_price/100 }元</i>
                            </p>
                            <p class="tejia_2">
                                  <span>項目定金：</span><i>${product.deposite_price/100  }元</i>
                            </p>
                            <p class="tejia_3">
                                <span>市场价：</span><i>${product.market_price/100 }元</i>
                                <b>分${product.divide }期</b>
                            </p>
                            <p class="tejia_4">
                                <span>医院名称：</span><i>${product.merchant_name }</i>
                            </p>
                            <p class="tejia_5">
                                <span>项目简介：</span><i>${product.des1 }</i>
                            </p>
                            <a class="tejia_6" href="javascript:;" target='_blank' onclick=window.open('${cxt}/goIm/sayadmin','jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px')>在线咨询专家</a>
                          </div>
                </div>
                <p class="hewayjq_3">
                    <span>建桥承诺：</span><i>随时退款 先行赔付</i>
                </p>  
            </div>
            <div class="xmmsjq">
                        <span>项目描述</span>
            </div>    
            <div class="xiangmujq">
                <!-- <p class="xiangmujq_1">韩式半永久妆是什么</p>
                <p class="xiangmujq_2">——韩式半永久化妆术也称韩式定妆——</p> -->
                <!-- <div class="xiangmujq_3">
                    <p>
                        他的美妆范围包括眉、眼睫、唇、发际等部位。将FDA审核认证的色料，移
        植到眉毛，眼线，嘴唇，发际线等部位。其最大的优势就是无痛，同时是半
        永久性的，定妆一次效果能够维持2-3年之久，随后会随着皮肤的新陈代谢，
        而逐渐淡去。
                    </p>
                    <p>韩式半永久妆不怕遇水脱妆、流汗晕妆，最重要的是省去了每天几小时化妆、 卸妆的时间和繁琐过程。</p>
                </div> -->
                ${product.detail_html }
            </div>
			<div class="xmmsjq">
				<span>问专家</span>
			</div>
			<div class="zhuanjia">
			<c:forEach items="${doctor}" var="doctor">
				<div class="zhuanjia_1">
					<img src="${img_service }/${doctor.image_header }" alt=""/>
						<ul>
							 <p><input name="doctorid" id="doctorid" value="${doctor.id }" type="hidden"/>
							 	<input name="id" id="id" value="${id }" type="hidden"/>
							 </p>
							 <%-- <p><input name="hospitalid" id="hospitalid" value="${doctor.hospitalid }" type="hidden"/></p>
							  <p><input name="doctorname" id="doctorname" value="${doctor.doctorname }" type="hidden"/></p> --%>
							<li><span class="zj_1" >${doctor.name }</span></li>
							<li class="zj_2"><span>${doctor.title_name}</span> | <span>${doctor.hospitalype_name }</span></li>
							<!-- 此处需要处理医生专长 -->
							<li class="zj_2">擅长: <span>${doctor.spec_name}</span></li>
							<li><a onclick ="pay(${doctor.id})" class="online_1" href="javascript:;">在线预约</a><a onclick="paySay(${doctor.id})" class="online_1" href="javascript:;">在线咨询</a></li>
<%-- 							class="online_1" href="javascript:;" onclick=window.open('${cxt}/goIm/sayDoctor?doctorId=${doctor.doctorid }','jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px')
 --%>						</ul>
				</div>
			</c:forEach>
			</div>
			<div class="xmmsjq">
				<span>查看病友日记</span>
			</div>
			<c:forEach items="${diary }" var="diary" >
			<div class="diarybox">
			
				<div class="diarynumber">
					${diary.count }
				</div>
				<div class="diarymain">
					<div class="article">
						${diary.content }
					</div>
					<div class="picbox">
						<div class="diarypic">
						<!-- 图片没有字段 -->
							<c:forEach items="${diary.imageList }" var="address">
							<img src="${img_service }/${address}" alt="">
							</c:forEach>
						</div>
					</div>
					<div class="timeandrank">
						<div class="time">
							${diary.created_time}
						</div>
						<div class="rank">
							<div class="likeicon">
								<div class="icon"></div>
							</div>
							<div class="like">${diary.like}</div>
							<div class="commenticon">
								<div class="icon"></div>
							</div>
							<div class="comment">${diary.commenter_num}</div>
							<div class="lookicon">
								<div class="icon"></div>
							</div>
							<div class="look">${diary.brow_num}</div>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
            <div class="xmmsjq">
                        <span>服务信息</span>
            </div>
           <div class="fwxxjq">
               <div class="fwxxjq_1">
                    <p class="wxxjq_12">服务流程</p>
                    <p class="wxxjq_22 clearfix">
                        <img src="${cxt }/img/qiaoq7.png">
                        <span class="rvicejq_2">下单支付预约金并拨打电话预约面诊-手术前，将订单密码 交给医生，并支付余下的费用 </span>
                    </p>
               </div>
               <div class="fwxxjq_1">
                    <p class="wxxjq_12">如何预约</p>
                    <p class="wxxjq_22 clearfix">
                        <img src="${cxt }/img/qiaoq8.png">
                        <span class="rvicejq_2">下单后，点击专家头像进入专家主页，点击“电话咨询”联 系专家/机构 </span>
                    </p>
               </div>
               <div class="fwxxjq_1">
                    <p class="wxxjq_12">如何退款</p>
                    <p class="wxxjq_22 clearfix">
                        <img src="${cxt }/img/qiaoq9.png">
                        <span class="rvicejq_2 ">点击app首页左上角“我”，找到我的订单，在“未消费” 栏里找到该订单，点击取消。退款将在15日退换到当初付款 账户。</span>
                    </p>
               </div>
               <div class="fwxxjq_1">
                    <p class="wxxjq_12">特别提示</p>
                    <p class="wxxjq_22 clearfix">
                        <img src="${cxt }/img/qiaoq10.png">
                        <span class="rvicejq">如有困难请拨打客服电话4000802315 </span>
                    </p>
               </div>
           </div>
        </div>
    </div>
	<%@include file="/common/bottom.jsp"%>
</body>
</html>