import java.util.HashMap;

public class Ambiente {
    private String nome;
    private HashMap<String, Ambiente> saidas;

    public Ambiente(String nome) {
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }
}
