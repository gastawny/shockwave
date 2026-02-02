package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormulaRepository extends JpaRepository<Formula, Long> {

    List<Formula> findDistinctByTableTableName(String tableName);
}
