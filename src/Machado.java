public class Machado extends Item{
  private int durabilidade;

  public Machado(String descricao, String localizacao) {
    super("machado", descricao, localizacao);
    durabilidade = 3;
  }
}
