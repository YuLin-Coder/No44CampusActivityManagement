package com.my.xymh.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.my.xymh.pagehelper.PageHelper;
import com.my.xymh.utils.Pager;
import com.my.xymh.utils.SystemContext;

/**
 * 通用实现类
 * @author 
 *
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	   //注入SqlSessionTemplate实例
		@Resource(name="sqlSessionTemplate")
	    private SqlSession sqlSession;
		
		/**
		 * 获得当前事物的session
		 */
		public SqlSession getSqlSession(){
			return this.sqlSession;
		}
		public void setSqlSession(SqlSessionTemplate sqlSession) {       
		    this.sqlSession = sqlSession;      
	    } 		
		
	
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;
	
	public Class<?> getClz() {
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}
	
	@Override
	public int insert(T entity) {
		return this.getSqlSession().insert(getClz().getName()+".insert",entity);
		
	}
	@Override
	public void insert(String sqlId, T obj) {
		this.getSqlSession().insert(getClz().getName()+"."+sqlId,obj);
	}
	@Override
	public void deleteById(Serializable id) {
		this.getSqlSession().delete(getClz().getName()+".delete", id);
		
	}
	@Override
	public void deleteBySqId(String sqlId, Map<String, Object> params) {
		this.getSqlSession().delete(getClz().getName()+"."+sqlId, params);
		
	}
	@Override
	public void update(T entity) {
		this.getSqlSession().update(getClz().getName()+".update", entity);
		
	}
	@Override
	public List<T> list(Map<String, Object> params) {
		return this.getSqlSession().selectList(getClz().getName()+".list", params);
	}
	@Override
	public List<T> list(String sqlId, Map<String, Object> params) {
		return this.getSqlSession().selectList(getClz().getName()+"."+sqlId, params);
	}
	@Override
	public List<T> listAll() {
		return this.getSqlSession().selectList(getClz().getName()+".listAll");
	}
	@Override
	public List<T> listAllByEntity(T entity) {
		return this.getSqlSession().selectList(getClz().getName()+".listAllByEntity", entity);
	}
	@Override
	public T load(Serializable id) {
		return (T)this.getSqlSession().selectOne(getClz().getName()+".load",id);
	}
	@Override
	public T loadBySqlId(String sqlId, Map<String, Object> params) {
		return (T)this.getSqlSession().selectOne(getClz().getName()+"."+sqlId,params);
	}
	@Override
	public T loadBySqlId(String sqlId, T entity) {
		return (T)this.getSqlSession().selectOne(getClz().getName()+"."+sqlId,entity);
	}
	/**
	 * 分页
	 */
	@Override
	public Pager<T> findByMap(Map<String, Object> params) {
		return findByMap(getClz().getName()+".findByMap", params);
	}
	
	/**
	 * 分页
	 */
	@Override
	public Pager<T> findByMap(String sqlId, Map<String, Object> params) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset = 0;
		if(pageSize==null||pageSize<0) pageSize = 15;
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		PageHelper.startPage(pageOffset, pageSize);
		List<T> datas = this.getSqlSession().selectList(sqlId, params);
		Pager<T> pages = new Pager<T>(datas);
		return pages;
	}
	
	@Override
	public Pager<T> findByEntity(T entity) {
		return findByEntity(getClz().getName()+".findByEntity", entity);
	}
	@Override
	public Pager<T> findByEntity(String sqlId, T entity) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset = 0;
		if(pageSize==null||pageSize<0) pageSize = 15;
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Integer index = null;
		if(pageOffset == 0){
			index = 1;
		}else{
			index = pageOffset/pageSize+1;
		}
		PageHelper.startPage(pageOffset, pageSize);
		List<T> datas = this.getSqlSession().selectList(sqlId, entity);
		Pager<T> pages = new Pager<T>(datas);
		return pages;
	}
	
	@Override
	public Pager<T> findByCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return findByCount(getClz().getName()+".find",params);
	}
	@Override
	public Pager<T> findByCount(String sqlId, Map<String, Object> params) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset = 0;
		if(pageSize==null||pageSize<0) pageSize = 15;
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Pager<T> pages = new Pager<T>();
		SqlSession session = null;
		if(params==null) params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		params.put("sort", sort);
		params.put("order", order);
		List<T> datas = session.selectList(sqlId, params);
		pages.setDatas(datas);
		pages.setOffset(pageOffset);
		pages.setSize(pageSize);
		int totalRecord = this.getSqlSession().selectOne(sqlId+"_count",params);
		pages.setTotal(totalRecord);
		return pages;
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
