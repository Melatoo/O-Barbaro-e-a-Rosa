package Itens;
public abstract class Item {
    private String nome;
    private String descricao;
    private String localizacao;

    public Item(String nome, String descricao, String localizacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome.toLowerCase();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public abstract String usar();
}
