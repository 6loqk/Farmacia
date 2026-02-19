import java.time.LocalDate;

public class Pill extends Medicine {

    private int tablets;
    private double milligrams;

    public Pill(String medicineName, String laboratoryName, boolean generic, int barCode, double medicinePrice, double medicinePricePublic, LocalDate manufacturingDate, LocalDate expirationDate, int tablets, double milligrams) {
        super(medicineName, laboratoryName, generic, barCode, medicinePrice, medicinePricePublic, manufacturingDate, expirationDate);

        this.tablets = tablets;
        this.milligrams = milligrams;
    }

    public int getTablets() {
        return tablets;
    }

    public void setTablets(int tablets) {
        this.tablets = tablets;
    }

    public double getMilligrams() {
        return milligrams;
    }

    public void setMilligrams(double milligrams) {
        this.milligrams = milligrams;
    }

    @Override
    public String toString() {
        return super.toString() + "" +
                "Tablets: " + tablets + ", " +
                "Milligrams: " + milligrams;
    }
}
