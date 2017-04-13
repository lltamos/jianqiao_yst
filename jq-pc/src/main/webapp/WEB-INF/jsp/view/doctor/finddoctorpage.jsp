<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% int a=1; %>
<%@include file="/common/commonPublic.jsp"%>
<head>
<meta charset="UTF-8">
<title>患者求医</title>
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt}/css/patient.css">
<script src="${cxt}/js/jquery-1.7.2.js"></script>
<script src="${cxt}/js/jianqiaoindex.js"></script>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
</head>

<script>
$(function(){
		getPage();
	});
	function getPage(){
		mypager.generPageHtml({
			pagerid:"customertransfersuccess-pager",
			//当前页码  provId="+$("#storeProId").val()+"&cityId="+$("#storeCityId").val()+"&industryId="+$("#storeIndustryId").val()+"&value="+$("#storeValue").val()+"&page_no="+(currPage-1)+"&page_size=6&time="+Math.random()
			pno:"${currPage}",
			total:"${totalPage}",
			click:function(currPage){
				$("#bob").load("${cxt }/pc/view/doctor/finddoctor?rows=2&page="+currPage);
			}
		});
	}
	
/* 	function deletedisease(id){
		$.ajax({
			  type:"POST",
			  url:"${cxt }/pc/view/doctor/delete",
			  data:{id:id},
			  success: function(result){
				if(result.code=1){
					window.location.href="${cxt }/pc/view/doctor/finddoctor";
				}
		        })
			  };
			} */
			function toIndex(){
				window.location.href="${cxt }/pc/view/customer/toIndex";
			}
	</script>
<div id="bob">
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<div class="subjectjq clearfix">
    <div class="leftjq">
        <div class="hzqiu">
            <p class="hzqiu_1">
               <a href="javascript:;" onclick="toIndex()">首页</a>>>>><i>患者求医</i>
            </p>
            <p class="hzqiu_2">
                    <span>求医内容</span>
            </p>
            <p class="hzqiu_3">
					<a href="${cxt}/pc/view/doctor/publishdisease">求医发布</a>
             </p>
        </div>
         <c:forEach items="${diseaseList}" var="disease">
         	<div class="messagejq" >
                <div class="messagejq_1 clearfix">
                    <div class="ssage_21">
                    		 <img <c:if test="${!empty disease.headImage }">src="${img_service }/${disease.headImage}"></c:if>
                        	<c:if test="${empty disease.headImage }">src="${cxt }/img/jianj_18.jpg" alt=""></c:if> 
                     </div>
                    <div class="ssage_22">
                        <p class="age_3">${disease.nickName}</p>
                        <p>${disease.provName }-${disease.patientAddress}</p>
                        <p>${disease.createTime}</p>
                    </div>
                </div>
                <div class="messagejq_2">
                    <p class="mestp">
                    <c:forEach items="${disease.imageUrl}" var="image">
                        <img src="${img_service }/${image}">
                        </c:forEach>
                    </p>
                    <p class="mestp_1">
                         ${disease.diseaseDesc}
                    </p>
                   <!-- <a href="javascript:;"  >删除记录>></a> --> 
                </div>
            </div>
      	</c:forEach>
        </div>
    </div>
    <div class="page-location">
		<div id="customertransfersuccess-pager" class="ui-paging ui-paging1"></div> 
	</div>
	<%@include file="/common/bottom.jsp"%>
</div>
