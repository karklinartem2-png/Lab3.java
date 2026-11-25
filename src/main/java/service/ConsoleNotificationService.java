package service;

public class ConsoleNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("[УВЕДОМЛЕНИЕ для " + recipient + "]: " + message);
    }
}
