package com.gastawny.shockwave.actions;

import java.util.List;

public interface ActionHandler<A> {

    String getActionName();
    List<A> findAll();
    A findById();
    A save();
    A update();
    void deleteById();
}
