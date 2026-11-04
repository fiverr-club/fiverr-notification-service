package club.fiverr.notification.presentation.rest.mapper;

import club.fiverr.notification.application.dto.command.SendNotificationCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;
import club.fiverr.notification.domain.model.NotificationChannel;
import club.fiverr.notification.presentation.rest.request.SendNotificationRequest;
import club.fiverr.notification.presentation.rest.response.NotificationResponse;

public final class NotificationRestMapper {

    private NotificationRestMapper() {
    }

    public static SendNotificationCommand toCommand(SendNotificationRequest request) {
        return new SendNotificationCommand(
                request.recipientUserId(),
                request.type(),
                request.title(),
                request.message(),
                request.channel() != null ? request.channel() : NotificationChannel.IN_APP
        );
    }

    public static NotificationResponse toResponse(NotificationResult result) {
        return new NotificationResponse(
                result.id(),
                result.recipientUserId(),
                result.type(),
                result.title(),
                result.message(),
                result.channel(),
                result.read(),
                result.readAt(),
                result.createdAt()
        );
    }
}
