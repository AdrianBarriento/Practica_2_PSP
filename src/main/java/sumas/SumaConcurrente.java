package sumas;

import gestionbbdd.GestionBd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SumaConcurrente {
    public void sumaConcurrente(){
        long tiempoInicio = System.currentTimeMillis();
        int totalRegistros=getRegistros(), porcionXHilo = totalRegistros/5;
        //sacar los registros que hay que sumar al ultimo hilo si no es divisible
        int diferenciaPorcion = totalRegistros-(porcionXHilo*5);
        HiloSuma hilo1 = new HiloSuma(0, porcionXHilo);
        hilo1.start();
        HiloSuma hilo2 = new HiloSuma(porcionXHilo+1, porcionXHilo*2);
        hilo2.start();
        HiloSuma hilo3 = new HiloSuma((porcionXHilo*2)+1, porcionXHilo*3);
        hilo3.start();
        HiloSuma hilo4 = new HiloSuma((porcionXHilo*3)+1, porcionXHilo*4);
        hilo4.start();
        HiloSuma hilo5 = new HiloSuma((porcionXHilo*4)+1, (porcionXHilo*5)+diferenciaPorcion);
        hilo5.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //sumamos todos los ingresos que ha recogido cada hilo y obtenemos los ingresos totales
        int ingresosTotales = hilo1.getIngresosParcial()+hilo2.getIngresosParcial()+hilo3.getIngresosParcial()+hilo4.getIngresosParcial()+hilo5.getIngresosParcial();
        long tiempoFin = System.currentTimeMillis();
        System.out.println("Ingresos totales-(CONCURRENTE): "+ingresosTotales+"\nSacado en "+(tiempoFin-tiempoInicio)+" milisegundos");






    }


    private int getRegistros(){
        int numRegistros =0;
        Connection conexion = new GestionBd().getConexion();
        ResultSet sacarRegistros = new GestionBd().getQuery(conexion);
        //sacar el numero total de columnas que hay en la base de datos
        try{
            while(sacarRegistros.next()){
                numRegistros+=1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return numRegistros;
    }



}
