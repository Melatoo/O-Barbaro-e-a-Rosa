package Comandos;
import java.util.Scanner;

/*Feito por: Thiago Melato e Davi Siqueira*/
public abstract class Analisador {
    /** 
     * analisa o comando digitado pelo usuário
     * @param comandoInserido
     * @return Comando
     */

    public static Comando analisarComando(String comandoInserido) {
        String palavra1 = null;
        String palavra2 = null;

        Scanner tokenizer = new Scanner(comandoInserido);

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
