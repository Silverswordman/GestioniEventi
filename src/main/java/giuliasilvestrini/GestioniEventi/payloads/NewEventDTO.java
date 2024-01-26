package giuliasilvestrini.GestioniEventi.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewEventDTO(
        @NotEmpty(message = "Title is mandatory")
        @NotBlank
        String title,
        @NotEmpty(message = " Descriptions are mandatory")
        @NotBlank
        String description,
        @NotNull(message = "Date is mandatory")
        @NotBlank
        LocalDate date,
        @NotEmpty(message = " Location is mandatory")
        @NotBlank
        String location,
        @NotNull(message = " Number of seats are mandatory")
        @NotBlank
        Integer seats,

        String eventImg

) {
}