package com.yst.web.utils;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.yst.web.model.PageModel;

public class BaseDaoImpl implements BaseDao {
	private static Log logger = LogFactory.getLog(BaseDaoImpl.class);
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Object o) {
		this.getSession().save(o);
	}

	@Override
	public void update(Object o) {
		this.getSession().update(o);
	}

	@Override
	public <T> void delete(Class<T> clazz, int id) {
		this.getSession().delete(this.load(clazz, id));
	}

	@Override
	public <T> void delete(Object o) {
		this.getSession().delete(o);
	}

	@Override
	public <T> T load(Class<T> clazz, int id) {
		return (T) this.getSession().load(clazz, id);
	}

	@Override
	public <T> T get(Class<T> clazz, int id) {
		return (T) this.getSession().get(clazz, id);
	}

	@Override
	public <T> List<T> query(Class<T> clazz) {
		return (List<T>) this.getSession().createCriteria(clazz).list();
	}

	@Override
	public void saveOrUpdate(Object o) {
		this.getSession().saveOrUpdate(o);
	}

	@Override
	public void merge(Object o) {
		this.getSession().merge(o);
	}

	// 带search需要分页
	@Override
	public List query(String hql, PageModel pm, Class<?> clazz,
			Object... params) {
		if (pm != null) {
			String search = pm.getSearch();
			if (search != null && !search.equals("")) {
				hql = this.search(hql, clazz, search);
			}
		}
		return this.query(hql, pm, params);
	}

	// 需要分页
	public List query(String hql, PageModel pm, Object... params) {
		if (pm != null) {
			Query query = this.getSession().createQuery(
					"select count(*) " + hql.substring(hql.indexOf("from")));
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			long rowCount = (Long) query.uniqueResult();
			pm.setRowCount((int) rowCount);
		}
		Query query = this.getSession().createQuery(hql);
		if (pm != null) {
			query.setFirstResult(PageModelContext.getPageModel()
					.getStartIndex());
			query.setMaxResults(PageModelContext.getPageModel().getPageSize());
		} 
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}

	public List queryPaginate(String hql) {
		return this.query(hql, PageModelContext.getPageModel(), null);
	}

	public List queryPaginate(String hql, Object... params) {
		return this.query(hql, PageModelContext.getPageModel(), params);
	}

	// 不需要分页
	public List query(String hql, Object... params) {
		return this.query(hql, null, params);
	}

	public List query(String hql) {
		return this.query(hql, null, null);
	}

	@Override
	public Object queryForObject(String hql, Object... params) {
		Query query = this.getSession().createQuery(hql).setMaxResults(1);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.uniqueResult();
	}

	public Object queryForObject(String hql) {
		return this.queryForObject(hql, null);
	}

	@Override
	public <T> List<T> selectByColumnLike(Class<T> clazz, String column,
			Object value) {
		return this
				.getSession()
				.createQuery(
						"from " + clazz.getSimpleName() + " as o where o."
								+ column + " like ?")
				.setParameter(0, "%" + value + "%").list();
	}

	@Override
	public <T> int deleteByColumnValue(Class<T> clazz, String column,
			Object value) {
		return this
				.getSession()
				.createQuery(
						"delete from " + clazz.getSimpleName()
								+ " as o where o." + column + "=:id")
				.setParameter("id", value).executeUpdate();
	}

	@Override
	public <T> T findByColumnValue(Class<T> clazz, String column, Object value) {
		// TODO Auto-generated method stub
		return (T) this
				.getSession()
				.createQuery(
						"from " + clazz.getSimpleName() + " as o where o."
								+ column + "=?").setParameter(0, value)
				.uniqueResult();
	}

	@Override
	public <T> int count(Class<T> clazz) {
		Query query = getSession().createQuery(
				"select count(*) from " + clazz.getName());
		return ((Long) query.iterate().next()).intValue();
	}

	@Override
	public <T> List<T> selectByColumnValue(Class<T> clazz, String column,
			Object value) {
		return (List<T>) this
				.getSession()
				.createQuery(
						"from " + clazz.getSimpleName() + " as o where o."
								+ column + "=?").setParameter(0, value).list();
	}

	@Override
	public <T> List<T> selectByAppList(Class<T> clazz, String column,
			Object value) {
		return (List<T>) this
				.getSession()
				.createQuery(
						"from " + clazz.getSimpleName()
								+ " as o where o.deleted!=1 and o." + column
								+ "=?").setParameter(0, value).list();
	}

	@Override
	public void flush() {
		this.getSession().flush();
		this.getSession().clear();
	}

	@Override
	public void index() {
		logger.debug("索引初始化开始......");
		FullTextSession fullTextSession = Search.getFullTextSession(this
				.getSession());
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("索引初始化结束......");
	}

	@Override
	public List search(Class oClass, PageModel pm) {
		FullTextSession fullTextSession = Search.getFullTextSession(this
				.getSession());
		// 使用builder
		String[] keywords = BeanUtils.getStringArray(oClass);
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(oClass).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword()
				.onFields(keywords).matching(pm.getSearch()).createQuery();
		logger.debug(pm.getSearch());
		// 执行检索，得到结果集
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(
				luceneQuery, oClass);
		fullTextQuery.setFirstResult(PageModelContext.getPageModel()
				.getStartIndex());
		fullTextQuery.setMaxResults(PageModelContext.getPageModel()
				.getPageSize());
		List list = fullTextQuery.list();
		// 查看结果做验证
		return list;
	}

	public String search(String hql, Class oClass, String search) {
		String[] keywords = BeanUtils.getStringArray(oClass);
		int i = 1;
		for (String string : keywords) {
			if (i == 1) {
				if (hql.indexOf("where") != -1) {
					hql += " and (o." + string + " like '%" + search + "%'";
				} else {
					hql += " where o." + string + " like '%" + search + "%'";
				}
			} else {
				hql += " or o." + string + " like '%" + search + "%'";
				if(keywords.length==i){
					hql+=")";
				}
			}
			i++;
		}
		return hql;
	}

	@Override
	public <T> List<T> findByPage(Class<T> clazz,Object... params) {
		String hql="from " + clazz.getSimpleName() +" as o order by o.createdTime desc";
		return query(hql, PageModelContext.getPageModel(), clazz, params);
	}

	@Override
	public <T> T get(Class<T> clazz, Long id) {
		return (T) this.getSession().get(clazz, id);
	}



	@Override
	public <T> List<T> findOtherColumnByPage(Class<T> clazz, String column,Object value) {
		String hql="from " + clazz.getSimpleName() +" as o where o."+column+"=? order by o.createdTime desc";
		return query(hql, PageModelContext.getPageModel(), clazz, value);
	}
	
	public <T> List<T> selectSql(Class<?> clazz,String sql,Object... params){
		Query query=this.getSession().createSQLQuery(sql).addEntity(clazz);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}
}
