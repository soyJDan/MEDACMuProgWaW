/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallas;

/**
 *
 * @author danie
 */
public class Ronda {
    private int numRonda;
    private static Ejercito atacante;
    private static Ejercito defensor;
    private final int resultado;
    
    public Ronda(int ronda, Ejercito atacante, Ejercito defensor){
        this.numRonda = ronda;
        Ronda.atacante = atacante;
        Ronda.defensor = defensor;
        this.resultado = luchar();
    }
    
    private int luchar(){
        return atacante.getAtaque() - defensor.getDefensa();
    }
    
    public int getResultado(){
        return resultado;
    }

    public int getNumRonda() {
        return numRonda;
    }

    public Ejercito getAtacante() {
        return atacante;
    }

    public Ejercito getDefensor() {
        return defensor;
    }

    @Override
    public String toString() {
        return "Ronda{" +
                "numRonda=" + numRonda +
                ", resultado=" + resultado +
                '}';
    }
}
