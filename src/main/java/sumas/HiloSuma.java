package sumas;

import gestionbbdd.GestionBd;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@Data
public class HiloSuma extends Thread {

    private int primerRegistro;
    private int ultimoRegistro;
    //cada hilo guardará una parte de los ingresos totales y se sumarán todos luego para sacar los ingresos totales
    private int ingresosParcial;

    //constructor con el inicio y el final del rango de IDs que tiene que abarcar el hilo
    public HiloSuma(int primerRegistro, int ultimoRegistro){
        this.primerRegistro=primerRegistro;
        this.ultimoRegistro=ultimoRegistro;
    }

    private int sumaConcurrente(){
        ArrayList<Integer> ingresos = new ArrayList();
        try{
            ResultSet datos = new GestionBd().getQuery(new GestionBd().getConexion(), primerRegistro, ultimoRegistro);
            while(datos.next()){
                int ingreso= datos.getInt("INGRESOS");
                ingresos.add(ingreso);
            }
        } catch (SQLException throwables) {
            System.out.println("No es posible acceder a los datos");
        }
        for (int recorrerLoop:ingresos) {
            ingresosParcial+=recorrerLoop;
        }
        return ingresosParcial;
    }

    @Override
    public void run() {ingresosParcial=sumaConcurrente();}
}
