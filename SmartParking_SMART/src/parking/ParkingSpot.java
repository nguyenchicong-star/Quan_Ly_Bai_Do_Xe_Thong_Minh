
package parking;
public class ParkingSpot {
    public String id;
    public SpotType type;
    public boolean available = true;
    public ParkingSpot(String id, SpotType type){
        this.id = id;
        this.type = type;
    }
    public boolean suitable(VehicleType v){
        if(type == SpotType.EV) return v == VehicleType.EV;
        return true;
    }
}
