package ej2;
import java.util.ArrayList;
import java.util.List;

class HabitacionJava {
    private int numero;
    private String tipo;
    private boolean disponible;

    public HabitacionJava(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}

class ClienteJava {
    private String nombre;
    private String id;

    public ClienteJava(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
}

class ReservaJava {
    private HabitacionJava habitacion;
    private ClienteJava cliente;

    public ReservaJava(HabitacionJava habitacion, ClienteJava cliente) {
        this.habitacion = habitacion;
        this.cliente = cliente;
    }

    public HabitacionJava getHabitacion() {
        return habitacion;
    }

    public ClienteJava getCliente() {
        return cliente;
    }
}

class HotelJava {
    private List<HabitacionJava> habitaciones = new ArrayList<>();
    private List<ReservaJava> reservas = new ArrayList<>();

    public void agregarHabitacion(HabitacionJava habitacion) {

        for (HabitacionJava h : habitaciones) {
            if (h.getNumero() == habitacion.getNumero()) {
                System.out.println("La habitación número " + habitacion.getNumero() + " ya existe en el hotel.");
                return;
            }
        }
        habitaciones.add(habitacion);
        System.out.println("Habitación número " + habitacion.getNumero() + " agregada al hotel.");
    }

    public void reservarHabitacion(HabitacionJava habitacion, ClienteJava cliente) {
        if (!habitacion.isDisponible()) {
            System.out.println("La habitación número " + habitacion.getNumero() + " ya está reservada.");
        } else {
            habitacion.setDisponible(false);
            ReservaJava reserva = new ReservaJava(habitacion, cliente);
            reservas.add(reserva);
            System.out.println("Habitación número " + habitacion.getNumero() + " reservada para " + cliente.getNombre() + ".");
        }
    }

    public void cancelarReserva(HabitacionJava habitacion, ClienteJava cliente) {
        ReservaJava reservaEncontrada = null;
        for (ReservaJava reserva : reservas) {
            if (reserva.getHabitacion().equals(habitacion) && reserva.getCliente().equals(cliente)) {
                reservaEncontrada = reserva;
                break;
            }
        }

        if (reservaEncontrada != null) {
            habitacion.setDisponible(true);
            reservas.remove(reservaEncontrada);
            System.out.println("Reserva de la habitación número " + habitacion.getNumero() + " cancelada para " + cliente.getNombre() + ".");
        } else {
            System.out.println("No se encontró una reserva activa para la habitación número " + habitacion.getNumero() + " a nombre de " + cliente.getNombre() + ".");
        }
    }

    public void mostrarReservasActivas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas activas.");
        } else {
            System.out.println("Reservas activas:");
            for (ReservaJava reserva : reservas) {
                System.out.println("Habitación número " + reserva.getHabitacion().getNumero() + ", Cliente: " + reserva.getCliente().getNombre());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        HotelJava hotel = new HotelJava();

        // Crear habitaciones
        HabitacionJava habitacion1 = new HabitacionJava(101, "Individual");
        HabitacionJava habitacion2 = new HabitacionJava(102, "Doble");

        // Agregar habitaciones al hotel
        hotel.agregarHabitacion(habitacion1);
        hotel.agregarHabitacion(habitacion2);
        hotel.agregarHabitacion(habitacion1); // Intento de agregar habitación duplicada

        // Crear clientes
        ClienteJava cliente1 = new ClienteJava("Juan", "C001");
        ClienteJava cliente2 = new ClienteJava("Maria", "C002");

        // Realizar reservas
        hotel.reservarHabitacion(habitacion1, cliente1);
        hotel.reservarHabitacion(habitacion2, cliente2);
        hotel.reservarHabitacion(habitacion1, cliente2); // Intento de reservar una habitación ya ocupada

        // Mostrar reservas activas
        hotel.mostrarReservasActivas();

        // Cancelar una reserva
        hotel.cancelarReserva(habitacion1, cliente1);
        hotel.mostrarReservasActivas();
    }
}
