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
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        var data = doctorService.getAllDoctors();
        return ResponseEntity.ok(data);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        var data = doctorService.addDoctor(doctor);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") Long doctorId,
                                               @RequestBody Doctor doctor){
        var data = doctorService.updateDoctorDetails(doctorId, doctor);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.noContent().build();
    }
}
