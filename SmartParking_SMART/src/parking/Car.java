
package parking;
public class Car extends Vehicle {
    public Car(String p){ super(p, VehicleType.CAR); }
    public double fee(long h){ return h * 10000; }
}
