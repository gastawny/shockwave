package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.entities.ValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepository extends JpaRepository<ValueEntity, Long> {
}
