package com.oshara.notification_service.controller;

import com.oshara.notification_service.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private NotificationService notificationService;

    @PostMapping("/{patientId}")
    public String sendNotification(@PathVariable Long patientId, @RequestBody String message) {
        return notificationService.sendNotification(patientId, message);
    }
}