package app;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * Clase principal que contiene el menú de la aplicación.
 */


public class Main {
    // Scanner global para leer datos del usuario
    private static Scanner scanner = new Scanner(System.in);
    // Gestor que maneja todas las tareas
    private static GestorTareas gestor = new GestorTareas();
    public static void main(String[] args) {
        // Mensaje de bienvenida
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ GESTOR DE TAREAS SIMPLE v1.0 ║");
        System.out.println("╚══════════════════════════════════════╝\n");
        // Cargar tareas guardadas previamente
        gestor.cargarDesdeArchivo();

        boolean salir = false;
        // Ciclo principal del programa
        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    System.out.println("\n➕ AGREGAR NUEVA TAREA");
                    System.out.println("━━━━━━━━━━━━━━━━━━━━━━");

                    // Pedir descripción
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine().trim();

                    // Validar que no esté vacía
                    if (descripcion.isEmpty()) {
                        System.out.println("❌ La descripción no puede estar vacía");
                        break;
                    }

                    // Pedir fecha
                    System.out.print("Fecha límite (YYYY-MM-DD): ");
                    String fechaStr = scanner.nextLine().trim();

                    // Intentar crear la tarea
                    try {
                        LocalDate fecha = LocalDate.parse(fechaStr);
                        Tarea nuevaTarea = new Tarea(descripcion, fecha);
                        gestor.agregarTarea(nuevaTarea);
                    } catch (DateTimeParseException e) {
                        System.out.println("❌ Formato de fecha inválido. Usa YYYY-MM-DD (ejemplo: 2025-12- 31)");
                    }
                    break;
                case 2:
                    //Simplemente llamar al método
                    gestor.listarTareas();
                    break;
                case 3:
                    System.out.println("\n✔️ MARCAR TAREA COMO COMPLETADA");
                    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

                    // Mostrar lista primero para que el usuario vea los índices
                    gestor.listarTareas();

                    // Verificar si hay tareas
                    if (gestor.getTareas().isEmpty()) {
                        break;
                    }

                    // Pedir índice
                    System.out.print("\nÍndice de la tarea a completar: ");
                    try {
                        int indice = Integer.parseInt(scanner.nextLine());
                        gestor.marcarCompletada(indice);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Debes ingresar un número válido");
                    }

                    break;
                case 4:
                    System.out.println("\n🗑️ ELIMINAR TAREA");
                    System.out.println("━━━━━━━━━━━━━━━━━━━━");

                    // Mostrar lista primero
                    gestor.listarTareas();

                    // Verificar si hay tareas
                    if (gestor.getTareas().isEmpty()) {
                        break;
                    }

                    // Pedir índice
                    System.out.print("\nÍndice de la tarea a eliminar: ");
                    try {
                        int indice = Integer.parseInt(scanner.nextLine());
                        gestor.eliminarTarea(indice);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Debes ingresar un número válido");
                    }

                    break;
                case 5:
                    System.out.println("\n💾 Guardando tareas...");
                    gestor.guardarEnArchivo();
                    System.out.println("👋 ¡Hasta luego!");
                    salir = true;
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intenta de nuevo.");
            }
        }
        scanner.close();
    }
    /**
     * Muestra el menú principal en pantalla.
     */
    private static void mostrarMenu() {
        System.out.println("\n════════════ MENÚ ════════════");
        System.out.println("1. ➕ Agregar tarea");
        System.out.println("2. 📄 Listar tareas");
        System.out.println("3. ✔️ Marcar como completada");
        System.out.println("4. 🗑️ Eliminar tarea");
        System.out.println("5. 🚪 Salir");
        System.out.println("══════════════════════════════");
        System.out.print("Selecciona una opción: ");
    }
    /**
     * Lee y valida la opción del menú ingresada por el usuario.
     */
    private static int leerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            return opcion;
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }
}