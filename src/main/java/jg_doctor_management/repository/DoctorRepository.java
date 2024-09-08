package jg_doctor_management.repository;

import jakarta.inject.Singleton;
import jg_doctor_management.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Singleton
public interface DoctorRepository extends JpaRepository<DoctorEntity, UUID> {

    Optional<DoctorEntity> findByEmail(String email);

    Optional<DoctorEntity> findByPhoneNumber(String phoneNumber);
}
