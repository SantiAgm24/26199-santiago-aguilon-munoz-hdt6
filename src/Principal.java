import java.util.Scanner;

// Vista de consola: muestra el menu y solicita los datos al usuario
public class Principal {

    private final Controlador controlador;
    private final Scanner entrada;

    // Inicia el controlador y el lector de entradas
    public Principal() {
        controlador = new Controlador();
        entrada = new Scanner(System.in);
    }

    // Crea el menu y solicita al usuario que seleccione una opcion hasta que decida salir
    public void iniciar() {
        int opcion;

        // Hoy no hay texto bonito, pero al menos hay un mensaje de bienvenida LOL
        System.out.println("SISTEMA DE INTEGRACIONES DE COMERCIO ELECTRONICO");

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            try {
                ejecutarOpcion(opcion);
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar la solicitud: "
                        + e.getMessage());
            }

        } while (opcion != 0);

        entrada.close();
    }

    // Presenta las operaciones disponibles
    private void mostrarMenu() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("1. Listar integraciones activas");
        System.out.println("2. Buscar y consultar configuracion");
        System.out.println("3. Ordenar catalogo por latencia");
        System.out.println("4. Realizar cobro sin referencia");
        System.out.println("5. Realizar cobro con referencia");
        System.out.println("6. Realizar reintegro");
        System.out.println("7. Generar documento");
        System.out.println("8. Consultar reporte de auditoria");
        System.out.println("0. Salir");
    }

    // Solicita los datos de cada operacion y muestra su resultado
    private void ejecutarOpcion(int opcion) {
        String identificador;
        String referencia;
        double monto;

        // Se ejecuta la opcion seleccionada
        switch (opcion) {
            case 1:
                System.out.println("\nINTEGRACIONES ACTIVAS");
                System.out.println(controlador.listarActivas());
                break;

            case 2:
                identificador = leerTexto(
                        "Identificador de la integracion: ");

                System.out.println(
                        controlador.consultarConfiguracion(identificador));
                break;

            case 3:
                System.out.println(
                        "\nCATALOGO ORDENADO DE MENOR A MAYOR LATENCIA");

                System.out.println(controlador.ordenarCatalogo());
                break;

            case 4:
                identificador = leerTexto(
                        "Identificador de la integracion: ");
                monto = leerDecimal("Monto a cobrar en quetzales: ");

                System.out.println(
                        controlador.cobrar(identificador, monto));
                break;

            case 5:
                identificador = leerTexto(
                        "Identificador de la integracion: ");
                monto = leerDecimal("Monto a cobrar en quetzales: ");
                referencia = leerTexto("Referencia del cobro: ");

                System.out.println(
                        controlador.cobrar(
                                identificador, monto, referencia));
                break;

            case 6:
                identificador = leerTexto(
                        "Identificador de la integracion: ");
                monto = leerDecimal("Monto a reintegrar en quetzales: ");

                System.out.println(
                        controlador.reintegrar(identificador, monto));
                break;

            case 7:
                identificador = leerTexto(
                        "Identificador de la integracion: ");
                referencia = leerTexto("Referencia del documento: ");

                System.out.println(
                        controlador.generarDocumento(
                                identificador, referencia));
                break;

            case 8:
                identificador = leerTexto(
                        "Identificador de la integracion: ");

                System.out.println(
                        controlador.auditar(identificador));
                break;

            case 0:
                System.out.println("Programa finalizado.");
                break;

            default:
                System.out.println(
                        "Opcion no valida. Seleccione un numero del 0 al 8.");
                break;
        }
    }

    // Repite la lectura hasta recibir un entero valido
    private int leerEntero(String mensaje) {
        int numero = 0;
        boolean valido = false;

        do {
            System.out.print(mensaje);

            try {
                numero = Integer.parseInt(entrada.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }

        } while (!valido);

        return numero;
    }

    // Repite la lectura hasta recibir un monto finito mayor que cero
    private double leerDecimal(String mensaje) {
        double numero = 0;
        boolean valido = false;

        do {
            System.out.print(mensaje);

            try {
                numero = Double.parseDouble(entrada.nextLine().trim());

                if (Double.isFinite(numero) && numero > 0) {
                    valido = true;
                } else {
                    System.out.println(
                            "Ingrese un numero finito mayor que cero.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Ingrese un numero valido. Use punto decimal, por ejemplo: 25.50");
            }

        } while (!valido);

        return numero;
    }

    // Repite la lectura hasta recibir texto que no este vacio
    private String leerTexto(String mensaje) {
        String texto;

        do {
            System.out.print(mensaje);
            texto = entrada.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("Este dato no puede estar vacio.");
            }

        } while (texto.isEmpty());

        return texto;
    }
}