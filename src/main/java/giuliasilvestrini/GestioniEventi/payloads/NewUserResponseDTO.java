package giuliasilvestrini.GestioniEventi.payloads;

import java.util.UUID;

public record NewUserResponseDTO(UUID id, String username, String fullname) {
}
