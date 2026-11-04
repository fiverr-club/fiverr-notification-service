package club.fiverr.notification.application.usecase;

import club.fiverr.notification.application.dto.command.GetUserNotificationsCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;
import club.fiverr.notification.application.mapper.NotificationResultMapper;
import club.fiverr.notification.application.port.in.GetUserNotificationsUseCase;
import club.fiverr.notification.domain.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetUserNotificationsUseCaseImpl implements GetUserNotificationsUseCase {

    private final NotificationRepository notificationRepository;

    public GetUserNotificationsUseCaseImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResult> execute(GetUserNotificationsCommand command) {
        return notificationRepository.findByRecipientUserIdOrderByCreatedAtDesc(command.recipientUserId())
                .stream()
                .map(NotificationResultMapper::toResult)
                .toList();
    }
}
