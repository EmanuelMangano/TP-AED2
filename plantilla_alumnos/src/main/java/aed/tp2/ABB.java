package aed.tp2;

public class ABB<T extends Comparable<T>> {
    // Agregar atributos privados del Conjunto
    private Nodo raiz;
    private int altura;
    private int cardinal;

    private class Nodo {
        // Agregar atributos privados del Nodo
        
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo(T v) {
        valor = v;
        izq = null;
        der = null;
        padre = null;
    }

        // Crear Constructor del nodo
    }

    public ABB() {
        raiz = null;
        cardinal = 0;
        altura = 0;
    }

    public int cardinal() {
        return this.cardinal;
    }

    public T minimo(){
        T res = minimoRecursivo(this.raiz);
        return res;
    }

    public T minimoRecursivo (Nodo abb){
        if (abb.izq != null) {
            return minimoRecursivo(abb.izq);
        }
        else{
            return abb.valor;
        }
    }

    public T maximo(){
         T res = maximoRecursivo(this.raiz);
        return res;
    }

    public T maximoRecursivo (Nodo abb){
        if (abb.der != null) {
            return maximoRecursivo(abb.der);
        }
        else{
            return abb.valor;
        }
    }

    public void insertar(T elem){
        Nodo abb = this.raiz;
        Nodo nuevoAbb = new Nodo(elem);
        if (abb == null){
            this.raiz = nuevoAbb;
            this.cardinal = 1;
        }
        else if (pertenece(elem) == true){
            return;
        }
        else{
            this.insertarRecursivo (nuevoAbb,abb);
            } 
    }

    public void insertarRecursivo(Nodo nuevoAbb, Nodo abb){
        if (abb.valor == null){
            return;
        }
        else if (nuevoAbb.valor.compareTo(abb.valor) > 0){
            if (abb.der == null){
                abb.der = agregarNodo(nuevoAbb, abb, abb.der);
            }
            else {
                insertarRecursivo(nuevoAbb, abb.der);
            }
        }
        else{
            if (abb.izq == null){
                abb.izq = agregarNodo(nuevoAbb, abb, abb.izq);
            }
            else {
                insertarRecursivo(nuevoAbb, abb.izq);
            }
        }
        }
    
    public Nodo agregarNodo (Nodo nuevoAbb, Nodo abb, Nodo rama){
        rama = nuevoAbb;
        nuevoAbb.padre = abb;
        this.cardinal += 1;
        return nuevoAbb;
    }

    public boolean pertenece(T elem){
        Nodo abb = this.raiz;
        boolean res = this.perteneceRecursivo(abb, elem);;
        return res;
    }

    public boolean perteneceRecursivo (Nodo abb,T elem){
        if (abb == null){
            return false;
        }
        if (abb.valor.compareTo(elem) == 0){
            return true;
        }
        else if (abb.valor.compareTo(elem) < 0){
                return perteneceRecursivo(abb.der, elem);
        }
        else {
            return perteneceRecursivo(abb.izq, elem);
            }
        }

    public void eliminar(T elem){
        Nodo nodo = this.raiz;
        Nodo padre = null;
        if (elem.compareTo(nodo.valor) == 0 && nodo.der == null && nodo.izq == null) {
            this.raiz = null;
            this.cardinal -= 1;
            return;
        }

        eliminarRecursivo(elem, padre, nodo);
    }

    public void eliminarRecursivo(T elem, Nodo padre, Nodo arbol){
        if(arbol == null) return; 
        if(elem.compareTo(arbol.valor) > 0) eliminarRecursivo(elem, arbol, arbol.der);
        else if(elem.compareTo(arbol.valor)<0) eliminarRecursivo(elem, arbol, arbol.izq);
        else {
            if(arbol.der == null || arbol.izq == null){
                Nodo hijo = arbol.izq != null ? arbol.izq : arbol.der;

                if (padre == null) {
                    this.raiz = hijo;
                } else if (padre.izq == arbol) {
                    padre.izq = hijo;
                } else {
                    padre.der = hijo;
                }
                this.cardinal -= 1;
            }else{
                Nodo nodoAct = arbol;
                Nodo predecesorInmediato = nodoAct.izq;

                while(predecesorInmediato.der != null){
                    nodoAct = predecesorInmediato;
                    predecesorInmediato = predecesorInmediato.der;
                }

                arbol.valor = predecesorInmediato.valor;
                
                if(nodoAct != arbol){
                    nodoAct.der = predecesorInmediato.izq; 
                }else{
                    nodoAct.izq = predecesorInmediato.izq;
                }
            
                this.cardinal -= 1;
            }
        }
    }

    public String toString(){
        Nodo abb = hallarNodoConMinimo(this.raiz);
        String res = "{";
        while (abb != null){
            res += abb.valor;
            abb = hallarNodoSiguiente(abb);
            if (abb != null){
                res += ",";
            }
        }
        res += "}";
        return res;
        }

    public Nodo hallarNodoConMinimo(Nodo arbol){
        if (arbol == null) {
            return null;
        }
        else if(arbol.izq == null){ 
            return arbol;}
        else{
            return hallarNodoConMinimo(arbol.izq);
        }
    }

     public Nodo hallarNodoSiguiente(Nodo arbol) {
        if (arbol == null) return null;
        if (arbol.der != null) return hallarNodoConMinimo(arbol.der);
        return hallarSiguienteNodoPadre(arbol);
    }

    private Nodo hallarSiguienteNodoPadre(Nodo arbol) {
        Nodo nodoPadre = arbol.padre;
        if (arbol == null || nodoPadre == null || arbol == nodoPadre.izq) return nodoPadre;
        return hallarSiguienteNodoPadre(nodoPadre);
    }

    public class ABB_Iterador {
        private Nodo _actual;

        public ABB_Iterador(){ 
            this._actual = hallarNodoConMinimo(raiz);
        }

        public boolean haySiguiente() {            
            return (this._actual != null); 
        }
    
        public T siguiente() {
            if (haySiguiente() == true){
            Nodo anterior = this._actual;
            Nodo siguiente = hallarNodoSiguiente(this._actual);
            this._actual = siguiente;
            return anterior.valor;
            }
            else{
                return this._actual.valor;
            }
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
