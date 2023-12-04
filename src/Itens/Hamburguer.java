package Itens;

/*Feito por: Thiago Melato e Davi Siqueira*/
public class Hamburguer extends Item {
    public Hamburguer(String localizacao) {
        super("Hamburguer", "Um hamburguer muito gostoso", localizacao);
    }

    
    /** 
     * @return String mensagem de uso do hamburguer
     */
    @Override
    public String usar() {
        return "Você comeu o hamburguer";
    }
}
