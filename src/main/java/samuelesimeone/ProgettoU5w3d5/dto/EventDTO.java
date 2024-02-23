package samuelesimeone.ProgettoU5w3d5.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record EventDTO(
        @NotEmpty(message = "Il titolo dell'evento è obbligatorio")
         String title,
        @NotEmpty(message = "La data dell'evento è obbligatoria")
         LocalDate date,
         String description,
        @NotEmpty(message = "Il numero massimo è obbligatorio")
         long nMax,
        @NotEmpty(message = "Il luogo dell'evento è obbligatorio")
         String location
) {
}
