package Jogo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import Comandos.Analisador;
import Comandos.Comando;
import Comandos.ComandosConhecidos;
import Interface.InterfaceDeUsuario;
import Itens.Dica;
import Itens.Hamburguer;
import Itens.Item;
import Itens.Machado;

/*Feito por: Thiago Melato e Davi Siqueira*/
public class Jogo {
    private static Jogo instance;
    private Barbaro barbaro;
    private Comando comando;
    private Ambiente ambienteAtual;
    private Ambiente ambienteComRosa;
    private InterfaceDeUsuario GUI;
    private HashMap<String, Ambiente> ambientes;
    private boolean fimDeJogo;

    private Jogo(InterfaceDeUsuario GUI) {
        barbaro = new Barbaro();
        fimDeJogo = false;
        ambientes = new HashMap<String, Ambiente>();
        this.GUI = GUI;
    }

    /**
     * retorna a instancia do jogo, se não existir uma instancia, cria uma nova
     * sigleton
     * 
     * @param GUI
     * @return Jogo
     */

    public static Jogo getInstance(InterfaceDeUsuario GUI) {
        if (instance == null) {
            instance = new Jogo(GUI);
        }
        return instance;
    }

    /**
     * inicia o jogo, os ambientes, os itens e imprime as boas vindas no log do jogo
     */
    public void iniciarJogo() {
        inicializarAmbientes();
        inicializarItens();
        imprimirBoasVindas();
        GUI.setEnergia(barbaro.getEnergia());
        GUI.setDurabilidade(0);
        gerarGabarito();
    }

    /*
     * inicializa os itens do jogo e os adiciona nos ambientes aleatoriamente
     */

