package ej3;

import java.util.ArrayList;
import java.util.List;

class Personaje {
    private String nombre;
    private int puntosDeVida;

    public Personaje(String nombre, int puntosDeVida) {
        this.nombre = nombre;
        this.puntosDeVida = puntosDeVida;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    public boolean estaVivo() {
        return puntosDeVida > 0;
    }

    public void recibirDanio(int danio) {
        puntosDeVida -= danio;
        if (puntosDeVida <= 0) {
            System.out.println(nombre + " ha sido derrotado.");
        } else {
            System.out.println(nombre + " tiene ahora " + puntosDeVida + " puntos de vida.");
        }
    }

    public void atacar(Personaje objetivo) {
        System.out.println(nombre + " ataca a " + objetivo.getNombre() + ".");
    }
}

class Guerrero extends Personaje {
    private int fuerza;

    public Guerrero(String nombre, int puntosDeVida, int fuerza) {
        super(nombre, puntosDeVida);
        this.fuerza = fuerza;
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (estaVivo()) {
            System.out.println(getNombre() + " (Guerrero) ataca a " + objetivo.getNombre() + " con fuerza de " + fuerza + ".");
            objetivo.recibirDanio(fuerza);
        } else {
            System.out.println(getNombre() + " no puede atacar, está fuera de combate.");
        }
    }
}

class Mago extends Personaje {
    private int poderMagico;
    private List<Hechizo> hechizos;

    public Mago(String nombre, int puntosDeVida, int poderMagico) {
        super(nombre, puntosDeVida);
        this.poderMagico = poderMagico;
        this.hechizos = new ArrayList<>();
    }

    public void aprenderHechizo(Hechizo hechizo) {
        hechizos.add(hechizo);
        System.out.println(getNombre() + " ha aprendido el hechizo '" + hechizo.getNombre() + "'.");
    }

    public void lanzarHechizo(Hechizo hechizo, Personaje objetivo) {
        if (hechizos.contains(hechizo) && estaVivo()) {
            System.out.println(getNombre() + " lanza el hechizo '" + hechizo.getNombre() + "' sobre " + objetivo.getNombre() + ".");
            objetivo.recibirDanio(hechizo.getDanio());
        } else {
            System.out.println(getNombre() + " no puede lanzar el hechizo '" + hechizo.getNombre() + "'.");
        }
    }
}

class Hechizo {
    private String nombre;
    private int danio;

    public Hechizo(String nombre, int danio) {
        this.nombre = nombre;
        this.danio = danio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDanio() {
        return danio;
    }
}

public class Main {
    public static void main(String[] args) {
        // Crear personajes
        Guerrero guerrero = new Guerrero("Aragorn", 100, 15);
        Mago mago = new Mago("Gandalf", 80, 20);

        // Crear hechizos
        Hechizo bolaDeFuego = new Hechizo("Bola de Fuego", 25);
        Hechizo rayo = new Hechizo("Rayo", 30);

        // Mago aprende hechizos
        mago.aprenderHechizo(bolaDeFuego);
        mago.aprenderHechizo(rayo);

        // Escena de combate
        guerrero.atacar(mago);
        mago.lanzarHechizo(bolaDeFuego, guerrero);
        guerrero.atacar(mago);
        mago.lanzarHechizo(rayo, guerrero);
    }
}
