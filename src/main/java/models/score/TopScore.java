package models.score;

import batallas.Ejercito;
import models.componentes.personas.General;

import java.sql.Date;

public class TopScore {

    private long id;
    private Ejercito ejercito;
    private General general;
    private int resultado;
    private Date fecha;

    public TopScore() {
    }

    public TopScore(long id, Ejercito ejercito, General general, int resultado, Date fecha) {
        this.id = id;
        this.ejercito = ejercito;
        this.general = general;
        this.resultado = resultado;
        this.fecha = fecha;
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

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
