package jg_doctor_management.mapper;

import javax.annotation.processing.Generated;
import jg_doctor_management.dto.response.DoctorResponse;
import jg_doctor_management.dto.response.InsertDoctorResponse;
import jg_doctor_management.dto.response.UpdateDoctorResponse;
import jg_doctor_management.entity.DoctorEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-05T18:11:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public InsertDoctorResponse toInsertDoctorResponse(DoctorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        InsertDoctorResponse.InsertDoctorResponseBuilder insertDoctorResponse = InsertDoctorResponse.builder();

        insertDoctorResponse.id( entity.getId() );
        insertDoctorResponse.token( entity.getToken() );
        insertDoctorResponse.refreshToken( entity.getRefreshToken() );

        return insertDoctorResponse.build();
    }

    @Override
    public DoctorResponse toDoctorResponse(DoctorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String name = null;
        String email = null;
        String phoneNumber = null;
        String crm = null;

        name = entity.getName();
        email = entity.getEmail();
        phoneNumber = entity.getPhoneNumber();
        crm = entity.getCrm();

        DoctorResponse doctorResponse = new DoctorResponse( name, email, phoneNumber, crm );

        return doctorResponse;
    }

    @Override
    public UpdateDoctorResponse toUpdateDoctorResponse(DoctorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UpdateDoctorResponse updateDoctorResponse = new UpdateDoctorResponse();

        updateDoctorResponse.setId( entity.getId() );
        updateDoctorResponse.setToken( entity.getToken() );
        updateDoctorResponse.setRefreshToken( entity.getRefreshToken() );

        return updateDoctorResponse;
    }
}
