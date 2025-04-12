package file_signs.vms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import file_signs.vms.model.entity.Patient;
import file_signs.vms.service.Patient_Service;

@RestController
@RequestMapping("/patient")
public class Patient_Controller {
    
    @Autowired 
    private Patient_Service patientService;

    // Get All Patient
    @GetMapping("/")
    public ResponseEntity<List<Patient>> getPatient(){
        List<Patient> patient = patientService.getPatient();
        return ResponseEntity.ok(patient);
    }

    // Get Patient by ID
    @GetMapping("/search_Patient/{patient_id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patient_id){
        Patient patient = patientService.getPatientById(patient_id);
        return ResponseEntity.ok(patient);
    }

    // Save Patient
    @PostMapping("/save_Patient")
    public ResponseEntity<Patient> savePatient(
        @RequestBody Patient patient,
        @RequestHeader("STAFF-ID") Long staff_id,
        @RequestHeader("STAFF-EMAIL") String email){
        Patient newPatient = patientService.savePatient(patient, staff_id, email);
        return ResponseEntity.ok(newPatient);
    }

    // Update Patient
    @PutMapping("/update_Patient")
    public ResponseEntity<Patient> updatePatient(
        @RequestBody Patient patient,
        @RequestHeader("STAFF-ID") Long staff_id,
        @RequestHeader("STAFF-EMAIL") String email){
        Patient updatePatient = patientService.updatePatient(patient, staff_id, email);
        return ResponseEntity.ok(updatePatient);
    }

    // Delete Patient
    @DeleteMapping("/delete_Patient/{patient_id}")
    public ResponseEntity<String> deletePatient(
        @PathVariable Long patient_id,
        @RequestHeader("STAFF-ID") Long staff_id,
        @RequestHeader("STAFF-EMAIL") String email){
        String message = patientService.deletePatient(patient_id, staff_id, email);
        return ResponseEntity.ok(message);
    }

}
