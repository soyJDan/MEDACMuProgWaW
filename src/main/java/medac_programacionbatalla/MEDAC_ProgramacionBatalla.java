/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medac_programacionbatalla;

import dao.GeneralDao;
import dao.SchemaDao;
import dao.TopScoreDao;
import vistas.PrincipalVista;

/**
 * @author danie
 */
public class MEDAC_ProgramacionBatalla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SchemaDao.createSchema();
        GeneralDao.createTable("GENERAL");
        TopScoreDao.createTable("TOPSCORE");

        PrincipalVista principalVista = new PrincipalVista();
        principalVista.setSize(400, 300);


    }
}
    

 
