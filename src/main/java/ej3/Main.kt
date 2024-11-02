package ej3

abstract class PersonajeKotlin(val nombre: String, var puntosDeVida: Int) {
    open fun atacar(objetivo: PersonajeKotlin) {
        println("$nombre ataca a ${objetivo.nombre}.")
    }

    open fun estaVivo(): Boolean {
        return puntosDeVida > 0
    }

    open fun recibirDanio(danio: Int) {
        puntosDeVida -= danio
        if (puntosDeVida <= 0) {
            println("$nombre ha sido derrotado.")
        } else {
            println("$nombre tiene ahora $puntosDeVida puntos de vida.")
        }
    }

}

class GuerreroKoltin(nombre: String, puntosDeVida: Int, private val fuerza: Int) : PersonajeKotlin(nombre, puntosDeVida) {
    override fun atacar(objetivo: PersonajeKotlin) {
        if (estaVivo()) {
            println("$nombre (Guerrero) ataca a ${objetivo.nombre} con fuerza de $fuerza.")
            objetivo.recibirDanio(fuerza)
        } else {
            println("$nombre no puede atacar, está fuera de combate.")
        }
    }

}

class MagoKotlin(nombre: String, puntosDeVida: Int, private val poderMagico: Int) : PersonajeKotlin(nombre, puntosDeVida) {
    private val hechizos = mutableListOf<HechizoKotlin>()

    fun aprenderHechizo(hechizo: HechizoKotlin) {
        hechizos.add(hechizo)
        println("$nombre ha aprendido el hechizo '${hechizo.nombre}'.")
    }

    fun lanzarHechizo(hechizo: HechizoKotlin, objetivo: PersonajeKotlin) {
        if (hechizos.contains(hechizo) && estaVivo()) {
            println("$nombre lanza el hechizo '${hechizo.nombre}' sobre ${objetivo.nombre}.")
            objetivo.recibirDanio(hechizo.danio + (poderMagico * 0.50).toInt())
        } else {
            println("$nombre no puede lanzar el hechizo '${hechizo.nombre}'.")
        }
    }
}

class HechizoKotlin(val nombre: String, val danio: Int)

fun main() {
    // Crear personajes
    val guerrero = GuerreroKoltin("Gordo mamon", 100, 15)
    val mago = MagoKotlin("Gandalf", 80, 20)

    // Crear hechizos
    val bolaDeFuego = HechizoKotlin("Bola de Fuego", 25)
    val rayo = HechizoKotlin("Rayo", 30)

    // Mago aprende hechizos
    mago.aprenderHechizo(bolaDeFuego)
    mago.aprenderHechizo(rayo)

    // Escena de combate
    guerrero.atacar(mago)
    mago.lanzarHechizo(bolaDeFuego, guerrero)
    guerrero.atacar(mago)
    mago.lanzarHechizo(rayo, guerrero)
}
