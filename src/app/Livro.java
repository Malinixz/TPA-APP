package app;

public class Livro {

    private String  titulo;
    private String  autor;
    private Integer anoPublicado;

    public Livro(String titulo, String autor, Integer anoPublicado){
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicado = anoPublicado;
    }

    @Override
    public String toString(){
        return "Livro{\n"+
               "  *Título: "+this.titulo+"\n"+
               "  *Autor : "+this.autor +"\n"+
               "  *Ano   : "+this.anoPublicado+"}\n";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoPublicado() {
        return anoPublicado;
    }

    public void setAnoPublicado(Integer anoPublicado) {
        this.anoPublicado = anoPublicado;
    }
}
