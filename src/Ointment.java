import java.time.LocalDate;

public class Ointment extends Medicine {
    private int grams;

    public Ointment(String medicineName, String laboratoryName, boolean generic, int barCode, double medicinePrice, double medicinePricePublic, LocalDate manufacturingDate, LocalDate expirationDate, int grams) {
        super(medicineName, laboratoryName, generic, barCode, medicinePrice, medicinePricePublic, manufacturingDate, expirationDate);

        this.grams = grams;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    @Override
    public String toString() {
        return super.toString() + "" +
                "Grams: " + grams;
    }
}
