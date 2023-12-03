
/*Feito por Thiago Melato */
import java.util.HashMap;
import java.util.Random;

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
        this.infestado = gerador.nextInt(5) == 1;
    }

    public void ajustarSaidas(Ambiente norte, Ambiente leste, Ambiente sul, Ambiente oeste) {
        saidas.put("norte", norte);
        saidas.put("leste", leste);
        saidas.put("sul", sul);
        saidas.put("oeste", oeste);
    }

    public Ambiente getSaida(String direcao) {
        return saidas.get(direcao);
    }

    public String getItens() {
        String itensNoAmbiente = "";
        for (String item : itens.keySet()) {
            if (itens.get(item) != null)
                itensNoAmbiente += item + " ";
        }
        return itensNoAmbiente;
    }

    public void adicionarItem(Item item) {
        itens.put(item.getNome(), item);
    }

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

    public void removerItem(String nomeItem) {
        if (itens.containsKey(nomeItem))
            itens.remove(nomeItem);

    }

    public boolean getInfestado() {
        return infestado;
    }

    public void setInfestado() {
        Random gerador = new Random();
        infestado = gerador.nextInt(5) == 1;
    }
}
