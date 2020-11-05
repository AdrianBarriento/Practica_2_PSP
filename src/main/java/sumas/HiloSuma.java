package sumas;

public class HiloSuma extends Thread {
    private int primerRegistro;
    private int ultimoRegistro;
    private SumaConcurrente sumar;
    private int ingresosTotal;

    public HiloSuma(int primerRegistro,int ultimoRegistro){
        this.primerRegistro=primerRegistro;
        this.ultimoRegistro=ultimoRegistro;
    }

    @Override
    public void run() {
        ingresosTotal+=sumar.sumaConcurrente(primerRegistro, ultimoRegistro);
    }
}
