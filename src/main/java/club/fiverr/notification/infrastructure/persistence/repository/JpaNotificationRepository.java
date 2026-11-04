package club.fiverr.notification.infrastructure.persistence.repository;

import club.fiverr.notification.domain.model.Notification;
import club.fiverr.notification.domain.repository.NotificationRepository;
import club.fiverr.notification.infrastructure.persistence.entity.NotificationEntity;
import club.fiverr.notification.infrastructure.persistence.mapper.NotificationPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaNotificationRepository implements NotificationRepository {

    private final NotificationJpaRepository jpaRepository;
    private final NotificationPersistenceMapper mapper;

    public JpaNotificationRepository(
            NotificationJpaRepository jpaRepository,
            NotificationPersistenceMapper mapper
    ) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Notification save(Notification notification) {
        NotificationEntity saved = jpaRepository.save(mapper.toEntity(notification));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Notification> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Notification> findByRecipientUserIdOrderByCreatedAtDesc(UUID recipientUserId) {
        return jpaRepository.findByRecipientUserIdOrderByCreatedAtDesc(recipientUserId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
