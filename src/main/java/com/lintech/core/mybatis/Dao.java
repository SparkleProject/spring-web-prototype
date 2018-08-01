package com.lintech.core.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 数据操作基础接口 
 * <p>For example：</p>
 * <code>public interface MyDao extends Dao&lt;MyEntity&gt;{// to do something}</code>
 * @param <T>
 */
public interface Dao<T>{
    
    /**
     * 保存
     * @param t
     */
    public int save(T t);
    
    /**
     * 批量保存
     * @param ts
     */
    public void saveList(List<T> ts);

    /**
     * 根据ID删除
     * @param id
     */
    public void delete(Serializable id);
    
    /**
     * 根据条件删除
     * @param params
     */
    public void deleteByClause(Map<String,Object> params);
    
    /**
     * 删除指定对象
     * @param t
     */
    public int delete(T t);
    
    /**
     * 删除全部（慎用）
     */
    public void truncate();
    
    /**
     * 更新
     * @param t
     */
    public int update(T t);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public T findOne(Serializable id);
    
    /**
     * 查询全部
     * @return
     */
    public List<T> findAll();
    
    /**
     * 查询全部
     * @return
     */
    public List<T> findAll(Page page);
    
    /**
     * 根据条件查询/根据条件分页查询
     * 分页时，params需包含<code>Page</code>类型对象
     * @param params
     * @return
     */
    public List<T> findAll(Map<String, Object> params);
    
    /**
     * 根据条件查询/根据条件分页查询
     * 分页时，params需包含<code>Page</code>类型对象
     * @param params
     * @return
     */
    public List<T> findAll(Map<String, Object> params,Page page);
    
    /**
     * 计算总数
     * @return
     */
    public Long countAll();
    
    /**
     * 根据条件计算总数
     * @param params
     * @return
     */
    public Long countAll(Map<String, Object> params);
    
}
