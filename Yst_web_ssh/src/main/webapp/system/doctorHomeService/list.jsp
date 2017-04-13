<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	$(function() {
		oTable = $("#table")
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
								"url" : "doctorHomeServiceInfo!listAjax.action",
								"type" : "POST",
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data
											.indexOf("&columns"))
											+ data.substring(data
													.indexOf("start") - 1, data
													.indexOf("&search"));
								}
							},
							"columns" : [
									{
										"data" : "order_id",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "contact_name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "address",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "phone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "member_count",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "home_doctor_type",
										"render" : function(data, type, full,
												meta) {
												if(data == null){
													return "";	
												}
												if(data == 0){
													return "小时";	
												}
												if(data == 1){
													return "按年";
												}
										}
									},
									{
										"data" : "order_id",
										"render" : function(data, type, full,
												meta) {

											var deleteStr = "";
											deleteStr = "<button class='btn' type='button' title='删除' onclick='pageGo(\"doctorHomeServiceInfo!deleteAjax?order_id="
													+ data
													+ "\")'><i class='icon-trash'></i></button>";
											return "<button class='btn' type='button' title='编辑' onclick='pageGo(\"doctorHomeServiceInfo!updatePage?order_id="
													+ data
													+ "\")'><i class='icon-pencil'></i></button>"
													+ deleteStr;
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
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="main.jsp">主页</a> <span class="divider">/</span></li>
		<li class="active">到家服务列表</li>
	</ul>
	<div class="btn-toolbar">
		<button class="btn" onclick="pageGo('doctorHomeServiceInfo!addPage')" type="button">添加到家服务
		</button>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>主键</th>
					<th>联系人</th>
					<th>服务地址</th>
					<th>联系电话</th>
					<th>家庭成员数量</th>
					<th>服务类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</body>
</html>
