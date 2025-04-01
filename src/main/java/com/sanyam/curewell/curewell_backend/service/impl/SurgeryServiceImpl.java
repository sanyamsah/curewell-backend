package com.sanyam.curewell.curewell_backend.service.impl;

import com.sanyam.curewell.curewell_backend.entity.Surgery;
import com.sanyam.curewell.curewell_backend.exception.DoctorNotFoundException;
import com.sanyam.curewell.curewell_backend.exception.SurgeryNotFoundException;
import com.sanyam.curewell.curewell_backend.repository.DoctorRepository;
import com.sanyam.curewell.curewell_backend.repository.SurgeryRepository;
import com.sanyam.curewell.curewell_backend.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurgeryServiceImpl implements SurgeryService {
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Surgery> getAllSurgeries() {
        return surgeryRepository.findAll();
    }

    @Override
    public List<Surgery> getSurgeriesByDoctorId(Long doctorId) {
        if(!doctorRepository.existsById(doctorId)){
            throw new DoctorNotFoundException(
                    "Doctor not found with ID: " + doctorId
            );
        }
        return surgeryRepository.findByDoctorId(doctorId);
    }

    @Override
    public Surgery getSurgeryById(Long surgeryId){
        return surgeryRepository.findById(surgeryId)
                .orElseThrow(() -> new SurgeryNotFoundException(
                        "Surgery not found with ID: " + surgeryId
                ));
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
