/*Feito por: Thiago Melato */

public class ComandosConhecidos {
    private static String[] comandos = { "ir", "sair", "ajuda", "pocao", "pegar", "bolsa", "usar",
            "observar" };

    public static void mostrarComandos() {
        System.out.print("Comandos disponíveis: ");
        for (String comando : comandos) {
            System.out.print("'" + comando + "' ");
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
