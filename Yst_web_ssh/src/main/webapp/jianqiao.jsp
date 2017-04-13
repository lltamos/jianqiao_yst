<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<script type="text/javascript">
    $('.carousel').carousel({
      interval: 5000
    });
	function changeType(product_type_id){
		pageGo('${baseurl}/product!productListPage?product_type_id='+product_type_id);
	}
	function productGo(product_id){
		pageGo('${baseurl}/product!productPage?product_id='+product_id);
	}
</script>
<s:set var="type_id" value="product_type_id"></s:set>
<script src="${pageContext.request.contextPath}/js/jianqiao.js"></script>
<ul class="nav">
	<li class="nav_1<s:if test="#type_id==null"> blue</s:if>"  onclick="changeType('')" >首页</li>
	<s:iterator value="#productTypeList" status="i" >
		<s:if test="#i.index<=6"><li <s:if test="#type_id==product_type_id">class="blue"</s:if> onclick="changeType('<s:property value="product_type_id" />')"><s:property value="name" /></li></s:if>
	</s:iterator>
	<li class="ing">&nbsp;</li>
</ul>
<!--                    导航栏                                  -->
<div id="myCarousel" class="carousel slide">
   <!-- 轮播（Carousel）指标 -->
   <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
   </ol>   
   <!-- 轮播（Carousel）项目 -->
   <div class="carousel-inner">
      <div class="item active">
         <img src="img/jq-2.jpg" alt="First slide">
      </div>
      <div class="item">
         <img src="img/jq-2.jpg" alt="Second slide">
      </div>
      <div class="item">
         <img src="img/jq-2.jpg" alt="Third slide">
      </div>
   </div>
   <!-- 轮播（Carousel）导航 -->
   <a class="carousel-control left" href="#myCarousel" 
      data-slide="prev">&lsaquo;</a>
   <a class="carousel-control right" href="#myCarousel" 
      data-slide="next">&rsaquo;</a>
</div> 
<!--                       广告                                -->
<div class="content clearfix">
	<ul class="cont">
		<li class="nt_2<s:if test="#type_id==null"> cont_1</s:if>" >健康首页</li>
		<s:iterator value="#productTypeList" status="i" >
		<s:if test="#i.index<=6"><li class="nt_2<s:if test="#type_id==product_type_id"> cont_1</s:if>" onclick="changeType('<s:property value="product_type_id" />')"><s:property value="name" /></li></s:if>
		</s:iterator>

		<li class="cont_2"></li>

		<li class="nt_2 cont_3">特色推荐</li>
		<li class="nt_2 cont_4">名医专家</li>
		<li class="nt_2 cont_5">活动会</li>
	</ul>
	<!--                       左侧导航                           -->
	<div class="tent">
		<ul class="ent_1 clearfix">
			<li class="enl_1">全部分类：</li>
			<li class="sou<s:if test="#type_id==null"> enl_2</s:if>" onclick="changeType('')">全部</li>
			<s:iterator value="#productTypeList">
				<li class="sou<s:if test="#type_id==product_type_id"> enl_2</s:if>" onclick="changeType('<s:property value="product_type_id" />')"><s:property value="name" /></li>
			</s:iterator>
		</ul>
		<!--                       中间导航                          -->
		<ul class="city clearfix">
			<li class="city_1">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;城：</li>
			<li class="city_3 city_2">全部</li>
			<s:iterator value="#provList">
			<li class="city_3"><s:property value="name" /></li>
			</s:iterator>
		</ul>
		<!--                    地区选择                                  -->
		<s:iterator value="#appResult.data">

			<div class="nrdl clearfix">
				<div class="nrdl_1" onclick="productGo('<s:property value="product_id" />')">
					<h3>
						<span>项目名称 : </span>
						<s:property value="name" />
					</h3>
					<img src="<s:property value="image1" />">
				</div>
				<div class="nrdl_2">
					<p class="dl_1"  onclick="productGo('<s:property value="product_id" />')">
						【
						<s:property value="productType.name" />
						】
						<s:property value="name" />
					</p>
					<p class="dl_2"  onclick="productGo('<s:property value="product_id" />')">
						<s:property value="name" />
					</p>
					<p class="dl_3"  onclick="productGo('<s:property value="product_id" />')">
						<s:property value="des1" />
					</p>
					<p class="dl_4" onclick="easemobIM()">咨询专家</p>
				</div>
			</div>
		</s:iterator>
		<!--                     项目                                        -->
		<!-- 分页div -->

	<ul class="page">
                <s:iterator begin="1" end="#appResult.page_model.pageCount" step="1"  var="No"  >
               			<li <s:if test="#appResult.page_model.pageNo+1==#No">class="age_2"</s:if>  <s:if test="#appResult.page_model.pageNo+1!=#No">onclick="pageGo('<s:property value="#appResult.uri" />?page_no=<fmt:formatNumber value="${No-1}" pattern="0" />&page_size=<s:property value="#appResult.page_model.pageSize" />&product_type_id=${type_id }')"</s:if>><s:property value="#No" /></li>
               		</s:iterator>
               
                <li class="age_1" <s:if test="(#appResult.page_model.pageNo+1)!=#appResult.page_model.pageCount">onclick="pageGo('<s:property value="#appResult.uri" />?page_no=<fmt:formatNumber value="${appResult.page_model.pageNo+1}" pattern="0" />&page_size=<s:property value="#appResult.page_model.pageSize" />&product_type_id=${type_id }')"</s:if>>&nbsp;</li>
            </ul>
		<!--                页码                                          -->
	</div>
	<div class="ghtr">
		<h4>关于建桥</h4>
		<ul class="ghtr_1">
		<s:iterator value="#serviceDescList">
			<li class="ghtr_12 clearfix"><img src="${baseurl}/<s:property value="image_url" />">
				<p class="htr">
					<span class="htr_1"><s:if test="type==1">疑难杂症</s:if>
					<s:if test="type==2">家庭服务</s:if>
					<s:if test="type==3">健康商品</s:if></span> <span class="htr_2"><s:property value="type_desc" /></span>
				</p></li>
				</s:iterator>
		</ul>
	</div>
	<!--                                 右侧                                   -->
	
    </div>