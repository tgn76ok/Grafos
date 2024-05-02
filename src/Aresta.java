public class Aresta {
    private String nome;
    private Vertice origem;
    private Vertice destino;
    private int peso;
  
    public Aresta(String nome, Vertice origem, Vertice destino, int peso) {
      this.nome = nome;
      this.origem = origem;
      this.destino = destino;
      this.peso = peso;
    }
  
    public String toString() {
      String nome = this.nome.isEmpty() ? ":" : this.nome + ": ";
      return "\nA distancia entre " + nome + this.getPeso() + "km ";
    }
  
    public String getNome() {
      return this.nome;
    }
  
    public Vertice getOrigem() {
      return this.origem;
    }
  
    public Vertice getDestino() {
      return this.destino;
    }
  
    public int getPeso() {
      return this.peso;
    }
  
    public void setNome(String nome) {
      this.nome = nome;
    }
  
    public void setOrigem(Vertice origem) {
      this.origem = origem;
    }
  
    public void setDestino(Vertice destino) {
      this.destino = destino;
    }
  
    public void setPeso(int peso) {
      this.peso = peso;
    }
  
    protected boolean canEqual(Object other) {
      return other instanceof Aresta;
    }
  
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      } else if (!(o instanceof Aresta)) {
        return false;
      } else {
        Aresta other = (Aresta) o;
        if (!other.canEqual(this)) {
          return false;
        } else {
          label47: {
            Object this$nome = this.getNome();
            Object other$nome = other.getNome();
            if (this$nome == null) {
              if (other$nome == null) {
                break label47;
              }
            } else if (this$nome.equals(other$nome)) {
              break label47;
            }
  
            return false;
          }
  
          Object this$origem = this.getOrigem();
          Object other$origem = other.getOrigem();
          if (this$origem == null) {
            if (other$origem != null) {
              return false;
            }
          } else if (!this$origem.equals(other$origem)) {
            return false;
          }
  
          Object this$destino = this.getDestino();
          Object other$destino = other.getDestino();
          if (this$destino == null) {
            if (other$destino != null) {
              return false;
            }
          } else if (!this$destino.equals(other$destino)) {
            return false;
          }
  
          return true;
        }
      }
    }
  
    public Aresta(String nome, Vertice origem, Vertice destino) {
      this.nome = nome;
      this.origem = origem;
      this.destino = destino;
    }
  }
  