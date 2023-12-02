import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private static Jogo instance;
    private Barbaro barbaro;
    private Comando comando;
    private Ambiente ambienteAtual;
    private boolean fimDeJogo;


    private Jogo() {
        barbaro = new Barbaro();
        fimDeJogo = false;
    }

    public static Jogo getInstance() {
        if (instance == null)
            instance = new Jogo();
        return instance;
    }

    public void iniciarJogo() {
        inicializarAmbientes();
        imprimirBoasVindas();
        jogar();
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
        System.out.println("Você é um bárbaro que está em uma jornada para a rosa que salvará seus amigos.");
        System.out.println("Você precisa encontrar a rosa antes que seja tarde demais.");
        System.out.println("Você tem " +  barbaro.getEnergia() + " de energia.");
        ComandosConhecidos.mostrarComandos();
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

    private void processarComando(Comando comando) {
        if(comando.ehDesconhecido())
            System.out.println("Eu não entendi o que você disse...");

        String palavraDeComando = comando.getPalavraComando();

        if (palavraDeComando.equals("ajuda"))
            imprimirAjuda();

        else if (palavraDeComando.equals("ir"))
            irParaAmbiente(comando);

        else if (palavraDeComando.equals("sair"))
            fimDeJogo = true;

        else if (palavraDeComando.equals("observar"))
            observar();
    }

    private void observar() {
        System.out.println("Você está em " + ambienteAtual.getNome());
        System.out.println("Saídas: " + ambienteAtual.getSaidas());
    }

    private void imprimirAjuda(){
        System.out.println("Você está perdido. Você está só. Você caminha pela floresta.");
        System.out.println("Suas palavras de comando são:");
        ComandosConhecidos.mostrarComandos();
    }

    private void irParaAmbiente(Comando comando){
        if (!comando.temSegundaPalavra()) {
            System.out.println("Ir para onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if(proximoAmbiente == null) {
            System.out.println("Essa saída não existe!");
        }
        else {
            ambienteAtual = proximoAmbiente;
            barbaro.andar();
            if(! barbaroMorreu()){
                System.out.println("Energia restante: " + barbaro.getEnergia());
                System.out.println(ambienteAtual.getNome());
                System.out.print("Saídas: ");
                String saidas = ambienteAtual.getSaidas();
                System.out.println(saidas);
            }
        }
    }

    private boolean barbaroMorreu(){
        if(barbaro.getEnergia() <= 0) {
            System.out.println("Você morreu!");
            fimDeJogo = true;
            return true;
        }
        return false;
    }
}
