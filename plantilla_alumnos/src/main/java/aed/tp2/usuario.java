package aed.tp2;

import java.util.ArrayList;

public class usuario implements Comparable<usuario>{
    private int id;
    private int millasTotales;
    private int millasDisponibles;
    private int millasGastadas;
    private ArrayList<transferencia> transferencias;

    public usuario(int id,int millasTotales, int millasDisponibles, int millasGastadas, ArrayList<transferencia> transferecias){

        this.id = id;
        this.millasTotales = millasTotales;
        this.millasDisponibles = millasDisponibles;
        this.millasGastadas = millasGastadas;
        this.transferencias = new ArrayList<>(transferecias);
    }


    public int id(){
        return this.id;
    }

    public int millasTotales(){
        return this.millasTotales;
    }

    public int millasDisponibles(){
        return this.millasDisponibles;
    }

    public int millasGastadas(){
        return this.millasGastadas;
    }

    public ArrayList<transferencia> transferencias(){
        return this.transferencias;
    }

    public String categoria(){
        String res = "";

        if (this.millasTotales < 10000 ){
            res = "Bronce";
        }
        else if (this.millasTotales >= 10000 && this.millasTotales <= 49999){
            res = "Plata";
        }
        else if (this.millasTotales >= 50000 && this.millasTotales <= 999999){
            res = "Oro";
        }
        else {
            res = "Platino";
        }

        return res;
    }

     @Override
    public int compareTo(usuario otro) {
        if (this.millasTotales != otro.millasTotales) {
            return Integer.compare(this.millasTotales, otro.millasTotales);
        } else {
            return Integer.compare(this.id, otro.id); 
        }
        }}
