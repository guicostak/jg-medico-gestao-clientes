package jg_doctor_management.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorResponse {

    private String name;
    private String email;
    private String phoneNumber;
    private String crm;
}
