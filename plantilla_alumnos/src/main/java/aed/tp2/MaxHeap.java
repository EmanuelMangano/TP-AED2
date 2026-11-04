package aed.tp2;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2

public class MaxHeap<T extends Comparable<T>> {
    private Nodo raiz;
    private int cardinal;
    public class Nodo {
         public T prioridad;
         public T data;          
         public Nodo padre;
         public Nodo izquierdo;
         public Nodo derecho;

         public Nodo(T data, T prioridad) {
            this.data = data;
            this.prioridad = prioridad;
            this.padre = null;
            this.izquierdo = null;
            this.derecho = null;
            
        }
    }
    public MaxHeap() {
        this.raiz = null;
        this.cardinal=0;
    }

    public int cardinal() {
        return this.cardinal;
    }
    public T maximo(){
        if (this.cardinal > 0){
            return this.raiz.data;

        }
        return null;
    }

    public void ordenar(Nodo nodo){
        while (((nodo.padre==null && ((nodo.izquierdo != null && (nodo.izquierdo.prioridad.compareTo(nodo.prioridad)>0))
            || (nodo.derecho != null && nodo.derecho.prioridad.compareTo(nodo.prioridad)>0)))
            || (nodo.padre !=null && nodo.padre.prioridad.compareTo(nodo.prioridad)<0)
            || (nodo.izquierdo != null && (nodo.izquierdo.prioridad.compareTo(nodo.prioridad)>0))
            || (nodo.derecho != null && nodo.derecho.prioridad.compareTo(nodo.prioridad)>0))
            
            

        ){
          
        if (nodo.padre==null){
            Nodo derecho = nodo.derecho;
            Nodo izquierdo = nodo.izquierdo;
            if(izquierdo !=null && derecho !=null){
                if (izquierdo.prioridad.compareTo(derecho.prioridad)>=0 && nodo.prioridad.compareTo(izquierdo.prioridad)<0){
                    if (izquierdo.izquierdo != null){
                        izquierdo.izquierdo.padre=nodo;
                    }
                    if (izquierdo.derecho != null){
                        izquierdo.derecho.padre=nodo;
                    }
                    izquierdo.padre=null;
                    nodo.izquierdo=izquierdo.izquierdo;
                    nodo.derecho=izquierdo.derecho;
                    nodo.padre=izquierdo;
                    derecho.padre=izquierdo;
                    izquierdo.izquierdo=nodo;
                    izquierdo.derecho=derecho;
                    this.raiz=izquierdo;

                }
                else if (derecho.prioridad.compareTo(izquierdo.prioridad)>0 && nodo.prioridad.compareTo(derecho.prioridad)<0){
                    if (derecho.izquierdo != null){
                        derecho.izquierdo.padre=nodo;
                    }
                    if (derecho.derecho != null){
                        derecho.derecho.padre=nodo;
                    }
                    derecho.padre=null;
                    nodo.izquierdo=derecho.izquierdo;
                    nodo.derecho=derecho.derecho;
                    nodo.padre=derecho;
                    izquierdo.padre=derecho;
                    derecho.izquierdo=izquierdo;
                    derecho.derecho=nodo;
                    this.raiz=derecho;
                }
            
            }
            else if(izquierdo!=null && derecho== null){
                if (nodo.prioridad.compareTo(izquierdo.prioridad)<0){
                    if (izquierdo.izquierdo != null){
                        izquierdo.izquierdo.padre=nodo;
                    }
                    if (izquierdo.derecho != null){
                        izquierdo.derecho.padre=nodo;
                    }
                    
                    izquierdo.padre = null;
                    nodo.padre=izquierdo;
                    nodo.izquierdo=izquierdo.izquierdo;
                    nodo.derecho=izquierdo.derecho;
                    izquierdo.derecho=null;
                    izquierdo.izquierdo=nodo;
                    this.raiz=izquierdo;
                    
                }

            }
            
        }
        else if (nodo.padre !=null && nodo.padre.prioridad.compareTo(nodo.prioridad)<0){
                Nodo padre = nodo.padre;
                Nodo abuelo = padre.padre;
                Nodo izquierdo= nodo.izquierdo;
                Nodo derecho = nodo.derecho;
                Nodo otroHijoPadre = null;
                if (padre.izquierdo == nodo){
                    otroHijoPadre= padre.derecho;
                }
                if (padre.derecho==nodo){
                    otroHijoPadre=padre.izquierdo;

                }

                if (abuelo != null){
                    if(abuelo.izquierdo == padre){
                        abuelo.izquierdo = nodo;
                    }
                    else if (abuelo.derecho == padre) {
                        abuelo.derecho = nodo;
                    }
                }
                if(padre.izquierdo == nodo){
                    nodo.izquierdo=padre;
                    nodo.derecho=otroHijoPadre;
                }
                if(padre.derecho == nodo){
                    nodo.derecho= padre;
                    nodo.izquierdo=otroHijoPadre;
                }
                
                if (izquierdo != null){
                    izquierdo.padre= padre;

                }
                padre.izquierdo= izquierdo;
                if (derecho !=null){
                   derecho.padre = padre;

                }
                padre.derecho= derecho;
                padre.padre=nodo;
                nodo.padre = abuelo;
                if (otroHijoPadre!=null){
                otroHijoPadre.padre=nodo;    
                }
                if (nodo.padre == null){
                this.raiz=nodo;
                }

        }
        else if ((nodo.izquierdo != null && (nodo.izquierdo.prioridad.compareTo(nodo.prioridad)>0))
            || (nodo.derecho != null && nodo.derecho.prioridad.compareTo(nodo.prioridad)>0))  {
                Nodo padre = nodo.padre;
                Nodo izquierdo= nodo.izquierdo;
                Nodo derecho = nodo.derecho;

            if (nodo.izquierdo != null && nodo.derecho!= null){
               if(nodo.izquierdo.prioridad.compareTo(nodo.derecho.prioridad)>= 0 && nodo.prioridad.compareTo(nodo.izquierdo.prioridad)< 0 ){
                   if (padre != null){
                    if (padre.izquierdo == nodo){
                        padre.izquierdo = izquierdo;
                        izquierdo.padre=padre;
                    }
                    else if (padre.derecho == nodo) {                        
                        padre.derecho = izquierdo;
                        izquierdo.padre=padre;
                    }

                   }
                   

                   if (izquierdo.izquierdo != null) {
                    izquierdo.izquierdo.padre = nodo;

                   }
                   if (izquierdo.derecho !=null){
                    izquierdo.derecho.padre = nodo;
                   }
                   nodo.izquierdo=izquierdo.izquierdo;
                   nodo.derecho=izquierdo.derecho;
                   nodo.padre=izquierdo;
                   izquierdo.izquierdo=nodo;
                   izquierdo.derecho=derecho;
                   derecho.padre=izquierdo;
                   izquierdo.padre=padre;
                   if (izquierdo.padre == null){
                    this.raiz=izquierdo;
                   }

                }
                else if(nodo.derecho.prioridad.compareTo(nodo.izquierdo.prioridad) > 0 && nodo.prioridad.compareTo(nodo.derecho.prioridad)< 0 ){
                   if (padre != null){
                    if (padre.izquierdo == nodo){
                        padre.izquierdo = derecho;
                        derecho.padre=padre;
                    }
                    if (padre.derecho == nodo) {                        
                        padre.derecho = derecho;
                        derecho.padre=padre;
                    }

                   }

                   if (derecho.izquierdo != null) {
                    derecho.izquierdo.padre = nodo;

                   }
                   if (derecho.derecho !=null){
                    derecho.derecho.padre = nodo;
                   }
                   nodo.izquierdo=derecho.izquierdo;
                   nodo.derecho=derecho.derecho;
                   nodo.padre=derecho;
                   derecho.izquierdo=izquierdo;
                   derecho.derecho=nodo;
                   izquierdo.padre=derecho;
                   derecho.padre=padre;
                   if (derecho.padre == null){
                    this.raiz=derecho;
                   }
                }
                
            }
            else if (nodo.izquierdo != null && nodo.derecho == null && nodo.prioridad.compareTo(nodo.izquierdo.prioridad)< 0){
                if (padre != null){
                    if (padre.izquierdo == nodo){
                        padre.izquierdo = izquierdo;
                        izquierdo.padre=padre;
                    }
                    if (padre.derecho == nodo){                        
                        padre.derecho = izquierdo;
                        izquierdo.padre=padre;
                    }

                }
                izquierdo.izquierdo=nodo;
                izquierdo.derecho=null;
                nodo.padre=izquierdo;
                nodo.izquierdo=null;
                nodo.derecho=null;
                if (izquierdo.padre == null){
                this.raiz=izquierdo;
                    }
            }
        

           
            }
            if (nodo.padre ==null){
            this.raiz=nodo;
            }
    }
    if (nodo.padre ==null){
            this.raiz=nodo;
            }
    }
    
