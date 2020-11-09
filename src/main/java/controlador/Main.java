package controlador;

import sumas.SumaConcurrente;
import sumas.SumaSecuencial;

public class Main {
    public static void main(String[] args) {
        new SumaSecuencial().sumaSecuencial();
        new SumaConcurrente().sumaConcurrente();
    }
}
