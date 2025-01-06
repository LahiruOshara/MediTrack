package com.oshara.notification_service.service;


import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public String sendNotification(Long patientId, String message) {
        // Implement actual notification logic here (e.g., email/SMS)
        return "Notification sent to patient " + patientId + ": " + message;
    }
}
