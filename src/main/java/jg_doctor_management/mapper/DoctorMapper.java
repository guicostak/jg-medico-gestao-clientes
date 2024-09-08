package jg_doctor_management.mapper;

import jg_doctor_management.dto.response.DoctorResponse;
import jg_doctor_management.dto.response.InsertDoctorResponse;
import jg_doctor_management.dto.response.UpdateDoctorResponse;
import jg_doctor_management.entity.DoctorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    InsertDoctorResponse toInsertDoctorResponse(DoctorEntity entity);
    DoctorResponse toDoctorResponse(DoctorEntity entity);
    UpdateDoctorResponse toUpdateDoctorResponse(DoctorEntity entity);
}
