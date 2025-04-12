package file_signs.vms.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import file_signs.vms.model.entity.Vital;

public interface Vital_Repository extends JpaRepository<Vital, UUID> {  

    //Get vital by Patient
    @Query(value="""
    SELECT 
        v.VITAL_ID,
        a.ASSIGNMENT_ID,
        mr.ROLE_NAME,
        a.STAFF_ID,
        ms.FIRST_NAME,
        ms.LAST_NAME,
        a.PATIENT_ID,
        p.FIRST_NAME,
        p.LAST_NAME,
        v.DATA_CREATED,
        v.HEART_RATE,
        v.SATURATION,
        v.RESPIRATORY_RATE,
        v.TEMPERATURE,
        v.BLOOD_GLUCOSE,
        v.BLOOD_PRESSURE,
        v.NOTE_MEDICAL
    FROM 
        MEDICAL_STAFF ms
    JOIN
        MEDICAL_ROL mr ON ms.ROLE_ID = mr.ROLE_ID
    JOIN
        ASSIGNMENT a ON ms.STAFF_ID = a.STAFF_ID
    JOIN 
        PATIENTS p ON a.PATIENT_ID =p.PATIENT_ID
    JOIN 
        VITAL v ON a.ASSIGNMENT_ID = v.ASSIGNMENT_ID
    WHERE 
        a.PATIENT_ID = :patient_id""", nativeQuery = true)
    List<Vital> getVitalByPatient(@Param("patient_id") Long PATIENT_ID);

}
