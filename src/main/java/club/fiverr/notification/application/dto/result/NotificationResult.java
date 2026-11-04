package club.fiverr.notification.application.dto.result;

import club.fiverr.notification.domain.model.NotificationChannel;
import club.fiverr.notification.domain.model.NotificationType;

import java.time.Instant;
import java.util.UUID;

public record NotificationResult(
        UUID id,
        UUID recipientUserId,
        NotificationType type,
        String title,
        String message,
        NotificationChannel channel,
        boolean read,
        Instant readAt,
        Instant createdAt
) {
}
