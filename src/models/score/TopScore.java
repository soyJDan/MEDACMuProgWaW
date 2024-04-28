package models.score;

import batallas.Ejercito;

import java.sql.Date;

public class TopScore {

    private long id;
    private Ejercito ejercito;
    private Date fecha;

    public TopScore() {
    }

    public TopScore(long id, Ejercito ejercito, Date fecha) {
        this.id = id;
        this.ejercito = ejercito;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
