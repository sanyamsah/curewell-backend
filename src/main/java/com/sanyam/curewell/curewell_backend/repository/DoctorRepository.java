package com.sanyam.curewell.curewell_backend.repository;

import com.sanyam.curewell.curewell_backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
