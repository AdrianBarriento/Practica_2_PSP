package sumas;

import gestionbbdd.GestionBd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SumaConcurrente {
    public void sumaConcurrente(){
        int totalRegistros=getRegistros(), porcionXHilo = totalRegistros/5;
        //sacar los registros que hay que sumar al ultimo hilo si no es divisible
        int diferenciaPorcion = totalRegistros-(porcionXHilo*5);







    }


    private int getRegistros(){
        int numRegistros =0;
        GestionBd bbdd = new GestionBd();
        Connection conexion = bbdd.getConexion();
        ResultSet sacarRegistros = bbdd.getQuery(conexion);
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
