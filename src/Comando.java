/*Feito por: Thiago Melato*/

public class Comando {
    private String palavraComando;
    private String segundaPalavra;

    public Comando(String palavraComando, String segundaPalavra) {
        this.palavraComando = palavraComando;
        this.segundaPalavra = segundaPalavra;
    }

    public String getPalavraComando() {
        return palavraComando;
    }

    public String getSegundaPalavra() {
        return segundaPalavra;
    }

    public boolean ehDesconhecido() {
        return (palavraComando == null);
    }

    public boolean temSegundaPalavra() {
        return (segundaPalavra != null);
    }
}
