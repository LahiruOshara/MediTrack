package com.oshara.patient_service.repository;

import com.oshara.patient_service.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}