package lib.grafo;

public class Vertice<T> {
    private T valor;

    public Vertice(T valor){
        this.setValor(valor);
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
}
