package lib.grafo;

import java.util.ArrayList;
import java.util.Stack;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    private int[][] arestas;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new int[0][0];
    }

    public Vertice<T> addVertice(T valor) {
        Vertice<T> novoVertice = new Vertice<>(valor);
        this.vertices.add(novoVertice);

        // Aumenta a matriz de adjacência para acomodar o novo vértice
        int tamanhoAtual = arestas.length;
        int[][] novaMatriz = new int[tamanhoAtual + 1][tamanhoAtual + 1];

        for (int i = 0; i < tamanhoAtual; i++) {
            System.arraycopy(arestas[i], 0, novaMatriz[i], 0, tamanhoAtual);
        }

        this.arestas = novaMatriz;

        return novoVertice;
    }

    public void removerVertice(T valor) {
        int indexVertice = getIndexVertice(valor);

        if (indexVertice != -1) {
            // Remove o vértice da lista de vértices
            vertices.remove(indexVertice);

            // Reduz a matriz de adjacência
            int tamanhoAtual = arestas.length;
            int[][] novaMatriz = new int[tamanhoAtual - 1][tamanhoAtual - 1];   // INSTANCIA NOVA MATRIZ DE ARESTAS

            for (int i = 0, k = 0; i < tamanhoAtual; i++) {   // LOOP PARA PERCORRER LINHA DAS MATRIZES
                if (i == indexVertice) {
                    continue;
                }

                for (int j = 0, l = 0; j < tamanhoAtual; j++) {   // LOOP PARA PERCORRER COLUNAS
                    if (j == indexVertice) {
                        continue;
                    }

                    novaMatriz[k][l] = arestas[i][j];
                    l++;
                }
                k++;
            }

            this.arestas = novaMatriz;
        }
    }

    public void addAresta(T origem, T destino) {
        int indexOrigem = getIndexVertice(origem);  
        int indexDestino = getIndexVertice(destino);

        if (indexOrigem != -1 && indexDestino != -1) {
            arestas[indexOrigem][indexDestino] = 1;
        }
    }

    public boolean temCiclo() {
        boolean[] visitado = new boolean[vertices.size()];
        boolean[] pilhaRecursao = new boolean[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            if (!visitado[i]) {
                if (temCicloUtil(i, visitado, pilhaRecursao)) {
                    return true; // Ciclo encontrado
                }
            }
        }

        return false; // Nenhum ciclo encontrado
    }

    private boolean temCicloUtil(int vertice, boolean[] visitado, boolean[] pilhaRecursao) {
        visitado[vertice] = true;
        pilhaRecursao[vertice] = true;

        for (int vizinho = 0; vizinho < vertices.size(); vizinho++) {
            if (arestas[vertice][vizinho] == 1) {
                if (!visitado[vizinho]) {
                    if (temCicloUtil(vizinho, visitado, pilhaRecursao)) {
                        return true;
                    }
                } else if (pilhaRecursao[vizinho]) {
                    return true; // Ciclo encontrado
                }
            }
        }

        pilhaRecursao[vertice] = false; // Remover da pilha de recursão ao retroceder
        return false;
    }

    public void ordenacaoTopologica() {
        Stack<Vertice<T>> pilha = new Stack<>();
        boolean[] visitado = new boolean[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            if (!visitado[i]) {
                ordenacaoTopologicaUtil(i, visitado, pilha);
            }
        }

        System.out.println("Ordenação Topológica:");
        while (!pilha.isEmpty()) {
            System.out.print(pilha.pop().getValor() + " ");
        }
        System.out.println();
    }

    private void ordenacaoTopologicaUtil(int vertice, boolean[] visitado, Stack<Vertice<T>> pilha) {
        visitado[vertice] = true;

        for (int vizinho = 0; vizinho < vertices.size(); vizinho++) {
            if (arestas[vertice][vizinho] == 1 && !visitado[vizinho]) {
                ordenacaoTopologicaUtil(vizinho, visitado, pilha);
            }
        }

        pilha.push(vertices.get(vertice));
    }

    private int getIndexVertice(T valor){   // PESQUISA ÍNDICE A PARTIR DE VALOR
        Vertice v;
        for(int i = 0; i < this.vertices.size(); i++){
            v = this.vertices.get(i);
            if(v.getValor() == valor){
                return i;
            }
        }
        return -1; // CASO NÃO EXISTA VERTICE COM O VALOR PASSADO
    }
}
