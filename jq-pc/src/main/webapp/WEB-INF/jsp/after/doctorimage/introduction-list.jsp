<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${cxt }/css/base.css">
    <link rel="stylesheet" href="${cxt }/css/yisheng_2.css"/>
    <%-- <script src="${cxt }/js/uploadPreview.js" ></script> --%>
    <%-- <script src="${cxt }/js/jquery.fileupload.js" ></script> --%>
    <!--引入CSS-->
	<link rel="stylesheet" type="text/css" href="${cxt }/webuploader/webuploader.css">
	<!--引入JS-->
	<script type="text/javascript" src="${cxt }/webuploader/webuploader.js"></script>
</head>
<body>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
    <div class="bg_gray">
        <div class="content">
            <%@include file="/common/mycenterleft_docter.jsp"%>
            	<%@include file="/common/mycentertop.jsp"%>
                <div class="item">
                   <ul id="warp" class="doctor">
                   <input type="hidden" id="num" name="num" value="${doctorImageList.size()}"/>
                   <c:forEach items="${doctorImageList}" var="doctorimage" varStatus="status">
	                      <li id="doctorimagelist" tabindex="-1" >
	                       	   <input type="hidden" id="id" name="id" value="${doctorimage.id }"/>
							   <input type="hidden" id="doctorName" value="${doctorimage.doctorName }"/>
	                           <img width="100" height="100" src="${img_service }/${doctorimage.image }" />
	                           <div class="doc_detailed">
	                               <p>${doctorimage.des}</p>
	                           </div>
	                       </li>
                    </c:forEach>
                    <!-- <form  id="doctorImageForm" enctype="form-data/multipart" method="post"> -->
	                       <li id="last" >
			                   <%-- <input class="tan" type="file" name="image" id="up_img_WU_FILE_1" />
	                           <img id="imgShow_WU_FILE_1" width="100" height="100" src="${cxt }/img/tupianTian.jpg"/> --%>
	                           <!-- <input type="file" name="urlfile" /> -->
	                           <!--dom结构部分-->
								<div id="uploader-demo">
								    <!-- 用来存放item -->
								    <div id="fileList" class="uploader-list"></div>
								    <div id="filePicker">选择图片</div>
								</div>
	                           <div class="doc_detailed">
	                               <textarea name="des" id="des" placeholder="点击添加相关描述....100字以内"></textarea>
	                           </div>
	                       </li>
	                	<input type="button" class="TiJiao_tu" id="TiJiao_tu" onclick="toSaveDoctorImage()" value="提交">
			        <!-- </form> -->
                    </ul>
            </div>
        </div>
    </div> 
   
    <script type="text/javascript">
    $(function(){
    	/* new uploadPreview({UpBtn: "up_img_WU_FILE_0",  ImgShow: "imgShow_WU_FILE_0"}); */
    	var num = $("#num").val();
    	if(num>3){
    		document.getElementById('last').style.display = "none";
    		document.getElementById('TiJiao_tu').style.display = "none";
    	}
    });
        function open_1(){
            var s = document.getElementById("test");
            s.style.display = "block";
            var d=document.getElementById("phone_num");
            d.style.border='1px dashed #9C9AA5';
            var a =document.getElementById("shezhi");
            a.style.visibility = "hidden";
        }
        function check(){
        	var image = $("#image").val();
     		var des = $("#des").val();
     		if( image =="" || image==null ){
     			alert("图片不能为空！")
     			return false;
     		}
     		if( des ==null || des =="undefined" ){
     			alert("描述不能为空！")
     			return false;
     		}
        	return true;
        };
        function goPage(){
        	window.location.href='${ctx }/pc/after/doctorimage/doctorimage-list';
        }
        
    </script>
    <script type="text/javascript">
  //图片上传demo
	var aids = "";
 jQuery(function() {
    var $ = jQuery,
        $list = $('#fileList'),
        // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,
        // 缩略图大小
        thumbnailWidth = 100 * ratio,
        thumbnailHeight = 100 * ratio,
        // Web Uploader实例
        uploader;
	    // 初始化Web Uploader
	    //初始化Web Uploader
		var uploader = WebUploader.create({
	    // 选完文件后，是否自动上传。
	    auto: true,
	    // swf文件路径
	    swf:  '${cxt}/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: 'http://${shangchuan}${cxt}/pc/view/attachment/newUpload-Attachment.do',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#filePicker',
	    //formData: { "name": "", "desc": ""},
	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/jpg,image/jpeg,image/png'
	    }
	});
    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                '</div>'
                ),
            $img = $li.find('img');

        $list.append( $li );
        // 创建缩略图
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );
    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');
        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
        }

        $percent.css( 'width', percentage * 100 + '%' );
    });
    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file, data, response) {
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
    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').remove();
    });
}); 
 
 function toSaveDoctorImage(){
	 var des = $("#des").val();
	 if(des == undefined || des == "" || des == null){
		 alert("描述内容不能为空");
		 return;
	 }
	 $.ajax({
		 type : "POST",
		 url : "${cxt}/pc/after/doctorimage/add-doctorimage",
		 data:{ "des": des, "aids": aids},
		 success : function(data) {
			 var sc = eval('(' + data + ')')
			 /*var parsedJson = jQuery.parseJSON(result); */
			var message = sc.msg;
			var code = sc.code;
			if(code==1){
				alert(message);
				window.location.href="${cxt }/pc/after/doctorimage/doctorimage-list";
			}
			window.location.href="${cxt }/pc/after/doctorimage/doctorimage-list";
		 }
		 
	 });
	 
	 
 }
    </script>
    
    <%@include file="/common/bottom.jsp"%>
    <script  type="text/javascript"  src="${ctx }/js/jquery.form.min.js"></script>
 
</body>
</html>
