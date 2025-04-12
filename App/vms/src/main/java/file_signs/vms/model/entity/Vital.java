package file_signs.vms.model.entity;

import java.time.*;
import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
// Get & Set
@Getter
@Setter
// Constructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VITAL", schema = "dbo")
public class Vital {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "VITAL_ID", nullable = false)
    private UUID vital_id;

    @NotNull(message = "Cannot assignment_id be null")
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false, referencedColumnName = "ASSIGNMENT_ID")
    private Assignment assignment_id;

    @Column(name = "DATA_CREATED", nullable = false, insertable = false, updatable = false)
    private LocalDateTime data_created;

    @Column(name = "HEART_RATE", nullable = false)
    @NotNull(message = "Heart rate cannot be null")
    @Min(value = 0, message = "Heart rate cannot be less than 0")
    @Max(value = 127, message = "Heart rate cannot be greater than 127")
    @PositiveOrZero(message = "Heart Rate cannot be negative")
    private byte heart_rate;

    @Column(name = "RESPIRATORY_RATE", nullable = false)
    @NotNull(message = "Respiratory rate cannot be null")
    @Min(value = 0, message = "Respiratory rate cannot be less than 0")
    @Max(value = 127, message = "Respiratory rate cannot be greater than 127")
    @PositiveOrZero(message = "Respiratory Rate cannot be negative")
    private byte respiratory_rate;

    @NotNull(message = "Saturation cannot be null")
    @PositiveOrZero(message = "Respiratory Rate cannot be negative")
    @Min(value = 0, message = "Respiratory rate cannot be less than 0")
    @Max(value = 127, message = "Respiratory rate cannot be greater than 127")
    @Column(name = "SATURATION", nullable = false)
    private byte saturation;

    @Column(name = "TEMPERATURE" , nullable = false)
    @NotNull(message = "Temperature cannot be null")
    @Min(value = 0, message = "Temperature cannot be less than 0")
    @Max(value = 127, message = "Temperature cannot be greater than 127")
    @PositiveOrZero(message = "Temperature cannot be negative")
    private byte temperature;

    @Column(name = "BLOOD_GLUCOSE", nullable = false)
    @NotNull(message = "Blood glucose cannot be null")
    @Min(value = 0, message = "Blood glucose cannot be less than 0")
    @Max(value = 127, message = "Blood glucose cannot be greater than 127")
    @PositiveOrZero(message = "Blood glucose cannot be negative")
    private byte blood_glucose;

    @Column(name = "BLOOD_PRESSURE", nullable = false)
    @NotNull(message = "Blood pressure cannot be null")
    @NotEmpty(message = "Blood pressure cannot be empty")
    @Pattern(regexp = "^[0-9]{3}/[0-9]{3}$", message = "Blood pressure must be 'XXX/XXX'")
    private String blood_pressure;

    @Column (name = "NOTE_MEDICAL", nullable = true, length = 255)
    @NotNull(message = "Note medical cannot be null")
    @NotEmpty(message = "Note medical cannot be empty")
    @Size(min = 0, max = 255, message = "Note medical must be between 0 and 255characters")
    private String note_medical;
}
