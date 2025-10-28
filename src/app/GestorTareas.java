package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que gestiona todas las operaciones sobre las tareas.
 */
public class GestorTareas {
    // Lista donde guardamos todas las tareas
    private ArrayList<Tarea> tareas;

    /**
     * Constructor: inicializa la lista vacía.
     */
    public GestorTareas() {
        this.tareas = new ArrayList<>();
    }

    // ============================================
    // MÉTODO 1: Agregar tarea
    // ============================================
    /**
     * Agrega una nueva tarea a la lista.
     */
    public void agregarTarea(Tarea tarea) {
        if (tarea == null) {
            System.out.println("❌ Error: La tarea no puede ser nula");
            return;
        }

        tareas.add(tarea);
        System.out.println("✅ Tarea agregada exitosamente");
    }

    // ============================================
    // MÉTODO 2: Listar tareas
    // ============================================
    /**
     * Muestra todas las tareas en pantalla.
     */
    public void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("\n📭 No hay tareas registradas");
            return;
        }

        System.out.println("\n📋 LISTA DE TAREAS");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        for (int i = 0; i < tareas.size(); i++) {
            System.out.println("[" + i + "] " + tareas.get(i).toString());
        }

        int completadas = 0;
        for (Tarea t : tareas) {
            if (t.isCompletada()) completadas++;
        }

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Total: " + tareas.size() + " | Completadas: " + completadas +
                " | Pendientes: " + (tareas.size() - completadas));
    }

    // ============================================
    // MÉTODO 3: Marcar como completada
    // ============================================
    /**
     * Marca una tarea como completada usando su índice.
     */
    public void marcarCompletada(int indice) {
        if (indice < 0 || indice >= tareas.size()) {
            System.out.println("❌ Índice inválido. Debe estar entre 0 y " + (tareas.size() - 1));
            return;
        }

        Tarea tarea = tareas.get(indice);

        if (tarea.isCompletada()) {
            System.out.println("ℹ️  La tarea ya estaba completada");
        } else {
            tarea.setCompletada(true);
            System.out.println("✅ Tarea marcada como completada: " + tarea.getDescripcion());
        }
    }

    // ============================================
    // MÉTODO 4: Eliminar tarea
    // ============================================
    /**
     * Elimina una tarea de la lista usando su índice.
     */
    public void eliminarTarea(int indice) {
        if (indice < 0 || indice >= tareas.size()) {
            System.out.println("❌ Índice inválido. Debe estar entre 0 y " + (tareas.size() - 1));
            return;
        }

        Tarea tareaEliminada = tareas.remove(indice);
        System.out.println("🗑️  Tarea eliminada: " + tareaEliminada.getDescripcion());
    }

    // ============================================
    // MÉTODO 5: Persistencia (guardar y cargar)
    // ============================================

    /**
     * Guarda las tareas en el archivo tareas.txt.
     */
    public void guardarEnArchivo() {
        try {
            FileWriter writer = new FileWriter("tareas.txt");

            for (Tarea tarea : tareas) {
                String linea = tarea.getDescripcion() + "," +
                        tarea.getFecha().toString() + "," +
                        tarea.isCompletada() + "\n";
                writer.write(linea);
            }

            writer.close();
            System.out.println("💾 Tareas guardadas correctamente (" + tareas.size() + " tarea(s))");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar: " + e.getMessage());
        }
    }

    /**
     * Carga las tareas desde el archivo tareas.txt.
     */
    public void cargarDesdeArchivo() {
        File archivo = new File("tareas.txt");

        if (!archivo.exists()) {
            System.out.println("ℹ️  No hay archivo previo. Iniciando con lista vacía.");
            return;
        }

        try {
            Scanner fileScanner = new Scanner(archivo);
            int tareasCargadas = 0;

            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine().trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    try {
                        String descripcion = partes[0];
                        LocalDate fecha = LocalDate.parse(partes[1]);
                        boolean completada = Boolean.parseBoolean(partes[2]);

                        Tarea tarea = new Tarea(descripcion, fecha);
                        tarea.setCompletada(completada);
                        tareas.add(tarea);
                        tareasCargadas++;
                    } catch (Exception e) {
                        System.out.println("⚠️  Error al procesar línea: " + linea);
                    }
                }
            }

            fileScanner.close();
            System.out.println("📂 Se cargaron " + tareasCargadas + " tarea(s) desde el archivo");
        } catch (IOException e) {
            System.out.println("❌ Error al cargar archivo: " + e.getMessage());
        }
    }

    // Método auxiliar
    public ArrayList<Tarea> getTareas() {
        return tareas;
    }
}