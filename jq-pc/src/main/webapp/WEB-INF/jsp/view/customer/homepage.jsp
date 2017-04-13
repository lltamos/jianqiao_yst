<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt}/css/base.css">
<link rel="stylesheet" href="${cxt}/css/jianqiaoindex.css">
<script src="${cxt}/js/jianqiaoindex.js"></script>
<link rel="stylesheet" href="${cxt}/js/slider.css">

<!-- <script src ='http://kefu.easemob.com/webim/easemob.js?tenantId=7555&hide=true' async='async'></script>
 -->
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<div id="Daohangdlw1" class="flexslider">

	<ul class="slides"  id="containers">

	</ul>

</div>

<script>
	function initSliderfix(data) {
		
		
		$.each(data, function() {

			var item=	'<li>'+
			'<div class="img">'+
			'<div style=\"background-image: url(\''+
			'http://yst-images.img-cn-hangzhou.aliyuncs.com/'+this.address+'\');\"' +
			' class="GuangGaoTu">'+
			'</div></div></li>';
		
			$("#containers").append(item);
			
		});
		
		$('#Daohangdlw1').flexslider({

			animation : "slide",

			slideshowSpeed : 6000,

			direction : "horizontal",

			easing : "swing",

			directionNav : true

		});

	}

	$(function() {

		$.ajax({
			type : "GET",
			url : "${cxt}/pc/view/banner/fecthbanner",
			success : function(result) {
				var jdata = JSON.parse(result);
				if (jdata.code == 1) {
					initSliderfix(jdata.content);
				}
			}
		});

		

		
	})
</script>
<script type="text/javascript">
	$
			.ajax({
				type : "GET",
				url : "${cxt}/pc/view/message/getAll",
				success : function(result) {
					var datas = eval('(' + result + ')');
					var content = datas.content;
					$.each(content,
									function(index, value) {
										var list = '<li class="swiper-slide">'
												+ '<div class="picbox"><a href="javascript:;">'
												+ '<img style="height: 200px;width: 300px" src="${img_service }/'+value.image+'" alt=""></a></div>'
												+ '<h3 class="newtitle">'
												+ value.title + '</h3>'
												+ '<p class="detailtxt">'
												+ value.des + '</p></li>';
										$("#messagelist").append(list);
									});
				}
			});
</script>
<script type="text/javascript">
	$(function() {
		var ul = $("#diseaselist");
		$
				.ajax({
					type : "GET",
					url : "${cxt}/pc/view/product/productindex",
					success : function(result) {
						var datas = eval('(' + result + ')');
						var content = datas.content;
						$
								.each(
										content,
										function(index, value) {
											var arry = [
													'<div class="diseasepic" onclick="toProduct(',
													value.id,
													')">',
													'<a href="javascript:;"> <img src="${img_service }/',value.address,
				           '"alt="" >',
													'</a>',
													'</div>',
													'<div class="diseaselisttitle">【',
													value.productTypeName,
													'】',
													value.name,
													'</div>',
													'<div class="diseasedetail">',
													value.des1,
													'</div>',
													'<div class="diseasebtn">',
													'<div class="dbtnleft">',
													'<div class="btniconbox">',
													'<div class="icon"></div>',
													'</div>',
													value.productTypeName,
													'</div>',
													'<div class="dbtnright">',
													"<a style='display: inline-block; href='#' target='_blank' onclick="
															+ "window.open('${cxt}/goIm/sayadmin','jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=684px,height=700px')",
													'<div class="rbtnicon"></div>',
													'</a>', '</div>', '</div>' ]
											var list = '<li class="lastdis">'
													+ arry.join("") + '</li>';
											ul.append(list);
										});
					}
				});

	});
	/* <!-- 跳转到疑难杂症页面--> */
	function todisease() {
		window.location.href = "${cxt }/pc/view/product/todisease";
	}

	/* <!-- 跳转到名医详情页--> */
	function doctorinfo() {
		$(document)
				.click(
						function(e) {
							var id = $(e.target).attr("id");
							window.location.href = "${cxt }/pc/view/doctor/showdoctorinfo?id="
									+ id;
						});
	}
	/* <!-- 跳转到服务包页面--> */
	function toProduct(id) {
		var doctorid = $.trim(id);
		window.location.href = "${cxt }/service/to-package?id=" + doctorid;
	}

	/* <!-- 跳转到患者求医页面--> */
	function publishdisease() {
		window.location.href = "${cxt }/pc/view/doctor/publishdisease";
	}
