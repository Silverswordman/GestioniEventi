package giuliasilvestrini.GestioniEventi.payloads;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record NewUserDTO(

        @NotEmpty(message = "Username cannot be empty")
        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotEmpty(message = "Password cannot be empty")
        @NotBlank
        String fullname,

        @NotEmpty(message = "Email cannot be empty")
        @NotBlank
        String email,
        @NotEmpty(message = "Password cannot be empty!")
        @NotBlank
        @Size(min = 4, message = "Password must be at least 5 characters")
        String password,
        UUID id
) {

}
