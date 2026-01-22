package com.gastawny.shockwave.shared.handlers;

import com.gastawny.shockwave.shared.GenericList;

import java.util.List;
import java.util.Map;

public interface Handler<A> {

    String getType();
    Class<A> getEntityClass();
    Object getService();
    List<A> findAll();
    A findById(Long id);
    A save(Map<String, Object> entity);
    A update(Map<String, Object> entity);
    void deleteById(Long id);
    List<GenericList> find2Select();
}
