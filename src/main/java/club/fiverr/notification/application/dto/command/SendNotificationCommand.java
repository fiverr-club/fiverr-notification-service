package club.fiverr.notification.application.dto.command;

import club.fiverr.notification.domain.model.NotificationChannel;
import club.fiverr.notification.domain.model.NotificationType;

import java.util.UUID;

public record SendNotificationCommand(
        UUID recipientUserId,
        NotificationType type,
        String title,
        String message,
        NotificationChannel channel
) {
}
