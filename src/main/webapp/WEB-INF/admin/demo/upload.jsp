<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="ueditor/1.4.3.1/ueditor.config.js"></script>
<script src="ueditor/1.4.3.1/ueditor.all.min.js"></script>
<script src="ueditor/1.4.3.1/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet" type="text/css" href="ueditor/1.4.3.1/third-party/webuploader/webuploader.css">
</head>
<body>
<div id="uploadeditor" style="display: none;"></div>
<p>
	<button type="button" class="webuploader-pick" onClick="upImage()">上传图片</button>
</p>
<p>
	<button type="button" class="webuploader-pick" onClick="upFiles()">上传文件</button>
</p>
<script>
//实例化编辑器
var uploadeditor = UE.getEditor('uploadeditor', {
	autoHeightEnabled : false
});
uploadeditor.ready(function() {
	uploadeditor.hide();//隐藏编辑器

	//监听图片上传
	uploadeditor.addListener('beforeInsertImage', function(t, arg) {
		alert('这是图片地址：' + arg[0].src);
	});

	/* 文件上传监听
	 * 需要在attachment.js文件中找到
	 *  editor.execCommand('insertfile', list);
	 * 之前插入editor.fireEvent('afterUpfile', list);
	 */
	uploadeditor.addListener('afterUpfile', function(t, arg) {
		alert('这是文件地址：' + arg[0].url);
	});
});

//弹出图片上传的对话框
function upImage() {
	var myImage = uploadeditor.getDialog("insertimage");
	myImage.open();
}
//弹出文件上传的对话框
function upFiles() {
	var myFiles = uploadeditor.getDialog("attachment");
	myFiles.open();
}
</script>
</body>
<html>