package app;

import lombok.*;


import java.util.ArrayList;
import java.util.List;



public class Vertice {
    @Setter
    @Getter
    private String nome;
    @Setter
    @Getter
    private List<Vertice> indegree;
    @Setter
    @Getter
    private List<Vertice> outdegree;

    public Vertice(String nome) {
        this.nome = nome;
        this.outdegree = new ArrayList<>();
        this.indegree = new ArrayList<>();

    }


    public int getGrau() {
        return outdegree.size() + indegree.size() ;
    }
    @Override
    public String toString() {
        return "\nVertice{" +
                "nome='" + nome + '\'' +
                ", indegree=" + indegree.size() +
                ", outdegree=" + outdegree.size() +
                "}";

    }
}