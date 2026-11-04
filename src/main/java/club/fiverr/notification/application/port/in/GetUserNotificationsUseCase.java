package club.fiverr.notification.application.port.in;

import club.fiverr.notification.application.dto.command.GetUserNotificationsCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;

import java.util.List;

public interface GetUserNotificationsUseCase {
    List<NotificationResult> execute(GetUserNotificationsCommand command);
}
