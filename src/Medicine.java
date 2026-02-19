import java.time.LocalDate;

public class Medicine {

    private String medicineName;
    private String laboratoryName;
    private boolean generic;
    private int barCode;
    private double medicinePrice;
    private double medicinePricePublic;
    private LocalDate manufacturingDate;
    private LocalDate expirationDate;

    public Medicine(String medicineName, String laboratoryName, boolean generic,
                    int barCode, double medicinePrice, double medicinePricePublic,
                    LocalDate manufacturingDate, LocalDate expirationDate) {

        this.medicineName = medicineName;
        this.laboratoryName = laboratoryName;
        this.generic = generic;
        this.barCode = barCode;
        this.medicinePrice = medicinePrice;
        this.medicinePricePublic = medicinePricePublic;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Medicine: " + medicineName +
                ", Laboratory: " + laboratoryName +
                ", Generic: " + generic +
                ", BarCode: " + barCode +
                ", Cost: " + medicinePrice +
                ", Public Price: " + medicinePricePublic +
                ", Manufacturing Date: " + manufacturingDate +
                ", Expiration Date: " + expirationDate;
    }
}
