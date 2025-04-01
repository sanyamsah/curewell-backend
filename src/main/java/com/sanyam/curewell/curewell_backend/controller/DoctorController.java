package com.sanyam.curewell.curewell_backend.controller;

import com.sanyam.curewell.curewell_backend.entity.Doctor;
import com.sanyam.curewell.curewell_backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        var data = doctorService.getAllDoctors();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/specialization/{code}/doctors")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable String code){
        var data = doctorService.getDoctorsBySpecialization(code);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/doctors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        var data = doctorService.addDoctor(doctor);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/doctors/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") Long doctorId,
                                               @RequestBody Doctor doctor){
        var data = doctorService.updateDoctorDetails(doctorId, doctor);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/doctors/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.noContent().build();
    }
}
