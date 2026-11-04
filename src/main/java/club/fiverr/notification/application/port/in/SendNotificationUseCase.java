package club.fiverr.notification.application.port.in;

import club.fiverr.notification.application.dto.command.SendNotificationCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;

public interface SendNotificationUseCase {
    NotificationResult execute(SendNotificationCommand command);
}
