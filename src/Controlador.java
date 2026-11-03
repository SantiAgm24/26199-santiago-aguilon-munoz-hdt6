public class Controlador {
//Clase controladora que sirve de intermediario entre la interfaz de usuario y el gestor de integraciones.

    private final GestorIntegraciones gestor;

    // Crea el gestor y carga las integraciones iniciales 
    public Controlador() {
        gestor = new GestorIntegraciones();
        gestor.cargarIntegracionesIniciales();
    }

    // Solicita el listado de integraciones activas
    public String listarActivas() {
        return gestor.listarIntegracionesActivas();
    }

    // Busca una integracion y devuelve su configuracion
    public String consultarConfiguracion(String identificador) {
        IntegracionAPI integracion =
                gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            return "No se encontro una integracion con ese identificador.";
        }

        return integracion.obtenerConfiguracion();
    }

    // Ordena el catalogo de menor a mayor latencia y lo devuelve
    public String ordenarCatalogo() {
        gestor.ordenarPorLatencia();
        return gestor.obtenerCatalogo();
    }

    // Localiza el servicio y solicita un cobro sin referencia
    public String cobrar(String identificador, double monto) {
        IntegracionAPI integracion =
                gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            return "No se encontro una integracion con ese identificdaor";
        }

        return gestor.procesarCobro(integracion, monto);
    }

    // Sobrecarga para que se pueda realizar el cobro con una referencia
    public String cobrar(String identificador, double monto,
            String referencia) {

        IntegracionAPI integracion =
                gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            return "No se encontro una integracion con ese identificador.";
        }

        return gestor.procesarCobro(integracion, monto, referencia);
    }

    // Localiza el servicio y solicita un reintegro
    public String reintegrar(String identificador, double monto) {
        IntegracionAPI integracion =
                gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            return "No se encontro una integracion con ese identificador.";
        }

        return gestor.procesarReintegro(integracion, monto);
    }

    // Localiza el servicio y solicita la generacion de un documento
    public String generarDocumento(String identificador,
            String referencia) {

        IntegracionAPI integracion =
                gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            return "No se encontro una integracion con ese identificdaor.";
        }

        return gestor.generarDocumento(integracion, referencia);
    }

    // Localiza el servicio y solicita su reporte de auditoria
    public String auditar(String identificador) {
        IntegracionAPI integracion =
                gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            return "No se encontro una integracion con ese identificador.";
        }

        return gestor.auditarServicio(integracion);
    }
}