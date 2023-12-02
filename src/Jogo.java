import java.util.HashMap;
import java.util.Random;

public class Jogo {
    private static Jogo instance = null;
    private Barbaro barbaro;
    private Comando comando;
    private Ambiente ambienteAtual;
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
        barbaro = new Barbaro();
        fimDeJogo = false;
        inicializarAmbientes();
        imprimirBoasVindas();
    }

    public void inicializarAmbientes() {
        Ambiente ghanor, floresta, rochaCeleste, kottar, vila, castelo, lago, abismo, rochaNegra, praia, pantano;
        Random random = new Random();
        ghanor = new Ambiente("Reino de Ghanor", random.nextInt(5));
        floresta = new Ambiente("Floresta Alta", random.nextInt(5));
        rochaCeleste = new Ambiente("Rocha Celeste", random.nextInt(5));
        kottar = new Ambiente("Reino de Kottar", random.nextInt(5));
        vila = new Ambiente("Vila do Sacrilégio", random.nextInt(5));
        castelo = new Ambiente("Castelo do Outono Eterno", random.nextInt(5));
        lago = new Ambiente("Lago de Po", random.nextInt(5));
        abismo = new Ambiente("Abismo Sereno", random.nextInt(5));
        rochaNegra = new Ambiente("Rocha Negra", random.nextInt(5));
        praia = new Ambiente("Praia dos Mortos", random.nextInt(5));
        pantano = new Ambiente("Pantanos Cinzentos", random.nextInt(5));

        ghanor.ajustarSaidas(kottar, praia, floresta, castelo);
        floresta.ajustarSaidas(ghanor, rochaNegra, null, rochaCeleste);
        rochaCeleste.ajustarSaidas(null, floresta, null, null);
        kottar.ajustarSaidas(null, vila, ghanor, pantano);
        vila.ajustarSaidas(null, null, praia, kottar);
        castelo.ajustarSaidas(null, ghanor, null, lago);
        lago.ajustarSaidas(null, castelo, abismo, null);
        abismo.ajustarSaidas(lago, null, null, null);
        rochaNegra.ajustarSaidas(praia, null, null, floresta);
        praia.ajustarSaidas(vila, null, rochaNegra, ghanor);
        pantano.ajustarSaidas(null, kottar, null, null);

        

        ambienteAtual = ghanor;
    }
    
    public void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem-vindo ao mundo de Ghanor!");
        System.out.println("Ghanor é um mundo de fantasia medieval, cheio de magia e aventuras.");
        System.out.println("Digite 'ajuda' se você precisar de ajuda.");
        System.out.println();
        System.out.println(ambienteAtual.getNome());
        System.out.print("Saídas: ");
        String saidas = ambienteAtual.getSaidas();
        System.out.println(saidas);
    }

    public void jogar() {
        while (!fimDeJogo) {
            comando = Analisador.analisarComando();
            processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Até mais!");
    }

    public void processarComando(Comando comando) {
        
    }

}
