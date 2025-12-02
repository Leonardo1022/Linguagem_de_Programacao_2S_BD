package SqLite;

import java.sql.*;

public class Main {
    private static final String url = "jdbc:sqlite:musica.db";

    public static void main(String[] args) throws Exception {


        // Criar tabela
        criarTabelas();

        // Inserir dados
        inserirArtista("The Beattles");
        inserirMusica("My Lady", null, "The");
    }

    private static void criarTabelas() throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        stmt.execute("PRAGMA foreign_keys = ON");

        // Tabela pai
        String sql = "CREATE TABLE IF NOT EXISTS Artista (" +
                "nome TEXT PRIMARY KEY" +
                ")";
        stmt.execute(sql);

        // Tabela Álbum com PK composta
        sql = "CREATE TABLE IF NOT EXISTS Album (" +
                "nome TEXT, " +
                "foto TEXT, " +
                "data_lancamento DATETIME, " +
                "artista_nome TEXT, " +
                "PRIMARY KEY (nome, artista_nome), " +
                "FOREIGN KEY (artista_nome) REFERENCES Artista(nome) ON DELETE CASCADE" +
                ")";
        stmt.execute(sql);

        // Tabela Música, FK para Album (PK composta) e Artista
        sql = "CREATE TABLE IF NOT EXISTS Musica (" +
                "nome TEXT, " +
                "album_nome TEXT, " +
                "artista_nome TEXT, " +
                "PRIMARY KEY (nome, artista_nome), " +
                "FOREIGN KEY (artista_nome) REFERENCES Artista(nome) ON DELETE CASCADE, " +
                "FOREIGN KEY (album_nome, artista_nome) REFERENCES Album(nome, artista_nome) ON DELETE CASCADE" +
                ")";
        stmt.execute(sql);

        stmt.close();
        conn.close();
    }

    public static void inserirArtista(String nome) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO Artista(nome) VALUES(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.executeUpdate();
            ps.close();
        }
    }

    public static void inserirAlbum(String nome, String foto, String data_lancamento, String artista_nome) throws SQLException {
        String sql = "INSERT INTO Album(nome, foto, data_lancamento, artista_nome) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, foto);
            ps.setString(3, data_lancamento);
            ps.setString(4, artista_nome);
            ps.executeUpdate();
        }
    }

    public static void inserirMusica(String nome, String album_nome, String artista_nome) throws SQLException {
        String sql = "INSERT INTO Musica(nome, album_nome, artista_nome) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, album_nome);
            ps.setString(3, artista_nome);
            ps.executeUpdate();
        }
    }

    public static void excluirArtista(String nome) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "DELETE FROM Artista WHERE nome = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.executeUpdate();
            ps.close();
        }
    }

}