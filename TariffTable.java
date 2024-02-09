import java.util.ArrayList;
import java.util.List;

public class TariffTable {
    private ParkingTariff[] parkingTariffs;
    private int nextFree;

    public TariffTable(int maxSize) {
        this.parkingTariffs = new ParkingTariff[maxSize];
        this.nextFree = 0;
    }

    public void addTariff(TimePeriod period, Money cost) {
        if (nextFree == 0 || period.precedes(parkingTariffs[nextFree - 1].getTimePeriod()) ||
            period.adjacent(parkingTariffs[nextFree - 1].getTimePeriod())) {
            if (nextFree < parkingTariffs.length) {
                parkingTariffs[nextFree] = new ParkingTariff(period, cost);
                nextFree++;
            } else {
                throw new IllegalArgumentException("TariffTable: maximum size reached.");
            }
        } else {
            throw new IllegalArgumentException("Tariff periods must be adjacent.");
        }
    }

    public Money getTariff(Duration lengthOfStay) {
        for (ParkingTariff tariff : parkingTariffs) {
            if (tariff.getTimePeriod().includes(lengthOfStay)) {
                return tariff.getCost();
            }
        }
        throw new IllegalArgumentException("No tariff found for the given length of stay.");
    }

   public String toString() {
    StringBuilder sb = new StringBuilder();
    boolean firstTariff = true; 

    for (ParkingTariff tariff : parkingTariffs) {
        if (tariff != null) {
            if (!firstTariff) {
                sb.append("\n");             }
            sb.append(tariff);
            firstTariff = false;
        }
    }
    return sb.toString();
}
}
