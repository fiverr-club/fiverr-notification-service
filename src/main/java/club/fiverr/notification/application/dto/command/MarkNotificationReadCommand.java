package club.fiverr.notification.application.dto.command;

import java.util.UUID;

public record MarkNotificationReadCommand(
        UUID notificationId,
        UUID recipientUserId
) {
}
