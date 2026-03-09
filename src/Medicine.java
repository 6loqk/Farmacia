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

    // ── Getters ─────────────────────────────────────────────────────────────
    public String getMedicineName() {
        return medicineName;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public boolean isGeneric() {
        return generic;
    }

    public int getBarCode() {
        return barCode;
    }

    public double getMedicinePrice() {
        return medicinePrice;
    }

    public double getMedicinePricePublic() {
        return medicinePricePublic;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    // ── Setters ─────────────────────────────────────────────────────────────
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public void setMedicinePrice(double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public void setMedicinePricePublic(double p) {
        this.medicinePricePublic = p;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "  Nombre          : " + medicineName +
                "\n  Laboratorio     : " + laboratoryName +
                "\n  Genérico        : " + (generic ? "Sí" : "No") +
                "\n  Código de barras: " + barCode +
                "\n  Costo           : $" + String.format("%.2f", medicinePrice) +
                "\n  Precio público  : $" + String.format("%.2f", medicinePricePublic) +
                "\n  Fecha fabricación: " + manufacturingDate +
                "\n  Fecha caducidad : " + expirationDate;
    }
}