package file_signs.vms.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import file_signs.vms.model.entity.Assignment;
import file_signs.vms.service.Assignment_Service;

@RestController
@RequestMapping("/assignment")
public class Assignment_Controller {

    @Autowired
    private Assignment_Service assignmentService;

    @GetMapping("/orden")
    public List<Assignment> obtener(){
        return assignmentService.obtener();
    }

    // Get all assignments
    @GetMapping("/")
    public List<Assignment> getAssignments() {
        return assignmentService.getAssignments();
    }

    // Get assignment by Staff Medical
    @GetMapping("/assignment_Staff")
    public ResponseEntity<List<Assignment>> getAssignmentByStaffMedical(
        @RequestParam(required = false) Long staff_id,
        @RequestParam(required = false) String email){

            List<Assignment> assignment = assignmentService.getAssignmentByStaffMedical(staff_id, email);
            if (!assignment.isEmpty()) {
                return ResponseEntity.ok(assignment);
            } else {
                return ResponseEntity.notFound().build();
            }

        }

    // Get assignment by Patient
    @GetMapping("/assignment_Patient")
    public ResponseEntity<List<Assignment>> getAssignmentByPatient(
        @RequestParam(required = false) Long patient_id) {

            List<Assignment> assignment = assignmentService.getAssignmentByPatient(patient_id);
            if (!assignment.isEmpty()) {
                return ResponseEntity.ok(assignment);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    
    // Save assignment
    @PostMapping("/save_Assignment")
    public ResponseEntity<Assignment> saveAssignment(@RequestBody Assignment assignment) {
        return ResponseEntity.ok(assignmentService.saveAssignment(assignment));
    }

    // Delete assignment
    @DeleteMapping("/delete_Assignment/{assignment_id}")
    public ResponseEntity<String> deleteAssignment(@PathVariable UUID assignment_id) {
        assignmentService.deleteAssignment(assignment_id);
        return ResponseEntity.ok("Deleted successfully: " + assignment_id);
    }

}

