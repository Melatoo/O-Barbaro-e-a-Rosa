import java.util.Scanner;

public abstract class Analisador {
    private static Scanner scanner = new Scanner(System.in);

    /*
     * analisa o comando digitado pelo usuário
     * @return Comando comando e analisado
     */

    public static Comando analisarComando() {
        String comando = scanner.nextLine();
        String palavra1 = null;
        String palavra2 = null;

        Scanner tokenizer = new Scanner(comando);

        if (tokenizer.hasNext()) {
            palavra1 = tokenizer.next().toLowerCase();
            if (tokenizer.hasNext()) {
                palavra2 = tokenizer.next().toLowerCase();
            }
        }

        tokenizer.close();

        // verifica se o comando é válido, se não for
        if (ComandosConhecidos.comandoValido(palavra1)) {
            return new Comando(palavra1, palavra2);
        }
        return new Comando(null, palavra2);
    }
}
