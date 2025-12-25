package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.ObjectFormatParameter;

import java.util.List;

public interface ObjectFormatParameterRepository extends BaseRepository<ObjectFormatParameter, Long> {

    List<ObjectFormatParameter> findByObjectFormatId(Long objectFormatId);
}
