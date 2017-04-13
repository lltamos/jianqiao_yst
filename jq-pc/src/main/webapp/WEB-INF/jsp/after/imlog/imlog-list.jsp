<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${cxt }css/base.css"/>
    <link rel="stylesheet" href="${cxt}/css/yisheng_1.css"/>
    <link rel="stylesheet" href="${cxt}/css/personal_center_9.css"/>
</head>
<body id="all">
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
	<div class="bg_gray">
        <div class="content">
         <%@include file="/common/mycenterleft_docter.jsp"%>
            	<%@include file="/common/mycentertop.jsp"%>
            <div class="log">
            <c:forEach items="${list}" var="imlog">
                <div class="log_content">
               		<a onclick="sayCustomer(${imlog.customer_id})">
                <input type="hidden" id="customerid" value="${imlog.customer_id}"/>
                    <img  style='height: 100px;width:100px' src="${img_service }/${imlog.image}" alt=""/>
                    </a>
                    <div class="log_right">
                        <p><span>${imlog.name}</span><b>${imlog.created_time}</b></p>
                    </div>
                </div>
            </c:forEach>
            </div>
            <div class="page-location">
					<div id="customertransfersuccess-pager"
						class="ui-paging ui-paging1"></div>
			</div>
     	</div>
	</div>
<script>
	

    function open_1(){
        var s = document.getElementById("test");
        s.style.display = "block";
        var d=document.getElementById("phone_num");
        d.style.border='1px dashed #9C9AA5';
        var a =document.getElementById("shezhi");
        a.style.visibility = "hidden";
    }
    $(function() {
			getPage("${cxt }/pc/after/doctorimlog/imlog-list?rows=4&page=");
	}); 
	function getPage(url) {
			mypager.generPageHtml({
				pagerid : "customertransfersuccess-pager",
				//当前页码  provId="+$("#storeProId").val()+"&cityId="+$("#storeCityId").val()+"&industryId="+$("#storeIndustryId").val()+"&value="+$("#storeValue").val()+"&page_no="+(currPage-1)+"&page_size=6&time="+Math.random()
				pno : "${currPage}",
				total : "${totalPage}",
				click : function(currPage) {
					$("#all").load(
							url+ currPage);
				}
			});
	}
/* 	function sayCustomer(){
		var customerid = $("input[id=customerid]").val();
		
		$.ajax({
			 type:"POST",
			 url:"${cxt}/goIm/verifyCustomer?yourId="+customerid,
			 success:function(result){
				 var sc=eval('(' + result + ')');
				 if(sc.code==3){
					 window.location.href="${cxt }/pc/view/customer/to-login";
				 } 
				if(sc.code==2){
					window.open('${cxt}/goIm/verifyCustomer?yourId=21','jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px');
			   	 }
				alert(sc.msg);
				}
			
			});
	} */
	
	function sayCustomer(customerId){
		  
		$.ajax({
			 type:"POST",
			 url:"${cxt}/goIm/verifyCustomer?customerId="+customerId,
			 success:function(result){
				 var sc=eval('(' + result + ')');
				 if(sc.code==3){
					 window.location.href="${cxt }/pc/view/customer/to-login";
				 } 
				if(sc.code==2){
					window.open('${cxt}/goIm/sayCustomer?customerId='+customerId,'jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px');
			   	 }else{
			   		alert(sc.msg);
			   	 }
				
				}
			
			});
	}  
</script>
<%@include file="/common/bottom.jsp"%>
</body>
