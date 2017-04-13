<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>医生认证</title>
    <link rel="stylesheet" href="${cxt }/css/base.css">
    <link rel="stylesheet" href="${cxt }/css/yishengrenzheng.css">
    <!--引入CSS-->
	<link rel="stylesheet" type="text/css" href="${cxt }/webuploader/webuploader.css">
	<!--引入JS-->
	<script type="text/javascript" src="${cxt }/webuploader/webuploader.js"></script>
</head>
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
    //限制上传的个数
    fileNumLimit: 4,
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
                /* '<div class="info">' + file.name + '</div>' + */
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
	var id = aid ; 
	aids = id + "," + aids;
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
function certification(){
	$("#doctorForm").append("<input type="+"hidden"+" name="+"aids"+" id="+"aids"+" value="+aids+"></input>")
	var name = $("#name").val();
	if(name == null || name == ""){
		alert("医生姓名不能为空");
		return;
	}
	var sex = $("input[name=sex]:checked").val();
	if(sex == null || sex == ""){
		alert("性别不能为空");
		return;
	}
	$.ajax({
		type:"POST",
		 url:"${cxt}/pc/view/doctor/doctor-certification",
		 data: $("#doctorForm").serialize(),
		 success:function(result){
			var data = eval('(' + result + ')')
			if(data.code == '0'){
				alert(data.msg)
				window.location = "${cxt}/index.jsp"
			}
			if(data.code == '1'){
				alert(data.msg)
				location.reload();
			}
		}
	})
}

/* function certification() {*/
	/* if (check()) { */
		/*var form = $("#doctorForm");
		var options = {
			url:"${cxt}/pc/view/doctor/doctor-certification",
			type : 'POST',
			success : function(result) {
				var appResult = eval("(" + result + ")");
				var message = appResult.msg;
				var success = appResult.code;
				alert(message);
				if (success == 0) {
				}else{
					
				}
			} //显示操作提示
		};
		form.ajaxSubmit(options);
	/* } */
/*} */

</script>
<body>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
    <form id="doctorForm" method="post" enctype="multipart/form-data">
    <div class="blue_bg">
        <div class="bg_dian">
    	<div class="YiShengRenZheng">
        <h2>医生认证</h2>
	        <div class="YiSheng_body">
	            <div class="YiSheng_body_1">
	                <span>姓&nbsp;&nbsp;&nbsp;名：</span>
	                <input name="name" id="name" type="text">
	            </div>
	            <div class="YiSheng_body_2">
	                <span>性&nbsp;&nbsp;&nbsp;别：</span>
	                <input name="sex" type=radio value="0" ><i>男</i> 
	                <input name="sex" type=radio value="1" ><i>女</i> 
	            </div>
	            <div class="YiSheng_body_3">
	                <span style="vertical-align: top;">认证照片：</span>
	                <!--dom结构部分-->
	                <div style="display: inline-block;">
						<div id="uploader-demo" style="display: inline-block;">
						    <!-- 用来存放item -->
						    <div id="fileList" class="uploader-list"></div>
						    <div id="filePicker">选择图片</div>
						</div>
	                </div>
	            </div>
	            <style>
	            	#fileList > div{
	            		float:left;
	            		width:94px;
	            		height:120px;
	            		margin-right:10px;
	            	} 
	            	#filePicker{
	            		float:left;
	            		width:94px;
	            		height:120px;
	            	}
	            	#uploader-demo>div{
	            		float:left;
	            	}
	            </style>
		                <i style="display:block; margin-left:85px;">免冠证件照1张，工作证件3张(身份证、医生资格证、健康证)，支持JPG 、 PNG格式，100kb以内。</i>
	            <div class="YiSheng_body_5" style="display: block; padding-top: 20px;">
	                <span>工作单位:</span>
	                <select name="merchantId">
		                <c:forEach items="${merchantlist }" var="merchant">
		                		<option  value="${merchant.id }">${merchant.name }</option>
		                </c:forEach>
	                </select>
	            </div>
	            
	            <div class="YiSheng_body_5">
	                <span>医生专长:</span>
	                <input id="specName" name="specName" type="text">
	            </div>
	            
	            <div class="YiSheng_body_5">
	                <span>科&nbsp;&nbsp;&nbsp;室:</span>
	                <select name="officeId">
	                	<c:forEach items="${officelist }" var="office">
	                		<option name="officeId" value="${office.id }">${office.name }</option>
	                	</c:forEach>
	                </select>
	            </div>
	            
	            <div class="YiSheng_body_5">
	                <span>职&nbsp;&nbsp;&nbsp;称:</span>
	                <select name="titleId">
	                	<c:forEach items="${titallist }" var="tital">
	                		<option value="${tital.id }">${tital.name }</option>
	                	</c:forEach>
	                </select>
	            </div>
	            <div class="YiSheng_body_6">
	                <span>个人介绍:</span>
	                <textarea name="des" id="" cols="30" rows="10"></textarea>
	            </div>
	        </div>
	        <input type="button" onclick="certification()" value="提交" class="TiJiaol">
	    </div>
	      </div>
    </div>
    </form>
    <%@include file="/common/bottom.jsp"%>
</body>