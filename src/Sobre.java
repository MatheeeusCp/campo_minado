import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sobre implements ActionListener {
    public Sobre() {
        JFrame frame = new JFrame("Sobre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        ImageIcon icone = new ImageIcon("images/mina.png");
        Image image = icone.getImage(); // transform it
        Image img = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon mina = new ImageIcon(img);
        JLabel imagem = new JLabel(mina);
        frame.add(imagem, BorderLayout.NORTH);

        JTextArea t1 = new JTextArea("A área do jogo consiste num campo de quadrados retangular. Cada \nquadrado pode ser revelado clicando sobre ele, e se o quadrado clicado \ncontiver uma mina, então o jogo acaba. Se, por outro lado, o quadrado não \ncontiver uma mina, uma de duas coisas paderá acontecer: \n\n1. Um número aparece, indicando a quantidade de \nquadrados adjacentes que contêm minas;\n2. Nenhum número aparece. Neste caso, o jogo revela \nautomaticamente os quadrados que se encontram adjacentes ao \nquadrado vazio, já que não podem conter minas;\n\nO jogo é ganho quando todos os quadrados que não têm inas são revelados.");
        t1.setEditable(false);
        t1.setBackground(Color.getColor("#eeeeee"));
        frame.add(t1, BorderLayout.CENTER);

        JButton botao = new JButton("Fechar");
        botao.addActionListener(this);
        frame.add(botao, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
