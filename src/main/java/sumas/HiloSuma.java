package sumas;

import gestionbbdd.GestionBd;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@Data @AllArgsConstructor
public class HiloSuma extends Thread {

    private int primerRegistro;
    private int ultimoRegistro;
    private int ingresosTotal;


    private synchronized int sumaConcurrente(int inicio, int fin){
        GestionBd bbdd = new GestionBd();
        ResultSet datos;
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

    @Override
    public void run() {
        ingresosTotal+=sumaConcurrente(primerRegistro, ultimoRegistro);
    }
}
