public class ProveedorLogistico extends IntegracionAPI {
// Implementa la interfaz Documentable para generar guias de envio. Proviene de IntegracionAPI para heredar los datos comunes de la integracion.

    private String cobertura;

    // Inicializa los datos heredados y la zona de cobertura
    public ProveedorLogistico(String identificador, String nombre, String urlBase, String claveApi, boolean activa,
            String estadoConexion, double latenciaPromedio, String cobertura) {

        super(identificador, nombre, urlBase, claveApi, activa, estadoConexion, latenciaPromedio);

        setCobertura(cobertura);
    }

    // Devuelve una respuesta simulada de rastreo
    public String rastrearEnvio(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo de rastreo no puede estar vacio.");
        }

        if (!isActiva()) {
            return "Rastreo no realizado: la integracion esta inactiva.";
        }

        if (!"Conectado".equalsIgnoreCase(getEstadoConexion())) {
            return "Rastreo no realizado: el servicio no esta conectado.";
        }

        return "Consulta de rastreo simulada."
                + "\nProveedor: " + getNombre()
                + "\nCodigo: " + codigo.trim()
                + "\nEstado simulado: en transito."
                + "\nCobertura: " + cobertura;
    }

    // Realiza una estamación de costo de envio basada en el peso del paquete
    // El cobro se define como Q 25.00 + Q 10.00 por cada kilogramo de peso.
    public double cotizarEnvio(double pesoKg) {
        if (!Double.isFinite(pesoKg) || pesoKg <= 0) {
            throw new IllegalArgumentException(
                    "El peso debe ser un numero finito mayor que cero.");
        }

        double total = 25.0 + (10.0 * pesoKg);

        if (!Double.isFinite(total)) {
            throw new IllegalArgumentException(
                    "El peso ingresado es demasiado grande para cotizar.");
        }

        return total;
    }

    // Genera una guia de envio simulada
    @Override
    public String generarDocumento(String referencia) {
        if (referencia == null || referencia.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La referencia no puede estar vacia.");
        }

        if (!isActiva()) {
            return "Guia no generada: la integracion esta inactiva.";
        }

        if (!"Conectado".equalsIgnoreCase(getEstadoConexion())) {
            return "Guia no generada: el servicio no esta conectado.";
        }

        return "GUIA DE ENVIO SIMULADA"
                + "\nProveedor: " + getNombre()
                + "\nIdentificador de integracion: " + getIdentificador()
                + "\nReferencia: " + referencia.trim()
                + "\nCobertura: " + cobertura;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        if (cobertura == null || cobertura.trim().isEmpty()) {
            throw new IllegalArgumentException("La cobertura no puede estar vacia.");
        }

        this.cobertura = cobertura.trim();
    }

    // Agrega la cobertura a los datos comunes
    @Override
    public String toString() {
        return super.toString()
                + " | Cobertura: " + cobertura;
    }
}