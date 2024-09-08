package jg_doctor_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateDoctorRequest(
        @NotBlank(message = "{notblank.name}")
        @Size(min = 2, max = 100, message = "{size.name}")
        String name,

        @NotBlank(message = "{notblank.email}")
        @Email(message = "{email.email}")
        String email,

        @NotBlank(message = "{notblank.phoneNumber}")
        @Pattern(regexp = "\\d{10,15}", message = "{pattern.phoneNumber}")
        String phoneNumber,

        @NotBlank(message = "{notblank.password}")
        @Size(min = 8, message = "{size.password}")
        String password,

        @NotBlank(message = "{notblank.crm}")
        @Size(min = 8, message = "{pattern.crm}")
        String crm
) { }
