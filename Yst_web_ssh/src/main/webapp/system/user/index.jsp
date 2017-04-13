<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
  <head>
    <%@ include file="/common/commonHead.jsp"%>
  </head>
  <script type="text/javascript">
  	function goList(){
  		window.location.href="user.action";
  	}
  	function goLogin(){
  		window.location.href="system/user/login.jsp";
  	}
  </script>
  <body>
    查询：<br>
    <form action="user!search" method="post" id="s1">
    名称：<input type="text" name="login_name" /><input type="submit" value="搜索"/>
     <input type="button" value="查询所有人员列表" onclick="goList()"/>
     <input type="button" value="登录页面" onclick="goLogin()" />
    </form>
  </body>
</html>
