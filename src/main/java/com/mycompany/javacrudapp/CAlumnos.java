/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudapp;

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mathe
 */
public class CAlumnos {
    int  codigo;
    String nombreAlumnos;
    String apellidosAlumnos;
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }
    
    public void insertarAlumno(JTextField paramNombres, JTextField paramApellidos){
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "insert into Alumnos (nombres, apellidos) values (?, ?);";
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidosAlumnos());
            cs.execute();
            JOptionPane.showMessageDialog(null, "se inserto");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "no se conecto error:" + e.toString());
        }
}
    public void MostrarAlumnos (JTable paramTablaTotalAlumnos){
        CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql = "SELECT * FROM bdescuela.alumnos;";
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        
        paramTablaTotalAlumnos.setModel(modelo);
        
        String[] datos = new String[3];
        Statement st;
        try {
            st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                
                modelo.addRow(datos);
            }
            paramTablaTotalAlumnos.setModel(modelo);
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "no se pudo mostrar la base de datos: " + e.toString());
        }
    }
    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombre, JTextField paramApellidos){
        try{
            int fila = paramTablaAlumnos.getSelectedRow();
            if (fila >= 0 ) {
                paramId.setText(paramTablaAlumnos.getValueAt(fila, 0).toString());
                paramNombre.setText(paramTablaAlumnos.getValueAt(fila, 1).toString());
                paramApellidos.setText(paramTablaAlumnos.getValueAt(fila, 2).toString());
            }
            else{
                JOptionPane.showMessageDialog(null, "fila no seleccionada");
            }
        }catch (HeadlessException e){
            JOptionPane.showMessageDialog(null, "no seleccionado" + e.toString());
        }
        
    }
    public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombre, JTextField paramApellidos) {
        
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         setNombreAlumnos(paramNombre.getText());
         setApellidosAlumnos(paramApellidos.getText());
         
         String consulta = "UPDATE Alumnos SET alumnos.nombres = ?, alumnos.apellidos = ? WHERE Alumnos.id = ?;";
         CConexion objetoConexion = new CConexion();
         try {
         CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
         cs.setString(1, getNombreAlumnos());
         cs.setString(2, getApellidosAlumnos());
         cs.setInt(3, getCodigo());
         cs.execute();
         JOptionPane.showMessageDialog(null, "modificacion exitosa");
         }catch(HeadlessException | SQLException e){
             JOptionPane.showMessageDialog(null, "no se modifico " + e.toString());
         }
    }
    public void EliminarAlumnos(JTextField paramCodigo) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM Alumnos WHERE Alumnos.id = ?;";
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            JOptionPane.showMessageDialog(null, "se elimino");
            cs.execute();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "no se elimino " + e.toString());
        }
        
    }
    
}