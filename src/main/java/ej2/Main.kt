package ej2


class HabitacionKotlin(val numero: Int, val tipo: String, var disponible: Boolean = true)

class ClienteKotlin(val nombre: String, val id: String)

class ReservaKotlin(val habitacion: HabitacionKotlin, val cliente: ClienteKotlin)

class HotelKotlin {
    private val habitaciones = mutableListOf<HabitacionKotlin>()
    private val reservas = mutableListOf<ReservaKotlin>()

    fun agregarHabitacion(habitacion: HabitacionKotlin) {

        val agregada = habitaciones.find { it.numero == habitacion.numero }

        if (agregada == null){
            habitaciones.add(habitacion)
            println("Habitación número ${habitacion.numero} agregada al hotel.")
        }else println("La habitacion que intentas agreagar ya esta en la base de datos")

    }

    fun reservarHabitacion(habitacion: HabitacionKotlin, cliente: ClienteKotlin) {
        if (!habitacion.disponible) {
            println("La habitación número ${habitacion.numero} ya está reservada.")
        } else {
            habitacion.disponible = false
            val reserva = ReservaKotlin(habitacion, cliente)
            reservas.add(reserva)
            println("Habitación número ${habitacion.numero} reservada para ${cliente.nombre}.")
        }
    }

    fun cancelarReserva(habitacion: HabitacionKotlin, cliente: ClienteKotlin) {
        val reserva = reservas.find { it.habitacion == habitacion && it.cliente == cliente }
        if (reserva != null) {
            habitacion.disponible = true
            reservas.remove(reserva)
            println("Reserva de la habitación número ${habitacion.numero} cancelada para ${cliente.nombre}.")
        } else {
            println("No se encontró una reserva activa para la habitación número ${habitacion.numero} a nombre de ${cliente.nombre}.")
        }
    }

    fun mostrarReservasActivas() {
        if (reservas.isEmpty()) {
            println("No hay reservas activas.")
        } else {
            println("Reservas activas:")
            reservas.forEach { reserva ->
                println("Habitación número ${reserva.habitacion.numero}, Cliente: ${reserva.cliente.nombre}")
            }
        }
    }
}

fun main() {
    val hotel = HotelKotlin()

    // Crear habitaciones
    val habitacion1 = HabitacionKotlin(101, "Individual")
    val habitacion2 = HabitacionKotlin(102, "Doble")

    // Agregar habitaciones al hotel
    hotel.agregarHabitacion(habitacion1)
    hotel.agregarHabitacion(habitacion2)

    // Crear clientes
    val cliente1 = ClienteKotlin("Juan", "C001")
    val cliente2 = ClienteKotlin("Maria", "C002")

    // Realizar reservas
    hotel.reservarHabitacion(habitacion1, cliente1)
    hotel.reservarHabitacion(habitacion2, cliente2)
    hotel.reservarHabitacion(habitacion1, cliente2) // Intento de reservar una habitación ya ocupada

    // Mostrar reservas activas
    hotel.mostrarReservasActivas()

    // Cancelar una reserva
    hotel.cancelarReserva(habitacion1, cliente1)
    hotel.mostrarReservasActivas()
}
