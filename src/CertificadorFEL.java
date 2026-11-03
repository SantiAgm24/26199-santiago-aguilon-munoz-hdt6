public class CertificadorFEL extends IntegracionAPI {
// Representa una integracion de certificador FEL que permite simular la generacion de facturas. Proviene de IntegracionAPI para heredar los datos comunes de la integracion.

    private String nitCertificador;

    // Inicializa los datos heredados y el NIT del certificador.
    public CertificadorFEL(String identificador, String nombre, String urlBase, String claveApi, boolean activa,
            String estadoConexion, double latenciaPromedio, String nitCertificador) {

        super(identificador, nombre, urlBase, claveApi, activa, estadoConexion, latenciaPromedio);

        setNitCertificador(nitCertificador);
    }

    // Genera un XML que no constituye un documento fiscal valido, pero que permite simular la generacion de facturas.
    @Override
    public String generarDocumento(String referencia) {
        if (referencia == null || referencia.trim().isEmpty()) {
            throw new IllegalArgumentException("La referencia no puede estar vacia.");
        }

        if (!isActiva()) {
            return "Factura no generada: la integracion esta inactiva.";
        }

        if (!"Conectado".equalsIgnoreCase(getEstadoConexion())) {
            return "Factura no generada: el servicio no esta conectado.";
        }

        // Escapa los caracteres especiales para mantener el XML bien formado.
        String referenciaXml = referencia.trim()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");

        String nombreXml = getNombre()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");

        String nitXml = nitCertificador
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "\n<FacturaSimulada>"
                + "\n    <Referencia>" + referenciaXml + "</Referencia>"
                + "\n    <Certificador>" + nombreXml + "</Certificador>"
                + "\n    <NitCertificador>" + nitXml + "</NitCertificador>"
                + "\n    <Estado>SIMULACION</Estado>"
                + "\n</FacturaSimulada>";
    }

    public String getNitCertificador() {
        return nitCertificador;
    }

    public void setNitCertificador(String nitCertificador) {
        if (nitCertificador == null
                || nitCertificador.trim().isEmpty()) {
            throw new IllegalArgumentException("El NIT del certificador no puede estar vacio.");
        }

        this.nitCertificador = nitCertificador.trim();
    }

    // Agrega el NIT del certificador a los datos comunes.
    @Override
    public String toString() {
        return super.toString()
                + " | NIT del certificador: " + nitCertificador;
    }
}