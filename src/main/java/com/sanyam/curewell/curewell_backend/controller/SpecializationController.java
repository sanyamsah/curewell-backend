package com.sanyam.curewell.curewell_backend.controller;

import com.sanyam.curewell.curewell_backend.entity.Specialization;
import com.sanyam.curewell.curewell_backend.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {
    @Autowired
    private SpecializationService specializationService;

    @GetMapping
    public ResponseEntity<List<Specialization>> getAllSpecializations() {
        var data = specializationService.getAllSpecializations();
        return ResponseEntity.ok(data);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Specialization> addSpecialization(
            @RequestBody Specialization specialization
    ) {
        var data = specializationService.addSpecialization(specialization);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{specializationCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSpecialization(
            @PathVariable("specializationCode") String specializationCode
    ) {
        specializationService.deleteSpecialization(specializationCode);
        return ResponseEntity.noContent().build();
    }
}
