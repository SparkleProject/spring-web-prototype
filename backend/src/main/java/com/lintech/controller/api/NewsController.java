package com.lintech.controller.api;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;

import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.News;
import com.lintech.service.admin.NewsService;

@RestController
@RequestMapping("/api/admin/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public DataGrid<News> list(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "20") int rows) {
        Page<News> result = newsService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Integer id) {
        return newsService.findOneWithBLOBs(id);
    }

    @PostMapping
    public Messager create(@RequestBody News news) {
        news.setCreateDate(new Date());
        news.setCreator(SecurityUtil.getCurrentStaffId());
        newsService.save(news);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}")
    public Messager update(@PathVariable Integer id, @RequestBody News news) {
        news.setId(id);
        news.setModificator(SecurityUtil.getCurrentStaffId());
        newsService.update(news);
        return Messager.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        newsService.delete(String.valueOf(id));
        return Messager.SUCCESS;
    }
}
