package Interface;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Jogo.Jogo;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.image.BufferedImage;

/*Feito por: Lucio Boari*/
public class InterfaceDeUsuario extends JFrame {

    // esquerda
    private Label energiaRestante;
    private Label durabilidadeMachado;

    // direita
    private JTextArea dicas;
    private Jogo jogo;

    // sul
    private JTextArea log;
    private JTextField entrada;
    private String comando;
    private JButton enviaComando;

    // inicializa painel de energia e durabilidade do machado
    private void inicializandoBordaEsquerda() {
        energiaRestante = new Label("Energia Restante: ");
        durabilidadeMachado = new Label("Durabilidade do Machado: ");
        JPanel esquerda = new JPanel();
        esquerda.setLayout(new BoxLayout(esquerda, BoxLayout.Y_AXIS));
        add(esquerda, BorderLayout.WEST);
        esquerda.add(energiaRestante);
        esquerda.add(durabilidadeMachado);
    }

    // inicializa painel com a imagem do mapa
    private void inicializandoBordaCentro() {
        try {
            BufferedImage imagem = ImageIO.read(
                    new File(System.getProperty("java.class.path") + File.separatorChar
                            + "interface/O barbaro e a rosa.png"));
            JLabel il = new JLabel(new ImageIcon(imagem));
            add(il, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // inicializa painel de dicas
    private void inicializandoBordaDireita() {
        dicas = new JTextArea();
        dicas.setEditable(false);
        dicas.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(dicas);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel direita = new JPanel();
        direita.setLayout(new BoxLayout(direita, BoxLayout.Y_AXIS));
        add(direita, BorderLayout.EAST);
        direita.add(new Label("Dicas encontradas:"));
        direita.add(jsp);
    }

    // inicializa log e entrada de comandos
    private void inicializandoBordaInferior() {
        JPanel sul = new JPanel();
        sul.setLayout(new BoxLayout(sul, BoxLayout.Y_AXIS));
        add(sul, BorderLayout.SOUTH);
        log = new JTextArea();
        log.setRows(8);
        log.setEditable(false);
        JScrollPane jsp = new JScrollPane(log);
        sul.add(jsp);
        JPanel sulEntrada = new JPanel();
        sulEntrada.setLayout(new BoxLayout(sulEntrada, BoxLayout.X_AXIS));
        ClickChecker clickChecker = new ClickChecker();
        entrada = new JTextField();
        entrada.addActionListener(clickChecker);
        sulEntrada.add(entrada);
        enviaComando = new JButton(">");
        sulEntrada.add(enviaComando);
        sul.add(sulEntrada);
    }

    public InterfaceDeUsuario() {
        super("O Bárbaro e a Rosa");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        inicializandoBordaEsquerda();
        inicializandoBordaCentro();
        inicializandoBordaDireita();
        inicializandoBordaInferior();

        setResizable(false);
        pack();
        jogo = Jogo.getInstance(this);
        setVisible(true);
        jogo.iniciarJogo();
    }

    
    /** 
     * adiciona mensagem de jogo ao log
     * @param String texto a ser adicionado
     */
    public void adicionarLog(String texto) {
        log.append(texto + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /** 
     * adiciona mensagem de jogo ao log
     * @param String texto a ser adicionado
     */
    public void adicionarLogSemQuebra(String texto) {
        log.append(texto);
        log.setCaretPosition(log.getDocument().getLength());
    }

    public void setEnergia(int energia) {
        energiaRestante.setText("Energia Restante: " + energia);
    }

    public void setDurabilidade(int durabilidade) {
        durabilidadeMachado.setText("Durabilidade do Machado: " + durabilidade);
    }

    /** 
     * adiciona dica no painel de dicas
     * @param String dica a ser adicionada
     */
    public void adicionarDica(String dica) {
        dicas.append(dica + "\n");
    }

    // classe para verificar se o algum comando novo foi inserido no campo de texto
    private class ClickChecker implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            jogo.jogar(entrada.getText());
            entrada.setText("");
        }
    }

    public String getComando() {
        return comando;
    }
}
