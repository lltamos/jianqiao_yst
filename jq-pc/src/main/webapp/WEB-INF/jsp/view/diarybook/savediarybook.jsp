<%@ page pageEncoding="UTF-8" autoFlush="false" buffer="300kb"%>
<%@ include file="/common/commonPublic.jsp"%>
<title>健康分享</title>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/chuangjianriji.css"/>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<script type="text/javascript">
$(function(){
	$('.chuangjiandl').click(function(){
	   	   var scorep = $('#star0').attr('score');
	   	   var fabulousval = "";
	   	   $("#fabulous>a").each(function(){
		   		if( $(this).hasClass("active")){
		   			fabulousval += $(this).attr("value")+",";
		   		}
	   	   });
	   	   var steponval = "";
	   	   $("#stepon>a").each(function(){
	   			if( $(this).hasClass("actives")){
	   				steponval += $(this).attr("value")+",";
		   		}
	   	   });
		   var product = $("#pid").val();
		   var bookName = $("#bookname").val();
		   if(scorep == null || scorep == ""){
			   alert("请选择满意度");
			   return;
		   }
		   if(product == null || product == ""){
			   alert("请选择已做的项目");
			   return;
		   }
		   if(bookName == null || bookName == ""){
			   alert("日记本名称不能为空");
			   return;
		   }
		   $.ajax({
			   type : "POST",
			   url : "${cxt}/pc/view/diarybook/saveDiaryBook.do",
			   data : {"scorep":scorep,"product":product,"bookName":bookName,"fabulousval":fabulousval,"steponval":steponval},
			   success : function(result) {
				 	var appResult = eval("(" + result + ")"); 
					var message = appResult.msg;
					var code = appResult.code;
					alert(message);
					if(code==0){
						window.location.href="${cxt }/pc/view/diarybook/toDiaryListPage.do?";
					}
				 }
		   });
	 });
})


function showBg() {
	   var bh = $("body").height();
	   var bw = $("body").width();
	   $("#fullbg").css({
		   height:bh,
		   width:bw,
		   display:"block"
	   });
	   $("#dialog").show();
}
function closeBg() {
	   $("#fullbg,#dialog").hide();
}
$('.mes_img >ul >li').click(function(){
	   $(".mes_img >ul >li").removeClass("blue_1");
	   $(this).addClass("blue_1");
});
function toIndex(){

	window.location.href="${cxt }/pc/view/customer/toIndex";
}

</script>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>

