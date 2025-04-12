package file_signs.vms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.Validated;

import file_signs.vms.model.entity.Medical_staff;
import file_signs.vms.repository.MedicalStaff_Repository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Validated
@Transactional
public class MedicalStaff_Service {

    @Autowired
    private MedicalStaff_Repository medicalStaffRepository;

    // Get Medical Staff
    public List<Medical_staff> getMedicalStaffRol() {
        return medicalStaffRepository.getStaffRol();
    }

    // Get Medical Staff by ID or Email
    public Optional<Medical_staff> getMedicalStaffByIdOrEmail(Long staff_id, String email) {
        return medicalStaffRepository.getStaffByIdOrEmail(staff_id, email);
    }

    // Save Medical Staff
    @Transactional
    public Medical_staff saveMedicalStaff(@Valid Medical_staff medicalStaff) {

        if (medicalStaff.getStaff_id() == null) {
            throw new RuntimeException("Staff ID cannot be null");
        }

        if (medicalStaffRepository.existsById(medicalStaff.getStaff_id())) {
            throw new RuntimeException("Staff ID already exists");
        }

        return medicalStaffRepository.save(medicalStaff);
    }

    // Update Medical Staff
    @Transactional
    public Medical_staff updateMedicalStaff(@Valid Medical_staff medicalStaff) {

        if (medicalStaff.getStaff_id() == null) {
            throw new RuntimeException("Staff ID cannot be null");
        }

        if (!medicalStaffRepository.existsById(medicalStaff.getStaff_id())) {
            throw new RuntimeException("Staff ID does not exist");
        }
        
        return medicalStaffRepository.save(medicalStaff);
    }

    // Delete Medical Staff
    public String deleteMedicalStaff (Long staff_id) {

        if(medicalStaffRepository.existsById(staff_id)){
            medicalStaffRepository.deleteById(staff_id);
            return "Staff ID deleted successfully " + staff_id;
        }else{
            throw new RuntimeException("Staff ID does not exist");
        }

    }

}
