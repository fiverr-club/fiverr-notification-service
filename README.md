# Fiverr Notification Service (`fiverr-notification-service`)

Production-grade **Multi-Channel Notification Microservice** for Fiverr Club, built with **Java 21**, **Spring Boot 3.3.5**, **Domain-Driven Design (DDD)**, and **Hexagonal Architecture (Ports and Adapters)**.

## Key Features
- **Multi-Channel Delivery**:
  - `IN_APP`: Persistent user notification feed with read/unread tracking.
  - `EMAIL`: Outbound email notifications via `EmailSenderPort`.
  - `PUSH`: Outbound mobile/web push notifications via `PushNotificationPort`.
  - `ALL`: Simultaneous In-App, Email, and Push delivery.
- **Domain-Rich Notification Types**: `ORDER_CREATED`, `ORDER_DELIVERED`, `ORDER_COMPLETED`, `NEW_MESSAGE`, `REVIEW_RECEIVED`, `CUSTOM_OFFER_RECEIVED`, `SYSTEM_ALERT`.

## Architecture Structure (`club.fiverr.notification`)
```text
club.fiverr.notification/
├── presentation/rest/
│   ├── NotificationController.java
│   ├── request/SendNotificationRequest.java
│   ├── response/NotificationResponse.java
│   └── mapper/NotificationRestMapper.java
├── application/
│   ├── dto/command/ (SendNotificationCommand, GetUserNotificationsCommand, MarkNotificationReadCommand)
│   ├── dto/result/  (NotificationResult)
│   ├── mapper/      (NotificationResultMapper)
│   ├── port/in/     (SendNotificationUseCase, GetUserNotificationsUseCase, MarkNotificationReadUseCase)
│   ├── port/out/    (EmailSenderPort, PushNotificationPort)
│   └── usecase/     (SendNotificationUseCaseImpl, GetUserNotificationsUseCaseImpl, MarkNotificationReadUseCaseImpl)
├── domain/
│   ├── model/       (Notification, NotificationType, NotificationChannel)
│   ├── repository/  (NotificationRepository)
│   └── exception/   (NotificationDomainException, NotificationNotFoundException)
└── infrastructure/
    ├── persistence/
    │   ├── entity/     (NotificationEntity)
    │   ├── mapper/     (NotificationPersistenceMapper)
    │   └── repository/ (JpaNotificationRepository, NotificationJpaRepository)
    └── adapter/        (SmtpEmailSenderAdapter, FcmPushNotificationAdapter)
```

## Running Locally & Testing
- **Port**: `8088`
- **Health Probes**: `/actuator/health/liveness`, `/actuator/health/readiness`
- **Run Unit Tests**:
  ```bash
  JAVA_HOME=/opt/homebrew/opt/openjdk@21 mvn test
  ```
