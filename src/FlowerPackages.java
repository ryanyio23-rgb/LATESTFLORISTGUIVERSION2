public abstract class FlowerPackages {

    protected String name;
    protected double price;
    protected int quantity = 1; // default 1

    public FlowerPackages(String name) {
        this.name = name; //6) now user selection for flower package will be stored here as name
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price * quantity; // total price based on quantity
    }

    public void setQuantity(int quantity) {
        if (quantity > 0)
            this.quantity = quantity;//sets quantity must be more than 0
            //using this. allows to replace values and will not get confused if a parameter has same name as field
            //example: quantity, name (considered as field)
            //field is a variable passed to a method or a custructor
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract void calculatePrice();
}
/* public - everywhere (any class can call it)
   protected - subclasses or some packages can call it
   private - only itself can call it
 */