package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.FormulaDependency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormulaDependencyRepository extends JpaRepository<FormulaDependency, Long> {

//    @Query("")
//    List<Map<String, Object>> findData(Long referenceId);

    Optional<FormulaDependency> findByAlias(String alias);
}
