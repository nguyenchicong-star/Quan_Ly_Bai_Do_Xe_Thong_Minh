
package parking.storage;

import parking.*;
import java.io.*;
import java.util.*;
// CSV file storage for parking data
public class FileStorageService {

    private static final String DATA_DIR = "data/";

    public static void saveLogs(Collection<ParkingLog> logs) {
        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(DATA_DIR + "parking_logs.csv"))) {
            bw.write("plate,spotId,timeIn\n");
            for (ParkingLog log : logs) {
                bw.write(log.plate + "," + log.spot.id + "," + log.in + "\n");
            }
        } catch (IOException e) {
            System.out.println("Lỗi lưu parking_logs.csv");
        }
    }

    public static void saveRevenue(double revenue) {
        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(DATA_DIR + "revenue.csv"))) {
            bw.write("total\n");
            bw.write(String.valueOf(revenue));
        } catch (IOException e) {
            System.out.println("Lỗi lưu revenue.csv");
        }
    }

    public static double loadRevenue() {
        File f = new File(DATA_DIR + "revenue.csv");
        if (!f.exists()) return 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            br.readLine();
            return Double.parseDouble(br.readLine());
        } catch (Exception e) {
            return 0;
        }
    }
}
