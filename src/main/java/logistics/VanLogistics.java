package logistics;

import exception.DuplicateClientException;
import exception.InvalidEmailException;
import exception.InvalidVanException;
import exception.VanServiceNotFoundException;
import model.VanService;
import service.NotificationService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class VanLogistics {
    private final Map<String, VanService> database = new HashMap<>();
    private final NotificationService notificationService;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public VanLogistics(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerVanService(String email, VanService serv) throws InvalidEmailException{
        validateEmail(email);

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

    private void validateEmail(String email) throws InvalidEmailException {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Некорректный формат email: " + email);
        }
    }
}