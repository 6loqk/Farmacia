import java.time.LocalDate;

public class Syrup extends Medicine {

    private int milliliters;
    private boolean measuringCup;

    public Syrup(String medicineName, String laboratoryName, boolean generic, int barCode, double medicinePrice, double medicinePricePublic, LocalDate manufacturingDate, LocalDate expirationDate, int milliliters, boolean measuringCup) {
        super(medicineName, laboratoryName, generic, barCode, medicinePrice, medicinePricePublic, manufacturingDate, expirationDate);

        this.milliliters = milliliters;
        this.measuringCup = measuringCup;
    }

    public int getMilliliters() {
        return milliliters;
    }

    public void setMilliliters(int milliliters) {
        this.milliliters = milliliters;
    }

    public boolean isMeasuringCup() {
        return measuringCup;
    }

    public void setMeasuringCup(boolean measuringCup) {
        this.measuringCup = measuringCup;
    }

    @Override
    public String toString() {
        return super.toString() + "" +
                "Milliliters: " + milliliters + ", " +
                "Measuring Cup: " + measuringCup;
    }
}
