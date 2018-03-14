<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 
	    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	    <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
     -->
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/commons.css">
	
	<script type="text/javascript">
		function requestMethod(url) {
			var form = document.getElementById("_f1");
			form.action = "main.do";
			form.submit();
		}
	</script>
	
  </head>
  
  <body>
  	<%=basePath %><br/>
  	<div>
		<form id="_f1" name="f1" class="mid" method="post">
			用户名:
			<input type="text" id="_username" name="username">
			<br />
			密 码:
			<input type="password" id="_pwd" name="pwd">
			<br />
			
			<input type="button" onclick="requestMethod('register');" value="注册"><br/>
			<input type="button" onclick="requestMethod('login');" value="登陆"><br/>
		</form>
  	</div>
  </body>
</html>
