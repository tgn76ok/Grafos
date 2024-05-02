import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        //norte
        Vertice AC = new Vertice("AC");
        grafo.adicionarVertice(AC);
        Vertice AM = new Vertice("AM");
        grafo.adicionarVertice(AM);
        Vertice RR = new Vertice("RR");
        grafo.adicionarVertice(RR);
        Vertice RO = new Vertice("RO");
        grafo.adicionarVertice(RO);
        Vertice PA = new Vertice("PA");
        grafo.adicionarVertice(PA);
        Vertice AP = new Vertice("AP");
        grafo.adicionarVertice(AP);
        Vertice TO = new Vertice("TO");
        grafo.adicionarVertice(TO);

        //nordeste
        Vertice MA = new Vertice("MA");
        grafo.adicionarVertice(MA);
        Vertice PI = new Vertice("PI");
        grafo.adicionarVertice(PI);
        Vertice CE = new Vertice("CE");
        grafo.adicionarVertice(CE);
        Vertice RN = new Vertice("RN");
        grafo.adicionarVertice(RN);
        Vertice PB = new Vertice("PB");
        grafo.adicionarVertice(PB);
        Vertice PE = new Vertice("PE");
        grafo.adicionarVertice(PE);
        Vertice AL = new Vertice("AL");
        grafo.adicionarVertice(AL);
        Vertice SE = new Vertice("SE");
        grafo.adicionarVertice(SE);
        Vertice BA = new Vertice("BA");
        grafo.adicionarVertice(BA);

        //CENTRO-OESTE
        Vertice MT = new Vertice("MT");
        grafo.adicionarVertice(MT);
        Vertice GO = new Vertice("GO");
        grafo.adicionarVertice(GO);
        Vertice DF = new Vertice("DF");
        grafo.adicionarVertice(DF);
        Vertice MS = new Vertice("MS");
        grafo.adicionarVertice(MS);

        //SUDESTE
        Vertice MG = new Vertice("MG");
        grafo.adicionarVertice(MG);
        Vertice ES = new Vertice("ES");
        grafo.adicionarVertice(ES);
        Vertice SP = new Vertice("SP");
        grafo.adicionarVertice(SP);
        Vertice RJ = new Vertice("RJ");
        grafo.adicionarVertice(RJ);

        //SUL
        Vertice PR = new Vertice("PR");
        grafo.adicionarVertice(PR);
        Vertice SC = new Vertice("SC");
        grafo.adicionarVertice(SC);
        Vertice RS = new Vertice("RS");
        grafo.adicionarVertice(RS);

        //NORTE
        grafo.adicionarAresta(new Aresta("RR-AM", RR, AM,666));
        grafo.adicionarAresta(new Aresta("RR-PA", RR, PA, 1437));
        grafo.adicionarAresta(new Aresta("AM-AC", AM, AC, 1303));
        grafo.adicionarAresta(new Aresta("AM-PA", AM, PA,1293));
        grafo.adicionarAresta(new Aresta("AM-RO", AM, RO, 1003));
        grafo.adicionarAresta(new Aresta("AC-RO", AC, RO,743));
        grafo.adicionarAresta(new Aresta("AP-PA", AP, PA, 334));
        grafo.adicionarAresta(new Aresta("PA-TO", PA, TO, 971));

        //NORDESTE
        grafo.adicionarAresta(new Aresta("MA-TO", MA, TO, 961));
        grafo.adicionarAresta(new Aresta("MA-PA", MA, PA, 479));
        grafo.adicionarAresta(new Aresta("MA-PI", MA, PI, 329));
        grafo.adicionarAresta(new Aresta("PI-CE", PI, CE, 654));
        grafo.adicionarAresta(new Aresta("PI-PE", PI, PE,1210));
        grafo.adicionarAresta(new Aresta("PI-BA", PI, BA, 1029));
        grafo.adicionarAresta(new Aresta("CE-RN", CE, RN,434));
        grafo.adicionarAresta(new Aresta("CE-PB", CE, PB, 552));
        grafo.adicionarAresta(new Aresta("CE-PE", CE, PE, 628));
        grafo.adicionarAresta(new Aresta("RN-PB", RN, PB, 151));
        grafo.adicionarAresta(new Aresta("PB-PE", PB, PE, 105));
        grafo.adicionarAresta(new Aresta("PE-AL", PE, AL,199));
        grafo.adicionarAresta(new Aresta("PE-BA", PE, BA, 670));
        grafo.adicionarAresta(new Aresta("AL-SE", AL, SE, 204));
        grafo.adicionarAresta(new Aresta("BA-TO", AL, BA, 1117));
        grafo.adicionarAresta(new Aresta("SE-BA", SE, BA, 199));
        grafo.adicionarAresta(new Aresta("PI-TO", PI, TO,1387 ));

        //CENTRO-OESTE
        grafo.adicionarAresta(new Aresta("MT-RO", MT, RO, 939));
        grafo.adicionarAresta(new Aresta("MT-AM", MT, AM,1593));
        grafo.adicionarAresta(new Aresta("MT-PA", MT, PA, 1801));
        grafo.adicionarAresta(new Aresta("MT-TO", MT, TO, 976));
        grafo.adicionarAresta(new Aresta("MT-MS", MT, MS, 563));
        grafo.adicionarAresta(new Aresta("MT-GOm", MT, GO, 741));
        grafo.adicionarAresta(new Aresta("GO-TO", GO, TO, 1035));
        grafo.adicionarAresta(new Aresta("GO-MG", GO, MG, 666));
        grafo.adicionarAresta(new Aresta("GO-DF", GO, DF, 164));
        grafo.adicionarAresta(new Aresta("MS-SP", MS, SP, 891));
        grafo.adicionarAresta(new Aresta("MS-PR", MS, PR, 477));

        //SUDESTE
        grafo.adicionarAresta(new Aresta("MG-ES", MG, ES, 383));
        grafo.adicionarAresta(new Aresta("MG-RJ", MG, RJ, 341));
        grafo.adicionarAresta(new Aresta("MG-SP", MG, SP, 490));
        grafo.adicionarAresta(new Aresta("SP-RJ", SP, RJ, 357));
        grafo.adicionarAresta(new Aresta("SP-PR", SP, PR, 362));

        //SUL
        grafo.adicionarAresta(new Aresta("PR-SC", PR, SC, 362));
        grafo.adicionarAresta(new Aresta("SC-RS", SC, RS, 376));




        grafo.verificaAdjacencias();
        grafo.printaMatrizAdjacencias();
        //grafo.printaMatrizIncidencia();
        System.out.println(grafo);
        grafo.maiorGrau();
        List<Vertice> menorCaminho = new ArrayList<>();
        int distancia = grafo.distanciaDijkstra(PB, TO, menorCaminho);

        if (distancia != -1) {
            System.out.println("Menor caminho encontrado com distância: " + distancia+"km");
            System.out.println("Caminho: " + menorCaminho);
        } else {
            System.out.println("Não foi possível encontrar um caminho do vértice de origem para o vértice de destino.");
        }

        grafo.pilhaDFS("PR","TO");

    }
}