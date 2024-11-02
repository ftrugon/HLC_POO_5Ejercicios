package ej1;

import java.util.ArrayList;
import java.util.List;

class LibroJava {
    private String titulo;
    private String autor;
    private String isbn;
    private boolean prestado;

    public LibroJava(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.prestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }
}

class UsuarioJava {
    private String nombre;
    private String id;

    public UsuarioJava(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}

class PrestamoJava {
    private LibroJava libro;
    private UsuarioJava usuario;

    public PrestamoJava(LibroJava libro, UsuarioJava usuario) {
        this.libro = libro;
        this.usuario = usuario;
    }

    public LibroJava getLibro() {
        return libro;
    }

    public UsuarioJava getUsuario() {
        return usuario;
    }
}

class BibliotecaJava {
    private List<LibroJava> libros = new ArrayList<>();
    private List<UsuarioJava> usuarios = new ArrayList<>();
    private List<PrestamoJava> prestamos = new ArrayList<>();

    public void agregarLibro(LibroJava libro) {
        libros.add(libro);
        System.out.println("Libro '" + libro.getTitulo() + "' agregado a la biblioteca.");
    }

    public void registrarUsuario(UsuarioJava usuario) {
        for (UsuarioJava u : usuarios) {
            if (u.getId().equals(usuario.getId())) {
                System.out.println("El usuario '" + usuario.getNombre() + "' ya está registrado.");
                return;
            }
        }
        usuarios.add(usuario);
        System.out.println("Usuario '" + usuario.getNombre() + "' registrado.");

    }

    public void prestarLibro(LibroJava libro, UsuarioJava usuario) {
        if (libro.isPrestado()) {
            System.out.println("El libro '" + libro.getTitulo() + "' ya está prestado.");
        } else {
            libro.setPrestado(true);
            prestamos.add(new PrestamoJava(libro, usuario));
            System.out.println("Libro '" + libro.getTitulo() + "' prestado a '" + usuario.getNombre() + "'.");
        }
    }

    public void devolverLibro(LibroJava libro, UsuarioJava usuario) {
        PrestamoJava prestamo = null;
        for (PrestamoJava p : prestamos) {
            if (p.getLibro().equals(libro) && p.getUsuario().equals(usuario)) {
                prestamo = p;
                break;
            }
        }

        if (prestamo != null) {
            libro.setPrestado(false);
            prestamos.remove(prestamo);
            System.out.println("Libro '" + libro.getTitulo() + "' devuelto por '" + usuario.getNombre() + "'.");
        } else {
            System.out.println("El libro '" + libro.getTitulo() + "' no fue prestado a '" + usuario.getNombre() + "'.");
        }
    }

    public void mostrarEstadoLibros() {
        System.out.println("\nEstado de los libros en la biblioteca:");
        for (LibroJava libro : libros) {
            String estado = libro.isPrestado() ? "Prestado" : "Disponible";
            System.out.println("Título: " + libro.getTitulo() + ", Estado: " + estado);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BibliotecaJava biblioteca = new BibliotecaJava();

        // Crear algunos libros
        LibroJava libro1 = new LibroJava("T1 Faker", "Miguel de Cervantes", "123-ABC");
        LibroJava libro2 = new LibroJava("T1 Zeus", "Miguel de Cervantes", "456-DEF");
        LibroJava libro3 = new LibroJava("T1 Keria", "Miguel de Cervantes", "789-GHI");

        // Agregar libros a la biblioteca
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);

        // Registrar algunos usuarios
        UsuarioJava usuario1 = new UsuarioJava("Juan", "U001");
        UsuarioJava usuario2 = new UsuarioJava("Maria", "U002");
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);

        // Prestar libros a los usuarios
        biblioteca.prestarLibro(libro1, usuario1);
        biblioteca.prestarLibro(libro2, usuario2);
        biblioteca.prestarLibro(libro1, usuario2);

        // Mostrar estado de los libros
        biblioteca.mostrarEstadoLibros();

        // Devolver un libro
        biblioteca.devolverLibro(libro1, usuario1);
        biblioteca.mostrarEstadoLibros();
    }
}