</script>
<div class="jianqiaonew">
	<h2 class="title">健桥资讯</h2>
	<div class="titleline"></div>
	<div class="txt">
		北京上善健桥健康管理中心战略合作伙伴、中国老年保健医学研究会、《健康指南》杂志社、国家中医药管理局
		<div class="next"></div>
		<div class="pre"></div>
	</div>
	<div class="newbox flexslider">
		<ul class="swiper-wrapper slides" id="messagelist">
		</ul>
	</div>
	<script>
		var list = parseInt($('.swiper-slide').size());
		var b = 1;
		var l = 0;
		$('.next').click(function() {
			var i = b * 4;
			if (list == i) {
				return false;
			}
			b++;
			l++;
			$('.slides').stop().animate({
				'left' : l * -1100 + 'px'
			}, 1000);
		});

		$('.pre').click(function() {
			var i = b * 4;
			if (l == 0) {
				return false;
			}
			b--;
			l--;
			$('.slides').stop().animate({
				'left' : l * -1100 + 'px'
			}, 1000);
		});
	</script>
	<!-- <div class="morenew">
		<a href="javascript:;">更多新闻咨询</a>
	</div> -->
</div>
<div class="jianqiaoservice">
	<h2 class="title">疑难杂症导医</h2>
	<div class="stitleline"></div>
	<div class="more">
		<a href="javascript:onclick=todisease();">更多+</a>
	</div>
	<ul class="diseaselistbox" id="diseaselist">
	</ul>
</div>
<%-- <div class="doctor">
		<h2 class="title">名医高手</h2>
		<div class="stitleline"></div>

		<div class="doctorlistbox">
			<ul class="doctorlist">
				<li>
					<div class="disease">肩周炎</div>
					<div class="doctormain">
						<div class="doctorintro">
							<div class="doctorpic">
								<img src="${cxt}/img/doctorpic.jpg" alt="">
							</div>
							<div class="doctorintrotxt">
								<div class="doctorname">
									王天一 
								</div>
								<p class="job">职位：主任医师</p>
								<p class="introduce">简介：决定工程品质的第一要素是工艺，高品质施工工艺是保证空间设计达到理想效果的关键。决定工程品质的第一要素是工艺高品质施工工艺是保证空...</p>
							</div>
						</div>
					</div>
					<div class="otherdoctor">
						<div class="otherdoclist">
							<div class="otherdocname">
								李中川
							</div>
							<div class="otherdocjob">
								职位：肩周炎专家
							</div>
						</div>
						<div class="otherdoclist">
							<div class="otherdocname">
								韩振东
							</div>
							<div class="otherdocjob">
								职位：肩周炎主任医师
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="disease">肩周炎</div>
					<div class="doctormain">
						<div class="doctorintro">
							<div class="doctorpic">
								<img src="${cxt}/img/doctorpic.jpg" alt="">
							</div>
							<div class="doctorintrotxt">
								<div class="doctorname">
									王天一 
								</div>
								<p class="job">职位：主任医师</p>
								<p class="introduce">简介：决定工程品质的第一要素是工艺，高品质施工工艺是保证空间设计达到理想效果的关键。决定工程品质的第一要素是工艺高品质施工工艺是保证空...</p>
							</div>
						</div>
					</div>
					<div class="otherdoctor">
						<div class="otherdoclist">
							<div class="otherdocname">
								李中川
							</div>
							<div class="otherdocjob">
								职位：肩周炎专家
							</div>
						</div>
						<div class="otherdoclist">
							<div class="otherdocname">
								韩振东
							</div>
							<div class="otherdocjob">
								职位：肩周炎主任医师
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="disease">肩周炎</div>
					<div class="doctormain">
						<div class="doctorintro">
							<div class="doctorpic">
								<img src="${cxt}/img/doctorpic.jpg" alt="">
							</div>
							<div class="doctorintrotxt">
								<div class="doctorname">
									王天一 
								</div>
								<p class="job">职位：主任医师</p>
								<p class="introduce">简介：决定工程品质的第一要素是工艺，高品质施工工艺是保证空间设计达到理想效果的关键。决定工程品质的第一要素是工艺高品质施工工艺是保证空...</p>
							</div>
						</div>
					</div>
					<div class="otherdoctor">
						<div class="otherdoclist">
							<div class="otherdocname">
								李中川
							</div>
							<div class="otherdocjob">
								职位：肩周炎专家
							</div>
						</div>
						<div class="otherdoclist">
							<div class="otherdocname">
								韩振东
							</div>
							<div class="otherdocjob">
								职位：肩周炎主任医师
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<div class="otherservice">
			<div class="otherservicel">
				<div class="docgoin">
					<div class="goinleft">
						<div class="gointitle">名医入驻：</div>
						<div class="goinnote">
							全国民间高手、名医专家都在这里开设自己的工作室，互联网+健康医疗势不可挡，把你精湛的技术和研究成果与全国病友一起分享，普及更多在病痛中挣杂的朋友
						</div>
					</div>
					<div class="goinbtn">
						<a href="javascript:;">
							我要入驻>>
						</a>
					</div>
				</div>
				<div class="docgoin findmedic">
					<div class="goinleft">
						<div class="gointitle">患者求医：</div>
						<div class="goinnote">
							输入您的求医信息，病情病史，我们会派专家组给你推荐适合您的专家或良方，这里有百万病友与您同在健康路上
						</div>
					</div>
					<div class="goinbtn">
						<a href="javascript:;">
							马上求医>>
						</a>
					</div>
				</div>
			</div>
			<div class="otherservicer">
				<div class="docsearch">
					<div class="docsearchtitle">
						名医搜索：
					</div>
					<div class="inputbox">
						<input type="text" >
						<a href="javascript:;">搜索</a>
						<div class="placeholder">
						    <div class="searchicon"></div>
                            请输入查找关键词
				        </div>
					</div>
				</div>
				<div class="docsearch patientsearch">
					<div class="docsearchtitle">
						搜索患者：
					</div>
					<div class="inputbox">
						<input type="text" >
						<a href="javascript:;">搜索</a>
						<div class="placeholder">
						    <div class="searchicon"></div>
                            请输入查找关键词
				        </div>
					</div>
				</div>
			</div>
		</div>
	</div> --%>
