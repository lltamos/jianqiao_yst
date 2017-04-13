<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		oTable = $("#doctortable")
				.DataTable(
						{
							"processing" : true,
							"serverSide" : true,
							"scrollX": true,
							"scrollCollapse": true,
        					"jQueryUI": true,
							"paging" : true,
							"ordering" : false,
							"searching" : false,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "${cxt}/doctor/doctor-list",
								"type" : "POST",
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data
											.indexOf("&columns"))
											+ data.substring(data
													.indexOf("start") - 1, data
													.indexOf("&search")+7)+ data
													.substring(
															data
																	.indexOf("&search%5Bvalue%5D") + 18,
															data
																	.indexOf("&search%5Bregex%5D"));
								}
							},
							"columns" : [
									{
										"data" : "customerPhone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "merchantName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "deleted",
										"render" : function(data, type, full,
												meta) {
											return data == 0 ? "未禁用" : (data == 1) ? "已禁用" : "未知状态";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {

													var butStr = "";
													butStr += "<button id='showbut' class='btn' type='button' title='查看' onclick='pageGo(\"${ctx}/doctor/doctor-view?doctorid="
														+ data
														+ "\")'><i class='icon-eye-open'></i></button>";
														if(full.deleted !=1){
															butStr += "<button  id='butban' class='btn' type='button' title='禁用' onclick='disable(\""
															+ data
															+"\",\""+full.deleted+"\")'><i class='icon-ban-circle'></i></button>";
														}else{
															butStr += "<button id='butok' class='btn' type='button' title='启用' onclick='disable(\""
																+ data
																+"\",\""+full.deleted+"\")'><i class='icon-ok-circle'></i></button>";
														}
													return butStr;
													
									}
									} ],
							"language" : {
								"emptyTable" : "没有数据",
								"info" : "显示 第 _START_ 到 _END_ 条数据  总共 _TOTAL_ 条数据 ",
								"infoEmpty" : "没有数据",
								"infoFiltered" : "(总共  _MAX_ 条数据)",
								"infoPostFix" : "",
								"thousands" : ",",
								"lengthMenu" : "每页显示条数 _MENU_ ",
								"loadingRecords" : "加载中...",
								"processing" : "处理中...",
								"search" : "搜索:",
								"zeroRecords" : "找不到匹配的数据",
								"paginate" : {
									"first" : "首页",
									"last" : "尾页",
									"next" : "下一页",
									"previous" : "上一页"
								}
							}
						});

	});
	function pageGo(href) {
		$("#index_div").load(href);
	}
	
	function queryInfo() {
		var query1 = $("#doctorphone");
		var query2 = $("#doctorstatus");
		var url = "${cxt}/doctor/doctor-list?doctorphone=" + encodeURI(query1.val())+"&status="+encodeURI(query2.val());
		oTable.ajax.url(url).load();
	}
	
	function disable(id,deleted){
		$("#deleteModal").modal({
			backdrop : "static"
		});
		$("#deleteModal").modal('show');
		if(deleted==0){//删除
			$("#confirmText").empty().append("您确认要禁用吗？");
			deleted=1;
		}else{
			$("#confirmText").empty().append("您确认要恢复吗？");
			deleted=0;
		}
		var $confirmDeleteDialogBut = $("#confirmDeleteDialogBut");
		$confirmDeleteDialogBut.attr('onclick', "setIdToConfirm(this,'${ctx}/doctor/doctor-disable?doctorid="+id+"')");
		/* var url = "${cxt}/doctor/doctor-list";
		$.ajax({
			type : 'POST',
			url : "${ctx}/doctor/doctor-disable?doctorid="+id,
			dateType : "JSON",
			success : function(result) {
				var appResult = eval("(" + result + ")");
				var message = appResult.msg;
				var success = appResult.code;
				if (success == 1) {
					alert(message);
				}else{
					alert(message);
					oTable.ajax.url(url).load();
				}
			}
		}); */
	}
	function setIdToConfirm(e,url){
			$.ajax({
				type : 'POST',
				url : url,
				dateType : "JSON",
				success : function(result) {
					var result = JSON.parse(result);
					var message = result.msg;
					var code = result.code;
					$(e).tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 1
					});
					//刷新数据
					oTable.draw(false);
				} //显示操作提示
			});
	}
	
	$("#showbut").
    tooltip({
    trigger:'hover',
    html:true,
    title:'禁用'});
	
	/* .popover({  trigger:'click',
	                              placement:'bottom',
	                              content:'弹出气泡提示' 
                              });*/
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li class="active" >医生列表</li>
	</ul>
	<div class="btn-toolbar">
		
	</div>
	<form class="form-horizontal" id="InfoForm"  onSubmit="return false;">
		<div class="control-group">
			<%-- <button class="btn" onclick="pageGo('${ctx}/doctor/to-doctory-apply')" type="button">医生申请</button> --%>
			<div class="controls">
				<label style="display: inline-block;" for="doctorphone">医生账号：</label>
				<input type="text" name="doctorphone" id="doctorphone" />
				<label style="display: inline-block;" for="customerPhone">禁用状态：</label>
				<select id="doctorstatus" name="doctorstatus">
					<option value="1">禁用</option>
					<option value="0">未禁用</option>
				</select>
				<button class="btn" type="button" onclick="queryInfo()" id="query">查询</button>
			</div>
			<!-- <div id="container">
				页面元素
			      <div class="text-center" data-toggle="tooltip" id="myDiv"> 鼠标滑动到这（1）显示提示，点击这（2）查看弹出气泡提示。</div>
			</div> -->
		</div>
	</form>
	<div class="container-fluid">
		<table id="doctortable" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>医生账号</th>
					<th>工作单位</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
	
			</tbody>
		</table>
	</div>
	<!-- dialog start -->
	<div class="modal small hide fade" id="deleteModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">请确认</h3>
		</div>
		<div class="modal-body">
			<p class="error-text">
				<i class="icon-warning-sign modal-icon"></i>
			<p id="confirmText">您确认要禁用吗?</p>
		</div>
		<div class="modal-footer">
			<button class="btn btn-danger" data-dismiss="modal"
				id="confirmDeleteDialogBut">确认</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		</div>
	</div>
	<!-- dialog end -->
</body>
