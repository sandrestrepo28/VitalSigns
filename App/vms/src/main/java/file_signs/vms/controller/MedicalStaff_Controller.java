package file_signs.vms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import file_signs.vms.model.entity.Medical_staff;
import file_signs.vms.service.MedicalStaff_Service;

@RestController
@RequestMapping("/medical_staff")
public class MedicalStaff_Controller {

    @Autowired
    private MedicalStaff_Service medicalStaffService;

    // Get medical staff
    @GetMapping("/")
    public List<Medical_staff> getMedicalStaff() {
        return medicalStaffService.getMedicalStaffRol();
    }

    // Get medical staff by ID or Email
    @GetMapping("/search_Staff")
    public ResponseEntity<Medical_staff> getStaffByIdOrEmail(
        @RequestParam(value = "staff_id", required = false) Long staff_id,
        @RequestParam(value = "email", required = false) String email) {

        Optional<Medical_staff> medicalStaff = medicalStaffService.getMedicalStaffByIdOrEmail(staff_id, email);
        if (medicalStaff.isPresent()) {
            return ResponseEntity.ok(medicalStaff.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Save medical staff
    @PostMapping("/save_Staff")
    public ResponseEntity<Medical_staff> saveMedicalStaff(@RequestBody Medical_staff medicalStaff) {
        return ResponseEntity.ok(medicalStaffService.saveMedicalStaff(medicalStaff));
    }

    // Update medical staff
    @PutMapping("/update_Staff")
    public ResponseEntity<Medical_staff> updateMedicalStaff(@RequestBody Medical_staff medicalStaff) {
        return ResponseEntity.ok(medicalStaffService.updateMedicalStaff(medicalStaff));
    }

    // Delete medical staff
    @DeleteMapping("/delete_Staff/{staff_id}")
    public ResponseEntity<String> deleteMedicalStaff(@PathVariable Long staff_id) {
        
        String message = medicalStaffService.deleteMedicalStaff(staff_id);

        if (message.contains("Deleted successfully")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.badRequest().body(message);
            
        }

    }

}


