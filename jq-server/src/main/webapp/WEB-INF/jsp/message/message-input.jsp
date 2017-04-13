<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<link rel="stylesheet" type="text/css" href="${cxt }/ueditor/third-party/webuploader/webuploader.css">
</head>
<script type="text/javascript" src="${cxt }/ueditor/third-party/webuploader/webuploader.js"></script>
<script type="text/javascript">
	function check() {
		var title = $("input[name='title']");
		if (title.val() == "") {
			title.tips({
				side : 2,
				msg : '标题名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			title.focus();
			return false;
		} else {
			title.val(jQuery.trim(title.val()));
		}
		var des = $("#des");
		if (des.val() == "") {
			des.tips({
				side : 2,
				msg : '请输入图片描述',
				bg : '#AE81FF',
				time : 3
			});

			des.focus();
			return false;
		}else if (des.val().length > 100) {
			des.tips({
				side : 2,
				msg : '图片描述不能超过100字！',
				bg : '#AE81FF',
				time : 3
			});
			des.focus();
			return false;
		} else {
			des.val(jQuery.trim(des.val()));
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
		var address = $("#address");
		if (address.val() == "") {
			address.tips({
				side : 2,
				msg : '请输入跳转链接',
				bg : '#AE81FF',
				time : 3
			});
			address.focus();
			return false;
		}else {
			address.val(jQuery.trim(address.val()));
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
	function saveMessage(){
		var id = $("#id").val();
		var status = $("#status").val();
		var title = $("#title").val();
		var des = $("#des").val();
		var address = $("#address").val();
		if(check()){
			$.ajax({
				url : '${ctx }/message/message-save',
				type : 'POST',
				data : {
					'aids' : aids,
					'id' : id,
					'status' : status,
					'title' : title,
					'des' : des,
					'address' : address,
				},
				success : function(data) {
					var result = JSON.parse(data);
					var message = result.msg;
					var code = result.code;
					if (code == 1) {
						alert(message);
						pageGo('${ctx }/message/to-message');
					} else {
						alert('操作失败！');	
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
		<li>健桥资讯列表</li>
		<li class="active">添加资讯</li>
	</ul>
	<form class="form-horizontal" id="messageInfoForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${message.id }"/>
		<input type="hidden" name="status" id="status" value="${message.status }"/>
		<div class="control-group">
			<label class="control-label" for="title">标题名称：</label>
			<div class="controls">
				<input type="text" name="title"  id="title" value="${message.title }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="des">文字描述：</label>
			<div class="controls">
				<textarea rows="5" cols="100" name="des" id="des" >${message.des }</textarea>
			</div>
		</div>
		<c:if test="${!empty message.image }">
		<div class="control-group">
			<label class="control-label" for="image">原资讯图片：</label>
			<div class="controls">
			<input type="hidden" id = "image" value="${message.image }">
				<img alt="资讯图片" src="${img_service }/${message.image }" width="200" height="200" />
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label" for="image">添加图片：</label>
			<div class="controls">
				<img id='panel' width="200" height="200" />
				<div id="filePicker">添加</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">跳转地址：</label>
			<div class="controls">
				<input type="text" name="address" id="address" value="${message.address }"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn"
						type="button" onclick="saveMessage()" id="save">确定</button>
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/message/to-message')">返回</button>
			</div>
		</div>
	</form>
</body>

