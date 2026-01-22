package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.Parameter;

import java.util.List;

public interface ParameterRepository extends BaseRepository<Parameter, Long> {

    List<Parameter> findByTableNameLike(String table);
}
