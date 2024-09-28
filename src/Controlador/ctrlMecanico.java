package Controlador;

import Modelo.tbMecanico;
import Vista.frmMecanico;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ctrlMecanico implements MouseListener{
    
    private frmMecanico vista;
    private tbMecanico modeloMec;
    
    public ctrlMecanico(frmMecanico Vista, tbMecanico ModeloMec){
        
        this.vista = Vista;
        this.modeloMec = ModeloMec;
    
        Vista.btnAgregar.addMouseListener(this);
        Vista.btnActualizar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        
        modeloMec.MostrarMecanicos(vista.jtbMecanicos);
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource()== vista.btnAgregar){
            
            if(vista.txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El nombre es obligatorio.");
            return;
            }
            
            modeloMec.setNombre_Mecanico(vista.txtNombre.getText());
            
            try {
                int edad = Integer.parseInt(vista.txtEdad.getText());
                if(edad < 18 || edad > 90) {
                    JOptionPane.showMessageDialog(vista, "La edad debe estar entre 18 y 90 años.");
                    return;
                }
                
            modeloMec.setEdad_Mecanico(edad);
            
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "La edad debe ser un número válido.");
                return;
            }
            
            try {
                
                double peso = Double.parseDouble(vista.txtPeso.getText());
                if(peso < 80 || peso > 250) {
                    JOptionPane.showMessageDialog(vista, "El peso debe estar entre 50lb y 250lb.");
                    return;
                }
                
            modeloMec.setPeso_Mecanico(peso);
            
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El peso debe ser un número válido.");
                return;
            }
            
            String correo = vista.txtCorreo.getText();
            
            if(!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(vista, "El formato del correo es inválido.");
                return;
            }
            
            modeloMec.setCorreo_Mecanico(correo);
            
            
            modeloMec.GuardarMecanico();
            
            JOptionPane.showMessageDialog(vista, "ERROR: MECANICO GUARDADO");
            
            modeloMec.MostrarMecanicos(vista.jtbMecanicos);
  
        }
        
        if(e.getSource()== vista.btnActualizar){
            
            if(vista.txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El nombre es obligatorio.");
                return;
            }
            
            modeloMec.setNombre_Mecanico(vista.txtNombre.getText());
            
            try {
                int edad = Integer.parseInt(vista.txtEdad.getText());
                if(edad < 18 || edad > 90) {
                    JOptionPane.showMessageDialog(vista, "La edad debe estar entre 18 y 90 años.");
                    return;
                }
                
                modeloMec.setEdad_Mecanico(edad);
            
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "La edad debe ser un número válido.");
                return;
            }

    try {
        double peso = Double.parseDouble(vista.txtPeso.getText());
        if(peso < 80 || peso > 250) {
            JOptionPane.showMessageDialog(vista, "El peso debe estar entre 80 y 250 kg.");
            return;
        }
        modeloMec.setPeso_Mecanico(peso);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(vista, "El peso debe ser un número válido.");
        return;
    }
    
    String correo = vista.txtCorreo.getText();
    
    if(!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        JOptionPane.showMessageDialog(vista, "El formato del correo es inválido.");
        return;
    }
    modeloMec.setCorreo_Mecanico(correo);
               
            modeloMec.ActualizarMecanico(vista.jtbMecanicos);
            
            JOptionPane.showMessageDialog(vista, "ERROR: MECANICO ACTUALIZADO");
            
            modeloMec.MostrarMecanicos(vista.jtbMecanicos);
                    
        }
        
        if(e.getSource()== vista.btnEliminar){
            
            int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Seguro de eliminar a este mecánico?", "Eliminar", JOptionPane.YES_NO_OPTION);
    
            if(confirmacion == JOptionPane.YES_OPTION) {
        
                modeloMec.EliminarMecanico(vista.jtbMecanicos);
        
                JOptionPane.showMessageDialog(vista, "ERROR: SE BORRÓ EL MECANICO.");
        
                modeloMec.MostrarMecanicos(vista.jtbMecanicos);
            } else {
                JOptionPane.showMessageDialog(vista, "Eliminación cancelada.");
            }
        }
        
        if(e.getSource()== vista.jtbMecanicos){
            modeloMec.cargarDatosTabla(vista);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
