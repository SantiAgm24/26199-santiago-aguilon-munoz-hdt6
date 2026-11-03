public interface Transaccional {

    // Si el servicio no admite el cobro, entonces lo omite
    default String cobrar(double monto) {
        return "Operacion omitida: este servicio no admite cobros.";
    }

    // Si el servicio no admite el cobro con referencia, entonces lo omite
    default String cobrar(double monto, String referencia) {
        return cobrar(monto);
    }

    // Si el servicio no admite reintegros, entonces lo omite
    default String reintegrar(double monto) {
        return "Operacion omitida: este servicio no admite reintegros.";
    }
}