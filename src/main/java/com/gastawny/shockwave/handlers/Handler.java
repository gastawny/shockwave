package com.gastawny.shockwave.handlers;

import com.gastawny.shockwave.shared.GenericList;

import java.util.List;

public interface Handler<A> {

    String getType();
    Class<A> getEntityClass();
    Object getService();
    List<A> findAll();
    A findById(Long id);
    A save(Object entity);
    A update(A entity);
    void deleteById(Long id);
    List<GenericList> find2Select();
}
