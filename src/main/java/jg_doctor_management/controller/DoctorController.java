package jg_doctor_management.controller;

import jakarta.validation.Valid;
import jg_doctor_management.dto.request.InsertDoctorRequest;
import jg_doctor_management.dto.request.UpdateDoctorRequest;
import jg_doctor_management.dto.response.DoctorResponse;
import jg_doctor_management.dto.response.InsertDoctorResponse;
import jg_doctor_management.dto.response.UpdateDoctorResponse;
import jg_doctor_management.repository.DoctorRepository;
import jg_doctor_management.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/doctor")
@Slf4j
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @Autowired
    DoctorRepository doctorRepository;


    @PostMapping
    public ResponseEntity<InsertDoctorResponse> insertDoctor(@RequestBody @Valid InsertDoctorRequest request) {
        return ResponseEntity.ok().body(service.insertDoctor(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getDoctorById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateDoctorResponse> atualizarMedico(@PathVariable UUID id, @RequestBody @Valid UpdateDoctorRequest medicoAtualizado) {
        return ResponseEntity.ok(service.updateDoctor(id, medicoAtualizado));
    }
}

