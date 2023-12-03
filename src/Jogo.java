import java.util.HashMap;
import java.util.Random;

public class Jogo {
    private static Jogo instance;
    private Barbaro barbaro;
    private Comando comando;
    private Ambiente ambienteAtual;
    private HashMap<String, Ambiente> ambientes;
    private boolean fimDeJogo;


    private Jogo() {
        barbaro = new Barbaro();
        fimDeJogo = false;
        ambientes = new HashMap<String, Ambiente>();
    }

    public static Jogo getInstance() {
        if (instance == null)
            instance = new Jogo();
        return instance;
    }

    public void iniciarJogo() {
        inicializarAmbientes();
        inicializarItens();
        imprimirBoasVindas();
        jogar();
    }

    public void inicializarItens(){
        Random gerador = new Random();
        String[] nomeAmbientes = ambientes.keySet().toArray(new String[ambientes.size()]);

        Ambiente tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Machado machado = new Machado("Machado de Guerra", tempAmbiente.getNome());
        tempAmbiente.adicionarItem(machado);

        tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item dica1 = new Item("DicaMachado", "O machado está em " + machado.getLocalizacao(), tempAmbiente.getNome());
        tempAmbiente.adicionarItem(dica1);

        tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item dica2 = new Item("Dica2", "Descrição", tempAmbiente.getNome());
        tempAmbiente.adicionarItem(dica2);

        tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item dica3 = new Item("Dica3", "Descrição", tempAmbiente.getNome());
        tempAmbiente.adicionarItem(dica3);

        tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item dica4 = new Item("Dica4", "Descrição", tempAmbiente.getNome());
        tempAmbiente.adicionarItem(dica4);
    }

    public void inicializarAmbientes() {

        Ambiente ghanor = new Ambiente("Reino de Ghanor");
        Ambiente floresta = new Ambiente("Floresta Alta");
        Ambiente rochaCeleste = new Ambiente("Rocha Celeste");
        Ambiente kottar = new Ambiente("Reino de Kottar");
        Ambiente vila = new Ambiente("Vila do Sacrilégio");
        Ambiente castelo = new Ambiente("Castelo do Outono Eterno");
        Ambiente lago = new Ambiente("Lago de Po");
        Ambiente abismo = new Ambiente("Abismo Sereno");
        Ambiente rochaNegra = new Ambiente("Rocha Negra");
        Ambiente praia = new Ambiente("Praia dos Mortos");
        Ambiente pantano = new Ambiente("Pantanos Cinzentos");

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

        ambientes.put(ghanor.getNome(), ghanor);
        ambientes.put(floresta.getNome(), floresta);
        ambientes.put(rochaCeleste.getNome(), rochaCeleste);
        ambientes.put(kottar.getNome(), kottar);
        ambientes.put(vila.getNome(), vila);
        ambientes.put(castelo.getNome(), castelo);
        ambientes.put(lago.getNome(), lago);
        ambientes.put(abismo.getNome(), abismo);
        ambientes.put(rochaNegra.getNome(), rochaNegra);
        ambientes.put(praia.getNome(), praia);
        ambientes.put(pantano.getNome(), pantano);

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
        
        else if (palavraDeComando.equals("pegar"))
            pegarItem(comando.getSegundaPalavra());

        else if (palavraDeComando.equals("bolsa"))
            barbaro.olharBolsa();

        else if (palavraDeComando.equals("descricao"))
            barbaro.lerDescricaoItem(comando.getSegundaPalavra());
    }


    private void pegarItem(String nomeItem) {
        Item item = ambienteAtual.getItem(nomeItem);
        if(item != null){
            barbaro.adicionarItem(ambienteAtual.getItem(nomeItem));
            ambienteAtual.removerItem(nomeItem);
            System.out.println("Você pegou o item " + nomeItem + "!");
        }
        else
            System.out.println("Esse item não está nesse ambiente!");
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

        if(proximoAmbiente == null)
            System.out.println("Essa saída não existe!");

        else {
            ambienteAtual = proximoAmbiente;
            barbaro.andar();
            if(ambienteAtual.getInfestado()){
                barbaro.derrotarMonstro();
                if(barbaro.temMachado())
                    System.out.println("Você derrotou um monstro com seu machado!");
                else
                    System.out.println("Você derrotou um monstro com suas mãos!");
            }
            if(! barbaroMorreu()){
                System.out.println("Energia restante: " + barbaro.getEnergia());
                System.out.println(ambienteAtual.getNome());
                if(ambienteAtual.quantidadeItens() > 0)
                    System.out.println("Você encontra os seguintes itens: " + ambienteAtual.getItens());
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
