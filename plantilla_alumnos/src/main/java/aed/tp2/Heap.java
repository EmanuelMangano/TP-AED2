package aed.tp2;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class Heap<T extends Comparable<T>> {
    private T[] lista;
    private int cardinal;
    private int capacidad;
    private int nivel;

@SuppressWarnings("unchecked")
    public Heap() {
        this.lista = (T[]) new Comparable[1];
        this.cardinal=0;
        this.capacidad= 0;
        this.nivel= 0;
    }

    public int cardinal() {
        return this.cardinal;
    }
    public int capacidad() {
        return this.capacidad;
    }
    public int nivel() {
        return this.nivel;
    }
    public T maximo(){
        if (this.cardinal > 0){
            return this.lista[1];

        }
        return null;
    }
    public int  ordenar(int posicion){
        int indice = posicion;
        T actual = this.lista[indice];
        T padre = null;
        if (indice > 1){
            padre = this.lista[indice/2];

        }
        if (indice > 1 && actual.compareTo(padre)>0){
            this.lista[indice] = padre;
            this.lista[indice/2] = actual;
            indice = ordenar(indice/2);    
        }
        else if (indice <= this.cardinal/2){
            if (this.lista[indice*2]!=null && this.lista[indice*2+1]!=null){
                T izquierdo= this.lista[indice*2];
                T derecho= this.lista[indice*2+1];
                
                if (actual.compareTo(izquierdo)<0 && derecho.compareTo(izquierdo)<0){
                    this.lista[indice*2] = actual;
                    this.lista[indice] = izquierdo;
                    indice= ordenar(indice*2);

                }
                else if (actual.compareTo(derecho)<0 && izquierdo.compareTo(derecho)<0){
                    this.lista[indice*2+1] = actual;
                    this.lista[indice] = derecho;
                    indice= ordenar(indice*2+1);
                }   
                else if (actual.compareTo(izquierdo)<0 && derecho.compareTo(izquierdo)==0) {
                    this.lista[indice*2] = actual;
                    this.lista[indice] = izquierdo;
                    indice= ordenar(indice*2);
                }
                
            }
            else if (this.lista[indice*2]!=null && this.lista[indice*2+1]==null){
                T izquierdo= this.lista[indice*2];
                if (actual.compareTo(izquierdo)<0){
                    this.lista[indice*2] = actual;
                    this.lista[indice] = izquierdo;
                    indice= ordenar(indice*2);
                }
            }
        }

        
        return indice;
    }

    public int cambiar(int posicion, T valor){
        this.lista[posicion ] = valor;
        return ordenar(posicion);
    }



@SuppressWarnings("unchecked")
    public int insertar(T elem){
        if (this.capacidad == this.cardinal){
            int nuevaCapacidad = 0;
            for (int i = 0 ; i<= this.nivel ; i++){
                nuevaCapacidad = nuevaCapacidad + (int) Math.pow(2, i);
            }
            T[] nuevaLista = (T[]) new Comparable[nuevaCapacidad+1];
                     for (int i = 0 ; i<= this.cardinal ; i++){
            nuevaLista[i] = this.lista[i];
            }
        this.lista=nuevaLista;
        this.nivel++;
        this.capacidad = nuevaCapacidad;
        }
        this.cardinal=this.cardinal+1;
        this.lista[this.cardinal] = elem;
        return ordenar(this.cardinal);
    }


    public T eliminar(){
        if(this.cardinal >= 1){
        T res = this.lista[1];
        T nuevaRaiz = this.lista[this.cardinal];
        this.lista[this.cardinal] = null;
        this.cardinal--;
        this.lista[1]=nuevaRaiz;
        ordenar(1);


        return res;
    }
    return null;
    
}


}
