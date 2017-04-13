<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<title>健康分享</title>
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/fengxiangxiangqing.css">
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<script src="${cxt }/js/jianqiaoindex.js"></script>
<script type="text/javascript">
$(function(){
	var id = $("#diaryId").val();
	$.ajax({
		url : "${cxt}/pc/view/diary/saveDiaryBrowNum.do",
		type : "POST",
		data : {"id":id},
		success : function(result) {
		}
	})
})

function dianz(){
	$(".zan").removeAttr("onclick"); 
	var dz = parseInt($("#dianzan").text());
	var dzs = dz+1;
	$("#dianzan").text(dzs);
	$(".zan").text("已赞");
	var diaryId = $("#diaryId").val();
	$.ajax({
		url : "${cxt}/pc/view/diary/saveDiaryFavourCount.do",
		data : {"diaryId":diaryId},
		type : "POST",
		success : function(result) {
			var appResult = eval("(" + result + ")"); 
			var message = appResult.msg;
			var code = appResult.code;
			if(code==3){
				alert(message);
				window.location.href="${cxt }/pc/view/customer/to-login";
			}
		}
	})
}

function topinglun(id){
	var niming = $("input[name=niming]:checked").val();
	if(niming == undefined || niming == ""){
		niming = 0;
	}
	var content = $("#elm3").val();
	var diaryId = $("#diaryId").val();
	$.ajax({
		url : "${cxt}/pc/view/diary/saveDiaryComment.do",
		data : {"diaryid":diaryId, "content":content, "niming":niming},
		type : "POST",
		success : function(result) {
		 	var appResult = eval("(" + result + ")"); 
			var message = appResult.msg;
			var code = appResult.code;
			alert(message);
			if(code==0){
				window.location.href="${cxt }/pc/view/diary/getDiaryDateils.do?diaryid="+diaryId;
			} else if(code==3){
				window.location.href="${cxt }/pc/view/customer/to-login";
			}
		 }
	});
	
}
function toIndex(){
	window.location.href="${cxt }/pc/view/customer/toIndex";
}
</script>
	<%@include file="/common/topindex.jsp"%>
	<%@include file="/common/navigate.jsp"%>

	<div class="daohangf">
	        <a href="javascript:;" onclick="toIndex()">首页</a>>>
	        <a href="${cxt }/pc/view/diary/toDiaryListPage.do">健康分享</a>>>
	        <a href="${cxt }/pc/view/diary/toDiaryListByBookId.do?diarybookid=${book.id}">分享列表</a>>>
	        <span>分享详情</span>
    </div>
    
    
    <div class="fenliu clearfix">
        <div class="fenliu_left">
            <div class="fenliu_1">
                <div class="tiao"></div>
                <div class="tupian">
                    <img src="${img_service}/${customer.image}">
                    <p id="xiangming">
                    	<c:if test="${!empty customer.nickName }">${customer.nickName}</c:if>
                    	<c:if test="${empty customer.nickName }">${customer.phone.substring(0,3)}*****${customer.phone.substring(8,11)}</c:if>
                    </p>
                </div>
                <div class="tiao"></div>
            </div>
            <div class="jieshao">
                <div class="tupian_2"><img width="132px" height="101px" src="${img_local }/${address }"></div>
                <div class="qukk">
                    <p class="qukk_1">项目链接 <a href="javascript:;">【${diary.productTypeName }】</br><span style="width:20em; overflow: hidden; font-size:19px; white-space:nowrap; text-overflow:ellipsis;">${product.des1 }</span></a></p>...
                    <a href="${cxt }/service/to-package?id=${pid}" class="qukk_2">去看看>></a>
                </div>
            </div>
            <div class="fenliu_2">
               ${diary.content }
               <input type="hidden" id=diaryId value="${diary.id }">
            </div>
            <div class="fenliu_3">
            	<c:forEach items="${diary.attachmentAddress }" var="diaryImage">
						<div class="webberpic webberpic1">
							<img width="200px" height="200px" <c:if test="${!empty diaryImage }">src="${img_service}/${diaryImage}"></c:if>
                        	<c:if test="${empty diaryImage }">src="${ctx }/pc/img/webberimg1.jpg" alt=""></c:if>
						</div>
				</c:forEach>
            </div>
            <div class="fenliu_4 clearfix">
               <div class="fenliu_41">
                    <span class="tub_1" id="dianzan">${favourCount }</span>
                    <span class="tub_2">${fn:length(diaryCommentAll) }</span>
                    <span class="tub_3">${diary.browNum }</span>
                </div>
                <div class="fenliu_42">
                    <span><fmt:formatDate  value="${diary.createdTime}" type="both" pattern="yyyy.MM.dd HH:mm" /></span>
                    &nbsp;&nbsp;&nbsp;&nbsp;<a class="zan" href="javascript:;" onclick="dianz()">赞</a><a class="pinglun" href="#elm3">评论</a>
                </div>
            </div>
            <div class="gg_1">
            	<p>所属日记本: <a href="${cxt }/pc/view/diary/toDiaryListByBookId.do?diarybookid=${book.id}">${book.diaryBookName }</a></p>
            </div>
            <div class="gondll">
            	<c:forEach items="${diaryCommentAll }" var="comment" varStatus="comment1">
                	<div class="fenliu_5 clearfix">
                    	<div class="fenliu_51">
                        	<div class="touxiang" style="width: 40px; height: 40px;">
                        		<img width="40px" height="40px"<c:if test="${!empty comment.customerHandImg }">src="${img_service}/${comment.customerHandImg}"></c:if>
                        		<c:if test="${empty comment.customerHandImg }">src="${ctx }/img/webber.jpg" alt=""</c:if>
                 			</div>
                        	<div>${comment.customerName }</div>
                    	</div>
                    	<div class="fenliu_52">
                        	<p class="pingjia">${comment.content }</p>
                        	<p class="loucen"><span>${comment1.count }楼</span><i><fmt:formatDate  value="${comment.createdTime}" type="both" pattern="yyyy.MM.dd HH:mm" /></i></p>
                    	</div>
                	</div>
               </c:forEach>
            </div>
            <div class="fenliu_6">
                <p class="fengliu_61">发表评价：</p>
                <textarea id="elm3" name="elm3" class="xheditor-simple" rows="12" cols="80" style="width: 100%"></textarea>
                <div class="tijiaod clearfix">
                    <span class="tijiaod_1 clearfix">
                        <input type="checkbox" id="niming" name="niming" value="1">
                        <span>匿名评论</span>
                    </span>
                    <a href="javascript:;" onclick="topinglun(${diary.id})">发表评论</a>
                </div>
            </div>
        </div>
        <div class="fenliu_right"></div>
    </div>
</div>
<%@include file="/common/bottom.jsp"%>