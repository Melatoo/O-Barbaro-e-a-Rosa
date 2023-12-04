package Itens;

/*Feito por: Thiago Melato e Davi Siqueira*/
public class Dica extends Item {
    public Dica(String nome, String descricao, String localizacao) {
        super(nome, descricao, localizacao);
    }

    
    /** 
     * @return String mensagem escrita na dica
     */
    public String usar() {
        return "Dica: " + getDescricao();
    }
}
