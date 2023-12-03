package Itens;
public class Machado extends Item {
    private int durabilidade;

    public Machado(String descricao, String localizacao) {
        super("Machado", descricao, localizacao);
        durabilidade = 3;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    /*
     * usa o machado, diminuindo sua durabilidade
     * 
     * @return String mensagem de uso do machado
     */

    @Override
    public String usar() {
        durabilidade--;
        return "Você usou o machado";
    }
}
