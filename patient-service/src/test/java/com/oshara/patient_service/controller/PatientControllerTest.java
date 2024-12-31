package com.oshara.patient_service.controller;

import com.oshara.patient_service.model.api.PatientApi;
import com.oshara.patient_service.model.entity.Patient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreatePatient() {
        PatientApi patientApi = new PatientApi();
        patientApi.setName("John Doe");
        patientApi.setEmail("test@test.com");
        patientApi.setPhoneNumber("1234567890");
        patientApi.setDateOfBirth("01/01/2000");

        ResponseEntity<PatientApi> response =
                restTemplate.postForEntity("/api/patients", patientApi, PatientApi.class);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void testGetPatient() {
        ResponseEntity<Patient> response = restTemplate.getForEntity("/patients/1", Patient.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
    }
}
