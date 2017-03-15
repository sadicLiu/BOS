package com.liuhy.bos.dao.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    void save(T entity);

    void update(T entity);

    void delete(T entity);

    T findById(Serializable id);

    List<T> findAll();

    void executeUpdate(String namedQuery, Object... args);

}
