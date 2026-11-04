package club.fiverr.notification.presentation.rest;

import club.fiverr.notification.application.dto.command.GetUserNotificationsCommand;
import club.fiverr.notification.application.dto.command.MarkNotificationReadCommand;
import club.fiverr.notification.application.dto.result.NotificationResult;
import club.fiverr.notification.application.mapper.NotificationResultMapper;
import club.fiverr.notification.application.port.in.GetUserNotificationsUseCase;
import club.fiverr.notification.application.port.in.MarkNotificationReadUseCase;
import club.fiverr.notification.application.port.in.SendNotificationUseCase;
import club.fiverr.notification.domain.repository.NotificationRepository;
import club.fiverr.notification.presentation.rest.mapper.NotificationRestMapper;
import club.fiverr.notification.presentation.rest.request.SendNotificationRequest;
import club.fiverr.notification.presentation.rest.response.NotificationResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final SendNotificationUseCase sendNotificationUseCase;
    private final GetUserNotificationsUseCase getUserNotificationsUseCase;
    private final MarkNotificationReadUseCase markNotificationReadUseCase;
    private final NotificationRepository notificationRepository;

    public NotificationController(
            SendNotificationUseCase sendNotificationUseCase,
            GetUserNotificationsUseCase getUserNotificationsUseCase,
            MarkNotificationReadUseCase markNotificationReadUseCase,
            NotificationRepository notificationRepository
    ) {
        this.sendNotificationUseCase = sendNotificationUseCase;
        this.getUserNotificationsUseCase = getUserNotificationsUseCase;
        this.markNotificationReadUseCase = markNotificationReadUseCase;
        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        List<NotificationResponse> responses = notificationRepository.findAll()
                .stream()
                .map(NotificationResultMapper::toResult)
                .map(NotificationRestMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> sendNotification(@Valid @RequestBody SendNotificationRequest request) {
        NotificationResult result = sendNotificationUseCase.execute(NotificationRestMapper.toCommand(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(NotificationRestMapper.toResponse(result));
    }

    @GetMapping("/users/{recipientUserId}")
    public ResponseEntity<List<NotificationResponse>> getUserNotifications(@PathVariable UUID recipientUserId) {
        List<NotificationResponse> responses = getUserNotificationsUseCase
                .execute(new GetUserNotificationsCommand(recipientUserId))
                .stream()
                .map(NotificationRestMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<NotificationResponse> markAsRead(
            @PathVariable UUID notificationId,
            @RequestParam(required = false) UUID recipientUserId
    ) {
        NotificationResult result = markNotificationReadUseCase
                .execute(new MarkNotificationReadCommand(notificationId, recipientUserId));
        return ResponseEntity.ok(NotificationRestMapper.toResponse(result));
    }
}
