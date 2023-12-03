import java.util.HashMap;
import java.util.Random;

public class Barbaro {
    private int energia;
    private HashMap<String, Item> itens;

    public Barbaro() {
        Random gerador = new Random();
        energia = gerador.nextInt(30) + 20;
        itens = new HashMap<String, Item>();
    }

    public void adicionarItem(Item item) {
        itens.put(item.getNome(), item);
    }

    public void comerHambuguer() {
        Item hamburguer = itens.get("hamburguer");
        if (hamburguer != null) {
            hamburguer.usar();
            energia += 10;
            System.out.println("Energia atual: " + energia);
            itens.remove(hamburguer.getNome());
        } else {
            System.out.println("Você não tem o hamburguer...");
        }
    }

    public void andar() {
        energia--;
    }

    public void derrotarMonstro() {
        Machado machado = (Machado) itens.get("machado");
        if (machado == null) {
            energia--;
            System.out.println("Você derrotou o monstro com suas mãos!");
        } else {
            machado.usar();
            System.out.println("Você derrotou o monstro com seu machado!");
            if (machado.getDurabilidade() == 0) {
                System.out.println("O machado quebrou!");
                machado = null;
            }
        }
    }

    public int getEnergia() {
        return energia;
    }

    public void olharBolsa() {
        String itensNaBolsa = "";
        for (String item : itens.keySet()) {
            itensNaBolsa += item + " ";
        }
        if (itensNaBolsa.equals(""))
            System.out.println("A bolsa está vazia");
        else
            System.out.println("Itens na bolsa: " + itensNaBolsa);
    }

    public void lerDescricaoItem(String segundaPalavra) {
        if (itens.containsKey(segundaPalavra))
            System.out.println(itens.get(segundaPalavra).getDescricao());
        else
            System.out.println("Esse item não está na sua bolsa!");
    }

    public void pegarItem(String segundaPalavra, Ambiente ambienteAtual) {
        Item item = ambienteAtual.getItem(segundaPalavra);
        if (item != null) {
            adicionarItem(item);
            ambienteAtual.removerItem(segundaPalavra);
            System.out.println("Você pegou o item " + segundaPalavra);
        } else {
            System.out.println("Esse item não está no ambiente!");
        }
    }

    public void usarItem(String segundaPalavra) {
        Item item = itens.get(segundaPalavra);
        if (item != null) {
            if (item instanceof Hamburguer)
                comerHambuguer();
            else
                item.usar();
        } else {
            System.out.println("Esse item não está na sua bolsa!");
        }
    }
}
