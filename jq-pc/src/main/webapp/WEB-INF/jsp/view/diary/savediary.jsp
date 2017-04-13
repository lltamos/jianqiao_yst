<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<title>健康分享</title>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/diary.css">
<link rel="stylesheet" href="${cxt }/css/default/ui.css">
<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="${cxt }/webuploader/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="${cxt }/webuploader/webuploader.js"></script>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
	#fileList{
		overflow: hidden;
	}
	#fileList>div{
		float:left;
	}
</style>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<script type="text/javascript">
//图片上传demo
	var aids = "";
	function geshu(){
		var cc = $("div.uploader-list>div").size();
		alert(cc);
		if(cc > 9){
			alert("已到达图片上传上线");
			
		}
	}
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
	    //数量
	    fileNumLimit: 9,
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
    	var cc = $("div.uploader-list>div").size();
    		if(cc > 8){
    			alert("已到达图片上传上线");
    			return;
    		}
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail" style="margin-left: 5px; margin-bottom: 5px;">' +
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

 function toGoSaveDiary(){
	 var diarybookid = $("#bookid").val();
	 var content = $("#elm3").val();
	 if(content == undefined || content == "" || content == null){
		 alert("日记内容不能为空");
		 return;
	 }
	 $.ajax({
		 type : "POST",
		 url : "${cxt}/pc/view/diary/saveDiaryByBookId.do?",
		 data : {"diarybookid":diarybookid,"content":content,"aids":aids},
		 success : function(result) {
		 	var appResult = eval("(" + result + ")"); 
			var message = appResult.msg;
			var code = appResult.code;
			alert(message);
			if(code==0){
				window.location.href="${cxt }/pc/view/diarybook/toDiaryListPage.do?";
			}
		 }
		 
	 }); 
	 
	 
 }
 function toIndex(){
		window.location.href="${cxt }/pc/view/customer/toIndex";
	}
</script>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
    <div class="topsue">
       <div class="topsue_1">
            <div class="issue">
                <a href="javascript:;" onclick="toIndex()">首页</a>>>
                <a href="${cxt }/pc/view/diary/toDiaryListPage.do">健康分享</a>>>
                <a href="${cxt }/pc/view/diarybook/toDiaryListPage.do">日记本</a>>>
                <i>更新日记页</i>
            </div>
            <div class="issue_1">
                更新日记
            </div>
        </div>
    </div>
    
    <div class="middlebj">
        <div class="clasify">
                <span>所属日记本：</span><select class="asify">
                                          <option id="bookid" value ="${diaryBook.id }">${diaryBook.diaryBookName }</option>
                                    </select>
            </div>
            <div class="describejq">
            	<input type="hidden"></input>
                <span>正文：</span>
				<textarea id="elm3" name="elm3" class="xheditor-simple" rows="12" cols="80" style="width: 80%"></textarea>
            </div>
			<!--dom结构部分-->
			<div style="margin-left: 126px; margin-top: 20px; ">
				<div id="uploader-demo">
				    <!-- 用来存放item -->
				    <div id="fileList" class="uploader-list"></div>
				    <div id="filePicker">选择图片</div>
				</div>
			</div>

	<a href="javascript:;" onclick="toGoSaveDiary()" class="fabujq">发布</a>
    </div>
    <%@include file="/common/bottom.jsp"%>