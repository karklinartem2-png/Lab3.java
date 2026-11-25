package model;

import exception.InvalidVanException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class VanService {

    private final String clientName;
    private final double basePrice;

    private final List<Loader> hiredLoaders = new ArrayList<>();

    private static final Pattern EMPTY_PATTERN = Pattern.compile("^\\s*$");

    public VanService(String clientName, double basePrice){
        this.clientName = clientName;
        if(basePrice <= 0){
            throw new InvalidVanException("[Система] ВНИМАНИЕ: попытка присвоить недопустимое значение. Передано basePrice = " + basePrice);
        }
        if(clientName == null || EMPTY_PATTERN.matcher(clientName).matches()){
            throw new InvalidVanException("[Система] ВНИМАНИЕ: попытка пустое имя клиента");
        }
        this.basePrice = basePrice;
    }

    public void addLoader(Loader loader) {
        if (loader == null) {
            throw new IllegalArgumentException("Объъект грузчика равен null!");
        }
        this.hiredLoaders.add(loader);
    }

    public abstract double calculateOrderPrice();

    public String getClientName() {
        return clientName;
    }
    public double getBasePrice(){
        return basePrice;
    }
    public List<Loader> getHiredLoaders() {return hiredLoaders;}
}