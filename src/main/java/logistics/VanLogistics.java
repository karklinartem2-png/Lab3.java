package logistics;

import exception.DuplicateClientException;
import exception.VanServiceNotFoundException;
import model.VanService;
import service.NotificationService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class VanLogistics {
    private final Map<String, VanService> database = new HashMap<>();
    private final NotificationService notificationService;

    public VanLogistics(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerVanService(String email, VanService serv) {
        database.put(email, serv);
    }

    public void processPaymentByEmail(String email) throws VanServiceNotFoundException {
        VanService serv = database.get(email);

        if (serv == null) {
            throw new VanServiceNotFoundException("Клиент с email " + email + " не зарегистрирован!");
        }

        double finalPrice = serv.calculateOrderPrice();
        String message = String.format("Уважаемый %s, списание за заказ прошло успешно. Итоговая сумма: %.2f руб.",
                serv.getClientName(), finalPrice);

        notificationService.sendNotification(message, email);
    }
}