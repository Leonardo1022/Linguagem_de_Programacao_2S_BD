package ImgDoodle;
import java.util.ArrayList;

public class Bioma {
    private String tipo;
    private ArrayList<String> animais;
    private int temperatura;

    public Bioma(String tipo, ArrayList<String> animais, int temperatura) {
        this.tipo = tipo;
        this.animais = animais;
        this.temperatura = temperatura;
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<String> getAnimais() {
        return animais;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void abrigar(){
        System.out.println("Abrigando");
    }
    public void mudarTemperatura(){
        System.out.println("O bioma esquentou");
    }
    public void chover(){
        System.out.println("Começou a chover");
    }
}
