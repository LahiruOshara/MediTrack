package com.oshara.appointment_service.service;

import com.oshara.appointment_service.model.entity.Appointment;
import com.oshara.appointment_service.model.entity.AppointmentFrequency;
import com.oshara.appointment_service.model.entity.ConsultationDetails;
import com.oshara.appointment_service.model.entity.DoctorAppointmentAverage;
import com.oshara.appointment_service.model.entity.DoctorAppointmentCount;
import com.oshara.appointment_service.model.entity.DoctorTimeSlotPercentage;
import com.oshara.appointment_service.model.entity.SymptomConditionCount;
import com.oshara.appointment_service.repository.AppointmentFrequencyRepository;
import com.oshara.appointment_service.repository.AppointmentRepository;
import com.oshara.appointment_service.repository.ConsultationRepository;
import com.oshara.appointment_service.repository.DoctorAppointmentAverageRepository;
import com.oshara.appointment_service.repository.DoctorAppointmentCountRepository;
import com.oshara.appointment_service.repository.DoctorTimeSlotPercentageRepository;
import com.oshara.appointment_service.repository.SymptomConditionCountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ConsultationRepository consultationRepository;
    private final DoctorAppointmentCountRepository doctorAppointmentCountRepository;
    private final AppointmentFrequencyRepository appointmentFrequencyRepository;
    private final SymptomConditionCountRepository symptomConditionCountRepository;
    private final DoctorAppointmentAverageRepository doctorAppointmentAverageRepository;
    private final DoctorTimeSlotPercentageRepository doctorTimeSlotPercentageRepository;


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

    public void aggregateData() {
        System.out.println("Running data aggregation job...");

        // Save number of appointments per doctor
        Map<Long, Long> numberOfAppointmentsPerDoctor = getNumberOfAppointmentsPerDoctor();
        numberOfAppointmentsPerDoctor.forEach((doctorId, count) -> {
            DoctorAppointmentCount doctorAppointmentCount = new DoctorAppointmentCount();
            doctorAppointmentCount.setDoctorId(doctorId);
            doctorAppointmentCount.setAppointmentCount(count);
            doctorAppointmentCountRepository.save(doctorAppointmentCount);
        });

        // Save frequency of appointments over time
        Map<LocalDate, Long> frequencyOfAppointmentsOverTime = getFrequencyOfAppointmentsOverTime();
        frequencyOfAppointmentsOverTime.forEach((date, frequency) -> {
            AppointmentFrequency appointmentFrequency = new AppointmentFrequency();
            appointmentFrequency.setDate(date);
            appointmentFrequency.setFrequency(frequency);
            appointmentFrequencyRepository.save(appointmentFrequency);
        });

        // Save common symptoms and conditions by speciality
        Map<String, Map<String, Long>> commonSymptomsAndConditionsBySpeciality = getCommonSymptomsAndConditionsBySpeciality();
        commonSymptomsAndConditionsBySpeciality.forEach((speciality, conditions) -> {
            conditions.forEach((diagnosis, count) -> {
                SymptomConditionCount symptomConditionCount = new SymptomConditionCount();
                symptomConditionCount.setSpeciality(speciality);
                symptomConditionCount.setDiagnosis(diagnosis);
                symptomConditionCount.setCount(count);
                symptomConditionCountRepository.save(symptomConditionCount);
            });
        });

        // Save average number of appointments per doctor
        Map<Long, Double> averageNumberOfAppointmentsPerDoctor = getAverageNumberOfAppointmentsPerDoctor();
        averageNumberOfAppointmentsPerDoctor.forEach((doctorId, average) -> {
            DoctorAppointmentAverage doctorAppointmentAverage = new DoctorAppointmentAverage();
            doctorAppointmentAverage.setDoctorId(doctorId);
            doctorAppointmentAverage.setAverageAppointments(average);
            doctorAppointmentAverageRepository.save(doctorAppointmentAverage);
        });

        // Save percentage of time slots filled
        Map<Long, Double> percentageOfTimeSlotsFilled = getPercentageOfTimeSlotsFilled();
        percentageOfTimeSlotsFilled.forEach((doctorId, percentage) -> {
            DoctorTimeSlotPercentage doctorTimeSlotPercentage = new DoctorTimeSlotPercentage();
            doctorTimeSlotPercentage.setDoctorId(doctorId);
            doctorTimeSlotPercentage.setPercentageFilled(percentage);
            doctorTimeSlotPercentageRepository.save(doctorTimeSlotPercentage);
        });
    }

    public Map<Long, Long> getNumberOfAppointmentsPerDoctor() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .collect(Collectors.groupingBy(Appointment::getDoctorId, Collectors.counting()));
    }

    public Map<LocalDate, Long> getFrequencyOfAppointmentsOverTime() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .collect(Collectors.groupingBy(Appointment::getAppointmentDate, Collectors.counting()));
    }

    public Map<String, Map<String, Long>> getCommonSymptomsAndConditionsBySpeciality() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<ConsultationDetails> consultations = consultationRepository.findAll();

        return consultations.stream()
                .collect(Collectors.groupingBy(
                        consultation -> appointments.stream()
                                .filter(appointment -> appointment.getId().equals(consultation.getAppointmentId()))
                                .findFirst()
                                .map(Appointment::getSpeciality)
                                .orElse("Unknown"),
                        Collectors.groupingBy(ConsultationDetails::getDiagnosis, Collectors.counting())
                ));
    }

    public Map<Long, Double> getAverageNumberOfAppointmentsPerDoctor() {
        List<Appointment> appointments = appointmentRepository.findAll();
        Map<Long, Long> appointmentsPerDoctor = getNumberOfAppointmentsPerDoctor();
        Map<Long, Long> weeksPerDoctor = appointments.stream()
                .collect(Collectors.groupingBy(Appointment::getDoctorId,
                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
                                .map(appointment -> appointment.getAppointmentDate().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()))
                                .distinct().count())));
        return appointmentsPerDoctor.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() / (double) weeksPerDoctor.get(entry.getKey())));
    }

    public Map<Long, Double> getPercentageOfTimeSlotsFilled() {
        List<Appointment> appointments = appointmentRepository.findAll();
        Map<Long, Long> filledSlotsPerDoctor = getNumberOfAppointmentsPerDoctor();

        // Create a map of doctor IDs to their total slots
        Map<Long, Long> totalSlotsPerDoctor = new HashMap<>();
        for (Appointment appointment : appointments) {
            totalSlotsPerDoctor.merge(appointment.getDoctorId(), 20L, Long::sum);
        }

        // Calculate the percentage of time slots filled
        return filledSlotsPerDoctor.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (entry.getValue() / (double) totalSlotsPerDoctor.get(entry.getKey())) * 100
                ));
    }
}
