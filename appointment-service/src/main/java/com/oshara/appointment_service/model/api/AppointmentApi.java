package com.oshara.appointment_service.model.api;

import lombok.Data;

@Data
public class AppointmentApi {

    private Long id;

    private Long patientId;
    private Long doctorId;
    private String appointmentDate;
    private String status;
}
