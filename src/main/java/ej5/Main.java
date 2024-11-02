package ej5;

import java.util.ArrayList;
import java.util.List;

class Tarea {
    private String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    public void marcarComoCompletada() {
        completada = true;
        System.out.println("La tarea '" + descripcion + "' ha sido marcada como completada.");
    }

    @Override
    public String toString() {
        return descripcion + " - " + (completada ? "Completada" : "Pendiente");
    }
}

class Proyecto {
    private String nombre;
    private List<Tarea> tareas;

    public Proyecto(String nombre) {
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
        System.out.println("Tarea '" + tarea.toString() + "' añadida al proyecto '" + nombre + "'.");
    }

    public void mostrarTareas() {
        System.out.println("Tareas en el proyecto '" + nombre + "':");
        for (Tarea tarea : tareas) {
            System.out.println(tarea);
        }
    }
}

class Usuario {
    private String nombre;
    private List<Proyecto> proyectos;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.proyectos = new ArrayList<>();
    }

    public Proyecto crearProyecto(String nombreProyecto) {
        Proyecto nuevoProyecto = new Proyecto(nombreProyecto);
        proyectos.add(nuevoProyecto);
        System.out.println("Usuario '" + nombre + "' ha creado el proyecto '" + nombreProyecto + "'.");
        return nuevoProyecto;
    }

    public void mostrarProyectos() {
        System.out.println("Proyectos de '" + nombre + "':");
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto.getNombre());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Crear usuario
        Usuario usuario = new Usuario("Juan");

        // Crear proyecto
        Proyecto proyecto = usuario.crearProyecto("Proyecto de Desarrollo");

        // Crear tareas
        Tarea tarea1 = new Tarea("Implementar la funcionalidad X");
        Tarea tarea2 = new Tarea("Realizar pruebas");
        Tarea tarea3 = new Tarea("Documentar el código");

        // Agregar tareas al proyecto
        proyecto.agregarTarea(tarea1);
        proyecto.agregarTarea(tarea2);
        proyecto.agregarTarea(tarea3);

        // Mostrar tareas
        proyecto.mostrarTareas();

        // Marcar una tarea como completada
        tarea1.marcarComoCompletada();

        // Mostrar tareas después de completar una
        proyecto.mostrarTareas();

        // Mostrar proyectos del usuario
        usuario.mostrarProyectos();
    }
}
