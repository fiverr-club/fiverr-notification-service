package club.fiverr.notification.application.usecase;

import club.fiverr.notification.NotificationTestFixtures;
import club.fiverr.notification.application.dto.command.SendNotificationCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;
import club.fiverr.notification.application.port.out.EmailSenderPort;
import club.fiverr.notification.application.port.out.PushNotificationPort;
import club.fiverr.notification.domain.model.Notification;
import club.fiverr.notification.domain.model.NotificationChannel;
import club.fiverr.notification.domain.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendNotificationUseCaseImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private EmailSenderPort emailSenderPort;

    @Mock
    private PushNotificationPort pushNotificationPort;

    @InjectMocks
    private SendNotificationUseCaseImpl sendNotificationUseCase;

    @Test
    void shouldSaveAndDispatchAllChannelsWhenChannelIsAll() {
        SendNotificationCommand command = NotificationTestFixtures.createValidSendNotificationCommand(NotificationChannel.ALL);
        when(notificationRepository.save(any(Notification.class))).thenAnswer(inv -> inv.getArgument(0));

        NotificationResult result = sendNotificationUseCase.execute(command);

        assertNotNull(result);
        assertEquals(NotificationTestFixtures.RECIPIENT_USER_ID, result.recipientUserId());
        assertFalse(result.read());
        verify(notificationRepository).save(any(Notification.class));
        verify(emailSenderPort).sendEmail(eq(NotificationTestFixtures.RECIPIENT_USER_ID), eq(command.title()), eq(command.message()));
        verify(pushNotificationPort).sendPushNotification(eq(NotificationTestFixtures.RECIPIENT_USER_ID), eq(command.title()), eq(command.message()));
    }

    @Test
    void shouldOnlySaveInAppWhenChannelIsInApp() {
        SendNotificationCommand command = NotificationTestFixtures.createValidSendNotificationCommand(NotificationChannel.IN_APP);
        when(notificationRepository.save(any(Notification.class))).thenAnswer(inv -> inv.getArgument(0));

        NotificationResult result = sendNotificationUseCase.execute(command);

        assertNotNull(result);
        verify(notificationRepository).save(any(Notification.class));
        verify(emailSenderPort, never()).sendEmail(any(), any(), any());
        verify(pushNotificationPort, never()).sendPushNotification(any(), any(), any());
    }
}
