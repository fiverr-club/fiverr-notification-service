package club.fiverr.notification.application.port.out;

import java.util.UUID;

public interface PushNotificationPort {
    void sendPushNotification(UUID recipientUserId, String title, String message);
}
