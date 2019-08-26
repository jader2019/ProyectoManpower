/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.test;

import edu.cecar.controladores.ControladorEmpleado;
import edu.cecar.modelos.Empleado;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carlos Daniel Escudero Corpas
 */
public class TestControladorEmpleado {
    
    @Test
    public void testConexionBaseDatos(){
    
        try{
            
            ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
            System.out.println("Conexión Exitosa");
            
        }catch(Exception ex){
            
            fail("Error de conexión");
            Logger.getLogger(TestControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
    }
    
    @Test
    public void testGuardar(){
        Empleado empleado = new Empleado("9253", "Carlos", "Escudero", "1999-03-21");
        
        try {
            
            ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
            controladorEmpleado.guardar(empleado);
            
            Empleado empleadoBaseDatos = controladorEmpleado.consultar(empleado.getCodigo());
            
            assertEquals("El codigo esperado no coincide con el Inserdo", empleado.getCodigo(), empleadoBaseDatos.getCodigo());
            assertEquals("Los nombres esperado no coincide con el Inserdo", empleado.getNombres(), empleadoBaseDatos.getNombres());
            assertEquals("Los apellidos esperado no coincide con el Inserdo", empleado.getApellidos(), empleadoBaseDatos.getApellidos());
            assertEquals("la Fecha esperado no coincide con la Inserdo", empleado.getFechaNaciemiento(), empleadoBaseDatos.getFechaNaciemiento());
        } catch (SQLException ex) {
            Logger.getLogger(TestControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testActualizar(){
        Empleado empleado = new Empleado("9253", "Daniel", "Corpas", "1999-03-21");
        
        try {
            
            ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
            controladorEmpleado.actualizar(empleado);
            
            Empleado empleadoBaseDatos = controladorEmpleado.consultar(empleado.getCodigo());
            
            assertEquals("El codigo esperado no coincide con el Inserdo", empleado.getCodigo(), empleadoBaseDatos.getCodigo());
            assertEquals("Los nombres esperado no coincide con el Inserdo", empleado.getNombres(), empleadoBaseDatos.getNombres());
            assertEquals("Los apellidos esperado no coincide con el Inserdo", empleado.getApellidos(), empleadoBaseDatos.getApellidos());
            assertEquals("la Fecha esperado no coincide con la Inserdo", empleado.getFechaNaciemiento(), empleadoBaseDatos.getFechaNaciemiento());
        } catch (SQLException ex) {
            Logger.getLogger(TestControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testEliminar(){
        Empleado empleado = new Empleado("9253", "Daniel", "Corpas", "1999-03-21");
        
        try {
            
            ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
            controladorEmpleado.eliminar(empleado.getCodigo());
            
            Empleado empleadoBaseDatos = controladorEmpleado.consultar(empleado.getCodigo());
            
            assertNull("El empleado no ha sido eliminado con exito", empleadoBaseDatos);
            
        } catch (SQLException ex) {
            Logger.getLogger(TestControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

    
}
