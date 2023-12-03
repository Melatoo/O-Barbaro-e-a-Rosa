public class Machado extends Item {
  private int durabilidade;

  public Machado(String descricao, String localizacao) {
    super("Machado", descricao, localizacao);
    durabilidade = 3;
  }

  public int getDurabilidade() {
    return durabilidade;
  }

  public void usar() {
    durabilidade--;
  }
}
