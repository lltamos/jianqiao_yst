<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>

<head>
<meta charset="UTF-8">
<title>疑难杂症</title>
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt}/css/disease.css">
<link rel="stylesheet" href="${cxt}/js/slider.css">
<script src="${cxt}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt}/js/jianqiaoindex.js"></script>
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
				$("#bob").load("${cxt }/pc/view/product/todisease?rows=12&page="+currPage);
			}
		});
	}
	function toProduct(id){
		location.href="${cxt }/service/to-package?id="+id;
	}
	
	function toChat(id){
		location.href=""+id;
	}
</script>



<div id="bob">
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<script src="${cxt}/js/slider.js"></script>
	<div class="banner_index">
		<div class="banner_menu">
			<ul>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						class="DaoHang_img" src="${cxt }/img/imag_2/DaoHang_03.png">
						<span>外科</span>
				</a>
				<div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div>
				</li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_07.png"> <span>内科</span>
				</a>
					<div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div>
				</li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_11.png"> <span>骨科</span>
				</a>
					<div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_14.png"> <span>男科</span>
				</a>
					<div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_17.png"> <span>妇科</span>
				</a><div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_21.png"> <span>儿科</span>
				</a><div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_24.png"> <span>精神科</span>
				</a><div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_28.png"> <span>眼科</span>
				</a><div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_31.png"> <span>口腔科</span>
				</a><div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_34.png"> <span>耳鼻喉科</span>
				</a><div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_37.png"> <span>中医科</span>
				</a><div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
				<li class="DaoHang"><a class="DaoHang_a" href=""> <img
						src="${cxt }/img/imag_2/DaoHang_40.png"> <span>皮肤性病科</span>
				</a><div class="KeShiFenLei">
					<div class="KeShiFenLei_1">
						<div class="KeShiFenLei_2">
						</div>
					</div>
				</div></li>
			</ul>
		</div>
	</div>

	<script>
            $(function(){
                var timer=null;
                $('.DaoHang').mouseover(function(event){
                    event.stopPropagation();
                    clearTimeout(timer);
                    $('.KeShiFenLei').hide();
                    var dapetementName = $(this).find('span').text();
                    var a = $(this).find('.KeShiFenLei_2');
                    $.ajax({
                    	  type:"GET",
                    	  url:"${cxt}/pc/view/product/category?departmentName="+dapetementName,
                    	  dataType: "text",
                    	  success: function(result){
                    		var datas =eval('(' + result + ')');
                    		 $.each(datas, function (index, value) {
                    			 if(value.departmentName=dapetementName){
									var aStr ="<a href="+'${cxt}/pc/view/product/search?serviceName='+value.serviceName+">" 
									+value.serviceName+"|</a>"; 
									var aContent = a.text()
									if(aContent.indexOf(value.serviceName)<0){
										a.append(aStr);
									}
                    			 }
                              });
                    	  }
                    	}); 
                    $(this).find('.KeShiFenLei').show();
                    $(this).find('.DaoHang_a').css('background','#d2d2d2');
                });
                $('.DaoHang').mouseout(function(){
                        $('.DaoHang_a').css('background','');
                        timer=setTimeout(function(){
                             $('.KeShiFenLei').hide();
                             
                        },150);
                  
                });
                $('.KeShiFenLei').mouseover(function(event){
                    event.stopPropagation();
                    clearTimeout(timer);
                    $(this).show();
                    $(this).siblings('.DaoHang_a').css('background','#d2d2d2');
                }); 
                 
                
                
            });
        </script>
	<div id="Daohangdlw" class="flexslider">

		<ul class="slides">

			<li><div class="img">
					<div class="GuangGaoTu"></div>
				</div></li>

			<li><div class="img">
					<div class="TuTu_2"></div>
				</div></li>

			<li><div class="img">
					<div class="TuTu_1"></div>
				</div></li>

			<li><div class="img">
					<div class="TuTu_3"></div>
				</div></li>

		</ul>


	</div>

	<script>
            $('#Daohangdlw').flexslider({

                animation: "slide",
                
                slideshowSpeed: 6000,

                direction:"horizontal",

                easing:"swing",

                directionNav: false

            });
        </script>



<div class="titleline"></div>
<div class="diffdisease">
	<ul class="diseaselistbox">
		<c:forEach items="${listProductAll }" var="product">
			<li>
				<div class="diseasepic">
					<img onclick="toProduct(${product.id})"
							<c:if test="${!empty product.address }"> src="${img_service }/${product.address}"></c:if>
						<c:if test="${empty product.address }"> src="${cxt }/img/diarypic1.jpg" alt=""></c:if>
					</a>
				</div>
				<div class="diseaselisttitle">【${product.productTypeName}】${product.name}</div>
				<div class="diseasedetail">${product.des1}</div>
				<div class="diseasebtn">
					<div class="dbtnleft">
						<div class="btniconbox">
							<div class="icon"></div>
						</div>
						${product.productTypeName}
					</div>
					<div class="dbtnright">
						<%-- <a href="javascript:;" onclick="toChat(${product.id})"> --%>
							<a style=display: inline-block; href='#' onclick=window.open('${cxt}/goIm/sayadmin','jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px')>
							<div class="rbtnicon">
							
							</div>
						</a>
					</div>
				</div>
			</li>
		</c:forEach>
	</ul>
	<div class="page-location">
		<div id="customertransfersuccess-pager" class="ui-paging ui-paging1"></div> 
	</div>
	<%@include file="/common/bottom.jsp"%>
	</div>
	<%-- 分页div --%> 
	
