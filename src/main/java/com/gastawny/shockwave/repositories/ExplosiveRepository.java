package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.Explosive;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ExplosiveRepository extends BaseRepository<Explosive, Long> {

    @Query(value = "SELECT d.data_id, d.data_type_id, dt.value_type, dt.name,\n" +
            "    CASE\n" +
            "        WHEN dt.value_type = 'STR' THEN (SELECT v.value FROM value_str v WHERE v.value_id = d.value_id)\n" +
            "        WHEN dt.value_type = 'NUM' THEN (SELECT v.value FROM value_num v WHERE v.value_id = d.value_id)\n" +
            "        WHEN dt.value_type = 'TEXT' THEN (SELECT v.value FROM value_text v WHERE v.value_id = d.value_id)\n" +
            "    END AS value\n" +
            "    FROM explosives e\n" +
            "    JOIN explosive_data_type edt ON e.explosive_id = edt.explosive_id\n" +
            "    JOIN data_types dt ON dt.data_type_id = edt.data_type_id AND dt.deleted = (0)\n" +
            "    JOIN datas d ON d.data_type_id = dt.data_type_id AND d.explosive_id = e.explosive_id AND d.deleted = false\n" +
            "    WHERE e.explosive_id = ?1 AND e.deleted = false\n" +
            "ORDER BY edt.sequence", nativeQuery = true)
    List<Map<String, Object>> findDataByExplosiveId(Long id);
}
