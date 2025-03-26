package com.sanyam.curewell.curewell_backend.repository;

import com.sanyam.curewell.curewell_backend.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<Specialization, String> {
    Optional<Specialization> findBySpecializationName(String specializationName);
    boolean existsBySpecializationName(String specializationName);
}
