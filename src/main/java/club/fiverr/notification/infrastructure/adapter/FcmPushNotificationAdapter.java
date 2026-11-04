package club.fiverr.notification.infrastructure.adapter;

import club.fiverr.notification.application.port.out.PushNotificationPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FcmPushNotificationAdapter implements PushNotificationPort {

    private static final Logger log = LoggerFactory.getLogger(FcmPushNotificationAdapter.class);

    @Override
    public void sendPushNotification(UUID recipientUserId, String title, String message) {
        log.info("Sending FCM push notification to userId={} with title='{}'", recipientUserId, title);
    }
}
