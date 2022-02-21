import database.Jogo;
import database.JogoDAO;
import database.JogoDAOJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Historico implements ActionListener {

    //Aqui é uma lista do Objeto do historico
    String[] data = {};
    String[] duracao = {};

    JogoDAO dao = new JogoDAOJDBC();
    ArrayList<Jogo> jogos = dao.listar(4);

    String [][] historico = new String[jogos.size()][2];

    JComboBox<String> combo;

    JTable tabela;
    JFrame frame;

    Historico() {
        for (int i = 0; i < jogos.size(); i++){
            historico[i][0] = String.valueOf(jogos.get(i).getDataJogo());
            historico[i][1] = String.valueOf(jogos.get(i).getDuracaoJogo());
        }


        frame = new JFrame("Histórico");
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
        tabela = new JTable(historico, colunas);
        frame.add(tabela);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String nivel = (String) cb.getSelectedItem();

        // Aqui tu limpa a lista "historico" e coloca os devidos valores
        switch(nivel) {
            case "Fácil":
                jogos = dao.listar(1);
                historico = new String[jogos.size()][2];
                for (int i = 0; i < jogos.size(); i++) {
                    historico[i][0] = String.valueOf(jogos.get(i).getDataJogo());
                    tabela.setValueAt(historico[i][0], i, 0);
                    historico[i][1] = String.valueOf(jogos.get(i).getDuracaoJogo());
                    tabela.setValueAt(historico[i][1], i, 1);
                }
                for (int i = 0; i < tabela.getRowCount(); i++){
                    if (i > jogos.size()-1){
                        tabela.setValueAt("", i, 0);
                        tabela.setValueAt("", i, 1);
                    }
                }
                break;
            case "Médio":
                jogos = dao.listar(2);
                historico = new String[jogos.size()][2];
                for (int i = 0; i < jogos.size(); i++) {
                    historico[i][0] = String.valueOf(jogos.get(i).getDataJogo());
                    tabela.setValueAt(historico[i][0], i, 0);
                    historico[i][1] = String.valueOf(jogos.get(i).getDuracaoJogo());
                    tabela.setValueAt(historico[i][1], i, 1);
                }
                for (int i = 0; i < tabela.getRowCount(); i++){
                    if (i > jogos.size()-1){
                        tabela.setValueAt("", i, 0);
                        tabela.setValueAt("", i, 1);
                    }
                }
                break;
            case "Difícil":
                jogos = dao.listar(3);
                historico = new String[jogos.size()][2];
                for (int i = 0; i < jogos.size(); i++) {
                    historico[i][0] = String.valueOf(jogos.get(i).getDataJogo());
                    tabela.setValueAt(historico[i][0], i, 0);
                    historico[i][1] = String.valueOf(jogos.get(i).getDuracaoJogo());
                    tabela.setValueAt(historico[i][1], i, 1);
                }
                for (int i = 0; i < tabela.getRowCount(); i++){
                    if (i > jogos.size()-1){
                        tabela.setValueAt("", i, 0);
                        tabela.setValueAt("", i, 1);
                    }
                }
                break;
        }
    }
}