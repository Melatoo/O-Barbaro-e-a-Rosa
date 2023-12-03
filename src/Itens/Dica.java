package Itens;

public class Dica extends Item {
    public Dica(String nome, String descricao, String localizacao) {
        super(nome, descricao, localizacao);
    }

    public String usar() {
        return "Dica: " + getDescricao();
    }
}
