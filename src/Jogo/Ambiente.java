package Jogo;

/*Feito por Thiago Melato */
import java.util.HashMap;
import java.util.Random;

import Itens.Item;

public class Ambiente {
    private String nome;
    private boolean infestado;
    private HashMap<String, Ambiente> saidas;
    private HashMap<String, Item> itens;

    public Ambiente(String nome) {
        saidas = new HashMap<String, Ambiente>();
        itens = new HashMap<String, Item>();
        this.nome = nome;
        Random gerador = new Random();
        // 25% de chance de ser infestado
        this.infestado = gerador.nextInt(4) == 1;
    }

    /*
     * @param Ambiente ambientes vizinhos
     */

    public void ajustarSaidas(Ambiente norte, Ambiente leste, Ambiente sul, Ambiente oeste) {
        saidas.put("norte", norte);
        saidas.put("leste", leste);
        saidas.put("sul", sul);
        saidas.put("oeste", oeste);
    }

    /*
     * @param String direcao
     * 
     * @return Ambiente ambiente com a direção passada
     */

    public Ambiente getSaida(String direcao) {
        return saidas.get(direcao);
    }

    /*
     * 
     * @return String itens no ambiente
     */

    public String getItens() {
        String itensNoAmbiente = "";
        for (String item : itens.keySet()) {
            if (itens.get(item) != null)
                itensNoAmbiente += item + " ";
        }
        return itensNoAmbiente;
    }

    /*
     * adiciona um item no ambiente
     * 
     * @param Item item a ser adicionado
     */

    public void adicionarItem(Item item) {
        itens.put(item.getNome(), item);
    }

    /*
     * @return String todas saidas do ambiente
     */

    public String getSaidas() {
        String saidas = "";
        for (String saida : this.saidas.keySet()) {
            if (getSaida(saida) != null)
                saidas += saida + " ";
        }
        return saidas;
    }

    public String getNome() {
        return nome;
    }

    public int quantidadeItens() {
        return itens.size();
    }

    public Item getItem(String nomeItem) {
        return itens.get(nomeItem);
    }

    /*
     * remove um item do ambiente
     * 
     * @param String nome do item a ser removido
     */

    public void removerItem(String nomeItem) {
        if (itens.containsKey(nomeItem))
            itens.remove(nomeItem);

    }

    public boolean getInfestado() {
        return infestado;
    }

    public void setInfestado() {
        Random gerador = new Random();
        infestado = gerador.nextInt(4) == 1;
        if (infestado)
            System.out.println("O ambiente " + nome + " foi infestado!");
    }
}
