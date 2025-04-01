package com.sanyam.curewell.curewell_backend.service.impl;

import com.sanyam.curewell.curewell_backend.entity.Doctor;
import com.sanyam.curewell.curewell_backend.exception.DoctorNotFoundException;
import com.sanyam.curewell.curewell_backend.exception.SpecializationNotFoundException;
import com.sanyam.curewell.curewell_backend.repository.DoctorRepository;
import com.sanyam.curewell.curewell_backend.repository.SpecializationRepository;
import com.sanyam.curewell.curewell_backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specializationCode) {
        if(!specializationRepository.existsById(specializationCode)){
            throw new SpecializationNotFoundException(
                    "Specialization not found with code: " + specializationCode
            );
        }
        return doctorRepository.findBySpecializations_SpecializationCode(specializationCode);
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctorDetails(Long doctorId, Doctor updatedDoctor) {
        Doctor savedDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + doctorId));
        savedDoctor.setDoctorName(updatedDoctor.getDoctorName());
        savedDoctor.setImage(updatedDoctor.getImage());
        return doctorRepository.save(savedDoctor);
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + doctorId));
        doctorRepository.delete(doctor);
    }
}
