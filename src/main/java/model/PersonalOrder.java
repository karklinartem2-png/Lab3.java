package model;

public class PersonalOrder extends VanService {

    public PersonalOrder(String clientName, double basePrice){
        super(clientName, basePrice);
    }

    @Override
    public double calculateOrderPrice() {
        double totalPrice = getBasePrice();
        for (Loader loader: getHiredLoaders()) {
            totalPrice += loader.getTotalPrice();
        }
        return totalPrice;
    }
}
