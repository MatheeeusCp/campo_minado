import java.util.Scanner;

public class CampoMinado extends Sistema {
    public CampoMinado(int dificuldade, Scanner teclado) {
        this.teclado = teclado;

        switch (dificuldade) {
            case 1:
                this.numeroBombas = 10;
                this.jogoReal = new Quadrado[8][10];
                this.tela = new Quadrado[8][10];
                break;
            case 2:
                this.numeroBombas = 40;
                this.jogoReal = new Quadrado[14][18];
                this.tela = new Quadrado[14][18];
                break;
            default:
                this.numeroBombas = 99;
                this.jogoReal = new Quadrado[20][24];
                this.tela = new Quadrado[20][24];
                break;
        }

        iniciaJogo();
    }
    private final Scanner teclado;
    private final int numeroBombas;
    private final Quadrado[][] jogoReal; //Essa matriz será utilizada para desenvolver a lógica do jogo.
    private final Quadrado[][] tela; //Essa m// atriz será a mostrada para o usuário.

    private int quadradosValidados = 0; //Variável para guardar a quantidade de casas que foram validadas na matriz
    int vitoria = 0; //Variável para guardar se o usuário ganhou ou perdeu, e retornar esse informação posteriormente

    //Função que setta a matriz do campo minado para o usuário.
    private void iniciaJogo() {
        System.out.println();

        //Loop que setta a matriz 'tela' com '#'.
        for (int i = 0; i < tela.length; i++) {
            for (int j = 0; j < tela[0].length; j++) {
                tela[i][j] = new Quadrado();
                tela[i][j].setValor("#");
            }
        }
        //Loop que setta a matriz 'jogo real' com ' '.
        for (int i = 0; i < tela.length; i++) {
            for (int j = 0; j < jogoReal[0].length; j++) {
                jogoReal[i][j] = new Quadrado();
                jogoReal[i][j].setValor(" ");
            }
        }

        //Loop que setta na matriz 'jogo real' as bombas em posições aleatórias.
        for (int i = 0; i < numeroBombas; i++) {
            final int x = (int) (jogoReal.length * Math.random());
            final int y = (int) (jogoReal[0].length * Math.random());
            //Se, por um acaso, houver a tentativa de colocar uma bomba em cima da outra, isso não será permitido.
            if (!jogoReal[x][y].getValor().equals("*")) {
                jogoReal[x][y].setValor("*");
            }else{
                i--;
            }
        }

        menuJogo();
    }

    private void menuJogo() {
        int linha = 0;
        int coluna = 0;
        int i = -1;
        while (i != 0) {
            mostraJogo(); //Printa o a matriz para o usuário
            System.out.println("\n-------------------------");
            //Coleta de linha e coluna da jogada do usuário
            linha = menuLinha();
            coluna = menuColuna();

            final boolean validado = validaCampos(linha, coluna); //Valida se os valores digitados são válidos ou se o
            //campo escolhido já foi utilizado anteriormente.


            if (validado) {
                //Se os valores são válidos, valida se há uma bomba no local desejado.
                if (jogoReal[linha][coluna].getValor().equals("*")) {
                    vocePerdeu();//Setta o ambiente de jogo onde o usuário perde.
                    i = 0;
                } else {
                    verificarBombas(linha, coluna);//Se não tem uma bomba, entra nessa lógica central do sistema
                    //que faz a validação da quantidade de bombas ao redor do local selecionado pelo usuário.

                    //Se a quantidade de quadrados validados for o valor de quantidade de quadrados da matriz-bombas
                    //significa que o usuário ganhou.
                    if (quadradosValidados == (jogoReal.length * jogoReal[0].length - numeroBombas)) {
                        voceGanhou();//Setta o ambiente de jogo onde o usuário ganha.
                        i = 0;
                    }
                }
            } else {
                System.out.println("Tente mais uma vez\n");
            }
        }
    }
    //Função que mostra a matrix settada anteriormente para o usuário.
    private void mostraJogo() {
        String linhas = "-";
        System.out.print("     ");
        //Loop para colocar os números da coordenada x no topo da matriz.
        for (int i = 0; i < tela[0].length; i++) {
            if (i < 10) {
                System.out.print("0" + i + "  ");
            } else {
                System.out.print(i + "  ");
            }
        }
        System.out.print("\n   -");
        for (int i = 0; i < tela[0].length; i++) {
            System.out.print("----");
        }
        System.out.print("\n");

        //Loop para colocar os números da coordenada y no canto esquerdo da matriz.
        for (int i = 0; i < tela.length; i++) {
            if (i < 10) {
                System.out.print("0" + i + " | ");
            } else {
                System.out.print(i + " | ");
            }
            //Loop que preenche a matriz para o usuário com '#'.
            for (int j = 0; j < tela[0].length; j++) {
                System.out.print(tela[i][j].getValor() + "   ");
            }
            System.out.println();
        }
    }

