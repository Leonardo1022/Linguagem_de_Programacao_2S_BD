package Musica;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.List;

public class Album {
    private String nome;
    private Image foto;
    private LocalDate data_lancamento;
    private Artista artista;
    private List<Musica> musicas;

    public Album(String nome, Image foto, Artista artista, LocalDate data_lancamento, List<Musica> musicas) {
        this.nome = nome;
        this.foto = foto;
        this.data_lancamento = data_lancamento;
        this.artista = artista;
        this.musicas = musicas;
    }

    public Album(String nome, Image foto, Artista artista, LocalDate data_lancamento) {
        this.nome = nome;
        this.foto = foto;
        this.artista = artista;
        this.data_lancamento = data_lancamento;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Image getFoto() {
        return foto;
    }
    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public LocalDate getData_lancamento() {
        return data_lancamento;
    }
    public void setData_lancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public Artista getArtista() {
        return artista;
    }
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return nome; // Aqui definimos o que aparece no ComboBox
    }

    public void tocar() {
        System.out.println("O instrumento foi tocado!");
    }
}
