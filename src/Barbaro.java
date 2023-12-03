import java.util.HashMap;
import java.util.Random;

public class Barbaro {
    private int energia;
    private boolean temMachado;
    private HashMap<String, Item> itens;

    public Barbaro() {
        Random gerador = new Random();
        energia = gerador.nextInt(30) + 20;
        itens = new HashMap<String, Item>();
        temMachado = false;
    }

    public void adicionarItem(Item item) {
        itens.put(item.getNome(), item);
    }

    public void comerHambuguer() {
        energia += 20;
    }

    public void andar(){
        energia--;
    }

    public boolean temMachado(){
        return temMachado;
    }

    public void derrotarMonstro() {
        if(! temMachado)
            energia--;
    }

    public void pegarMachado() {
        temMachado = true;
    }

    public void usarPoção() {

    }

    public int getEnergia() {
        return energia;
    }

    public void olharBolsa() {
        String itensNaBolsa = "";
        for (String item : itens.keySet()) {
            itensNaBolsa += item + " ";
        }
        if(itensNaBolsa.equals(""))
            System.out.println("A bolsa está vazia");
        else
            System.out.println("Itens na bolsa: " + itensNaBolsa);
    }

    public void lerDescricaoItem(String segundaPalavra) {
        if(itens.containsKey(segundaPalavra))
            System.out.println(itens.get(segundaPalavra).getDescricao());
        else
            System.out.println("Esse item não está na sua bolsa!");
    }


}
