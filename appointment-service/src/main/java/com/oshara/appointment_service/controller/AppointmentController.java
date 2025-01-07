package com.oshara.appointment_service.controller;

import com.oshara.appointment_service.client.Patient;
import com.oshara.appointment_service.model.entity.Appointment;
import com.oshara.appointment_service.model.entity.ConsultationDetails;
import com.oshara.appointment_service.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.saveAppointment(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/consultations")
    public ResponseEntity<ConsultationDetails> createConsultation(@RequestBody ConsultationDetails consultationDetails) {
        ConsultationDetails savedConsultation = appointmentService.saveConsultation(consultationDetails);
        return new ResponseEntity<>(savedConsultation, HttpStatus.CREATED);
    }

    @GetMapping("/consultations")
    public List<ConsultationDetails> getAllConsultations() {
        return appointmentService.getAllConsultations();
    }

    @GetMapping("/consultations/{id}")
    public ResponseEntity<ConsultationDetails> getConsultationById(@PathVariable Long id) {
        Optional<ConsultationDetails> consultation = appointmentService.getConsultationById(id);
        return consultation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/consultations/{id}")
    public ResponseEntity<ConsultationDetails> updateConsultation(@PathVariable Long id, @RequestBody ConsultationDetails consultationDetails) {
        try {
            ConsultationDetails updatedConsultation = appointmentService.updateConsultation(id, consultationDetails);
            return ResponseEntity.ok(updatedConsultation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/consultations/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        appointmentService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/run")
    public ResponseEntity<String> run() {
        appointmentService.aggregateData();
        return ResponseEntity.ok("Running");
    }
}
