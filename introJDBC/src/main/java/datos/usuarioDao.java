/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import static datos.conexion.close;
import static datos.conexion.getConnection;
import dominio.persona;
import dominio.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public class usuarioDao {
     private static final String SQL_SELECT ="SELECT * FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (nombre, password)"
            + " VALUES (?,?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET "
            + "nombre = ?, "
            + "password = ?,"
            + "WHERE personaId = ?";
    
//    Método que nos lista todas las personas de nuestro sistema
    public List<usuario> seleccionar() throws SQLException {
        //INICIALIZAR VARIABLES
        
        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet rs = null;
        persona persona = null;
        List<usuario> usuarios = new ArrayList<>();
        
        conn = getConnection();
        stmt = conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            int usuarioId = rs.getInt("id");
            String nombre = rs.getString("personaNmae");
            String password = rs.getString("personaApellidos");
            
            //Instancio un nuevo objeto
            usuarios.add(new usuario(usuarioId, nombre,password));
            
        }
        
        
        close(rs);
        close(stmt);
        close(conn);
        
        return usuarios;
    }
    
    //MÉTODO QUE INSERTA UNA PERSONA EN EL SISTEMA
    public int insertar (usuario usuario){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_INSERT);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            
            
            //4. EJECUTO LA QUERY
            
            registro = stmt.executeUpdate();
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            
            }
            
        }
        return registro;
    }
    
    public int actualizar (usuario usuario){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(5, usuario.getId());
            
            
            //4. EJECUTO LA QUERY
            
            registro = stmt.executeUpdate();
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            
            }
            
        }
        return registro;
    }
}
