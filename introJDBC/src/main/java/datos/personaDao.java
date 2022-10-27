/*
 *Conjunto de operaciones que yo voy a poder realizar sobre una persona
 */
package datos;

import dominio.persona;
import static datos.conexion.close;
import static datos.conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno Mañana
 */
public class personaDao {
    private static final String SQL_SELECT= "SELECT * FROM persona";
    private static final String SQL_INSERT= "INSERT INTO persona(nombre,apellidos,email,telefono) VALUES(?,?,?,?)";
     private static final String SQL_UPDATE= "UPDATE persona SET nombre =?, apellidos =?, email =?, telefono =? where idPersona =?";
    
    // Metodo que nos lista todas las personas de nuestro sistema
    public List<persona> seleccionar() throws SQLException{
        // inicializo mis variables
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        persona persona= null;
        List<persona> person = new ArrayList<>();
        conn= getConnection();
        stmt = conn.prepareStatement(SQL_SELECT);
        rs= stmt.executeQuery();
        
        while(rs.next()){
            int idpersona = rs.getInt("idpersona");
            String nombre= rs.getString("nombre");
            String apellidos= rs.getString("apellidos");
            String email= rs.getString("email");
            String telefono= rs.getString("telefono");
            
            // Instancio un nuevo ojeto
            person.add(new persona(idpersona,nombre,apellidos,email,telefono));
        }
        close(rs);
        close(stmt);
        close(conn);
        
        return person;
    }
    // Metodo que inserta una persona en mi sistema
    public int insertar(persona persona){
        Connection conn= null;
        PreparedStatement stmt= null;
        int registros = 0;
        
        try {
            // 1.Establecemos la conexion
            conn = getConnection();
            
            // 2.Preparo mi instruccion para ejecutarla contra la base de datos
            stmt = conn.prepareStatement(SQL_INSERT);
            
            //Asignar los valores a los ? de la consulta
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            // 3.Ejecuto la query
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
          ex.printStackTrace(System.out);
        }finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                 ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    
    public int actualizar(persona persona) throws SQLException{
        
        //Inicializo mis variables
        Connection conn= null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getEmail());
            stmt.setString(1, persona.getTelefono());
            stmt.setInt(1, persona.getIdPersona());
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            }
           
                 
        }
        return registros;
        
    }
    
    
}