<div class="doctor">
	<h2 class="title">名医高手</h2>
	<div class="stitleline"></div>
	<div class="txt">
		<div class="next"></div>
		<div class="pre"></div>
	</div>
	<div class="doctorlistbox">
		<ul class="doctorlist">
			<li>
				<div class="disease">肩周炎</div>
				<div class="doctormain">
					<div class="doctorintro">
						<div class="doctorpic">
							<img id="1" src="img/doctorpic.jpg" alt="" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
						</div>
						<div class="doctorintrotxt">
							<div class="doctorname">王天一</div>
							<p class="job">职位：主任医师</p>
							<p class="introduce">简介：决定工程品质的第一要素是工艺，高品质施工工艺是保证空间设计达到理想效果的关键。决定工程品质的第一要素是工艺高品质施工工艺是保证空...</p>
						</div>
					</div>
				</div>
				<div class="otherdoctor">
					<div class="otherdoclist">
						<div id="1" class="otherdocname" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
							李中川
						</div>
						<div class="otherdocjob">
							<div class="otherdocjob_1">
								<span>职位：</span> <span>简介：</span>
							</div>
							<div class="otherdocjob_2">
								<p>肩周炎专家</p>
								<p>决定医疗品质的第一要素是医生的技术，医生要医生要医生要医生要</p>
							</div>
						</div>
					</div>
					<div class="otherdoclist">
						<div id="1" class="otherdocname" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
							李中川
						</div>
						<div class="otherdocjob">
							<div class="otherdocjob_1">
								<span>职位：</span> <span>简介：</span>
							</div>
							<div class="otherdocjob_2">
								<p>肩周炎专家</p>
								<p>决定医疗品质的第一要素是医生的技术，医生要医生要医生要医生要</p>
							</div>
						</div>
					</div>
					<div class="otherdoclist">
						<div id="1" class="otherdocname" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
							李中川
						</div>
						<div class="otherdocjob">
							<div class="otherdocjob_1">
								<span>职位：</span> <span>简介：</span>
							</div>
							<div class="otherdocjob_2">
								<p>肩周炎专家</p>
								<p>决定医疗品质的第一要素是医生的技术，医生要医生要医生要医生要</p>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="disease">肩周炎</div>
				<div class="doctormain">
					<div class="doctorintro">
						<div class="doctorpic">
							<img id="1" src="img/doctorpic.jpg" alt="" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
						</div>
						<div class="doctorintrotxt">
							<div class="doctorname">王天一</div>
							<p class="job">职位：主任医师</p>
							<p class="introduce">简介：决定工程品质的第一要素是工艺，高品质施工工艺是保证空间设计达到理想效果的关键。决定工程品质的第一要素是工艺高品质施工工艺是保证空...</p>
						</div>
					</div>
				</div>
				<div class="otherdoctor">
					<div class="otherdoclist">
						<div id="1" class="otherdocname" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
							李中川
						</div>
						<div class="otherdocjob">
							<div class="otherdocjob_1">
								<span>职位：</span> <span>简介：</span>
							</div>
							<div class="otherdocjob_2">
								<p>肩周炎专家</p>
								<p>决定医疗品质的第一要素是医生的技术，医生要医生要医生要医生要</p>
							</div>
						</div>
					</div>
					<div class="otherdoclist">
						<div id="1" class="otherdocname" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
							李中川
						</div>
						<div class="otherdocjob">
							<div class="otherdocjob_1">
								<span>职位：</span> <span>简介：</span>
							</div>
							<div class="otherdocjob_2">
								<p>肩周炎专家</p>
								<p>决定医疗品质的第一要素是医生的技术，医生要医生要医生要医生要</p>
							</div>
						</div>
					</div>
					<div class="otherdoclist">
						<div id="1" class="otherdocname" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
							李中川
						</div>
						<div class="otherdocjob">
							<div class="otherdocjob_1">
								<span>职位：</span> <span>简介：</span>
							</div>
							<div class="otherdocjob_2">
								<p>肩周炎专家</p>
								<p>决定医疗品质的第一要素是医生的技术，医生要医生要医生要医生要</p>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="disease">肩周炎</div>
				<div class="doctormain">
					<div class="doctorintro">
						<div class="doctorpic">
							<img id="1" src="img/doctorpic.jpg" alt="" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
						</div>
						<div class="doctorintrotxt">
							<div class="doctorname">王天一</div>
							<p class="job">职位：主任医师</p>
							<p class="introduce">简介：决定工程品质的第一要素是工艺，高品质施工工艺是保证空间设计达到理想效果的关键。决定工程品质的第一要素是工艺高品质施工工艺是保证空...</p>
						</div>
					</div>
				</div>
				<div class="otherdoctor">
					<div class="otherdoclist">
						<div id="1" class="otherdocname" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
							李中川
						</div>
						<div class="otherdocjob">
							<div class="otherdocjob_1">
								<span>职位：</span> <span>简介：</span>
							</div>
							<div class="otherdocjob_2">
								<p>肩周炎专家</p>
								<p>决定医疗品质的第一要素是医生的技术，医生要医生要医生要医生要</p>
							</div>
						</div>
					</div>
					<div class="otherdoclist">
						<div id="1" class="otherdocname" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
							李中川
						</div>
						<div class="otherdocjob">
							<div class="otherdocjob_1">
								<span>职位：</span> <span>简介：</span>
							</div>
							<div class="otherdocjob_2">
								<p>肩周炎专家</p>
								<p>决定医疗品质的第一要素是医生的技术，医生要医生要医生要医生要</p>
							</div>
						</div>
					</div>
					<div class="otherdoclist">
						<div id="1" class="otherdocname" onclick="doctorinfo()">
							<!-- 这里的id表示该医生id -->
							李中川
						</div>
						<div class="otherdocjob">
							<div class="otherdocjob_1">
								<span>职位：</span> <span>简介：</span>
							</div>
							<div class="otherdocjob_2">
								<p>肩周炎专家</p>
								<p>决定医疗品质的第一要素是医生的技术，医生要医生要医生要医生要</p>
							</div>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<div class="otherservice">
		<div class="otherservicel">
			<div class="docgoin">
				<div class="goinleft">
					<div class="gointitle">名医入驻：</div>
					<div class="goinnote">
						全国民间高手、名医专家都在这里开设自己的工作室，互联网+健康医疗势不可挡，把你精湛的技术和研究成果与全国病友一起分享，普及更多在病痛中挣杂的朋友
					</div>
				</div>
				<div class="goinbtn">
					<a href="javascript:;" onclick="goToDoctor()"> 我要入驻>> </a>
				</div>
			</div>
			<div class="docgoin findmedic">
				<div class="goinleft">
					<div class="gointitle">患者求医：</div>
					<div class="goinnote">
						输入您的求医信息，病情病史，我们会派专家组给你推荐适合您的专家或良方，这里有百万病友与您同在健康路上</div>
				</div>
				<div class="goinbtn">
					<a href="javascript:onclick=publishdisease();"> 马上求医>> </a>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function() {
		$('.placeholder').click(function() {
			$(this).css('display', 'none');
			$(this).siblings('input').focus();
		});
		$('input').focus(function() {
			$(this).siblings('.placeholder').css('display', 'none');
		});
		$('input').blur(function() {
			if ($(this).val() == '') {
				$(this).siblings('.placeholder').css('display', 'block');
			}
		});
	});
