package database;

import java.util.ArrayList;

public interface JogoDAO {

    public void inserirJogo(Jogo jogo);

    ArrayList<Jogo> listar(int dificuldade);
}
