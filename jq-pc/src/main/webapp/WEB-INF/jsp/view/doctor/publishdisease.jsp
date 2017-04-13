<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>

<head>
<meta charset="UTF-8">
<title>发布求医</title>
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt}/css/qyfb.css">
<script src="${cxt}/js/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="${cxt}/css/default/ui.css">
<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="${cxt }/webuploader/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="${cxt }/webuploader/webuploader.js"></script>
<script src="${cxt}/js/xheditor-1.2.2.min.js"></script>
<script src="${cxt}/js/zh-cn.js"></script>
<script src="${cxt}/js/jianqiaoindex.js"></script>
</head>

<script type="text/javascript">
    function RepNumber(obj) {
        var reg = /^[\d]+$/g;
        if (!reg.test(obj.value)) {
            var txt = obj.value;
            txt.replace(/[^0-9]+/, function (char, index, val) {
                obj.value = val.replace(/\D/g, "");
                var rtextRange = null;
                if (obj.setSelectionRange) {
                    obj.setSelectionRange(index, index);
                } else {//支持ie
                    rtextRange = obj.createTextRange();
                    rtextRange.moveStart('character', index);
                    rtextRange.collapse(true);
                    rtextRange.select();
                }
            })
        }
    }
    
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
   	    server: '${cxt}/pc/view/attachment/newUpload-Attachment.do',
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
       	$("#images").val(aids);
       	alert("图片上传成功")
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
    
    
    function publishdisease() {
    	var images = $("input[name=images]").val();
    	var nickName = $("input[name=nickName]").val();
    	var tell = $("input[name=tell]").val();
    	var patientAddress = $("input[name=patientAddress]").val();
    	var diseaseDesc = $("#diseaseDesc").val();
    	if(images==null||images==""){
    		alert("图片不能为空");
    		return false;
    	}
    	if(nickName==null||nickName==""){
    		alert("昵称不能为空");
    		return false;
    	}
    	if(tell==null||tell==""){
    		alert("电话不能为空");
    		return false;
    	}
    	if(patientAddress==null||patientAddress==""){
    		alert("地址不能为空");
    		return false;
    	}
    	if(diseaseDesc==null||diseaseDesc==""){
    		alert("病情不能为空");
    		return false;
    	}
    	var strs= new Array(); //定义一数组 
    	strs=images.split(","); 
    	 if(strs.length>10){
    		alert("您上传图片超过9张,请重新操作!");
    		return false;
    	} 
    	$.ajax({
    		type: "POST",
    		url:"${cxt}/pc/view/doctor/publishtofinddoctor?images="+images,
    		data:$('#disease').serialize(),// 你的formid
    		success: function(result) {
    			var sc=eval('(' + result + ')');
    			if(sc.code==1){
    				alert("添加成功");
    				window.location.href="${cxt }/pc/view/doctor/finddoctor";
    			}else{
    				alert("添加失败，请重新输入");
    			}
    		}
    	});
	}
    function toIndex(){
		window.location.href="${cxt }/pc/view/customer/toIndex";
	}
    function tofinddoctor() {
    	window.location.href="${cxt }/pc/view/doctor/finddoctor";
    }
    </script>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<style>
	.uploadingjq{
		padding-left:110px;
		white-space:nowrap; 
	}
	.thumbnail{
		display:inline-block;
		margin-right:10px;
	}
	.thumbnail img{
	    height: 109px;
    	width: 109px;
	}
	.upload-state-done img{
	
	} 
</style>
<div class="topsue">

	<div class="topsue_1">
		<div class="issue">
			<a href="javascript:;" onclick="toIndex()">首页</a>>><a href="javascript:;" onclick="tofinddoctor()">患者求医</a>>><i>求医发布</i>
		</div>
		<div class="issue_1">发布内容</div>
	</div>
