import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Label;
import java.io.File;
import java.awt.image.BufferedImage;

public class InterfaceDeUsuario extends JFrame{

    //esquerda
    private Label energiaRestante;
    private Label durabilidadeMachado;

    //direita
    private JTextArea dicas;

    //sul
    private JTextArea log;
    private JTextField entrada;
    private JButton enviaComando;

    private void inicializandoBordaEsquerda(){
        energiaRestante = new Label("Energia Restante: ");
        durabilidadeMachado = new Label("Durabilidade do Machado: ");
        JPanel esquerda = new JPanel();
        esquerda.setLayout(new BoxLayout(esquerda, BoxLayout.Y_AXIS));
        add(esquerda, BorderLayout.WEST);
        esquerda.add(energiaRestante);
        esquerda.add(durabilidadeMachado);
    }

    private void inicializandoBordaCentro(){
        try{
            BufferedImage imagem = ImageIO.read(new File(System.getProperty("java.class.path") + File.separatorChar + "O bárbaro e a rosa.png"));
            JLabel il = new JLabel(new ImageIcon(imagem));
            add(il, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void inicializandoBordaDireita(){
        dicas = new JTextArea();
        dicas.setEditable(false);
        JPanel direita = new JPanel();
        direita.setLayout(new BoxLayout(direita, BoxLayout.Y_AXIS));
        add(direita, BorderLayout.EAST);
        direita.add(new Label("Dicas encontradas:"));
        direita.add(dicas);
    }

    private void inicializandoBordaInferior(){
        JPanel sul = new JPanel();
        sul.setLayout(new BoxLayout(sul, BoxLayout.Y_AXIS));
        add(sul, BorderLayout.SOUTH);
        log = new JTextArea();
        log.setRows(8);
        log.setEditable(false);
        sul.add(log);
        JPanel sulEntrada = new JPanel();
        sulEntrada.setLayout(new BoxLayout(sulEntrada, BoxLayout.X_AXIS));
        entrada = new JTextField();
        sulEntrada.add(entrada);
        enviaComando = new JButton(">");
        sulEntrada.add(enviaComando);
        sul.add(sulEntrada);
    }

    public InterfaceDeUsuario(){
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

    public void adicionarLog(String texto){
        log.append(texto + "\n");
    }
}
