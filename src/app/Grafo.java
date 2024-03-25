package app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private boolean eDirecionado;

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
            if (!this.eDirecionado) eDigrafo(aresta);
            arestas.add(aresta);
            System.out.println("Aresta " + aresta.getNome() + " adicionada com sucesso!");
        }
    }

    private void eDigrafo(Aresta aresta) {
        if (aresta.getOrigem().equals(aresta.getDestino())) this.eDirecionado = true; // Self Loop
        else{
            for(Aresta arestaSalva : arestas ){
                if (aresta.getOrigem() == arestaSalva.getDestino() && aresta.getDestino() == arestaSalva.getOrigem()) {
                    this.eDirecionado = true;
                    break;
                } // Ciclo
            }
        }
    }

    public void verificandoAdjacencias(){
        if (this.eDirecionado){
            for (Aresta aresta : arestas) {
                Vertice origem = aresta.getOrigem();
                Vertice destino = aresta.getDestino();

                if (destino.getAdjacencias().contains(origem)) {
                    destino.getAdjacencias().add(origem);
                }
            }
        } else{
            for (Aresta aresta : arestas){
                Vertice origem = aresta.getOrigem();
                Vertice destino = aresta.getDestino();

                origem.getAdjacencias().add(destino);
                destino.getAdjacencias().add(origem);
            }
        }
    }

    public int tamanho() {
        return arestas.size();
    }

    public int ordem() {
        return vertices.size();
    }

}
