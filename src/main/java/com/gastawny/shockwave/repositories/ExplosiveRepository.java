package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.Explosive;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ExplosiveRepository extends BaseRepository<Explosive, Long> {

    @Query(value = "SELECT ep.explosive_parameter_id, p.value_type, p.name, p.parameter_id,\n" +
            "    CASE\n" +
            "        WHEN p.value_type = 'STRING' THEN (SELECT v.value FROM value_str v WHERE v.value_id = ep.value_id)\n" +
            "        WHEN p.value_type = 'NUMBER' THEN (SELECT v.value FROM value_num v WHERE v.value_id = ep.value_id)\n" +
            "        WHEN p.value_type = 'TEXT' THEN (SELECT v.value FROM value_text v WHERE v.value_id = ep.value_id)\n" +
            "    END AS value\n" +
            "    FROM explosives e\n" +
            "    JOIN explosive_parameters ep ON e.explosive_id = ep.explosive_id\n" +
            "    JOIN parameters p ON p.parameter_id = ep.parameter_id\n" +
            "    WHERE e.explosive_id = ?1 AND e.deleted = false\n", nativeQuery = true)
    List<Map<String, Object>> findDataByExplosiveId(Long id);

    @Query(value = "SELECT \n" +
            "    CASE\n" +
            "        WHEN p.value_type = 'STRING' THEN (SELECT v.value FROM value_str v WHERE v.value_id = ep.value_id)\n" +
            "        WHEN p.value_type = 'NUMBER' THEN (SELECT v.value FROM value_num v WHERE v.value_id = ep.value_id)\n" +
            "        WHEN p.value_type = 'TEXT' THEN (SELECT v.value FROM value_text v WHERE v.value_id = ep.value_id)\n" +
            "    END AS value\n" +
            "    FROM explosives e\n" +
            "    JOIN explosive_parameters ep ON e.explosive_id = ep.explosive_id\n" +
            "    JOIN parameters p ON p.parameter_id = ep.parameter_id\n" +
            "    WHERE p.symbol = ?1 AND e.explosive_id = ?2 AND e.deleted = false\n" +
            "LIMIT 1", nativeQuery = true)
    Map<String, Object> findValueByParameterSymbol(String symbol, Long explosiveId);

    @Query("SELECT e FROM explosives e LEFT JOIN FETCH e.explosiveParameters WHERE e.id = :id")
    Explosive findByIdWithParameters(Long id);
}
