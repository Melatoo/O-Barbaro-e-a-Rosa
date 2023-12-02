import java.util.Scanner;

public class Analisador {
    private Scanner scanner;

    public Analisador() {
        scanner = new Scanner(System.in);
    }

    public Comando analisarComando() {
        String comando = scanner.nextLine();
        String palavra1 = null;
        String palavra2 = null;

        try (Scanner tokenizer = new Scanner(comando)) {
            if (tokenizer.hasNext()) {
                palavra1 = tokenizer.next();
                if (tokenizer.hasNext()) {
                    palavra2 = tokenizer.next();
                }
            }
        }
        if (ComandosConhecidos.comandoValido(palavra1)) {
            return new Comando(palavra1, palavra2);
        }
        return new Comando(null, palavra2);
    }
}
