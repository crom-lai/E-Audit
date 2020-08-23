<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Download App Page</title>
<script type="text/javascript">
	function autoDownloadApp(){
		console.log("Test");
		var links = document.getElementsByTagName("a");
		for(var i=0;i<links.length;i++){
	        var link = links[i].href;
	        window.location.href=link; 
	    }
	}
</script>
</head>
<body onload="autoDownloadApp()">
	<span><b>${appLink}</b></span>
</body>
</html>