package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
