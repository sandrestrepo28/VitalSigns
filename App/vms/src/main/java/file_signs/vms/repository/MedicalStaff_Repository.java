package file_signs.vms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import file_signs.vms.model.entity.Medical_staff;

public interface MedicalStaff_Repository extends JpaRepository<Medical_staff, Long>{
    
    //⬇️ Get all medical staff
    @Query(value = "SELECT mr.ROLE_NAME, ms.ROLE_ID, ms.STAFF_ID, ms.FIRST_NAME, LAST_NAME, ms.EMAIL FROM MEDICAL_STAFF ms JOIN MEDICAL_ROL mr ON mr.ROLE_ID = ms.ROLE_ID", nativeQuery = true)
    public List<Medical_staff> getStaffRol();

    //⬇️ Get medical staff by ID or Email
    @Query(value = "SELECT mr.ROLE_NAME, ms.ROLE_ID, ms.STAFF_ID, ms.FIRST_NAME, ms.LAST_NAME, ms.EMAIL FROM MEDICAL_STAFF ms JOIN MEDICAL_ROL mr ON mr.ROLE_ID = ms.ROLE_ID WHERE STAFF_ID = :staff_id OR EMAIL = :email", nativeQuery = true)
    Optional<Medical_staff> getStaffByIdOrEmail(@Param("staff_id") Long STAFF_ID, @Param("email") String EMAIL);

}
