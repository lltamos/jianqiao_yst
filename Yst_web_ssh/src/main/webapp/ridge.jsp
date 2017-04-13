<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<script type="text/javascript">
	function changeType(product_type_id){
		pageGo('${baseurl}/product!productListPage?product_type_id='+product_type_id);
	}
</script>
<script src="${baseurl}/js/jianqiao.js"></script>
<!--                     头                                  -->
    <ul class="nav">
        <li class=" nav_1 blue" onclick="changeType('')">首页</li>
        <s:iterator value="#productTypeList" status="i" >
		<s:if test="#i.index<=6"><li class="nt_2" onclick="changeType('<s:property value="product_type_id" />')"><s:property value="name" /></li></s:if>
		</s:iterator>
        <li class="ing">&nbsp;</li>
    </ul>
    <div class="tiao"></div>
<!--                    导航栏                                  -->
    <div class="content clearfix">
        <ul class="cont">
            <li class="won_1">分享给朋友</li>
            <li class="won_2">新浪微博</li>
            <li class="won_3">QQ空间</li>
            <li class="won_4">腾讯微博</li>
            <li class="won_5">网易微博</li>
            <li class="won_6">人人网</li>
            <li class="won_7">开心网</li>
            <li class="won_8">咨询客户</li>
        </ul>
<!--                       左侧导航                           -->
        <div class="tent">
            <p class="ten_1">【<s:property value="productType.name" />】<s:property value="name" /></p>
            <p class="ten_2">地点：<s:property value="merchant.name" /></p>
			<s:property value="detail_html"  escape="false" />
            <p class="ten_5" onclick="easemobIM()">在线咨询专家</p>
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