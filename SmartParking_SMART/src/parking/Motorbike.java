
package parking;
public class Motorbike extends Vehicle {
    public Motorbike(String p){ super(p, VehicleType.MOTORBIKE); }
    public double fee(long h){ return h * 3000; }
}
