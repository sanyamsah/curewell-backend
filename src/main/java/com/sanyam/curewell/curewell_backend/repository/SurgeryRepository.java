package com.sanyam.curewell.curewell_backend.repository;

import com.sanyam.curewell.curewell_backend.entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
    List<Surgery> findByDoctorId(Long doctorId);
}
