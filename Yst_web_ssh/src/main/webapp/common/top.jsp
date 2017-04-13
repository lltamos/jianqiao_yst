<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav  pull-right">
				<li id="fat-menu" class="dropdown">
				<a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> 
					<i class="icon-user"></i><s:property value="#session.dbUser.login_name" />   <i class="icon-caret-down"></i>
				</a>

				<ul class="dropdown-menu">
					<li><a tabindex="-1" href="user!logout">注销</a></li>
				</ul></li>
			</ul>
			<a class="brand" href=""><span class="second">健桥后台管理系统</span>
			</a>
		</div>
</div>
<!-- 弹出框 -->
	<div class="modal hide fade" id="common_dialog" style="overflow:hidden;" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="head_name"></h3>
		</div>
		<div class="modal-body" id="dialog_body" style="max-height:425px; overflow-x: hidden;">
			
		</div>
	</div>
<script type="text/javascript">
	function loading(){
		$('body').prepend('<div class="loading" id="loading"><span>努力加载,请稍后...</span></div>');
	}
	
	function cancelloading(){
		$('#loading').fadeOut();
		$('#loading').remove();
	}
	/**
	 * 禁用按钮20秒，防止重复提交
	 * @param e 按钮对象
	 */
	function disBut(e){
		loading();
		var onclick=$(e).attr("onclick");
		$(e).attr("onclick","");
		setTimeout(function(){
			$(e).attr("onclick",onclick);
			cancelloading();
		},5000);
	}
	/* 公共提示弹框 */
    function alertWindow(title,content) {
			$("#Modal").modal({
				backdrop : "static"
			});
			$("#Modal").modal('show');
			$("#ModalLabel").empty().append(title);
			$("#modal-content").html(content);
	}
	function pageGo(href) {
		if($('#loading').length==0){
			loading();
		}
		
		$.ajax({
			url : "${baseurl}/user!getSession?url="+href+"&t="+Math.random(),
			type : 'POST',
			success : function(result) {
				if(result=="false"){
					alert("登录超时，请重新登录");
					parent.window.location.href="${baseurl}/user!loginPage";
					cancelloading(); 
				}else{
					$("#index_div").load(href,function(){
 					 cancelloading(); 
					});
				}
			}
		});
	}
</script>