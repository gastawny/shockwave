package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.entities.LocatedObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatedObjectRepository extends JpaRepository<LocatedObjectEntity, Long> {
}
