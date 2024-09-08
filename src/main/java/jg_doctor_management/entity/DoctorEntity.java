package jg_doctor_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jg_doctor_management.util.JwtUtil;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Table(name = "tb_doctors")
@Entity
@Data
@Builder
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String crm;
    private String token;
    private String refreshToken;

    public void updatePassword(JwtUtil jwtUtil, String password) {
        if (password != null && !password.isEmpty()) {
            this.setPassword(jwtUtil.encryptPassword(password));
        }
    }

    public void updateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            this.setEmail(email);
        }
    }

    public void updateName(String name) {
        if (name != null && !name.isEmpty()) {
            this.setName(name);
        }
    }

    public void updateCrm(String crm) {
        if (crm != null && !crm.isEmpty()) {
            this.setCrm(crm);
        }
    }
}
