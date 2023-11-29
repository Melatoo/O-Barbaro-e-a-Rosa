/*Feito por: Thiago Melato */

public abstract class ComandosConhecidos {
    private static final String[] comandos = {};

    private ComandosConhecidos() {
    }

    public static void mostrarComandos() {
        System.out.println("Comandos disponíveis:");
        for (String comando : comandos) {
            System.out.print(comando + " ");
        }
        System.out.println();
    }

    public static boolean comandoValido(String comando) {
        for (String comandoConhecido : comandos) {
            if (comandoConhecido.equals(comando)) {
                return true;
            }
        }
        return false;
    }

}
