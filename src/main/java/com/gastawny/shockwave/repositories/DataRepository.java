package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.entities.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<DataEntity, Long> {
}
