public abstract class IntegracionAPI implements Transaccional, Documentable, Auditable, Comparable<IntegracionAPI> {
//Es clase abstracta porque no se puede instanciar directamente, sino que se debe crear una subclase concreta que implemente los métodos abstractos de las interfaces.

    private final String identificador;
    private String nombre;
    private String urlBase;
    private String claveApi;
    private boolean activa;
    private String estadoConexion;
    private double latenciaPromedio;

    // Inicializa los datos comunes y valida sus valores.
    public IntegracionAPI(String identificador, String nombre, String urlBase, String claveApi, boolean activa, String estadoConexion, double latenciaPromedio) {

        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador no puede estar vacio.");
        }

        this.identificador = identificador.trim();
        setNombre(nombre);
        setUrlBase(urlBase);
        setClaveApi(claveApi);
        setActiva(activa);
        setEstadoConexion(estadoConexion);
        setLatenciaPromedio(latenciaPromedio);
    }

    // Devuelve el estado actual de conexion
    public String consultarEstado() {
        return estadoConexion;
    }

    // Devuelve la configuracion sin revelar la clave de acceso
    public String obtenerConfiguracion() {
        return "Identificador: " + identificador
                + "\nNombre: " + nombre
                + "\nURL base: " + urlBase
                + "\nClave API: ********"
                + "\nActiva: " + (activa ? "Si" : "No")
                + "\nEstado de conexion: " + estadoConexion
                + "\nLatencia promedio: " + latenciaPromedio + " ms";
    }

    // Implementa el contrato de auditoria para todas las subclases
    @Override
    public String obtenerReporteAuditoria() {
        return "Reporte de auditoria"
                + "\nTipo de integracion: " + getClass().getSimpleName()
                + "\n" + obtenerConfiguracion();
    }

    // Mantiene un orden de menor a mayor latencia
    @Override
    public int compareTo(IntegracionAPI otra) {
        return Double.compare(
                this.latenciaPromedio, otra.latenciaPromedio);
    }

    // Representa la integracion para los listados del catalogo
    @Override
    public String toString() {
        return "ID: " + identificador
                + " | Nombre: " + nombre
                + " | Activa: " + (activa ? "Si" : "No")
                + " | Conexion: " + estadoConexion
                + " | Latencia: " + latenciaPromedio + " ms";
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }

        this.nombre = nombre.trim();
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        if (urlBase == null || urlBase.trim().isEmpty()) {
            throw new IllegalArgumentException("La URL base no puede estar vacia.");
        }

        this.urlBase = urlBase.trim();
    }

    // Permite actualizar la clave sin dar un getter que la pueda exponer
    public void setClaveApi(String claveApi) {
        if (claveApi == null || claveApi.trim().isEmpty()) {
            throw new IllegalArgumentException("La clave API no puede estar vacia.");
        }

        this.claveApi = claveApi;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getEstadoConexion() {
        return estadoConexion;
    }

    public void setEstadoConexion(String estadoConexion) {
        if (estadoConexion == null
                || estadoConexion.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de conexion no puede estar vacio.");
        }

        this.estadoConexion = estadoConexion.trim();
    }

    public double getLatenciaPromedio() {
        return latenciaPromedio;
    }

    public void setLatenciaPromedio(double latenciaPromedio) {
        if (!Double.isFinite(latenciaPromedio)
                || latenciaPromedio < 0) {
            throw new IllegalArgumentException("La latencia tiene que ser un numero finito mayor o igual a cero.");
        }

        this.latenciaPromedio = latenciaPromedio;
    }
}