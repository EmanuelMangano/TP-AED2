package aed.tp2;

public class transferencia {

    private int id_receptor;
    private int id_emisor;
    private int parExclusivo;

    public transferencia(int id_receptor, int id_emisor, int parExclusivo){
        this.id_receptor = id_receptor;
        this.id_emisor = id_emisor;
        this.parExclusivo = parExclusivo;
    }

    public int id_receptor(){
        return this.id_receptor;
    }

    public int id_emisor(){
        return this.id_emisor;
    }

    public int parExclusivo(){
        return this.parExclusivo;
    }
}
