package ej1

class LibroKotlin(val titulo: String, val autor: String, val isbn: String, var prestado: Boolean = false) {
}

class UsuarioKotlin(val nombre: String, val id: String)


class PrestamoKotlin(val usuario: UsuarioKotlin, val libro:LibroKotlin)


// Clase Biblioteca
class Biblioteca {
    private val libros = mutableListOf<LibroKotlin>()
    private val usuarios = mutableListOf<UsuarioKotlin>()
    private val prestamos = mutableListOf<PrestamoKotlin>()

    fun agregarLibro(libro: LibroKotlin) {
        libros.add(libro)
        println("Libro '${libro.titulo}' agregado a la biblioteca.")
    }

    fun registrarUsuario(usuario: UsuarioKotlin) {
        usuarios.add(usuario)
        println("Usuario '${usuario.nombre}' registrado.")
    }

    fun prestarLibro(libro: LibroKotlin, usuario: UsuarioKotlin) {
        if (libro.prestado) {
            println("El libro '${libro.titulo}' ya está prestado.")
        } else {
            libro.prestado = true
            prestamos.add(PrestamoKotlin(usuario, libro))
            println("Libro '${libro.titulo}' prestado a '${usuario.nombre}'.")
        }
    }

    fun devolverLibro(libro: LibroKotlin, usuario: UsuarioKotlin) {
        val prestamo = prestamos.find { it.libro == libro && it.usuario == usuario }
        if (prestamo != null) {
            libro.prestado = false
            prestamos.remove(prestamo)
            println("Libro '${libro.titulo}' devuelto por '${usuario.nombre}'.")
        } else {
            println("El libro '${libro.titulo}' no fue prestado a '${usuario.nombre}'.")
        }
    }

    fun mostrarEstadoLibros() {
        println("\nEstado de los libros en la biblioteca:")
        libros.forEach { 
            val estado = if (it.prestado) "Prestado" else "Disponible"
            println("Título: ${it.titulo}, Autor: ${it.autor}, ISBN: ${it.isbn}, Estado: $estado")
        }
    }
}

fun main() {
    val biblioteca = Biblioteca()

    // Agregar libros a la biblioteca
    val libro1 = LibroKotlin("T1 Faker", "Miguel de Cervantes", "123-ABC")
    val libro2 = LibroKotlin("T1 Zeus", "Miguel de Cervantes", "456-DEF")
    val libro3 = LibroKotlin("T1 Keria", "Miguel de Cervantes", "789-GHI")
    biblioteca.agregarLibro(libro1)
    biblioteca.agregarLibro(libro2)
    biblioteca.agregarLibro(libro3)

    // Registrar algunos usuarios
    val usuario1 = UsuarioKotlin("Fran", "U001")
    val usuario2 = UsuarioKotlin("Paco", "U002")
    biblioteca.registrarUsuario(usuario1)
    biblioteca.registrarUsuario(usuario2)

    // Prestar libros a los usuarios
    biblioteca.prestarLibro(libro1, usuario1)
    biblioteca.prestarLibro(libro2, usuario2)
    biblioteca.prestarLibro(libro1, usuario2)

    // Mostrar estado de los libros
    biblioteca.mostrarEstadoLibros()

    // Devolver un libro
    biblioteca.devolverLibro(libro1, usuario1)
    biblioteca.mostrarEstadoLibros()
}
