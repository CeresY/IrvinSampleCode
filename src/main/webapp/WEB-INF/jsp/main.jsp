<%@ page language="java" import="java.util.*,corejava.model.Account" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	Account account = (Account)session.getAttribute("account");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href=css/commons.css">
	
  </head>
  
  <body>
  	<div class="mid">
    	姓名：<%=account.getUsername() %><br/>
    	密码：<%=account.getPwd() %><br/>
    	<a href="<%=basePath%>index.jsp">首页</a>
  	</div>
  	
  </body>
</html>
