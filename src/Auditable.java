public interface Auditable {

    // Cada implementacion debe devolver su reporte de auditoria al ejecutar el metodo
    String obtenerReporteAuditoria();
}