package club.fiverr.notification.application.usecase;

import club.fiverr.notification.NotificationTestFixtures;
import club.fiverr.notification.application.dto.command.MarkNotificationReadCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;
import club.fiverr.notification.domain.model.Notification;
import club.fiverr.notification.domain.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarkNotificationReadUseCaseImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private MarkNotificationReadUseCaseImpl markNotificationReadUseCase;

    @Test
    void shouldMarkNotificationAsReadAndPersist() {
        Notification unread = NotificationTestFixtures.createUnreadNotification();
        MarkNotificationReadCommand command = NotificationTestFixtures.createValidMarkNotificationReadCommand();

        when(notificationRepository.findById(NotificationTestFixtures.NOTIFICATION_ID))
                .thenReturn(Optional.of(unread));
        when(notificationRepository.save(any(Notification.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        NotificationResult result = markNotificationReadUseCase.execute(command);

        assertTrue(result.read());
        assertNotNull(result.readAt());
        verify(notificationRepository).save(any(Notification.class));
    }
}
