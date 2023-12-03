import java.util.HashMap;
import java.util.Random;

public class Barbaro {
    private int energia;
    private HashMap<String, Item> itens;
    private Machado machado;

    public Barbaro() {
        Random gerador = new Random();
        energia = gerador.nextInt(30) + 20;
        itens = new HashMap<String, Item>();
    }

    public void adicionarItem(Item item) {
        itens.put(item.getNome(), item);
    }

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

    public void andar() {
        energia--;
    }

    public String derrotarMonstro() {
        machado = (Machado) itens.get("machado");
        if (machado == null) {
            energia--;
            return "Você derrotou o monstro com suas mãos!";
        } else {
            machado.usar();
            String mensagem = "Você derrotou o monstro com seu machado!";
            if (machado.getDurabilidade() == 0) {
                mensagem += "O machado quebrou!";
                machado = null;
            }
            return mensagem;
        }
    }

    public int getEnergia() {
        return energia;
    }

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

    public String lerDescricaoItem(String segundaPalavra) {
        if (itens.containsKey(segundaPalavra))
            return itens.get(segundaPalavra).getDescricao();
        else
            return "Esse item não está na sua bolsa!";
    }

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

    public String usarItem(String segundaPalavra, InterfaceDeUsuario interfaceDeUsuario) {
        Item item = itens.get(segundaPalavra);
        if (item != null) {
            itens.remove(item.getNome());
            if (item instanceof Hamburguer)
                return comerHambuguer();
            else if(item instanceof Dica){
                interfaceDeUsuario.adicionarDica(item.usar());
                return "Dica adicionada!";
            }
            else
                return item.usar();
        } else {
            return "Esse item não está na sua bolsa!";
        }
    }

    public int getDurabilidadeMachado(){
        return machado.getDurabilidade();
    }

    public boolean temMachado(){
        return machado != null;
    }

    
}
