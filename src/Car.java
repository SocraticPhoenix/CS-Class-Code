public class Car {
    private int mileage;
    private String model;

    public Car(String model) {
        this.model = model;
        this.mileage = 0;
    }

    public Car() {
        this("Hyundai");
    }

    public Car(String model, int mileage) {
        this(model);
        this.mileage = mileage;
    }

    public String getModel() {
        return this.model;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

}
