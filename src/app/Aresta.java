package app;

import lombok.*;

@Data
@NoArgsConstructor
public class Aresta {
    public Aresta(String nome, Vertice origem, Vertice destino) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
    }



    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private Vertice origem;

    @Getter
    @Setter
    private Vertice destino;



    @Getter
    @Setter
    private boolean caminhoiverso =false;

    public String toString() {
        return "Aresta{" +
                "nome='" + nome + '\'' +
                ", origem=" + origem.getNome() +
                ", destino=" + destino.getNome() +
                ", caminhoInverso=" + caminhoiverso +
                "}\n";
    }
}
