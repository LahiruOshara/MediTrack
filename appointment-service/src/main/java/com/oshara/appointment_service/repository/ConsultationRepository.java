package com.oshara.appointment_service.repository;

import com.oshara.appointment_service.model.entity.Appointment;
import com.oshara.appointment_service.model.entity.ConsultationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationDetails, Long> {
}
