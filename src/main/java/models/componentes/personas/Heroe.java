package models.componentes.personas;

import java.util.List;

public class Heroe extends Persona {

    public static final int PESO_HEROE = 5;

    private long id;

    public Heroe() {
        super();
        setMultiplicador(1.5f);
        setPeso(PESO_HEROE);

        List<Integer> atributos = generarAtributos(150);
        setAtaque((int) Math.ceil(atributos.get(0) * getMultiplicador()));
        setDefensa((int) Math.ceil(atributos.get(1) * getMultiplicador()));
        setSalud((int) Math.ceil(atributos.get(2) * getMultiplicador()));
    }

    public Heroe(long id, String nombre, int ataque, int defensa, int salud, int peso) {
        this.setId(id);
        this.setNombre(nombre);
        this.setAtaque(ataque);
        this.setDefensa(defensa);
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
        return "Heroe {" +
                "id='" + id + '\'' +
                ", Nombre='" + this.getNombre() + '\'' +
                ", Ataque='" + this.getAtaque() + '\'' +
                ", Defensa='" + this.getDefensa() + '\'' +
                ", Salud='" + this.getSalud() + '\'' +
                ", Peso='" + this.getPeso() + '\'' +
                '}';
    }
}
