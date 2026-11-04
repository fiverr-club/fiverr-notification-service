package club.fiverr.notification.infrastructure.persistence.mapper;

import club.fiverr.notification.domain.model.Notification;
import club.fiverr.notification.infrastructure.persistence.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationPersistenceMapper {

    public NotificationEntity toEntity(Notification domain) {
        NotificationEntity entity = new NotificationEntity();
        entity.setId(domain.getId());
        entity.setRecipientUserId(domain.getRecipientUserId());
        entity.setType(domain.getType());
        entity.setTitle(domain.getTitle());
        entity.setMessage(domain.getMessage());
        entity.setChannel(domain.getChannel());
        entity.setRead(domain.isRead());
        entity.setReadAt(domain.getReadAt());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }

    public Notification toDomain(NotificationEntity entity) {
        return Notification.restore(
                entity.getId(),
                entity.getRecipientUserId(),
                entity.getType(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getChannel(),
                entity.isRead(),
                entity.getReadAt(),
                entity.getCreatedAt()
        );
    }
}
