package com.oshara.appointment_service.repository;

import com.oshara.appointment_service.model.entity.DoctorTimeSlotPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorTimeSlotPercentageRepository extends JpaRepository<DoctorTimeSlotPercentage, Long> {
}
