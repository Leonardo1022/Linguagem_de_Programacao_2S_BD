package ImgVikMuniz;

import javafx.scene.paint.Color;

public class Veiculo {
    String marca;
    Color cor;
    String modelo;

    public Veiculo(String marca, Color cor, String modelo) {
        this.marca = marca;
        this.cor = cor;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public Color getCor() {
        return cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void andar() {
        System.out.println("Veículo andando");
    }
}
