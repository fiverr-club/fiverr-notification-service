package club.fiverr.notification.application.port.in;

import club.fiverr.notification.application.dto.command.MarkNotificationReadCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;

public interface MarkNotificationReadUseCase {
    NotificationResult execute(MarkNotificationReadCommand command);
}
