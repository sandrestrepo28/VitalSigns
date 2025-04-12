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

@Table(name = "MEDICAL_ROL" , schema = "dbo")
public class Medical_rol {

    @Id
    @Column(name = "ROLE_ID" , nullable = false)
    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID cannot be negative")
    private Short role_id;

    @NotNull(message = "Role name cannot be null")
    @NotEmpty(message = "Role name cannot be empty")
    @Size(min = 1, max = 100, message = "Role name must be between 1 and 100 characters")
    @Column(name = "ROLE_NAME", nullable = false, length = 100)
    private String role_name;

}
