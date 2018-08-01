package com.lintech.service.admin;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.NewsDao;
import com.lintech.entity.News;


@Service
public class NewsService {
    @Autowired
    private NewsDao newsDao;
    
    public void save(News news){
        newsDao.save(news);  
    }
    
    public void delete(String id){
    	newsDao.delete(id);
    }
    
    public void update(News news){
        newsDao.update(news); 
    }
    
    public News findOne(String id){
        return newsDao.findOne(id);   
    }
    
    public News findOneWithBLOBs(Integer id){
    	return newsDao.findOneWithBLOBs(id);   
    }
    
    public List<News> findAll(){
        return newsDao.findAll();
    }
    
    public List<News> findAll(Map<String, Object> params,Page page){
        if(params!=null){
            return newsDao.findAll(params,page);
        }
        return Collections.emptyList();
    }
    
	public List<News> findAllWithBLOBs(Map<String, Object> params,Page page){
    	if(params!=null){
    		return newsDao.findAllWithBLOBs(params,page);
    	}
    	return Collections.emptyList();
    }
}
