package app;

import java.util.ArrayList;

public class Livro {

    private String  titulo;
    private String  autor;
    private Integer anoPublicado;
    private ArrayList<Livro> dependenciasList;

    public Livro(String titulo, String autor, Integer anoPublicado){
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicado = anoPublicado;
        this.dependenciasList = new ArrayList<>();
    }

    @Override
    public String toString(){
        StringBuilder dependencias = new StringBuilder();

        for (Livro dependencia : this.dependenciasList){    // CONSTRUIR UMA STRING DE TITULOS PARA DEPENDENCIAS
            dependencias.append(dependencia.getTitulo()).append(", ");
        }

        if (dependencias.length() > 0) {                    // REMOVER A ÚLTIMA VÍRGULA DA STRING
            dependencias.delete(dependencias.length() - 2, dependencias.length());
        }
        return "Livro{ Título: "+this.titulo+" | Autor: "+this.autor +" | Ano: "+this.anoPublicado+" | Dependências: " +dependencias+"}\n";
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

    public void adicionarDependencia(Livro dependencia){
        dependenciasList.add(dependencia);
    }

    public ArrayList<Livro> getDependencias(){
        return this.dependenciasList;
    }
}
