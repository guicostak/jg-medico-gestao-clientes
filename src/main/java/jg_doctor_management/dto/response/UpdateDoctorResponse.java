package jg_doctor_management.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class UpdateDoctorResponse {
    private UUID id;
    private String token;
    private String refreshToken;
}

