import java.util.*;

public class Grafo {
  private List<Vertice> vertices;
  private List<Aresta> arestas;
  private boolean eDirecionado;
  private int ordem = 0;
  private int tamanho = 0;
  private int[][] matrizAdjacencia;
  private int[][] matrizIncidencia;

  public Grafo(List<Vertice> vertices, List<Aresta> arestas, boolean eDirecionado, int ordem, int tamanho,
      int[][] matrizAdjacencia, int[][] matrizIncidencia) {
    this.vertices = vertices;
    this.arestas = arestas;
    this.eDirecionado = eDirecionado;
    this.ordem = ordem;
    this.tamanho = tamanho;
    this.matrizAdjacencia = matrizAdjacencia;
    this.matrizIncidencia = matrizIncidencia;
  }

  public Grafo() {
    this.vertices = new ArrayList();
    this.arestas = new ArrayList();
  }

  public Grafo(boolean eDirecionado) {
    this.vertices = new ArrayList();
    this.arestas = new ArrayList();
    this.eDirecionado = eDirecionado;
  }

  public void maiorGrau() {
    int maiorGrau = Integer.MIN_VALUE;
    List<Vertice> verticesMaiorGrau = new ArrayList<>();

    // Encontrar o maior grau entre os vértices
    for (Vertice vertice : vertices) {
      if (vertice.getGrau() > maiorGrau) {
        maiorGrau = vertice.getGrau();
        verticesMaiorGrau.clear();
        verticesMaiorGrau.add(vertice);
      } else if (vertice.getGrau() == maiorGrau) {
        verticesMaiorGrau.add(vertice);
      }
    }

    // Imprimir os vértices com o maior grau
    if (verticesMaiorGrau.size() == 1) {
      System.out.println("O vértice de maior grau é: " + verticesMaiorGrau.get(0).getNome() + " com grau " + maiorGrau);
    } else {
      System.out.println("Os vértices de maior grau são:");
      for (Vertice vertice : verticesMaiorGrau) {
        System.out.println(vertice.getNome() + " com grau " + maiorGrau);
      }
    }
  }
  public void pilhaDFS(String estadoInicial) {
    Map<String, Vertice> mapaEstados = new HashMap<>();
    for (Vertice vertice : vertices) {
      mapaEstados.put(vertice.getNome(), vertice);
    }

    boolean[] visitados = new boolean[vertices.size()];
    Stack<Vertice> pilha = new Stack<>();
    pilha.push(mapaEstados.get(estadoInicial));
    visitados[vertices.indexOf(mapaEstados.get(estadoInicial))] = true;

    while (!pilha.isEmpty()) {
      Vertice topo = pilha.pop();
      System.out.print(topo.getNome() + " ");

      for (Vertice vizinho : topo.getAdjacencias()) {
        if (!visitados[vertices.indexOf(vizinho)]) {
          visitados[vertices.indexOf(vizinho)] = true;
          pilha.push(vizinho);
        }
      }
    }
  }
  public void pilhaDFS(String estadoInicial, String estadoFim) {
    Map<String, Vertice> mapaEstados = new HashMap<>();
    for (Vertice vertice : vertices) {
      mapaEstados.put(vertice.getNome(), vertice);
    }

    boolean[] visitados = new boolean[vertices.size()];
    Stack<Vertice> pilha = new Stack<>();
    pilha.push(mapaEstados.get(estadoInicial));
    visitados[vertices.indexOf(mapaEstados.get(estadoInicial))] = true;

    while (!pilha.isEmpty()) {
      Vertice topo = pilha.pop();
      System.out.print(topo.getNome() + " ");

      if (topo.getNome().equals(estadoFim)) {
        System.out.println("\nCaminho encontrado!");
        return; // Retorna quando o estado final é alcançado
      }

      for (Vertice vizinho : topo.getAdjacencias()) {
        if (!visitados[vertices.indexOf(vizinho)]) {
          visitados[vertices.indexOf(vizinho)] = true;
          pilha.push(vizinho);
        }
      }
    }

    System.out.println("\nCaminho não encontrado!");
  }

