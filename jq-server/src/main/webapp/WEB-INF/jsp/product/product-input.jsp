<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<link rel="stylesheet" type="text/css" href="${cxt }/ueditor/third-party/webuploader/webuploader.css">
<script type="text/javascript" src="${cxt }/ueditor/third-party/webuploader/webuploader.js"></script>
</head>
<html>
<script type="text/javascript">
	function check() {
		var merchant = $("#merchantId");
		if (merchant.val() == "") {
			merchant.tips({
				side : 2,
				msg : '请选择所属总院',
				bg : '#AE81FF',
				time : 1
			});

			merchant.focus();
			return false;
		} else {
			merchant.val(jQuery.trim(merchant.val()));
		}
		var doctor = $("input[name='doctorId']");
		var doctorSel=$("#doctorSel");
		if(doctor.length==0){
			doctorSel.tips({
				side : 2,
				msg : '请选择医生',
				bg : '#AE81FF',
				time : 1
			});
			doctorSel.focus();
			return false;
		}
		var customerServiceId = $("#customerServiceId");
		if (customerServiceId.val() == "") {
			customerServiceId.tips({
				side : 2,
				msg : '请输入客服电话',
				bg : '#AE81FF',
				time : 1
			});
			customerServiceId.focus();
			return false;
		} else {
			customerServiceId.val(jQuery.trim(customerServiceId.val()));
		}
		var checkMobile=new RegExp(/^1[3|4|5|7|8][0-9]\d{8}$/);
		var checkzuoJ = new RegExp(/^[0-9-]{10,12}$/);
		if(checkzuoJ.test(customerServiceId.val())){
			
		}else if(!checkMobile.test(customerServiceId.val())){
			customerServiceId.tips({
				side : 2,
				msg : '客服电话格式不正确',
				bg : '#AE81FF',
				time : 1
			});
			customerServiceId.focus();
			return false;
	  	}else {
			customerServiceId.val(jQuery.trim(customerServiceId.val()));
		}	
		var productType = $("#serviceTypeId");
		if (productType.val() == "") {
			productType.tips({
				side : 2,
				msg : '请选择项目类别',
				bg : '#AE81FF',
				time : 1
			});

			productType.focus();
			return false;
		} else {
			productType.val(jQuery.trim(productType.val()));
		}
		var name = $("#name");
		if (name.val() == "") {
			name.tips({
				side : 2,
				msg : '名称不得为空',
				bg : '#AE81FF',
				time : 1
			});

			name.focus();
			return false;
		} else {
			name.val(jQuery.trim(name.val()));
		}
		var id = $("#id");
		if(id.val()==""){
			var image = $("#panel").attr('src');
			var img =$("#panel");
			if (!image) {
				img.tips({
					side : 2,
					msg : '请选择上传图片',
					bg : '#AE81FF',
					time : 3
				});
				img.focus();
				return false;
			} 
		}
		var customerPhone = $("#customerPhone");
		if (customerPhone.val() != ""){
			if(isNaN(customerPhone.val()) || customerPhone.val().length!=11 ) {
				customerPhone.tips({
					side : 2,
					msg : '推荐人手机号输入错误',
					bg : '#AE81FF',
					time : 1
				});
				customerPhone.focus();
				return false;
			}else{
				customerPhone.val(jQuery.trim(customerPhone.val()));
			}
		}
		var des1 = $("#des1");
		if (des1.val() == "") {
			des1.tips({
				side : 2,
				msg : '内容介绍不得为空',
				bg : '#AE81FF',
				time : 1
			});
			des1.focus();
			return false;
		} else {
			des1.val(jQuery.trim(des1.val()));
		}
		var marketPrice = $("#marketPrice");

		if (marketPrice.val() == "") {
			marketPrice.tips({
				side : 2,
				msg : '市场价不得为空',
				bg : '#AE81FF',
				time : 1
			});
			marketPrice.focus();
			return false;
		}
		if (isNaN(marketPrice.val())) {
			marketPrice.tips({
				side : 2,
				msg : '请输入数字',
				bg : '#AE81FF',
				time : 1
			});
			marketPrice.focus();
			return false;
		}
		if(parseFloat(marketPrice.val())<=0){
			marketPrice.tips({
				side : 2,
				msg : '市场价不能低于0',
				bg : '#AE81FF',
				time : 1
			});
			marketPrice.focus();
			return false;
		}
		marketPrice.val(jQuery.trim(marketPrice.val()));
		var price = $("#totalPrice");
		if (price.val() == "") {
			price.tips({
				side : 2,
				msg : '总价不得为空',
				bg : '#AE81FF',
				time : 1
			});
			price.focus();
			return false;
		}
		if (isNaN(price.val())) {
			price.tips({
				side : 2,
				msg : '请输入数字',
				bg : '#AE81FF',
				time : 1
			});
			price.focus();
			return false;
		}
		if(parseFloat(price.val())<=0){
			price.tips({
				side : 2,
				msg : '总价不能低于0',
				bg : '#AE81FF',
				time : 1
			});
			price.focus();
			return false;
		}
		if(parseFloat(price.val())>parseFloat(marketPrice.val())){
			price.tips({
				side : 2,
				msg : '总价不能超出市场价',
				bg : '#AE81FF',
				time : 1
			});
			price.focus();
			return false;
		}
		price.val(jQuery.trim(price.val()));
		var depositePrice = $("#depositePrice");

		if (depositePrice.val() == "") {
			depositePrice.tips({
				side : 2,
				msg : '定金不得为空',
				bg : '#AE81FF',
				time : 1
			});
			depositePrice.focus();
			return false;
		} else {
			if (isNaN(depositePrice.val())) {
				depositePrice.tips({
					side : 2,
					msg : '请输入数字',
					bg : '#AE81FF',
					time : 1
				});
				depositePrice.focus();
				return false;
			} else {
				if(parseFloat(depositePrice.val())<=0){
					depositePrice.tips({
						side : 2,
						msg : '定金不能低于0',
						bg : '#AE81FF',
						time : 1
					});
					depositePrice.focus();
					return false;
				}
				if(parseFloat(depositePrice.val())>parseFloat(price.val())){
					depositePrice.tips({
						side : 2,
						msg : '定金不能超出总价',
						bg : '#AE81FF',
						time : 1
					});
					depositePrice.focus();
					return false;
				}else{
					depositePrice.val(jQuery.trim(depositePrice.val()));
				}
			}
		}
		
		var divide = $("#divide");
		var sum = 0;
		for (var i = 1; i <= divide.val(); i++) {
			var fee = $("input[name='fee" + i + "']" );
			if (fee.val() == "") {
				fee.tips({
					side : 2,
					msg : i + '期价格不得为空',
					bg : '#AE81FF',
					time : 1
				});
				fee.focus();
				return false;
			} else {
				if (isNaN(fee.val())) {
					fee.tips({
						side : 2,
						msg : '请输入数字',
						bg : '#AE81FF',
						time : 1
					});
					fee.focus();
					return false;
				} else {
					sum += Number(fee.val());
				}
			}
		}
		sum=sum.toFixed(2);
		if (sum != Number(price.val()).toFixed(2)) {
			divide.tips({
				side : 2,
				msg : '分期总和金额与总价不一致',
				bg : '#AE81FF',
				time : 1
			});
			divide.focus();
			return false;
		}
		var detail = $("#detailHtml");
		if (detail.val() == "") {
			$("#save").tips({
				side : 2,
				msg : '图文详情不得为空',
				bg : '#AE81FF',
				time : 1
			});
			return false;
		} else {
			detail.val(jQuery.trim(detail.val()));
		}
		/* var serviceProcess = $("#serviceProcess");
		if (serviceProcess.val() == "") {
			serviceProcess.tips({
				side : 2,
				msg : '服务流程不得为空',
				bg : '#AE81FF',
				time : 1
			});
			serviceProcess.focus();
			return false;
		} else {
			serviceProcess.val(jQuery.trim(serviceProcess.val()));
		}
		var appointment = $("#appointment");
		if (appointment.val() == "") {
			appointment.tips({
				side : 2,
				msg : '如何预约不得为空',
				bg : '#AE81FF',
				time : 1
			});
			appointment.focus();
			return false;
		} else {
			appointment.val(jQuery.trim(appointment.val()));
		}
		var refundProcess = $("#refundProcess");
		if (refundProcess.val() == "") {
			refundProcess.tips({
				side : 2,
				msg : '退款流程不得为空',
				bg : '#AE81FF',
				time : 1
			});
			refundProcess.focus();
			return false;
		} else {
			refundProcess.val(jQuery.trim(refundProcess.val()));
		}
		var special = $("#special");
		if (special.val() == "") {
			special.tips({
				side : 2,
				msg : '特别提示不得为空',
				bg : '#AE81FF',
				time : 1
			});
			special.focus();
			return false;
		} else {
			special.val(jQuery.trim(special.val()));
		} */
		price.val(Math.round(price.val() * 100));
		depositePrice.val(Math.round(depositePrice.val() * 100));
		marketPrice.val(Math.round(marketPrice.val() * 100));
		for (var i = 1; i <= divide.val(); i++) {
			var fee = $("input[name='fee" + i + "']");
			fee.val(Math.round(fee.val()*100));
		}
		return true;
	}
	//上传图片阿里云开始
	//图片上传demo
	var aids = "";
	jQuery(function() {
		var $ = jQuery,
		$list = $('#panel'),
		// 优化retina, 在retina下这个值是2
		ratio = window.devicePixelRatio || 1,
		// 缩略图大小
		thumbnailWidth = 100 * ratio, 
		thumbnailHeight = 100 * ratio,
		// Web Uploader实例
        uploader;
		//初始化Web Uploader
		uploader = WebUploader.create({
			// 选完文件后，是否自动上传。
			auto : true,
			// swf文件路径
			swf : '${ctx}/javascript/jqueryui/Uploader.swf',
			// 文件接收服务端。
			server : '${ctx}/attachment/newUpload-Attachment.do',
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick : '#filePicker',
			// 只允许选择图片文件。
			accept : {
				title : 'Images',
				extensions : 'gif,jpg,jpeg,bmp,png',
				mimeTypes : 'image/jpg,image/jpeg,image/png'
			}
		});
		// 当有文件添加进来的时候
		uploader.on('fileQueued', function(file) {
			// 创建缩略图
			uploader.makeThumb(file, function(error, src) {
				$("#panel").attr("src", src);
			}, thumbnailWidth, thumbnailHeight);
		});
		// 文件上传过程中创建进度条实时显示。
	   /*  uploader.on( 'uploadProgress', function( file, percentage ) {
	        var $li = $( '#'+file.id ),
	            $percent = $li.find('.progress span');
	        // 避免重复创建
	        if ( !$percent.length ) {
	            $percent = $('<p class="progress"><span></span></p>')
	                    .appendTo( $li )
	                    .find('span');
	        }

	        $percent.css( 'width', percentage * 100 + '%' );
	    }); */
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on('uploadSuccess', function(file, data, response) {
			var aid = data.id;
	    	aids = aid ; 
			$( '#'+file.id ).addClass('upload-state-done');
		});
		// 文件上传失败，现实上传出错。
		uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id ),
            $error = $li.find('div.error');
        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }
        $error.text('上传失败');
  		});
		/* // 完成上传完了，成功或者失败，先删除进度条。
		uploader.on('uploadComplete', function(file) {
			 $( '#'+file.id ).find('.progress').remove();
		});
 */
	});
	//上传图片阿里云结束
	// 保存用户信息
	function saveProduct() {
		var detail = $("#detailHtml");
		detail.val(document.getElementById("ckIframe").contentWindow.editor.getData());
		if (check()) {
			var form = $("#productInfoForm");
			var options = {
				url : '${ctx}/product/product-save', //提交给哪个执行
				type : 'POST',
				data : {
					"aids" : aids
				},
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var result = JSON.parse(result);
					var message = result.msg;
					var code = result.code;
					$("#save").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 2
					});
					if (code==1) {
						pageGo('${ctx }/product/product-list');
					}else{
						var divide = $("#divide");
						var price = $("#totalPrice");
						var depositePrice = $("#depositePrice");
						var marketPrice = $("#marketPrice");
						price.val((parseFloat(price.val())/100).toFixed(2));
						depositePrice.val((parseFloat(depositePrice.val())/100).toFixed(2));
						marketPrice.val((parseFloat(marketPrice.val())/100).toFixed(2));
						for (var i = 1; i <= divide.val(); i++) {
							var fee = $("input[name='fee" + i + "']");
							fee.val((parseFloat(fee.val())/100).toFixed(2));
						}
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function checkDivide() {
		var divide = $("#divide").val();
		for (var i = 1; i <= 5; i++) {
			if (i <= divide) {
				$("#fee" + i).show();
				var fee=$("input[name='fee" + i + "']").val();
				if(fee=="" || fee==0){
					$("input[name='fee" + i + "']").val("0.00");
				}
			} else {
				$("#fee" + i).hide();
				$("input[name='fee" + i + "']").val("0");
			}
		}
	}
	function clickDoctor(){
		var merchant = $("#merchantId");
		if (merchant.val() == "") {
			merchant.tips({
				side : 2,
				msg : '请选择所属总院',
				bg : '#AE81FF',
				time : 1
			});

			merchant.focus();
			return false;
		}
		var office = $("#officeId");
		if (office.val() == "") {
			office.tips({
				side : 2,
				msg : '请选择科室',
				bg : '#AE81FF',
				time : 1
			});

			office.focus();
			return false;
		}
	}
	function changeDoctor(e){
		var doctor = $("#doctorSel");
		if (doctor.val() == "") {
			return false;
		}
		var selected=$("#doctorSel option:selected");
		var val=selected.val();
		var doctorId=$("input[name='doctorId']");
		var flg = false;
		$.each(doctorId,function(){
			if($(this).val()==val){
				alert("已添加过此医生！")	
				flg=true;
				return false;
			}
		});
		if(flg){
			return false;
		}
		$(e).parent().parent().after("<div class='control-group'><label class='control-label'>医生信息：</label><div class='controls'><span>"+selected.html()+"&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='hidden' name='doctorId' id='doctorId' value='"+val+"'/><button class='btn' type='button' title='删除' onclick='delDoctor(this)'><i class='icon-trash'></i></button><br/></div></div>");
	}
	function delDoctor(e){
		$(e).parent().parent().remove();
	}
	function changeOffice(){
		var merchant = $("#merchantId");
		if (merchant.val() == "") {
			return false;
		}
		var office = $("#officeId");
		if (office.val() == "") {
			return false;
		}
		$.ajax({
			url : "${ctx}/doctor/doctor-search-select?search_EQ_merchantId=" + merchant.val()+"&search_EQ_officeId="+office.val(),
			type : 'POST',
			success : function(result) {
				var data = eval("(" + result + ")");
				if(data.length>0){
					$("#doctorSel").empty().append(
					"<option value=''>请选择</option>");
					$.each(data, function() {
						var phone =this.customerPhone==null?"暂无":this.customerPhone;
						$("#doctorSel").append(
								"<option value="+this.id+">姓名：" +this.name+"，电话："+phone 
										+ "</option>");
					});
				}
				
			}
		});
	}
	$(function(){
		checkDivide();
	});
	
	$(function(){
		$('.deletekong').click(function(){
			$(this).val('');
		});
	});
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a></li>
		<li>项目列表</li>
		<li class="active">项目信息：</li>
	</ul>
	<form class="form-horizontal" id="productInfoForm" method="post">
		<input type="hidden" name="id" id="id" value="${product.id }"/>
		<input type="hidden" name="detailHtml" id="detailHtml" value="${product.detailHtml }"/>
		<div class="control-group">
			<label class="control-label" for="merchantId">所属总院：</label>
			<div class="controls">
				<select name="merchantId" id="merchantId" onchange="changeOffice()">
					<option value="" >请选择</option>
					<c:forEach items="${merchants }" var="merchant" >
						<option value="${merchant.id }" <c:if test="${product.merchantId==merchant.id }">selected</c:if>>${merchant.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="doctorId">项目医生：</label>
			<div class="controls">
				<select name="officeId" id="officeId"  onchange="changeOffice()">
					<option value="">请选择</option>
					<c:forEach items="${offices }" var="office">
						<option value="${office.id }" >${office.name }</option>
					</c:forEach>
				</select>
				<select name="doctorSel" id="doctorSel" onchange="changeDoctor(this)" onclick="clickDoctor()">
						<option value="">请选择</option>
				</select>
			</div>
		</div>
		<c:forEach items="${doctors }" var="doc">
		<div class="control-group">
			<label class="control-label" for="customerServiceId">医生信息：</label>
			<div class="controls">
				<span>姓名：${doc.name }，电话：${doc.customerPhone }&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='hidden' name='doctorId' id='doctorId' value='${doc.id }'/><button class='btn' type='button' title='删除' onclick='delDoctor(this)'><i class='icon-trash'></i></button>
			</div>
		</div>
		</c:forEach>
		<div class="control-group">
			<label class="control-label" for="customerServiceId">客服电话：</label>
			<div class="controls">
				<input type="text" name="customerServiceId" id="customerServiceId" value="${product.customerServiceId }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="serviceTypeId">项目类别：</label>
			<div class="controls">
				<select name="serviceTypeId" id="serviceTypeId">
					<option value="">请选择</option>
					<c:forEach items="${productTypes }" var="type" >
						<option value="${type.id }" <c:if test="${product.serviceTypeId==type.id }">selected</c:if>>${type.serviceName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">项目名称：</label>
			<div class="controls">
				<input type="text" name="name" id="name" value="${product.name }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customerPhone">项目推荐人手机：</label>
			<div class="controls">
				<input type="text" name="customerPhone" id="customerPhone" value="${product.customerPhone }"/>
			</div>
		</div>
		<c:if test="${!empty images }">
		<div class="control-group">
			<label class="control-label" for="image">项目原主图：</label>
			<div class="controls">
				<c:forEach items="${images }" var="img">
				<img src="${img_service }/${img.address }" style="height: 200px; width: 200px"/><br/>
				</c:forEach>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label" for="image"><c:if test="${!empty product.id }">替换</c:if>项目主图：</label>
			<div class="controls">
				<img id='panel' width="200" height="200" />
				<div id="filePicker">添加</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="des1" >内容介绍：</label>
			<div class="controls">
				<textarea name="des1" id="des1" placeholder="限60字" cols="30" rows="4" maxlength="60">${product.des1 }</textarea>
			</div>
			<script type="text/javascript">  
				$('#des1').each(function() {  
					var ta = $(this), p = ta.parent(), ml = parseInt(ta.attr('maxlength')),  
					v = ta.val(), h = ta.attr('placeholder');  
					if (v == h) v = '';  
					if (h && ml) {  
					//var sp = '<span style="bottom: 10px;position: absolute;right: -10px;">'+v.length+'/'+ml+'</span>';  
					p.css({'position': 'relative'});  
					//ta.before(sp);  
					ta.bind('click keyup', function() {  
					var m = $(this), v1 = m.val();  
					if (v1.length > ml) {  
					m.val(v1.substring(0, ml))  
					}  
					//m.prev().text(m.val().length + '/' + ml);  
					$("#xxxx").text(ml-m.val().length);  
					});  
					}  
				});  
			</script>
		</div>
		<div class="control-group">
			<label class="control-label" for="marketPrice">项目市场价：</label>
			<div class="controls">
				<input type="text" name="marketPrice" class="deletekong" id="marketPrice" value="<fmt:formatNumber pattern="0.00" value="${product.marketPrice/100 }"/>"/>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="totalPrice">项目总价：</label>
			<div class="controls">
				<input type="text" name="totalPrice" id="totalPrice" class="deletekong" value="<fmt:formatNumber pattern="0.00" value="${product.totalPrice/100 }"/>"/>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="depositePrice">项目定金：</label>
			<div class="controls">
				<input type="text" name="depositePrice" class="deletekong" id="depositePrice" class="deletekong" value="<fmt:formatNumber pattern="0.00" value="${product.depositePrice/100 }"/>"/>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="divide">分期：</label>
			<div class="controls">
				<select name="divide" id="divide" onchange="checkDivide()">
						<option value=1 <c:if test="${product.divide==1 }">selected</c:if>>1</option>
						<option value=2 <c:if test="${product.divide==2 }">selected</c:if>>2</option>
						<option value=3 <c:if test="${product.divide==3 }">selected</c:if>>3</option>
						<option value=4 <c:if test="${product.divide==4 }">selected</c:if>>4</option>
						<option value=5 <c:if test="${product.divide==5 }">selected</c:if>>5</option>
				</select> <br> <span id="fee1" hidden="hidden"> 第一期价格：<input
						type="text" name="fee1" class="deletekong" value="<fmt:formatNumber pattern="0.00" value="${product.fee1/100 }"/>"/>元<br>
				</span> <span id="fee2" hidden="hidden"> 第二期价格：<input type="text"
						name="fee2" class="deletekong" value="<fmt:formatNumber pattern="0.00" value="${product.fee2/100 }"/>"/>元<br>
				</span> <span id="fee3" hidden="hidden">第三期价格：<input type="text"
						name="fee3" class="deletekong" value="<fmt:formatNumber pattern="0.00" value="${product.fee3/100 }"/>"/>元<br>
				</span> <span id="fee4" hidden="hidden">第四期价格：<input type="text"
						name="fee4" class="deletekong" value="<fmt:formatNumber pattern="0.00" value="${product.fee4/100 }"/>"/>元<br>
				</span> <span id="fee5" hidden="hidden"> 第五期价格：<input type="text"
						name="fee5" class="deletekong"  value="<fmt:formatNumber pattern="0.00" value="${product.fee5/100 }"/>"/>元
				</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="detailHtml">项目描述：</label>
			<div class="controls">
				<iframe name="ckIframe" id="ckIframe" width="700px" height="500px" src="${ctx}/product/product-ckeditor" scrolling="no" frameborder="0">
				</iframe>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="serviceProcess">服务流程：</label>
			<div class="controls">
				<textarea name="serviceProcess" id="serviceProcess">${product.serviceProcess }</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="appointment">如何预约：</label>
			<div class="controls">
				<textarea name="appointment" id="appointment">${product.appointment }</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="refundProcess">退款流程：</label>
			<div class="controls">
				<textarea name="refundProcess" id="refundProcess">${product.refundProcess }</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="special">特别提示：</label>
			<div class="controls">
				<textarea name="special" id="special">${product.special }</textarea>
			</div>
		</div> --%>
		<div class="control-group">
			<div class="controls">
				<button class="btn" type="button" onclick="saveProduct()" id="save">确定</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="btn" type="button" onclick="pageGo('${ctx}/product/product-list')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
