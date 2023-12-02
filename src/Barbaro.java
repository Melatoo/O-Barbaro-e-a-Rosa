import java.util.Random;

public class Barbaro {
    private int energia;
    private boolean temMachado;

    public Barbaro() {
        Random gerador = new Random();
        energia = gerador.nextInt(30) + 20;
        temMachado = false;

    }

    public void comerHambuguer() {
        energia += 20;
    }

    public void derrotarMonstro() {
        energia = temMachado ? energia : energia--;
    }

    public void pegarMachado() {
        temMachado = true;
    }

    public void usarPoção() {
        
    }

    public int getEnergia() {
        return energia;
    }


}
