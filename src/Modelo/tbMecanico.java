package Modelo;

import java.sql.*;
import Vista.frmMecanico;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class tbMecanico {
    
    private String UUID_Mecanico; 
    private String Nombre_Mecanico; 
    private int Edad_Mecanico;
    private Double Peso_Mecanico; 
    private String Correo_Mecanico; 

    public int getEdad_Mecanico() {
        return Edad_Mecanico;
    }

    public void setEdad_Mecanico(int Edad_Mecanico) {
        this.Edad_Mecanico = Edad_Mecanico;
    }

    public Double getPeso_Mecanico() {
        return Peso_Mecanico;
    }

    public void setPeso_Mecanico(Double Peso_Mecanico) {
        this.Peso_Mecanico = Peso_Mecanico;
    }

    public String getUUID_Mecanico() {
        return UUID_Mecanico;
    }

    public void setUUID_Mecanico(String UUID_Mecanico) {
        this.UUID_Mecanico = UUID_Mecanico;
    }

    public String getNombre_Mecanico() {
        return Nombre_Mecanico;
    }

    public void setNombre_Mecanico(String Nombre_Mecanico) {
        this.Nombre_Mecanico = Nombre_Mecanico;
    }

    public String getCorreo_Mecanico() {
        return Correo_Mecanico;
    }

    public void setCorreo_Mecanico(String Correo_Mecanico) {
        this.Correo_Mecanico = Correo_Mecanico;
    }
    
    
    public void MostrarMecanicos(JTable tabla) {

        Connection conexion = ClaseConexion.getConexion();

        DefaultTableModel ModeloMec = new DefaultTableModel();
        
        ModeloMec.setColumnIdentifiers(new Object[]{"UUID_Mecanico", "Nombre_Mecanico", "Edad_Mecanico", "Peso_Mecanico", "Correo_Mecanico"});
        try {

            Statement statement = conexion.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM tbMecanico");

            while (rs.next()) {

                ModeloMec.addRow(new Object[]{rs.getString("UUID_Mecanico"), 
                    rs.getString("Nombre_Mecanico"), 
                    rs.getInt("Edad_Mecanico"),
                    rs.getDouble("Peso_Mecanico"),
                    rs.getString("Correo_Mecanico")});
            }

            tabla.setModel(ModeloMec);
            
        } catch (Exception e) {
            System.out.println("Este es el error en el metodo de MostrarMecanicos: " + e);
        }
    }
    

    
    public void cargarDatosTabla(frmMecanico Vista) {

    int filaSeleccionada = Vista.jtbMecanicos.getSelectedRow();

    if (filaSeleccionada != -1) {
        String UUID_Mecanico = Vista.jtbMecanicos.getValueAt(filaSeleccionada, 0).toString();
        String Nombre_Mecanico = Vista.jtbMecanicos.getValueAt(filaSeleccionada, 1).toString();
        String Edad_Mecanico = Vista.jtbMecanicos.getValueAt(filaSeleccionada, 2).toString();
        String Peso_Mecanico = Vista.jtbMecanicos.getValueAt(filaSeleccionada, 3).toString();
        String Correo_Mecanico = Vista.jtbMecanicos.getValueAt(filaSeleccionada, 4).toString();

        Vista.txtNombre.setText(Nombre_Mecanico);
        Vista.txtEdad.setText(Edad_Mecanico);
        Vista.txtPeso.setText(Peso_Mecanico);  
        Vista.txtCorreo.setText(Correo_Mecanico);
    }
}
    
    
    // Método Guardar Usuario
    
    public void GuardarMecanico(){       

        Connection conexion = ClaseConexion.getConexion();
        try {           

            PreparedStatement newUs = conexion.prepareStatement ("INSERT INTO tbMecanico (UUID_Mecanico, Nombre_Mecanico, Edad_Mecanico, Peso_Mecanico, Correo_Mecanico) Values (?, ?, ?, ?, ?)");          

            newUs.setString (1, UUID.randomUUID().toString());
            newUs.setString (2, getNombre_Mecanico());
            newUs.setInt (3, getEdad_Mecanico());
            newUs.setDouble(4, getPeso_Mecanico());
            newUs.setString (5, getCorreo_Mecanico());
            
            
            newUs.executeUpdate();
        } 
        
        catch (SQLException ex) {
            System.out.println("este es el error en el metodo de GuardarMecanico " + ex) ;
        }
                
    }
    
    // Método Actualizar Usuario
    
    public void ActualizarMecanico(JTable tabla){

        Connection conexion = ClaseConexion.getConexion();

        int filaseleccionada = tabla.getSelectedRow();
        if(filaseleccionada != -1){

            String UUIDMec = tabla.getValueAt(filaseleccionada, 0).toString();
            
            try{

                PreparedStatement ActualizarMecanico = conexion.prepareStatement("UPDATE tbMecanico set Nombre_Mecanico =?, Edad_Mecanico =?, Peso_Mecanico =?, Correo_Mecanico =? WHERE UUID_Mecanico =?");
                ActualizarMecanico.setString(1, getNombre_Mecanico());
                ActualizarMecanico.setInt (2, getEdad_Mecanico());
                ActualizarMecanico.setDouble(3, getPeso_Mecanico());
                ActualizarMecanico.setString(4, getCorreo_Mecanico());
                ActualizarMecanico.setString(5, UUIDMec);
                ActualizarMecanico.executeUpdate();
                
            }
            catch(Exception e){
                System.out.println("este es el error en el metodo de ActualizarMecanico: " + e);
            }
        }
        else {
            System.out.println("no funciona actualizar");
        }

    }
    
    
    // Método Eliminar Usuario
    
    public void EliminarMecanico(JTable tabla){
        
        Connection conexion = ClaseConexion.getConexion();
        
        int filaSeleccionada = tabla.getSelectedRow();
        
        String UUIDMec = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        try{
            PreparedStatement EliminarMecanico = conexion.prepareStatement("DELETE FROM tbMecanico WHERE UUID_Mecanico =?");
            EliminarMecanico.setString(1, UUIDMec);
            EliminarMecanico.executeUpdate();
        }
        catch(Exception e){
            System.out.println("este es el error metodo de EliminarMecanico: " + e);
        }
    }  
}
