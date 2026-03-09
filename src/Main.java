import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Pill[] pastillas = new Pill[150];
    static Syrup[] jarabes = new Syrup[100];
    static Ointment[] pomadas = new Ointment[100];

    static int numPastillas = 0;
    static int numJarabes = 0;
    static int numPomadas = 0;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarArchivo("src/LoadProducts.txt");

        int opcion;
        do {
            System.out.println("\n========================================");
            System.out.println("   FARMACIA EL AHORRO - MENU PRINCIPAL  ");
            System.out.println("========================================");
            System.out.println("1. Ingresar medicamento");
            System.out.println("2. Borrar medicamento");
            System.out.println("3. Modificar medicamento");
            System.out.println("4. Imprimir medicamentos de Patente");
            System.out.println("5. Imprimir medicamentos Genericos");
            System.out.println("6. Imprimir Resumen");
            System.out.println("7. Buscar");
            System.out.println("8. Ordenar e Imprimir");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: menuIngresar(); break;
                case 2: menuBorrar(); break;
                case 3: menuModificar(); break;
                case 4: menuPatente(); break;
                case 5: menuGenerico(); break;
                case 6: menuResumen(); break;
                case 7: menuBuscar(); break;
                case 8: menuOrdenar(); break;
                case 9: menuSalir(); break;
                default: System.out.println("Opcion no valida.");
            }

        } while (opcion != 9);
    }

    static void cargarArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            Scanner lector = new Scanner(archivo);

            while (lector.hasNextLine()) {
                String nombre = lector.nextLine().trim();
                if (nombre.isEmpty()) continue;

                String laboratorio = lector.nextLine().trim();
                boolean esGenerico = Boolean.parseBoolean(lector.nextLine().trim());
                int codigoBarras = Integer.parseInt(lector.nextLine().trim());
                double costo = Double.parseDouble(lector.nextLine().trim());
                double precio = Double.parseDouble(lector.nextLine().trim());
                LocalDate fechaFab = LocalDate.parse(lector.nextLine().trim());
                LocalDate fechaCad = LocalDate.parse(lector.nextLine().trim());
                String lineaExtra = lector.nextLine().trim();

                if (lineaExtra.contains("TABLETAS")) {
                    int tabletas = Integer.parseInt(lineaExtra.split(" ")[0]);
                    double miligramos = Double.parseDouble(lector.nextLine().trim().split(" ")[0]);
                    pastillas[numPastillas] = new Pill(nombre, laboratorio, esGenerico, codigoBarras, costo, precio, fechaFab, fechaCad, tabletas, miligramos);
                    numPastillas++;
                } else if (lineaExtra.contains("ML")) {
                    int mililitros = Integer.parseInt(lineaExtra.split(" ")[0]);
                    boolean vaso = Boolean.parseBoolean(lector.nextLine().trim());
                    jarabes[numJarabes] = new Syrup(nombre, laboratorio, esGenerico, codigoBarras, costo, precio, fechaFab, fechaCad, mililitros, vaso);
                    numJarabes++;
                } else if (lineaExtra.contains("GRAMOS")) {
                    int gramos = Integer.parseInt(lineaExtra.split(" ")[0]);
                    pomadas[numPomadas] = new Ointment(nombre, laboratorio, esGenerico, codigoBarras, costo, precio, fechaFab, fechaCad, gramos);
                    numPomadas++;
                }
            }

            lector.close();
            System.out.println("Archivo cargado correctamente.");

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + nombreArchivo);
        }
    }

    static void menuIngresar() {
        System.out.println("\na) Pastilla   b) Jarabe   c) Pomada");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "a": ingresarPastilla(); break;
            case "b": ingresarJarabe(); break;
            case "c": ingresarPomada(); break;
            default: System.out.println("Opcion no valida.");
        }
    }

    static void ingresarPastilla() {
        if (numPastillas >= pastillas.length) {
            System.out.println("Inventario de pastillas lleno.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Laboratorio: ");
        String laboratorio = sc.nextLine();
        System.out.print("Es generico? (true/false): ");
        boolean esGenerico = Boolean.parseBoolean(sc.nextLine());
        System.out.print("Codigo de barras: ");
        int codigoBarras = Integer.parseInt(sc.nextLine());
        System.out.print("Costo: ");
        double costo = Double.parseDouble(sc.nextLine());
        System.out.print("Precio publico: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Fecha fabricacion (yyyy-MM-dd): ");
        LocalDate fechaFab = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha caducidad (yyyy-MM-dd): ");
        LocalDate fechaCad = LocalDate.parse(sc.nextLine());
        System.out.print("Numero de tabletas: ");
        int tabletas = Integer.parseInt(sc.nextLine());
        System.out.print("Miligramos: ");
        double miligramos = Double.parseDouble(sc.nextLine());

        pastillas[numPastillas] = new Pill(nombre, laboratorio, esGenerico, codigoBarras, costo, precio, fechaFab, fechaCad, tabletas, miligramos);
        numPastillas++;
        System.out.println("Pastilla agregada correctamente.");
    }

    static void ingresarJarabe() {
        if (numJarabes >= jarabes.length) {
            System.out.println("Inventario de jarabes lleno.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Laboratorio: ");
        String laboratorio = sc.nextLine();
        System.out.print("Es generico? (true/false): ");
        boolean esGenerico = Boolean.parseBoolean(sc.nextLine());
        System.out.print("Codigo de barras: ");
        int codigoBarras = Integer.parseInt(sc.nextLine());
        System.out.print("Costo: ");
        double costo = Double.parseDouble(sc.nextLine());
        System.out.print("Precio publico: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Fecha fabricacion (yyyy-MM-dd): ");
        LocalDate fechaFab = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha caducidad (yyyy-MM-dd): ");
        LocalDate fechaCad = LocalDate.parse(sc.nextLine());
        System.out.print("Mililitros: ");
        int mililitros = Integer.parseInt(sc.nextLine());
        System.out.print("Incluye vaso dosificador? (true/false): ");
        boolean vaso = Boolean.parseBoolean(sc.nextLine());

        jarabes[numJarabes] = new Syrup(nombre, laboratorio, esGenerico, codigoBarras, costo, precio, fechaFab, fechaCad, mililitros, vaso);
        numJarabes++;
        System.out.println("Jarabe agregado correctamente.");
    }

    static void ingresarPomada() {
        if (numPomadas >= pomadas.length) {
            System.out.println("Inventario de pomadas lleno.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Laboratorio: ");
        String laboratorio = sc.nextLine();
        System.out.print("Es generico? (true/false): ");
        boolean esGenerico = Boolean.parseBoolean(sc.nextLine());
        System.out.print("Codigo de barras: ");
        int codigoBarras = Integer.parseInt(sc.nextLine());
        System.out.print("Costo: ");
        double costo = Double.parseDouble(sc.nextLine());
        System.out.print("Precio publico: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Fecha fabricacion (yyyy-MM-dd): ");
        LocalDate fechaFab = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha caducidad (yyyy-MM-dd): ");
        LocalDate fechaCad = LocalDate.parse(sc.nextLine());
        System.out.print("Gramos: ");
        int gramos = Integer.parseInt(sc.nextLine());

        pomadas[numPomadas] = new Ointment(nombre, laboratorio, esGenerico, codigoBarras, costo, precio, fechaFab, fechaCad, gramos);
        numPomadas++;
        System.out.println("Pomada agregada correctamente.");
    }

    static void menuBorrar() {
        System.out.println("\na) Pastilla   b) Jarabe   c) Pomada");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "a": borrarPastilla(); break;
            case "b": borrarJarabe(); break;
            case "c": borrarPomada(); break;
            default: System.out.println("Opcion no valida.");
        }
    }

    static void borrarPastilla() {
        System.out.print("Nombre de la pastilla a borrar: ");
        String nombre = sc.nextLine();
        int posicion = -1;

        for (int i = 0; i < numPastillas; i++) {
            if (pastillas[i].getMedicineName().equalsIgnoreCase(nombre)) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            System.out.println("No se encontro: " + nombre);
            return;
        }

        System.out.println("Encontrado: " + pastillas[posicion].getMedicineName());
        System.out.print("Confirma eliminar? (s/n): ");
        String conf = sc.nextLine();

        if (conf.equalsIgnoreCase("s")) {
            for (int i = posicion; i < numPastillas - 1; i++) {
                pastillas[i] = pastillas[i + 1];
            }
            pastillas[numPastillas - 1] = null;
            numPastillas--;
            System.out.println("Pastilla eliminada.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    static void borrarJarabe() {
        System.out.print("Nombre del jarabe a borrar: ");
        String nombre = sc.nextLine();
        int posicion = -1;

        for (int i = 0; i < numJarabes; i++) {
            if (jarabes[i].getMedicineName().equalsIgnoreCase(nombre)) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            System.out.println("No se encontro: " + nombre);
            return;
        }

        System.out.println("Encontrado: " + jarabes[posicion].getMedicineName());
        System.out.print("Confirma eliminar? (s/n): ");
        String conf = sc.nextLine();

        if (conf.equalsIgnoreCase("s")) {
            for (int i = posicion; i < numJarabes - 1; i++) {
                jarabes[i] = jarabes[i + 1];
            }
            jarabes[numJarabes - 1] = null;
            numJarabes--;
            System.out.println("Jarabe eliminado.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    static void borrarPomada() {
        System.out.print("Nombre de la pomada a borrar: ");
        String nombre = sc.nextLine();
        int posicion = -1;

        for (int i = 0; i < numPomadas; i++) {
            if (pomadas[i].getMedicineName().equalsIgnoreCase(nombre)) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            System.out.println("No se encontro: " + nombre);
            return;
        }

        System.out.println("Encontrado: " + pomadas[posicion].getMedicineName());
        System.out.print("Confirma eliminar? (s/n): ");
        String conf = sc.nextLine();

        if (conf.equalsIgnoreCase("s")) {
            for (int i = posicion; i < numPomadas - 1; i++) {
                pomadas[i] = pomadas[i + 1];
            }
            pomadas[numPomadas - 1] = null;
            numPomadas--;
            System.out.println("Pomada eliminada.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    static void menuModificar() {
        System.out.println("\na) Precio de una pastilla");
        System.out.println("b) Precio de TODAS las pastillas (porcentaje del costo)");
        System.out.println("c) Precio de un jarabe");
        System.out.println("d) Precio de TODOS los jarabes (porcentaje del costo)");
        System.out.println("e) Precio de una pomada");
        System.out.println("f) Precio de TODAS las pomadas (porcentaje del costo)");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "a": modificarUnaPastilla(); break;
            case "b": modificarTodasPastillas(); break;
            case "c": modificarUnJarabe(); break;
            case "d": modificarTodosJarabes(); break;
            case "e": modificarUnaPomada(); break;
            case "f": modificarTodasPomadas(); break;
            default: System.out.println("Opcion no valida.");
        }
    }

    static void modificarUnaPastilla() {
        System.out.print("Nombre de la pastilla: ");
        String nombre = sc.nextLine();

        for (int i = 0; i < numPastillas; i++) {
            if (pastillas[i].getMedicineName().equalsIgnoreCase(nombre)) {
                System.out.println("Precio actual: " + pastillas[i].getMedicinePricePublic());
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(sc.nextLine());
                System.out.print("Confirma el cambio? (s/n): ");
                String conf = sc.nextLine();
                if (conf.equalsIgnoreCase("s")) {
                    pastillas[i].setMedicinePricePublic(nuevoPrecio);
                    System.out.println("Precio actualizado.");
                } else {
                    System.out.println("Cancelado.");
                }
                return;
            }
        }
        System.out.println("No se encontro: " + nombre);
    }

    static void modificarTodasPastillas() {
        System.out.print("Porcentaje sobre el costo (ej. 30 para 30%): ");
        double porcentaje = Double.parseDouble(sc.nextLine());
        System.out.print("Confirma modificar TODAS las pastillas? (s/n): ");
        String conf = sc.nextLine();
        if (conf.equalsIgnoreCase("s")) {
            for (int i = 0; i < numPastillas; i++) {
                pastillas[i].setMedicinePricePublic(pastillas[i].getMedicinePrice() * (1 + porcentaje / 100));
            }
            System.out.println("Precios actualizados.");
        } else {
            System.out.println("Cancelado.");
        }
    }

    static void modificarUnJarabe() {
        System.out.print("Nombre del jarabe: ");
        String nombre = sc.nextLine();

        for (int i = 0; i < numJarabes; i++) {
            if (jarabes[i].getMedicineName().equalsIgnoreCase(nombre)) {
                System.out.println("Precio actual: " + jarabes[i].getMedicinePricePublic());
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(sc.nextLine());
                System.out.print("Confirma el cambio? (s/n): ");
                String conf = sc.nextLine();
                if (conf.equalsIgnoreCase("s")) {
                    jarabes[i].setMedicinePricePublic(nuevoPrecio);
                    System.out.println("Precio actualizado.");
                } else {
                    System.out.println("Cancelado.");
                }
                return;
            }
        }
        System.out.println("No se encontro: " + nombre);
    }

    static void modificarTodosJarabes() {
        System.out.print("Porcentaje sobre el costo (ej. 30 para 30%): ");
        double porcentaje = Double.parseDouble(sc.nextLine());
        System.out.print("Confirma modificar TODOS los jarabes? (s/n): ");
        String conf = sc.nextLine();
        if (conf.equalsIgnoreCase("s")) {
            for (int i = 0; i < numJarabes; i++) {
                jarabes[i].setMedicinePricePublic(jarabes[i].getMedicinePrice() * (1 + porcentaje / 100));
            }
            System.out.println("Precios actualizados.");
        } else {
            System.out.println("Cancelado.");
        }
    }

    static void modificarUnaPomada() {
        System.out.print("Nombre de la pomada: ");
        String nombre = sc.nextLine();

        for (int i = 0; i < numPomadas; i++) {
            if (pomadas[i].getMedicineName().equalsIgnoreCase(nombre)) {
                System.out.println("Precio actual: " + pomadas[i].getMedicinePricePublic());
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(sc.nextLine());
                System.out.print("Confirma el cambio? (s/n): ");
                String conf = sc.nextLine();
                if (conf.equalsIgnoreCase("s")) {
                    pomadas[i].setMedicinePricePublic(nuevoPrecio);
                    System.out.println("Precio actualizado.");
                } else {
                    System.out.println("Cancelado.");
                }
                return;
            }
        }
        System.out.println("No se encontro: " + nombre);
    }

    static void modificarTodasPomadas() {
        System.out.print("Porcentaje sobre el costo (ej. 30 para 30%): ");
        double porcentaje = Double.parseDouble(sc.nextLine());
        System.out.print("Confirma modificar TODAS las pomadas? (s/n): ");
        String conf = sc.nextLine();
        if (conf.equalsIgnoreCase("s")) {
            for (int i = 0; i < numPomadas; i++) {
                pomadas[i].setMedicinePricePublic(pomadas[i].getMedicinePrice() * (1 + porcentaje / 100));
            }
            System.out.println("Precios actualizados.");
        } else {
            System.out.println("Cancelado.");
        }
    }

    static void menuPatente() {
        System.out.println("\na) Pastillas de patente");
        System.out.println("b) Jarabes de patente");
        System.out.println("c) Pomadas de patente");
        System.out.println("d) Todos los medicamentos de patente");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "a": imprimirPastillas(false); break;
            case "b": imprimirJarabes(false); break;
            case "c": imprimirPomadas(false); break;
            case "d": imprimirPastillas(false); imprimirJarabes(false); imprimirPomadas(false); break;
            default: System.out.println("Opcion no valida.");
        }
    }

    static void menuGenerico() {
        System.out.println("\na) Pastillas genericas");
        System.out.println("b) Jarabes genericos");
        System.out.println("c) Pomadas genericas");
        System.out.println("d) Todos los medicamentos genericos");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "a": imprimirPastillas(true); break;
            case "b": imprimirJarabes(true); break;
            case "c": imprimirPomadas(true); break;
            case "d": imprimirPastillas(true); imprimirJarabes(true); imprimirPomadas(true); break;
            default: System.out.println("Opcion no valida.");
        }
    }

    static void imprimirPastillas(boolean generico) {
        System.out.println("\n------ PASTILLAS ------");
        int contador = 0;
        for (int i = 0; i < numPastillas; i++) {
            if (pastillas[i].isGeneric() == generico) {
                System.out.println(pastillas[i].toString());
                System.out.println("---");
                contador++;
            }
        }
        if (contador == 0) System.out.println("No hay registros.");
        System.out.println("Total: " + contador);
    }

    static void imprimirJarabes(boolean generico) {
        System.out.println("\n------ JARABES ------");
        int contador = 0;
        for (int i = 0; i < numJarabes; i++) {
            if (jarabes[i].isGeneric() == generico) {
                System.out.println(jarabes[i].toString());
                System.out.println("---");
                contador++;
            }
        }
        if (contador == 0) System.out.println("No hay registros.");
        System.out.println("Total: " + contador);
    }

    static void imprimirPomadas(boolean generico) {
        System.out.println("\n------ POMADAS ------");
        int contador = 0;
        for (int i = 0; i < numPomadas; i++) {
            if (pomadas[i].isGeneric() == generico) {
                System.out.println(pomadas[i].toString());
                System.out.println("---");
                contador++;
            }
        }
        if (contador == 0) System.out.println("No hay registros.");
        System.out.println("Total: " + contador);
    }

    static void menuResumen() {
        System.out.println("\na) Resumen de medicamentos de patente");
        System.out.println("b) Resumen de medicamentos genericos");
        System.out.println("c) Resumen total");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "a": imprimirResumen(false); break;
            case "b": imprimirResumen(true); break;
            case "c": imprimirResumenTotal(); break;
            default: System.out.println("Opcion no valida.");
        }
    }

    static void imprimirResumen(boolean generico) {
        int numP = 0, numJ = 0, numPom = 0;
        double costoP = 0, costoJ = 0, costoPom = 0;
        double precioP = 0, precioJ = 0, precioPom = 0;

        for (int i = 0; i < numPastillas; i++) {
            if (pastillas[i].isGeneric() == generico) {
                numP++;
                costoP += pastillas[i].getMedicinePrice();
                precioP += pastillas[i].getMedicinePricePublic();
            }
        }
        for (int i = 0; i < numJarabes; i++) {
            if (jarabes[i].isGeneric() == generico) {
                numJ++;
                costoJ += jarabes[i].getMedicinePrice();
                precioJ += jarabes[i].getMedicinePricePublic();
            }
        }
        for (int i = 0; i < numPomadas; i++) {
            if (pomadas[i].isGeneric() == generico) {
                numPom++;
                costoPom += pomadas[i].getMedicinePrice();
                precioPom += pomadas[i].getMedicinePricePublic();
            }
        }

        System.out.println("\n========== RESUMEN ==========");
        System.out.println("Pastillas : " + numP + " | Costo: " + costoP + " | Precio: " + precioP);
        System.out.println("Jarabes   : " + numJ + " | Costo: " + costoJ + " | Precio: " + precioJ);
        System.out.println("Pomadas   : " + numPom + " | Costo: " + costoPom + " | Precio: " + precioPom);
        System.out.println("-----------------------------");
        System.out.println("Total medicamentos : " + (numP + numJ + numPom));
        System.out.println("Total costo        : " + (costoP + costoJ + costoPom));
        System.out.println("Total precio       : " + (precioP + precioJ + precioPom));
    }

    static void imprimirResumenTotal() {
        int numP = 0, numJ = 0, numPom = 0;
        double costoP = 0, costoJ = 0, costoPom = 0;
        double precioP = 0, precioJ = 0, precioPom = 0;

        for (int i = 0; i < numPastillas; i++) {
            numP++;
            costoP += pastillas[i].getMedicinePrice();
            precioP += pastillas[i].getMedicinePricePublic();
        }
        for (int i = 0; i < numJarabes; i++) {
            numJ++;
            costoJ += jarabes[i].getMedicinePrice();
            precioJ += jarabes[i].getMedicinePricePublic();
        }
        for (int i = 0; i < numPomadas; i++) {
            numPom++;
            costoPom += pomadas[i].getMedicinePrice();
            precioPom += pomadas[i].getMedicinePricePublic();
        }

        System.out.println("\n========== RESUMEN TOTAL ==========");
        System.out.println("Pastillas : " + numP + " | Costo: " + costoP + " | Precio: " + precioP);
        System.out.println("Jarabes   : " + numJ + " | Costo: " + costoJ + " | Precio: " + precioJ);
        System.out.println("Pomadas   : " + numPom + " | Costo: " + costoPom + " | Precio: " + precioPom);
        System.out.println("----------------------------------");
        System.out.println("Total medicamentos : " + (numP + numJ + numPom));
        System.out.println("Total costo        : " + (costoP + costoJ + costoPom));
        System.out.println("Total precio       : " + (precioP + precioJ + precioPom));
    }

    static void menuBuscar() {
        System.out.println("\na) Por nombre");
        System.out.println("b) Por codigo de barras");
        System.out.println("c) Por anio de fabricacion");
        System.out.println("d) Medicamentos caducados");
        System.out.println("e) Entre dos fechas");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "a": buscarPorNombre(); break;
            case "b": buscarPorCodigo(); break;
            case "c": buscarPorAnio(); break;
            case "d": buscarCaducados(); break;
            case "e": buscarEntreFechas(); break;
            default: System.out.println("Opcion no valida.");
        }
    }

    static void buscarPorNombre() {
        System.out.print("Nombre a buscar: ");
        String nombre = sc.nextLine();
        int encontrados = 0;

        for (int i = 0; i < numPastillas; i++) {
            if (pastillas[i].getMedicineName().toLowerCase().contains(nombre.toLowerCase())) {
                System.out.println("\n[Pastilla]\n" + pastillas[i].toString());
                encontrados++;
            }
        }
        for (int i = 0; i < numJarabes; i++) {
            if (jarabes[i].getMedicineName().toLowerCase().contains(nombre.toLowerCase())) {
                System.out.println("\n[Jarabe]\n" + jarabes[i].toString());
                encontrados++;
            }
        }
        for (int i = 0; i < numPomadas; i++) {
            if (pomadas[i].getMedicineName().toLowerCase().contains(nombre.toLowerCase())) {
                System.out.println("\n[Pomada]\n" + pomadas[i].toString());
                encontrados++;
            }
        }

        if (encontrados == 0) System.out.println("No se encontro ningun medicamento con ese nombre.");
        else System.out.println("\nEncontrados: " + encontrados);
    }

    static void buscarPorCodigo() {
        System.out.print("Codigo de barras: ");
        int codigo = Integer.parseInt(sc.nextLine());
        int encontrados = 0;

        for (int i = 0; i < numPastillas; i++) {
            if (pastillas[i].getBarCode() == codigo) {
                System.out.println("\n[Pastilla]\n" + pastillas[i].toString());
                encontrados++;
            }
        }
        for (int i = 0; i < numJarabes; i++) {
            if (jarabes[i].getBarCode() == codigo) {
                System.out.println("\n[Jarabe]\n" + jarabes[i].toString());
                encontrados++;
            }
        }
        for (int i = 0; i < numPomadas; i++) {
            if (pomadas[i].getBarCode() == codigo) {
                System.out.println("\n[Pomada]\n" + pomadas[i].toString());
                encontrados++;
            }
        }

        if (encontrados == 0) System.out.println("No se encontro ningun medicamento con ese codigo.");
        else System.out.println("\nEncontrados: " + encontrados);
    }

    static void buscarPorAnio() {
        System.out.print("Anio de fabricacion: ");
        int anio = Integer.parseInt(sc.nextLine());
        int encontrados = 0;

        for (int i = 0; i < numPastillas; i++) {
            if (pastillas[i].getManufacturingDate().getYear() == anio) {
                System.out.println("[Pastilla] " + pastillas[i].getMedicineName() + " | " + pastillas[i].getManufacturingDate() + " | $" + pastillas[i].getMedicinePricePublic());
                encontrados++;
            }
        }
        for (int i = 0; i < numJarabes; i++) {
            if (jarabes[i].getManufacturingDate().getYear() == anio) {
                System.out.println("[Jarabe] " + jarabes[i].getMedicineName() + " | " + jarabes[i].getManufacturingDate() + " | $" + jarabes[i].getMedicinePricePublic());
                encontrados++;
            }
        }
        for (int i = 0; i < numPomadas; i++) {
            if (pomadas[i].getManufacturingDate().getYear() == anio) {
                System.out.println("[Pomada] " + pomadas[i].getMedicineName() + " | " + pomadas[i].getManufacturingDate() + " | $" + pomadas[i].getMedicinePricePublic());
                encontrados++;
            }
        }

        if (encontrados == 0) System.out.println("No se encontraron medicamentos de ese anio.");
        else System.out.println("\nEncontrados: " + encontrados);
    }

    static void buscarCaducados() {
        LocalDate hoy = LocalDate.now();
        int encontrados = 0;
        System.out.println("\n------ MEDICAMENTOS CADUCADOS ------");

        for (int i = 0; i < numPastillas; i++) {
            if (pastillas[i].getExpirationDate().isBefore(hoy)) {
                System.out.println("[Pastilla] " + pastillas[i].getMedicineName() + " | Caduca: " + pastillas[i].getExpirationDate() + " | $" + pastillas[i].getMedicinePricePublic());
                encontrados++;
            }
        }
        for (int i = 0; i < numJarabes; i++) {
            if (jarabes[i].getExpirationDate().isBefore(hoy)) {
                System.out.println("[Jarabe] " + jarabes[i].getMedicineName() + " | Caduca: " + jarabes[i].getExpirationDate() + " | $" + jarabes[i].getMedicinePricePublic());
                encontrados++;
            }
        }
        for (int i = 0; i < numPomadas; i++) {
            if (pomadas[i].getExpirationDate().isBefore(hoy)) {
                System.out.println("[Pomada] " + pomadas[i].getMedicineName() + " | Caduca: " + pomadas[i].getExpirationDate() + " | $" + pomadas[i].getMedicinePricePublic());
                encontrados++;
            }
        }

        if (encontrados == 0) System.out.println("No hay medicamentos caducados.");
        else System.out.println("\nTotal caducados: " + encontrados);
    }

    static void buscarEntreFechas() {
        System.out.print("Fecha inicio (yyyy-MM-dd): ");
        LocalDate fechaInicio = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha fin (yyyy-MM-dd): ");
        LocalDate fechaFin = LocalDate.parse(sc.nextLine());
        int encontrados = 0;

        for (int i = 0; i < numPastillas; i++) {
            LocalDate fecha = pastillas[i].getManufacturingDate();
            if (!fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin)) {
                System.out.println("[Pastilla] " + pastillas[i].getMedicineName() + " | " + fecha + " | $" + pastillas[i].getMedicinePricePublic());
                encontrados++;
            }
        }
        for (int i = 0; i < numJarabes; i++) {
            LocalDate fecha = jarabes[i].getManufacturingDate();
            if (!fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin)) {
                System.out.println("[Jarabe] " + jarabes[i].getMedicineName() + " | " + fecha + " | $" + jarabes[i].getMedicinePricePublic());
                encontrados++;
            }
        }
        for (int i = 0; i < numPomadas; i++) {
            LocalDate fecha = pomadas[i].getManufacturingDate();
            if (!fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin)) {
                System.out.println("[Pomada] " + pomadas[i].getMedicineName() + " | " + fecha + " | $" + pomadas[i].getMedicinePricePublic());
                encontrados++;
            }
        }

        if (encontrados == 0) System.out.println("No se encontraron medicamentos en ese rango.");
        else System.out.println("\nEncontrados: " + encontrados);
    }

    // -----------------------------------------------
    // 8. ORDENAR (BURBUJA)
    // -----------------------------------------------
    static void menuOrdenar() {
        System.out.println("\na) Pastillas");
        System.out.println("b) Jarabes");
        System.out.println("c) Pomadas");
        System.out.println("d) Todos por fecha de fabricacion (ascendente)");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        if (op.equals("d")) {
            ordenarTodosPorFecha();
            return;
        }

        System.out.println("1. Por nombre   2. Por precio");
        System.out.print("Criterio: ");
        int criterio = Integer.parseInt(sc.nextLine());

        switch (op) {
            case "a":
                ordenarPastillas(criterio);
                System.out.println("\n------ PASTILLAS ORDENADAS ------");
                for (int i = 0; i < numPastillas; i++) System.out.println(pastillas[i].toString() + "\n---");
                break;
            case "b":
                ordenarJarabes(criterio);
                System.out.println("\n------ JARABES ORDENADOS ------");
                for (int i = 0; i < numJarabes; i++) System.out.println(jarabes[i].toString() + "\n---");
                break;
            case "c":
                ordenarPomadas(criterio);
                System.out.println("\n------ POMADAS ORDENADAS ------");
                for (int i = 0; i < numPomadas; i++) System.out.println(pomadas[i].toString() + "\n---");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    static void ordenarPastillas(int criterio) {
        for (int i = 0; i < numPastillas - 1; i++) {
            for (int j = 0; j < numPastillas - 1 - i; j++) {
                boolean intercambiar = false;
                if (criterio == 1) {
                    intercambiar = pastillas[j].getMedicineName().compareToIgnoreCase(pastillas[j + 1].getMedicineName()) > 0;
                } else {
                    intercambiar = pastillas[j].getMedicinePricePublic() > pastillas[j + 1].getMedicinePricePublic();
                }
                if (intercambiar) {
                    Pill temp = pastillas[j];
                    pastillas[j] = pastillas[j + 1];
                    pastillas[j + 1] = temp;
                }
            }
        }
    }

    static void ordenarJarabes(int criterio) {
        for (int i = 0; i < numJarabes - 1; i++) {
            for (int j = 0; j < numJarabes - 1 - i; j++) {
                boolean intercambiar = false;
                if (criterio == 1) {
                    intercambiar = jarabes[j].getMedicineName().compareToIgnoreCase(jarabes[j + 1].getMedicineName()) > 0;
                } else {
                    intercambiar = jarabes[j].getMedicinePricePublic() > jarabes[j + 1].getMedicinePricePublic();
                }
                if (intercambiar) {
                    Syrup temp = jarabes[j];
                    jarabes[j] = jarabes[j + 1];
                    jarabes[j + 1] = temp;
                }
            }
        }
    }

    static void ordenarPomadas(int criterio) {
        for (int i = 0; i < numPomadas - 1; i++) {
            for (int j = 0; j < numPomadas - 1 - i; j++) {
                boolean intercambiar = false;
                if (criterio == 1) {
                    intercambiar = pomadas[j].getMedicineName().compareToIgnoreCase(pomadas[j + 1].getMedicineName()) > 0;
                } else {
                    intercambiar = pomadas[j].getMedicinePricePublic() > pomadas[j + 1].getMedicinePricePublic();
                }
                if (intercambiar) {
                    Ointment temp = pomadas[j];
                    pomadas[j] = pomadas[j + 1];
                    pomadas[j + 1] = temp;
                }
            }
        }
    }

    static void ordenarTodosPorFecha() {
        int total = numPastillas + numJarabes + numPomadas;
        Medicine[] todos = new Medicine[total];
        int k = 0;

        for (int i = 0; i < numPastillas; i++) { todos[k] = pastillas[i]; k++; }
        for (int i = 0; i < numJarabes; i++)   { todos[k] = jarabes[i];   k++; }
        for (int i = 0; i < numPomadas; i++)   { todos[k] = pomadas[i];   k++; }

        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - 1 - i; j++) {
                if (todos[j].getManufacturingDate().isAfter(todos[j + 1].getManufacturingDate())) {
                    Medicine temp = todos[j];
                    todos[j] = todos[j + 1];
                    todos[j + 1] = temp;
                }
            }
        }

        System.out.println("\n------ TODOS LOS MEDICAMENTOS POR FECHA ------");
        for (int i = 0; i < total; i++) {
            System.out.println(todos[i].getMedicineName() + " | " + todos[i].getManufacturingDate() + " | $" + todos[i].getMedicinePricePublic());
        }
        System.out.println("Total: " + total);
    }

    static void menuSalir() {
        System.out.print("Seguro que deseas salir? (s/n): ");
        String conf = sc.nextLine();
        if (conf.equalsIgnoreCase("s")) {
            System.out.println("Hasta luego!");
            System.exit(0);
        } else {
            System.out.println("Regresando al menu...");
        }
    }
}