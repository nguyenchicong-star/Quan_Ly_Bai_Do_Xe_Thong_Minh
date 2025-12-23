
package parking;
public class EV extends Vehicle {
    public EV(String p){ super(p, VehicleType.EV); }
    public double fee(long h){ return h * 12000; }
}
