<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<base href="${ctx}/" />
	<title>用户控制面板</title>

	<!-- Global stylesheets -->
	<link rel="shortcut icon" href="assets/images/favicon.ico" />
	<link rel="apple-touch-icon" href="assets/images/logo_white.png" />
	<link href="assets/css/beter/fonts.googleapis.com.css" rel="stylesheet" type="text/css">
	<link href="assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
	<link href="assets/css/icons/fontawesome/styles.min.css" rel="stylesheet" type="text/css">
	<link href="assets/css/minified/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="assets/css/minified/core.min.css" rel="stylesheet" type="text/css">
	<link href="assets/css/minified/components.min.css" rel="stylesheet" type="text/css">
	<link href="assets/css/minified/colors.min.css" rel="stylesheet" type="text/css">
	<!-- /global stylesheets -->

	<!-- Core JS files -->
	<script type="text/javascript" src="assets/js/plugins/loaders/pace.min.js"></script>
	<script type="text/javascript" src="assets/js/core/libraries/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/core/libraries/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/js/plugins/loaders/blockui.min.js"></script>
	<!-- /core JS files -->

	<!-- Theme JS files -->
	<script type="text/javascript" src="assets/js/plugins/visualization/d3/d3.min.js"></script>
	<script type="text/javascript" src="assets/js/plugins/visualization/d3/d3_tooltip.js"></script>
	<script type="text/javascript" src="assets/js/plugins/forms/styling/switchery.min.js"></script>
	<script type="text/javascript" src="assets/js/plugins/forms/styling/uniform.min.js"></script>
	<script type="text/javascript" src="assets/js/plugins/forms/selects/bootstrap_multiselect.js"></script>
	<script type="text/javascript" src="assets/js/plugins/ui/moment/moment.min.js"></script>
	<script type="text/javascript" src="assets/js/plugins/pickers/daterangepicker.js"></script>
	
	<!-- /theme JS files -->
  	<sitemesh:write property='head'/>
</head>

<body>
	<!-- Main navbar -->
	<div class="navbar navbar-default header-highlight">
		<div class="navbar-header">
			<a class="navbar-brand" href="javascript:void(0);" style="color: white;">${company.name } </a>

			<ul class="nav navbar-nav visible-xs-block">
				<li><a data-toggle="collapse" data-target="#navbar-mobile"><i class="icon-tree5"></i></a></li>
				<li><a class="sidebar-mobile-main-toggle"><i class="icon-paragraph-justify3"></i></a></li>
			</ul>
		</div>

		<div class="navbar-collapse collapse" id="navbar-mobile">
			<ul class="nav navbar-nav">
				<li><a class="sidebar-control sidebar-main-toggle hidden-xs"><i class="icon-paragraph-justify3"></i></a></li>
			</ul>
			<p class="navbar-text"><span class="label "></span></p>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown dropdown-user">
					<a class="dropdown-toggle" data-toggle="dropdown">
						<img src="assets/images/user.png" alt="">
						<span><shiro:principal property="name"/>（<shiro:principal property="loginName"/>）</span>
						<i class="caret"></i>
					</a>

					<ul class="dropdown-menu dropdown-menu-right">
						<li><a href="user/logout"><i class="icon-switch2"></i> 退出</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	
	<!-- /main navbar -->


	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">

			<!-- Main sidebar -->
			<div class="sidebar sidebar-main">
				<div class="sidebar-content">

					<!-- User menu -->
					<div class="sidebar-user">
						<div class="category-content">
							<div class="media">
								<a href="#" class="media-left"><img src="assets/images/user.png" class="img-circle img-sm" alt=""></a>
								<div class="media-body">
									<span class="media-heading text-semibold"><shiro:principal property="name"/>（<shiro:principal property="loginName"/>）</span>
									<%-- <div class="text-size-mini text-muted">
									<c:if test="${userLogin.region==null }">
									<i class="icon-pin text-size-small"></i>
										${userLogin.ip}
									</c:if>
									<c:if test="${userLogin.region!=null }">
										<i class="icon-pin text-size-small"></i>
										${userLogin.region },${userLogin.city}
									</c:if>
									</div> --%>
								</div>
								<!-- <div class="media-right media-middle">
									<ul class="icons-list">
				                		<li class="dropdown">
				                			<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-cog3"></i> </a>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a href="user/logout"><i class="icon-switch2"></i> 退出</a></li>
											</ul>
				                		</li>
				                	</ul>
								</div> -->
							</div>
						</div>
					</div>
					<!-- /user menu -->

					<!-- Main navigation -->
					<div class="sidebar-category sidebar-category-visible">
						<div class="category-content no-padding">
							<ul class="navigation navigation-main navigation-accordion">
								<c:forEach var="menu" items="${menuList }">
									<c:if test="${menu.pid!=0 }">
										<c:choose>
											<c:when test="${servletPath==menu.url}">
												<li class="active"><a href="${menu.url }"><i class="${menu.icon }"></i> <span>${menu.name }</span></a></li>
											</c:when>
											<c:otherwise>
												<li><a href="${menu.url }"><i class="${menu.icon }"></i> <span>${menu.name }</span></a></li>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
					<!-- /main navigation -->

				</div>
			</div>
			<!-- /main sidebar -->


			<!-- Main content -->
			<div class="content-wrapper">
			<sitemesh:write property='body'/>
			</div>
			<!-- /main content -->

		</div>
		<!-- /page content -->

	</div>
	<!-- /page container -->
	<div style="display: none;">
		<!-- <script src="http://s11.cnzz.com/z_stat.php?id=1261291546&web_id=1261291546" language="JavaScript"></script> -->
	</div>
</body>
</html>


