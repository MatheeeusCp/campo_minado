package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JogoDAOJDBC implements JogoDAO{

    private JDBCUtil banco;

    public JogoDAOJDBC(){
        banco = new JDBCUtil();
    }

    @Override
    public void inserirJogo(Jogo jogo) {
        int id = jogo.getId();
        String dataJogo = jogo.getDataJogo();
        String duracaoJogo = jogo.getDuracaoJogo();
        int dificuldade = jogo.getDificuldade();

        String sql = "INSERT INTO jogo VALUES ("+id+","+"'"+dataJogo+"'"+","+"'"+duracaoJogo+"'"+","+dificuldade+");";
        try {
            Statement statement = banco.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Jogo> listar(int dificuldade) {
        ArrayList<Jogo> jogos = new ArrayList<Jogo>();
        String sql = "";
        if (dificuldade == 1 || dificuldade == 2 || dificuldade ==3) {
            sql = "SELECT * FROM jogo WHERE dificuldade = " + dificuldade + " ORDER BY duracaoJogo;";
        }else{
            sql = "SELECT * FROM jogo ORDER BY duracaoJogo;";
        }
        try {
            Statement statement = banco.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Jogo jogo = new Jogo();
                jogo.setId(resultSet.getInt(1));
                jogo.setDataJogo(String.valueOf(resultSet.getDate(2)));
                jogo.setDuracaoJogo(String.valueOf(resultSet.getTime(3)));
                jogo.setDificuldade(resultSet.getInt(4));
                jogos.add(jogo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogos;
    }

}