    public Nodo insertar(T data, T prioridad){
        this.cardinal++;
        Nodo nodoNuevo = new Nodo(data, prioridad);
        if (this.cardinal == 1){
            this.raiz = nodoNuevo ;
        }
        if (this.cardinal > 1){
        int numero = this.cardinal;
        String binario = Integer.toBinaryString(numero);
        Nodo nodo = this.raiz;
        for (int i = 1; i < binario.length()-1; i++){
            char letra = binario.charAt(i);
            if (Character.getNumericValue(letra) == 0){
                nodo=nodo.izquierdo;
            
            }
            else {
                nodo = nodo.derecho;
            } 
        }
        char letra = binario.charAt(binario.length()-1);
        if (Character.getNumericValue(letra) == 0){
            nodo.izquierdo= nodoNuevo;
            nodo.izquierdo.padre = nodo;
            ordenar(nodo.izquierdo);

        }
        else {
            nodo.derecho = nodoNuevo;
            nodo.derecho.padre = nodo;
            ordenar(nodo.derecho);
        }

       }
        return nodoNuevo;
    }
    public void modificar(Nodo nodo, T prioridad){
        nodo.prioridad = prioridad;
        ordenar(nodo);

    }


    public T eliminar(){
        Nodo nodoActual = this.raiz;
        if(nodoActual == null){
            return null;
        }

        Nodo nodoRaizViejo = this.raiz;

        if(this.cardinal==1){
            this.raiz=null;
            this.cardinal--;
            return nodoRaizViejo.data;
        }
        else if (this.cardinal> 1){
        String binario = Integer.toBinaryString(this.cardinal);
        for (int i = 1; i < binario.length(); i++){
            char letra = binario.charAt(i);
            if (Character.getNumericValue(letra) == 0){
                nodoActual=nodoActual.izquierdo;
            
            }
            else {
                nodoActual = nodoActual.derecho;
            } 
        }
        
        if(nodoActual.padre!=null){
            if (nodoActual.padre.izquierdo == nodoActual){
                nodoActual.padre.izquierdo=null;
            }
            else {nodoActual.padre.derecho=null;}
        }

        nodoActual.padre=null;
        nodoActual.izquierdo=null;
        nodoActual.derecho=null;

        Nodo derecho = nodoRaizViejo.derecho;
        Nodo izquierdo = nodoRaizViejo.izquierdo;


        if (derecho!=null){
            derecho.padre=nodoActual;

        }
        if (izquierdo!=null){
            izquierdo.padre=nodoActual;
        }
        nodoActual.derecho=derecho;
        nodoActual.izquierdo=izquierdo;
        nodoRaizViejo.derecho=null;
        nodoRaizViejo.izquierdo=null;
        this.raiz=nodoActual;

        ordenar (nodoActual);
        if (nodoActual.padre==null){
            this.raiz=nodoActual;
        }
        cardinal--;
        }

        return nodoRaizViejo.data;
        
}

}
