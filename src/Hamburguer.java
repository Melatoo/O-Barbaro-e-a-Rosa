public class Hamburguer extends Item {
    public Hamburguer(String localizacao) {
        super("Hamburguer", "Um hamburguer muito gostoso", localizacao);
    }

    @Override
    public void usar() {
        System.out.println("Você comeu o hamburguer");
    }
}
