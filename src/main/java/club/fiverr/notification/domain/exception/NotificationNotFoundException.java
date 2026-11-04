package club.fiverr.notification.domain.exception;

import java.util.UUID;

public class NotificationNotFoundException extends NotificationDomainException {
    public NotificationNotFoundException(UUID notificationId) {
        super("Notification not found with ID: " + notificationId);
    }
}
