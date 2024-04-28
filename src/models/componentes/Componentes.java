/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.componentes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>Clase que representa un componente de un ejército.</p>
 *
 * @author Daniel Ojados
 * @author Daniel Romero
 * @version 1.0
 */
public class Componentes {

    private static final int MINIMO_ATRIBUTOS = 25;
    private static final Random random = new Random();
    private static final ArrayList<Integer> ids = new ArrayList<>();
    private String nombre;
    private int ataque;
    private int defensa;
    private int salud;
    private int peso;
    private float multiplicador;


    public Componentes() {
        this.nombre = getID();
        this.ataque = 0;
        this.defensa = 0;
        this.salud = 0;
        this.peso = 0;
        this.multiplicador = 0f;
    }

    public static String getID() {
        Integer rID;
        while (true) {
            rID = random.nextInt(1000);
            if (!ids.contains(rID)) {
                ids.add(rID);
                break;
            }
        }
        return Integer.toString(rID);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public float getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(float multiplicador) {
        this.multiplicador = multiplicador;
    }

    public void recibirDano(int dano) {
        salud -= dano;
    }

    public List<Integer> generarAtributos(int limiteSuperior) {
        ArrayList<Integer> atributos = new ArrayList<>();

        atributos.add(random.nextInt(MINIMO_ATRIBUTOS, limiteSuperior));
        atributos.add(random.nextInt(limiteSuperior - atributos.get(0)));
        atributos.add(limiteSuperior - (atributos.get(1) + atributos.get(0)));

        for (Integer atributo : atributos) {
            if (atributo == 0) {
                generarAtributos(limiteSuperior);
            }
        }

        return atributos;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "Nombre='" + this.getNombre() + '\'' +
                ", Ataque='" + this.getAtaque() + '\'' +
                ", Defensa='" + this.getDefensa() + '\'' +
                ", Peso='" + this.getPeso() + '\'' +
                '}';
    }
}
