
package parking;
import java.time.*;
public class ParkingLog {
    public String plate;
    public ParkingSpot spot;
    public LocalDateTime in;
    public ParkingLog(String p, ParkingSpot s){
        plate = p;
        spot = s;
        in = LocalDateTime.now();
    }
}
