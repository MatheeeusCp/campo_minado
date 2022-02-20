import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Historico implements ActionListener {

    //Aqui é uma lista do Objeto do historico
    String[][] historico = {{"02/02/2022", "00:14:00"}, {"05/02/2022", "00:54:00"}};

    JComboBox<String> combo;

    Historico() {
        JFrame frame = new JFrame("Histórico");
        frame.setVisible(true);
        frame.setSize(200, 300);
        frame.setLayout(new FlowLayout());

        JLabel nivel = new JLabel("Nível: ");
        frame.add(nivel);

        String[] niveis = {"Fácil", "Médio", "Difícil"};
        combo = new JComboBox<String>(niveis);
        combo.addActionListener(this);
        frame.add(combo);

        String[] colunas = {"Data", "Duração"};
        JTable tabela = new JTable(historico, colunas);
        frame.add(tabela);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String nivel = (String) cb.getSelectedItem();

        // Aqui tu limpa a lista "historico" e coloca os devidos valores
        switch(nivel) {
            case "Fácil":
                break;
            case "Médio":
                break;
            case "Difícil":
                break;
        }
    }
}