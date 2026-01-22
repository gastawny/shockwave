package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE NOT EXISTS (SELECT p FROM u.permissions p WHERE p.description = 'ADMIN')")
    List<User> findAllWithoutAdmin();
}
