<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--引入CSS-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/javascript/jqueryui/webuploader.css">
<!--引入JS-->
<script type="text/javascript"
	src="${ctx}/javascript/jqueryui/webuploader.js"></script>
<html>

<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span
			class="divider">/</span></li>
		<li><a href="javascript:back();">宣传图列表 </a><span class="divider">/</span>
		</li>
		<li class="active">添加宣传图：</li>
	</ul>




	<form class="form-horizontal" id="messageInfoForm" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${model.id }" />
		<div class="control-group">
			<label class="control-label" for="title">图片描述：</label>
			<div class="controls">
				<input type="text" name="title" id="title" value="${model.descs }" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">图片预览：</label>
			<div class="controls">
				<img id='panel' alt="资讯图片" style='height: 120px;width: 220px' src="${img_service }/${model.address }" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="image">添加图片：</label>
			<div class="controls">
				<div id="filePicker">添加</div>
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button class="btn" type="button" onclick="upload();" id="save">确定</button>
				<button id="textss" class="btn" type="button" onclick="back();">返回</button>
			</div>
		</div>
	</form>





	<script type="text/javascript">
		//图片上传demo

		var uploader = null;
		var address = null;
		var smark = null;
		var aids = null;
		$(function() {
			
		
			// 优化retina, 在retina下这个值是2
			ratio = window.devicePixelRatio || 1,
			// 缩略图大小
			thumbnailWidth = 100 * ratio, thumbnailHeight = 100 * ratio,
		
			//初始化Web Uploader
			uploader = WebUploader.create({
				// 选完文件后，是否自动上传。
				auto : false,
				
				
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
				},
				

			});
		
	
			// 当有文件添加进来的时候
			uploader.on('fileQueued', function(file) {

				// 创建缩略图
				uploader.makeThumb(file, function(error, src) {

					$("#panel").attr("src", src);

				}, thumbnailWidth, thumbnailHeight);
			});

			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			uploader.on('uploadSuccess', function(file, data, response) {

				address = data.address;
				if (smark) {
					//修改
					$.ajax({
						url : '${ctx}/banner/modify',
						type : 'POST',
						data : {
							'id' : '${model.id}',
							'type' : '0',
							'descs' : $("#title").val(),
							'address' : address

						},
						success : function(data) {
							var jData = JSON.parse(data);
							if (jData.code == 1) {

								pageGo('${ctx }/banner/to-bannerlist-page');

							} else {
								alert('保存失败！');
							}

						}

					});

				} else {
					//新增
					aids = data.id;
					$.ajax({
						url : '${ctx}/banner/modify',
						type : 'POST',
						data : {
							'id' : aids,
							'type' : '0',
							'descs' : $("#title").val()
						},
						success : function(data) {
							var jData = JSON.parse(data);
							if (jData.code == 1) {

								pageGo('${ctx }/banner/to-bannerlist-page');

							} else {
								alert('保存失败！');
							}

						}

					});

				}

			});
			// 文件上传失败，现实上传出错。
			uploader.on('uploadError', function(file) {
				alert('网络异常，请重试！');
			});
			// 完成上传完了，成功或者失败，先删除进度条。
			uploader.on('uploadComplete', function(file) {

			});

		});

		function back() {
			pageGo('${ctx }/banner/to-bannerlist-page');

		}

		function upload() {
			var a = "${model.address}";
			var b = $("#panel").attr("src").indexOf(a);
			var splitstr = $("#panel").attr("src").split("${img_service }");

			if (a) {
				//修改
				if ("/" + a == splitstr[1]) {

					//不修改图片
					$.ajax({
						url : '${ctx}/banner/modify',
						type : 'POST',
						data : {
							'id' : "${model.id}",
							'type' : '0',
							'descs' : $("#title").val()
						},
						success : function(data) {
							var jData = JSON.parse(data);
							if (jData.code == 1) {
								alert('保存成功！');
								pageGo('${ctx }/banner/to-bannerlist-page');
							} else {
								alert('保存失败！');
							}
						}
					});
				} else {
				
					smark = true;
					uploader.upload();
				}

			} else {
			
				smark = false;
				uploader.upload();
			}

		}
	</script>

</body>
</html>