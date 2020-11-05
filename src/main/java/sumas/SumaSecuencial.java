package sumas;

import gestionbbdd.GestionBd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SumaSecuencial {
    private GestionBd bbdd;
    public void sumaSecuencial(){
        long tiempoInicio= System.currentTimeMillis();
        ResultSet datos = null;
        ArrayList<Integer> ingresos = new ArrayList();
        try{
            datos = bbdd.getQuery(bbdd.getConexion());
            while(datos.next()){
                int ingreso= datos.getInt("INGRESOS");
                ingresos.add(ingreso);
            }
        } catch (SQLException throwables) {
            System.out.println("No es posible acceder a los datos");
        }
        int ingresosTotal=0;
        for (int recorrerLoop:ingresos) {
            ingresosTotal+=recorrerLoop;
        }
        long tiempoFinal = System.currentTimeMillis();

        System.out.println("La suma total de ingresos es de: "+ingresosTotal+", y se ha realizado en "+(tiempoFinal-tiempoInicio)+" milisegundos");
    }
}
