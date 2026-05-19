package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.NewsRepository;
import com.lintech.entity.News;


@Service
@Transactional(readOnly = true)
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Transactional
    public void save(News news){
        newsRepository.save(news);
    }

    @Transactional
    public void delete(String id){
        newsRepository.deleteById(Integer.parseInt(id));
    }

    @Transactional
    public void update(News news){
        newsRepository.save(news);
    }

    public News findOne(String id){
        return newsRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public News findOneWithBLOBs(Integer id){
        // With JPA, the full entity including LOBs is loaded by default
        return newsRepository.findById(id).orElse(null);
    }

    public List<News> findAll(){
        return newsRepository.findAll();
    }

    public Page<News> findAll(Pageable pageable){
        return newsRepository.findAll(pageable);
    }

    public Page<News> findAllWithBLOBs(Pageable pageable){
        // With JPA, the full entity including LOBs is loaded by default
        return newsRepository.findAll(pageable);
    }
}
