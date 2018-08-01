package com.lintech.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;
import com.lintech.core.mybatis.Page;
import com.lintech.core.util.ConfigUtil;
import com.lintech.core.util.ControllerUtils;
import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.News;
import com.lintech.service.admin.NewsService;

@Controller("AdminNewsController")
@RequestMapping("/admin/news")
public class NewsController{
    @Autowired
    NewsService newsService;
    
    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        String title=ControllerUtils.getString(request, "title");
        int index=ControllerUtils.getInt(request, "page",1);
        Page page=new Page(index,ConfigUtil.getInt("pagesize"));
        params.put("title", title);
        List<News> newsList=newsService.findAll(params,page);
        DataGrid<News> datagrid=new DataGrid<News>(newsList,page.getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/{id}")
    public Object load(@PathVariable Integer id){
        if(id==null)
            return "";
        return newsService.findOneWithBLOBs(id);
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            newsService.delete(id);
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(News news){
        if(news.getId()==null){
        	news.setCreateDate(new Date());
        	news.setCreator(SecurityUtil.getCurrentStaffId());
            newsService.save(news);
            return Messager.SUCCESS;
        }else{
        	news.setModificator(SecurityUtil.getCurrentStaffId());
            newsService.update(news);
            return Messager.SUCCESS;
        }
    }
}
