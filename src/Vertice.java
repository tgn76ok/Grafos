
import java.util.ArrayList;
import java.util.List;

public class Vertice {
  private String nome;
  private List<Vertice> adjacencias;
  private int grau = 0;

  public Vertice(String nome) {
    this.nome = nome;
    this.adjacencias = new ArrayList();
  }

  public String toString() {
    return "Vertice " + this.nome + " com grau " + this.grau;
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof Vertice)) {
      return false;
    } else {
      Vertice other = (Vertice) o;
      if (!other.canEqual(this)) {
        return false;
      } else if (this.getGrau() != other.getGrau()) {
        return false;
      } else {
        Object this$nome = this.getNome();
        Object other$nome = other.getNome();
        if (this$nome == null) {
          if (other$nome != null) {
            return false;
          }
        } else if (!this$nome.equals(other$nome)) {
          return false;
        }

        Object this$adjacencias = this.getAdjacencias();
        Object other$adjacencias = other.getAdjacencias();
        if (this$adjacencias == null) {
          if (other$adjacencias != null) {
            return false;
          }
        } else if (!this$adjacencias.equals(other$adjacencias)) {
          return false;
        }

        return true;
      }
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof Vertice;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  // Getters e Setters para 'adjacencias'
  public List<Vertice> getAdjacencias() {
    return adjacencias;
  }

  public void setAdjacencias(List<Vertice> adjacencias) {
    this.adjacencias = adjacencias;
  }

  // Getter e Setter para 'grau'
  public int getGrau() {
    return grau;
  }

  public void setGrau(int grau) {
    this.grau = grau;
  }
}
