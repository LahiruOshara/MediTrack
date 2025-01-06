package com.oshara.appointment_service.service;

import com.oshara.appointment_service.model.entity.Appointment;
import com.oshara.appointment_service.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAppointments() {
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(appointment1, appointment2));

        List<Appointment> appointments = appointmentService.getAllAppointments();

        assertEquals(2, appointments.size());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    void testGetAppointmentById() {
        Appointment appointment = new Appointment();
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));

        Optional<Appointment> result = appointmentService.getAppointmentById(1L);

        assertEquals(appointment, result.orElse(null));
        verify(appointmentRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment result = appointmentService.saveAppointment(appointment);

        assertEquals(appointment, result);
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void testDeleteAppointment() {
        doNothing().when(appointmentRepository).deleteById(anyLong());

        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository, times(1)).deleteById(1L);
    }
}