</script>

<div class="seekdoc">
	<h2 class="title">患者求医直播</h2>
	<div class="stitleline"></div>
	<div class="diseasebtn">
		<a href="javascript:;" class="checked">糖尿病</a> <a href="javascript:;">脑瘫</a>
		<a href="javascript:;">肺炎</a> <a href="javascript:;">肠炎</a> <a
			href="javascript:;">呼吸道传染病</a> <a href="javascript:;">心绞痛</a> <a
			href="javascript:;">白血病</a>
	</div>
	<div class="seekdocbox">
		<ul class="seekdoclist">
			<li>
				<div class="seekdocimg">
					<a href="javascript:;"> <img src="${cxt}/img/seekdoc.jpg"
						alt="">
					</a>
				</div>
				<div class="detailbox">
					<div class="line name">
						姓名：<span>华立新</span>
					</div>
					<div class="line sex">
						性别：<span>男</span>
					</div>
					<div class="line age">
						年龄：<span>25</span>
					</div>
					<div class="line address">
						地址：<span>北京市海淀区上地六街</span>
					</div>
					<div class="line diseasedet">
						病情介绍：<span>上门、家庭服务，提供就医绿色通道及大病专家会诊...</span>
					</div>
				</div>
			</li>
			<li>
				<div class="seekdocimg">
					<a href="javascript:;"> <img src="${cxt}/img/seekdoc.jpg"
						alt="">
					</a>
				</div>
				<div class="detailbox">
					<div class="line name">
						姓名：<span>华立新</span>
					</div>
					<div class="line sex">
						性别：<span>男</span>
					</div>
					<div class="line age">
						年龄：<span>25</span>
					</div>
					<div class="line address">
						地址：<span>北京市海淀区上地六街</span>
					</div>
					<div class="line diseasedet">
						病情介绍：<span>上门、家庭服务，提供就医绿色通道及大病专家会诊...</span>
					</div>
				</div>
			</li>
			<li>
				<div class="seekdocimg">
					<a href="javascript:;"> <img src="${cxt}/img/seekdoc.jpg"
						alt="">
					</a>
				</div>
				<div class="detailbox">
					<div class="line name">
						姓名：<span>华立新</span>
					</div>
					<div class="line sex">
						性别：<span>男</span>
					</div>
					<div class="line age">
						年龄：<span>25</span>
					</div>
					<div class="line address">
						地址：<span>北京市海淀区上地六街</span>
					</div>
					<div class="line diseasedet">
						病情介绍：<span>上门、家庭服务，提供就医绿色通道及大病专家会诊...</span>
					</div>
				</div>
			</li>
			<li>
				<div class="seekdocimg">
					<a href="javascript:;"> <img src="${cxt}/img/seekdoc.jpg"
						alt="">
					</a>
				</div>
				<div class="detailbox">
					<div class="line name">
						姓名：<span>华立新</span>
					</div>
					<div class="line sex">
						性别：<span>男</span>
					</div>
					<div class="line age">
						年龄：<span>25</span>
					</div>
					<div class="line address">
						地址：<span>北京市海淀区上地六街</span>
					</div>
					<div class="line diseasedet">
						病情介绍：<span>上门、家庭服务，提供就医绿色通道及大病专家会诊...</span>
					</div>
				</div>
			</li>
			<li>
				<div class="seekdocimg">
					<a href="javascript:;"> <img src="${cxt}/img/seekdoc.jpg"
						alt="">
					</a>
				</div>
				<div class="detailbox">
					<div class="line name">
						姓名：<span>华立新</span>
					</div>
					<div class="line sex">
						性别：<span>男</span>
					</div>
					<div class="line age">
						年龄：<span>25</span>
					</div>
					<div class="line address">
						地址：<span>北京市海淀区上地六街</span>
					</div>
					<div class="line diseasedet">
						病情介绍：<span>上门、家庭服务，提供就医绿色通道及大病专家会诊...</span>
					</div>
				</div>
			</li>
			<li>
				<div class="seekdocimg">
					<a href="javascript:;"> <img src="${cxt}/img/seekdoc.jpg"
						alt="">
					</a>
				</div>
				<div class="detailbox">
					<div class="line name">
						姓名：<span>华立新</span>
					</div>
					<div class="line sex">
						性别：<span>男</span>
					</div>
					<div class="line age">
						年龄：<span>25</span>
					</div>
					<div class="line address">
						地址：<span>北京市海淀区上地六街</span>
					</div>
					<div class="line diseasedet">
						病情介绍：<span>上门、家庭服务，提供就医绿色通道及大病专家会诊...</span>
					</div>
				</div>
			</li>
			<li>
				<div class="seekdocimg">
					<a href="javascript:;"> <img src="${cxt}/img/seekdoc.jpg"
						alt="">
					</a>
				</div>
				<div class="detailbox">
					<div class="line name">
						姓名：<span>华立新</span>
					</div>
					<div class="line sex">
						性别：<span>男</span>
					</div>
					<div class="line age">
						年龄：<span>25</span>
					</div>
					<div class="line address">
						地址：<span>北京市海淀区上地六街</span>
					</div>
					<div class="line diseasedet">
						病情介绍：<span>上门、家庭服务，提供就医绿色通道及大病专家会诊...</span>
					</div>
				</div>
			</li>
			<li>
				<div class="seekdocimg">
					<a href="javascript:;"> <img src="${cxt}/img/seekdoc.jpg"
						alt="">
					</a>
				</div>
				<div class="detailbox">
					<div class="line name">
						姓名：<span>华立新</span>
					</div>
					<div class="line sex">
						性别：<span>男</span>
					</div>
					<div class="line age">
						年龄：<span>25</span>
					</div>
					<div class="line address">
						地址：<span>北京市海淀区上地六街</span>
					</div>
					<div class="line diseasedet">
						病情介绍：<span>上门、家庭服务，提供就医绿色通道及大病专家会诊...</span>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>
