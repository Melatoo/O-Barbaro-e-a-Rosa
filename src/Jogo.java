import java.util.HashMap;
import java.util.Random;

public class Jogo {
    private static Jogo instance;
    private Barbaro barbaro;
    private Comando comando;
    private Ambiente ambienteAtual;
    private Ambiente ambienteComRosa;
    private InterfaceDeUsuario interfaceDeUsuario;
    private HashMap<String, Ambiente> ambientes;
    private boolean fimDeJogo;

    private Jogo() {
        barbaro = new Barbaro();
        fimDeJogo = false;
        ambientes = new HashMap<String, Ambiente>();
        interfaceDeUsuario = new InterfaceDeUsuario();
    }

    public static Jogo getInstance() {
        if (instance == null)
            instance = new Jogo();
        return instance;
    }

    public void iniciarJogo() {
        interfaceDeUsuario.setVisible(true);
        inicializarAmbientes();
        inicializarItens();
        imprimirBoasVindas();
        jogar();
    }

    public void inicializarItens() {
        Random gerador = new Random();
        String[] nomeAmbientes = ambientes.keySet().toArray(new String[ambientes.size()]);

        Ambiente tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item machado = new Machado("Machado de Guerra", tempAmbiente.getNome());
        tempAmbiente.adicionarItem(machado);

        tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item hamburguer = new Hamburguer(tempAmbiente.getNome());
        tempAmbiente.adicionarItem(hamburguer);

        ambienteComRosa = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);

        String ambienteSemRosa = gerarAmbienteSemRosa();
        String ambientePertoRosa = gerarAmbientePertoRosa();

        tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item dica1 = new Dica("DicaMachado", "O machado está em " + machado.getLocalizacao(), tempAmbiente.getNome());
        tempAmbiente.adicionarItem(dica1);

        tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item dica2 = new Dica("Dica2", "A rosa não está em " + ambienteSemRosa, tempAmbiente.getNome());
        tempAmbiente.adicionarItem(dica2);

        tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item dica3 = new Dica("Dica3", "A rosa está perto de " + ambientePertoRosa, tempAmbiente.getNome());
        tempAmbiente.adicionarItem(dica3);

