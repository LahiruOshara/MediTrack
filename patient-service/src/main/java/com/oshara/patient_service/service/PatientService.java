package com.oshara.patient_service.service;

import com.oshara.patient_service.model.api.PatientApi;
import com.oshara.patient_service.model.entity.Patient;
import com.oshara.patient_service.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientService {
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();

    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient savePatient(PatientApi patient) {
        Patient newPatient = new Patient();
        BeanUtils.copyProperties(patient, newPatient);
        return patientRepository.save(newPatient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
