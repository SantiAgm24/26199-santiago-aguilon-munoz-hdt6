public interface Documentable {

    // Si el servicio no admite la generacion de documentos, entonces lo omite.
    default String generarDocumento(String referencia) {
        return "Operacion omitida: este servicio no genera documentos.";
    }
}