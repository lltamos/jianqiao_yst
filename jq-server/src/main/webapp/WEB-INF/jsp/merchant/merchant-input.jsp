<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<link rel="stylesheet" type="text/css" href="${cxt }/ueditor/third-party/webuploader/webuploader.css">
</head>
<script type="text/javascript" src="${cxt }/ueditor/third-party/webuploader/webuploader.js"></script>
<html>
<script type="text/javascript">
	function check() {
		var name = $("input[name='name']");
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
		}/* else{
			img.val(jQuery.trim(img.val()));
		} */ 
		var phone = $("input[name='customerPhone']");
		if (phone.val() == "") {
			phone.tips({
				side : 2,
				msg : '手机号不得为空',
				bg : '#AE81FF',
				time : 1
			});

			phone.focus();
			return false;
		} else if (isNaN(phone.val())) {
			phone.tips({
				side : 2,
				msg : '请输入正确的手机号！',
				bg : '#AE81FF',
				time : 1
			});

			phone.focus();
			return false;
		} else if (phone.val().length != 11) {
			phone.tips({
				side : 2,
				msg : '请输入正确的手机号！',
				bg : '#AE81FF',
				time : 1
			});
			phone.focus();
			return false;
		} else {
			phone.val(jQuery.trim(phone.val()));
		}
		var des = $("#des");
		if (des.val() == "") {
			des.tips({
				side : 2,
				msg : '请输入总院简介',
				bg : '#AE81FF',
				time : 1
			});

			des.focus();
			return false;
		}else if (des.val().length > 100) {
			des.tips({
				side : 2,
				msg : '总院简介不能超过100字！',
				bg : '#AE81FF',
				time : 1
			});
			des.focus();
			return false;
		} else {
			des.val(jQuery.trim(des.val()));
		}
		return true;
	}
</script>
<script>
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
	// 保存用户信息
	function saveMerchant() {
		var id = $("#id").val();
		var name = $("#name").val();
		var customerPhone = $("#customerPhone").val();
		var des = $("#des").val();
		if(check()){
			$.ajax({
				url : '${ctx }/merchant/merchant-save',
				type : 'POST',
				data : {
					'aids' : aids,
					'id' : id,
					'name' : name,
					'customerPhone' : customerPhone,
					'des' : des,
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
						pageGo('${ctx }/merchant/merchant-list');
					}
				}
			});
		}else{
			uploader.upload();
		}
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a></li>
		<li>总院列表</li>
		<li class="active">总院信息：</li>
	</ul>
	<form class="form-horizontal" id="merchantInfoForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${merchant.id }"/>
		<div class="control-group">
			<label class="control-label" for="name">总院名称：</label>
			<div class="controls">
				<input type="text" name="name" id="name" value="${merchant.name }"/>
			</div>
		</div>
		<c:if test="${!empty merchant.imageCred1 }">
		<div class="control-group">
			<label class="control-label" for="image_cred_1">原营业执照：</label>
			<div class="controls">
				<img style="width:200px; height:200px;" alt="营业执照1" src="${img_service }/${merchant.imageCred1 }"  />
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label" for="image_cred_1">添加营业执照 ：</label>
			<div class="controls">
				<img id='panel' width="200" height="200" />
				<div id="filePicker">添加</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customerPhone">所属用户手机号：</label>
			<div class="controls">
				<input type="text" name="customerPhone" id="customerPhone" value="${merchant.customerPhone }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="des">总院简介：</label>
			<div class="controls">
				<textarea rows="5" cols="100" name="des" id="des" >${merchant.des }</textarea>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn"
						type="button" onclick="saveMerchant()" id="save">确定</button>
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/merchant/merchant-list')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
