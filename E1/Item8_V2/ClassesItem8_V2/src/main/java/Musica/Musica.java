package Musica;

import java.util.List;

public class Musica {
    private String nome;
    private Artista artista;
    private Album album;

    public Musica(String nome, Artista artista, Album album) {
        this.nome = nome;
        this.artista = artista;
        this.album = album;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Artista getArtista() {
        return artista;
    }
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void entreterPublico() {
        System.out.println("O público é entretido");
    }
}
