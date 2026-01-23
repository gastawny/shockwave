package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.Parameter;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParameterRepository extends BaseRepository<Parameter, Long> {

    List<Parameter> findByTableName(String table);
}
