<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	
				<textarea name="myDetailHtml" id="myDetailHtml"></textarea>
				<script type="text/javascript">
				var html=$("#detailHtml", window.parent.document).val();
				if(html!=""){
					$("#myDetailHtml").html(html);
				}
				if (CKEDITOR.env.ie && CKEDITOR.env.version < 9)
					CKEDITOR.tools.enableHtml5Elements(document);
				// The trick to keep the editor in the sample quite small
				// unless user specified own height.
				var editor = CKEDITOR.replace("myDetailHtml");
			</script>
</body>
</html>