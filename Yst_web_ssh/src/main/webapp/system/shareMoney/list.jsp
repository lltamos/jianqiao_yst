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
							"scrollX" : true,
							"scrollCollapse" : false,
							"jQueryUI" : true,
							"paging" : true,
							"ordering" : false,
							"searching" : false,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "ydmvc/shareMoney/shareMoney.do",
								"type" : "POST",
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data.indexOf("&columns"))
											+ data.substring(data.indexOf("start") - 1, data.indexOf("&search") + 7)
											+ data.substring(data.indexOf("&search%5Bvalue%5D") + 18,data.indexOf("&search%5Bregex%5D"))
											;
								}
							},
							"columns" : [
									 {
										"data" : "orderNumber",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									 {
										"data" : "projectName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "patientName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "patientTel",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "orderMoney",
										"render" : function(data, type, full,
												meta) {
											return data != null ? (data/100)+"元" : "";
										}
									},
									/* {
										"data" : "orderStatues",//订单状态
										"render" : function(data, type, full,
												meta) {
											return (data ==  0 ? "专家机构":data == 1 ? "推荐人": "");
										}
									}, */
									{
										"data" : "shareType",//分润类型 0:专家机构 1:患者推荐人2：平台3：专家推荐人
										"render" : function(data, type, full,
												meta) {
											return (data ==  0 ? "专家机构":data == 1 ? "患者推荐人": data ==  3 ? "专家推荐人":data ==  2 ? "平台":"");
										}
									},
									{
										"data" : "shareStatues",//分润状态 1:已分润 0:待分润
										"render" : function(data, type, full,
												meta) {
											return (data ==  1 ? "已分润":data == 0 ? "待分润": "");
										}
									},
									{
										"data" : "shareMoney",
										"render" : function(data, type, full,
												meta) {
											return data != null ? (data/100)+"元" : "";
										}
									},
									{
										"data" : "patientId",
										"render" : function(data, type, full,
												meta) {
											return "<button class='btn' type='button' title='查看' onclick='pageGo(\"ydmvc/shareMoney/toPatientOrderPage.do?id="
													+ data+"&type=1"
													+ "\")'><i class='icon-eye-open'></i></button>";
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
	 //查询
     function serach()
    {
		var url = "ydmvc/shareMoney/shareMoney.do";
		url=url+"?search_LIKE_projectName="+encodeURI(($("#nameText").val()).trim());
		oTable.ajax.url(url).load();
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li class="active">分润记录</li>
	</ul>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
		
	           项目名称：<input type="text" style="width: 200px; height: 30px;" id="nameText"/>
		<input type="button" value="查询" onclick="serach();"/>
			<thead>
				<tr>
				    <th>订单号</th>
					<th>项目名称</th>
					<th>患者姓名</th>
					<th>患者电话</th>
					<th>订单金额</th>
					<!-- <th>订单状态</th> -->
					<th>分润类型</th>
					<th>分润状态</th>
					<th>分润金额</th>
					<th>订单详情</th>
				</tr>
			</thead>
			<tbody></tbody>
			
		</table>
	</div>
</body>
</html>