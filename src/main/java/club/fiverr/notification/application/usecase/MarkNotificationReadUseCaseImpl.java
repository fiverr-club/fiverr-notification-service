package club.fiverr.notification.application.usecase;

import club.fiverr.notification.application.dto.command.MarkNotificationReadCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;
import club.fiverr.notification.application.mapper.NotificationResultMapper;
import club.fiverr.notification.application.port.in.MarkNotificationReadUseCase;
import club.fiverr.notification.domain.exception.NotificationDomainException;
import club.fiverr.notification.domain.exception.NotificationNotFoundException;
import club.fiverr.notification.domain.model.Notification;
import club.fiverr.notification.domain.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarkNotificationReadUseCaseImpl implements MarkNotificationReadUseCase {

    private final NotificationRepository notificationRepository;

    public MarkNotificationReadUseCaseImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional
    public NotificationResult execute(MarkNotificationReadCommand command) {
        Notification notification = notificationRepository.findById(command.notificationId())
                .orElseThrow(() -> new NotificationNotFoundException(command.notificationId()));

        if (command.recipientUserId() != null && !notification.getRecipientUserId().equals(command.recipientUserId())) {
            throw new NotificationDomainException("Notification does not belong to user " + command.recipientUserId());
        }

        notification.markAsRead();
        Notification saved = notificationRepository.save(notification);
        return NotificationResultMapper.toResult(saved);
    }
}
