public class Delivery {

    private double distance;

    public Delivery(double distance) {
        this.distance = distance;
    }

    public double calculateFee() {
        if (distance <= 5)
            return 5.00;
        else if (distance <= 10)
            return 10.00;
        else
            return 15.00;
    }
}
