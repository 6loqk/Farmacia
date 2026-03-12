import java.time.LocalDate;

public class Syrup extends Medicine {

    private int     milliliters;
    private boolean measuringCup;

    public Syrup(String medicineName, String laboratoryName, boolean generic,
                 int barCode, double medicinePrice, double medicinePricePublic,
                 LocalDate manufacturingDate, LocalDate expirationDate,
                 int milliliters, boolean measuringCup) {
        super(medicineName, laboratoryName, generic, barCode,
                medicinePrice, medicinePricePublic, manufacturingDate, expirationDate);
        this.milliliters  = milliliters;
        this.measuringCup = measuringCup;
    }

    public int     getMilliliters()  { return milliliters; }
    public boolean isMeasuringCup()  { return measuringCup; }

    @Override
    public String toString() {
        return super.toString() +
                "<br><b>Mililitros:</b> "         + milliliters +
                "<br><b>Vaso dosificador:</b> "   + (measuringCup ? "Sí" : "No") +
                "</body></html>";
    }
}