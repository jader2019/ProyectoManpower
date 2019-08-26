/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase: $(name)
 * 
 * @version: 0.1
 *  
 * @since: $(date)
 * 
 * Fecha de Modificación:
 * 
 * @author: Carlos Daniel Escudero Corpas
 * 
 * Copyrigth: CECAR
 */



package edu.cecar.controladores;

import edu.cecar.componentes.basesDeDatos.ConectarMySQL;
import edu.cecar.modelos.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Clase que controla las operaciones CRUD de los empleados
 */
public class ControladorEmpleado {

    private ConectarMySQL conectarMySQL;
    
    public ControladorEmpleado() {
        
        conectarMySQL = new ConectarMySQL("127.0.0.1", "manpower", "root", "");
        
    }
    
    
    public void guardar(Empleado empleado) throws SQLException{
        
        PreparedStatement preparedStatement = conectarMySQL.getConnection().prepareStatement("insert into empleados values(?, ?, ?, ?)");
        preparedStatement.setString(1, empleado.getCodigo());
        preparedStatement.setString(2, empleado.getNombres());
        preparedStatement.setString(3, empleado.getApellidos());
        preparedStatement.setString(4, empleado.getFechaNaciemiento());
        preparedStatement.execute();
    }
    
    public Empleado consultar(String codigo) throws SQLException{
        
        Empleado empleado = null;
        
        PreparedStatement preparedStatement = conectarMySQL.getConnection().prepareStatement(""
                + "select nombres,apellidos,fechanacimiento from empleados where codigo = ?");
        
        preparedStatement.setString(1, codigo);
        ResultSet resultado = preparedStatement.executeQuery();
        
        if(resultado.next()){
            empleado = new Empleado(codigo, resultado.getString(1), resultado.getString(2), resultado.getString(3));
        }
        
        return empleado;
    }
    
    public void actualizar(Empleado empleado) throws SQLException{
        
        PreparedStatement preparedStatement = conectarMySQL.getConnection().prepareStatement("update empleados set nombres = ?, apellidos = ?, fechaNacimiento = ? where cedula = ?");
        preparedStatement.setString(1, empleado.getNombres());
        preparedStatement.setString(2, empleado.getApellidos());
        preparedStatement.setString(3, empleado.getFechaNaciemiento());
        preparedStatement.setString(4, empleado.getCodigo());
        preparedStatement.execute();
    }
    
    public void eliminar(String codigo) throws SQLException{
        PreparedStatement preparedStatement = conectarMySQL.getConnection().prepareStatement("delete from empleados where cedula = ?");
        preparedStatement.setString(1, codigo);
        preparedStatement.execute();
    }
}
