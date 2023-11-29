import java.util.HashMap;

public class Jogo {
    private static Jogo instance = null;
    private HashMap <String, Ambiente> locais;
    private Barbaro barbaro;
    private Comando comando;
    private boolean fimDeJogo;


    private Jogo() {
    }

    public static Jogo getInstance() {
        if (instance == null) {
            instance = new Jogo();
        }
        return instance;
    }

    public void iniciarJogo() {
        locais = new HashMap<String, Ambiente>();
        barbaro = new Barbaro();
        fimDeJogo = false;
    }

    public void inicializarAmbientes() {
       
    }
}
