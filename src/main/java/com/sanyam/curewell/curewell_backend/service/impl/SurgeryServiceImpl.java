package com.sanyam.curewell.curewell_backend.service.impl;

import com.sanyam.curewell.curewell_backend.entity.Surgery;
import com.sanyam.curewell.curewell_backend.exception.SurgeryNotFoundException;
import com.sanyam.curewell.curewell_backend.repository.SurgeryRepository;
import com.sanyam.curewell.curewell_backend.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class SurgeryServiceImpl implements SurgeryService {
    @Autowired
    private SurgeryRepository surgeryRepository;

    @Override
    public List<Surgery> getAllSurgeries() {
        return surgeryRepository.findAll();
    }

    @Override
    public Optional<Surgery> getSurgeryById(Long surgeryId) {
        return surgeryRepository.findById(surgeryId);
    }

    @Override
    public Surgery addSurgery(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }

    @Override
    public Surgery updateSurgery(Long surgeryId, Surgery updatedSurgery) {
        return surgeryRepository.findById(surgeryId)
                .map(surgery -> {
                    surgery.setDoctorId(updatedSurgery.getDoctorId());
                    surgery.setSurgeryDate(updatedSurgery.getSurgeryDate());
                    surgery.setStartTime(updatedSurgery.getStartTime());
                    surgery.setEndTime(updatedSurgery.getEndTime());
                    surgery.setSurgeryCategory(updatedSurgery.getSurgeryCategory());
                    return surgeryRepository.save(surgery);
                }).orElseThrow(() -> new SurgeryNotFoundException(
                        "Surgery not found with ID: " + surgeryId)
                );
    }

    @Override
    public void deleteSurgery(Long surgeryId) {
        if (!surgeryRepository.existsById(surgeryId)) {
            throw new SurgeryNotFoundException(
                    "Surgery not found with ID: " + surgeryId
            );
        }
        surgeryRepository.deleteById(surgeryId);
    }
}