</div>

	<div class="middlebj">
	<form id="disease" method="post">
		<div class="nickname">
			<span>昵称：</span><input type="text" name="nickName" maxlength="6"
				<c:if test="${!empty customer.name }"> value="${customer.name}"></c:if>
				<c:if test="${empty customer.name }">value=""></c:if>
		</div>
		<div class="agebj">
			<span>年龄：</span>
			<input type="text" name="age" id="age" maxlength="3"  style="height: 29px;line-height: 29px;width: 208px;" onkeyup="RepNumber(this)"
				<c:if test="${!empty customer.age }"> value="${customer.age }"></c:if>
				<c:if test="${empty customer.age  }">value=""></c:if>
		</div>

		<div class="genderjq">
			<span>性别：</span>
        	 <span class="derjq boy"><i class="der_1"></i> 男 </span>
        	 <span class="derjq girl"><i class="der_2"></i> 女</span> 
        	 <input id="sex" name="sex" value=1 style="display: none">
		</div>
		<div class="provincejq">
			<span>省份：</span>
			 <!-- <input type="text" name="cityName" value="" style="height: 29px;line-height: 29px;width: 208px;" > -->
			 <select type="text" name="provId" style="height: 29px;line-height: 29px;width: 208px;">
			 <c:forEach items="${findAllPro }" var="pro">
			 <option value="${pro.id }" >${pro.name }</option>
			 </c:forEach>
			 </select>
			 
		</div>

		<div>
			<span class="provincejq">电话：</span> <input type="text" name="tell" maxlength="11"  style="height: 29px;line-height: 29px;width: 208px; margin-top:24px" 
				onkeyup="RepNumber(this)"
				<c:if test="${!empty customer.phone }"> value="${customer.phone }"></c:if>
				<c:if test="${empty customer.phone  }">value=""></c:if>
		</div>
		<div class="particulars">
			<span>详情地址：</span> <input type="text" name="patientAddress" maxlength="25" style="height: 29px;line-height: 29px;width: 208px;" 
				<c:if test="${!empty customer.address }"> value="${customer.address }"></c:if>
				<c:if test="${empty customer.address  }">value=""></c:if>
		</div>
		<div class="clasify">
			<span>病情分类：</span><select class="asify" id="productTypeId" name="productTypeId" style="height: 29px;line-height: 29px;width: 208px;" >
				<c:forEach items="${findProductType }" var="productType" >
				<option value="${productType.id }">${productType.service_name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="describejq">
        <span>病情描述：</span>
        <textarea id="diseaseDesc" name="diseaseDesc" maxlength="100" rows="12" cols="80" style="width: 80%"></textarea>
    </div>
    <input id="images" name="images" value="" style="display: none">
		</form>
		
		<!--dom结构部分-->
			<div id="uploader-demo">
			    <!-- 用来存放item -->
			    <div id="fileList" class="uploader-list uploadingjq">
			    	
			    </div>
			    <div id="filePicker" style="margin-left: 114px;margin-top: -23px;display: none;"></div>
			</div>
			
	</div>

<a href="javascript:onclick=publishdisease();" class="fabujq">发布</a>
<%@include file="/common/bottom.jsp"%>
<!--上传文件-->
<script>

    $(".boy").bind("click",function(e){
        var aa= e.target.childNodes.length==0?e.target:e.target.querySelector("i");
        if(aa.className=="der_2"){
            $(".der_1").attr("class","der_2");
            aa.className="der_1";
        }
        $("#sex").val(1);
    });
    $(".girl").bind("click",function(e){
        var aa=e.target.childNodes.length==0?e.target: e.target.querySelector("i");
        if(aa.className=="der_2"){
            $(".der_1").attr("class","der_2");
            aa.className="der_1";
        }
        $("#sex").val(0);
    });
        $('.uploadingjq').live('click',function(){
            var ie = !-[1,];
            if(ie){
                $('input:file').trigger('click').trigger('change');
            }else{
                $('input:file').trigger('click');
            }
        });
    </script>