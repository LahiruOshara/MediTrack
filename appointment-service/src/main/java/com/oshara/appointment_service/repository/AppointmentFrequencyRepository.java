package com.oshara.appointment_service.repository;


import com.oshara.appointment_service.model.entity.AppointmentFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentFrequencyRepository extends JpaRepository<AppointmentFrequency, Long> {
}
