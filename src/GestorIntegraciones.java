import java.util.ArrayList;
import java.util.Collections;
//Se importan las clases necesarias para manejar listas y colecciones.

public class GestorIntegraciones {

    private final ArrayList<IntegracionAPI> integraciones;

    // Inicializa la unica lista polimorfica
    public GestorIntegraciones() {
        integraciones = new ArrayList<>();
    }

    // Carga 10 integraciones para demostrar el prototipo
    // Solo debe ejecutarse una vez, antes de agregar otras integraciones
    // Las integraciones son falsas y solo sirven para la demostración del ejericio
    public void cargarIntegracionesIniciales() {
        if (!integraciones.isEmpty()) {
            return;
        }

        agregarIntegracion(new PasarelaPago("PAG-01", "PagoTarjeta", "https://tarjeta.example",
                "demo-pag-01", true, "Conectado", 120.0,"Tarjeta de credito"));

        agregarIntegracion(new PasarelaPago("PAG-02", "TransferenciaGT", "https://banco.example",
                "demo-pag-02", true, "Conectado", 180.0, "Transferencia bancaria"));

        agregarIntegracion(new PasarelaPago("PAG-03", "BilleteraDigital", "https://billetera.example",
                "demo-pag-03", true, "Conectado", 95.0, "Billetera digital"));

        agregarIntegracion(new PasarelaPago("PAG-04", "PagoAlternativo", "https://pago.example",
                "demo-pag-04", false, "Desconectado", 250.0, "Tarjeta de debito"));

        agregarIntegracion(new ProveedorLogistico("LOG-01", "EnviosNacionales", "https://envios.example",
                "demo-log-01", true, "Conectado", 210.0, "Guatemala"));

        agregarIntegracion(new ProveedorLogistico("LOG-02", "EntregaExpress", "https://express.example",
                "demo-log-02", true, "Conectado", 85.0, "Ciudad de Guatemala"));

        agregarIntegracion(new ProveedorLogistico("LOG-03", "CargaRegional", "https://carga.example",
                "demo-log-03", true, "Desconectado", 420.0, "Centroamerica"));

        agregarIntegracion(new CertificadorFEL("FEL-01", "CertificaGT", "https://certifica.example",
                "demo-fel-01", true, "Conectado", 160.0, "100001-1"));

        agregarIntegracion(new CertificadorFEL("FEL-02", "FacturaDigital", "https://factura.example",
                "demo-fel-02", true, "Conectado", 140.0, "100002-2"));

        agregarIntegracion(new CertificadorFEL("FEL-03", "CertificacionRegional", "https://certificacion.example",
                "demo-fel-03", true, "Conectado", 310.0, "100003-3"));
    }

    // Agrega una integracion sin permitir objetos nulos ni IDs duplicados
    public void agregarIntegracion(IntegracionAPI integracion) {
        if (integracion == null) {
            throw new IllegalArgumentException("La integracion no puede ser nula.");
        }

        if (buscarIntegracion(integracion.getIdentificador()) != null) {
            throw new IllegalArgumentException("Ya existe una integracion con ese identificador.");
        }

        integraciones.add(integracion);
    }

    // Construye el listado sin crear una segunda lista.
    public String listarIntegracionesActivas() {
        StringBuilder resultado = new StringBuilder();

        for (IntegracionAPI integracion : integraciones) {
            if (integracion.isActiva()) {
                resultado.append(integracion)
                        .append("\n");
            }
        }

        if (resultado.length() == 0) {
            return "No hay integraciones activas.";
        }

        return resultado.toString();
    }

    // Busca por identificador sin distinguir mayusculas y minusculas.
    public IntegracionAPI buscarIntegracion(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador no puede estar vacio.");
        }

        String identificadorBuscado = identificador.trim();

        for (IntegracionAPI integracion : integraciones) {
            if (integracion.getIdentificador()
                    .equalsIgnoreCase(identificadorBuscado)) {
                return integracion;
            }
        }

        return null;
    }

    // Utiliza el compareTo definido en IntegracionAPI.
    public void ordenarPorLatencia() {
        Collections.sort(integraciones);
    }

    // Devuelve todas las integraciones en su orden actual.
    public String obtenerCatalogo() {
        if (integraciones.isEmpty()) {
            return "El catalogo no tiene integraciones.";
        }

        StringBuilder resultado = new StringBuilder();

        for (IntegracionAPI integracion : integraciones) {
            resultado.append(integracion)
                    .append("\n");
        }

        return resultado.toString();
    }

    // Solicita el cobro mediante el contrato Transaccional
    public String procesarCobro(Transaccional servicio, double monto) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo.");
        }

        return servicio.cobrar(monto);
    }

    // Sobrecarga que permite asociar una referencia al cobro
    public String procesarCobro(Transaccional servicio, double monto,
            String referencia) {

        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo.");
        }

        return servicio.cobrar(monto, referencia);
    }

    // El servicio decide si ejecuta u omite el reintegro
    public String procesarReintegro(Transaccional servicio, double monto) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo.");
        }

        return servicio.reintegrar(monto);
    }

    // Solicita el documento mediante el contrato Documentable.
    public String generarDocumento(Documentable servicio,
            String referencia) {

        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo.");
        }

        return servicio.generarDocumento(referencia);
    }

    // Solicita el reporte mediante el contrato Auditable.
    public String auditarServicio(Auditable servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo.");
        }

        return servicio.obtenerReporteAuditoria();
    }
}