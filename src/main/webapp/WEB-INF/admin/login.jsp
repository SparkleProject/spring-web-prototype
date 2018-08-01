<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/admin/language/english/login.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<base href="${ctx }/"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/admin/assets/stylesheet/login.css" rel="stylesheet" type="text/css" />
<title>${head_title}</title>
<!--[if lte IE 8]>
<link href="admin/assets/stylesheet/login-ie8.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<link href="admin/assets/stylesheet/login-ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script src="${ctx }/admin/assets/easyui/1.5.4.4/jquery.min.js"></script>
<script src="${ctx }/admin/assets/javascript/jquery.placeholder.min.js"></script>
</head>
 <body>
 <div class="page" id="page"> 
   <div style="height: 100px;">
    &nbsp;
   </div> 
   <div class="main" id="main"> 
    <div class="inner"> 
     <div class="tab"> 
      <div class="panel"> 
       <div class="form"> 
        <h2 class="hd" id="LoginTitle">${text_login}</h2> 
        <form class="bd" action="admin/login" method="post" id="loginForm"> 
         <div class="ipt ipt-t user"> 
          <input type="text" placeholder="${entry_username}" id="username" name="username"    value="" tabindex="1" style="width: 317px;" /> 
         </div> 
         <div class="ipt ipt-t pass"> 
          <input type="password" placeholder="${entry_password}" name="password" value="" tabindex="2" /> 
          <button  class="btn btn-login" type="submit">Login</button> 
         </div> 
         <div class="ipt error"> 
             ${error}
         </div> 
        </form> 
       </div> 
      </div> 
     </div> 
    </div> 
   </div> 
  </div>
   
  <shiro:user>
  <script type="text/javascript">
	top.location.href="admin/";
	</script>
  </shiro:user>
  <script type="text/javascript">
    var form = document.getElementById('loginForm');
    //form.submit();
    //$('input').placeholder();
    $("#username").focus();
  </script>
  <script type="text/javascript">
   if(top!=this){
		top.location.href="admin/login";
   }
  </script>
 </body>
</html>