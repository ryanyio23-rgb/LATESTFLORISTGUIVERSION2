public class Order {

    private FlowerPackages flowerPackages;
    private Delivery delivery;

    public Order(FlowerPackages flowerPackage, Delivery delivery) { //want to call from other classes
        this.flowerPackages = flowerPackage;
        this.delivery = delivery;
    }

    public double getTotal() {
        return flowerPackages.getPrice() + delivery.calculateFee();//get data from flowerpackages and delivery
    }

    public FlowerPackages getFlowerPackages() {
        return flowerPackages;//throws back data back to flowerpackages
    }

    public Delivery getDelivery() {
        return delivery;//throws back data back to delivery
    }
}
