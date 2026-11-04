package club.fiverr.notification.application.usecase;

import club.fiverr.notification.application.dto.command.SendNotificationCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;
import club.fiverr.notification.application.mapper.NotificationResultMapper;
import club.fiverr.notification.application.port.in.SendNotificationUseCase;
import club.fiverr.notification.application.port.out.EmailSenderPort;
import club.fiverr.notification.application.port.out.PushNotificationPort;
import club.fiverr.notification.domain.model.Notification;
import club.fiverr.notification.domain.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendNotificationUseCaseImpl implements SendNotificationUseCase {

    private final NotificationRepository notificationRepository;
    private final EmailSenderPort emailSenderPort;
    private final PushNotificationPort pushNotificationPort;

    public SendNotificationUseCaseImpl(
            NotificationRepository notificationRepository,
            EmailSenderPort emailSenderPort,
            PushNotificationPort pushNotificationPort
    ) {
        this.notificationRepository = notificationRepository;
        this.emailSenderPort = emailSenderPort;
        this.pushNotificationPort = pushNotificationPort;
    }

    @Override
    @Transactional
    public NotificationResult execute(SendNotificationCommand command) {
        Notification notification = Notification.createNew(
                command.recipientUserId(),
                command.type(),
                command.title(),
                command.message(),
                command.channel()
        );

        Notification savedNotification = notificationRepository.save(notification);

        if (savedNotification.requiresEmailDelivery()) {
            emailSenderPort.sendEmail(
                    savedNotification.getRecipientUserId(),
                    savedNotification.getTitle(),
                    savedNotification.getMessage()
            );
        }

        if (savedNotification.requiresPushDelivery()) {
            pushNotificationPort.sendPushNotification(
                    savedNotification.getRecipientUserId(),
                    savedNotification.getTitle(),
                    savedNotification.getMessage()
            );
        }

        return NotificationResultMapper.toResult(savedNotification);
    }
}
