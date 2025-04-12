package file_signs.vms.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import file_signs.vms.model.entity.Assignment;
import file_signs.vms.repository.Assignment_Repository;
import file_signs.vms.repository.MedicalStaff_Repository;
import file_signs.vms.repository.Patient_Repository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class Assignment_Service {

    @Autowired
    private Assignment_Repository assignmentRepository;

    @Autowired
    private MedicalStaff_Repository medicalStaffRepository;

    @Autowired
    private Patient_Repository patientRepository;

    public List<Assignment> obtener(){
        List<Assignment> fecha = assignmentRepository.findAll();
        return fecha.stream()
            .sorted((a, b) -> a.getDate_assigned().compareTo(b.getDate_assigned()))
            .collect(Collectors.toList());
    }

    // Get all assignments 
    public List<Assignment> getAssignments() {
        return assignmentRepository.getallAssignments();
    }

    // Get assignment by Staff Medical
    public List <Assignment> getAssignmentByStaffMedical(Long staff_id, String email) {
        return assignmentRepository.getAssignmentByStaffMedical(staff_id, email);
    }

    // Get assignment by Patient
    public List <Assignment> getAssignmentByPatient(Long patient_id) {
        return assignmentRepository.getAssignmentByPatient(patient_id);
    }

    // Save assignment
    @Transactional
    public Assignment saveAssignment(@Valid Assignment assignment){

        if(assignment.getStaffId() == null || assignment.getStaffId().getStaff_id() == null){
            throw new RuntimeException("Staff ID cannot be null");
        }

        if(!medicalStaffRepository.existsById(assignment.getStaffId().getStaff_id())){
            throw new RuntimeException("Staff ID does not exist");
        }

        if(assignment.getPatientId() == null || assignment.getPatientId().getPatient_id() == null){
            throw new RuntimeException("Patient ID cannot be null");
        }

        if(!patientRepository.existsById(assignment.getPatientId().getPatient_id())){
            throw new RuntimeException("Patient ID does not exist");
        }

        return assignmentRepository.save(assignment);
    }

    // Delete assignment
    public void deleteAssignment(UUID assignment_id) {
        assignmentRepository.deleteById(assignment_id);
    }

}
