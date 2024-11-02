package ej4

class EstudianteKotlin(val nombre: String) {
    private val cursos = mutableListOf<CursoKotlin>()

    fun agregarCurso(curso: CursoKotlin) {
        cursos.add(curso)
        curso.agregarEstudiante(this)
    }

    fun calcularPromedio(): Double {
        return if (cursos.isNotEmpty()) {
            cursos.map { it.calificacion }.average()
        } else {
            0.0
        }
    }

    fun mostrarInformacion() {
        println("Estudiante: $nombre")
        println("Promedio: ${calcularPromedio()}")
    }
}

class CursoKotlin(val nombre: String, val calificacion: Double) {
    private val estudiantes = mutableListOf<EstudianteKotlin>()

    fun agregarEstudiante(estudiante: EstudianteKotlin) {
        estudiantes.add(estudiante)
    }

    fun mostrarEstudiantes() {
        println("Estudiantes en el curso $nombre:")
        for (estudiante in estudiantes) {
            println(estudiante.nombre)
        }
    }
}

class ProfesorKotlin(val nombre: String) {
    fun asignarCurso(estudiante: EstudianteKotlin, curso: CursoKotlin) {
        estudiante.agregarCurso(curso)
        println("$nombre ha asignado el curso '${curso.nombre}' a ${estudiante.nombre}.")
    }
}

fun main() {
    // Crear estudiantes
    val estudiante1 = EstudianteKotlin("Juan")
    val estudiante2 = EstudianteKotlin("Maria")

    // Crear cursos
    val cursoMatematicas = CursoKotlin("Matemáticas", 85.0)
    val cursoHistoria = CursoKotlin("Historia", 90.0)

    // Crear profesor
    val profesor = ProfesorKotlin("Sr. Pérez")

    // Asignar cursos a estudiantes
    profesor.asignarCurso(estudiante1, cursoMatematicas)
    profesor.asignarCurso(estudiante1, cursoHistoria)
    profesor.asignarCurso(estudiante2, cursoMatematicas)

    // Mostrar información de los estudiantes
    estudiante1.mostrarInformacion()
    estudiante2.mostrarInformacion()

    // Mostrar estudiantes en un curso
    cursoMatematicas.mostrarEstudiantes()
    cursoHistoria.mostrarEstudiantes()
}