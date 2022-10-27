/*
 * La clase conexion contiene todos los metodos para trabajar con una conexion
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Alumno Mañana
 */
public class conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false"+
            "&useTimezone=true&serverTimezone=UTC"
            +"&allowPublicKeyretrieval=true";;
    private static final String JDBC_USER ="root";
    private static final String JDBC_PASSWORD ="1234";
    
    //Metodo para esablecer la conexion
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    public static void close(ResultSet rs) throws SQLException{
        rs.close();       
    }
    public static void close(Statement stm) throws SQLException{
        stm.close();
    }
    
    public static void close(PreparedStatement stm) throws SQLException{
        stm.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