    private int menuLinha() {
        int linha;

        System.out.println("Digite a linha:");
        try {
            linha = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }

        return linha;
    }

    private int menuColuna() {
        int coluna;

        System.out.println("Digite a coluna:");
        try {
            coluna = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }

        return coluna;
    }

    private boolean validaCampos(int linha, int coluna) {
        if (linha == -1 && coluna == -1) {
            System.out.println("Campos inválidos");
            return false;
        }

        if (linha >= jogoReal.length || linha < 0) {
            System.out.println("Linha inexistente");
            return false;
        }

        if (coluna >= jogoReal[0].length || coluna < 0) {
            System.out.println("Coluna inexistente");
            return false;
        }

        if (jogoReal[linha][coluna].isValidado()) {
            System.out.println("Coordenada já usada");
            return false;
        }

        return true;
    }

    private void vocePerdeu() {
        System.out.println("");
        System.out.println("Você PERDEU!");
        abrirCampo();//Abre todos campos da matriz
        mostraJogo();//Mostra a matriz para o usuário
    }

    private void voceGanhou() {
        vitoria = 1;
        System.out.println("");
        System.out.println("Você GANHOU!");
        mostraJogo();//Mostra a matriz para o usuário
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

        //Faz algumas validações para garantir que os quadrados a serem validados são os quadrados ao redor do quadrado
        //selecionado
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

        //Valida se há alguma bomba ao redor do local selecionado e atribui a variável bombas
        for (int i = minLinha; i <= maxLinha; i++) {
            for (int j = minColuna; j <= maxColuna; j++) {
                if (jogoReal[i][j].getValor().equals("*")) {
                    bombas++;
                }
            }
        }

        //Setta, em ambas as matrizes, o valor de bombas no local escolhido e também setta o atributo "validado"
        //como true no quadrado, para não ser validado novamente.
        tela[linha][coluna].setValor(String.valueOf(bombas));
        tela[linha][coluna].setValidado(true);
        jogoReal[linha][coluna].setValor(String.valueOf(bombas));
        jogoReal[linha][coluna].setValidado(true);
        //Guardamos a quantidade de quadrados validados para definir se o usuário ganhou ou não o jogo.
        quadradosValidados++;

        //Se a quantidade de bombas que foram encontradas ao redor do quadrado escolhido foi de 0, fazemos a mesma
        //validação só que com as bombas ao redor da atual validada, entrando em um loop de validação até uma área de
        //quadrados vazios for descoberta.
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
        //Percorre toda a matriz abrindo os campos.
        for (int x = 0; x < jogoReal.length; x++) {
            for (int y = 0; y < jogoReal[0].length; y++) {
                if (jogoReal[x][y].getValor().equals("*")) {
                    tela[x][y].setValor("*");
                    continue;
                }
                //É uma versão reduzida do método "verificarBombas", que passa casa a casa revelando a quantidade de
                //bombas presente ao redor das coordenadas;
                descobrirCampos(x, y);
            }
        }
    }

    private void descobrirCampos(int linha, int coluna) {
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

        tela[linha][coluna].setValor(String.valueOf(bombas));
        tela[linha][coluna].setValidado(true);
        jogoReal[linha][coluna].setValor(String.valueOf(bombas));
        jogoReal[linha][coluna].setValidado(true);
    }
}
