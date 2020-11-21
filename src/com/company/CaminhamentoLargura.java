package com.company;
import java.util.LinkedList;
import java.util.List;

public class CaminhamentoLargura {

    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    private int start;

    public CaminhamentoLargura(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        this.start = start;
        bfs(G, s);
    }

    private void bfs(Graph g, int s) {
        List<Integer> q = new LinkedList<>();
        q.add(s);
        marked[s] = true; // marca o inicial como visitado
        edgeTo[s] = -1; // ninguém
        distTo[s] = 0;
        while (!q.isEmpty()) {
            int v = q.remove(0);
            // System.out.println("Analisando " + v);
            for (int w : g.adj(v)) {
                if (!marked[w]) // se não foi marcado ainda (não está na fila)
                {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1; // dist. ao vizinho é esta + 1
                    q.add(w);
                }
            }
        }
    }

    // Retorna um objeto com o caminho de start até v
    // (pode ser uma lista, uma pilha, etc)
    public Iterable<Integer> pathTo(int v) {
        List<Integer> path = new LinkedList<>();
        while (v != start) {
            path.add(0, v); // insere no início para inverter a ordem
            v = edgeTo[v];
        }
        path.add(0, start); // idem
        return path;
    }

    public void exibe() {
        for (int v = 0; v < marked.length; v++) {
            System.out.println(v + ": " + marked[v] + " - " + edgeTo[v] + " - " + distTo[v]);
        }
    }

    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