  public int distanciaDijkstra(Vertice origem, Vertice destino, List<Vertice> menorCaminho) {
    Map<Vertice, Integer> distancias = new HashMap<>();
    Map<Vertice, Vertice> predecessores = new HashMap<>(); // Mapa para rastrear predecessores no caminho
    Set<Vertice> visitados = new HashSet<>();
    PriorityQueue<Vertice> fila = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

    for (Vertice vertice : vertices) {
      distancias.put(vertice, Integer.MAX_VALUE);
    }
    distancias.put(origem, 0);
    fila.offer(origem);

    while (!fila.isEmpty()) {
      Vertice atual = fila.poll();
      visitados.add(atual);

      if (atual.equals(destino)) {
        reconstruirCaminho(origem, destino, predecessores, menorCaminho); // Reconstrói o caminho mais curto
        return this.modulo(distancias.get(atual)); // Retorna a distância até o destino
      }

      for (Vertice adjacente : atual.getAdjacencias()) { // Itera sobre os vértices adjacentes
        int pesoAresta = calcularPesoAresta(atual, adjacente); // Calcula o peso da aresta
        int novaDistancia = distancias.get(atual) + pesoAresta;
        if (!visitados.contains(adjacente) && novaDistancia < distancias.get(adjacente)) {
          distancias.put(adjacente, novaDistancia);
          predecessores.put(adjacente, atual); // Atualiza o predecessor no caminho
          fila.offer(adjacente);
        }
      }
    }

    // Se o destino não for alcançável a partir da origem, retorna um valor
    // indicando isso
    return -1;
  }

  private void reconstruirCaminho(Vertice origem, Vertice destino, Map<Vertice, Vertice> predecessores,
      List<Vertice> caminho) {
    Vertice atual = destino;
    while (!atual.equals(origem)) {
      caminho.add(0, atual); // Adiciona o vértice ao início da lista para manter a ordem do caminho
      atual = predecessores.get(atual);
    }
    caminho.add(0, origem); // Adiciona o vértice de origem ao início do caminho
  }

  public int calcularPesoAresta(Vertice origem, Vertice destino) {
    for (Aresta aresta : arestas) {
      if (aresta.getOrigem().equals(origem) && aresta.getDestino().equals(destino)) {
        return aresta.getPeso(); // Retorna o peso da aresta que conecta os vértices origem e destino
      }
    }
    return Integer.MAX_VALUE; // Se não encontrar a aresta, retorna um valor máximo
  }

  public List<Aresta> buscarArestasPorVertices(Vertice origem, Vertice destino) {
    List<Aresta> arestasEncontradas = new ArrayList<>();

    for (Aresta aresta : arestas) {
      if ((aresta.getOrigem().equals(origem) && aresta.getDestino().equals(destino)) ||
          (this.eDirecionado && aresta.getOrigem().equals(destino) && aresta.getDestino().equals(origem))) {
        arestasEncontradas.add(aresta);
      }
    }

    return arestasEncontradas;
  }

  public void adicionarVertice(Vertice vertice) {
    if (vertice == null) {
      System.out.println("Vértice nulo não pode ser adicionado!");
    } else if (this.vertices.contains(vertice)) {
      System.out.println("Vértice " + vertice.getNome() + " já existe!");
    } else if (vertice.getNome().isEmpty()) {
      System.out.println("Vértice sem nome não pode ser adicionado!");
    } else {
      this.vertices.add(vertice);
      ++this.ordem;
      // System.out.println("Vértice " + vertice.getNome() + " adicionado com
      // sucesso!");
    }

  }

