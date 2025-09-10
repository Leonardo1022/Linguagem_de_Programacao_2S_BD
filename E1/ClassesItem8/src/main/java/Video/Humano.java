package Video;

public class Humano {
    private String nome;
    private String corPele;
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
