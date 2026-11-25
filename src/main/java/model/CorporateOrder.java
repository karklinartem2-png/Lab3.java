package model;

public class CorporateOrder extends VanService{
    private final double discountRate;

    public CorporateOrder(String clientName, double basePrice, double discountRate) {
        super(clientName, basePrice);
        this.discountRate = discountRate;
    }

    @Override
    public double calculateOrderPrice() {
        double totalPrice = getBasePrice();
        for (Loader loader : getHiredLoaders()) {
            totalPrice += loader.getTotalPrice();
        }
        return totalPrice * (1 - discountRate);
    }
}
