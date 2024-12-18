package com.oshara.patient_service.model.api;

import lombok.Data;

@Data
public class PatientApi {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
}