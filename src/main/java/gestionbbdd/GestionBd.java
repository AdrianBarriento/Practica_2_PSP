package gestionbbdd;

import java.sql.*;

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
        PreparedStatement query =null;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("SELECT INGRESOS FROM empleados");
            datos = query.executeQuery();
        } catch (SQLException e) {
            System.out.println("No es posible acceder a los datos");
        }
        return datos;
    }
    public ResultSet getQuery(Connection conexion, int inicio, int fin){
        PreparedStatement query =null;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("SELECT INGRESOS FROM empleados WHERE ID>"+inicio+"AND ID<"+fin);
            datos = query.executeQuery();
        } catch (SQLException e) {
            System.out.println("No es posible acceder a los datos");
        }
        return datos;
    }




}
