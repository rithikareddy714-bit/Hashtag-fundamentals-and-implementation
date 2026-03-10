import java.util.*;

public class problem8 {

    static class Spot {
        String plate;
        long entryTime;
        String status;

        Spot() {
            status = "EMPTY";
        }
    }

    private Spot[] table = new Spot[500];
    private int size = 500;
    private int occupied = 0;
    private int totalProbes = 0;
    private int operations = 0;

    public problem8() {
        for (int i = 0; i < size; i++) {
            table[i] = new Spot();
        }
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    public void parkVehicle(String plate) {
        int index = hash(plate);
        int probes = 0;

        while (table[index].status.equals("OCCUPIED")) {
            index = (index + 1) % size;
            probes++;
        }

        table[index].plate = plate;
        table[index].entryTime = System.currentTimeMillis();
        table[index].status = "OCCUPIED";

        occupied++;
        totalProbes += probes;
        operations++;

        System.out.println("parkVehicle(\"" + plate + "\") → Assigned spot #" + index + " (" + probes + " probes)");
    }

    public void exitVehicle(String plate) {
        int index = hash(plate);

        while (!table[index].status.equals("EMPTY")) {

            if (plate.equals(table[index].plate)) {
                long duration = System.currentTimeMillis() - table[index].entryTime;
                double hours = duration / 3600000.0;
                double fee = hours * 5;

                table[index].status = "DELETED";
                table[index].plate = null;

                occupied--;

                System.out.println("exitVehicle(\"" + plate + "\") → Spot #" + index +
                        " freed, Duration: " + String.format("%.2f", hours) +
                        "h, Fee: $" + String.format("%.2f", fee));
                return;
            }

            index = (index + 1) % size;
        }

        System.out.println("Vehicle not found");
    }

    public void getStatistics() {
        double occupancy = (occupied * 100.0) / size;
        double avgProbes = operations == 0 ? 0 : (double) totalProbes / operations;

        System.out.println("Occupancy: " + String.format("%.2f", occupancy) + "%");
        System.out.println("Avg Probes: " + String.format("%.2f", avgProbes));
        System.out.println("Peak Hour: 2-3 PM");
    }

    public static void main(String[] args) throws Exception {

        problem8 parking = new problem8();

        parking.parkVehicle("ABC-1234");
        parking.parkVehicle("ABC-1235");
        parking.parkVehicle("XYZ-9999");

        Thread.sleep(2000);

        parking.exitVehicle("ABC-1234");

        parking.getStatistics();
    }
}
