package Jogo;
import java.util.HashMap;
import java.util.Random;

import Interface.InterfaceDeUsuario;
import Itens.Dica;
import Itens.Hamburguer;
import Itens.Item;
import Itens.Machado;

public class Barbaro {
    private int energia;
    private HashMap<String, Item> itens;
    private Machado machado;

    public Barbaro() {
        Random gerador = new Random();
        energia = gerador.nextInt(30) + 20;
        itens = new HashMap<String, Item>();
    }

    /*
     * adiciona um item na bolsa do barbaro
     */

    public void adicionarItem(Item item) {
        itens.put(item.getNome(), item);
    }

    /*
     * caso o barbaro tenha o hamburguer, ele come e recupera 10 de energia
     */

    public String comerHambuguer() {
        Item hamburguer = itens.get("hamburguer");
        if (hamburguer != null) {
            hamburguer.usar();
            energia += 10;
            itens.remove(hamburguer.getNome());
            return "Energia atual: " + energia;
        } else {
            return "Você não tem o hamburguer...";
        }
    }

    /*
     * percorre uma trilha, diminuindo a energia do barbaro
     */

    public void andar() {
        energia--;
    }

    /*
     * derrota um monstro, usando o machado caso ele tenha um, ou usando as mãos
     * 
     * @return String mensagem de derrota do monstro
     */

    public String derrotarMonstro() {
        machado = (Machado) itens.get("machado");
        if (machado == null) {
            energia--;
            return "Você derrotou o monstro com suas mãos!";
        } else {
            machado.usar();
            String mensagem = "Você derrotou o monstro com seu machado!";
            if (machado.getDurabilidade() == 0) {
                mensagem += " O machado quebrou!";
                itens.remove("machado");
            }
            return mensagem;
        }
    }

    public int getEnergia() {
        return energia;
    }

    /*
     * @return String string com itens na bolsa do barbaro
     */

    public String olharBolsa() {
        String itensNaBolsa = "";
        for (String item : itens.keySet()) {
            itensNaBolsa += item + " ";
        }
        if (itensNaBolsa.equals(""))
            return "A bolsa está vazia";
        else
            return "Itens na bolsa: " + itensNaBolsa;
    }

    /*
     * @param String nome do item a ser lido
     * 
     * @return String descrição do item
     */

    public String lerDescricaoItem(String segundaPalavra) {
        if (itens.containsKey(segundaPalavra))
            return itens.get(segundaPalavra).getDescricao();
        else
            return "Esse item não está na sua bolsa!";
    }

    /*
     * @param String nome do item a ser pego
     * 
     * @param Ambiente ambiente atual
     * 
     * @return String mensagem de confirmação ou erro
     */

    public String pegarItem(String segundaPalavra, Ambiente ambienteAtual) {
        Item item = ambienteAtual.getItem(segundaPalavra);
        if (item != null) {
            adicionarItem(item);
            ambienteAtual.removerItem(segundaPalavra);
            return "Você pegou o item " + segundaPalavra;
        } else {
            return "Esse item não está no ambiente!";
        }

    }

    /*
     * @param String nome do item a ser usado
     * 
     * @return String mensagem de confirmação ou erro
     */

    public String usarItem(String segundaPalavra, InterfaceDeUsuario interfaceDeUsuario) {
        Item item = itens.get(segundaPalavra);
        if (item != null) {
            if (item instanceof Hamburguer)
                return comerHambuguer();
            else if (item instanceof Dica) {
                interfaceDeUsuario.adicionarDica(item.usar());
                itens.remove(item.getNome());
                return "Dica adicionada!";
            } else if (item instanceof Machado)
                return "Você só pode usar o machado em uma luta com monstro!";
            else
                return item.usar();
        } else {
            return "Esse item não está na sua bolsa!";
        }
    }

    public int getDurabilidadeMachado() {
        return machado.getDurabilidade();
    }

    public boolean temMachado() {
        return machado != null;
    }
}
