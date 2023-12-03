public class Dica extends Item {
    Dica(String nome, String descricao, String localizacao) {
        super(nome, descricao, localizacao);
    }

    public String usar() {
        return "Dica: " + getDescricao();
    }
}
