package com.sanyam.curewell.curewell_backend.controller;

import com.sanyam.curewell.curewell_backend.entity.Surgery;
import com.sanyam.curewell.curewell_backend.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Surgery> getAllSurgeries() {
        return surgeryService.getAllSurgeries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Surgery> getSurgeryById(@PathVariable Long id) {
        Optional<Surgery> surgery = surgeryService.getSurgeryById(id);
        return surgery.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Surgery addSurgery(@RequestBody Surgery surgery) {
        return surgeryService.addSurgery(surgery);
    }

    @PutMapping("/{id}")
    public Surgery updateSurgery(@PathVariable Long id, @RequestBody Surgery surgery) {
        return surgeryService.updateSurgery(id, surgery);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurgery(@PathVariable Long id) {
        surgeryService.deleteSurgery(id);
        return ResponseEntity.noContent().build();
    }
}
