package  com.oshara.patient_service.service;

import com.oshara.patient_service.model.entity.Patient;
import com.oshara.patient_service.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    public PatientServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPatient() {
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmail("john@example.com");
        patient.setPhoneNumber("1234567890");
        patient.setDateOfBirth("01/01/2000");
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient created = patientService.addPatient(patient);

        assertNotNull(created);
        assertEquals("John Doe", created.getName());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testGetPatientById() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        patient.setEmail("test@test.com");
        patient.setPhoneNumber("1234567890");
        patient.setDateOfBirth("01/01/2000");
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Patient fetched = patientService.getPatientById(1L).get();

        assertNotNull(fetched);
        assertEquals("John Doe", fetched.getName());
        verify(patientRepository, times(1)).findById(1L);
    }
}
