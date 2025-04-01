package com.sanyam.curewell.curewell_backend.service.impl;

import com.sanyam.curewell.curewell_backend.entity.Doctor;
import com.sanyam.curewell.curewell_backend.entity.Specialization;
import com.sanyam.curewell.curewell_backend.exception.SpecializationNotFoundException;
import com.sanyam.curewell.curewell_backend.repository.SpecializationRepository;
import com.sanyam.curewell.curewell_backend.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class SpecializationServiceImpl implements SpecializationService {
    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization addSpecialization(Specialization specialization) {
        // Check if specialization with the same name already exists
        if (specializationRepository
                .existsBySpecializationName(specialization.getSpecializationName())
        ) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Specialization already exists"
            );
        }
        return specializationRepository.save(specialization);
    }

    @Override
    public void deleteSpecialization(String specializationCode) {
        if (!specializationRepository.existsById(specializationCode)) {
            throw new SpecializationNotFoundException(
                    "Specialization not found with code: " + specializationCode
            );
        }
        specializationRepository.deleteById(specializationCode);
    }

}
