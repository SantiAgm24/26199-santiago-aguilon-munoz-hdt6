public class PasarelaPago extends IntegracionAPI {
// Representa una integracion de pasarela de pago que permite simular cobros y reintegros. Proviene de IntegracionAPI para heredar los datos comunes de la integracion. 

    private String metodoPago;

    // Inicia los datos heredados y el metodo de pago
    public PasarelaPago(String identificador, String nombre, String urlBase, String claveApi, boolean activa,
            String estadoConexion, double latenciaPromedio, String metodoPago) {

        super(identificador, nombre, urlBase, claveApi, activa, estadoConexion, latenciaPromedio);

        setMetodoPago(metodoPago);
    }

    // Simula un cobro sin referencia
    @Override
    public String cobrar(double monto) {
        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser un numero finito mayor que cero.");
        }

        if (!isActiva()) {
            return "Cobro no realizado: la integracion esta inactiva.";
        }

        if (!"Conectado".equalsIgnoreCase(getEstadoConexion())) {
            return "Cobro no realizado: el servicio no esta conectado.";
        }

        return "Cobro simulado exitoso."
                + "\nProveedor: " + getNombre()
                + "\nMetodo de pago: " + metodoPago
                + "\nMonto: Q " + String.format(
                        java.util.Locale.US, "%.2f", monto);
    }

    // Sobrecarga que agrega la referencia al cobro
    @Override
    public String cobrar(double monto, String referencia) {
        if (referencia == null || referencia.trim().isEmpty()) {
            throw new IllegalArgumentException("La referencia no puede estar vacia.");
        }

        return cobrar(monto)
                + "\nReferencia: " + referencia.trim();
    }

    // Simula la devolucion de dinero
    @Override
    public String reintegrar(double monto) {
        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException(
                    "El monto debe ser un numero finito mayor que cero.");
        }

        if (!isActiva()) {
            return "Reintegro no realizado: la integracion esta inactiva.";
        }

        if (!"Conectado".equalsIgnoreCase(getEstadoConexion())) {
            return "Reintegro no realizado: el servicio no esta conectado.";
        }

        return "Reintegro simulado exitoso."
                + "\nProveedor: " + getNombre()
                + "\nMetodo de pago: " + metodoPago
                + "\nMonto: Q " + String.format(
                        java.util.Locale.US, "%.2f", monto);
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        if (metodoPago == null || metodoPago.trim().isEmpty()) {
            throw new IllegalArgumentException("El metodo de pago no puede estar vacio.");
        }

        this.metodoPago = metodoPago.trim();
    }

    // Agrega el metodo de pago a los datos comunes
    @Override
    public String toString() {
        return super.toString()
                + " | Metodo de pago: " + metodoPago;
    }
}