  public void adicionarAresta(Aresta aresta) {
    if (aresta == null) {
      System.out.println("Aresta " + aresta.getNome() + " é nula não pode ser adicionada!");
    } else if (this.arestas.contains(aresta)) {
      System.out.println("Aresta " + aresta.getNome() + " já existe!");
    } else if (aresta.getNome().isEmpty()) {
      System.out.println("Aresta sem nome não pode ser adicionada!");
    } else if (this.vertices.contains(aresta.getOrigem()) && this.vertices.contains(aresta.getDestino())) {
      if (!this.eDirecionado) {
        this.descobreSeEDigrafo(aresta);
      }

      this.arestas.add(aresta);
      ++this.tamanho;
      aresta.getOrigem().setGrau(aresta.getOrigem().getGrau() + 1);
      aresta.getDestino().setGrau(aresta.getDestino().getGrau() + 1);
      // System.out.println("Aresta " + aresta.getNome() + " adicionada com
      // sucesso!");
    } else {
      System.out.println("Vértice de origem ou destino da aresta" + aresta.getNome() + " não existe!");
    }

  }

  private void descobreSeEDigrafo(Aresta aresta) {
    if (aresta.getOrigem() == aresta.getDestino()) {
      this.eDirecionado = true;
    } else {
      Iterator var2 = this.arestas.iterator();

      while (var2.hasNext()) {
        Aresta arestaSalva = (Aresta) var2.next();
        if (aresta.getOrigem() == arestaSalva.getDestino() && aresta.getDestino() == arestaSalva.getOrigem()) {
          this.eDirecionado = true;
          break;
        }
      }
    }

  }

  public void verificaAdjacencias() {
    this.matrizAdjacencia = new int[this.ordem][this.ordem];
    this.matrizIncidencia = new int[this.ordem][this.tamanho];
    Map<String, Integer> verticesIndices = new HashMap();

    int aux;
    for (aux = 0; aux < this.ordem; ++aux) {
      verticesIndices.put(((Vertice) this.vertices.get(aux)).getNome(), aux);
    }

    aux = 0;
    Iterator var3;
    Aresta aresta;
    Vertice origem;
    Vertice destino;
    if (this.eDirecionado) {
      for (var3 = this.arestas.iterator(); var3.hasNext(); ++aux) {
        aresta = (Aresta) var3.next();
        origem = aresta.getOrigem();
        destino = aresta.getDestino();
        if (!destino.getAdjacencias().contains(origem)) {
          destino.getAdjacencias().add(origem);
          this.matrizAdjacencia[(Integer) verticesIndices.get(origem.getNome())][(Integer) verticesIndices
              .get(destino.getNome())] = 1;
        }

        this.matrizIncidencia[(Integer) verticesIndices.get(origem.getNome())][aux] = -1;
        this.matrizIncidencia[(Integer) verticesIndices.get(destino.getNome())][aux] = 1;
      }
    } else {
      for (var3 = this.arestas.iterator(); var3.hasNext(); ++aux) {
        aresta = (Aresta) var3.next();
        origem = aresta.getOrigem();
        destino = aresta.getDestino();
        origem.getAdjacencias().add(destino);
        this.matrizAdjacencia[(Integer) verticesIndices.get(origem.getNome())][(Integer) verticesIndices
            .get(destino.getNome())] = 1;
        destino.getAdjacencias().add(origem);
        this.matrizAdjacencia[(Integer) verticesIndices.get(destino.getNome())][(Integer) verticesIndices
            .get(origem.getNome())] = 1;
        this.matrizIncidencia[(Integer) verticesIndices.get(origem.getNome())][aux] = 1;
        this.matrizIncidencia[(Integer) verticesIndices.get(destino.getNome())][aux] = 1;
      }
    }

  }

  public int modulo(int valor) {

    if (valor < 0) {

      return valor * -1;
    } else {
      return valor;

    }
  }

  public void printaMatrizAdjacencias() {
    StringBuilder matriz = new StringBuilder("\nMatriz de adjacências\n");
    matriz.append("  ");

    int i;
    for (i = 0; i < this.ordem; ++i) {
      matriz.append(((Vertice) this.vertices.get(i)).getNome()).append("  ");
    }

    matriz.append("\n");

    for (i = 0; i < this.ordem; ++i) {
      matriz.append(((Vertice) this.vertices.get(i)).getNome()).append(" ");

      for (int j = 0; j < this.ordem; ++j) {
        matriz.append(this.matrizAdjacencia[i][j]).append("   ");
      }

      matriz.append("\n");
    }

    System.out.printf(matriz.toString());
  }

