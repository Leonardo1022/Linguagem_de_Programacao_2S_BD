package ImgDoodle;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal(1.2, 2, "Bob");
        Profissional profissional = new Profissional("Engenheiro", true, 12);
        ArrayList<String> animais = new ArrayList<String>();
        animais.add(animal.getNome());
        Bioma bioma = new Bioma("Savana", animais, 12);

        profissional.trabalhar();
        animal.andar();
        bioma.abrigar();
    }
}
