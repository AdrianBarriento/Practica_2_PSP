package sumas;

import gestionbbdd.GestionBd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SumaSecuencial {

    public void sumaSecuencial(){
        long tiempoInicio= System.currentTimeMillis();
        ResultSet datos ;
        ArrayList<Integer> ingresos = new ArrayList();

        try{
            datos = new GestionBd().getQuery(new GestionBd().getConexion());
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
        System.out.println("Ingresos totales-(SECUENCIAL): "+ingresosTotal+"\nSacado en "+(tiempoFinal-tiempoInicio)+" milisegundos");
    }
}
