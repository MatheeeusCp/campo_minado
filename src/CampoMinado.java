import database.Jogo;
import database.JogoDAO;
import database.JogoDAOJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class CampoMinado implements ActionListener {
    int linhas;
    int colunas;
    int numeroBombas;
    JFrame frame;
    JButton[][] botoes;
    Quadrado[][] jogoReal;
    int dificuldade;
    final Date hoje = new Date(); //Variável criada para guardar a data e instante em que o usuário iniciou o jogo.

    public CampoMinado(int dificuldade) {
        int largura;
        int altura;
        this.dificuldade = dificuldade;

        switch (dificuldade) {
            case 1:
                this.numeroBombas = 10;
                linhas = 8;
                colunas = 10;
                largura = 500;
                altura = 500;
                botoes = new JButton[8][10];
                this.jogoReal = new Quadrado[8][10];
                break;
            case 2:
                this.numeroBombas = 40;
                linhas = 14;
                colunas = 18;
                largura = 780;
                altura = 780;
                botoes = new JButton[14][18];
                this.jogoReal = new Quadrado[14][18];
                break;
            default:
                this.numeroBombas = 99;
                linhas = 20;
                colunas = 24;
                largura = 1000;
                altura = 1000;
                botoes = new JButton[20][24];
                this.jogoReal = new Quadrado[20][24];
                break;
        }

        frame = new JFrame("Campo Minado");
        frame.setVisible(true);
        frame.setSize(largura, altura);
        frame.setLayout(new GridLayout(linhas, colunas));

        for (int i = 0; i < botoes.length; i++) {
            for (int j = 0; j < botoes[0].length; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].setActionCommand(i + ":" + j);
                botoes[i][j].addActionListener(this);
                botoes[i][j].setBackground(Color.gray);
                frame.add(botoes[i][j]);
            }
        }

        iniciaJogo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int linha;
        int coluna;

        String[] valores = e.getActionCommand().split(":");
        linha = Integer.parseInt(valores[0]);
        coluna = Integer.parseInt(valores[1]);

        if (jogoReal[linha][coluna].getValor().equals("*")) {
            abrirCampo();
        } else {
            verificarBombas(linha, coluna);
        }
    }

    private void iniciaJogo() {
        for (int i = 0; i < jogoReal.length; i++) {
            for (int j = 0; j < jogoReal[0].length; j++) {
                jogoReal[i][j] = new Quadrado();
                jogoReal[i][j].setValor(" ");
            }
        }

        for (int i = 0; i < numeroBombas; i++) {
            final int x = (int) (jogoReal.length * Math.random());
            final int y = (int) (jogoReal[0].length * Math.random());

            if (!jogoReal[x][y].getValor().equals("*")) {
                jogoReal[x][y].setValor("*");
            }
        }
    }

    private void verificarBombas(int linha, int coluna) {
        if (jogoReal[linha][coluna].isValidado()) {
            return;
        }

        int bombas = 0;
        int minLinha;
        int maxLinha;
        int minColuna;
        int maxColuna;

        if (linha != 0 && linha != jogoReal.length - 1) {
            minLinha = linha - 1;
            maxLinha = linha + 1;
        } else if (linha == 0) {
            minLinha = linha;
            maxLinha = linha + 1;
        } else {
            minLinha = linha - 1;
            maxLinha = linha;
        }

        if (coluna != 0 && coluna != jogoReal[0].length - 1) {
            minColuna = coluna - 1;
            maxColuna = coluna + 1;
        } else if (coluna == 0) {
            minColuna = coluna;
            maxColuna = coluna + 1;
        } else {
            minColuna = coluna - 1;
            maxColuna = coluna;
        }

        for (int i = minLinha; i <= maxLinha; i++) {
            for (int j = minColuna; j <= maxColuna; j++) {
                if (jogoReal[i][j].getValor().equals("*")) {
                    bombas++;
                }
            }
        }

        jogoReal[linha][coluna].setValor(String.valueOf(bombas));
        jogoReal[linha][coluna].setValidado(true);

        botoes[linha][coluna].setText(String.valueOf(bombas));
        botoes[linha][coluna].setBackground(Color.white);

        int cont = 0;

        for (Quadrado[] quadrados : jogoReal) {
            for (int j = 0; j < jogoReal[0].length; j++) {
                if (quadrados[j].isValidado()) {
                    cont++;
                }
            }
        }

        if (cont == (linhas * colunas - numeroBombas)){
            abrirCampo();
        }

        if (bombas == 0) {
            for (int i = minLinha; i <= maxLinha; i++) {
                for (int j = minColuna; j <= maxColuna; j++) {
                    if (i == linha && j == coluna) {
                        continue;
                    }

                    verificarBombas(i, j);
                }
            }
        }
    }

    private void abrirCampo() {
        for (int x = 0; x < botoes.length; x++) {
            for (int y = 0; y < botoes[0].length; y++) {
                if (jogoReal[x][y].getValor().equals("*")) {
                    botoes[x][y].setBackground(Color.red);
                    botoes[x][y].setIcon(new ImageIcon("images/mina-jogo.png"));
                } else {
                    botoes[x][y].setEnabled(false);
                }

            }
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");

        final Duration duracao = Duration.ofMillis(new Date().getTime() - hoje.getTime());
        final long hora = duracao.toHours();
        final long minutos = duracao.toMinutes();
        final long segundos = duracao.getSeconds();
        final String tempo =  hora + ":" + minutos + ":" + segundos; //Duração do jogo

        try {
            Jogo jogo = new Jogo(new Random().nextInt(9999), ""+dateFormat.format(hoje)+"",""+horaFormat.format(horaFormat.parse(tempo))+"", this.dificuldade);
            JogoDAO dao = new JogoDAOJDBC();
            dao.inserirJogo(jogo);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
