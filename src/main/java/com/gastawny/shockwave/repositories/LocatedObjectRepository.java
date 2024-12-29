package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.LocatedObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatedObjectRepository extends JpaRepository<LocatedObject, Long> {
}
