package Musica;

import java.util.List;

public class Artista {
    private String nome;
    private List<Musica> musicas;
    private List<Album> albuns;
    private String genero;

    public Artista(String nome, List<Musica> musicas, List<Album> albuns) {
        this.nome = nome;
        this.musicas = musicas;
        this.albuns = albuns;
    }

    public Artista(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public List<Album> getAlbums() {
        return albuns;
    }
    public void setAlbums(List<Album> albums) {
        this.albuns = albums;
    }

    @Override
    public String toString() {
        return nome; // Aqui definimos o que aparece no ComboBox
    }

    public void andar() {
        System.out.println("O indivíduo andou 5 metros");
    }
}
