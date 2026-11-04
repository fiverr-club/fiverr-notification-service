package club.fiverr.notification.application.usecase;

import club.fiverr.notification.NotificationTestFixtures;
import club.fiverr.notification.application.dto.command.GetUserNotificationsCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;
import club.fiverr.notification.domain.model.Notification;
import club.fiverr.notification.domain.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserNotificationsUseCaseImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private GetUserNotificationsUseCaseImpl getUserNotificationsUseCase;

    @Test
    void shouldReturnUserNotificationsOrderedByCreatedAtDesc() {
        Notification notification = NotificationTestFixtures.createUnreadNotification();
        GetUserNotificationsCommand command = NotificationTestFixtures.createValidGetUserNotificationsCommand();

        when(notificationRepository.findByRecipientUserIdOrderByCreatedAtDesc(NotificationTestFixtures.RECIPIENT_USER_ID))
                .thenReturn(List.of(notification));

        List<NotificationResult> results = getUserNotificationsUseCase.execute(command);

        assertEquals(1, results.size());
        assertEquals(NotificationTestFixtures.NOTIFICATION_ID, results.getFirst().id());
        verify(notificationRepository).findByRecipientUserIdOrderByCreatedAtDesc(NotificationTestFixtures.RECIPIENT_USER_ID);
    }
}
