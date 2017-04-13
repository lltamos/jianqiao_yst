package com.yst.web.utils;

import java.util.List;

import com.yst.web.model.PageModel;

public interface BaseDao {
	public void save(Object o);
	public void update(Object o);
	public <T> void delete(Class<T> clazz,int id);
	public <T> void delete(Object o);
	public <T> T load(Class<T> clazz,int id);
	public <T> T get(Class<T> clazz,int id);
	public <T> T get(Class<T> clazz,Long id);
	public <T> List<T> query(Class<T> clazz);
	public <T> List<T> findByPage(Class<T> clazz,Object... params);
	
	public <T> List<T> findOtherColumnByPage(Class<T> clazz, String column, Object value);
	public void saveOrUpdate(Object o);
	public void merge(Object o);
	public void flush();
	//查询某列LIKE返回list
	public <T> List<T> selectByColumnLike(Class<T> clazz,String column,Object value);
	//查找某列的某值返回一个对象
	public <T> T findByColumnValue(Class<T> clazz,String column,Object value);
	//查找某列的某值返回一个list
	public <T> List<T> selectByColumnValue(Class<T> clazz,String column,Object value);
	//获取某对象的数量
	public <T> int count(Class<T> clazz);
	public <T> List<T> selectByAppList(Class<T> clazz,String column,Object value);
	//分页查询带参数
	public List query(String hql,PageModel pm,Object... params );
	//分页查询带search
	public List query(String hql,PageModel pm,Class<?> oClass,Object... params );
	//查询单个对象
	public Object queryForObject(String hql,Object... params);
	public List search(Class oClass,PageModel pm);
	public void index();
	public <T> int deleteByColumnValue(Class<T> clazz, String column,Object value);
	//sql查询返回List
	public <T> List<T> selectSql(Class<?> clazz,String sql,Object... params);
}
