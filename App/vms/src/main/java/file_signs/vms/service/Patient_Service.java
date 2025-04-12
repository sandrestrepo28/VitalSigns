package file_signs.vms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import file_signs.vms.model.entity.*;
import file_signs.vms.repository.Patient_Repository;
import jakarta.transaction.Transactional;

@Transactional
@Validated
@Service
public class Patient_Service {

    @Autowired
    private Patient_Repository patientRepository;

    @Autowired
    private MedicalStaff_Service medicalStaffService;

    // Get All Patient
    public List<Patient> getPatient() {
        return patientRepository.findAll();
    }

    // Get Patient by ID
    public Patient getPatientById(Long patient_id) {
        return patientRepository.findById(patient_id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patient_id));
    }

    // Save Patient
    @Transactional
    public Patient savePatient(Patient patient, Long staff_id, String email) {

        if (patient.getPatient_id() == null) {
            throw new RuntimeException("Patient ID cannot be null");
        }

        if (patientRepository.existsById(patient.getPatient_id())) {
            throw new RuntimeException("Patient ID already exists");
        }

        Medical_staff authUser = medicalStaffService
        .getMedicalStaffByIdOrEmail(staff_id, email)
        .orElseThrow(()-> new RuntimeException("Medical Staff not found"));

        int roleId = authUser.getRol_id().getRole_id();
        if (roleId != 1 && roleId != 2 && roleId != 3) {
            throw new RuntimeException("Medical Staff not authorized");
        }

        return patientRepository.save(patient);
    }

    // Update Patient
    @Transactional
    public Patient updatePatient(Patient patient, Long staff_id, String email) {

        Medical_staff authUser = medicalStaffService
        .getMedicalStaffByIdOrEmail(staff_id, email)
        .orElseThrow(()-> new RuntimeException("Medical Staff not found"));

        int roleId = authUser.getRol_id().getRole_id();
        if (roleId != 1 && roleId != 2) {
            throw new RuntimeException("Medical Staff not authorized");
        }

        if (patient.getPatient_id() == null) {
            throw new RuntimeException("Patient ID cannot be null");
        }

        return patientRepository.save(patient);
    }

    // Delete Patient
    public String deletePatient(Long patient_id, Long staff_id, String email) {

        Medical_staff authUser = medicalStaffService
        .getMedicalStaffByIdOrEmail(staff_id, email)
        .orElseThrow(()-> new RuntimeException("Medical Staff not found"));

        int roleId = authUser.getRol_id().getRole_id();
        if (roleId != 1 && roleId != 2) {
            throw new RuntimeException("Medical Staff not authorized");
        }

        if(patientRepository.existsById(patient_id)){
            patientRepository.deleteById(patient_id);
            return "Patient ID deleted successfully " + patient_id;
        }else{
            throw new RuntimeException("Patient ID does not exist");
        }

    }

}
