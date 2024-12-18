package com.oshara.appointment_service.service;

import com.oshara.appointment_service.client.Patient;
import com.oshara.appointment_service.client.PatientClient;
import com.oshara.appointment_service.model.entity.Appointment;
import com.oshara.appointment_service.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private PatientClient patientClient;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public Patient getPatientDetails(Long patientId) {
        return patientClient.getPatientById(patientId);  // Feign client call to PatientService
    }
}
