import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    static Pill[] pills = new Pill[150];
    static Syrup[] syrups = new Syrup[100];
    static Ointment[] ointments = new Ointment[100];

    static int pillCount = 0;
    static int syrupCount = 0;
    static int ointCount = 0;

    static Scanner sc = new Scanner(System.in);
    static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static void loadFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String name = fileReader.nextLine().trim();
                if (name.isEmpty()) continue;

                String lab = fileReader.nextLine().trim();
                boolean generic = Boolean.parseBoolean(fileReader.nextLine().trim());
                int barCode = Integer.parseInt(fileReader.nextLine().trim());
                double cost = Double.parseDouble(fileReader.nextLine().trim());
                double publicPrice = Double.parseDouble(fileReader.nextLine().trim());
                LocalDate manuf = LocalDate.parse(fileReader.nextLine().trim());
                LocalDate expir = LocalDate.parse(fileReader.nextLine().trim());

                String extraLine = fileReader.nextLine().trim();

                if (extraLine.contains("TABLETAS")) {
                    int tablets = Integer.parseInt(extraLine.split(" ")[0]);
                    double mg = Double.parseDouble(fileReader.nextLine().trim().split(" ")[0]);

                    if (pillCount < pills.length) {
                        pills[pillCount++] = new Pill(
                                name, lab, generic, barCode,
                                cost, publicPrice, manuf, expir,
                                tablets, mg
                        );
                    }

                } else if (extraLine.contains("ML")) {
                    int ml = Integer.parseInt(extraLine.split(" ")[0]);
                    boolean cup = Boolean.parseBoolean(fileReader.nextLine().trim());

                    if (syrupCount < syrups.length) {
                        syrups[syrupCount++] = new Syrup(
                                name, lab, generic, barCode,
                                cost, publicPrice, manuf, expir,
                                ml, cup
                        );
                    }

                } else if (extraLine.contains("GRAMOS")) {
                    int grams = Integer.parseInt(extraLine.split(" ")[0]);

                    if (ointCount < ointments.length) {
                        ointments[ointCount++] = new Ointment(
                                name, lab, generic, barCode,
                                cost, publicPrice, manuf, expir,
                                grams
                        );
                    }
                }
            }

            fileReader.close();
            System.out.println("  ✔ Archivo cargado: " + fileName);

        } catch (FileNotFoundException e) {
            System.out.println("  Archivo no encontrado: " + fileName);
        }
    }

    public static void main(String[] args) {
        loadFromFile("LoadProducts.txt");
        int op;
        do {
            op = mainMenu();
            switch (op) {
                case 1 -> menuAdd();
                case 2 -> menuDelete();
                case 3 -> menuModify();
                case 4 -> menuPrintPatent();
                case 5 -> menuPrintGeneric();
                case 6 -> menuSummary();
                case 7 -> menuSearch();
                case 8 -> menuSort();
                case 9 -> exitProgram();
                default -> System.out.println("  Opción no válida.\n");
            }
        } while (op != 9);
    }

    static String readString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    static int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un número entero válido.");
            }
        }
    }

    static double readDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un número válido.");
            }
        }
    }

    static boolean readYesNo(String msg) {
        while (true) {
            System.out.print(msg + " (s/n): ");
            String r = sc.nextLine().trim().toLowerCase();
            if (r.equals("s") || r.equals("si") || r.equals("sí")) return true;
            if (r.equals("n") || r.equals("no")) return false;
            System.out.println("  Responda s o n.");
        }
    }

    static LocalDate readDate(String msg) {
        while (true) {
            System.out.print(msg + " (dd/MM/yyyy): ");
            try {
                return LocalDate.parse(sc.nextLine().trim(), FMT);
            } catch (DateTimeParseException e) {
                System.out.println("  Formato incorrecto. Use dd/MM/yyyy.");
            }
        }
    }

    static void line() {
        System.out.println("─".repeat(72));
    }

    static void title(String t) {
        line();
        System.out.println("  " + t);
        line();
    }

    static String compactPill(Pill p) {
        return String.format("%-28s | %-16s | $%7.2f | %-8s | %d tabs, %.0fmg",
                p.getMedicineName(), p.getLaboratoryName(), p.getMedicinePricePublic(),
                p.isGeneric() ? "Genérico" : "Patente", p.getTablets(), p.getMilligrams());
    }

    static String compactSyrup(Syrup s) {
        return String.format("%-28s | %-16s | $%7.2f | %-8s | %dml, vaso:%s",
                s.getMedicineName(), s.getLaboratoryName(), s.getMedicinePricePublic(),
                s.isGeneric() ? "Genérico" : "Patente", s.getMilliliters(),
                s.isMeasuringCup() ? "Sí" : "No");
    }

    static String compactOintment(Ointment o) {
        return String.format("%-28s | %-16s | $%7.2f | %-8s | %dg",
                o.getMedicineName(), o.getLaboratoryName(), o.getMedicinePricePublic(),
                o.isGeneric() ? "Genérico" : "Patente", o.getGrams());
    }

    static String compactMed(Medicine m) {
        if (m instanceof Pill) return "[Pastilla] " + compactPill((Pill) m);
        if (m instanceof Syrup) return "[Jarabe  ] " + compactSyrup((Syrup) m);
        if (m instanceof Ointment) return "[Pomada  ] " + compactOintment((Ointment) m);
        return m.toString();
    }

    // ════════════════════════ MENÚ PRINCIPAL ════════════════════════════════
    static int mainMenu() {
        System.out.println();
        title("FARMACIA EL AHORRO  –  MENÚ PRINCIPAL");
        System.out.println("  1. Ingresar medicamento");
        System.out.println("  2. Borrar medicamento");
        System.out.println("  3. Modificar medicamento");
        System.out.println("  4. Imprimir medicamentos de Patente");
        System.out.println("  5. Imprimir medicamentos Genéricos");
        System.out.println("  6. Imprimir Resumen");
        System.out.println("  7. Buscar");
        System.out.println("  8. Ordenar e Imprimir (Burbuja)");
        System.out.println("  9. Salir");
        line();
        return readInt("  Seleccione una opción: ");
    }

    // ══════════════════════════ 1. INGRESAR ═════════════════════════════════
    static void menuAdd() {
        title("INGRESAR MEDICAMENTO");
        System.out.println("  a) Pastilla   b) Jarabe   c) Pomada");
        String op = readString("  Seleccione: ").toLowerCase();
        switch (op) {
            case "a" -> addPill();
            case "b" -> addSyrup();
            case "c" -> addOintment();
            default -> System.out.println("  Opción no válida.");
        }
    }

    static Object[] readCommonFields() {
        String name = readString("  Nombre del medicamento : ");
        String lab = readString("  Laboratorio            : ");
        boolean gen = readYesNo("  ¿Es genérico?          ");
        int barcode = readInt("  Código de barras       : ");
        double cost = readDouble("  Costo                  : $");
        double price = readDouble("  Precio al público      : $");
        LocalDate fab = readDate("  Fecha de fabricación   ");
        LocalDate exp = readDate("  Fecha de caducidad     ");
        return new Object[]{name, lab, gen, barcode, cost, price, fab, exp};
    }

    static void addPill() {
        if (pillCount >= pills.length) {
            System.out.println("  Inventario de pastillas lleno.");
            return;
        }
        title("NUEVA PASTILLA");
        Object[] c = readCommonFields();
        int tabs = readInt("  Número de tabletas en caja: ");
        double mg = readDouble("  Miligramos                : ");
        pills[pillCount++] = new Pill(
                (String) c[0], (String) c[1], (boolean) c[2], (int) c[3],
                (double) c[4], (double) c[5], (LocalDate) c[6], (LocalDate) c[7], tabs, mg);
        System.out.println("  ✔ Pastilla registrada.");
    }

    static void addSyrup() {
        if (syrupCount >= syrups.length) {
            System.out.println("  Inventario de jarabes lleno.");
            return;
        }
        title("NUEVO JARABE");
        Object[] c = readCommonFields();
        int ml = readInt("  Mililitros en el frasco   : ");
        boolean cup = readYesNo("  ¿Incluye vaso dosificador?");
        syrups[syrupCount++] = new Syrup(
                (String) c[0], (String) c[1], (boolean) c[2], (int) c[3],
                (double) c[4], (double) c[5], (LocalDate) c[6], (LocalDate) c[7], ml, cup);
        System.out.println("  ✔ Jarabe registrado.");
    }

    static void addOintment() {
        if (ointCount >= ointments.length) {
            System.out.println("  Inventario de pomadas lleno.");
            return;
        }
        title("NUEVA POMADA");
        Object[] c = readCommonFields();
        int grams = readInt("  Gramos en el tubo          : ");
        ointments[ointCount++] = new Ointment(
                (String) c[0], (String) c[1], (boolean) c[2], (int) c[3],
                (double) c[4], (double) c[5], (LocalDate) c[6], (LocalDate) c[7], grams);
        System.out.println("  ✔ Pomada registrada.");
    }

    // ══════════════════════════ 2. BORRAR ═══════════════════════════════════
    static void menuDelete() {
        title("BORRAR MEDICAMENTO");
        System.out.println("  a) Pastilla   b) Jarabe   c) Pomada");
        String op = readString("  Seleccione: ").toLowerCase();
        switch (op) {
            case "a" -> deletePill();
            case "b" -> deleteSyrup();
            case "c" -> deleteOintment();
            default -> System.out.println("  Opción no válida.");
        }
    }

    static void deletePill() {
        String name = readString("  Nombre de la pastilla a eliminar: ");
        for (int i = 0; i < pillCount; i++) {
            if (pills[i].getMedicineName().equalsIgnoreCase(name)) {
                System.out.println("\n  Encontrado:\n  " + compactPill(pills[i]));
                if (!readYesNo("\n  ¿Confirma eliminar?")) {
                    System.out.println("  Cancelado.");
                    return;
                }
                for (int j = i; j < pillCount - 1; j++) pills[j] = pills[j + 1];
                pills[--pillCount] = null;
                System.out.println("  ✔ Pastilla eliminada.");
                return;
            }
        }
        System.out.println("  No encontrado: " + name);
    }

    static void deleteSyrup() {
        String name = readString("  Nombre del jarabe a eliminar: ");
        for (int i = 0; i < syrupCount; i++) {
            if (syrups[i].getMedicineName().equalsIgnoreCase(name)) {
                System.out.println("\n  Encontrado:\n  " + compactSyrup(syrups[i]));
                if (!readYesNo("\n  ¿Confirma eliminar?")) {
                    System.out.println("  Cancelado.");
                    return;
                }
                for (int j = i; j < syrupCount - 1; j++) syrups[j] = syrups[j + 1];
                syrups[--syrupCount] = null;
                System.out.println("  ✔ Jarabe eliminado.");
                return;
            }
        }
        System.out.println("  No encontrado: " + name);
    }

    static void deleteOintment() {
        String name = readString("  Nombre de la pomada a eliminar: ");
        for (int i = 0; i < ointCount; i++) {
            if (ointments[i].getMedicineName().equalsIgnoreCase(name)) {
                System.out.println("\n  Encontrado:\n  " + compactOintment(ointments[i]));
                if (!readYesNo("\n  ¿Confirma eliminar?")) {
                    System.out.println("  Cancelado.");
                    return;
                }
                for (int j = i; j < ointCount - 1; j++) ointments[j] = ointments[j + 1];
                ointments[--ointCount] = null;
                System.out.println("  ✔ Pomada eliminada.");
                return;
            }
        }
        System.out.println("  No encontrado: " + name);
    }

    // ════════════════════════ 3. MODIFICAR ══════════════════════════════════
    static void menuModify() {
        title("MODIFICAR PRECIOS");
        System.out.println("  a) Precio de una pastilla");
        System.out.println("  b) Precio de TODAS las pastillas (% del costo)");
        System.out.println("  c) Precio de un jarabe");
        System.out.println("  d) Precio de TODOS los jarabes (% del costo)");
        System.out.println("  e) Precio de una pomada");
        System.out.println("  f) Precio de TODAS las pomadas (% del costo)");
        String op = readString("  Seleccione: ").toLowerCase();
        switch (op) {
            case "a" -> modifyOne("Pastilla");
            case "b" -> modifyAll("Pastilla");
            case "c" -> modifyOne("Jarabe");
            case "d" -> modifyAll("Jarabe");
            case "e" -> modifyOne("Pomada");
            case "f" -> modifyAll("Pomada");
            default -> System.out.println("  Opción no válida.");
        }
    }

    static void modifyOne(String type) {
        String name = readString("  Nombre del medicamento: ");
        Medicine m = findByName(type, name);
        if (m == null) {
            System.out.println("  No encontrado.");
            return;
        }
        System.out.printf("  Precio actual: $%.2f%n", m.getMedicinePricePublic());
        double newPrice = readDouble("  Nuevo precio público: $");
        if (!readYesNo("  ¿Confirma el cambio?")) {
            System.out.println("  Cancelado.");
            return;
        }
        m.setMedicinePricePublic(newPrice);
        System.out.println("  ✔ Precio actualizado.");
    }

    static void modifyAll(String type) {
        double pct = readDouble("  Porcentaje sobre el costo (ej. 30 para +30%): ");
        if (!readYesNo("  ¿Confirma modificar TODOS los " + type + "s?")) {
            System.out.println("  Cancelado.");
            return;
        }
        int n = 0;
        switch (type) {
            case "Pastilla" -> {
                for (int i = 0; i < pillCount; i++) {
                    pills[i].setMedicinePricePublic(pills[i].getMedicinePrice() * (1 + pct / 100));
                    n++;
                }
            }
            case "Jarabe" -> {
                for (int i = 0; i < syrupCount; i++) {
                    syrups[i].setMedicinePricePublic(syrups[i].getMedicinePrice() * (1 + pct / 100));
                    n++;
                }
            }
            case "Pomada" -> {
                for (int i = 0; i < ointCount; i++) {
                    ointments[i].setMedicinePricePublic(ointments[i].getMedicinePrice() * (1 + pct / 100));
                    n++;
                }
            }
        }
        System.out.printf("  ✔ %d %ss actualizados con %.1f%% sobre el costo.%n", n, type, pct);
    }

    static Medicine findByName(String type, String name) {
        return switch (type) {
            case "Pastilla" -> {
                for (int i = 0; i < pillCount; i++)
                    if (pills[i].getMedicineName().equalsIgnoreCase(name)) yield pills[i];
                yield null;
            }
            case "Jarabe" -> {
                for (int i = 0; i < syrupCount; i++)
                    if (syrups[i].getMedicineName().equalsIgnoreCase(name)) yield syrups[i];
                yield null;
            }
            default -> {
                for (int i = 0; i < ointCount; i++)
                    if (ointments[i].getMedicineName().equalsIgnoreCase(name)) yield ointments[i];
                yield null;
            }
        };
    }

    // ════════════════════ 4. IMPRIMIR PATENTE ═══════════════════════════════
    static void menuPrintPatent() {
        title("IMPRIMIR MEDICAMENTOS DE PATENTE");
        System.out.println("  a) Pastillas");
        System.out.println("  b) Jarabes");
        System.out.println("  c) Pomadas");
        System.out.println("  d) Todos los medicamentos de Patente");  // REPOSICIÓN
        String op = readString("  Seleccione: ").toLowerCase();
        switch (op) {
            case "a" -> printPills(false);
            case "b" -> printSyrups(false);
            case "c" -> printOintments(false);
            case "d" -> printAllByType(false);   // REPOSICIÓN
            default -> System.out.println("  Opción no válida.");
        }
    }

    static void menuPrintGeneric() {
        title("IMPRIMIR MEDICAMENTOS GENÉRICOS");
        System.out.println("  a) Pastillas");
        System.out.println("  b) Jarabes");
        System.out.println("  c) Pomadas");
        System.out.println("  d) Todos los medicamentos Genéricos");
        String op = readString("  Seleccione: ").toLowerCase();
        switch (op) {
            case "a" -> printPills(true);
            case "b" -> printSyrups(true);
            case "c" -> printOintments(true);
            case "d" -> printAllByType(true);
            default -> System.out.println("  Opción no válida.");
        }
    }

    static void printPills(boolean generic) {
        String label = generic ? "GENÉRICAS" : "PATENTE";
        title("PASTILLAS – " + label);
        int count = 0;
        for (int i = 0; i < pillCount; i++) {
            if (pills[i].isGeneric() == generic) {
                System.out.println("  " + compactPill(pills[i]));
                count++;
            }
        }
        if (count == 0) System.out.println("  (Sin registros)");
        line();
        System.out.println("  Total: " + count);
    }

    static void printSyrups(boolean generic) {
        String label = generic ? "GENÉRICOS" : "PATENTE";
        title("JARABES – " + label);
        int count = 0;
        for (int i = 0; i < syrupCount; i++) {
            if (syrups[i].isGeneric() == generic) {
                System.out.println("  " + compactSyrup(syrups[i]));
                count++;
            }
        }
        if (count == 0) System.out.println("  (Sin registros)");
        line();
        System.out.println("  Total: " + count);
    }

    static void printOintments(boolean generic) {
        String label = generic ? "GENÉRICAS" : "PATENTE";
        title("POMADAS – " + label);
        int count = 0;
        for (int i = 0; i < ointCount; i++) {
            if (ointments[i].isGeneric() == generic) {
                System.out.println("  " + compactOintment(ointments[i]));
                count++;
            }
        }
        if (count == 0) System.out.println("  (Sin registros)");
        line();
        System.out.println("  Total: " + count);
    }

    static void printAllByType(boolean generic) {
        String label = generic ? "GENÉRICOS" : "PATENTE";
        title("TODOS LOS MEDICAMENTOS DE " + label);
        System.out.println("  -- PASTILLAS --");
        int total = 0, c;
        c = 0;
        for (int i = 0; i < pillCount; i++)
            if (pills[i].isGeneric() == generic) {
                System.out.println("  " + compactPill(pills[i]));
                c++;
            }
        total += c;
        System.out.println("  Subtotal: " + c);
        System.out.println("\n  -- JARABES --");
        c = 0;
        for (int i = 0; i < syrupCount; i++)
            if (syrups[i].isGeneric() == generic) {
                System.out.println("  " + compactSyrup(syrups[i]));
                c++;
            }
        total += c;
        System.out.println("  Subtotal: " + c);
        System.out.println("\n  -- POMADAS --");
        c = 0;
        for (int i = 0; i < ointCount; i++)
            if (ointments[i].isGeneric() == generic) {
                System.out.println("  " + compactOintment(ointments[i]));
                c++;
            }
        total += c;
        System.out.println("  Subtotal: " + c);
        line();
        System.out.println("  TOTAL GENERAL: " + total);
    }

    static void menuSummary() {
        title("RESUMEN");
        System.out.println("  a) Medicamentos de Patente");
        System.out.println("  b) Medicamentos Genéricos");
        System.out.println("  c) Resumen total (todos)");
        String op = readString("  Seleccione: ").toLowerCase();
        switch (op) {
            case "a" -> printSummary(false);
            case "b" -> printSummary(true);
            case "c" -> printTotalSummary();
            default -> System.out.println("  Opción no válida.");
        }
    }

    static void printSummary(boolean generic) {
        String label = generic ? "GENÉRICOS" : "PATENTE";
        title("RESUMEN – " + label);
        int[] cnt = new int[3];
        double[] costs = new double[3];
        double[] prices = new double[3];

        for (int i = 0; i < pillCount; i++)
            if (pills[i].isGeneric() == generic) {
                cnt[0]++;
                costs[0] += pills[i].getMedicinePrice();
                prices[0] += pills[i].getMedicinePricePublic();
            }
        for (int i = 0; i < syrupCount; i++)
            if (syrups[i].isGeneric() == generic) {
                cnt[1]++;
                costs[1] += syrups[i].getMedicinePrice();
                prices[1] += syrups[i].getMedicinePricePublic();
            }
        for (int i = 0; i < ointCount; i++)
            if (ointments[i].isGeneric() == generic) {
                cnt[2]++;
                costs[2] += ointments[i].getMedicinePrice();
                prices[2] += ointments[i].getMedicinePricePublic();
            }

        System.out.printf("  %-12s | %5s | %12s | %12s%n", "Tipo", "Cant", "Suma Costos", "Suma Precios");
        line();
        System.out.printf("  %-12s | %5d | $%11.2f | $%11.2f%n", "Pastillas", cnt[0], costs[0], prices[0]);
        System.out.printf("  %-12s | %5d | $%11.2f | $%11.2f%n", "Jarabes", cnt[1], costs[1], prices[1]);
        System.out.printf("  %-12s | %5d | $%11.2f | $%11.2f%n", "Pomadas", cnt[2], costs[2], prices[2]);
        line();
        System.out.printf("  %-12s | %5d | $%11.2f | $%11.2f%n", "TOTAL",
                cnt[0] + cnt[1] + cnt[2], costs[0] + costs[1] + costs[2], prices[0] + prices[1] + prices[2]);
    }

    static void printTotalSummary() {
        title("RESUMEN TOTAL – TODOS LOS MEDICAMENTOS");
        int[] cnt = new int[3];
        double[] costs = new double[3];
        double[] prices = new double[3];

        for (int i = 0; i < pillCount; i++) {
            cnt[0]++;
            costs[0] += pills[i].getMedicinePrice();
            prices[0] += pills[i].getMedicinePricePublic();
        }
        for (int i = 0; i < syrupCount; i++) {
            cnt[1]++;
            costs[1] += syrups[i].getMedicinePrice();
            prices[1] += syrups[i].getMedicinePricePublic();
        }
        for (int i = 0; i < ointCount; i++) {
            cnt[2]++;
            costs[2] += ointments[i].getMedicinePrice();
            prices[2] += ointments[i].getMedicinePricePublic();
        }

        System.out.printf("  %-12s | %5s | %12s | %12s%n", "Tipo", "Cant", "Suma Costos", "Suma Precios");
        line();
        System.out.printf("  %-12s | %5d | $%11.2f | $%11.2f%n", "Pastillas", cnt[0], costs[0], prices[0]);
        System.out.printf("  %-12s | %5d | $%11.2f | $%11.2f%n", "Jarabes", cnt[1], costs[1], prices[1]);
        System.out.printf("  %-12s | %5d | $%11.2f | $%11.2f%n", "Pomadas", cnt[2], costs[2], prices[2]);
        line();
        System.out.printf("  %-12s | %5d | $%11.2f | $%11.2f%n", "TOTAL",
                cnt[0] + cnt[1] + cnt[2], costs[0] + costs[1] + costs[2], prices[0] + prices[1] + prices[2]);
    }

    static void menuSearch() {
        title("BUSCAR MEDICAMENTO");
        System.out.println("  a) Por nombre (formato amplio)");
        System.out.println("  b) Por código de barras (formato amplio)");
        System.out.println("  c) Por año de fabricación (formato compacto)");
        System.out.println("  d) Medicamentos caducados (formato compacto)");
        System.out.println("  e) Entre dos fechas (formato compacto)");
        String op = readString("  Seleccione: ").toLowerCase();
        switch (op) {
            case "a" -> searchByName();
            case "b" -> searchByBarCode();
            case "c" -> searchByYear();
            case "d" -> searchExpired();
            case "e" -> searchBetweenDates();
            default -> System.out.println("  Opción no válida.");
        }
    }

    static void searchByName() {
        String name = readString("  Nombre a buscar: ");
        title("RESULTADOS – NOMBRE CONTIENE: \"" + name + "\"");
        int found = 0;
        for (int i = 0; i < pillCount; i++)
            if (pills[i].getMedicineName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("\n[Pastilla]\n" + pills[i].toString());
                found++;
            }
        for (int i = 0; i < syrupCount; i++)
            if (syrups[i].getMedicineName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("\n[Jarabe]\n" + syrups[i].toString());
                found++;
            }
        for (int i = 0; i < ointCount; i++)
            if (ointments[i].getMedicineName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("\n[Pomada]\n" + ointments[i].toString());
                found++;
            }
        printFoundCount(found);
    }

    static void searchByBarCode() {
        int code = readInt("  Código de barras: ");
        title("RESULTADOS – CÓDIGO: " + code);
        int found = 0;
        for (int i = 0; i < pillCount; i++)
            if (pills[i].getBarCode() == code) {
                System.out.println("\n[Pastilla]\n" + pills[i].toString());
                found++;
            }
        for (int i = 0; i < syrupCount; i++)
            if (syrups[i].getBarCode() == code) {
                System.out.println("\n[Jarabe]\n" + syrups[i].toString());
                found++;
            }
        for (int i = 0; i < ointCount; i++)
            if (ointments[i].getBarCode() == code) {
                System.out.println("\n[Pomada]\n" + ointments[i].toString());
                found++;
            }
        printFoundCount(found);
    }

    static void searchByYear() {
        int year = readInt("  Año de fabricación: ");
        title("MEDICAMENTOS FABRICADOS EN " + year);
        int found = 0;
        for (int i = 0; i < pillCount; i++)
            if (pills[i].getManufacturingDate().getYear() == year) {
                System.out.println("  " + compactPill(pills[i]));
                found++;
            }
        for (int i = 0; i < syrupCount; i++)
            if (syrups[i].getManufacturingDate().getYear() == year) {
                System.out.println("  " + compactSyrup(syrups[i]));
                found++;
            }
        for (int i = 0; i < ointCount; i++)
            if (ointments[i].getManufacturingDate().getYear() == year) {
                System.out.println("  " + compactOintment(ointments[i]));
                found++;
            }
        printFoundCount(found);
    }

    static void searchExpired() {
        title("MEDICAMENTOS CADUCADOS");
        LocalDate today = LocalDate.now();
        int found = 0;
        for (int i = 0; i < pillCount; i++)
            if (pills[i].getExpirationDate().isBefore(today)) {
                System.out.println("  " + compactPill(pills[i]));
                found++;
            }
        for (int i = 0; i < syrupCount; i++)
            if (syrups[i].getExpirationDate().isBefore(today)) {
                System.out.println("  " + compactSyrup(syrups[i]));
                found++;
            }
        for (int i = 0; i < ointCount; i++)
            if (ointments[i].getExpirationDate().isBefore(today)) {
                System.out.println("  " + compactOintment(ointments[i]));
                found++;
            }
        if (found == 0) System.out.println("  No hay medicamentos caducados.");
        else {
            line();
            System.out.println("  Total caducados: " + found);
        }
    }

    static void searchBetweenDates() {
        title("BUSCAR ENTRE DOS FECHAS DE FABRICACIÓN");
        LocalDate from = readDate("  Fecha INICIO");
        LocalDate to = readDate("  Fecha FIN   ");
        if (from.isAfter(to)) {
            System.out.println("  La fecha inicio debe ser anterior a la fecha fin.");
            return;
        }
        int found = 0;
        for (int i = 0; i < pillCount; i++) {
            LocalDate d = pills[i].getManufacturingDate();
            if (!d.isBefore(from) && !d.isAfter(to)) {
                System.out.println("  " + compactPill(pills[i]));
                found++;
            }
        }
        for (int i = 0; i < syrupCount; i++) {
            LocalDate d = syrups[i].getManufacturingDate();
            if (!d.isBefore(from) && !d.isAfter(to)) {
                System.out.println("  " + compactSyrup(syrups[i]));
                found++;
            }
        }
        for (int i = 0; i < ointCount; i++) {
            LocalDate d = ointments[i].getManufacturingDate();
            if (!d.isBefore(from) && !d.isAfter(to)) {
                System.out.println("  " + compactOintment(ointments[i]));
                found++;
            }
        }
        printFoundCount(found);
    }

    static void printFoundCount(int found) {
        if (found == 0) System.out.println("  Sin resultados.");
        else {
            line();
            System.out.println("  Encontrados: " + found);
        }
    }

    static void menuSort() {
        title("ORDENAR E IMPRIMIR – MÉTODO BURBUJA");
        System.out.println("  a) Pastillas");
        System.out.println("  b) Jarabes");
        System.out.println("  c) Pomadas");
        System.out.println("  d) Todos los medicamentos por fecha de fabricación (asc)");
        String op = readString("  Seleccione: ").toLowerCase();
        if (op.equals("d")) {
            sortAllByDate();
            return;
        }
        System.out.println("  1. Por nombre   2. Por precio");
        int crit = readInt("  Criterio: ");
        if (crit < 1 || crit > 2) {
            System.out.println("  Criterio no válido.");
            return;
        }
        switch (op) {
            case "a" -> {
                sortPills(crit);
                printSortedPills();
            }
            case "b" -> {
                sortSyrups(crit);
                printSortedSyrups();
            }
            case "c" -> {
                sortOintments(crit);
                printSortedOintments();
            }
            default -> System.out.println("  Opción no válida.");
        }
    }

    static void sortPills(int crit) {
        for (int i = 0; i < pillCount - 1; i++)
            for (int j = 0; j < pillCount - 1 - i; j++)
                if (compareMed(pills[j], pills[j + 1], crit) > 0) {
                    Pill tmp = pills[j];
                    pills[j] = pills[j + 1];
                    pills[j + 1] = tmp;
                }
    }

    static void sortSyrups(int crit) {
        for (int i = 0; i < syrupCount - 1; i++)
            for (int j = 0; j < syrupCount - 1 - i; j++)
                if (compareMed(syrups[j], syrups[j + 1], crit) > 0) {
                    Syrup tmp = syrups[j];
                    syrups[j] = syrups[j + 1];
                    syrups[j + 1] = tmp;
                }
    }

    static void sortOintments(int crit) {
        for (int i = 0; i < ointCount - 1; i++)
            for (int j = 0; j < ointCount - 1 - i; j++)
                if (compareMed(ointments[j], ointments[j + 1], crit) > 0) {
                    Ointment tmp = ointments[j];
                    ointments[j] = ointments[j + 1];
                    ointments[j + 1] = tmp;
                }
    }

    static int compareMed(Medicine a, Medicine b, int crit) {
        if (crit == 1) return a.getMedicineName().compareToIgnoreCase(b.getMedicineName());
        return Double.compare(a.getMedicinePricePublic(), b.getMedicinePricePublic());
    }

    static void sortAllByDate() {
        title("TODOS LOS MEDICAMENTOS ORDENADOS POR FECHA DE FABRICACIÓN (ASC)");
        int total = pillCount + syrupCount + ointCount;
        Medicine[] all = new Medicine[total];
        int k = 0;
        for (int i = 0; i < pillCount; i++) all[k++] = pills[i];
        for (int i = 0; i < syrupCount; i++) all[k++] = syrups[i];
        for (int i = 0; i < ointCount; i++) all[k++] = ointments[i];


        for (int i = 0; i < total - 1; i++)
            for (int j = 0; j < total - 1 - i; j++)
                if (all[j].getManufacturingDate().isAfter(all[j + 1].getManufacturingDate())) {
                    Medicine tmp = all[j];
                    all[j] = all[j + 1];
                    all[j + 1] = tmp;
                }

        System.out.printf("  %-28s | %-10s | %s%n", "Nombre", "Fab.", "Compacto");
        line();
        for (int i = 0; i < total; i++) {
            System.out.printf("  %s%n", compactMed(all[i]));
        }
        line();
        System.out.println("  Total: " + total);
    }

    static void printSortedPills() {
        title("PASTILLAS ORDENADAS");
        for (int i = 0; i < pillCount; i++) System.out.println("  " + compactPill(pills[i]));
        line();
    }

    static void printSortedSyrups() {
        title("JARABES ORDENADOS");
        for (int i = 0; i < syrupCount; i++) System.out.println("  " + compactSyrup(syrups[i]));
        line();
    }

    static void printSortedOintments() {
        title("POMADAS ORDENADAS");
        for (int i = 0; i < ointCount; i++) System.out.println("  " + compactOintment(ointments[i]));
        line();
    }

    static void exitProgram() {
        if (readYesNo("  ¿Desea salir del sistema?")) {
            System.out.println("\n  Hasta luego. Farmacia El Ahorro.");
            System.exit(0);
        } else {
            System.out.println("  Regresando al menú principal.");
        }
    }
}