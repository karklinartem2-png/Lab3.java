package service.impl;
import service.NotificationService;

public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("Получен некорректный телефон. [SMS] не было доставлено!");
    }
}