    private void inicializarItens() {
        Random gerador = new Random();
        // retorna um array com os nomes dos ambientes
        String[] nomeAmbientes = ambientes.keySet().toArray(new String[ambientes.size()]);

        // tempAmbiente recebe um ambiente aleatorio, com base na array de cima
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

    /**
     * gera um ambiente aleatorio que não seja o ambiente com a rosa
     * 
     * @return String com o nome do ambiente sem a rosa
     */

    private String gerarAmbienteSemRosa() {
        Random gerador = new Random();
        String[] nomeAmbientes = ambientes.keySet().toArray(new String[ambientes.size()]);
        Ambiente ambienteAleatorio = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        while (ambienteAleatorio == ambienteComRosa)
            ambienteAleatorio = ambientes.get(nomeAmbientes[gerador.nextInt(nomeAmbientes.length)]);
        return ambienteAleatorio.getNome();
    }

    /**
     * gera um ambiente aleatorio que seja adjacente ao ambiente com a rosa
     * 
     * @return String com o nome do ambiente adjacente ao ambiente com a rosa
     */

    private String gerarAmbientePertoRosa() {
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

    /*
     * usa a poção, se o barbaro estiver no ambiente com a rosa, o jogo acaba como
     * vitoria
     * se não, o jogo acaba como derrota
     */

    private void usarPoção() {
        if (ambienteAtual == ambienteComRosa) {
            GUI.adicionarLog("Você encontrou a rosa!");
            GUI.adicionarLog("Você venceu o jogo!");
            fimDeJogo = true;
        } else {
            GUI.adicionarLog("Você não está no ambiente certo!");
            GUI.adicionarLog("A rosa estava em: " + ambienteComRosa.getNome());
            fimDeJogo = true;
        }
    }

    /*
     * inicializa os ambientes do jogo
     */

    private void inicializarAmbientes() {
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
        vila.ajustarSaidas(praia, null, praia, kottar);
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

    /*
     * imprime as boas vindas do jogo
     */

    private void imprimirBoasVindas() {
        GUI.adicionarLog("Bem-vindo ao mundo de Ghanor!");
        GUI.adicionarLog("Ghanor é um mundo de fantasia medieval, cheio de magia e aventuras.");
        GUI.adicionarLog("Você é um bárbaro que está em uma jornada para a rosa que salvará seus amigos.");
        GUI.adicionarLog("Você precisa encontrar a rosa antes que seja tarde demais.");
        GUI.adicionarLog("Você tem " + barbaro.getEnergia() + " de energia.");
        GUI.adicionarLog(ComandosConhecidos.mostrarComandos());
        GUI.adicionarLog("Digite 'ajuda' se você precisar de ajuda.");
        GUI.adicionarLog(ambienteAtual.getNome());
        GUI.adicionarLogSemQuebra("Saídas: ");
        String saidas = ambienteAtual.getSaidas();
        GUI.adicionarLog(saidas);
    }

    /*
     * processa o comando inserido pelo usuario pela interface e atualiza as
     * informações na GUI
     */

    public void jogar(String comandoInserido) {
        comando = Analisador.analisarComando(comandoInserido);
        processarComando(comando);
        GUI.setEnergia(barbaro.getEnergia());
        GUI.setDurabilidade(barbaro.temMachado() ? barbaro.getDurabilidadeMachado() : 0);
        if (fimDeJogo) {
            JOptionPane.showMessageDialog(GUI,
                    ambienteAtual == ambienteComRosa ? "Você venceu o jogo!" : "Você perdeu o jogo!");
            GUI.dispose();
        }
    }

    /**
     * processa o comando do usuario
     * 
     * @param Comando comando a ser processado
     */

    private void processarComando(Comando comando) {
        if (comando.ehDesconhecido()) {
            GUI.adicionarLog("Eu não entendi o que você disse...");
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
            GUI.adicionarLog(barbaro.pegarItem(comando.getSegundaPalavra(), ambienteAtual));

        else if (palavraDeComando.equals("bolsa"))
            GUI.adicionarLog(barbaro.olharBolsa());

        else if (palavraDeComando.equals("usar"))
            GUI.adicionarLog(barbaro.usarItem(comando.getSegundaPalavra(), GUI));

        else if (palavraDeComando.equals("pocao"))
            usarPoção();
    }

    /*
     * imprime o ambiente atual e suas saidas
     */

    private void observar() {
        GUI.adicionarLog("Você está em " + ambienteAtual.getNome());
        GUI.adicionarLog("Saídas: " + ambienteAtual.getSaidas());
    }

    /*
     * imprime os comandos disponiveis
     */

    private void imprimirAjuda() {
        GUI.adicionarLog("Suas palavras de comando são:");
        GUI.adicionarLog(ComandosConhecidos.mostrarComandos());
    }

    /**
     * move o barbaro para o ambiente desejado
     * 
     * @param Comando comando ir com a direção desejada
     */

    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            GUI.adicionarLog("Ir para onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if (proximoAmbiente == null)
            GUI.adicionarLog("Essa saída não existe!");

        else {
            ambienteAtual = proximoAmbiente;
            barbaro.andar();
            if (ambienteAtual.getInfestado()) {
                GUI.adicionarLog(barbaro.derrotarMonstro());
            }
            if (!barbaroMorreu()) {
                GUI.adicionarLog(ambienteAtual.getNome());
                if (ambienteAtual.quantidadeItens() > 0)
                    GUI.adicionarLog("Você encontra os seguintes itens: " + ambienteAtual.getItens());
                GUI.adicionarLogSemQuebra("Saídas: ");
                String saidas = ambienteAtual.getSaidas();
                GUI.adicionarLog(saidas);
                redefinirInfestacao();
            }
        }
    }

    /**
     * verifica se o barbaro morreu
     * 
     * @return boolean true se o barbaro morreu, false se não
     */

    private boolean barbaroMorreu() {
        if (barbaro.getEnergia() <= 0) {
            GUI.adicionarLog("Você morreu!");
            fimDeJogo = true;
            return true;
        }
        return false;
    }

    /*
     * redefine a infestação dos ambientes
     */

    private void redefinirInfestacao() {
        for (String ambiente : ambientes.keySet()) {
            ambientes.get(ambiente).setInfestado();
        }
    }

    /*
     * gera um arquivo com o gabarito do jogo, ou seja, posicao de todos os itens
     */

    private void gerarGabarito() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("gabarito.txt"))) {
            // para cada ambiente, escreve no arquivo o item e o ambiente
            for (String ambiente : ambientes.keySet()) {
                String[] itens = ambientes.get(ambiente).getItens().split(" ");
                for (String item : itens) {
                    if (!item.equals(""))
                        writer.write("O item " + item + " estava em " + ambiente + "\n");
                }
            }
            writer.write("A rosa estava em " + ambienteComRosa.getNome() + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao gerar gabarito");
        }
    }
}
