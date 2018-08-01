<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="assets/js/core/app.js"></script>
<script type="text/javascript">
function  passwordSubmit(){
	var pold=$("#passwordOld").val();
	var pnew=$("#passwordNew").val();
	var pconfirm=$("#passwordConfirm").val();
	if(pold==""){
	   alert("请输入旧密码");
	   $("#passwordOld").focus();
	   return;
	}else if(pnew==""){
	  alert("请输入New Password");
	  $("#passwordNew").focus();
	  return;
	}else if(pconfirm==""){
		  alert("请输入确认密码");
		  $("#pconfirm").focus();
		  return;
	}else if(pnew!=pconfirm){
		alert("New Password两次输入不匹配");
		$("#passwordNew").focus();
		return;
	}
	$.getJSON("/user/password/submit",{
		passwordOld:pold,
		passwordNew:pnew
	},function (data) {
	    if(data.success) {
	    	alert("修改成功！");
	    	window.location="/user/logout";
	    }else{
	    	$("#passwordOld").focus();
	    	alert(data.msg);
	    }
	});
}
</script>
</head>
<body>
	<div class="content">
	<!-- Form horizontal -->
					<div class="panel panel-flat">
						<div class="panel-heading">
							<h5 class="panel-title">修改密码</h5>
						</div>

						<div class="panel-body">
							<form class="form-horizontal" action="#">
								<fieldset class="content-group">
							<div class="form-group">
								<label class="control-label col-lg-2">旧密码</label>
								<div class="col-lg-2">
									<input  id="passwordOld" type="password" class="form-control">
									
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-lg-2">New Password</label>
								<div class="col-lg-2">
									<input  id="passwordNew" type="password" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-lg-2">确认New Password</label>
								<div class="col-lg-2">
									<input  id="passwordConfirm" type="password" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-lg-2"></label>
								<div class="col-lg-10"><button type="button" class="btn btn-primary" onclick="javascript:passwordSubmit();">提交 <i class="icon-arrow-right14 position-right"></i></button></div>
							</div>
					</fieldset>
				</form>
			</div>
			<!-- Footer -->
			<div class="footer text-muted">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&copy; 2018 <a href="${company.website }" target="_blank">${company.name }</a>
			</div>
			<!-- /footer -->
		</div>
	</div>
</body>
</html>