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

        scanner.nextLine();  // CONSOME A QUEBRA DE LINHA APÓS NEXTINT

        Livro novoLivro = new Livro(titulo, autor, anoPublicacao);
        arvoreLivros.adicionar(novoLivro);
        grafoLivros.addVertice(novoLivro);

        System.out.println("Digite o título de uma dependência (ou '0' para encerrar): ");
        String dependenciaTitulo;
        while (!(dependenciaTitulo = scanner.nextLine()).equals("0")) {
            //dependenciaTitulo = dependenciaTitulo.substring(0, 1).toUpperCase() + dependenciaTitulo.substring(1); // PRIMEIRA LETRA MAIUSCULA
            Livro dependencia = (Livro) arvoreLivros.pesquisar(new Livro(dependenciaTitulo, null, 0));
            if (dependencia != null && !novoLivro.getDependencias().contains(dependencia) && dependencia != novoLivro)  {
                novoLivro.adicionarDependencia(dependencia);
                grafoLivros.addAresta(dependencia,novoLivro);
                System.out.println("Dependência adicionada: \n" + dependencia);
            } else {
                System.out.println("Livro não encontrado como dependência.");
            }
            System.out.println("Digite o título de outra dependência (ou '0' para encerrar): ");
        }

        //arvoreLivros.adicionar(novoLivro);
        //grafoLivros.addVertice(novoLivro);
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

    public static void removerLivro(Scanner scanner, ArvoreBinaria arvoreLivros, Grafo grafoLivros) {
        System.out.print("Digite o título do livro a ser removido: ");
        String titulo = scanner.nextLine();
        titulo = titulo.substring(0,1).toUpperCase() + titulo.substring(1);  // PRIMEIRA LETRA MAIUSCULA

        Livro livroRemovido = (Livro) arvoreLivros.remover(new Livro(titulo, null, 0));
        grafoLivros.removerVertice(livroRemovido);

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

    public static void ordemLeitura(Grafo grafoLivros){
        System.out.println("Ordem recomendada de leitura: \n");
        grafoLivros.ordenacaoTopologica();
    }

    public static void verificaCiclos(Grafo grafoLivros){
        if (grafoLivros.temCiclo()){
            System.out.println("Há ciclo nas dependências de Leitura");
        } else {
            System.out.println("Não há ciclo nas dependências");
        }
    }
    
    public static void criaDependencia(Scanner scanner, ArvoreBinaria arvoreLivros, Grafo grafoLivros){
        System.out.println("Digite o título do livro origem :\n");
        String tituloOrigem = scanner.nextLine();
        Livro livroOrigem = (Livro) arvoreLivros.pesquisar(new Livro(tituloOrigem, null, 0));   // PESQUISA O LIVRO ATRAVÉS DO TITULO
        System.out.println("Digite o título do livro destino :\n");
        String tituloDestino = scanner.nextLine();
        Livro livroDestino = (Livro) arvoreLivros.pesquisar(new Livro(tituloDestino, null, 0));   // PESQUISA O LIVRO ATRAVÉS DO TITULO
        
        if (livroDestino != null && livroOrigem != null && !livroDestino.getDependencias().contains(livroOrigem) && livroDestino != livroOrigem){
            grafoLivros.addAresta(livroOrigem, livroDestino);
            livroDestino.getDependencias().add(livroOrigem);
            System.out.println("Dependência adicionada: \n" + livroOrigem);
        } else {
            System.out.println("Livro não encontrado como dependência.");
        } 
    }
}
