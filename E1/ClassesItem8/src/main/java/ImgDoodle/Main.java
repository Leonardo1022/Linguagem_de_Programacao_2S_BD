package ImgDoodle;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Profissional profissional = new Profissional();
        Bioma bioma = new Bioma();

        profissional.trabalhar();
        animal.andar();
        bioma.abrigar();
    }
}
