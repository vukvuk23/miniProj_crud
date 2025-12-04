/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrator
 */
public class Konekcija {
       
    private Connection connection;
    
    private static Konekcija instance;
    public static Konekcija getInstance(){
        if(instance == null){
            instance = new Konekcija();
        }
        return instance;
    }
     
    private Konekcija(){
        try{
            String url = "jdbc:mysql://localhost:3306/ps_sql_1";
            connection = DriverManager.getConnection(url, "root", "root");
            connection.setAutoCommit(false);
            System.out.println("Konekcija sa bazom podataka uspesno uspostavljena!");
        }catch(SQLException ex){
            System.out.println("Konekcija sa bazom podataka nije uspesno uspostavljena!");
            ex.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
}
