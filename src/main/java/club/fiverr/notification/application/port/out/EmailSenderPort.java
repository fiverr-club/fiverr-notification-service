package club.fiverr.notification.application.port.out;

import java.util.UUID;

public interface EmailSenderPort {
    void sendEmail(UUID recipientUserId, String subject, String body);
}
