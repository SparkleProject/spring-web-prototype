package com.lintech.controller.admin;

import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;
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
        int index = ControllerUtils.getInt(request, "page", 1);
        int rows = ConfigUtil.getInt("pagesize");
        Pageable pageable = PageRequest.of(index - 1, rows);
        Page<News> result = newsService.findAll(pageable);
        DataGrid<News> datagrid = new DataGrid<News>(result.getContent(), (int) result.getTotalElements());
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
