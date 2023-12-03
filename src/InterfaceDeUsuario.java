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
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.image.BufferedImage;

public class InterfaceDeUsuario extends JFrame {

    // esquerda
    private Label energiaRestante;
    private Label durabilidadeMachado;

    // direita
    private JTextArea dicas;

    // sul
    private JTextArea log;
    private JTextField entrada;
    private String comando;
    private JButton enviaComando;

    private void inicializandoBordaEsquerda() {
        energiaRestante = new Label("Energia Restante: ");
        durabilidadeMachado = new Label("Durabilidade do Machado: ");
        JPanel esquerda = new JPanel();
        esquerda.setLayout(new BoxLayout(esquerda, BoxLayout.Y_AXIS));
        add(esquerda, BorderLayout.WEST);
        esquerda.add(energiaRestante);
        esquerda.add(durabilidadeMachado);
    }

    private void inicializandoBordaCentro() {
        try {
            BufferedImage imagem = ImageIO.read(
                    new File(System.getProperty("java.class.path") + File.separatorChar + "O barbaro e a rosa.png"));
            JLabel il = new JLabel(new ImageIcon(imagem));
            add(il, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
        entrada = new JTextField();
        sulEntrada.add(entrada);
        enviaComando = new JButton(">");
        ClickChecker clickChecker = new ClickChecker();
        enviaComando.addActionListener(clickChecker);
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
    }

    public void adicionarLog(String texto) {
        log.append(texto + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

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

    public void adicionarDica(String dica) {
        dicas.append(dica + "\n");
    }

    private class ClickChecker implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {

        }
    }
}
