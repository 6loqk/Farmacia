import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Medicine> inventario = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // ────────────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
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
                case 1:
                    menuIngresar();
                    break;
                case 2:
                    menuBorrar();
                    break;
                case 3:
                    menuModificar();
                    break;
                case 4:
                    menuPatente();
                    break;
                case 5:
                    menuGenerico();
                    break;
                case 6:
                    menuResumen();
                    break;
                case 7:
                    menuBuscar();
                    break;
                case 8:
                    menuOrdenar();
                    break;
                case 9:
                    menuSalir();
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 9);
    }

    // ── INGRESAR ─────────────────────────────────────────────────────────────
    static void menuIngresar() {
        System.out.println("\na) Pastilla   b) Jarabe   c) Pomada");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();
        switch (op) {
            case "a":
                ingresarPastilla();
                break;
            case "b":
                ingresarJarabe();
                break;
            case "c":
                ingresarPomada();
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    static void ingresarPastilla() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Laboratorio: ");
        String lab = sc.nextLine();

        System.out.print("Es generico? (s/n): ");
        boolean gen = sc.nextLine().equalsIgnoreCase("s");

        System.out.print("Codigo de barras: ");
        int codigo = Integer.parseInt(sc.nextLine());

        System.out.print("Costo: ");
        double costo = Double.parseDouble(sc.nextLine());

        System.out.print("Precio publico: ");
        double precio = Double.parseDouble(sc.nextLine());

        System.out.print("Fecha fabricacion (yyyy-MM-dd): ");
        LocalDate fab = LocalDate.parse(sc.nextLine());

        System.out.print("Fecha caducidad  (yyyy-MM-dd): ");
        LocalDate cad = LocalDate.parse(sc.nextLine());

        System.out.print("Numero de tabletas: ");
        int tabletas = Integer.parseInt(sc.nextLine());

        System.out.print("Miligramos: ");
        double mg = Double.parseDouble(sc.nextLine());

        inventario.add(new Pill(nombre, lab, gen, codigo, costo, precio, fab, cad, tabletas, mg));
        System.out.println("Pastilla agregada correctamente.");
    }

    static void ingresarJarabe() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Laboratorio: ");
        String lab = sc.nextLine();
        System.out.print("Es generico? (s/n): ");
        boolean gen = sc.nextLine().equalsIgnoreCase("s");
        System.out.print("Codigo de barras: ");
        int codigo = Integer.parseInt(sc.nextLine());
        System.out.print("Costo: ");
        double costo = Double.parseDouble(sc.nextLine());
        System.out.print("Precio publico: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Fecha fabricacion (yyyy-MM-dd): ");
        LocalDate fab = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha caducidad  (yyyy-MM-dd): ");
        LocalDate cad = LocalDate.parse(sc.nextLine());
        System.out.print("Mililitros: ");
        int ml = Integer.parseInt(sc.nextLine());
        System.out.print("Incluye vaso dosificador? (s/n): ");
        boolean vaso = sc.nextLine().equalsIgnoreCase("s");

        inventario.add(new Syrup(nombre, lab, gen, codigo, costo, precio, fab, cad, ml, vaso));
        System.out.println("Jarabe agregado correctamente.");
    }

    static void ingresarPomada() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Laboratorio: ");
        String lab = sc.nextLine();
        System.out.print("Es generico? (s/n): ");
        boolean gen = sc.nextLine().equalsIgnoreCase("s");
        System.out.print("Codigo de barras: ");
        int codigo = Integer.parseInt(sc.nextLine());
        System.out.print("Costo: ");
        double costo = Double.parseDouble(sc.nextLine());
        System.out.print("Precio publico: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Fecha fabricacion (yyyy-MM-dd): ");
        LocalDate fab = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha caducidad  (yyyy-MM-dd): ");
        LocalDate cad = LocalDate.parse(sc.nextLine());
        System.out.print("Gramos: ");
        int gramos = Integer.parseInt(sc.nextLine());

        inventario.add(new Ointment(nombre, lab, gen, codigo, costo, precio, fab, cad, gramos));
        System.out.println("Pomada agregada correctamente.");
    }

    // ── BORRAR ────────────────────────────────────────────────────────────────
    static void menuBorrar() {
        System.out.print("Nombre del medicamento a borrar: ");
        String nombre = sc.nextLine();

        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).getMedicineName().equalsIgnoreCase(nombre)) {
                // Mostrar con JOptionPane antes de confirmar
                JOptionPane.showMessageDialog(null, inventario.get(i).toString(),
                        "Medicamento encontrado", JOptionPane.INFORMATION_MESSAGE);
                System.out.print("Confirma eliminar? (s/n): ");
                if (sc.nextLine().equalsIgnoreCase("s")) {
                    inventario.remove(i);
                    System.out.println("Medicamento eliminado.");
                } else {
                    System.out.println("Operacion cancelada.");
                }
                return;
            }
        }
        System.out.println("No se encontro: " + nombre);
    }

    static void menuModificar() {
        System.out.println("\na) Precio de un medicamento");
        System.out.println("b) Precio de TODOS los medicamentos (% sobre costo)");
        System.out.println("c) Precio de todos los de un tipo (% sobre costo)");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "a":
                modificarUno();
                break;
            case "b":
                modificarTodos();
                break;
            case "c":
                modificarTipo();
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    static void modificarUno() {
        System.out.print("Nombre del medicamento: ");
        String nombre = sc.nextLine();

        for (Medicine m : inventario) {
            if (m.getMedicineName().equalsIgnoreCase(nombre)) {
                JOptionPane.showMessageDialog(null, m.toString(),
                        "Medicamento encontrado", JOptionPane.INFORMATION_MESSAGE);
                System.out.print("Nuevo precio publico: ");
                double nuevoPrecio = Double.parseDouble(sc.nextLine());
                System.out.print("Confirma? (s/n): ");
                if (sc.nextLine().equalsIgnoreCase("s")) {
                    m.setMedicinePricePublic(nuevoPrecio);
                    System.out.println("Precio actualizado.");
                } else {
                    System.out.println("Cancelado.");
                }
                return;
            }
        }
        System.out.println("No se encontro: " + nombre);
    }

    static void modificarTodos() {
        System.out.print("Porcentaje sobre el costo (ej. 30 para 30%): ");
        double pct = Double.parseDouble(sc.nextLine());
        System.out.print("Confirma modificar TODOS? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            for (Medicine m : inventario)
                m.setMedicinePricePublic(m.getMedicinePrice() * (1 + pct / 100));
            System.out.println("Precios actualizados.");
        } else {
            System.out.println("Cancelado.");
        }
    }

    static void modificarTipo() {
        System.out.println("a) Pastillas   b) Jarabes   c) Pomadas");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        Class<?> clase = null;
        if (op.equals("a")) clase = Pill.class;
        else if (op.equals("b")) clase = Syrup.class;
        else if (op.equals("c")) clase = Ointment.class;
        else {
            System.out.println("Opcion no valida.");
            return;
        }

        System.out.print("Porcentaje sobre el costo (ej. 30 para 30%): ");
        double pct = Double.parseDouble(sc.nextLine());
        System.out.print("Confirma? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            for (Medicine m : inventario)
                if (m.getClass() == clase)
                    m.setMedicinePricePublic(m.getMedicinePrice() * (1 + pct / 100));
            System.out.println("Precios actualizados.");
        } else {
            System.out.println("Cancelado.");
        }
    }

    static void menuPatente() {
        System.out.println("\na) Pastillas   b) Jarabes   c) Pomadas   d) Todos");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();
        imprimirPorTipoYGenericidad(op, false);
    }

    static void menuGenerico() {
        System.out.println("\na) Pastillas   b) Jarabes   c) Pomadas   d) Todos");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();
        imprimirPorTipoYGenericidad(op, true);
    }

    static void imprimirPorTipoYGenericidad(String op, boolean gen) {
        Class<?> clase = null;
        if (op.equals("a")) clase = Pill.class;
        else if (op.equals("b")) clase = Syrup.class;
        else if (op.equals("c")) clase = Ointment.class;
        else if (op.equals("d")) clase = null;           // todos los tipos
        else {
            System.out.println("Opcion no valida.");
            return;
        }

        int idx = 1;
        for (Medicine m : inventario) {
            boolean tipoOk = (clase == null) || (m.getClass() == clase);
            if (tipoOk && m.isGeneric() == gen) {
                JOptionPane.showMessageDialog(null, m.toString(),
                        "Medicamento " + idx++, JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (idx == 1) System.out.println("No hay registros.");
        else System.out.println("Mostrados: " + (idx - 1));
    }

    static void menuResumen() {
        System.out.println("\na) Medicamentos de patente");
        System.out.println("b) Medicamentos genericos");
        System.out.println("c) Resumen total");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();
        switch (op) {
            case "a":
                imprimirResumen(false);
                break;
            case "b":
                imprimirResumen(true);
                break;
            case "c":
                imprimirResumenTotal();
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    static void imprimirResumen(boolean gen) {
        int nP = 0, nJ = 0, nPom = 0;
        double cP = 0, cJ = 0, cPom = 0;
        double pP = 0, pJ = 0, pPom = 0;

        for (Medicine m : inventario) {
            if (m.isGeneric() != gen) continue;
            if (m instanceof Pill) {
                nP++;
                cP += m.getMedicinePrice();
                pP += m.getMedicinePricePublic();
            }
            if (m instanceof Syrup) {
                nJ++;
                cJ += m.getMedicinePrice();
                pJ += m.getMedicinePricePublic();
            }
            if (m instanceof Ointment) {
                nPom++;
                cPom += m.getMedicinePrice();
                pPom += m.getMedicinePricePublic();
            }
        }

        String html = "<html><body style='font-family:monospace;font-size:13px;padding:8px'>"
                + "<b>RESUMEN " + (gen ? "GENÉRICO" : "PATENTE") + "</b><br><br>"
                + "<b>Pastillas:</b> " + nP + " | Costo: $" + String.format("%.2f", cP) + " | Precio: $" + String.format("%.2f", pP) + "<br>"
                + "<b>Jarabes:</b> " + nJ + " | Costo: $" + String.format("%.2f", cJ) + " | Precio: $" + String.format("%.2f", pJ) + "<br>"
                + "<b>Pomadas:</b> " + nPom + " | Costo: $" + String.format("%.2f", cPom) + " | Precio: $" + String.format("%.2f", pPom) + "<br><br>"
                + "<b>Total medic.:</b> " + (nP + nJ + nPom) + "<br>"
                + "<b>Total costo: $</b>" + String.format("%.2f", cP + cJ + cPom) + "<br>"
                + "<b>Total precio: $</b>" + String.format("%.2f", pP + pJ + pPom)
                + "</body></html>";

        JOptionPane.showMessageDialog(null, html, "Resumen", JOptionPane.INFORMATION_MESSAGE);
    }

    static void imprimirResumenTotal() {
        int nP = 0, nJ = 0, nPom = 0;
        double cP = 0, cJ = 0, cPom = 0;
        double pP = 0, pJ = 0, pPom = 0;

        for (Medicine m : inventario) {
            if (m instanceof Pill) {
                nP++;
                cP += m.getMedicinePrice();
                pP += m.getMedicinePricePublic();
            }
            if (m instanceof Syrup) {
                nJ++;
                cJ += m.getMedicinePrice();
                pJ += m.getMedicinePricePublic();
            }
            if (m instanceof Ointment) {
                nPom++;
                cPom += m.getMedicinePrice();
                pPom += m.getMedicinePricePublic();
            }
        }

        String html = "<html><body style='font-family:monospace;font-size:13px;padding:8px'>"
                + "<b>RESUMEN TOTAL</b><br><br>"
                + "<b>Pastillas:</b> " + nP + " | Costo: $" + String.format("%.2f", cP) + " | Precio: $" + String.format("%.2f", pP) + "<br>"
                + "<b>Jarabes:</b> " + nJ + " | Costo: $" + String.format("%.2f", cJ) + " | Precio: $" + String.format("%.2f", pJ) + "<br>"
                + "<b>Pomadas:</b> " + nPom + " | Costo: $" + String.format("%.2f", cPom) + " | Precio: $" + String.format("%.2f", pPom) + "<br><br>"
                + "<b>Total medic.:</b> " + (nP + nJ + nPom) + "<br>"
                + "<b>Total costo: $</b>" + String.format("%.2f", cP + cJ + cPom) + "<br>"
                + "<b>Total precio: $</b>" + String.format("%.2f", pP + pJ + pPom)
                + "</body></html>";

        JOptionPane.showMessageDialog(null, html, "Resumen Total", JOptionPane.INFORMATION_MESSAGE);
    }

    static void menuBuscar() {
        System.out.println("\na) Por nombre");
        System.out.println("b) Por codigo de barras");
        System.out.println("c) Por anio de fabricacion");
        System.out.println("d) Medicamentos caducados");
        System.out.println("e) Entre dos fechas de fabricacion");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();
        switch (op) {
            case "a":
                buscarPorNombre();
                break;
            case "b":
                buscarPorCodigo();
                break;
            case "c":
                buscarPorAnio();
                break;
            case "d":
                buscarCaducados();
                break;
            case "e":
                buscarEntreFechas();
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    static void mostrarResultados(ArrayList<Medicine> lista, String titulo) {
        if (lista.isEmpty()) {
            System.out.println("No se encontraron medicamentos.");
            return;
        }
        System.out.println("Encontrados: " + lista.size());
        int idx = 1;
        for (Medicine m : lista)
            JOptionPane.showMessageDialog(null, m.toString(),
                    titulo + " (" + idx++ + "/" + lista.size() + ")", JOptionPane.INFORMATION_MESSAGE);
    }

    static void buscarPorNombre() {
        System.out.print("Nombre a buscar: ");
        String nombre = sc.nextLine().toLowerCase();
        ArrayList<Medicine> res = new ArrayList<>();
        for (Medicine m : inventario)
            if (m.getMedicineName().toLowerCase().contains(nombre)) res.add(m);
        mostrarResultados(res, "Búsqueda por nombre");
    }

    static void buscarPorCodigo() {
        System.out.print("Codigo de barras: ");
        int codigo = Integer.parseInt(sc.nextLine());
        ArrayList<Medicine> res = new ArrayList<>();
        for (Medicine m : inventario)
            if (m.getBarCode() == codigo) res.add(m);
        mostrarResultados(res, "Búsqueda por código");
    }

    static void buscarPorAnio() {
        System.out.print("Anio de fabricacion: ");
        int anio = Integer.parseInt(sc.nextLine());
        ArrayList<Medicine> res = new ArrayList<>();
        for (Medicine m : inventario)
            if (m.getManufacturingDate().getYear() == anio) res.add(m);
        mostrarResultados(res, "Fabricación " + anio);
    }

    static void buscarCaducados() {
        LocalDate hoy = LocalDate.now();
        ArrayList<Medicine> res = new ArrayList<>();
        for (Medicine m : inventario)
            if (m.getExpirationDate().isBefore(hoy)) res.add(m);
        mostrarResultados(res, "Caducados");
    }

    static void buscarEntreFechas() {
        System.out.print("Fecha inicio (yyyy-MM-dd): ");
        LocalDate ini = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha fin    (yyyy-MM-dd): ");
        LocalDate fin = LocalDate.parse(sc.nextLine());
        ArrayList<Medicine> res = new ArrayList<>();
        for (Medicine m : inventario) {
            LocalDate f = m.getManufacturingDate();
            if (!f.isBefore(ini) && !f.isAfter(fin)) res.add(m);
        }
        mostrarResultados(res, "Entre fechas");
    }

    static void menuOrdenar() {
        System.out.println("\na) Por nombre (A-Z)");
        System.out.println("b) Por precio (menor a mayor)");
        System.out.println("c) Por fecha de fabricacion (ascendente)");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        ArrayList<Medicine> copia = new ArrayList<>(inventario);

        switch (op) {
            case "a":
                ordenarBurbuja(copia, "nombre");
                break;
            case "b":
                ordenarBurbuja(copia, "precio");
                break;
            case "c":
                ordenarBurbuja(copia, "fecha");
                break;
            default:
                System.out.println("Opcion no valida.");
                return;
        }

        System.out.println("Ordenados: " + copia.size());
        int idx = 1;
        for (Medicine m : copia)
            JOptionPane.showMessageDialog(null, m.toString(),
                    "Ordenado (" + idx++ + "/" + copia.size() + ")", JOptionPane.INFORMATION_MESSAGE);
    }

    static void ordenarBurbuja(ArrayList<Medicine> lista, String criterio) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                boolean intercambiar = false;
                Medicine a = lista.get(j);
                Medicine b = lista.get(j + 1);
                if (criterio.equals("nombre"))
                    intercambiar = a.getMedicineName().compareToIgnoreCase(b.getMedicineName()) > 0;
                else if (criterio.equals("precio"))
                    intercambiar = a.getMedicinePricePublic() > b.getMedicinePricePublic();
                else if (criterio.equals("fecha"))
                    intercambiar = a.getManufacturingDate().isAfter(b.getManufacturingDate());

                if (intercambiar) {
                    lista.set(j, b);
                    lista.set(j + 1, a);
                }
            }
        }
    }

    // ── SALIR ─────────────────────────────────────────────────────────────────
    static void menuSalir() {
        System.out.print("Seguro que deseas salir? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Hasta luego!");
            System.exit(0);
        } else {
            System.out.println("Regresando al menu...");
        }
    }
}