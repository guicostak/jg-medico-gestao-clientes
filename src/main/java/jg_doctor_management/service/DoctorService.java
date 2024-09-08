package jg_doctor_management.service;

import jakarta.transaction.Transactional;
import jg_doctor_management.dto.request.InsertDoctorRequest;
import jg_doctor_management.dto.request.UpdateDoctorRequest;
import jg_doctor_management.dto.response.DoctorResponse;
import jg_doctor_management.dto.response.InsertDoctorResponse;
import jg_doctor_management.dto.response.UpdateDoctorResponse;
import jg_doctor_management.entity.DoctorEntity;
import jg_doctor_management.exception.AlreadyExistException;
import jg_doctor_management.exception.handler.NotFound.NotFoundException;
import jg_doctor_management.mapper.DoctorMapper;
import jg_doctor_management.repository.DoctorRepository;
import jg_doctor_management.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repository;
    private final JwtUtil jwtUtil;

    @SneakyThrows
    public InsertDoctorResponse insertDoctor(InsertDoctorRequest request) {
        final var doctor = DoctorEntity.builder()
                .name(request.name())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .password(request.password())
                .crm(request.crm())
                .build();

        if(repository.findByEmail(doctor.getEmail()).isPresent() || repository.findByPhoneNumber(doctor.getPhoneNumber()).isPresent()) {
            throw new AlreadyExistException("Email ou telefone já cadastrados");
        }
        doctor.setPassword(jwtUtil.encryptPassword(doctor.getPassword()));
        doctor.setToken(jwtUtil.generateToken(doctor.getEmail()));
        doctor.setRefreshToken(jwtUtil.generateRefreshToken(doctor.getEmail()));

        return DoctorMapper.INSTANCE.toInsertDoctorResponse(repository.save(doctor));
    }

    public DoctorResponse getDoctorById(UUID id) {
        return repository.findById(id)
                .map(DoctorMapper.INSTANCE::toDoctorResponse)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado" + id));
    }

    public UpdateDoctorResponse updateDoctor(UUID id, UpdateDoctorRequest request) {
        final var doctor = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado: " + id));

        doctor.updatePassword(jwtUtil, request.password());
        doctor.updateEmail(request.email());
        doctor.updateName(request.name());
        doctor.updateCrm(request.crm());

        DoctorEntity medicoSalvo = repository.save(doctor);
        return DoctorMapper.INSTANCE.toUpdateDoctorResponse(medicoSalvo);
    }

}
