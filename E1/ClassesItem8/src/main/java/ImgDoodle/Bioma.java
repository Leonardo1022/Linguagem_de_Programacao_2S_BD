package ImgDoodle;
import java.util.List;

public class Bioma {
    private String nome;
    private List<Animal> animais;
    private int temperatura;

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
