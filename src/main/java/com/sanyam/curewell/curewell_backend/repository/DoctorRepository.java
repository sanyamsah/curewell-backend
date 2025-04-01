package com.sanyam.curewell.curewell_backend.repository;

import com.sanyam.curewell.curewell_backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecializations_SpecializationCode(String specializationCode);
}
