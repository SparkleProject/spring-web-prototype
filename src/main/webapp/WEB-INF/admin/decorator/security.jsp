<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title'/></title>
<sitemesh:write property='head'/></head>
<body><sitemesh:write property='body'/>
<script type="text/javascript">
if(top==this){
	var url=window.location.pathname;
	top.location.href="/admin/";
}
</script>
</body>
</html>
