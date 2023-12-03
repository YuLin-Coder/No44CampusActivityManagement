package com.my.xymh.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.my.xymh.utils.Pager;


/**
 * 基础dao封装一些简单的方法
 * @author 
 *
 */
public interface BaseDao<T>{
	/**  
     * 插入一个实体  
     * @param entity  
     */  
	int insert(T entity) ; 
	
	/**
	 * 自定义插入
	 * @param sqlId
	 * @param obj
	 */
	void insert(String sqlId, T obj);
      
    /**  
     * 根据实体主键删除一个实体  
     * @param primaryKey  
     */  
    void deleteById(Serializable id);  
      
    /**
     * 通过多个参数删除自定义
     * @param sqlId
     * @param params
     */
    public void deleteBySqId(String sqlId, Map<String, Object> params);
    
    
    /**  
     * 更新一个实体  
     * @param entity  
     */  
    void update(T entity);   
    
    /**
     * 根据参数查询
     * @param clz
     * @param params
     * @return
     */
    public List<T> list(Map<String, Object> params);
    
    /**
     * 自定义根据参数查询
     * @param sqlId
     * @param params
     * @return
     */
    public List<T> list(String sqlId, Map<String, Object> params);
    /**  
     * 查询所有实体  
     * @return  
     */  
    List<T> listAll();  
  
    /**  
     * 查询所有实体,根据实体属性值为判断条件查询所有实体，  
     * @param entity  
     * @return  
     */  
    List<T> listAllByEntity(T entity);  
      
    /**  
     * 根据主键获取一个实体  
     * @param primaryKey  
     * @return  
     */  
    T load(Serializable id);  
    
    /**
     *自定义查询
     * @param sqlId
     * @param params
     * @return
     */
    public T loadBySqlId(String sqlId, Map<String, Object> params);
    
    /**
     * 通过对象查询
     * @param sqlId
     * @param entity
     * @return
     */
    public T loadBySqlId(String sqlId, T entity);
    
    //=======================一下是分页方法================================
    /**
     * 默认 sqlId find是分页
     * @param sqlId
     * @param params
     * @return
     */
    public Pager<T> findByMap(Map<String, Object> params);
    
    /**
     * 自定义分页
     * @param params
     * @return
     */
    public Pager<T> findByMap(String sqlId, Map<String, Object> params);
    
    /**
     * 通过对象查询分页
     * @param entity
     * @return
     */
    public Pager<T> findByEntity(T entity);
    
    /**
     * 通过对象自定义查询分页
     * @param sqlId
     * @param entity
     * @return
     */
    public Pager<T> findByEntity(String sqlId, T entity);
    /**
     * 需要自己写count的分页
     * @param sqlId
     * @param params
     * @return
     */
    public Pager<T> findByCount(Map<String, Object> params);
    
    public Pager<T> findByCount(String sqlId, Map<String, Object> params);
}
