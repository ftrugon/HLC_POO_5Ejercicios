package ej4;

import java.util.ArrayList;
import java.util.List;


class EstudianteJava {
    private String nombre;
    private List<CursoJava> cursos;

    public EstudianteJava(String nombre) {
        this.nombre = nombre;
        this.cursos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarCurso(CursoJava curso) {
        cursos.add(curso);
        curso.agregarEstudiante(this);
    }

    public double calcularPromedio() {
        if (cursos.isEmpty()) {
            return 0.0;
        }
        return cursos.stream().mapToDouble(CursoJava::getCalificacion).average().orElse(0.0);
    }

    public void mostrarInformacion() {
        System.out.println("Estudiante: " + nombre);
        System.out.println("Promedio: " + calcularPromedio());
    }
}

class CursoJava {
    private String nombre;
    private double calificacion;
    private List<EstudianteJava> estudiantes;

    public CursoJava(String nombre, double calificacion) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.estudiantes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void agregarEstudiante(EstudianteJava estudiante) {
        estudiantes.add(estudiante);
    }

    public void mostrarEstudiantes() {
        System.out.println("Estudiantes en el curso " + nombre + ":");
        for (EstudianteJava estudiante : estudiantes) {
            System.out.println(estudiante.getNombre());
        }
    }
}

class ProfesorJava {
    private String nombre;

    public ProfesorJava(String nombre) {
        this.nombre = nombre;
    }

    public void asignarCurso(EstudianteJava estudiante, CursoJava curso) {
        estudiante.agregarCurso(curso);
        System.out.println(nombre + " ha asignado el curso '" + curso.getNombre() + "' a " + estudiante.getNombre() + ".");
    }
}

public class Main {
    public static void main(String[] args) {
        // Crear estudiantes
        EstudianteJava estudiante1 = new EstudianteJava("Juan");
        EstudianteJava estudiante2 = new EstudianteJava("Maria");

        // Crear cursos
        CursoJava cursoMatematicas = new CursoJava("Matemáticas", 85.0);
        CursoJava cursoHistoria = new CursoJava("Historia", 90.0);

        // Crear profesor
        ProfesorJava profesor = new ProfesorJava("Sr. Pérez");

        // Asignar cursos a estudiantes
        profesor.asignarCurso(estudiante1, cursoMatematicas);
        profesor.asignarCurso(estudiante1, cursoHistoria);
        profesor.asignarCurso(estudiante2, cursoMatematicas);

        // Mostrar información de los estudiantes
        estudiante1.mostrarInformacion();
        estudiante2.mostrarInformacion();

        // Mostrar estudiantes en un curso
        cursoMatematicas.mostrarEstudiantes();
        cursoHistoria.mostrarEstudiantes();
    }
}