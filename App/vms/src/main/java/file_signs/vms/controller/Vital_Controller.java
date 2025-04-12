package file_signs.vms.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import file_signs.vms.model.entity.Vital;
import file_signs.vms.service.Vital_Service;

@RestController
@RequestMapping("/vital")
public class Vital_Controller {

    @Autowired
    private Vital_Service vitalService;

    // Get vital by Patient
    @GetMapping("/vital_patient")
    public ResponseEntity<List<Vital>> getVitalByPatient(
            @RequestParam(required = false) Long patient_id) {

        List<Vital> vital = vitalService.getVitalByPatient(patient_id);
        if (!vital.isEmpty()) {
            return ResponseEntity.ok(vital);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get vital by Id_vital
    @GetMapping("/vital_id/{vital_id}")
    public ResponseEntity<Vital> getVitalById(@PathVariable UUID vital_id) {
        return ResponseEntity.ok(vitalService.getVitalById(vital_id));
    }

    // Save vital 
    @PostMapping("/save_vital")
    public ResponseEntity<Vital> saveVital(@RequestBody Vital vital) {
        return ResponseEntity.ok(vitalService.saveVital(vital));
    }

    // Delete vital
    @DeleteMapping("/delete_vital/{vital_id}")
    public ResponseEntity<String> deleteVital(@PathVariable UUID vital_id) {
        vitalService.deleteVital(vital_id);
        return ResponseEntity.ok("Vital deleted successfully " + vital_id);
    }

    // Chat Vital
    @GetMapping("/chat_vital/{vital_id}")
    public String getchatVital(@PathVariable UUID vital_id) {
        return vitalService.chatVital(vital_id);
    }

}
