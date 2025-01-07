package com.oshara.appointment_service.service;

import com.oshara.appointment_service.model.entity.Appointment;
import com.oshara.appointment_service.model.entity.ConsultationDetails;
import com.oshara.appointment_service.repository.AppointmentRepository;
import com.oshara.appointment_service.repository.ConsultationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private ConsultationRepository consultationRepository;

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

    // Create a new consultation
    public ConsultationDetails saveConsultation(ConsultationDetails consultationDetails) {
        return consultationRepository.save(consultationDetails);
    }

    // Retrieve all consultations
    public List<ConsultationDetails> getAllConsultations() {
        return consultationRepository.findAll();
    }

    // Retrieve a consultation by ID
    public Optional<ConsultationDetails> getConsultationById(Long id) {
        return consultationRepository.findById(id);
    }

    // Update an existing consultation
    public ConsultationDetails updateConsultation(Long id, ConsultationDetails consultationDetails) {
        if (consultationRepository.existsById(id)) {
            consultationDetails.setId(id);
            return consultationRepository.save(consultationDetails);
        } else {
            throw new RuntimeException("Consultation not found with id " + id);
        }
    }

    // Delete a consultation by ID
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

}
