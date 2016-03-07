<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>原生态JS的Ajax请求</title>
<script type="text/javascript">
    window.onload = sendXmlHttpRequest("userController/xmlHttpRequest");
   
    var xmlHttpReq;
    
    //创建XMLHttpRequest
    function createXMLHttpRequest(){
    	if(window.ActiveXObject){  //是否为IE浏览器
    		xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    	}else{
    		xmlHttpReq = new XMLHttpRequest();
    	}
    }
    
    //发送xmlHttpRequest请求
    function sendXmlHttpRequest(url){
    	//创建XMLHttpRequest
    	createXMLHttpRequest();
    	//呼叫服务
    	xmlHttpReq.open("POST", url, true);
    	//回调函数
    	xmlHttpReq.onreadystatechange = xmlHttpReqSuccess;
    	//发送请求到服务器
    	xmlHttpReq.send(null);
    	
    	console.log(xmlHttpReq);
    }
    
    //回调函数
	function xmlHttpReqSuccess() {
	    //检测xmlHttpReq目前的状态  0：还没开始   1：读取中  2：已读取  3：咨询交换中  4：一切完成
		if (xmlHttpReq.readyState == 4) {
			//检测http协议的状态
			if (xmlHttpReq.status == 200) {
				alert(xmlHttpReq.responseText);
			} else {
				alert("请求异常！");
			}
		}
	}
</script>
</head>
<body>
    
</body>
</html>