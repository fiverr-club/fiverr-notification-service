package club.fiverr.notification.application.dto.command;

import java.util.UUID;

public record GetUserNotificationsCommand(
        UUID recipientUserId
) {
}
