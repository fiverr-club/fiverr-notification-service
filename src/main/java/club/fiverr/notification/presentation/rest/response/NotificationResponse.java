package club.fiverr.notification.presentation.rest.response;

import club.fiverr.notification.domain.model.NotificationChannel;
import club.fiverr.notification.domain.model.NotificationType;

import java.time.Instant;
import java.util.UUID;

public record NotificationResponse(
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
