package com.sanyam.curewell.curewell_backend.service;


import com.sanyam.curewell.curewell_backend.entity.Doctor;
import com.sanyam.curewell.curewell_backend.entity.Specialization;

import java.util.List;

public interface SpecializationService {
    List<Specialization> getAllSpecializations();
    Specialization addSpecialization(Specialization doctor);
    void deleteSpecialization(String specializationCode);
}
