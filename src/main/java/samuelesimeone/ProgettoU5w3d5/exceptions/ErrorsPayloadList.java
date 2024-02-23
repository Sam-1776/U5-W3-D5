package samuelesimeone.ProgettoU5w3d5.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorsPayloadList extends ErrorsPayload{
    private List<String> errorList;

    public ErrorsPayloadList(String message, LocalDateTime time, List<String> errorList) {
        super(message, time);
        this.errorList = errorList;
    }
}
