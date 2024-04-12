/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallas;

import componentes.Componentes;
import componentes.animales.Elefante;
import componentes.animales.Tigre;
import componentes.personas.Caballeria;
import componentes.personas.General;
import componentes.personas.Infanteria;
import excepciones.animales.MaxAnimalesException;
import excepciones.batallas.*;
import excepciones.personas.GeneralMinimoException;
import excepciones.personas.MaxCapGeneralException;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>Clase que representa un ejército.</p>
 *
 * @author Daniel Ojados
 * @author Daniel Romero
 * @version 1.0
 */
public class Ejercito implements Serializable {

//    @Serial
//    private static final long serialVersionUID = 1L;

    private static final int MAX_PESO = 50;
    private static final int MAX_ANIMALES = 3;
    private static final int MIN_UNIDADES = 2;
    private static final List<String> nombres = new ArrayList<>();
    private final ArrayList<Componentes> unidades = new ArrayList<>();
    private int contadorAnimales = 0;
    private boolean hayGeneral = false;
    private int ataque;
    private int defensa;
    private int salud;
    private int saldoPeso;
    private String nombre;
    private int resultExecute = 0;

    public Ejercito() {

        nombre = "";
        saldoPeso = 0;
        restablecerAtributos();
    }

    public static int getMaxPeso() {
        return MAX_PESO;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getSalud() {
        return salud;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSaldoPeso() {
        return saldoPeso;
    }

    public void menu(String opcion) {


        String[] opciones = {"Crear ID para ejército", "Añadir infantería",
                "Añadir caballería", "Añadir general", "Añadir elefante", "Añadir tigre",
                "Consultar saldo ejército", "Eliminar unidad", "Salir y confirmar"};


        char letra = 97; // Letra en código ASCII (a)
        for (String text : opciones) {
            System.out.println((letra) + ". " + text);
            letra++;
        }

        switch (opcion) {
            case "a":
                if (nombre.isBlank() || nombre.isEmpty()) {
                    System.out.print(System.lineSeparator() + "Asignale un nombre a tu ejército: ");

                    if (!nombre.isEmpty()) {
                        System.out.println("Nombre del ejército: " + nombre + System.lineSeparator());
                    }
                } else {
                    System.out.println(System.lineSeparator() + "El ejército ya tiene un nombre asignado.");
                    System.out.println("Nombre del ejército: " + nombre + System.lineSeparator());
                }

                break;
            case "b":

                try {
                    if ((saldoPeso + Infanteria.PESO_INFANTERIA) <= MAX_PESO) {
                        adicionarUnidad(new Infanteria());
                        imprimirInfo(unidades.getLast());
                        resultExecute = 1;
                    } else {
                        resultExecute = 0;

                        if (saldoPeso == MAX_PESO) {
                            throw new MaxCapPesoEjercitoException(Message.MAX_CAP_PESO_EJERCITO);
                        } else {
                            JOptionPane.showMessageDialog(null, Message.UNIDAD_SUPERA_PESO + " " + Message.PESO_DISPONIBLE
                                    + (MAX_PESO - saldoPeso));
                        }
                    }
                } catch (MaxCapPesoEjercitoException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                break;
            case "c":

                try {
                    if ((saldoPeso + Caballeria.PESO_CABALLERIA) <= MAX_PESO) {
                        adicionarUnidad(new Caballeria());
                        imprimirInfo(unidades.getLast());
                        resultExecute = 1;
                    } else {
                        resultExecute = 0;

                        if (saldoPeso == MAX_PESO) {
                            throw new MaxCapPesoEjercitoException(Message.MAX_CAP_PESO_EJERCITO);
                        } else {
                            JOptionPane.showMessageDialog(null, Message.UNIDAD_SUPERA_PESO + " " + Message.PESO_DISPONIBLE
                                    + (MAX_PESO - saldoPeso));
                        }
                    }
                } catch (MaxCapPesoEjercitoException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                break;
            case "d":
                try {
                    if (((saldoPeso + General.PESO_GENERAL) <= MAX_PESO) && !hayGeneral) {
                        adicionarUnidad(new General());
                        imprimirInfo(unidades.getLast());
                        resultExecute = 1;
                    } else {
                        resultExecute = 0;

                        if (saldoPeso == MAX_PESO) {
                            throw new MaxCapPesoEjercitoException(Message.MAX_CAP_PESO_EJERCITO);
                        } else {
                            throw new MaxCapGeneralException(Message.GENERAL_EXISTENTE);
                        }
                    }
                } catch (MaxCapPesoEjercitoException | MaxCapGeneralException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                break;
            case "e":
                try {
                    if (((saldoPeso + Elefante.PESO_ELEFANTE) <= MAX_PESO) && contadorAnimales < MAX_ANIMALES) {
                        adicionarUnidad(new Elefante());
                        imprimirInfo(unidades.getLast());
                        resultExecute = 1;
                    } else {
                        resultExecute = 0;

                        if (saldoPeso == MAX_PESO) {
                            throw new MaxCapPesoEjercitoException(Message.MAX_CAP_PESO_EJERCITO);
                        } else if (MAX_ANIMALES == contadorAnimales) {
                            throw new MaxAnimalesException(Message.MAX_ANIMALES);
                        } else {
                            JOptionPane.showMessageDialog(null, Message.UNIDAD_SUPERA_PESO + " " + Message.PESO_DISPONIBLE
                                    + (MAX_PESO - saldoPeso));
                        }
                    }
                } catch (MaxCapPesoEjercitoException | MaxAnimalesException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                break;
            case "f":
                try {
                    if ((saldoPeso + Tigre.PESO_TIGRE) <= MAX_PESO && contadorAnimales < MAX_ANIMALES) {
                        adicionarUnidad(new Tigre());
                        imprimirInfo(unidades.getLast());
                        resultExecute = 1;
                    } else {
                        resultExecute = 0;

                        if (saldoPeso == MAX_PESO) {
                            throw new MaxCapPesoEjercitoException(Message.MAX_CAP_PESO_EJERCITO);
                        } else if (MAX_ANIMALES == contadorAnimales) {
                            throw new MaxAnimalesException(Message.MAX_ANIMALES);
                        } else {
                            JOptionPane.showMessageDialog(null, Message.UNIDAD_SUPERA_PESO + " " + Message.PESO_DISPONIBLE
                                    + (MAX_PESO - saldoPeso));
                        }
                    }
                } catch (MaxAnimalesException | MaxCapPesoEjercitoException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                break;
            case "g":
                System.out.println(Message.SALDO_ACTUAL + getSaldoPeso());
                break;
            case "h":
                try {
                    if (!unidades.isEmpty()) {
                        System.out.println("Eliminar unidad del ejército: ");
                        informacionEjercito();
                        resultExecute = 1;

                        System.out.println("Nombre de la unidad a eliminar: ");
//                            String nombreUnidad = scanner.nextLine();

//                            eliminarUnidad(nombreUnidad);
                    } else {
                        resultExecute = 0;
                        throw new EjercitoVacioException(Message.EJERCITO_VACIO);
                    }
                } catch (EjercitoVacioException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                break;
            case "i":
                try {
                    if (saldoPeso >= MIN_UNIDADES && hayGeneral) {
                        System.out.println(System.lineSeparator() + "Su Ejército está formado por: "
                                + System.lineSeparator());

                        informacionEjercito();

                        actualizarEjercito();
                        resultExecute = 1;

                        break;
                    }

                    if (nombre == null || nombre.isEmpty() || nombre.isBlank()) {
                        resultExecute = 0;
                        throw new EjercitoNombreException(Message.EJERCITO_SIN_NOMBRE);
                    }

                    if (saldoPeso < MIN_UNIDADES) {
                        resultExecute = 0;
                        throw new UnidadMinimaException(Message.UNIDADES_MINIMAS);
                    } else {
                        throw new GeneralMinimoException(Message.GENERAL_MINIMO);
                    }
                } catch (EjercitoNombreException | UnidadMinimaException | GeneralMinimoException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }


                break;
            default:
                System.out.println(Message.OPCION_INAVLIDA);
                break;
        }
    }

    private void imprimirInfo(Componentes componente) {
        System.out.println(Message.ADICIONAR_COMPONENTE + System.lineSeparator() + componente);
        System.out.println(System.lineSeparator() + Message.SALDO_ACTUAL + getSaldoPeso());
    }

    public void recibirDano(int dano) {
        Iterator<Componentes> iterador = unidades.iterator();

        while (dano > 0 && iterador.hasNext()) {
            Componentes componente = iterador.next();
            componente.recibirDano(dano);

            if (componente.getSalud() < 0) {

                dano = Math.abs(componente.getSalud());
                iterador.remove();
                unidades.remove(componente);

                System.out.println("El componente " + componente.getNombre() + " ha sido eliminado del ejército " +
                        nombre + " por falta de salud");
            }

            restablecerAtributos();
            actualizarEjercito();
        }
    }

    public void actualizarEjercito() {
        restablecerAtributos();

        for (Componentes componente : unidades) {
            ataque += componente.getAtaque();
            defensa += componente.getDefensa();
            salud += componente.getSalud();
        }
    }

    private void restablecerAtributos() {
        ataque = 0;
        defensa = 0;
        salud = 0;
    }

    public void adicionarUnidad(Componentes componentes) {
        if (saldoPeso == MAX_PESO - 1 && !hayGeneral) {
            JOptionPane.showMessageDialog(null, "Falta un general. Se agrego un general " +
                    "por defecto al ejercito en la unidad reservada.");
            unidades.add(new General());
            saldoPeso += General.PESO_GENERAL;
            hayGeneral = true;
        } else {
            if (componentes instanceof Infanteria || componentes instanceof Caballeria) {
                unidades.add(componentes);
                saldoPeso += componentes.getPeso();
            } else if (componentes instanceof General) {
                unidades.add(componentes);
                saldoPeso += componentes.getPeso();
                hayGeneral = true;
            } else if (componentes instanceof Elefante || componentes instanceof Tigre) {
                unidades.add(componentes);
                saldoPeso += componentes.getPeso();
                contadorAnimales++;
            }
        }
    }

    public void eliminarUnidad(String nombreUnidad) {
        try {
            for (Componentes unidad : unidades) {
                if (unidad.getNombre().equalsIgnoreCase(nombreUnidad)) {
                    if (unidad instanceof General) {
                        hayGeneral = false;
                    } else if (unidad instanceof Elefante || unidad instanceof Tigre) {
                        contadorAnimales--;
                    }

                    unidades.remove(unidad);
                    saldoPeso -= unidad.getPeso();

                    System.out.println(unidad.getNombre() + ": " + Message.UNIDAD_ELIM_SATIS);

                    break;
                }
            }

            throw new UnidadInexistenteException(Message.UNIDAD_INEXISTENTE);

        } catch (UnidadInexistenteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void informacionEjercito() {
        for (Componentes unidad : unidades) {
            System.out.println(unidad);
        }
    }

    public void asignarNombre(String nombre) {
        try {
            if (!nombres.contains(nombre)) {
                resultExecute = 1;

                nombres.add(nombre);
                this.nombre = nombre;
            } else {
                resultExecute = 0;

                throw new NombreExistenteException(Message.NOMBRE_EXISTENTE);
            }
        } catch (NombreExistenteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public ArrayList<Componentes> getUnidades() {
        return unidades;
    }

    public int getResultExecute() {
        return resultExecute;
    }

    public void setResultExecute(int resultExecute) {
        this.resultExecute = resultExecute;
    }

    @Override
    public String toString() {
        return "Ejercito{" +
                "unidades=" + unidades +
                ", contadorAnimales=" + contadorAnimales +
                ", hayGeneral=" + hayGeneral +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", salud=" + salud +
                ", saldoPeso=" + saldoPeso +
                ", nombre='" + nombre + '\'' +
                ", resultExecute=" + resultExecute +
                '}';
    }
}