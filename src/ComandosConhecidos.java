/*Feito por: Thiago Melato */

public class ComandosConhecidos {
    private static String[] comandos = { "ir", "sair", "ajuda", "pocao", "pegar", "bolsa", "usar",
            "observar" };

    public static String mostrarComandos() {
        String comandosDisponiveis = "Comandos disponíveis: ";
        for (String comando : comandos) {
            comandosDisponiveis += "'" + comando + "' ";
        }
        return comandosDisponiveis;
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
