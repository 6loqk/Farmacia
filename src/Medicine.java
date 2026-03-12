import java.time.LocalDate;

public abstract class Medicine {

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

    // Getters
    public String getMedicineName()          { return medicineName; }
    public String getLaboratoryName()        { return laboratoryName; }
    public boolean isGeneric()               { return generic; }
    public int getBarCode()                  { return barCode; }
    public double getMedicinePrice()         { return medicinePrice; }
    public double getMedicinePricePublic()   { return medicinePricePublic; }
    public LocalDate getManufacturingDate()  { return manufacturingDate; }
    public LocalDate getExpirationDate()     { return expirationDate; }

    // Setter solo para precio público (modificación de inventario)
    public void setMedicinePricePublic(double p) { this.medicinePricePublic = p; }

    @Override
    public String toString() {
        return "<html><body style='font-family:monospace; font-size:13px; padding:8px'>" +
                "<b>Nombre:</b> "           + medicineName  + "<br>" +
                "<b>Laboratorio:</b> "      + laboratoryName + "<br>" +
                "<b>Genérico:</b> "         + (generic ? "Sí" : "No") + "<br>" +
                "<b>Código de barras:</b> " + barCode        + "<br>" +
                "<b>Costo:</b> $"           + String.format("%.2f", medicinePrice) + "<br>" +
                "<b>Precio público:</b> $"  + String.format("%.2f", medicinePricePublic) + "<br>" +
                "<b>Fabricación:</b> "      + manufacturingDate + "<br>" +
                "<b>Caducidad:</b> "        + expirationDate;
    }
}