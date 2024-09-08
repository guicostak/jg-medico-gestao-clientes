package jg_doctor_management.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Builder
@Data
public class InsertDoctorResponse {
    private UUID id;
    private String token;
    private String refreshToken;
}

