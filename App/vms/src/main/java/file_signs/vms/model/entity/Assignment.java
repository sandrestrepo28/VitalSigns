package file_signs.vms.model.entity;

import java.time.*;
import java.util.*;

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
@Table(name = "ASSIGNMENT", schema = "dbo")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ASSIGNMENT_ID", nullable = false)
    private UUID assignment_id;

    @NotNull(message = "Cannot staff_id be null")
    @ManyToOne
    @JoinColumn(name = "STAFF_ID", nullable = false, referencedColumnName = "STAFF_ID")
    private Medical_staff staffId;

    @NotNull(message = "Cannot patient_id be null")
    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", nullable = false, referencedColumnName = "PATIENT_ID")
    private Patient patientId;

    @Column(name = "DATE_ASSIGNED", nullable = false, insertable = false, updatable = false)
    private LocalDate date_assigned;

}
