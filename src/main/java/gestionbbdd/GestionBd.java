package gestionbbdd;

import java.sql.*;

//clase para gestionar los accesos a la base de datos
public class GestionBd {
    public Connection getConexion(){
        Connection conexion=null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1",
                    "DAM2020_PSP",
                    "DAM2020_PSP");

        } catch (SQLException throwables) {
            System.out.println("No es posible conectar a la base de datos. Compruebe la conexion o reinicie la aplicación");
        }
        return conexion;
    }

    public ResultSet getQuery(Connection conexion){
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("SELECT INGRESOS FROM empleados");
            datos = query.executeQuery();
        } catch (SQLException e) {
            System.out.println("No es posible acceder a los datos");
        }
        return datos;
    }

    //metodo para hacer la query pero dentro de un rango de IDs, para usarlo en hilos
    public ResultSet getQuery(Connection conexion, int inicio, int fin){
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("SELECT INGRESOS FROM empleados WHERE ID BETWEEN "+inicio+" AND "+fin);
            datos = query.executeQuery();
        } catch (SQLException e) {
            System.out.println("No es posible acceder a los datos");
        }
        return datos;
    }




}
