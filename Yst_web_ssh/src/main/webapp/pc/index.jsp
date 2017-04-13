<%@ page pageEncoding="UTF-8" %>
<link rel="stylesheet" href="${baseurl }pc/css/jianqiaoindex.css">
<script src="${baseurl }pc/js/jianqiaoindex.js"></script>
<div id="banner_tabs" class="flexslider">
	<ul class="slides">
		<li>
			<a title="" href="#">
				<img width="1920" height="480" alt="" style="background: url(pc/img/banner1.jpg) no-repeat center;" src="pc/img/alpha.png">
			</a>
		</li>
		<li>
			<a title="" href="#">
				<img width=	"1920" height="480" alt="" style="background: url(pc/img/banner2.jpg) no-repeat center;" src="pc/img/alpha.png">
			</a>
		</li>
		<li>
			<a title="" href="#">
				<img width="1920" height="480" alt="" style="background: url(pc/img/banner3.jpg) no-repeat center;" src="pc/img/alpha.png">
			</a>
		</li>
		
	</ul>
	<ul class="flex-direction-nav fangxiang">
		<li><a class="flex-prev" href="javascript:;">&nbsp;</a></li>
		<li><a class="flex-next" href="javascript:;">&nbsp;</a></li>
	</ul>
	<ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
		<li class="active"><a>1</a></li>
		<li><a>2</a></li>
		<li><a>2</a></li>
	</ol>
