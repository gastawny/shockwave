package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
