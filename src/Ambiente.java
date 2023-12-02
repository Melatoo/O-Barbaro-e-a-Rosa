import java.util.HashMap;

public class Ambiente {
    private String nome;
    private boolean infestado;
    private HashMap<String, Ambiente> saidas;
    

    public Ambiente(String nome, int random) {
        saidas = new HashMap<String, Ambiente>();
        this.nome = nome;
        this.infestado = random == 1;
    }

    public void ajustarSaidas(Ambiente norte, Ambiente leste, Ambiente sul, Ambiente oeste) {
        saidas.put("norte", norte);
        saidas.put("leste", leste);
        saidas.put("sul", sul);
        saidas.put("oeste", oeste);
    }

    public void getSaida(String direcao) {
        saidas.get(direcao);
    }

    public String getSaidas() {
        String saidas = "";
        for (String saida : this.saidas.keySet()) {
            saidas += saida + " ";
        }
        return saidas;
    }
}
