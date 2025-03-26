package com.sanyam.curewell.curewell_backend.service;

import com.sanyam.curewell.curewell_backend.entity.Surgery;

import java.util.List;
import java.util.Optional;

public interface SurgeryService {
    List<Surgery> getAllSurgeries();
    Optional<Surgery> getSurgeryById(Long surgeryId);
    Surgery addSurgery(Surgery surgery);
    Surgery updateSurgery(Long surgeryId, Surgery surgery);
    void deleteSurgery(Long surgeryId);
}
