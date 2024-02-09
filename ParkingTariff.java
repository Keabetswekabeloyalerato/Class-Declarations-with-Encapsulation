public class ParkingTariff {
    private TimePeriod timePeriod;
    private Money cost;

    public ParkingTariff(TimePeriod timePeriod, Money cost) {
        this.timePeriod = timePeriod;
        this.cost = cost;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Money getCost() {
        return cost;
    }

    public void setCost(Money cost) {
        this.cost = cost;
    }

    public String toString() {
        return timePeriod + " : " + cost;
    }
}
