package club.fiverr.notification.infrastructure.adapter;

import club.fiverr.notification.application.port.out.EmailSenderPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SmtpEmailSenderAdapter implements EmailSenderPort {

    private static final Logger log = LoggerFactory.getLogger(SmtpEmailSenderAdapter.class);

    @Override
    public void sendEmail(UUID recipientUserId, String subject, String body) {
        log.info("Sending email notification to userId={} with subject='{}'", recipientUserId, subject);
    }
}
