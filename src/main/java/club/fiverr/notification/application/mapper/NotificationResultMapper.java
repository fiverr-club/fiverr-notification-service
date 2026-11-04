package club.fiverr.notification.application.mapper;

import club.fiverr.notification.application.dto.result.NotificationResult;
import club.fiverr.notification.domain.model.Notification;

public final class NotificationResultMapper {

    private NotificationResultMapper() {
    }

    public static NotificationResult toResult(Notification notification) {
        return new NotificationResult(
                notification.getId(),
                notification.getRecipientUserId(),
                notification.getType(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getChannel(),
                notification.isRead(),
                notification.getReadAt(),
                notification.getCreatedAt()
        );
    }
}
