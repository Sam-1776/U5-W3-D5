package samuelesimeone.ProgettoU5w3d5.exceptions;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsPayload {
    private String message;
    private LocalDateTime time;
}
