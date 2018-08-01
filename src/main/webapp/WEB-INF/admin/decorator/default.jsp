<!DOCTYPE html>
<html>
<head>
<base href="${ctx}/">
<title><sitemesh:write property='title'/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="admin/assets/easyui/1.5.5.1/themes/metro/easyui.css?2018">
<link rel="stylesheet" type="text/css" href="admin/assets/stylesheet/admin.css?2019">
<script type="text/javascript" src="admin/assets/easyui/1.5.5.1/jquery.min.js"></script>
<script type="text/javascript" src="admin/assets/easyui/1.5.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="admin/assets/easyui/1.5.5.1/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="admin/assets/javascript/admin.js"></script>
<sitemesh:write property='head'/></head>
<body><sitemesh:write property='body'/>
<script type="text/javascript">
//for view
//js.show_mask();

$.parser.onComplete = function(){
	//js.close_mask();
	//alert("abc");
	$(".easyui-layout").css("visibility","visible");
};
//for security
if(top==this){
	var path=window.location.pathname;
	var paths=["admin/dashboard"];
	if(path!="admin/"){
		var result=false;
		for(var i=0;i<paths.length;i++){
			if(path.indexOf(paths[i])>=0){
				result=true;
				break;
			}
		}
		if(!result){
			//top.location.href="/admin/";
		}
	}
}
</script>
</body>
</html>
