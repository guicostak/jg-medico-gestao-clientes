package jg_doctor_management.service;

import jg_doctor_management.dto.request.InsertDoctorRequest;
import jg_doctor_management.dto.response.InsertDoctorResponse;
import jg_doctor_management.entity.DoctorEntity;
import jg_doctor_management.repository.DoctorRepository;
import jg_doctor_management.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService service;

    @Mock
    private DoctorRepository repository;

    @Mock
    private JwtUtil jwtUtil;

    @DisplayName("Insert doctor when doctor doesn't exist")
    @Test
    public void insertDoctorTest() {
        UUID doctorId = UUID.randomUUID();

        final var expectedResponse = InsertDoctorResponse.builder()
                .id(doctorId) // Usar o UUID simulado no response esperado
                .refreshToken("refresh token")
                .token("token de acesso")
                .build();

        final var request = new InsertDoctorRequest(
                "John Doe",
                "johndoe@example.com",
                "1234567890",
                "securePassword123",
                "12345678"
        );

        when(repository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(repository.findByPhoneNumber(request.phoneNumber())).thenReturn(Optional.empty());
        when(jwtUtil.encryptPassword(request.password())).thenReturn("Senha criptografada");
        when(jwtUtil.generateToken(request.email())).thenReturn("token de acesso");
        when(jwtUtil.generateRefreshToken(request.email())).thenReturn("refresh token");

        DoctorEntity savedDoctor = DoctorEntity.builder()
                .id(doctorId)
                .name(request.name())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .password("Senha criptografada")
                .crm(request.crm())
                .token("token de acesso")
                .refreshToken("refresh token")
                .build();

        when(repository.save(any(DoctorEntity.class))).thenReturn(savedDoctor);

        final var response = service.insertDoctor(request);

        assertEquals(expectedResponse, response);

        verify(jwtUtil, times(1)).generateToken(request.email());
        verify(jwtUtil, times(1)).generateRefreshToken(request.email());
        verify(jwtUtil, times(1)).encryptPassword(request.password());
        verify(repository, times(1)).save(any(DoctorEntity.class));
    }
}
