package app;
import java.time.LocalDate;
/**
 * Representa una tarea individual con su descripción, fecha y estado.
 */

public class Tarea {
    // Atributos
    private String descripcion;
    private LocalDate fecha;
    private boolean completada;
    /**
     * Constructor: crea una nueva tarea.
     */
    public Tarea(String descripcion, LocalDate fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.completada = false; // Por defecto, no está completada
    }
    // Métodos para obtener información (getters)
    public String getDescripcion() {
        return descripcion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public boolean isCompletada() {
        return completada;
    }
    // Método para modificar el estado (setter)
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
    /**
     * Convierte la tarea a texto legible.
     */
    @Override
    public String toString() {
        String estado = completada ? "✓ Completada" : "○ Pendiente";
        return descripcion + " | Fecha: " + fecha + " | Estado: " + estado;
    }
}
