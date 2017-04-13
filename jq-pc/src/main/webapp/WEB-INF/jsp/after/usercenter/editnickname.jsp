<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/base.css" />
<link rel="stylesheet" href="${cxt }/css/personal_center_2.css" />
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<script src="${cxt }/js/upload.js"></script>

<!--引入CSS-->
<link rel="stylesheet" type="text/css"
	href="${cxt }/webuploader/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="${cxt }/webuploader/webuploader.js"></script>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>

<body>
	<div class="bg_gray">
		<div class="content">
			<%@include file="/common/mycenterleft.jsp"%>
			<div class="con_right">
				<div class="con_1">
					<ul>
						<li><span>昵 称:</span><input id="nickname" maxlength="12" type="text"
							 value="${sessionScope.customer.nickName}" /></li>
						<li><span>头 像:</span> <img style="height: 120px;width: 120px;display: block;"
							src="${img_service }/${sessionScope.customer.image}" alt="" id='headimg' /> <!--dom结构部分-->
							<div id="uploader-demo" style="margin-left: 68px;margin-top: 14px;">
								<!-- 用来存放item -->

								<div id="filePicker">修改头像</div>
							</div>


							<p>建议使用正方形的图片，支持JPG、GIF、PNG格式，100kb以内</p></li>
					</ul>

					<button id="keep" style="background-color: blue;" onclick="toSaveDoctorImage();">保存修改</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		function open_2() {
			var s = document.getElementById("keep");
			s.style.backgroundColor = "#EDEFEF";
			s.style.color = "#BCBCBC";
			alert('修改成功');
		}
		
		//图片上传demo
		var aids = -1;
		var finalname="null";
		jQuery(function() {
			
			var $ = jQuery,
			
			// 优化retina, 在retina下这个值是2
			ratio = window.devicePixelRatio || 1,
			// 缩略图大小
			thumbnailWidth = 100 * ratio, thumbnailHeight = 100 * ratio,
			// Web Uploader实例
			uploader;
			// 初始化Web Uploader
			//初始化Web Uploader
			var uploader = WebUploader.create({
				// 选完文件后，是否自动上传。
				auto : true,
				// swf文件路径
				swf : '${cxt}/webuploader/Uploader.swf',
				// 文件接收服务端。
				server : '${cxt}/pc/view/attachment/newUpload-Attachment.do',
				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick : '#filePicker',
				//formData: { "name": "", "desc": ""},
				// 只允许选择图片文件。
				accept : {
					title : 'Images',
					extensions : 'gif,jpg,jpeg,bmp,png',
					mimeTypes : 'image/jpg,image/jpeg,image/png'
				}
			});
			// 当有文件添加进来的时候
			uploader.on('fileQueued', function(file) {
				$("#keep").attr("disabled", true);
				$("#keep").css("background-color", 'gray');
				// 创建缩略图
				uploader.makeThumb(file, function(error, src) {
					if (error) {
						$("#headimg").replaceWith('<span>不能预览</span>');
						return;
					}
					$("#headimg").attr('src', src);

				}, thumbnailWidth, thumbnailHeight);
			});

			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			uploader.on('uploadSuccess', function(file, data, response) {
				
				aids=data.id;
				
			});
			// 文件上传失败，现实上传出错。
			uploader.on('uploadError', function(file) {
				alert('网络异常，请重试！');
			});
			// 完成上传完了，成功或者失败，先删除进度条。
			uploader.on('uploadComplete', function(file) {
				$("#keep").attr("disabled", false);
				$("#keep").css("background-color", 'blue');
			});
			
			finalname=$("#nickname").val();
		});

		function toSaveDoctorImage() {
			
			if(finalname==$("#nickname").val()&&aids==-1){
				alert("没有变化");
				return false;
			}
			$.ajax({
				type : "POST",
				url : "${cxt}/pc/after/customer/change-nicknameandinfo",
				data : {
				
					"aids" : aids,
					"nickname":$("#nickname").val()
				},
				success : function(result) {
					var data=JSON.parse(result);
					if(data.code==1){
						
						location.replace(location.href);
					}else{
						alert(data.msg);
					}
					
				},
				error : function(data) {
					alert("保存失败");
				}
			});

		}
	</script>
</body>
</html>