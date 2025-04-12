package file_signs.vms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.Validated;

import file_signs.vms.model.entity.Medical_rol;
import file_signs.vms.repository.MedicalRol_Repository;
import jakarta.transaction.*;
import jakarta.validation.Valid;

@Service
@Validated
@Transactional
public class MedicalRol_Service {

    @Autowired
    private MedicalRol_Repository medicalRolRepository;

    // Get Medical Rol
    public List<Medical_rol> getMedicalRol() {
        return medicalRolRepository.findAll();
    }

    // Get Medical Rol by ID
    public Medical_rol getMedicalRolById(Short rol_id) {
        return medicalRolRepository.findById(rol_id)
                .orElseThrow(() -> new RuntimeException("Medical Rol not found with ID: " + rol_id));
    }

    // Save Medical Rol
    @Transactional
    public Medical_rol insertMedicalRol(@Valid Medical_rol medicalRol) {

        if (medicalRolRepository.existsById(medicalRol.getRole_id())) {
            throw new RuntimeException("Role ID already exists");
        }

        return medicalRolRepository.save(medicalRol);
    }

    // Save Medical Rol List 
    @Transactional
    public List<Medical_rol> insertMedicalRolList(@Valid List<Medical_rol> medicalRol) {

        for (Medical_rol rol : medicalRol) {
            if (rol.getRole_id() == null) {
                throw new RuntimeException("Role ID cannot be null");
            }

            if (medicalRolRepository.existsById(rol.getRole_id())) {
                throw new RuntimeException("Role ID already exists: " + rol.getRole_id());
            }
        }

        return medicalRolRepository.saveAll(medicalRol);
    }
    
    // Update Medical Rol
    @Transactional
    public Medical_rol updateMedicalRol(Short rol_id, @Valid Medical_rol medicalRol) {
        Medical_rol medicalRolData = medicalRolRepository.findById(rol_id)
                .orElseThrow(() -> new RuntimeException("Medical Rol not found with ID: " + rol_id));

        medicalRolData.setRole_name(medicalRol.getRole_name());

        return medicalRolRepository.save(medicalRolData);
    }

    // Delete Medical Rol
    public void deleteMedicalRol(Short rol_id) {
        medicalRolRepository.deleteById(rol_id);
    }
}
