package database;

public class Jogo {
    private int id;
    private String dataJogo;
    private String duracaoJogo;
    private int dificuldade;
    public Jogo(){}

    public Jogo(int id, String dataJogo, String duracaoJogo, int dificuldade) {
        this.id = id;
        this.dataJogo = dataJogo;
        this.duracaoJogo = duracaoJogo;
        this.dificuldade = dificuldade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataJogo() {
        return dataJogo;
    }

    public void setDataJogo(String dataJogo) {
        this.dataJogo = dataJogo;
    }

    public String getDuracaoJogo() {
        return duracaoJogo;
    }

    public void setDuracaoJogo(String duracaoJogo) {
        this.duracaoJogo = duracaoJogo;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }
}
