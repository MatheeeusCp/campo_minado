import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;

public class Sistema {
    public Sistema() {
        this.teclado = new Scanner(System.in);
    }

    private final Scanner teclado;

    public void iniciar() {
        int escolha = -1;
        while (escolha != 0) {
            escolha = menu();
            switch(escolha) {
                case 1:
                    final Date hoje = new Date();
                    int[] retornoJogo = novoJogo(); //Retorna na primeira posicao se foi vitoria e na segunda posicao a dificuldade

                    final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    final SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");

                    final Duration duracao = Duration.ofMillis(new Date().getTime() - hoje.getTime());
                    final long hora = duracao.toHours();
                    final long minutos = duracao.toMinutes();
                    final long segundos = duracao.toSeconds();
                    final String tempo =  hora + ":" + minutos + ":" + segundos;

                    String vitoria;
                    if (retornoJogo[0] == 1){
                        vitoria = "Ganhou";
                    }else{
                        vitoria = "Perdeu";
                    }

                    String dificuldade;
                    if(retornoJogo[1] == 1){
                        dificuldade = "Nível Fácil";
                    }else if(retornoJogo[1] == 2){
                        dificuldade = "Nível Médio";
                    }else{
                        dificuldade = "Nível Difícil";
                    }

                    try {
                        //Salvar o jogo
                        FileWriter fw = new FileWriter("historico.txt", true);
                        fw.write(dateFormat.format(hoje) + " | " + dificuldade + " | " + "Duração " + horaFormat.format(horaFormat.parse(tempo)) + " | " + vitoria + "\r\n");
                        fw.close();
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar arquivo");
                    }


                    final int retorno = menuFinal();
                    if (retorno == 0) {
                        System.out.println("\nFinalizando Sistema...");
                        escolha = 0;
                    }
                    break;
                case 2:
                    exibirHistorico();
                    break;
                case 3:
                    limparHistorico();
                    break;
                case 4:
                    mostraCreditos();
                    break;
                case 0:
                    System.out.println("\nFinalizando Sistema...");
                    break;
                case -1:
                    break;
                default:
                    System.out.println("\nTente mais uma vez");
                    break;
            }
        }
    }

    private void exibirHistorico(){
        File file = new File("C:\\Users\\pankaj\\Desktop\\test.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true){
            assert sc != null;
            if (!sc.hasNextLine()) break;
            System.out.println(sc.nextLine());
        }
    }

    private void limparHistorico() {
        try {
            FileWriter fw = new FileWriter("historico.txt");
            fw.close();
            System.out.println("Histórico apagado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao limpar arquivo");
        }
    }

    private int menu() {
        System.out.println("------------------------------------");
        System.out.println("Menu principal");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Exibir histórico de vitórias");
        System.out.println("3 - Limpar histórico");
        System.out.println("4 - Créditos");
        System.out.println("0 - Sair");

        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nTente mais uma vez");
            return -1;
        }
    }

    private int menuFinal() {
        System.out.println("------------------------------------");
        System.out.println("1 - Voltar para o Menu Principal");
        System.out.println("0 - Sair");

        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nTente mais uma vez");
            return -1;
        }
    }

    private void mostraCreditos() {
        System.out.println("\nEste sistema foi desenvolvido por Felipe Matheus Nogueira da Silva e Matheus Cipriani\n");
    }

    private int[] novoJogo() {
        int escolha = -1;

        int vitoria = 0;
        int dificuldade = 0;
        int [] retorno = new int[2];

        while(escolha != 0) {
            escolha = menuNovoJogo();
            dificuldade = escolha;
            switch (escolha) {
                case 1:
                    CampoMinado campo1 = new CampoMinado(1, teclado);
                    vitoria = campo1.vitoria;
                    escolha = 0;
                    break;
                case 2:
                    CampoMinado campo2 = new CampoMinado(2, teclado);
                    vitoria = campo2.vitoria;
                    escolha = 0;
                    break;
                case 3:
                    CampoMinado campo3 = new CampoMinado(3, teclado);
                    vitoria = campo3.vitoria;
                    escolha = 0;
                    break;
                case -1:
                    break;
                default:
                    System.out.println("\nTente mais uma vez");
                    break;
            }
        }
        retorno[0] = vitoria;
        retorno[1] = dificuldade;
        return retorno;
    }

    private int menuNovoJogo() {
        System.out.println("---------------------------------");
        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");

        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nTente mais uma vez");
            return -1;
        }
    }
}
