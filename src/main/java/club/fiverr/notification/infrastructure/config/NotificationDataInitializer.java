package club.fiverr.notification.infrastructure.config;

import club.fiverr.notification.domain.model.Notification;
import club.fiverr.notification.domain.model.NotificationChannel;
import club.fiverr.notification.domain.model.NotificationType;
import club.fiverr.notification.domain.repository.NotificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotificationDataInitializer implements CommandLineRunner {

    private final NotificationRepository notificationRepository;

    public NotificationDataInitializer(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(String... args) {
        if (!notificationRepository.findAll().isEmpty()) {
            return;
        }

        UUID buyerId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID sellerId = UUID.fromString("22222222-2222-2222-2222-222222222222");

        Notification notification1 = Notification.createNew(
                sellerId,
                NotificationType.ORDER_CREATED,
                "New Enterprise Order Placed",
                "A buyer placed an order for Enterprise Spring Boot 3 Hexagonal DDD Architecture & API Suite.",
                NotificationChannel.ALL
        );
        notification1.markAsRead();
        notificationRepository.save(notification1);

        Notification notification2 = Notification.createNew(
                buyerId,
                NotificationType.CUSTOM_OFFER_RECEIVED,
                "Custom Milestone Offer Received",
                "You received a custom offer ($1,250.00 - 5 days delivery) for your FIVERREX architecture inquiry.",
                NotificationChannel.IN_APP
        );
        notificationRepository.save(notification2);

        Notification notification3 = Notification.createNew(
                sellerId,
                NotificationType.REVIEW_RECEIVED,
                "New 5.0 Star Verified Review",
                "A buyer left a 5.0-star verified review across all 3 criteria on your gig!",
                NotificationChannel.EMAIL
        );
        notificationRepository.save(notification3);
    }
}
