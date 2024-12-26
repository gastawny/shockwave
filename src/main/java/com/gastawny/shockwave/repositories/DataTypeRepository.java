package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.entities.DataTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataTypeRepository extends JpaRepository<DataTypeEntity, Long> {
}
