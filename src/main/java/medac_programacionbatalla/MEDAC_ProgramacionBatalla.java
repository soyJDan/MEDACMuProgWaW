/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medac_programacionbatalla;

import com.db4o.ObjectContainer;
import dao.mysql.GeneralDao;
import dao.mysql.SchemaDao;
import dao.mysql.TopScoreDao;
import utilidades.Db4oConnection;
import vistas.PrincipalVista;

/**
 * @author danie
 */
public class MEDAC_ProgramacionBatalla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ObjectContainer objectContainer = Db4oConnection.getConnection();
        Db4oConnection.closeConnection(objectContainer);

        PrincipalVista principalVista = new PrincipalVista();
        principalVista.setSize(400, 300);


    }
}
    

 