<div class="healthshare">
	<c:import url="/pc/view/diary/toDiaryHomePage.do"></c:import>
	<%-- <h2 class="title">健康分享</h2>
		<div class="stitleline"></div>
		<div class="sharebox">
			<div class="webbershare">
				<div class="wstitle">
					<span class="left">网友分享</span>
					<a href="javascript:;"><span class="right">更多+</span></a>
				</div>
				<div class="webberlist">
					<div class="webbertitle">
						<div class="webberimg">
							<img src="${cxt}/img/webber.jpg" alt="">
						</div>
						<div class="webbertright">
							<div class="sstitle">
								刑小美的七周美白日记第一篇 
							</div>
							<div class="authortime">
								<span>大头葱葱儿</span><span>（2015-12-29）</span>
							</div>
						</div>
					</div>
					<div class="webberimgbox">
						<div class="webberpic webberpic1">
							<img src="${cxt}/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic2">
							<img src="${cxt}/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic3">
							<img src="${cxt}/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic4">
							<img src="${cxt}/img/webberimg1.jpg" alt="">
						</div>
					</div>
					<div class="webbernote">
						刑小美的七周美白日记第一篇 
					</div>
				</div>
				<div class="webberlist">
					<div class="webbertitle">
						<div class="webberimg">
							<img src="${cxt}/img/webber.jpg" alt="">
						</div>
						<div class="webbertright">
							<div class="sstitle">
								刑小美的七周美白日记第一篇 
							</div>
							<div class="authortime">
								<span>大头葱葱儿</span><span>（2015-12-29）</span>
							</div>
						</div>
					</div>
					<div class="webberimgbox">
						<div class="webberpic webberpic1">
							<img src="${cxt}/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic2">
							<img src="${cxt}/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic3">
							<img src="${cxt}/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic4">
							<img src="${cxt}/img/webberimg1.jpg" alt="">
						</div>
					</div>
					<div class="webbernote">
						刑小美的七周美白日记第一篇 
					</div>
				</div>
			</div>
			<div class="recommenditem">
				<div class="wstitle">
					<span class="left">推荐项目</span>
					<a href="javascript:;"><span class="right">更多+</span></a>
				</div>
				<ul class="recommendlist">
					<li>
						<div class="recommendpic">
							<img src="${cxt}/img/recommendimg.jpg" alt="">
						</div>
						<div class="recommendr">
							<div class="retitle">
								足不出户问医生
							</div>
							<div class="redetail">
								患者的复诊费用及加号收费，完全归您所有，利用空余时间轻松赚取额外收入紫色医疗承诺不收取任何中间费用。
							</div>
							<div class="remoney">
								￥<span>400.00</span>
							</div>
						</div>
					</li>
					<li>
						<div class="recommendpic">
							<img src="${cxt}/img/recommendimg.jpg" alt="">
						</div>
						<div class="recommendr">
							<div class="retitle">
								足不出户问医生
							</div>
							<div class="redetail">
								患者的复诊费用及加号收费，完全归您所有，利用空余时间轻松赚取额外收入紫色医疗承诺不收取任何中间费用。
							</div>
							<div class="remoney">
								￥<span>400.00</span>
							</div>
						</div>
					</li>
					<li>
						<div class="recommendpic">
							<img src="${cxt}/img/recommendimg.jpg" alt="">
						</div>
						<div class="recommendr">
							<div class="retitle">
								足不出户问医生
							</div>
							<div class="redetail">
								患者的复诊费用及加号收费，完全归您所有，利用空余时间轻松赚取额外收入紫色医疗承诺不收取任何中间费用。
							</div>
							<div class="remoney">
								￥<span>400.00</span>
							</div>
						</div>
					</li>
					<li>
						<div class="recommendpic">
							<img src="${cxt}/img/recommendimg.jpg" alt="">
						</div>
						<div class="recommendr">
							<div class="retitle">
								足不出户问医生
							</div>
							<div class="redetail">
								患者的复诊费用及加号收费，完全归您所有，利用空余时间轻松赚取额外收入紫色医疗承诺不收取任何中间费用。
							</div>
							<div class="remoney">
								￥<span>400.00</span>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div> --%>