  public void printaMatrizIncidencia() {
    String espacamento = this.eDirecionado ? "   " : " ";
    StringBuilder matriz = new StringBuilder("\nMatriz de incidência\n");
    matriz.append("  ");

    int i;
    for (i = 0; i < this.tamanho; ++i) {
      matriz.append(((Aresta) this.arestas.get(i)).getNome()).append(espacamento);
    }

    matriz.append("\n");

    for (i = 0; i < this.ordem; ++i) {
      matriz.append(((Vertice) this.vertices.get(i)).getNome()).append(espacamento);

      for (int j = 0; j < this.tamanho; ++j) {
        matriz.append(this.matrizIncidencia[i][j]).append(espacamento);
      }

      matriz.append("\n");
    }

    System.out.printf(matriz.toString());
  }

  // get e set

  public List<Vertice> getVertices() {
    return this.vertices;
  }

  public List<Aresta> getArestas() {
    return this.arestas;
  }

  public boolean isEDirecionado() {
    return this.eDirecionado;
  }

  public int getOrdem() {
    return this.ordem;
  }

  public int getTamanho() {
    return this.tamanho;
  }

  public int[][] getMatrizAdjacencia() {
    return this.matrizAdjacencia;
  }

  public int[][] getMatrizIncidencia() {
    return this.matrizIncidencia;
  }

  public void setVertices(List<Vertice> vertices) {
    this.vertices = vertices;
  }

  public void setArestas(List<Aresta> arestas) {
    this.arestas = arestas;
  }

  public void setEDirecionado(boolean eDirecionado) {
    this.eDirecionado = eDirecionado;
  }

  public void setOrdem(int ordem) {
    this.ordem = ordem;
  }

  public void setTamanho(int tamanho) {
    this.tamanho = tamanho;
  }

  public void setMatrizAdjacencia(int[][] matrizAdjacencia) {
    this.matrizAdjacencia = matrizAdjacencia;
  }

  public void setMatrizIncidencia(int[][] matrizIncidencia) {
    this.matrizIncidencia = matrizIncidencia;
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof Grafo)) {
      return false;
    } else {
      Grafo other = (Grafo) o;
      if (!other.canEqual(this)) {
        return false;
      } else if (this.isEDirecionado() != other.isEDirecionado()) {
        return false;
      } else if (this.getOrdem() != other.getOrdem()) {
        return false;
      } else if (this.getTamanho() != other.getTamanho()) {
        return false;
      } else {
        Object this$vertices = this.getVertices();
        Object other$vertices = other.getVertices();
        if (this$vertices == null) {
          if (other$vertices != null) {
            return false;
          }
        } else if (!this$vertices.equals(other$vertices)) {
          return false;
        }

        label43: {
          Object this$arestas = this.getArestas();
          Object other$arestas = other.getArestas();
          if (this$arestas == null) {
            if (other$arestas == null) {
              break label43;
            }
          } else if (this$arestas.equals(other$arestas)) {
            break label43;
          }

          return false;
        }

        if (!Arrays.deepEquals(this.getMatrizAdjacencia(), other.getMatrizAdjacencia())) {
          return false;
        } else if (!Arrays.deepEquals(this.getMatrizIncidencia(), other.getMatrizIncidencia())) {
          return false;
        } else {
          return true;
        }
      }
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof Grafo;
  }

  public String toString() {
    String isDirecionado = this.eDirecionado ? "direcionado" : "não direcionado";
    return "Grafo " + isDirecionado + " com " + this.ordem + " vértices e " + this.tamanho + " arestas:\nVértices:\n"
        + this.vertices + "\nArestas:\n" + this.arestas;
  }

}