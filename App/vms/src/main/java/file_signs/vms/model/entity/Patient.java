package file_signs.vms.model.entity;

import java.time.*;

import org.springframework.format.annotation.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
// Set & Get
@Getter
@Setter
// Constructor
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "PATIENTS", schema = "dbo")
public class Patient {

    @Id
    @NotNull(message = "ID cannot be null")
    @Column(name = "PATIENT_ID", nullable = false)
    @Positive(message = "ID cannot be negative")
    private Long patient_id;

    @Column(name = "REGISTRATION_DATE", nullable = false, insertable = false, updatable = false)
    private LocalDate registration_date;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String first_name_patient;

    @NotNull(message = "Surname cannot be null")
    @NotEmpty(message = "Surname cannot be empty")
    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String last_name_patient;

    @NotNull(message="Date of birth cannot be null")
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_of_birth;

    @NotNull(message ="Gender cannot be null")
    @NotEmpty(message ="Gender cannot be empty")
    @Pattern(regexp = "M|F", message="Gender must be 'M' or 'F'")
    @Column(name = "GENDER", nullable = false, length = 10)
    private String gender;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age cannot be less than 0")
    @Max(value = 127, message = "Age cannot be greater than 120")
    @PositiveOrZero(message = "Age cannot be negative")
    @Column(name = "AGE", nullable = false)
    private byte age;

    @NotNull(message = "Blood type cannot be null")
    @NotEmpty(message = "Blood type cannot be empty")
    @Column(name = "BLOOD_TYPE", nullable = true, length = 5)
    @Pattern(regexp = "A+ |A- |B+ |B- |AB+ | AB- | O+ | O-", message = "Blood type must be 'A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'")
    private String blood_type;
}
