import java.util.HashMap;
import java.util.Map;

public class ReadyMadePackage extends FlowerPackages {

    private static final Map<String, Double> PRICE_MAP = new HashMap<>();

    static {
        PRICE_MAP.put("Flower Bouquet", 228.00);
        PRICE_MAP.put("Graduation Bouquet (Mini)", 58.00);
        PRICE_MAP.put("Flower Stand", 178.00);
        PRICE_MAP.put("Graduation Bouquet (Flower)", 128.00);
        PRICE_MAP.put("Graduation Bouquet (Toy)", 188.00);
        PRICE_MAP.put("Wedding Bouquet Mini", 68.00);
        PRICE_MAP.put("Wedding Bouquet", 168.00);
    }

    public ReadyMadePackage(String name) {
        super(name.trim()); // 5) this will call the parent class and store string name
        calculatePrice();
    }

    @Override
    public void calculatePrice() {
        price = PRICE_MAP.getOrDefault(getName(), 0.0); //getName() will go to parent class and get name
        //example: getName() returned "Flower Bouquet" and it will look for the price in Hasmap and it will find price =228 and store price = 228
    }
}
