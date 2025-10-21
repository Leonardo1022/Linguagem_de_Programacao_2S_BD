package Musica;

import javafx.scene.image.Image;

public class Instrumento {
    private String tipo;
    private Image foto;
    private long valor;

    public Instrumento(String tipo, Image foto, long valor) {
        this.tipo = tipo;
        this.foto = foto;
        this.valor = valor;
    }

    public void tocar() {
        System.out.println("O instrumento foi tocado!");
    }
}
