/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudapp;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author mathe
 */
public class CConexion {
    Connection conectar = null;
    
    String usuario = "root";
    String contrasenia = "1002mateo";
    String bd = "bdescuela";
    String ip = "localhost";
    String port = "3307";
   String cadena = "jdbc:mysql://" + ip + ":" + port + "/" + bd;
   public Connection estableceConexion() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
                //JOptionPane.showMessageDialog(null,"conexion realizada con exito");
            } catch (HeadlessException | ClassNotFoundException | SQLException e){
                JOptionPane.showMessageDialog(null, "error al conectarse a la base de datos" + e.toString()); 
            }
            return conectar;
   }
}
