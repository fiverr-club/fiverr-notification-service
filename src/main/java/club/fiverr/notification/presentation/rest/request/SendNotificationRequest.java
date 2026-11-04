package club.fiverr.notification.presentation.rest.request;

import club.fiverr.notification.domain.model.NotificationChannel;
import club.fiverr.notification.domain.model.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SendNotificationRequest(
        @NotNull UUID recipientUserId,
        @NotNull NotificationType type,
        @NotBlank String title,
        @NotBlank String message,
        NotificationChannel channel
) {
}
