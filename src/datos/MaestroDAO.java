/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Maestros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeff
 */
public class MaestroDAO {
    private static final String SQL_SELECT = "SELECT codigo_maestro, nombre_maestro, direccion_maestro, telefono_maetro, email_maestro, estatus_maestro FROM maestros";
    private static final String SQL_INSERT = "insert into maestros values(?,?,?,?,?,?)";
    private static final String SQL_DELETE = "delete from maestros where codigo_maestro = ?";  
    private static final String SQL_UPDATE = "UPDATE maestros SET nombre_maestro=?, direccion_maestro=?, telefono_maetro=?, email_maestro=?, estatus_maestro=? WHERE codigo_maestro=?";
    private static final String SQL_QUERY = "SELECT codigo_maestro, nombre_maestro, direccion_maestro, telefono_maetro, email_maestro, estatus_maestro  FROM maestros WHERE codigo_maestro = ?";
   
    
    public List<Maestros> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Maestros maestros = null;
        List<Maestros> maestro = new ArrayList<Maestros>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                String codigo_maestro = rs.getString("codigo_maestro");
                String nombre_maestro = rs.getString("nombre_maestro");
                String direccion_maestro = rs.getString("direccion_maestro");
                String telefono_maetro = rs.getString("telefono_maetro");
                String email_maestro = rs.getString("email_maestro");
                String estatus_maestro = rs.getString("estatus_maestro");
                
                maestros = new Maestros();
                maestros.setCodigo(codigo_maestro);
                maestros.setNombre(nombre_maestro);
                maestros.setEstatus(direccion_maestro);
                maestros.setCodigo(telefono_maetro);
                maestros.setNombre(email_maestro);
                maestros.setEstatus(estatus_maestro);
                
                maestro.add(maestros);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return maestro;
    }
    public int insert(Maestros maestro){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, maestro.getCodigo());
            stmt.setString(2, maestro.getNombre());
            stmt.setString(3, maestro.getDireccion());
            stmt.setString(4, maestro.getTelefono());
            stmt.setString(5, maestro.getEmail());
            stmt.setString(6, maestro.getEstatus());
//            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int delete(Maestros maestro){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, maestro.getCodigo());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int update(Maestros maestro){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, maestro.getNombre());
            stmt.setString(2, maestro.getDireccion());
            stmt.setString(3, maestro.getTelefono());
            stmt.setString(4, maestro.getEmail());
            stmt.setString(5, maestro.getEstatus());
            stmt.setString(6, maestro.getCodigo());
            rows = stmt.executeUpdate();
            System.out.println( stmt);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    public Maestros query(Maestros maestro) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, maestro.getCodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String codigo_maestro = rs.getString("codigo_maestro");
                String nombre_maestro = rs.getString("nombre_maestro");
                String direccion_maestro = rs.getString("direccion_maestro");
                String telefono_maetro = rs.getString("telefono_maetro");
                String email_maestro = rs.getString("email_maestro");
                String estatus_maestro = rs.getString("estatus_maestro");
                
                maestro = new Maestros();
                maestro.setCodigo(codigo_maestro);
                maestro.setNombre(nombre_maestro);
                maestro.setDireccion(direccion_maestro);
                maestro.setTelefono(telefono_maetro);
                maestro.setEmail(email_maestro);
                maestro.setEstatus(estatus_maestro);
                rows++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return maestro;
    }
}
