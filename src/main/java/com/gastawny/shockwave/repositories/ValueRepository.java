package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepository extends JpaRepository<Value, Long> {
}
