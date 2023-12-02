/*Feito por: Thiago Melato */

public class ComandosConhecidos {
    private static String[] comandos = {"andar", "sair", "ajuda", "usar poçao"};

    public static void mostrarComandos() {
        System.out.println("Comandos disponíveis:");
        for (String comando : comandos) {
            System.out.print(comando + " ");
        }
        System.out.println();
    }

    public static boolean comandoValido(String comando) {
        comando.toLowerCase();
        for (String comandoConhecido : comandos) {
            if (comandoConhecido.equals(comando))
                return true;
        }
        return false;
    }
}
