package club.fiverr.notification.domain.model;

import club.fiverr.notification.NotificationTestFixtures;
import club.fiverr.notification.domain.exception.NotificationDomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificationTest {

    @Test
    void shouldCreateNotificationAndMarkAsRead() {
        Notification notification = Notification.createNew(
                NotificationTestFixtures.RECIPIENT_USER_ID,
                NotificationType.NEW_MESSAGE,
                "New Message Received",
                "You have a new message from buyer_john.",
                NotificationChannel.ALL
        );

        assertNotNull(notification.getId());
        assertFalse(notification.isRead());
        assertNull(notification.getReadAt());
        assertTrue(notification.requiresEmailDelivery());
        assertTrue(notification.requiresPushDelivery());

        notification.markAsRead();
        assertTrue(notification.isRead());
        assertNotNull(notification.getReadAt());
    }

    @Test
    void shouldThrowExceptionWhenTitleIsBlank() {
        assertThrows(NotificationDomainException.class, () -> Notification.createNew(
                NotificationTestFixtures.RECIPIENT_USER_ID,
                NotificationType.SYSTEM_ALERT,
                "   ",
                "Valid message",
                NotificationChannel.IN_APP
        ));
    }
}
