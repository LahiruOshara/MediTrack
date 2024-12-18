package com.oshara.appointment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service")  // This is the application name of PatientService
@Component
public interface PatientClient {

    @GetMapping("/api/patients/{id}")  // Define endpoint on PatientService
    Patient getPatientById(@PathVariable("id") Long id);
}
