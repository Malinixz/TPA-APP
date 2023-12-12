package app;

import lib.arvore.ArvoreBinaria;
import lib.grafo.Grafo;

import java.util.Scanner;

public class Application {

    public static void adicionarLivro(Scanner scanner, ArvoreBinaria arvoreLivros, Grafo grafoLivros) {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        titulo = titulo.substring(0,1).toUpperCase() + titulo.substring(1);   // PRIMEIRA LETRA MAIUSCULA
        System.out.print("Digite o nome do autor: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o ano de publicação: ");
        int anoPublicacao = scanner.nextInt();

        Livro novoLivro = new Livro(titulo, autor, anoPublicacao);
        arvoreLivros.adicionar(novoLivro);
        grafoLivros.addVertice(novoLivro);
        System.out.println("Livro adicionado com sucesso!");
    }

    public static void pesquisarLivro(Scanner scanner, ArvoreBinaria arvoreLivros) {
        System.out.print("Digite o título do livro a ser pesquisado: ");
        String titulo = scanner.nextLine();
        titulo = titulo.substring(0,1).toUpperCase() + titulo.substring(1);  // PRIMEIRA LETRA MAIUSCULA

        Livro resultado = (Livro) arvoreLivros.pesquisar(new Livro(titulo, null, 0));

        if (resultado != null) {
            System.out.println("Livro encontrado: " + resultado);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void removerLivro(Scanner scanner, ArvoreBinaria arvoreLivros) {
        System.out.print("Digite o título do livro a ser removido: ");
        String titulo = scanner.nextLine();
        titulo = titulo.substring(0,1).toUpperCase() + titulo.substring(1);  // PRIMEIRA LETRA MAIUSCULA

        Livro livroRemovido = (Livro) arvoreLivros.remover(new Livro(titulo, null, 0));

        if (livroRemovido != null) {
            System.out.println("Livro removido: " + livroRemovido);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void listarLivros(ArvoreBinaria arvoreLivros) {
        String listaLivros = arvoreLivros.caminharEmOrdem();
        System.out.println("Lista de Livros:");
        System.out.println(listaLivros);
    }
}
