public class Machado extends Item {
    private int durabilidade;

    public Machado(String descricao, String localizacao) {
        super("Machado", descricao, localizacao);
        durabilidade = 3;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    @Override
    public String usar() {
        durabilidade--;
        return "Você usou o machado";
    }
}
