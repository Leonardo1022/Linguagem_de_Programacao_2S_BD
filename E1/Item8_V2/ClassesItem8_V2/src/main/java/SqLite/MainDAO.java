package SqLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Musica.*;

public class MainDAO {
    private static final String url = "jdbc:sqlite:musica.db";

    public static void main(String[] args) throws Exception {


        // Criar tabelas
        criarTabelas();

        // Inserção de dados
        inserirArtista("The Beattles");
        inserirArtista("Ren");
        inserirArtista("Arctic Monkeys");

        listarMusicas();
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
            //Insere, e se já existir não adiciona
            String sql = "INSERT OR IGNORE INTO Artista(nome) VALUES(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.executeUpdate();
            ps.close();
        }
    }

    public static void inserirAlbum(Album album) throws SQLException {
        String sql = "INSERT OR IGNORE INTO Album(nome, foto, data_lancamento, artista_nome) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, album.getNome());
            ps.setString(2, album.getFoto().getUrl());
            ps.setString(3, album.getData_lancamento().toString());
            ps.setString(4, album.getArtista().getNome());
            ps.executeUpdate();
        }
    }

    public static void inserirMusica(Musica musica) throws SQLException {
        String sql = "INSERT INTO Musica(nome, album_nome, artista_nome) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, musica.getNome());
            //Permite que o valor seja nulo
            if (musica.getAlbum() != null) {
                ps.setString(2, musica.getAlbum().toString());
            } else {
                ps.setNull(2, java.sql.Types.VARCHAR);
            }
            ps.setString(3, musica.getArtista().toString());
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

    public static List<Artista> coletarArtistas() throws SQLException {
        List<Artista> artistas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("SELECT nome FROM artista");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Artista a = new Artista(rs.getString("nome"), null, null);
                artistas.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return artistas;
    }

    public static List<Album> coletarAlbuns(Artista artista) throws SQLException {
        List<Album> albuns = new ArrayList<>();
        String art = artista.getNome();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("SELECT nome FROM album WHERE artista_nome = ?")) {
            stmt.setString(1, art);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Album a = new Album(rs.getString("nome"), null, artista, null, null);
                    albuns.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return albuns;
    }

    public static void listarMusicas() {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM musica")) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("nome"));
                    System.out.println(rs.getString("album_nome"));
                    System.out.println(rs.getString("artista_nome"));
                    System.out.println("===================");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}