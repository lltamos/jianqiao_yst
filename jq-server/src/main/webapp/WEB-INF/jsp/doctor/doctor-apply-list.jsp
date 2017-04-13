<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		oTable = $("#doctorapplytable")
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
								"url" : "${cxt}/doctor/doctor-apply-list",
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
										"data" : "applyStatus",
										"render" : function(data, type, full,
												meta) {
											return data == 0 ? "待审核" : (data == 1) ? "已审核" : (data == 2) ? "未通过" : "";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
													var butStr = "";
													butStr += "<button class='text-center' data-toggle='tooltip' id='showbut' class='btn' type='button' title='查看' onclick='pageGo(\"${ctx}/doctor/doctor-apply-view?doctorid="
														+ data
														+ "\")'><i class='icon-eye-open'></i></button>";
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
		var query2 = $("#applyStatus");
		var url = "${cxt}/doctor/doctor-apply-list?doctorphone=" + encodeURI(query1.val())+"&applyStatus="+encodeURI(query2.val());
		oTable.ajax.url(url).load();
	}
	
	/* function approval(id,verify){
		var url = "${cxt}/doctor/doctor-apply-list";
		$.ajax({
			type : 'POST',
			url : "${ctx}/doctor/doctor-approval?doctorid="+id+"&verify="+verify,
			dateType : "JSON",
			success : function(result) {
				var appResult = eval("(" + result + ")");
				var message = appResult.msg;
				var success = appResult.code;
				if (success == 0) {
					$("#regBox").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 2
					});
				}else{
					oTable.ajax.url(url).load();
				}
			}
		});
	} */
	
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
				<label style="display: inline-block;" for="applyStatus">审核状态：</label>
				<select id="applyStatus" name="applyStatus">
					<option></option>
					<option value="0">待审核</option>
					<option value="2">未通过</option>
				</select> 
				<button class="btn" type="button" onclick="queryInfo()" id="query">查询</button>
			</div>
			<!-- <div id="container">
			      <div class="text-center" data-toggle="tooltip" id="myDiv"> 鼠标滑动到这（1）显示提示，点击这（2）查看弹出气泡提示。</div>
			</div> -->
		</div>
	</form>
	<div class="container-fluid">
		<table id="doctorapplytable" class="display" cellspacing="0" width="100%">
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
</body>