</div>
<div class="bighealth">
	<div class="title">大健康+互联网</div>
	<div class="stitleline1"></div>
	<div class="polygonbox">
		<div class="normal1">
			<img src="${cxt}/img/polygonnormal1.png" alt="">
		</div>
		<div class="normal2">
			<img src="${cxt}/img/polygonnormal2.png" alt="">
		</div>
		<div class="normal3">
			<img src="${cxt}/img/polygonnormal3.png" alt="">
		</div>
		<div class="normal4">
			<img src="${cxt}/img/polygonnormal4.png" alt="">
		</div>
		<div class="normal5">
			<img src="${cxt}/img/polygonnormal5.png" alt="">
		</div>
	</div>
</div>
<div class="healthguide">
	<div class="title">健康指南</div>
	<div class="stitleline"></div>
	<div class="guidefloatbox">
		<div class="magazine">
			<div class="guidetitle">健康指南杂志</div>
			<div class="guidemain">
				<img src="${cxt}/img/magazine.jpg" alt="">
				《健康指南》是中国创办最早、发行量最大的中老年健康长寿指导期刊。本刊是中国领先的健康传媒，创办于1988年，至今已有25年的运营历史。每期发行量为25万本，平均传阅率为5人。影响着超过130万人的健康理念与保健行为。发行量连续5年保持了年均15%的增长，在全球传统媒体集体下滑的今天，本刊在中国正在创造奇迹。本刊过硬的品质为其在读者之中赢得了良好的口碑，绝大部分读者一旦订阅，即终生订阅，而且乐于向自己的......
			</div>
			<div class="moreinfo">
				<a href="javascript:;">查看详情>></a>
			</div>
		</div>
		<div class="class">
			<div class="guidetitle">健康大讲堂</div>
			<div class="classmainbox">
				<div class="classmain">
					<a href="javascript:;"> <img src="${cxt}/img/classclip.jpg"
						alt="">
					</a>
				</div>
				<div class="clipbox">
					<a href="javascript:;"> <img src="${cxt}/img/classclip1.jpg"
						class="mr10" alt="">
					</a> <a href="javascript:;"> <img src="${cxt}/img/classclip2.jpg"
						class="mr10" alt="">
					</a> <a href="javascript:;"> <img src="${cxt}/img/classclip3.jpg"
						alt="">
					</a>
				</div>
			</div>
			<div class="moreinfo">
				<a href="javascript:;">查看详情>></a>
			</div>
		</div>
		<div class="article">
			<div class="guidetitle">健康指南文章</div>
			<ul class="articlemain">
				<li>
					<div class="showbox" style="display: none;">
						<div class="iconbox">
							<div class="icon"></div>
						</div>
						<div class="articletitle">浅谈经济管理科聘用人员管理</div>
						<div class="time">12-06</div>
					</div>
					<div class="hidebox">
						<div class="hidearticlepic">
							<img src="${cxt}/img/articlepic.jpg" alt="">
						</div>
						<div class="hidetxtbox">
							<div class="hidetitle">健康的守护专家</div>
							<div class="note">弘扬中医中药在预防医学上特殊作用生活化调理亚健康，实现"治未病"国家卫生计生委主管的.....</div>
						</div>
					</div>
				</li>
				<li>
					<div class="showbox">
						<div class="iconbox">
							<div class="icon"></div>
						</div>
						<div class="articletitle">浅谈经济管理科聘用人员管理</div>
						<div class="time">12-06</div>
					</div>
					<div class="hidebox" style="display: none;">
						<div class="hidearticlepic">
							<img src="${cxt}/img/articlepic.jpg" alt="">
						</div>
						<div class="hidetxtbox">
							<div class="hidetitle">健康的守护专家</div>
							<div class="note">弘扬中医中药在预防医学上特殊作用生活化调理亚健康，实现"治未病"国家卫生计生委主管的.....</div>
						</div>
					</div>
				</li>
				<li>
					<div class="showbox">
						<div class="iconbox">
							<div class="icon"></div>
						</div>
						<div class="articletitle">浅谈经济管理科聘用人员管理</div>
						<div class="time">12-06</div>
					</div>
					<div class="hidebox" style="display: none;">
						<div class="hidearticlepic">
							<img src="${cxt}/img/articlepic.jpg" alt="">
						</div>
						<div class="hidetxtbox">
							<div class="hidetitle">健康的守护专家</div>
							<div class="note">弘扬中医中药在预防医学上特殊作用生活化调理亚健康，实现"治未病"国家卫生计生委主管的.....</div>
						</div>
					</div>
				</li>
				<li>
					<div class="showbox">
						<div class="iconbox">
							<div class="icon"></div>
						</div>
						<div class="articletitle">浅谈经济管理科聘用人员管理</div>
						<div class="time">12-06</div>
					</div>
					<div class="hidebox" style="display: none;">
						<div class="hidearticlepic">
							<img src="${cxt}/img/articlepic.jpg" alt="">
						</div>
						<div class="hidetxtbox">
							<div class="hidetitle">健康的守护专家</div>
							<div class="note">弘扬中医中药在预防医学上特殊作用生活化调理亚健康，实现"治未病"国家卫生计生委主管的.....</div>
						</div>
					</div>
				</li>
				<li>
					<div class="showbox">
						<div class="iconbox">
							<div class="icon"></div>
						</div>
						<div class="articletitle">浅谈经济管理科聘用人员管理</div>
						<div class="time">12-06</div>
					</div>
					<div class="hidebox" style="display: none;">
						<div class="hidearticlepic">
							<img src="${cxt}/img/articlepic.jpg" alt="">
						</div>
						<div class="hidetxtbox">
							<div class="hidetitle">健康的守护专家</div>
							<div class="note">弘扬中医中药在预防医学上特殊作用生活化调理亚健康，实现"治未病"国家卫生计生委主管的.....</div>
						</div>
					</div>
				</li>
				<li>
					<div class="showbox">
						<div class="iconbox">
							<div class="icon"></div>
						</div>
						<div class="articletitle">浅谈经济管理科聘用人员管理</div>
						<div class="time">12-06</div>
					</div>
					<div class="hidebox" style="display: none;">
						<div class="hidearticlepic">
							<img src="${cxt}/img/articlepic.jpg" alt="">
						</div>
						<div class="hidetxtbox">
							<div class="hidetitle">健康的守护专家</div>
							<div class="note">弘扬中医中药在预防医学上特殊作用生活化调理亚健康，实现"治未病"国家卫生计生委主管的.....</div>
						</div>
					</div>
				</li>
				<li>
					<div class="showbox">
						<div class="iconbox">
							<div class="icon"></div>
						</div>
						<div class="articletitle">浅谈经济管理科聘用人员管理</div>
						<div class="time">12-06</div>
					</div>
					<div class="hidebox" style="display: none;">
						<div class="hidearticlepic">
							<img src="${cxt}/img/articlepic.jpg" alt="">
						</div>
						<div class="hidetxtbox">
							<div class="hidetitle">健康的守护专家</div>
							<div class="note">弘扬中医中药在预防医学上特殊作用生活化调理亚健康，实现"治未病"国家卫生计生委主管的.....</div>
						</div>
					</div>
				</li>
				<li>
					<div class="showbox">
						<div class="iconbox">
							<div class="icon"></div>
						</div>
						<div class="articletitle">浅谈经济管理科聘用人员管理</div>
						<div class="time">12-06</div>
					</div>
					<div class="hidebox" style="display: none;">
						<div class="hidearticlepic">
							<img src="${cxt}/img/articlepic.jpg" alt="">
						</div>
						<div class="hidetxtbox">
							<div class="hidetitle">健康的守护专家</div>
							<div class="note">弘扬中医中药在预防医学上特殊作用生活化调理亚健康，实现"治未病"国家卫生计生委主管的.....</div>
						</div>
					</div>
				</li>
			</ul>
			<div class="moreinfo">
				<a href="javascript:;">查看详情>></a>
			</div>
		</div>
	</div>
</div>
<%@include file="/common/bottom.jsp"%>
<script src="${cxt}/js/slider.js"></script>