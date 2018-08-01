package com.lintech.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class CKEditorController{
    Log log=LogFactory.getLog(getClass());
    private  boolean CKEditor_uploadIsEnable = true;
    private  String CKEditor_uploadDir="/static/upload/editor/";
    private  String CKEditor_uploadDirFormat = "yyyy-MM";
    private final String CKEditor_uploadFileExt="rar|zip|docx|doc|xlsx|xls|txt";
    private final String CKEditor_uploadImageExt="jpg|jpeg|gif|png|bmp|psd";
    private final String CKEditor_uploadFlashExt="swf|fla|flv";
    private final String UPLOAD_TYPE_FILE="file";
    private final String UPLOAD_TYPE_IMAGE="image";
    private final String UPLOAD_TYPE_FLASH="flash";

    @RequestMapping("/editor/upload/{type}")
    public void upload(
            @PathVariable String type,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (type == null) {
            type = UPLOAD_TYPE_FILE;
        }
        String editorFuncNum=request.getParameter("CKEditorFuncNum");
        if (CKEditor_uploadIsEnable) {
            MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
            MultipartFile multiFile = multipart.getFile("upload");
            String originalFilename = multiFile.getOriginalFilename();
            log.debug("Upload file name:"+originalFilename);
            int pos = originalFilename.lastIndexOf(".");
            String ext = originalFilename.substring(pos);
            String extWithoutPoiont = ext.substring(1);
            log.debug("Upload file extension:"+extWithoutPoiont);
            if (isAllowedExtenstion(extWithoutPoiont,type)) {
                String saveFileName = String.valueOf(new Date().getTime());
                SimpleDateFormat sdf = new SimpleDateFormat(CKEditor_uploadDirFormat);
                String dateDir = sdf.format(new Date());
                saveFileName = CKEditor_uploadDir + type + "/" + dateDir + "/" + saveFileName + ext;
                String saveFilePath = request.getSession().getServletContext().getRealPath(saveFileName);
                File file = new File(saveFilePath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                multiFile.transferTo(file);
                log.debug("CKEditor upload file:" + saveFileName);
                String jscon="window.parent.CKEDITOR.tools.callFunction(" + editorFuncNum+ ",'" + saveFileName + "',''" + ")";
                responseJS(response, jscon);
                
            } else {
                  log.debug("Invalid file extension.");
                  editorFuncNum = request.getParameter("CKEditorFuncNum");
                  String jscon="window.parent.CKEDITOR.tools.callFunction(" + editorFuncNum+ ",'" + "" + "','文件类型不支持！'" + ")";
                  responseJS(response, jscon);
            }
        } else {
            log.debug("CKEditor uploadding is disabled!");
          editorFuncNum = request.getParameter("CKEditorFuncNum");
          String jscon="window.parent.CKEDITOR.tools.callFunction(" + editorFuncNum+ ",'" + "" + "','CKEditor未开启上传功能,请与管理员联系！'" + ")";
          responseJS(response, jscon);
        }
    }

    /**
     * 检查是否为 '允许后缀名'
     * @param extension
     * @param uploadType 
     * @return
     */
    private boolean isAllowedExtenstion(String extension,String type) {
        String allowedExtension="";
        if(UPLOAD_TYPE_IMAGE.equals(type)){
            allowedExtension=CKEditor_uploadImageExt;
        }else if(UPLOAD_TYPE_FLASH.equals(type)){
            allowedExtension=CKEditor_uploadFlashExt;
        }else{
            allowedExtension=CKEditor_uploadFileExt;
        }
        int e = allowedExtension.indexOf(extension);
        if (e > -1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 输出javascript
     * @param response
     * @param con 脚本主体内容
     */
    private void responseJS(HttpServletResponse response,String jscon){
        StringBuffer buff=new StringBuffer();
        buff.append("<script type='text/javascript'>")
        .append(jscon)
        .append("</script>");
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Cache-Control","no-cache");
            response.getWriter().print(buff.toString());
        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
}
