package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
}
