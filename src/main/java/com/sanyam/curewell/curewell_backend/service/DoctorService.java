package com.sanyam.curewell.curewell_backend.service;

import com.sanyam.curewell.curewell_backend.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    List<Doctor> getDoctorsBySpecialization(String specializationCode);
    Doctor addDoctor(Doctor doctor);
    Doctor updateDoctorDetails(Long doctorId, Doctor updatedDoctor);
    void deleteDoctor(Long doctorId);
}
