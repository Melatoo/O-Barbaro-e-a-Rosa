package Itens;
public class Hamburguer extends Item {
    public Hamburguer(String localizacao) {
        super("Hamburguer", "Um hamburguer muito gostoso", localizacao);
    }

    @Override
    public String usar() {
        return "Você comeu o hamburguer";
    }
}
