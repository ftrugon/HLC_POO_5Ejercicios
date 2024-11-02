package ej5

class TareaKotlin(val descripcion: String) {
    var completada: Boolean = false

    fun marcarComoCompletada() {
        completada = true
        println("La tarea '$descripcion' ha sido marcada como completada.")
    }

    override fun toString(): String {
        return "$descripcion - ${if (completada) "Completada" else "Pendiente"}"
    }
}

class ProyectoKotlin(val nombre: String) {
    private val tareas = mutableListOf<TareaKotlin>()

    fun agregarTarea(tarea: TareaKotlin) {
        tareas.add(tarea)
        println("Tarea '${tarea.descripcion}' añadida al proyecto '$nombre'.")
    }

    fun mostrarTareas() {
        println("Tareas en el proyecto '$nombre':")
        for (tarea in tareas) {
            println(tarea)
        }
    }
}

class UsuarioKotlin(val nombre: String) {
    private val proyectos = mutableListOf<ProyectoKotlin>()

    fun crearProyecto(nombreProyecto: String): ProyectoKotlin {
        val nuevoProyecto = ProyectoKotlin(nombreProyecto)
        proyectos.add(nuevoProyecto)
        println("Usuario '$nombre' ha creado el proyecto '$nombreProyecto'.")
        return nuevoProyecto
    }

    fun mostrarProyectos() {
        println("Proyectos de '$nombre':")
        for (proyecto in proyectos) {
            println(proyecto.nombre)
        }
    }
}

fun main() {
    // Crear usuario
    val usuario = UsuarioKotlin("Juan")

    // Crear proyecto
    val proyecto = usuario.crearProyecto("Proyecto de Desarrollo")

    // Crear tareas
    val tarea1 = TareaKotlin("Implementar la funcionalidad X")
    val tarea2 = TareaKotlin("Realizar pruebas")
    val tarea3 = TareaKotlin("Documentar el código")

    // Agregar tareas al proyecto
    proyecto.agregarTarea(tarea1)
    proyecto.agregarTarea(tarea2)
    proyecto.agregarTarea(tarea3)

    // Mostrar tareas
    proyecto.mostrarTareas()

    // Marcar una tarea como completada
    tarea1.marcarComoCompletada()

    // Mostrar tareas después de completar una
    proyecto.mostrarTareas()

    // Mostrar proyectos del usuario
    usuario.mostrarProyectos()
}
