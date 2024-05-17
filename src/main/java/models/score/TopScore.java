package models.score;

import batallas.Ejercito;
import models.componentes.personas.General;

import java.util.Date;

public class TopScore {

    private long id;
    private Ejercito ejercito;
    private General general;
    private Date fecha;
    private int resultado;

    public TopScore() {
    }

    public TopScore(long id, Ejercito ejercito, General general, Date fecha, int resultado) {
        this.id = id;
        this.ejercito = ejercito;
        this.general = general;
        this.fecha = fecha;
        this.resultado = resultado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ejercito getEjercito() {
        return ejercito;
    }

    public void setEjercito(Ejercito ejercito) {
        this.ejercito = ejercito;
    }

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "TopScore{" +
                "id=" + id +
                ", ejercito=" + ejercito +
                ", general=" + general +
                ", fecha=" + fecha +
                ", resultado=" + resultado +
                '}';
    }
}