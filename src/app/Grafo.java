package app;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private boolean eDirecionado = false;

    public Grafo(){
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }
    // Sobrecarga de método
    public Grafo(boolean eDirecionado){
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
        this.eDirecionado = eDirecionado;
    }

    public void adicionarVertice(Vertice vertice) {
        if (vertice == null) System.out.println("Vértice nulo não pode ser adicionado!");
        else if (vertices.contains(vertice)) System.out.println("Vértice " + vertice.getNome() + " já existe!");
        else if (vertice.getNome().isEmpty()) System.out.println("Vértice sem nome não pode ser adicionado!");
        else {
            vertices.add(vertice);
            System.out.println("Vértice " + vertice.getNome() + " adicionado com sucesso!");
        }
    }

    public void adicionarAresta(Aresta aresta) {
        if (aresta == null) System.out.println("Aresta nula não pode ser adicionada!");
        else if (arestas.contains(aresta)) System.out.println("Aresta " + aresta.getNome() + " já existe!");
        else if (aresta.getNome().isEmpty()) System.out.println("Aresta sem nome não pode ser adicionada!");
        else if (!vertices.contains(aresta.getOrigem()) || !vertices.contains(aresta.getDestino()))
            System.out.println("Vértice de origem ou de destino da aresta" +  aresta.getNome() + " não existe!");
        else {


            arestas.add(aresta);
            System.out.println("Aresta " + aresta.getNome() + " adicionada com sucesso!");

            eDigrafo(aresta);


            System.out.println("é direcionado :  " +this.eDirecionado);
        }
    }


    private boolean temCamnhoIverso(Aresta aresta){
        if (!aresta.isCaminhoiverso()){
            for(Aresta arestaSalva : arestas ){


                if (aresta.getOrigem() == arestaSalva.getDestino() && aresta.getDestino() == arestaSalva.getOrigem()) {
                    return true;
                } // Ciclo
            }
        }

        return false;
    }

    private void eDigrafo(Aresta aresta) {

        this.eDirecionado = temCamnhoIverso(aresta);
    }




    public void verificandoAdjacencias(){
            for (Aresta aresta : arestas){
                Vertice origem = aresta.getOrigem();
                Vertice destino = aresta.getDestino();

                origem.getIndegree().add(destino);
                destino.getOutdegree().add(origem);
            }

    }

    public int tamanho() {
        return arestas.size();
    }

    public int ordem() {
        return vertices.size();
    }



    public void matrizAdjacencia(){
        int ordem = vertices.size();
        int matriz[][] = new int[ordem][ordem];

        zerarmatriz(ordem, ordem, matriz);

        for (Aresta aresta : arestas) {
            int indiceOrigem = vertices.indexOf(aresta.getOrigem());
            int indiceDestino = vertices.indexOf(aresta.getDestino());

            if (eDirecionado) {
                matriz[indiceOrigem][indiceDestino] = 1;
                matriz[indiceDestino][indiceOrigem] = 1;  // Para grafos não direcionados
            } else {
                matriz[indiceOrigem][indiceDestino] = 1;  // Para grafos direcionados
            }
        }
        mostrarMatriz(ordem, ordem, matriz);

    }

    public void matrizIncidencia() {
        int ordem = vertices.size();
        int tamanho = arestas.size();
        int matriz[][] = new int[ordem][tamanho];

        zerarmatriz(ordem, tamanho, matriz);

        for (int j = 0; j < tamanho; j++) {
            Aresta aresta = arestas.get(j);
            int indiceOrigem = vertices.indexOf(aresta.getOrigem());
            int indiceDestino = vertices.indexOf(aresta.getDestino());


            matriz[indiceOrigem][j] = -1;
            matriz[indiceDestino][j] = 1;  // Para grafos direcionados

        }

        // Imprimir a matriz de incidência (opcional)
        mostrarMatriz(tamanho, ordem, matriz);
    }

    private static void mostrarMatriz(int tamanho, int ordem, int[][] matriz) {
        System.out.print("\t");
        for (int i = 0; i < tamanho; i++) {
            System.out.print("a" + (i + 1) + "  ");
        }
        System.out.println();

        for (int i = 0; i < ordem; i++) {
            System.out.print("v" + (i + 1));
            for (int j = 0; j < tamanho; j++) {
                System.out.print("  " + matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void zerarmatriz(int ordem, int tamanho, int[][] matriz) {
        for (int i = 0; i < ordem; i++) {
            for (int j = 0; j < tamanho; j++) {
                matriz[i][j] = 0;  // Inicializa todos os elementos com zero
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grafo {");
        sb.append("\nvertices=").append(vertices);
        sb.append(",\narestas=").append(arestas);
        sb.append(", eDirecionado=").append(eDirecionado);
        sb.append('}');
        return sb.toString();
    }
}
