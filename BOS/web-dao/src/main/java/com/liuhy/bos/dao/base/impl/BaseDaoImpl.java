package com.liuhy.bos.dao.base.impl;

import com.liuhy.bos.dao.base.BaseDao;
import com.liuhy.bos.utils.PageBean;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class<T> entityClass;

    public BaseDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type type = genericSuperclass.getActualTypeArguments()[0];
        entityClass = (Class<T>) type.getClass();
    }

    // 默认按类型注入
    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    public List<T> findAll() {
        String hql = "FROM " + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    @Override
    public void executeUpdate(String namedQuery, Object... args) {
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().getNamedQuery(namedQuery);

        for(int i = 0; i < args.length; i++) {
            query.setParameter(i, args[i]);
        }

        query.executeUpdate();
    }

    /**
     * 通用分页查询方法
     */
    public void pageQuery(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        //总数据量----select count(*) from bc_staff
        //改变Hibernate框架发出的sql形式
        detachedCriteria.setProjection(Projections.rowCount());//select count(*) from bc_staff
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long total = list.get(0);
        pageBean.setTotal(total.intValue());//设置总数据量
        detachedCriteria.setProjection(null);//修改sql的形式为select * from ....
        //重置表和类的映射关系
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        //当前页展示的数据集合
        int firstResult = (currentPage - 1) * pageSize;
        int maxResults = pageSize;
        List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
        pageBean.setRows(rows);
    }

}
