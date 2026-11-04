package club.fiverr.notification;

import club.fiverr.notification.application.dto.command.GetUserNotificationsCommand;
import club.fiverr.notification.application.dto.command.MarkNotificationReadCommand;
import club.fiverr.notification.application.dto.command.SendNotificationCommand;
import club.fiverr.notification.domain.model.Notification;
import club.fiverr.notification.domain.model.NotificationChannel;
import club.fiverr.notification.domain.model.NotificationType;

import java.time.Instant;
import java.util.UUID;

public final class NotificationTestFixtures {

    public static final UUID NOTIFICATION_ID = UUID.fromString("99999999-9999-9999-9999-999999999999");
    public static final UUID RECIPIENT_USER_ID = UUID.fromString("88888888-8888-8888-8888-888888888888");

    private NotificationTestFixtures() {
    }

    public static Notification createUnreadNotification() {
        return Notification.restore(
                NOTIFICATION_ID,
                RECIPIENT_USER_ID,
                NotificationType.ORDER_DELIVERED,
                "Your Order Has Been Delivered!",
                "Seller delivered your logo design order #FO12345.",
                NotificationChannel.ALL,
                false,
                null,
                Instant.parse("2026-02-10T09:30:00Z")
        );
    }

    public static SendNotificationCommand createValidSendNotificationCommand(NotificationChannel channel) {
        return new SendNotificationCommand(
                RECIPIENT_USER_ID,
                NotificationType.ORDER_DELIVERED,
                "Your Order Has Been Delivered!",
                "Seller delivered your logo design order #FO12345.",
                channel
        );
    }

    public static GetUserNotificationsCommand createValidGetUserNotificationsCommand() {
        return new GetUserNotificationsCommand(RECIPIENT_USER_ID);
    }

    public static MarkNotificationReadCommand createValidMarkNotificationReadCommand() {
        return new MarkNotificationReadCommand(NOTIFICATION_ID, RECIPIENT_USER_ID);
    }
}
