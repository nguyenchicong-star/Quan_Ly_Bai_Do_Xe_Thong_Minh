// Base class for all vehicle types
// Base vehicle class
package parking;
public abstract class Vehicle {
    protected String plate;
    protected VehicleType type;
    public Vehicle(String plate, VehicleType type){
        this.plate = plate;
        this.type = type;
    }
    public VehicleType getType(){ return type; }
    public abstract double fee(long hours);
}
