package app;

import lib.arvore.ArvoreBinaria;
import lib.grafo.Grafo;

import java.util.Comparator;
import java.util.Scanner;

import static app.ApplicationUtil.*;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Comparator<Livro> comparador = new LivroComparator();
        ArvoreBinaria arvoreLivros = new ArvoreBinaria(comparador);
        Grafo grafoLivros = new Grafo();

        while (true) {
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Pesquisar Livro");
            System.out.println("3. Remover Livro");
            System.out.println("4. Listar Livros");
            System.out.println("5. Ordem de Leitura Indicada");
            System.out.println("6. Verificar Ciclos");
            System.out.println("7. Criar Dependência");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (escolha) {
                case 1:
                    adicionarLivro(scanner, arvoreLivros, grafoLivros);
                    break;
                case 2:
                    pesquisarLivro(scanner, arvoreLivros);
                    break;
                case 3:
                    removerLivro(scanner, arvoreLivros, grafoLivros);
                    break;
                case 4:
                    listarLivros(arvoreLivros);
                    break;
                case 5:
                    ordemLeitura(grafoLivros);
                    break;
                case 6:
                    verificaCiclos(grafoLivros);
                    break;
                case 7:
                    criaDependencia(scanner, arvoreLivros, grafoLivros);
                    break;
                case 8:
                    System.out.println("Programa Finalizado.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}