<div class="daohangf">
	        <a href="javascript:;" onclick="toIndex()">首页</a>>>
	        <a href="${cxt }/pc/view/diary/toDiaryListPage.do">健康分享</a>>>
	        <a href="${cxt }/pc/view/diarybook/toDiaryListPage.do">日记本</a>>>
	        <span>创建日记本</span>
    </div>
    
    <div  class="fuwdu_2">
        <span class="fuwdu_21">创建日记本</span>
        <img src="${cxt }/img/jianp_5.png">
    </div>
   <div class="chujiriji clearfix">
      <div class="jiriji">
           <div class="jiriji_1 clearfix">
               <span class="jianjuli">日记本名称：</span>
			   <input type="text" id="bookname" placeholder="输入日记名称" value="">
            </div>
            <div id="productid">
            </div>
            <div class="jiriji_1 clearfix">
                <span class="jianjuli">项目：</span>
                <a href="javascript:showBg();" class="jiriji_2">请选择你做过的项目</a>
            </div>
            <!-- <div class="jiriji_1 hui clearfix">
                <span class="jianjuli">项目名称：</span>
                <b class="jiriji_3" id="xiangmu"></b>
            </div> -->
            <div class="jiriji_1 hui clearfix">
                <span class="jianjuli">医院：</span>
                <b class="jiriji_3" id="yiyuan"></b>
            </div>
            <div class="jiriji_1 hui clearfix">
                <span class="jianjuli">标签：</span>
                <b class="jiriji_3" id="biaoqian"></b>
            </div>
            <div class="jiriji_1 clearfix">
                <span class="jianjuli">赞意度：</span>
                <ul class="jiriji_4" id="star0">
					<li class="star"></li>
					<li class="star"></li>	
					<li class="star"></li>
					<li class="star"></li>
					<li class="star"></li>
				</ul>
			<script type="text/javascript">

			    $(".jiriji_4 li").click(function(){
			        var index = $(this).index();
			        $(this).parent().attr("score",index+1);
			        var parentId = $(this).parent().attr("id");
			        $("#"+parentId).find("li").css("background-image","url('${cxt }/img/jianp_7.png')");
			        for(var i=0;i<=index;i++){
			            $("#"+parentId).find("li").eq(i).css("background-image","url('${cxt }/img/jianp_13.png')");
			        }
			    });
			</script>
            </div>
            <div class="jiriji_6 clearfix">
                <span class="jianjuli">赞一句：</span>
                <div class="jiriji_5 clearfix" id="fabulous">
                    <a href="javascript:;" value="效果非常好">效果非常好</a>
                    <a href="javascript:;" value="效果非常好">效果非常好</a>
                    <a href="javascript:;" value="效果非常好">效果非常好</a>
                    <a href="javascript:;" value="效果非常好">效果非常好</a>
                    <a href="javascript:;" value="效果非常好">效果非常好</a>
                    <a href="javascript:;" value="效果非常好">效果非常好</a>
                    <a href="javascript:;" value="效果非常好">效果非常好</a>
                    <a href="javascript:;" value="效果非常好">效果非常好</a>
                </div>
            </div>
            <div class="jiriji_6 clearfix">
                <span class="jianjuli">踩一句：</span>
                <div class="jiriji_5 clearfix" id="stepon">
                    <a href="javascript:;" value="效果非常不好">效果非常不好</a>
                    <a href="javascript:;" value="效果非常不好">效果非常不好</a>
                    <a href="javascript:;" value="效果非常不好">效果非常不好</a>
                    <a href="javascript:;" value="效果非常不好">效果非常不好</a>
                    <a href="javascript:;" value="效果非常不好">效果非常不好</a>
                    <a href="javascript:;" value="效果非常不好">效果非常不好</a>
                    <a href="javascript:;" value="效果非常不好">效果非常不好</a>
                    <a href="javascript:;" value="效果非常不好">效果非常不好</a>
                </div>
            </div>
            <a class="chuangjiandl" href="javascript:;">创建 </a>
        </div>
   </div>
   <div id="dialog">
	   <div class="mes_img">
		   <a href="javascript:;" class="GuanBi_ei" >关闭</a>
	   	   <ul style="margin-left: 50px; " class="GuanBi_Li">
			   <c:import url="/pc/view/diarybook/getProductorder.do"></c:import> 
		   </ul>
		   <a class="queding" href="javascript:;" >确定</a>
	   </div>
   </div>
   <script>
	   $("#fabulous>a").click(function(){
			$(this).toggleClass("active");
		})
	   $("#stepon>a").click(function(){
			$(this).toggleClass("actives");
		})
		$(function(){
	   		$('.GuanBi_ei').click(function(){
	   	   		$('#dialog').hide();
	   		});
	   		$(document).bind("click",function(e){ 
	   			var target = $(e.target); 
	   			if(target.closest("#dialog").length == 0){ 
	   			$("#dialog").hide(); 
	   		} 
	   		});
		});
		$(function(){
			$('.queding').click(function(){
	   			var productId = parent.$(".blue_1 .mes_44 .productorderId").val();
	   			$("#productid").html("<input type="+"hidden"+" value="+productId+" id="+"pid"+">")
	   			var prodname = parent.$(".blue_1 .mes_44 .xmnae").text();
	   			$(".jiriji_2").html("<span>"+prodname+"</span>")
	   			var merchantName = parent.$(".blue_1 .mes_44 .mername").text();
	   			$("#yiyuan").html("<span>"+merchantName+"</span>")
	   			var productName = parent.$(".blue_1 .mes_44 .poname").text();
	   			$("#biaoqian").html("<span>"+productName+"</span>")
				$('#dialog').hide();
	   		});
	   		$(document).bind("click",function(e){ 
	   			var target = $(e.target); 
	   			if(target.closest("#dialog").length == 0){ 
	   			$("#dialog").hide(); 
	   		} 
	   		});
		}); 
	</script>                
   <style>
   .GuanBi_Li{
   		height:504px;
   		overflow: auto;
   		width:783px;
   }
     .GuanBi_ei{    
	   	float: right;
	    height: 20px;
	    margin-top: 10px;
	    margin-right: 10px;
	    font-size: 18px;
	    position: absolute;
	    top:4px;
	    right: 15px;
   	}
   	.queding{
	    font-size: 18px;
	    width: 60px;
	    position: absolute;
	    color: #fff;
    	text-align: center;
	    background-color: #FF9C00;
	    border-radius: 5px;
	    bottom: 20px;  
	    right: 20px;
	    float: right;
	} 
   	
   	#fabulous>.active{
	   	border: 1px solid #2ba6d9; 
	   	border-radius: 5px;  
	   	box-shadow: 0 0 2px 2px #2ba6d9 ;
		-moz-box-shadow: 0 0 2px 2px #2ba6d9 ;
		-webkit-box-shadow: 0 0 2px 2px #2ba6d9 ;
		-o-box-shadow: 0 0 2px 2px #2ba6d9 ;
		-ms-box-shadow: 0 0 2px 2px #2ba6d9 ;
	   	color: #2ba6d9;
   	}
   	#stepon>.actives{
	   	border: 1px solid #de2b2b; 
	   	border-radius: 5px;  
	   	box-shadow: 0 0 2px 2px #de2b2b ;
		-moz-box-shadow: 0 0 2px 2px #de2b2b ;
		-webkit-box-shadow: 0 0 2px 2px #de2b2b ;
		-o-box-shadow: 0 0 2px 2px #de2b2b ;
		-ms-box-shadow: 0 0 2px 2px #de2b2b ;
	   	color: #de2b2b;
   	}
   </style>
   
  <%@include file="/common/bottom.jsp"%>