public class CustomMadePackages extends FlowerPackages {

    private int flowerCount;

    public CustomMadePackages(int flowerCount) {
        super("Custom Flower Package");
        this.flowerCount = flowerCount;
        calculatePrice();
    }

    @Override
    public void calculatePrice() {
        if (flowerCount >= 1 && flowerCount <= 10) {
            price = flowerCount * 10.0;
        } else if (flowerCount >= 11 && flowerCount <= 50) {
            price = flowerCount * 9.50;
        } else if (flowerCount > 50) {
            price = flowerCount * 8.00;
        } else {
            price = 0.0;
        }
        price += 10.0;
    }

    public int getFlowerCount() {
        return flowerCount;
    }

    @Override
    public int getQuantity() {
        return 1; // custom package ignores quantity
    }
}
