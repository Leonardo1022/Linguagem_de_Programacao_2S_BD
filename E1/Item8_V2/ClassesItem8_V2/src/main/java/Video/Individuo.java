package Video;

public class Individuo {
    private String nome;
    private int n;
    private int hp;

    public void falar() {
        System.out.println("Falando");
    }
    public void atacar() {
        System.out.println("Atacando");
    }
    public void darItem() {
        System.out.println("O indivíduo entregou um item");
    }
}
