<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<style>
        .inp_11{
            padding: 50px 102px;
        }
        .inp_11 span{
            float: left;
            margin-right: 10px;
        }
        .inp_11 input{
            width: 228px;
            padding-left: 10px;
            height: 30px;
        }
        .btn_33{
            padding: 15px 146px;
            text-align: right;
        }
</style>
<script type="text/javascript">
	
	
	
	function examine(apply){
		var id = $("input[id=doctorId]").val();
		var reason = $("input[id=yuanyin]").val();
		if(apply == 2){
			if(reason == "" || reason == null){
				alert("拒绝原因不能为空");
				return;
			}
		}
		$.ajax({
			type : "POST",
			url : "${ctx}/doctor/examine-doctor",
			data : {"apply":apply, "id":id, "reason":reason},
			success : function(result) {
			 	var appResult = eval("(" + result + ")"); 
				var message = appResult.msg;
				var code = appResult.code;
				alert(message);
				if(code==0){
					pageGo("${ctx }/doctor/to-doctor-apply-list");
				}
			 }
		})
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li>医生列表 <span class="divider">/</span>
		</li>
		<li class="active">查看医生详细信息：</li>
	</ul>
	<form class="form-horizontal" id="productInfoForm" method="post"  enctype="multipart/form-data">
		<input type="hidden" id="doctorId" value="${doctors.id }"/>
		<div class="control-group">
			<label class="control-label" for="name">医生名称：</label>
			<div class="controls">
				${doctors.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="merchantName">所属医院：</label>
			<div class="controls">
				${doctors.merchantName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="officeName">科室：</label>
			<div class="controls">
				${doctors.officeName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="specName">医生专长：</label>
			<div class="controls">
				${doctors.specName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="titleName">职称：</label>
			<div class="controls">
				${doctors.titleName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="titleName">医生介绍：</label>
			<div class="controls">
				${doctors.des }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="titleName">头像图片：</label>
			<div class="controls">
				<s:if test="image_header!=null">
					<img alt="image_header" src="${img_service }/${doctors.image_header }" width="200" height="200">
				</s:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="titleName">工作证：</label>
			<div class="controls">
				<s:if test="image_work_1!=null">
					<img alt="image_work_1" src="${img_service }/${doctors.imageWork1 }" width="200" height="200">
				</s:if>
				<s:if test="image_work_2!=null">
					<img alt="image_work_2" src="${img_service }/${doctors.imageWork2 }" width="200" height="200">
				</s:if>
				<s:if test="image_work_3!=null">
					<img alt="image_work_3" src="${img_service }/${doctors.imageWork3 }" width="200" height="200">
				</s:if>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn" onclick="examine(1)" type="button">审核通过</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button"  class="btn btn-lg" data-toggle="modal" data-target="#myModal">审核未通过</button>
			</div>
		</div>
	</form>
	  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="inp_11">
                        <span>拒绝原因:</span><input type="text" id="yuanyin">
                    </div>
                </div>
                <div class="btn_33">
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="examine(2)" >确认</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
    </div>
    <style>
    .controls img{
    				height: 200px ;
					width: 200px ;
   				}
    </style>

</body>