        tempAmbiente = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        Item dica4 = new Dica("DicaHamburguer", "O hamburguer está em " + hamburguer.getLocalizacao(),
                tempAmbiente.getNome());
        tempAmbiente.adicionarItem(dica4);
    }

    public String gerarAmbienteSemRosa() {
        Random gerador = new Random();
        String[] nomeAmbientes = ambientes.keySet().toArray(new String[ambientes.size()]);
        Ambiente ambienteAleatorio = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        while (ambienteAleatorio == ambienteComRosa)
            ambienteAleatorio = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        return ambienteAleatorio.getNome();
    }

    public String gerarAmbientePertoRosa() {
        Random gerador = new Random();
        while (true)
            switch (gerador.nextInt(4)) {
                case 0:
                    if (ambienteComRosa.getSaida("norte") != null)
                        return ambienteComRosa.getSaida("norte").getNome();
                    break;
                case 1:
                    if (ambienteComRosa.getSaida("sul") != null)
                        return ambienteComRosa.getSaida("sul").getNome();
                    break;
                case 2:
                    if (ambienteComRosa.getSaida("leste") != null)
                        return ambienteComRosa.getSaida("leste").getNome();
                    break;
                case 3:
                    if (ambienteComRosa.getSaida("oeste") != null)
                        return ambienteComRosa.getSaida("oeste").getNome();
                    break;
            }
    }

    public void usarPoção() {
        if (ambienteAtual == ambienteComRosa) {
            interfaceDeUsuario.adicionarLog("Você encontrou a rosa!");
            interfaceDeUsuario.adicionarLog("Você venceu o jogo!");
            fimDeJogo = true;
        } else {
            interfaceDeUsuario.adicionarLog("Você não está no ambiente certo!");
            interfaceDeUsuario.adicionarLog("A rosa estava em: " + ambienteComRosa.getNome());
            fimDeJogo = true;
        }
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
        interfaceDeUsuario.adicionarLog("Bem-vindo ao mundo de Ghanor!");
        interfaceDeUsuario.adicionarLog("Ghanor é um mundo de fantasia medieval, cheio de magia e aventuras.");
        interfaceDeUsuario.adicionarLog("Você é um bárbaro que está em uma jornada para a rosa que salvará seus amigos.");
        interfaceDeUsuario.adicionarLog("Você precisa encontrar a rosa antes que seja tarde demais.");
        interfaceDeUsuario.adicionarLog("Você tem " + barbaro.getEnergia() + " de energia.");
        interfaceDeUsuario.adicionarLog(ComandosConhecidos.mostrarComandos());
        interfaceDeUsuario.adicionarLog("Digite 'ajuda' se você precisar de ajuda.");
        interfaceDeUsuario.adicionarLog(ambienteAtual.getNome());
        interfaceDeUsuario.adicionarLog("Saídas: ");
        String saidas = ambienteAtual.getSaidas();
        interfaceDeUsuario.adicionarLog(saidas);
    }

    public void jogar() {
        while (!fimDeJogo) {
            interfaceDeUsuario.setEnergia(barbaro.getEnergia());
            interfaceDeUsuario.setDurabilidade(barbaro.temMachado() ? barbaro.getDurabilidadeMachado() : 0);
            comando = Analisador.analisarComando();
            processarComando(comando);
        }
        interfaceDeUsuario.adicionarLog("Obrigado por jogar. Até mais!");
    }

    private void processarComando(Comando comando) {
        if (comando.ehDesconhecido()) {
            interfaceDeUsuario.adicionarLog("Eu não entendi o que você disse...");
            return;
        }

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
            interfaceDeUsuario.adicionarLog(barbaro.pegarItem(comando.getSegundaPalavra(), ambienteAtual));

        else if (palavraDeComando.equals("bolsa"))
            interfaceDeUsuario.adicionarLog(barbaro.olharBolsa());

        else if (palavraDeComando.equals("usar"))
            interfaceDeUsuario.adicionarLog(barbaro.usarItem(comando.getSegundaPalavra(), interfaceDeUsuario));

        else if (palavraDeComando.equals("pocao"))
            usarPoção();
    }

    private void observar() {
        interfaceDeUsuario.adicionarLog("Você está em " + ambienteAtual.getNome());
        interfaceDeUsuario.adicionarLog("Saídas: " + ambienteAtual.getSaidas());
    }

    private void imprimirAjuda() {
        interfaceDeUsuario.adicionarLog("Suas palavras de comando são:");
        interfaceDeUsuario.adicionarLog(ComandosConhecidos.mostrarComandos());
    }

    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            interfaceDeUsuario.adicionarLog("Ir para onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if (proximoAmbiente == null)
            interfaceDeUsuario.adicionarLog("Essa saída não existe!");

        else {
            ambienteAtual = proximoAmbiente;
            barbaro.andar();
            if (ambienteAtual.getInfestado()) {
                barbaro.derrotarMonstro();
            }
            if (!barbaroMorreu()) {
                interfaceDeUsuario.adicionarLog("Energia restante: " + barbaro.getEnergia());
                interfaceDeUsuario.adicionarLog(ambienteAtual.getNome());
                if (ambienteAtual.quantidadeItens() > 0)
                    interfaceDeUsuario.adicionarLog("Você encontra os seguintes itens: " + ambienteAtual.getItens());
                interfaceDeUsuario.adicionarLog("Saídas: ");
                String saidas = ambienteAtual.getSaidas();
                interfaceDeUsuario.adicionarLog(saidas);
                redefinirInfestacao();
            }
        }
    }

    private boolean barbaroMorreu() {
        if (barbaro.getEnergia() <= 0) {
            interfaceDeUsuario.adicionarLog("Você morreu!");
            fimDeJogo = true;
            return true;
        }
        return false;
    }

    private void redefinirInfestacao() {
        for (String ambiente : ambientes.keySet()) {
            ambientes.get(ambiente).setInfestado();
        }
    }
}
