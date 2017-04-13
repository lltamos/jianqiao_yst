<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${cxt }/css/base.css" />
<link rel="stylesheet" href="${cxt }/css/yisheng_3.css" />
<%-- <script src="${cxt }/js/jedate/jquery.jedate.js"></script> --%>
<link rel="stylesheet" href="${cxt }/js/jedate/skin/jedate.css">
<script>
$(function() {
	$("#date01").jeDate({
		isinitVal : false,
		festival : true,
		ishmsVal : false,
		minDate : '2016-06-16 23:59:59',
		maxDate : $.nowDate(0),
		format : "hh:mm",
		zIndex : 3000,
	});
	$("#date02").jeDate({
		isinitVal : false,
		festival : true,
		ishmsVal : false,
		minDate : '2016-06-16 23:59:59',
		maxDate : $.nowDate(0),
		format : "hh:mm",
		zIndex : 3000,
	});
});
</script>
</head>
<body>
	<%@include file="/common/topindex.jsp"%>
	<%@include file="/common/navigate.jsp"%>
	<div class="bg_gray">
		<div class="content">
			<%@include file="/common/mycenterleft_docter.jsp"%>
			<%@include file="/common/mycentertop.jsp"%>
			<div class="item">
				<div class="doctor">
					<img src="${cxt }/img/ZaiXianzixun.jpg" alt="" />
					
					<div class="doc_detailed">
						<ul>
							<li class="inp_1">
								<span class="fon_1">在线咨询</span>
								<b class="fon_5">￥<span><input id="price1" name="price1" style="width: 30px" value="${price1/100 }"></span>/<i>七天</i></b>
								<input type="checkbox" name="check-3" onclick="closeDoctor();" class="lcs_check lcs_tt1" 
								<c:if test="${onOff==1 }"></c:if>
								<c:if test="${onOff==0 }">checked="checked"</c:if>
								 autocomplete="off" />
							</li>
							<li>
								<p>可咨询时间:</p> 
								<span class="fon_2"><i>周一</i><input type="checkbox" id="mon" name="mon"   <c:if test="${doctorServiceTime.monday==0 }" >checked="checked" </c:if> value="0"></span> 
								<span class="fon_2"><i>周二</i><input type="checkbox" id="tue" name="tue"  <c:if test="${doctorServiceTime.tuesday==0 }" >checked="checked"</c:if> value="0"></span> 
								<span class="fon_2"><i>周三</i><input type="checkbox" id="wed" name="wed"  <c:if test="${doctorServiceTime.wednesday==0 }" >checked="checked" </c:if> value="0"></span> 
								<span class="fon_2"><i>周四</i><input type="checkbox" id="thu" name="thu"  <c:if test="${doctorServiceTime.thursday==0 }" >checked="checked" </c:if> value="0"></span><br /> 
								<span class="fon_2" style="margin-left: 87px;"><i>周五</i><input type="checkbox" id="fri" name="fri" <c:if test="${doctorServiceTime.friday==0 }" >checked="checked" </c:if> value="0"></span>
								<span class="fon_2"><i>周六</i><input type="checkbox" id="sat" name="sat" <c:if test="${doctorServiceTime.saturday==0 }" >checked="checked"</c:if>value="0"></span>
								<span class="fon_2"><i>周日</i><input type="checkbox" id="sun" name="sun" <c:if test="${doctorServiceTime.sunday==0 }" >checked="checked" </c:if> value="0"></span>
							</li>
							<li>
								<p>在线时间段:</p> 
								<input class="ShiJian" id="date01" type="text" placeholder="开始时间" value="${doctorServiceTime.can_consult_starttime }" readonly> - 
								<input class="ShiJian" id="date02" type="text" placeholder="结束时间" value="${doctorServiceTime.can_consult_endtime }" readonly>
							</li>
							
						</ul>
					</div>
				</div>
			</div>
			<input type="button" class="TiJiao_tu" value="保存" onclick="submit()">
			<!-- <a class="TiJiao_tu" href="javascript:;" onclick="closeDoctor()" style="text-align: center;">关闭</a> -->
		</div>
	</div>
	</div>
	<script>
		function open_1() {
			var s = document.getElementById("test");
			s.style.display = "block";
			var d = document.getElementById("phone_num");
			d.style.border = '1px dashed #9C9AA5';
			var a = document.getElementById("shezhi");
			a.style.visibility = "hidden";
		}
		function submit() {
			var price1 = $("input[id=price1]").val()*100;
			if (price1 == null || price1 == "undefined") {
				alert("您输入的价格为空");
				return false;
				
			}
			
			var mon = $("input[id=mon]:checked").val();
			if (mon == null || mon == "undefined") {
				mon = 1;
			}
			var tue = $("input[id=tue]:checked").val();
			if (tue == null || tue == "undefined") {
				tue = 1;
			}
			var wed = $("input[id=wed]:checked").val();
			if (wed == null || wed == "undefined") {
				wed = 1;
			}
			var thu = $("input[id=thu]:checked").val();
			if (thu == null || thu == "undefined") {
				thu = 1;
			}
			var fri = $("input[id=fri]:checked").val();
			if (fri == null || fri == "undefined") {
				fri = 1;
			}
			var sat = $("input[id=sat]:checked").val();
			if (sat == null || sat == "undefined") {
				sat = 1;
			}
			var sun = $("input[id=sun]:checked").val();
			if (sun == null || sun == "undefined") {
				sun = 1;
			}
			var date01 = $("input[id=date01]").val();
			if (date01 == null || date01 == "undefined") {
				date01 = "";
			}

			
			var date02 = $("input[id=date02]").val();
			if (date02 == null || date02 == "undefined") {
				date02 = "";
			}
			$.ajax({
				type:"POST",
				url :"${cxt}/pc/after/doctorservice/tosettings",
				data:{
					"price1":price1,
					"mon":mon,
					"tue":tue,
					"wed" :wed,
					"thu" : thu,
					"fri" : fri,
					"sat" : sat,
					"sun" : sun,
					"date01" : date01,
					"date02" : date02
				},
				success:function(result) {
					var sc = eval('(' + result + ')')
					alert(sc.msg);
				}

			});
		}
		
		function closeDoctor() {
			$.ajax({
				type : "POST",
				url : "${cxt}/pc/after/doctorservice/close",
				success : function(result) {
					var sc = eval('(' + result + ')');
					alert(sc.content);
				}

			});
		};
		(function($){
		    if(typeof($.fn.lc_switch) != 'undefined') {return false;} // prevent dmultiple scripts inits

		    $.fn.lc_switch = function(on_text, off_text) {

		        // destruct
		        $.fn.lcs_destroy = function() {

		            $(this).each(function() {
		                var $wrap = $(this).parents('.lcs_wrap');

		                $wrap.children().not('input').remove();
		                $(this).unwrap();
		            });

		            return true;
		        };


		        // set to ON
		        $.fn.lcs_on = function() {

		            $(this).each(function() {
		                var $wrap = $(this).parents('.lcs_wrap');
		                var $input = $wrap.find('input');

		                if(typeof($.fn.prop) == 'function') {
		                    $wrap.find('.inp_1 input').prop('checked', true);
		                } else {
		                    $wrap.find('.inp_1 input').attr('checked', true);
		                }

		                $wrap.find('.inp_1 input').trigger('lcs-on');
		                $wrap.find('.inp_1 input').trigger('lcs-statuschange');
		                $wrap.find('.lcs_switch').removeClass('lcs_off').addClass('lcs_on');
		                // if radio - disable other ones
		                if( $wrap.find('.lcs_switch').hasClass('lcs_radio_switch') ) {
		                    var f_name = $input.attr('name');
		                    $wrap.parents('form').find('input[name='+f_name+']').not($input).lcs_off();
		                }
		                closeDoctor();
		            });

		            return true;
		        };


		        // set to OFF
		        $.fn.lcs_off = function() {

		            $(this).each(function() {
		                var $wrap = $(this).parents('.lcs_wrap');

		                if(typeof($.fn.prop) == 'function') {
		                    $wrap.find('.inp_1 input').prop('checked', false);
		                } else {
		                    $wrap.find('.inp_1 input').attr('checked', false);
		                }

		                $wrap.find('.inp_1 input').trigger('lcs-off');
		                $wrap.find('.inp_1 input').trigger('lcs-statuschange');
		                $wrap.find('.lcs_switch').removeClass('lcs_on').addClass('lcs_off');
		            });
		            closeDoctor();
		            return true;
		        };


		        // construct
		        return this.each(function(){

		            // check against double init
		            if( !$(this).parent().hasClass('lcs_wrap') ) {

		                // default texts
		                var ckd_on_txt = (typeof(on_text) == 'undefined') ? '开' : on_text;
		                var ckd_off_txt = (typeof(off_text) == 'undefined') ? '关' : off_text;

		                // labels structure
		                var on_label = (ckd_on_txt) ? '<div class="lcs_label lcs_label_on">'+ ckd_on_txt +'</div>' : '';
		                var off_label = (ckd_off_txt) ? '<div class="lcs_label lcs_label_off">'+ ckd_off_txt +'</div>' : '';


		                // default states
		                var disabled 	= ($(this).is(':disabled')) ? true: false;
		                var active 		= ($(this).is(':checked')) ? true : false;

		                var status_classes = '';
		                status_classes += (active) ? ' lcs_on' : ' lcs_off';
		                if(disabled) {status_classes += ' lcs_disabled';}


		                // wrap and append
		                var structure =
		                        '<div class="lcs_switch '+status_classes+'">' +
		                        '<div class="lcs_cursor"></div>' +
		                        on_label + off_label +
		                        '</div>';

		                if( $(this).is(':input') && ($(this).attr('type') == 'checkbox' || $(this).attr('type') == 'radio') ) {

		                    $(this).wrap('<div class="lcs_wrap"></div>');
		                    $(this).parent().append(structure);

		                    $(this).parent().find('.lcs_switch').addClass('lcs_'+ $(this).attr('type') +'_switch');
		                }
		            }
		            
		        });
		    };
		    // handlers
		    $(document).ready(function() {

		        // on click
		        $(document).delegate('.lcs_switch:not(.lcs_disabled)', 'click tap', function(e) {

		            if( $(this).hasClass('lcs_on') ) {
		                if( !$(this).hasClass('lcs_radio_switch') ) { // not for radio
		                    $(this).lcs_off();
		                }
		            } else {
		                $(this).lcs_on();
		            }
		        });
		        // on checkbox status change
		        $(document).delegate('.lcs_wrap input', 'change', function() {

		            if( $(this).is(':checked') ) {
		                $(this).lcs_on();
		            } else {
		                $(this).lcs_off();
		            }
		        });

		    });

		})(jQuery);
		$(document).ready(function(e) {
		    $('.inp_1 input').lc_switch();
		    // triggered each time a field changes status
		    $('body').delegate('.lcs_check', 'lcs-statuschange', function() {
		        var status = ($(this).is(':checked')) ? 'checked' : 'unchecked';
		        console.log('field changed status: '+ status );
		    });

		    // triggered each time a field is checked
		    $('body').delegate('.lcs_check', 'lcs-on', function() {
		        console.log('field is checked');
		    });


		    // triggered each time a is unchecked
		    $('body').delegate('.lcs_check', 'lcs-off', function() {
		        console.log('field is unchecked');
		    });
		});
	</script>
</body>
</html>