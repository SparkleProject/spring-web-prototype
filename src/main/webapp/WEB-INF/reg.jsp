<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户登录</title>
	<script>
	$(document).ready(function() {
	    $('.ui.form').form({
	        fields: {
	        	username: {
	                identifier: 'username',
	                rules: [{
	                    type: 'empty',
	                    prompt: '请输入用户名'
	                }]
	            },
	            password: {
	                identifier: 'password',
	                rules: [{
	                    type: 'empty',
	                    prompt: '请输入密码'
	                },
	                {
	                    type: 'length[5]',
	                    prompt: '密码至少5位数'
	                }]
	            }
	        }
	    });
	});
	</script>
</head>
<body>
<div class="ui vertical segment four column middle aligned very relaxed stackable grid" style="min-height: 360px;">
  <div class="column">
  </div>
  <div class="column">
  <form class="ui large form" action="${ctx}/login" method="post">
      <div class="field">
        <label>用户名</label>
        <div class="ui left icon input">
          <input type="text" name="username" value="admin"  placeholder="User Name">
          <i class="user icon"></i>
        </div>
      </div>
      <div class="field">
        <label>密码</label>
        <div class="ui left icon input">
          <input type="password" name="password"  value="admin"  placeholder="密码">
          <i class="lock icon"></i>
        </div>
      </div>
      <div class="ui blue large submit button">注册</div>
    </form>
  </div>
</div>
</body>
</html>
