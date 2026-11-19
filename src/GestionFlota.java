import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase administradora: contiene main(), el menú con Scanner y switch,
 * y gestiona los vehículos en un ArrayList.
 */
public class GestionFlota {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Vehiculo> flota = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            System.out.println();

            switch (opcion) {
                case 1:
                    crearPorDefecto();
                    break;
                case 2:
                    crearConParametros();
                    break;
                case 3:
                    modificarAtributos();
                    break;
                case 4:
                    visualizarObjetos();
                    break;
                case 5:
                    ejecutarRecorrer();
                    break;
                case 6:
                    ejecutarTanquear();
                    break;
                case 7:
                    System.out.println("Saliendo del programa. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción inválida. Elija un número entre 1 y 7.");
            }
        } while (opcion != 7);

        scanner.close();
    }

    // ---------- Menú ----------

    private static void mostrarMenu() {
        System.out.println("\n===== CONTROL DE FLOTA =====");
        System.out.println("1. Crear objeto por defecto");
        System.out.println("2. Crear objeto con parámetros");
        System.out.println("3. Modificar atributos del objeto");
        System.out.println("4. Visualizar objetos registrados");
        System.out.println("5. Ejecutar método de comportamiento 1 (recorrer)");
        System.out.println("6. Ejecutar método de comportamiento 2 (tanquear)");
        System.out.println("7. Salir");
    }

    // ---------- Opciones del menú ----------

    private static void crearPorDefecto() {
        flota.add(new Vehiculo());
        System.out.println("Vehículo por defecto creado en la posición " + flota.size() + ".");
    }

    private static void crearConParametros() {
        try {
            String placa = leerTexto("Placa (6 caracteres, ej: ABC123): ");
            double combustible = leerDouble("Combustible (litros, 0-" + Vehiculo.CAPACIDAD_MAXIMA + "): ");
            double rendimiento = leerDouble("Rendimiento (km por litro): ");
            double kilometraje = leerDouble("Kilometraje inicial (km): ");

            flota.add(new Vehiculo(placa, combustible, rendimiento, kilometraje));
            System.out.println("Vehículo creado en la posición " + flota.size() + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("No se creó el vehículo. " + e.getMessage());
        }
    }

    private static void modificarAtributos() {
        Vehiculo v = seleccionarVehiculo();
        if (v == null) {
            return;
        }

        System.out.println("Vehículo seleccionado: " + v);
        System.out.println("¿Qué atributo desea modificar?");
        System.out.println("1. Placa");
        System.out.println("2. Combustible");
        System.out.println("3. Rendimiento");
        System.out.println("4. Kilometraje");
        int atributo = leerEntero("Opción: ");

        try {
            switch (atributo) {
                case 1:
                    v.setPlaca(leerTexto("Nueva placa: "));
                    break;
                case 2:
                    v.setCombustible(leerDouble("Nuevo combustible (litros): "));
                    break;
                case 3:
                    v.setRendimiento(leerDouble("Nuevo rendimiento (km/L): "));
                    break;
                case 4:
                    v.setKilometraje(leerDouble("Nuevo kilometraje (km): "));
                    break;
                default:
                    System.out.println("Opción inválida.");
                    return;
            }
            System.out.println("Atributo modificado correctamente.");
            System.out.println(v);
        } catch (IllegalArgumentException e) {
            System.out.println("No se modificó el atributo. " + e.getMessage());
        }
    }

    private static void visualizarObjetos() {
        if (flota.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
            return;
        }
        System.out.println("--- Vehículos registrados ---");
        for (int i = 0; i < flota.size(); i++) {
            System.out.println((i + 1) + ". " + flota.get(i));
        }
    }

    private static void ejecutarRecorrer() {
        Vehiculo v = seleccionarVehiculo();
        if (v == null) {
            return;
        }
        double km = leerDouble("Kilómetros a recorrer: ");
        v.recorrer(km);
    }

    private static void ejecutarTanquear() {
        Vehiculo v = seleccionarVehiculo();
        if (v == null) {
            return;
        }
        double litros = leerDouble("Litros a tanquear: ");
        v.tanquear(litros);
    }

    // ---------- Utilidades ----------

    /** Muestra la lista, pide la posición y verifica que el objeto exista. */
    private static Vehiculo seleccionarVehiculo() {
        if (flota.isEmpty()) {
            System.out.println("No hay vehículos registrados. Cree uno primero (opción 1 o 2).");
            return null;
        }
        visualizarObjetos();
        int posicion = leerEntero("Ingrese la posición del vehículo: ");

        if (posicion < 1 || posicion > flota.size()) {
            System.out.println("La posición " + posicion + " no existe.");
            return null;
        }
        return flota.get(posicion - 1);
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    /** Lee un entero; repite hasta que el usuario escriba un valor válido. */
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
            }
        }
    }

    /** Lee un decimal (acepta punto o coma); repite hasta que sea válido. */
    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número (ej: 12.5).");
            }
        }
    }
}
