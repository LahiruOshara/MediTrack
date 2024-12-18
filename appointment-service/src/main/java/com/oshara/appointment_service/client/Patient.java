package com.oshara.appointment_service.client;

import lombok.Data;

@Data
public class Patient {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
}
