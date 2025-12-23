package parking;
import parking.storage.FileStorageService;

import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ParkingService service = new ParkingService();

        while(true){
            System.out.println("\n===== SMART PARKING =====");
            System.out.println("1. Vào bãi (gợi ý chỗ)");
            System.out.println("2. Ra bãi & thanh toán");
            System.out.println("3. Danh sách xe đang đậu");
            System.out.println("4. Gợi ý chỗ trống theo loại xe");
            System.out.println("5. Thống kê doanh thu");
            System.out.println("6. Thống kê xe EV");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int c = sc.nextInt();
            sc.nextLine();

            switch(c){
                case 1:
                    System.out.print("Biển số: ");
                    service.enter(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Biển số: ");
                    service.exit(sc.nextLine());
                    break;
                case 3:
                    service.listActiveVehicles();
                    break;
                case 4:
                    System.out.print("Nhập loại xe (CAR/MOTORBIKE/EV): ");
                    service.suggestSpotByType(sc.nextLine());
                    break;
                case 5:
                    service.showRevenue();
                    break;
                case 6:
                    service.countEV();
                    break;
                case 0:
                    FileStorageService.saveLogs(service.getActiveLogs());
                    FileStorageService.saveRevenue(service.getRevenue());
                    System.out.println("Đã lưu dữ liệu. Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }
}
