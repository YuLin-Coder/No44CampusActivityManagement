package com.my.xymh.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.xymh.utils.Pager;

public abstract  class BaseServiceImpl<T> implements BaseService<T>{

    private BaseDao<T> baseDao;  
    public abstract BaseDao<T> getBaseDao();  
	@Override
	public int insert(T entity) {
		return this.getBaseDao().insert(entity);
	}
	@Override
	public void insert(String sqlId, T obj) {
		this.getBaseDao().insert(sqlId, obj);
	}
	@Override
	public void deleteById(Serializable id) {
		this.getBaseDao().deleteById(id);
	}
	@Override
	public void deleteBySqId(String sqlId, Map<String, Object> params) {
		this.getBaseDao().deleteBySqId(sqlId, params);
	}
	@Override
	public void update(T entity) {
		this.getBaseDao().update(entity);
	}
	@Override
	public List<T> list(Map<String, Object> params) {
		return this.getBaseDao().list(params);
	}
	@Override
	public List<T> list(String sqlId, Map<String, Object> params) {
		return this.getBaseDao().list(sqlId, params);
	}
	@Override
	public List<T> listAll() {
		return this.getBaseDao().listAll();
	}
	@Override
	public List<T> listAllByEntity(T entity) {
		return this.getBaseDao().listAllByEntity(entity);
	}
	@Override
	public T load(Serializable id) {
		return this.getBaseDao().load(id);
	}
	@Override
	public T loadBySqlId(String sqlId, Map<String, Object> params) {
		return this.getBaseDao().loadBySqlId(sqlId, params);
	}
	@Override
	public T loadBySqlId(String sqlId, T entity) {
		return this.getBaseDao().loadBySqlId(sqlId, entity);
	}
	@Override
	public Pager<T> findByMap(Map<String, Object> params) {
		return this.getBaseDao().findByMap(params);
	}
	@Override
	public Pager<T> findByMap(String sqlId, Map<String, Object> params) {
		return this.getBaseDao().findByMap(sqlId, params);
	}  
	/**
     * 通过对象查询分页
     * @param entity
     * @return
     */
    public Pager<T> findByEntity(T entity){
    	return this.getBaseDao().findByEntity(entity);
    }
    
    /**
     * 通过对象自定义查询分页
     * @param sqlId
     * @param entity
     * @return
     */
    public Pager<T> findByEntity(String sqlId,T entity){
    	return this.getBaseDao().findByEntity(sqlId,entity);
    }
	@Override
	public Pager<T> findByCount(Map<String, Object> params) {
		return this.getBaseDao().findByCount(params);
	}
	@Override
	public Pager<T> findByCount(String sqlId, Map<String, Object> params) {
		return this.getBaseDao().findByCount(sqlId, params);
	}

	
	 //判断空
	public boolean isEmpty(String str) {
		return (null == str) || (str.trim().length() <= 0);
	}

	public boolean isEmpty(Character cha) {
		return (null == cha) || cha.equals(' ');
	}

	public boolean isEmpty(Object obj) {
		return (null == obj);
	}

	public boolean isEmpty(Object[] objs) {
		return (null == objs) || (objs.length <= 0);
	}

	public boolean isEmpty(Collection<?> obj) {
		return (null == obj) || obj.isEmpty();
	}

	public boolean isEmpty(Set<?> set) {
		return (null == set) || set.isEmpty();
	}

	public boolean isEmpty(Serializable obj) {
		return null == obj;
	}

	public boolean isEmpty(Map<?, ?> map) {
		return (null == map) || map.isEmpty();
	}
}
