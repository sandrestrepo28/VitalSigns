package file_signs.vms.model.entity;

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

@Table(name = "MEDICAL_STAFF", schema = "dbo")

public class Medical_staff {

    @Id
    @NotNull(message = "ID cannot be null")
    @Column(name = "STAFF_ID", nullable = false)
    @Positive(message = "ID cannot be negative")
    private Long staff_id;

    @NotNull(message = "Cannot role_id be null")
    @ManyToOne
    @Min(value = 1, message = "Rol cannot be less than 1")
    @Max(value = 4, message = "Rol cannot be greater than 4")
    @Positive(message = "ID cannot be negative")
    @JoinColumn(name = "ROLE_ID", nullable = false, referencedColumnName = "role_id")
    private Medical_rol rol_id;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String first_name_staff;

    @NotNull(message = "Surname cannot be null")
    @NotEmpty(message = "Surname cannot be empty")
    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String last_name_staff;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    @Column(name = "EMAIL", nullable = false, unique = true ,length = 100)
    private String email;

}
