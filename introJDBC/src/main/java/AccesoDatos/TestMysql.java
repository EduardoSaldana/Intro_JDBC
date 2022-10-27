/*
* Programa de testeo conexion base de datos MySQL
 */
package AccesoDatos;

import datos.personaDao;
import dominio.persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno Mañana
 */
public class TestMysql {
         
    public static personaDao personaDao = new personaDao();
    
    public static List <persona> personas(){
        
        List <persona> pers = null;
        try{
            List <persona> persona = personaDao.seleccionar();
            pers = persona;
            //pers.forEach(persona->{
            //System.out.println("persona = "+persona);          });
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return pers;
        
    }
        
    
    
    
    public static void main(String[] args) {
        
        
        
        
        persona persona = new persona("Nicolás","Correa","nicolasN@Gmail.com","654654654");
        
//        personaDao.insertar(persona);

        persona persona2 = persAct(1);
        persona2.setTelefono("666666666");
        
        
        System.out.println(persona2.getTelefono()); 
//        System.out.println(persAct(1));
        //personaDao.actualizar(persona2);
        
        
       
    }
    
    public static persona persAct(int id){
        persona actualizable = null;
        List <persona> lista = personas();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPersona()== id){
                actualizable = lista.get(i);
            }
        }
            
        return actualizable;
    }
            
        }
        
        
        
        
        
        
        
        
        /*
        //Defino la url de conexion a nuestra base de datos y sus parametros
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false"+
        "&useTimezone=true&serverTimezone=UTC"
        +"&allowPublicKeyretrieval=true";
        try{
        //Ahora tratamos de establecer nuestra conexion con nuestra base de datos
        //Esta sentencia puede generar una excepción de SQL
        Connection conexion = DriverManager.getConnection(url, "root","1234");
        //Creo un statement para pder ejecutar mis consultas SQL
        Statement instruccion = conexion.createStatement();
        //Defino mi consulta SQL
        String sql="SELECT * FROM persona";
        //Ejecuto la instrucción
        ResultSet resultado = instruccion.executeQuery(sql);
        //Mientras haya algo en resultado muestro la información iterando
        while(resultado.next()){
        System.out.println("Id.Persona:" + resultado.getInt("idPersona"));
        System.out.println("Nombre:" + resultado.getNString("nombre"));
        System.out.println("Apellidos:" + resultado.getNString("apellidos"));
        System.out.println("Gmail:" + resultado.getNString("email"));
        System.out.println("Telefono:" + resultado.getNString("telefono"));
        }
        resultado.close();
        instruccion.close();
        conexion.close();
        }catch(SQLException ex){
        ex.printStackTrace(System.out);
        }
        }*/ 
    
    
    
    
    
    
    
    

   

