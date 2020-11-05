package sumas;

import gestionbbdd.GestionBd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SumaConcurrente {
    private GestionBd bbdd;
    public int ingresosTotal=0;
    public synchronized int sumaConcurrente(int inicio, int fin){
        ResultSet datos = null;
        ArrayList<Integer> ingresos = new ArrayList();
        try{
            datos = bbdd.getQuery(bbdd.getConexion(), inicio, fin);
            while(datos.next()){
                int ingreso= datos.getInt("INGRESOS");
                ingresos.add(ingreso);
            }
        } catch (SQLException throwables) {
            System.out.println("No es posible acceder a los datos");
        }
        for (int recorrerLoop:ingresos) {
            ingresosTotal+=recorrerLoop;
        }
        return ingresosTotal;
    }



}
