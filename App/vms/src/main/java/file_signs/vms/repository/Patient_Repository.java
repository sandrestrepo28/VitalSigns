package file_signs.vms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import file_signs.vms.model.entity.Patient;

public interface Patient_Repository extends JpaRepository<Patient, Long>{
}
