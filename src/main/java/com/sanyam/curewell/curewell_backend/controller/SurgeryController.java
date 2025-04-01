package com.sanyam.curewell.curewell_backend.controller;

import com.sanyam.curewell.curewell_backend.entity.Surgery;
import com.sanyam.curewell.curewell_backend.service.SurgeryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/surgeries")
public class SurgeryController {

    @Autowired
    private SurgeryService surgeryService;

    @GetMapping
    public ResponseEntity<List<Surgery>> getAllSurgeries() {
        return ResponseEntity.ok(surgeryService.getAllSurgeries());
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Surgery>> getSurgeriesByDoctorId(
            @PathVariable Long doctorId
    ) {
        return ResponseEntity.ok(surgeryService.getSurgeriesByDoctorId(doctorId));
    }

    @GetMapping("/{surgeryId}")
    public ResponseEntity<Surgery> getSurgeryById(
            @PathVariable Long surgeryId
    ) {
        return ResponseEntity.ok(surgeryService.getSurgeryById(surgeryId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Surgery> addSurgery(
            @RequestBody Surgery surgery
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(surgeryService.addSurgery(surgery));
    }

    @PutMapping("/{surgeryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Surgery> updateSurgery(
            @PathVariable Long surgeryId,
            @RequestBody Surgery surgery
    ) {
        return ResponseEntity.ok(surgeryService.updateSurgery(surgeryId, surgery));
    }

    @DeleteMapping("/{surgeryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSurgery(
            @PathVariable Long surgeryId
    ) {
        surgeryService.deleteSurgery(surgeryId);
        return ResponseEntity.noContent().build();
    }
}