</div>
<script type="text/javascript">
$(function() {
	var bannerSlider = new Slider($('#banner_tabs'), {
		time: 5000,
		delay: 400,
		event: 'hover',
		auto: true,
		mode: 'fade',
		controller: $('#bannerCtrl'),
		activeControllerCls: 'active'
	});
	$('#banner_tabs .flex-prev').click(function() {
		bannerSlider.prev();
	});
	$('#banner_tabs .flex-next').click(function() {
		bannerSlider.next();
	});
    $('#banner_tabs').mousemove(function() {
		bannerSlider.tingzhi();
    });
    $('#banner_tabs').mouseout(function() {
		bannerSlider.kaishi();
    });
});
</script>







	<div class="jianqiaonew">
		<h2 class="title">健桥资讯</h2>
		<div class="titleline"></div>
		<div class="txt">北京上善健桥健康管理中心战略合作伙伴、中国老年保健医学研究会、《健康指南》杂志社、国家中医药管理局
			<div class="next"></div>
			<div class="pre"></div>
		</div>
		<div class="newbox flexslider">
			<ul class="swiper-wrapper slides">
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业一周投资并购事件</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业一周投资并购事件</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				
				
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业一周投资并购事件</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业一周投资并购事件</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
				<li class="swiper-slide">
					<div class="picbox">
						<a href="javascript:;">
							<img src="pc/img/newpic.jpg" alt="">
						</a>
					</div>
					<h3 class="newtitle">医药行业ewrfewqarf</h3>
					<p class="detailtxt">国内方面： 掌上药店、医学界、易康就医、美柚获得融资，绿叶集团、仁和药业、亿帆鑫富均有大手笔并购</p>
				</li>
			</ul>
		</div>
		<script>
            var list=parseInt($('.swiper-slide').size());
            var b=1;
            var l=0;
            $('.next').click(function () {
                    var i=b*4;
                    if(list == i){
                        return false;
                    }
                    b++;
                    l++;
                $('.slides').stop().animate({'left' : l*-1100+'px'}, 1000);
	       });
            
            $('.pre').click(function () {
                    var i=b*4;
                    if(l == 0){
                        return false;
                    }
                    b--;
                    l--;
                $('.slides').stop().animate({'left' : l*-1100+'px'}, 1000);
	       });
          
        </script>
		<div class="morenew">
			<a href="javascript:;">更多新闻咨询</a>
		</div>
	</div>
	<div class="jianqiaoservice">
		<h2 class="title">疑难杂症导医</h2>
		<div class="stitleline"></div>
		<div class="more"><a href="javascript:;">更多+</a></div>
		<ul class="diseaselistbox">
			<li>
				<div class="diseasepic">
					<a href="javascript:;">
						<img src="pc/img/diceaseguide.jpg" alt="">
					</a>
				</div>
				<div class="diseaselisttitle">
					【肩周炎】中医治疗糖尿病
				</div>
				<div class="diseasedetail">
					症状治疗介绍，症状治疗介绍，症状治疗介绍，症状治 疗介绍，症状治...
				</div>
				<div class="diseasebtn">
					<div class="dbtnleft">
						<div class="btniconbox">
							<div class="icon"></div>
						</div>
						湿疹
					</div>
					<div class="dbtnright">
						<a href="javascript:;">
							<div class="rbtnicon"></div>
						</a>
					</div>
				</div>
			</li>
			<li>
				<div class="diseasepic">
					<a href="javascript:;">
						<img src="pc/img/diceaseguide.jpg" alt="">
					</a>
				</div>
				<div class="diseaselisttitle">
					【肩周炎】中医治疗糖尿病
				</div>
				<div class="diseasedetail">
					症状治疗介绍，症状治疗介绍，症状治疗介绍，症状治 疗介绍，症状治...
				</div>
				<div class="diseasebtn">
					<div class="dbtnleft">
						<div class="btniconbox">
							<div class="icon"></div>
						</div>
						湿疹
					</div>
					<div class="dbtnright">
						<a href="javascript:;">
							<div class="rbtnicon"></div>
						</a>
					</div>
				</div>
			</li>
			<li class="lastdis">
				<div class="diseasepic">
					<a href="javascript:;">
						<img src="pc/img/diceaseguide.jpg" alt="">
					</a>
				</div>
				<div class="diseaselisttitle">
					【肩周炎】中医治疗糖尿病
				</div>
				<div class="diseasedetail">
					症状治疗介绍，症状治疗介绍，症状治疗介绍，症状治 疗介绍，症状治...
				</div>
				<div class="diseasebtn">
					<div class="dbtnleft">
						<div class="btniconbox">
							<div class="icon"></div>
						</div>
						湿疹
					</div>
					<div class="dbtnright">
						<a href="javascript:;">
							<div class="rbtnicon"></div>
						</a>
					</div>
				</div>
			</li>
			<li>
				<div class="diseasepic">
					<a href="javascript:;">
						<img src="pc/img/diceaseguide.jpg" alt="">
					</a>
				</div>
				<div class="diseaselisttitle">
					【肩周炎】中医治疗糖尿病
				</div>
				<div class="diseasedetail">
					症状治疗介绍，症状治疗介绍，症状治疗介绍，症状治 疗介绍，症状治...
				</div>
				<div class="diseasebtn">
					<div class="dbtnleft">
						<div class="btniconbox">
							<div class="icon"></div>
						</div>
						湿疹
					</div>
					<div class="dbtnright">
						<a href="javascript:;">
							<div class="rbtnicon"></div>
						</a>
					</div>
				</div>
			</li>
			<li>
				<div class="diseasepic">
					<a href="javascript:;">
						<img src="pc/img/diceaseguide.jpg" alt="">
					</a>
				</div>
				<div class="diseaselisttitle">
					【肩周炎】中医治疗糖尿病
				</div>
				<div class="diseasedetail">
					症状治疗介绍，症状治疗介绍，症状治疗介绍，症状治 疗介绍，症状治...
				</div>
				<div class="diseasebtn">
					<div class="dbtnleft">
						<div class="btniconbox">
							<div class="icon"></div>
						</div>
						湿疹
					</div>
					<div class="dbtnright">
						<a href="javascript:;">
							<div class="rbtnicon"></div>
						</a>
					</div>
				</div>
			</li>
			<li class="lastdis">
				<div class="diseasepic">
					<a href="javascript:;">
						<img src="pc/img/diceaseguide.jpg" alt="">
					</a>
				</div>
				<div class="diseaselisttitle">
					【肩周炎】中医治疗糖尿病
				</div>
				<div class="diseasedetail">
					症状治疗介绍，症状治疗介绍，症状治疗介绍，症状治 疗介绍，症状治...
				</div>
				<div class="diseasebtn">
					<div class="dbtnleft">
						<div class="btniconbox">
							<div class="icon"></div>
						</div>
						湿疹
					</div>
					<div class="dbtnright">
						<a href="javascript:;">
							<div class="rbtnicon"></div>
						</a>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<div class="doctor">
		<h2 class="title">名医高手</h2>
		<div class="stitleline"></div>

		<div class="doctorlistbox">
			<ul class="doctorlist">
				<li>
					<div class="disease">肩周炎</div>
					<div class="doctormain">
						<div class="doctorintro">
							<div class="doctorpic">
								<img src="pc/img/doctorpic.jpg" alt="">
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
								<img src="pc/img/doctorpic.jpg" alt="">
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
								<img src="pc/img/doctorpic.jpg" alt="">
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
	</div>
	<script>
        $(function(){
             $('.placeholder').click(function(){
                 $(this).css('display','none');
                 $(this).siblings('input').focus();
             });
             $('input').focus(function(){
                 $(this).siblings('.placeholder').css('display','none');
             });
             $('input').blur(function(){
                 if($(this).val()==''){
                      $(this).siblings('.placeholder').css('display','block');
                 }
             });
        });
    </script>
	
	<div class="seekdoc">
		<h2 class="title">患者求医直播</h2>
		<div class="stitleline"></div>
		<div class="diseasebtn">
			<a href="javascript:;" class="checked">糖尿病</a>
			<a href="javascript:;">脑瘫</a>
			<a href="javascript:;">肺炎</a>
			<a href="javascript:;">肠炎</a>
			<a href="javascript:;">呼吸道传染病</a>
			<a href="javascript:;">心绞痛</a>
			<a href="javascript:;">白血病</a>
		</div>
		<div class="seekdocbox">
			<ul class="seekdoclist">
				<li>
					<div class="seekdocimg">
						<a href="javascript:;">
							<img src="pc/img/seekdoc.jpg" alt="">
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
						<a href="javascript:;">
							<img src="pc/img/seekdoc.jpg" alt="">
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
						<a href="javascript:;">
							<img src="pc/img/seekdoc.jpg" alt="">
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
						<a href="javascript:;">
							<img src="pc/img/seekdoc.jpg" alt="">
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
						<a href="javascript:;">
							<img src="pc/img/seekdoc.jpg" alt="">
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
						<a href="javascript:;">
							<img src="pc/img/seekdoc.jpg" alt="">
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
						<a href="javascript:;">
							<img src="pc/img/seekdoc.jpg" alt="">
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
						<a href="javascript:;">
							<img src="pc/img/seekdoc.jpg" alt="">
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
		<h2 class="title">健康分享</h2>
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
							<img src="pc/img/webber.jpg" alt="">
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
							<img src="pc/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic2">
							<img src="pc/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic3">
							<img src="pc/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic4">
							<img src="pc/img/webberimg1.jpg" alt="">
						</div>
					</div>
					<div class="webbernote">
						刑小美的七周美白日记第一篇 
					</div>
				</div>
				<div class="webberlist">
					<div class="webbertitle">
						<div class="webberimg">
							<img src="pc/img/webber.jpg" alt="">
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
							<img src="pc/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic2">
							<img src="pc/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic3">
							<img src="pc/img/webberimg1.jpg" alt="">
						</div>
						<div class="webberpic webberpic4">
							<img src="pc/img/webberimg1.jpg" alt="">
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
							<img src="pc/img/recommendimg.jpg" alt="">
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
							<img src="pc/img/recommendimg.jpg" alt="">
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
							<img src="pc/img/recommendimg.jpg" alt="">
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
							<img src="pc/img/recommendimg.jpg" alt="">
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
		</div>
	</div>
	<div class="bighealth">
		<div class="title">大健康+互联网</div>
		<div class="stitleline1"></div>
		<div class="polygonbox">
			<div class="normal1">
				<img src="pc/img/polygonnormal1.png" alt="">
			</div>
			<div class="normal2">
				<img src="pc/img/polygonnormal2.png" alt="">
			</div>
			<div class="normal3">
				<img src="pc/img/polygonnormal3.png" alt="">
			</div>
			<div class="normal4">
				<img src="pc/img/polygonnormal4.png" alt="">
			</div>
			<div class="normal5">
				<img src="pc/img/polygonnormal5.png" alt="">
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
					<img src="pc/img/magazine.jpg" alt="">
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
						 <a href="javascript:;">
							<img src="pc/img/classclip.jpg" alt="">
						 </a>
					</div>
					<div class="clipbox">
						<a href="javascript:;">
							<img src="pc/img/classclip1.jpg" class="mr10" alt="">
						</a>
						<a href="javascript:;">
							<img src="pc/img/classclip2.jpg" class="mr10" alt="">
						</a>
						<a href="javascript:;">
							<img src="pc/img/classclip3.jpg" alt="">
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
								<img src="pc/img/articlepic.jpg" alt="">
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
								<img src="pc/img/articlepic.jpg" alt="">
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
								<img src="pc/img/articlepic.jpg" alt="">
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
								<img src="pc/img/articlepic.jpg" alt="">
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
								<img src="pc/img/articlepic.jpg" alt="">
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
								<img src="pc/img/articlepic.jpg" alt="">
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
								<img src="pc/img/articlepic.jpg" alt="">
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
								<img src="pc/img/articlepic.jpg" alt="">
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