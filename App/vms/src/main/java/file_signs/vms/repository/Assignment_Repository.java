package file_signs.vms.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import file_signs.vms.model.entity.Assignment;

public interface Assignment_Repository extends JpaRepository<Assignment, UUID>{

    //⬇️ Get all assignments
    @Query(value= """
        SELECT 
            a.ASSIGNMENT_ID,
            mr.ROLE_NAME,
            a.STAFF_ID,
            ms.FIRST_NAME, 
            ms.LAST_NAME,
            a.PATIENT_ID,
            p.FIRST_NAME,
            p.LAST_NAME,
            a.DATE_ASSIGNED
        FROM 
            MEDICAL_STAFF ms
        JOIN
            MEDICAL_ROL mr ON ms.ROLE_ID = mr.ROLE_ID
        JOIN
            ASSIGNMENT a ON ms.STAFF_ID = a.STAFF_ID
        JOIN 
            PATIENTS p ON a.PATIENT_ID =p.PATIENT_ID""", nativeQuery = true)
    public List<Assignment> getallAssignments();

    //⬇️ Get assignment by Staff Medical 
    @Query(value="""
        SELECT 
            a.ASSIGNMENT_ID,
            mr.ROLE_NAME,
            a.STAFF_ID,
            ms.FIRST_NAME, 
            ms.LAST_NAME,
            a.PATIENT_ID,
            p.FIRST_NAME,
            p.LAST_NAME,
            a.DATE_ASSIGNED
        FROM 
            MEDICAL_STAFF ms
        JOIN
            MEDICAL_ROL mr ON ms.ROLE_ID = mr.ROLE_ID
        JOIN
            ASSIGNMENT a ON ms.STAFF_ID = a.STAFF_ID
        JOIN 
            PATIENTS p ON a.PATIENT_ID =p.PATIENT_ID
        WHERE 
            a.STAFF_ID = :staff_id OR ms.EMAIL = :email""", nativeQuery = true)
        List<Assignment> getAssignmentByStaffMedical(@Param("staff_id") Long STAFF_ID, @Param("email") String EMAIL);

    // ⬇️ Get assignment by Patient
    @Query(value= """
        SELECT 
            a.ASSIGNMENT_ID,
            mr.ROLE_NAME,
            a.STAFF_ID,
            ms.FIRST_NAME, 
            ms.LAST_NAME,
            a.PATIENT_ID,
            p.FIRST_NAME,
            p.LAST_NAME,
            a.DATE_ASSIGNED
        FROM 
            MEDICAL_STAFF ms
        JOIN
            MEDICAL_ROL mr ON ms.ROLE_ID = mr.ROLE_ID
        JOIN
            ASSIGNMENT a ON ms.STAFF_ID = a.STAFF_ID
        JOIN 
            PATIENTS p ON a.PATIENT_ID =p.PATIENT_ID
        WHERE 
            a.PATIENT_ID = :patient_id""", nativeQuery = true)
    List<Assignment> getAssignmentByPatient(@Param("patient_id") Long PATIENT_ID);

}