package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.ParameterDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterDependencyRepository extends JpaRepository<ParameterDependency, Long> {
}
