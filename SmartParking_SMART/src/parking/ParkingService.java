
package parking;
import java.util.*;
import java.time.*;
public class ParkingService {
    List<ParkingSpot> spots = new ArrayList<>();
    Map<String, ParkingLog> active = new HashMap<>();
    double revenue = 0;
    public ParkingService(){
        spots.add(new ParkingSpot("S01", SpotType.STANDARD));
        spots.add(new ParkingSpot("V01", SpotType.VIP));
        spots.add(new ParkingSpot("E01", SpotType.EV));
    }
    ParkingSpot suggestSpot(VehicleType type){
        for(ParkingSpot s : spots)
            if(s.available && s.suitable(type)) return s;
        return null;
    }
    Vehicle createVehicle(String plate){
        if(plate.startsWith("EV")) return new EV(plate);
        if(plate.endsWith("M")) return new Motorbike(plate);
        return new Car(plate);
    }
    public void enter(String plate){
        Vehicle v = createVehicle(plate);
        ParkingSpot s = suggestSpot(v.getType());
        if(s == null){
            System.out.println("Khong co cho phu hop");
            return;
        }
        s.available = false;
        active.put(plate, new ParkingLog(plate, s));
        System.out.println("Xe vao bai | Goi y cho: " + s.id);
    }
    public void exit(String plate){
        ParkingLog log = active.remove(plate);
        if(log == null){
            System.out.println("Khong tim thay xe");
            return;
        }
        long h = Duration.between(log.in, LocalDateTime.now()).toHours()+1;
        Vehicle v = createVehicle(plate);
        double fee = v.fee(h);
        revenue += fee;
        log.spot.available = true;
        System.out.println("Xe ra bai | Phi: " + fee);
    }
    public double getRevenue() { return revenue; }
    public Collection<ParkingLog> getActiveLogs() { return active.values(); }
    public void stats(){
        System.out.println("Xe dang dau: " + active.size());
        System.out.println("Doanh thu: " + revenue);
    }

    // Danh sách xe đang đậu
    public void listActiveVehicles() {
        if (active.isEmpty()) {
            System.out.println("Không có xe nào đang đậu");
            return;
        }
        for (ParkingLog log : active.values()) {
            System.out.println(
                    "Biển số: " + log.plate +
                            " | Chỗ: " + log.spot.id +
                            " | Loại chỗ: " + log.spot.type
            );
        }
    }

    // Gợi ý chỗ trống theo loại xe
    public void suggestSpotByType(String typeInput) {
        VehicleType type;
        try {
            type = VehicleType.valueOf(typeInput.toUpperCase());
        } catch (Exception e) {
            System.out.println("Loại xe không hợp lệ (CAR / MOTORBIKE / EV)");
            return;
        }

        ParkingSpot spot = suggestSpot(type);
        if (spot == null) {
            System.out.println("❌ Không có chỗ phù hợp");
        } else {
            System.out.println("✅ Gợi ý chỗ: " + spot.id + " (" + spot.type + ")");
        }
    }

    // Thống kê xe EV
    public void countEV() {
        long count = active.values().stream()
                .filter(log -> log.spot.type == SpotType.EV)
                .count();
        System.out.println("⚡ Số xe EV đang đậu: " + count);
    }

    // Doanh thu chi tiết
    public void showRevenue() {
        System.out.println("💰 Tổng doanh thu: " + revenue);
    }

}
