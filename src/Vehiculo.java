/**
 * Clase que representa un vehículo de la flota.
 * Atributos privados, dos constructores, getters/setters con validaciones
 * y dos métodos de comportamiento: recorrer() y tanquear().
 */
public class Vehiculo {

    /** Capacidad máxima del tanque en litros. */
    public static final double CAPACIDAD_MAXIMA = 50;

    // ---------- Atributos privados ----------
    private String placa;
    private double combustible;   // litros disponibles
    private double rendimiento;   // kilómetros por litro
    private double kilometraje;   // kilómetros recorridos acumulados

    // ---------- Constructores ----------

    /** Constructor por defecto: "AAA000", 20, 40, 0 */
    public Vehiculo() {
        this("AAA000", 20, 40, 0);
    }

    /** Constructor con parámetros (usa los setters para validar). */
    public Vehiculo(String placa, double combustible, double rendimiento, double kilometraje) {
        setPlaca(placa);
        setCombustible(combustible);
        setRendimiento(rendimiento);
        setKilometraje(kilometraje);
    }

    // ---------- Getters ----------

    public String getPlaca() {
        return placa;
    }

    public double getCombustible() {
        return combustible;
    }

    public double getRendimiento() {
        return rendimiento;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    // ---------- Setters con validaciones ----------

    public void setPlaca(String placa) {
        if (placa == null || !placa.trim().matches("[A-Za-z0-9]{6}")) {
            throw new IllegalArgumentException(
                    "La placa debe tener exactamente 6 caracteres alfanuméricos (ej: ABC123).");
        }
        this.placa = placa.trim().toUpperCase();
    }

    public void setCombustible(double combustible) {
        if (combustible < 0 || combustible > CAPACIDAD_MAXIMA) {
            throw new IllegalArgumentException(
                    "El combustible debe estar entre 0 y " + CAPACIDAD_MAXIMA + " litros.");
        }
        this.combustible = combustible;
    }

    public void setRendimiento(double rendimiento) {
        if (rendimiento <= 0) {
            throw new IllegalArgumentException("El rendimiento debe ser mayor que 0 (km por litro).");
        }
        this.rendimiento = rendimiento;
    }

    public void setKilometraje(double kilometraje) {
        if (kilometraje < 0) {
            throw new IllegalArgumentException("El kilometraje no puede ser negativo.");
        }
        this.kilometraje = kilometraje;
    }

    // ---------- Métodos de comportamiento ----------

    /**
     * Método 1: recorre una distancia. Calcula el combustible requerido,
     * verifica que haya suficiente y actualiza combustible y kilometraje.
     *
     * @return true si el recorrido se realizó, false si no fue posible
     */
    public boolean recorrer(double km) {
        if (km <= 0) {
            System.out.println("Error: la distancia debe ser mayor que 0.");
            return false;
        }

        double requerido = km / rendimiento;

        if (requerido > combustible) {
            double maxKm = combustible * rendimiento;
            System.out.printf("Combustible insuficiente. Requiere %.2f L y solo hay %.2f L.%n",
                    requerido, combustible);
            System.out.printf("Con el combustible actual solo puede recorrer %.2f km.%n", maxKm);
            return false;
        }

        combustible -= requerido;
        kilometraje += km;
        System.out.printf("Recorrido exitoso de %.2f km. Combustible gastado: %.2f L.%n", km, requerido);
        System.out.printf("Combustible restante: %.2f L | Kilometraje total: %.2f km%n",
                combustible, kilometraje);
        return true;
    }

    /**
     * Método 2: agrega combustible validando que los litros sean positivos
     * y que no se supere la capacidad máxima de 50 litros.
     *
     * @return true si se tanqueó, false si no fue posible
     */
    public boolean tanquear(double litros) {
        if (litros <= 0) {
            System.out.println("Error: los litros deben ser mayores que 0.");
            return false;
        }

        double espacioLibre = CAPACIDAD_MAXIMA - combustible;

        if (litros > espacioLibre) {
            System.out.printf("No se puede tanquear %.2f L. Supera la capacidad máxima de %.0f L.%n",
                    litros, CAPACIDAD_MAXIMA);
            System.out.printf("Espacio disponible en el tanque: %.2f L.%n", espacioLibre);
            return false;
        }

        combustible += litros;
        System.out.printf("Tanqueo exitoso. Combustible actual: %.2f L%n", combustible);
        return true;
    }

    @Override
    public String toString() {
        return String.format("Placa: %s | Combustible: %.2f L | Rendimiento: %.2f km/L | Kilometraje: %.2f km",
                placa, combustible, rendimiento, kilometraje);
    }
}
