package com.codelean.responsitory;

import com.codelean.model.Post;

import java.util.List;

public interface Responsitory<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T model);

    void remove(T id);

    void update(Long id,T model);
}
