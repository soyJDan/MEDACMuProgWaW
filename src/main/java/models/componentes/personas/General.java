/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.componentes.personas;

import java.util.List;
import java.util.Random;

/**
 * @author danie
 */
public class General extends Persona {
    public static final int PESO_GENERAL = 1;

    private long id;

    public General() {
        super();
        Random random = new Random();
        setMultiplicador(random.nextFloat(2, 3));
        setPeso(PESO_GENERAL);

        List<Integer> atributos = generarAtributos(100);
        setAtaque((int) Math.ceil(atributos.get(0) * getMultiplicador()));
        setDefensa((int) Math.ceil(atributos.get(1) * getMultiplicador()));
        setSalud((int) Math.ceil(atributos.get(2) * getMultiplicador()));
    }

    public General(long id, String nombre, int ataque, int defensa, int salud, int peso) {
        this.setId(id);
        this.setNombre(nombre);
        this.setAtaque(ataque);
        this.setId(defensa);
        this.setSalud(salud);
        this.setPeso(peso);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "General {" +
                "id='" + id + '\'' +
                ", Nombre='" + this.getNombre() + '\'' +
                ", Ataque='" + this.getAtaque() + '\'' +
                ", Defensa='" + this.getDefensa() + '\'' +
                ", Salud='" + this.getSalud() + '\'' +
                ", Peso='" + this.getPeso() + '\'' +
                '}';
    }
}
