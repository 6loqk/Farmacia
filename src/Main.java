import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Medicine[] inventory = new Medicine[350];
    static int count = 0;

    static Scanner sc = new Scanner(System.in).useLocale(java.util.Locale.US);

    public static void main(String[] args) {

        int opcion;
        do {
            System.out.println("" +
                    "Inventario <-- Farmacia 'El Ahorro' --> \n " +
                    "1. Agregar Pastillas \n " +
                    "2. Agregar Jarabes \n " +
                    "3. Agregar Pomadas \n " +
                    "4. Mostrar Inventario \n " +
                    "5. Cargar roductos \n " +
                    "6. Salir");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    addPills();
                    break;
                case 2:
                    addSyrups();
                    break;
                case 3:
                    addOintment();
                    break;
                case 4:
                    showInventory();
                    break;
                case 5:
                    loadFromFile("src/LoadProducts.txt");
                    break;
                case 6:
                    System.out.println("Saliendo del Programa.");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 6);

    }

    public static void addPills() {
        if (count >= inventory.length) {
            System.out.println("Inventario lleno.");
            return;
        }

        System.out.println("Nombre:");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.println("Laboratorio:");
        String lab = sc.next();

        System.out.println("Es generico? (true/false)");
        boolean generic = sc.nextBoolean();

        System.out.println("Codigo de barras:");
        int barCode = sc.nextInt();

        System.out.println("Precio:");
        double price = sc.nextDouble();

        System.out.println("Precio publico:");
        double publicPrice = sc.nextDouble();

        System.out.println("Numero de tabletas:");
        int tablets = sc.nextInt();

        System.out.println("Miligramos:");
        double mg = sc.nextDouble();

        System.out.println("Año de Fabricación: ");
        int yearF = sc.nextInt();

        System.out.println("Mes de Fabricación: ");
        int monthF = sc.nextInt();

        System.out.println("Dia de Fabricación: ");
        int dayF = sc.nextInt();

        System.out.println("Año de Caducidad: ");
        int yearE = sc.nextInt();

        System.out.println("Mes de Caducidad: ");
        int monthE = sc.nextInt();

        System.out.println("Dia de Caducidad: ");
        int dayE = sc.nextInt();

        LocalDate expirationDate = LocalDate.of(yearE, monthE, dayE);


        LocalDate manufacturingDate = LocalDate.of(yearF, monthF, dayF);

        inventory[count] = new Pill(
                name, lab, generic, barCode,
                price, publicPrice,
                manufacturingDate, expirationDate,
                tablets, mg
        );

        count++;
        System.out.println("Pastilla agregada.");
    }

    public static void addSyrups() {
        if (count >= inventory.length) {
            System.out.println("Inventario lleno.");
            return;
        }

        System.out.println("Nombre:");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.println("Laboratorio:");
        String lab = sc.next();

        System.out.println("Es generico? (true/false)");
        boolean generic = sc.nextBoolean();

        System.out.println("Codigo de barras:");
        int barCode = sc.nextInt();

        System.out.println("Precio:");
        double price = sc.nextDouble();

        System.out.println("Precio publico:");
        double publicPrice = sc.nextDouble();

        System.out.println("Mililitros:");
        int ml = sc.nextInt();

        System.out.println("Incluye vaso medidor? (true/false)");
        boolean cup = sc.nextBoolean();

        System.out.println("Año de Fabricación: ");
        int yearF = sc.nextInt();

        System.out.println("Mes de Fabricación: ");
        int monthF = sc.nextInt();

        System.out.println("Dia de Fabricación: ");
        int dayF = sc.nextInt();

        System.out.println("Año de Caducidad: ");
        int yearE = sc.nextInt();

        System.out.println("Mes de Caducidad: ");
        int monthE = sc.nextInt();

        System.out.println("Dia de Caducidad: ");
        int dayE = sc.nextInt();

        LocalDate expirationDate = LocalDate.of(yearE, monthE, dayE);

        LocalDate manufacturingDate = LocalDate.of(yearF, monthF, dayF);

        inventory[count] = new Syrup(
                name, lab, generic, barCode,
                price, publicPrice, manufacturingDate, expirationDate,
                ml, cup
        );

        count++;
        System.out.println("Jarabe agregado.");
    }

    public static void addOintment() {
        if (count >= inventory.length) {
            System.out.println("Inventario lleno.");
            return;
        }

        System.out.println("Nombre:");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.println("Laboratorio:");
        String lab = sc.next();

        System.out.println("Es generico? (true/false)");
        boolean generic = sc.nextBoolean();

        System.out.println("Codigo de barras:");
        int barCode = sc.nextInt();

        System.out.println("Precio:");
        double price = sc.nextDouble();

        System.out.println("Precio publico:");
        double publicPrice = sc.nextDouble();

        System.out.println("Gramos:");
        int grams = sc.nextInt();

        System.out.println("Año de Fabricación: ");
        int yearF = sc.nextInt();

        System.out.println("Mes de Fabricación: ");
        int monthF = sc.nextInt();

        System.out.println("Dia de Fabricación: ");
        int dayF = sc.nextInt();

        System.out.println("Año de Caducidad: ");
        int yearE = sc.nextInt();

        System.out.println("Mes de Caducidad: ");
        int monthE = sc.nextInt();

        System.out.println("Dia de Caducidad: ");
        int dayE = sc.nextInt();

        LocalDate expirationDate = LocalDate.of(yearE, monthE, dayE);

        LocalDate manufacturingDate = LocalDate.of(yearF, monthF, dayF);

        inventory[count] = new Ointment(
                name, lab, generic, barCode,
                price, publicPrice,
                manufacturingDate, expirationDate,
                grams
        );

        count++;
        System.out.println("Pomada agregada.");
    }

    public static void showInventory() {
        if (count == 0) {
            System.out.println("Inventario vacio.");
            return;
        }

        System.out.println("<-- Pastillas --> \n");
        for (int i = 0; i < count; i++) {
            if (inventory[i] instanceof Pill) {
                System.out.println(inventory[i]);
            }
        }

        System.out.println("<-- Jarabes --> \n");
        for (int i = 0; i < count; i++) {
            if (inventory[i] instanceof Syrup) {
                System.out.println(inventory[i]);
            }
        }

        System.out.println("<-- Pomadas --> \n");
        for (int i = 0; i < count; i++) {
            if (inventory[i] instanceof Ointment) {
                System.out.println(inventory[i]);
            }
        }
    }

    public static void loadFromFile(String fileName) {

        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {

                if (count >= inventory.length) {
                    System.out.println("Inventario lleno.");
                    break;
                }

                String name = fileReader.nextLine();
                if(name.isEmpty()) continue; // salta líneas vacías

                String lab = fileReader.nextLine();
                boolean generic = Boolean.parseBoolean(fileReader.nextLine());
                int barCode = Integer.parseInt(fileReader.nextLine());
                double price = Double.parseDouble(fileReader.nextLine());
                double publicPrice = Double.parseDouble(fileReader.nextLine());

                LocalDate manufacturingDate = LocalDate.parse(fileReader.nextLine());
                LocalDate expirationDate = LocalDate.parse(fileReader.nextLine());

                String extraLine = fileReader.nextLine();

                // 🔎 DETECTAR TIPO

                if (extraLine.contains("TABLETAS")) {

                    int tablets = Integer.parseInt(extraLine.split(" ")[0]);

                    String mgLine = fileReader.nextLine();
                    double mg = Double.parseDouble(mgLine.split(" ")[0]);

                    inventory[count] = new Pill(
                            name, lab, generic, barCode,
                            price, publicPrice,
                            manufacturingDate, expirationDate,
                            tablets, mg
                    );

                } else if (extraLine.contains("ML")) {

                    int ml = Integer.parseInt(extraLine.split(" ")[0]);
                    boolean cup = Boolean.parseBoolean(fileReader.nextLine());

                    inventory[count] = new Syrup(
                            name, lab, generic, barCode,
                            price, publicPrice,
                            manufacturingDate, expirationDate,
                            ml, cup
                    );

                } else if (extraLine.contains("GRAMOS")) {

                    int grams = Integer.parseInt(extraLine.split(" ")[0]);

                    inventory[count] = new Ointment(
                            name, lab, generic, barCode,
                            price, publicPrice,
                            manufacturingDate, expirationDate,
                            grams
                    );
                }

                count++;
            }

            fileReader.close();
            System.out.println("Archivo cargado correctamente.");

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        }
    }


}