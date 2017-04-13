<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/chuangjianriji.css"/>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<script type="text/javascript">
$(function(){
	var type = ${type}
    if(type==1){
    	$(".GuanBi_Li").append("<a><h1 id="+"tishi"+">你还没有完成的项目</h1></a>")
	}
})



/* $(function(){
	getPage();
});
function getPage(){
	mypager.generPageHtml({
		pagerid:"customertransfersuccess-pager",
		pno:"${currPage}",
		total:"${totalPage}",
		click:function(currPage){
			$(".rijixin").load("${cxt }/pc/view/diarybook/getProductorder.do?rows=6&page="+currPage);
		}
	});
} */
</script>		
	
		
			<c:forEach items="${orderlist }" var="order">
			   <li class="f_left">
				   <img class="img_11" width="110px" height="80px;" src="${img_local }/${order.productImage }" alt="">
				   <div class="mes_44">
				   	   <input type="hidden" class="productorderId" value="${order.id }"></input>
					   <h1>项目名称&nbsp;&nbsp;<span class="xmnae">${order.productName }</span></h1><h2 class="poname"><span></span>${order.priductOrderTypeName }</h2>
					   <p class="mername">${order.merchantName }</p>
					   <b><span class="price">${order.totalPrice/100 }</span>元</b>
				   </div>
			   </li>
			</c:forEach>
		
			 <script>
				 $('.mes_img >ul >li').click(function(){
					   $(".mes_img >ul >li").removeClass("blue_1");
					   $(this).addClass("blue_1");
				   });
			 </script>
			 <style>
			 	#tishi{
			 		padding-top: 220px;
			 		text-align: center;
			 		font-size: 30px;
			 		color: #00b0fb;
			 		
			 	}
			 </style>
