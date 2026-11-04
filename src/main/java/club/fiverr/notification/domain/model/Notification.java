package club.fiverr.notification.domain.model;

import club.fiverr.notification.domain.exception.NotificationDomainException;

import java.time.Instant;
import java.util.UUID;

public class Notification {

    private final UUID id;
    private final UUID recipientUserId;
    private final NotificationType type;
    private final String title;
    private final String message;
    private final NotificationChannel channel;
    private boolean read;
    private Instant readAt;
    private final Instant createdAt;

    private Notification(
            UUID id,
            UUID recipientUserId,
            NotificationType type,
            String title,
            String message,
            NotificationChannel channel,
            boolean read,
            Instant readAt,
            Instant createdAt
    ) {
        if (id == null) {
            throw new NotificationDomainException("Notification ID must not be null");
        }
        if (recipientUserId == null) {
            throw new NotificationDomainException("Recipient user ID must not be null");
        }
        if (type == null) {
            throw new NotificationDomainException("Notification type must not be null");
        }
        if (title == null || title.isBlank()) {
            throw new NotificationDomainException("Notification title must not be blank");
        }
        if (message == null || message.isBlank()) {
            throw new NotificationDomainException("Notification message must not be blank");
        }
        if (channel == null) {
            throw new NotificationDomainException("Notification channel must not be null");
        }

        this.id = id;
        this.recipientUserId = recipientUserId;
        this.type = type;
        this.title = title.trim();
        this.message = message.trim();
        this.channel = channel;
        this.read = read;
        this.readAt = readAt;
        this.createdAt = createdAt != null ? createdAt : Instant.now();
    }

    public static Notification createNew(
            UUID recipientUserId,
            NotificationType type,
            String title,
            String message,
            NotificationChannel channel
    ) {
        return new Notification(
                UUID.randomUUID(),
                recipientUserId,
                type,
                title,
                message,
                channel != null ? channel : NotificationChannel.IN_APP,
                false,
                null,
                Instant.now()
        );
    }

    public static Notification restore(
            UUID id,
            UUID recipientUserId,
            NotificationType type,
            String title,
            String message,
            NotificationChannel channel,
            boolean read,
            Instant readAt,
            Instant createdAt
    ) {
        return new Notification(
                id,
                recipientUserId,
                type,
                title,
                message,
                channel,
                read,
                readAt,
                createdAt
        );
    }

    public void markAsRead() {
        if (!this.read) {
            this.read = true;
            this.readAt = Instant.now();
        }
    }

    public boolean requiresEmailDelivery() {
        return channel == NotificationChannel.EMAIL || channel == NotificationChannel.ALL;
    }

    public boolean requiresPushDelivery() {
        return channel == NotificationChannel.PUSH || channel == NotificationChannel.ALL;
    }

    public UUID getId() {
        return id;
    }

    public UUID getRecipientUserId() {
        return recipientUserId;
    }

    public NotificationType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public boolean isRead() {
        return read;
    }

    public Instant getReadAt() {
        return readAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
