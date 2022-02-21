import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;

public class Sistema implements ActionListener {
    public Sistema() {
        JFrame frame = new JFrame("Campo Minado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);

        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);

        JMenu inicio = new JMenu("Inicio");
        menu.add(inicio);

        JMenu novoJogo = new JMenu("Novo jogo");
        JMenuItem historico = new JMenuItem("Historico");
        historico.addActionListener(this);
        JMenuItem sobre = new JMenuItem("Sobre");
        sobre.addActionListener(this);
        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(this);

        JMenuItem facil = new JMenuItem("Fácil");
        facil.addActionListener(this);
        JMenuItem medio = new JMenuItem("Médio");
        medio.addActionListener(this);
        JMenuItem Dificil = new JMenuItem("Difícil");
        Dificil.addActionListener(this);
        novoJogo.add(facil);
        novoJogo.add(medio);
        novoJogo.add(Dificil);

        inicio.add(novoJogo);
        inicio.add(historico);
        inicio.add(sobre);
        inicio.add(sair);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Fácil":
                new CampoMinado(1);
                break;
            case "Médio":
                new CampoMinado(2);
                break;
            case "Difícil":
                new CampoMinado(3);
                break;
            case "Historico":
                new Historico();
                break;
            case "Sobre":
                new Sobre();
                break;
            case "Sair":
                System.exit(0);
                break;
        }
    }
}
